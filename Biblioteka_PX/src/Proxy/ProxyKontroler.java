/* ProxyKontroler.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */
package Proxy;

import RealSubject.Kontroler2;
import Subject.Kontroler;

public class ProxyKontroler extends Kontroler { // Proxy

    protected Kontroler2 kon;

    public ProxyKontroler(Kontroler2 kon1) {
        kon = kon1;
        kon.Povezi(this);
    }

    @Override
    public boolean zapamtiDomenskiObjekat() {
        return kon.zapamtiDomenskiObjekat();
    }

    @Override
    public boolean kreirajDomenskiObjekat() {
        return kon.kreirajDomenskiObjekat();
    }

    @Override
    public boolean obrisiDomenskiObjekat() {
        kon.prikaziPoruku("Zabranjeno je brisanje knjiga!!!");
        return false;
    }

    @Override
    public boolean promeniDomenskiObjekat() {
        return kon.promeniDomenskiObjekat();
    }

    @Override
    public boolean nadjiDomenskiObjekat() {
        return kon.nadjiDomenskiObjekat();
    }

    @Override
    public void napuniDomenskiObjekatIzGrafickogObjekta() {
        kon.napuniDomenskiObjekatIzGrafickogObjekta();
    }

}
