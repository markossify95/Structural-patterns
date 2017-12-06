/* PrilagodiSrEn.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 08.11.2017
 */


package Target;

import Adaptee.Projektant;


public abstract class Prevodilac { // Target
    protected Projektant pr;
    public Prevodilac(Projektant pr1) {pr = pr1;}
    public abstract void createScreenForm();   
    public abstract void createDatabaseBroker();
    public abstract void createController ();
    public abstract void createSoftwareSystem();
    public abstract void showScreenForm();
}
