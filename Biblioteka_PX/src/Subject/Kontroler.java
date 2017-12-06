/* Kontroler.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */

package Subject;

import AbstractProductA.EkranskaForma;
import AbstractProductB.BrokerBazePodataka;
import DomainClasses.Knjiga;

public abstract class Kontroler {
    protected EkranskaForma ef;
    protected BrokerBazePodataka bbp;
    protected Knjiga knjiga;   // Promenljivo!!!
    protected String poruka;
    
    public void Povezi(Kontroler kon) {}
    public abstract boolean zapamtiDomenskiObjekat();
    public abstract boolean kreirajDomenskiObjekat();
    public abstract boolean obrisiDomenskiObjekat();
    public abstract boolean promeniDomenskiObjekat();
    public abstract boolean nadjiDomenskiObjekat();
    public void napuniDomenskiObjekatIzGrafickogObjekta(){}
    public EkranskaForma getEkranskaForma(){return ef;}
    public void setIkonu(String nazivIkone){}
    public void startAudioKlip(String nazivKlipa){}
}
