/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mark
 */
public class Clan implements GeneralDObject{
    
    private int clan_id;
    private String ime_prezime;
    private int uplatio_clanarinu;

    public Clan() {
        clan_id = 0;
        ime_prezime = "";
        uplatio_clanarinu = 0;
    }

    public Clan(int clan_id, String ime_prezime, int uplatio_clanarinu) {
        this.clan_id = clan_id;
        this.ime_prezime = ime_prezime;
        this.uplatio_clanarinu = uplatio_clanarinu;
    }
    
    
    @Override
    public String getAtrValue() {
        return clan_id + ", '" + ime_prezime + "', " + uplatio_clanarinu;
    }

    @Override
    public String setAtrValue() {
        return "clan_id=" + clan_id + ", ime_prezime= '" + ime_prezime + "', uplatio_clanarinu=" + uplatio_clanarinu;  
    }

    @Override
    public String getClassName() {
        return "clanovi";
    }

    @Override
    public String getWhereCondition() {
        return "clan_id=" + clan_id;
    }

    @Override
    public String getNameByColumn(int column) {
        String[] names = {"clan_id", "ime_prezime", "uplatio_clanarinu"};
        return names[column];
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
         return new Clan(rs.getInt(1), rs.getString(2), rs.getInt(3));
    }

    /**
     * @return the clan_id
     */
    public int getClan_id() {
        return clan_id;
    }

    /**
     * @param clan_id the clan_id to set
     */
    public void setClan_id(int clan_id) {
        this.clan_id = clan_id;
    }

    /**
     * @return the ime_prezime
     */
    public String getIme_prezime() {
        return ime_prezime;
    }

    /**
     * @param ime_prezime the ime_prezime to set
     */
    public void setIme_prezime(String ime_prezime) {
        this.ime_prezime = ime_prezime;
    }

    /**
     * @return the uplatio_clanarinu
     */
    public int getUplatio_clanarinu() {
        return uplatio_clanarinu;
    }

    /**
     * @param uplatio_clanarinu the uplatio_clanarinu to set
     */
    public void setUplatio_clanarinu(int uplatio_clanarinu) {
        this.uplatio_clanarinu = uplatio_clanarinu;
    }
    
}
