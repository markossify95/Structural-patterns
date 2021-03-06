/* Projektant4.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */
package Adaptee;

import AbstractProductA.*;
import AbstractProductB.*;
import AbstractProductC.*;

// Promenljivo!!!
public class Projektant4 extends Projektant {
    
     @Override
    public void kreirajSoftverskiSistem()
    { ss = new SoftverskiSistem();
    }
    
    
    @Override
    public void prikaziEkranskuFormu() 
      { ss.ef.prikaziEkranskuFormu();
      }
        
    @Override
    public void kreirajEkranskuFormu() {
        Panel pan = new MojPanel2(); // Promenljivo!!!
        ss.ef = new EkranskaForma2(); // Promenljivo!!!
        ss.ef.setPreferredSize(pan.getPreferredSize());
        ss.ef.setMinimumSize(pan.getPreferredSize());
        ss.ef.setMaximumSize(pan.getPreferredSize());
        ss.ef.setPanel(pan);
    }   
    
    @Override
    public void kreirajBrokerBazePodataka () 
      { ss.bbp  = new BrokerBazePodataka2(); // Promenljivo!!!
       }  
    
    @Override
    public void kreirajKontroler () {
        ss.kon = new Kontroler2(ss.ef,ss.bbp); // Promenljivo!!!
    }
}

