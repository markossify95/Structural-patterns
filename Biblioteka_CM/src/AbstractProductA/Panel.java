/* Panel.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */


package AbstractProductA;



import java.awt.event.KeyEvent;
import java.util.Date;

// Promenljivo!!!
public abstract class Panel extends javax.swing.JPanel{
       
       public abstract String getIDKnjige(); // Promenljivo!!!
       public abstract javax.swing.JTextField getIDKnjige1(); // Promenljivo!!!
       public abstract String getNaziv(); // Promenljivo!!!
       public abstract String getIzdavac(); // Promenljivo!!!
       public abstract String getAutori(); // Promenljivo!!!
       public abstract Date getDatum(); // Promenljivo!!!
       
       public abstract void setIDKnjige(String SifraPrijave); // Promenljivo!!!
       public abstract void setNaziv(String naziv); // Promenljivo!!!
       public abstract void setIzdavac(String izdavac); // Promenljivo!!!
       public abstract void setAutori(String Ocena); // Promenljivo!!!
       public abstract void setDatum(Date Datum); // Promenljivo!!!
       
       public abstract void setPoruka(String Poruka);
       
       public abstract javax.swing.JButton getDodaj(); 
       public abstract javax.swing.JButton getIzmeni(); 
       public abstract javax.swing.JButton getObrisi(); 
       public abstract javax.swing.JButton getIzdaj();
       
       
       
}
