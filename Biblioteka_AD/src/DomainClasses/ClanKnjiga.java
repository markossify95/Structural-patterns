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
public class ClanKnjiga implements GeneralDObject {

    private int ck_id; //mozda jedan clan vise puta uzima jednu knjigu
    private int clan_id;
    private int knjiga_id;

    public ClanKnjiga() {
        ck_id = 0;
        clan_id = 0;
        knjiga_id = 0;
    }

    public ClanKnjiga(int ck_id, int clan_id, int knjiga_id) {
        this.ck_id = ck_id;
        this.clan_id = clan_id;
        this.knjiga_id = knjiga_id;
    }

    @Override
    public String getAtrValue() {
        return ck_id + ", " + clan_id + ", " + knjiga_id;
    }

    @Override
    public String setAtrValue() {
        return "ck_id=" + ck_id + ", clan_id=" + clan_id + ", knjiga_id=" + knjiga_id;
    }

    @Override
    public String getClassName() {
        return "clan_knjiga";
    }

    @Override
    public String getWhereCondition() {
        return "clan_id=" + clan_id + " AND knjiga_id=" + knjiga_id;
    }

    @Override
    public String getNameByColumn(int column) {
        String[] nm = {"ck_id", "clan_id", "knjiga_id"};
        return nm[column];
    }

    @Override
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException {
        return new ClanKnjiga(rs.getInt(1), rs.getInt(2),
                rs.getInt(3));
    }

    /**
     * @return the ck_id
     */
    public int getCk_id() {
        return ck_id;
    }

    /**
     * @param ck_id the ck_id to set
     */
    public void setCk_id(int ck_id) {
        this.ck_id = ck_id;
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

}
