/* BrokerBazePodataka2.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */
package AbstractProductB;

import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import DomainClasses.GeneralDObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;

public class BrokerBazePodataka2 extends BrokerBazePodataka {

    Connection conn = null;
    String driver = "";
    String url = "";
    String dbusername = "";
    String dbpassword = "";

    @Override
    public boolean makeConnection() {
        readConfigProperties();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            conn.setAutoCommit(false); // Ako se ovo ne uradi nece moci da se radi roolback.
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BrokerBazePodataka2.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    void readConfigProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");
            prop.load(input);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            dbusername = prop.getProperty("dbusername");
            dbpassword = prop.getProperty("dbpassword");
        } catch (IOException ex) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public boolean insertRecord(GeneralDObject odo) {
        String upit = "INSERT INTO " + odo.getClassName() + " VALUES (" + odo.getAtrValue() + ")";
        return executeUpdate(upit);
    }

    @Override
    public boolean getCounter(GeneralDObject odo, AtomicInteger counter) {
        String sql = "SELECT counter FROM counter WHERE table_name = '" + odo.getClassName() + "'";

        ResultSet rs = null;
        Statement st = null;

        boolean signal = false; // record doesn't exist
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
            signal = rs.next();
            counter.set(rs.getInt("counter") + 1);
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka2.class.getName()).log(Level.SEVERE, null, ex);
            signal = false;
        } finally {
            close(null, st, rs);
        }
        return signal;

    }

    @Override
    public boolean increaseCounter(GeneralDObject odo, AtomicInteger counter) {
        String upit = "UPDATE counter SET counter =" + counter.get() + " WHERE table_name = '" + odo.getClassName() + "'";
        return executeUpdate(upit);
    }

    @Override
    public boolean deleteRecord(GeneralDObject odo) {
        String upit = "DELETE FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        return executeUpdate(upit);
    }

    @Override
    public boolean updateRecord(GeneralDObject odo, GeneralDObject odoold) {
        String upit = "UPDATE " + odo.getClassName() + " SET " + odo.setAtrValue() + " WHERE " + odoold.getWhereCondition();
        return executeUpdate(upit);
    }

    @Override
    public boolean updateRecord(GeneralDObject odo) {
        String upit = "UPDATE " + odo.getClassName() + " SET " + odo.setAtrValue() + " WHERE " + odo.getWhereCondition();
        return executeUpdate(upit);
    }

    public boolean executeUpdate(String upit) {
        Statement st = null;
        boolean signal = false;
        try {
            st = conn.prepareStatement(upit);
            int rowcount = st.executeUpdate(upit);
            if (rowcount > 0) {
                signal = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka2.class.getName()).log(Level.SEVERE, null, ex);
            signal = false;
        } finally {
            close(null, st, null);
        }
        return signal;
    }

    @Override
    public GeneralDObject findRecord(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            signal = rs.next(); // rs.next() vraca true ako ima postoji rezultat upita.
            if (signal == true) {
                odo = odo.getNewRecord(rs);
            } else {
                odo = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return odo;
    }

    @Override
    public LinkedList<GeneralDObject> getList(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName();
        LinkedList<GeneralDObject> lista = new LinkedList<>();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            while (rs.next()) {
                odo = odo.getNewRecord(rs);
                lista.add(odo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return lista;
    }

    @Override
    public boolean commitTransation() {
        try {
            conn.commit();
        } catch (SQLException esql) {
            return false;
        }
        return true;
    }

    @Override
    public boolean rollbackTransation() {
        try {
            conn.rollback();
        } catch (SQLException esql) {
            return false;
        }

        return true;
    }

    @Override
    public void closeConnection() {
        close(conn, null, null);
    }

    public void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodataka2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodataka2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodataka2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
