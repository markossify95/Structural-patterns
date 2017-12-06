/* PrilagodiSrEn1.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 08.11.2017
 */


package Adapter;

import Adaptee.Projektant;
import Target.Prevodilac;

public class Prevodilac1 extends Prevodilac{  // Adapter
    public Prevodilac1(Projektant pr1){super(pr1);}
    @Override
    public void createScreenForm(){pr.kreirajEkranskuFormu();}   
    @Override
    public void createDatabaseBroker(){pr.kreirajBrokerBazePodataka();}
    @Override
    public void createController (){pr.kreirajKontroler();}
    @Override
    public void createSoftwareSystem(){pr.kreirajSoftverskiSistem();}
    @Override
    public void showScreenForm(){pr.prikaziEkranskuFormu();}
    
}
