/* LogNedeljeni.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 19.11.2017
 */
package Flyweight;

import DomainClasses.GeneralDObject;

public class LogNedeljeni implements Log{ // UnsharedConcreteFlyweight
    GeneralDObject gdo;
    
 public LogNedeljeni(GeneralDObject gdo1){ gdo = gdo1;}

    @Override
    public String vratiNazivOperacije() {
       return ""; 
    }
    
  @Override
  public String vratiDomenskiObjekat() {return gdo.getAtrValue();}
  
  @Override
  public String vratiPoruku(){return null;}
    
}
