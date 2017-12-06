/* Kontroler.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package AbstractProductC;

import AbstractProductA.EkranskaForma;
import AbstractProductB.BrokerBazePodataka;
import DomainClasses.Knjiga;

public abstract class Kontroler {
    EkranskaForma ef;
    BrokerBazePodataka bbp;
    Knjiga knjiga;   // Promenljivo!!!
    String poruka;
         
}
