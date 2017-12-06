/* DekoratorKontroler.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 19.11.2017
 */

package Decorator;


import Component.Kontroler;


public class DekoratorKontroler extends Kontroler{ // Decorator
       
    protected Kontroler kon;
    
    
    public DekoratorKontroler(Kontroler kon1) {kon=kon1;}
          
    @Override
    public boolean zapamtiDomenskiObjekat(){ return kon.zapamtiDomenskiObjekat();} 
            
    @Override
    public boolean kreirajDomenskiObjekat(){return kon.kreirajDomenskiObjekat();} 
    
    @Override
    public boolean obrisiDomenskiObjekat(){ return kon.obrisiDomenskiObjekat();} 
    
    @Override
    public boolean promeniDomenskiObjekat(){return kon.promeniDomenskiObjekat();} 
    
    @Override
    public boolean nadjiDomenskiObjekat(){return kon.nadjiDomenskiObjekat();} 
    
    @Override
    public void napuniDomenskiObjekatIzGrafickogObjekta(){kon.napuniDomenskiObjekatIzGrafickogObjekta();}

    }     