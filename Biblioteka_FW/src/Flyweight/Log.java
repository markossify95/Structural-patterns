/* Log.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 19.11.2017
 */

package Flyweight;

import DomainClasses.GeneralDObject;

public interface Log { // Flyweight
    
    String vratiNazivOperacije();
    String vratiDomenskiObjekat();
    String vratiPoruku();
}
