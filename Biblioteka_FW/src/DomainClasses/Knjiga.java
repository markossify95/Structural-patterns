/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mark
 */
public class Knjiga implements GeneralDObject {

    private int knjiga_id;
    private String naziv;
    private String izdavac;
    private String autori;
    private Date datum_izdavanja;

    public Knjiga(int knjiga_id, String naziv, String izdavac, String autori, Date datum_izdavanja) {
        this.knjiga_id = knjiga_id;
        this.naziv = naziv;
        this.izdavac = izdavac;
        this.autori = autori;
        this.datum_izdavanja = datum_izdavanja;
    }

    public Knjiga() {
        knjiga_id = 0;
        naziv = "";
        izdavac = "";
        autori = "";
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = new Date();
        datum_izdavanja = java.sql.Date.valueOf(sm.format(dDatum));
    }

    @Override
    public String getAtrValue() {
        return knjiga_id + ", '" + naziv + "', '" + izdavac + 
                "', '" + autori + "', '" + datum_izdavanja + "'";
    }

    @Override
    public String setAtrValue() {
        return "knjiga_id=" + knjiga_id + ", naziv= '" + naziv + "'," +
                "izdavac= '"+ izdavac + "', " + "autori= '" + autori + 
                "', " + "datum_izdavanja= '" + datum_izdavanja + "'";    
    }

    @Override
    public String getClassName() {
        return "knjige";
    }

    @Override
    public String getWhereCondition() {
        return "knjiga_id= " + knjiga_id;
    }

    @Override
    public String getNameByColumn(int column) {
        String names[] = {"knjiga_id", "naziv", 
            "izdavac", "autori", "datum_izdavanja"};
        return names[column];
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new Knjiga(rs.getInt(1), rs.getString(2),
                rs.getString(3),rs.getString(4), rs.getDate(5));
    }

    /**
     * @return the knjiga_id
     */
    public int getKnjiga_id() {
        return knjiga_id;
    }

    /**
     * @param knjiga_id the knjiga_id to set
     */
    public void setKnjiga_id(int knjiga_id) {
        this.knjiga_id = knjiga_id;
    }

    /**
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * @param naziv the naziv to set
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * @return the izdavac
     */
    public String getIzdavac() {
        return izdavac;
    }

    /**
     * @param izdavac the izdavac to set
     */
    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    /**
     * @return the autori
     */
    public String getAutori() {
        return autori;
    }

    /**
     * @param autori the autori to set
     */
    public void setAutori(String autori) {
        this.autori = autori;
    }

    /**
     * @return the datum_izdavanja
     */
    public Date getDatum_izdavanja() {
        return datum_izdavanja;
    }

    /**
     * @param datum_izdavanja the datum_izdavanja to set
     */
    public void setDatum_izdavanja(Date datum_izdavanja) {
        this.datum_izdavanja = datum_izdavanja;
    }

}
