/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RefinedAbstraction;

import Abstraction.BrokerBazePodataka1;

/**
 *
 * @author mark
 */
public class BrokerBazePodataka1RA extends BrokerBazePodataka1{
    
    @Override
    protected String vratiDrajver(){return "com.mysql.jdbc.Driver";} // Promenljivo!!!
    @Override
    protected String vratiURL(){return "jdbc:mysql://127.0.0.1:3306/PAF4";} // Promenljivo!!!
    @Override
    protected String vratiKorisnickoIme(){return "root";} // Promenljivo!!!
    @Override
    protected String vratiSifru(){return "root";} // Promenljivo!!!
    @Override
    protected String vratiDBMS(){return "MySQL DBMS";} // Promenljivo!!!
    
}
