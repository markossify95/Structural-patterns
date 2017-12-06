/* LogFactory.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */
package FlyweightFactory;

import DomainClasses.GeneralDObject;
import Flyweight.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogFactory  { // FlyweightFactory
  Log lg[];
  int brojLogova;
  boolean signal;
    
  public LogFactory(){lg=new Log[100]; brojLogova=0;}  
  public void dodajLog(String nazivOperacije,GeneralDObject gdo,String poruka)
  {  signal = false;
     int i;
     for(i=0; i<brojLogova; i++) 
     { if (lg[i].vratiNazivOperacije().equals(nazivOperacije) && lg[i].vratiPoruku().equals(poruka))
        { signal = true;
          break;
        }
     }
     
     if (signal)
     {lg [brojLogova] = lg[i];
     }
     else
     { lg[brojLogova] = new LogDeljeni(nazivOperacije,poruka);
     }
     
     Log lg1 = lg[brojLogova];
     brojLogova++;
     
     lg[brojLogova] = new LogNedeljeni(gdo);
     
     Log lg2 = lg[brojLogova];
     brojLogova++;
     
     zapamtiLogUDatoteci(lg1,lg2);
     
     
   } 
  
  void zapamtiLogUDatoteci(Log lg1,Log lg2) {
  BufferedWriter f = null;
      
   try {
     
     f = new BufferedWriter(new FileWriter("LogBiblioteka.txt", true));
     f.write("Datum:" + new Date() + " Naziv operacije:" + lg1.vratiNazivOperacije() + "\n");
     f.write("Domenski objekat:" + lg2.vratiDomenskiObjekat() + " Poruka:" + lg1.vratiPoruku() + "\n");
     f.flush();
     }
     catch(IOException ex) {System.out.println(ex);}
     finally {                       // always close the file
	 if (f != null) try {
	    f.close();
	 } catch (IOException ex1) {
	    
	 }
      } 
  
   }  
  
  
  public void prikaziTekuciLog()
  {    for(int i=0; i<brojLogova;) {
         System.out.println("Datum:" + new Date() + "Naziv operacije:" + lg[i].vratiNazivOperacije());
         System.out.println("Domenski objekat:" + lg[i+1].vratiDomenskiObjekat() + " Poruka:" + lg[i].vratiPoruku());
         i = i + 2;
       }
  }
}
