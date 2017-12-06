/* PoslovniPartner.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 19.11.2017
 */
package Client;

import Director.*;

public class PoslovniPartner { // Client

    Sef sef;

    PoslovniPartner(Sef sef1) {
        sef = sef1;
    }

    public static void main(String args[]) {
        PoslovniPartner pp;
        Sef sef = new Sef(); // promenljivo!!!
        pp = new PoslovniPartner(sef);
        pp.Konstruisi();
    }

    void Konstruisi() {
        sef.kreirajSoftverskiSistem();
        sef.kreirajEkranskuFormu();
        sef.kreirajBrokerBazePodataka();
        sef.kreirajKontroler();
        sef.prikaziEkranskuFormu();
    }

}
