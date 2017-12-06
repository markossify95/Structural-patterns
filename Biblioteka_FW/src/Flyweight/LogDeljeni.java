/* LogDeljeni.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 19.11.2017
 */
package Flyweight;

import DomainClasses.GeneralDObject;

public class LogDeljeni implements Log{ // ConcreteFlyweight
  private final String nazivOperacije;
  private String poruka;
  public LogDeljeni(String nazivOperacije1, String poruka1){nazivOperacije = nazivOperacije1; poruka = poruka1;}  
  @Override
  public String vratiNazivOperacije(){return nazivOperacije;}
  @Override
  public String vratiDomenskiObjekat() {return "";}
  @Override
  public String vratiPoruku(){return poruka;}
}
