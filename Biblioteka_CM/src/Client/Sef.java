/* Sef.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */
package Client;

import AbstractFactory.*;
import AbstractProductA.*;
import AbstractProductB.*;
import AbstractProductC.*;
import Component.Komponenta;
import Composition.*;
import Leaf.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Sef { // Client

    Projektant proj; // Abstract Factory   

    class SoftverskiSistem // Complex Product
    {

        EkranskaForma ef; // AbstractProductA 
        BrokerBazePodataka bbp; // AbstractProductB 
        Kontroler kon; // AbstractProductC 

        SoftverskiSistem(EkranskaForma ef1, BrokerBazePodataka bbp1, Kontroler kon1) {
            ef = ef1;
            bbp = bbp1;
            kon = kon1;
        }

        void prikaziEkranskuFormu() {
            ef.prikaziEkranskuFormu();
        }
    }
    SoftverskiSistem ss;
    Komponenta kom3;
    PanelStablo ps;
    //int nivo;

    Sef(Projektant proj1) {
        proj = proj1;
    }

    public static void main(String args[]) {
        Sef sef;
// ConcreteFactory3
        Projektant proj = new Projektant1(); // Promenljivo!!! 
        sef = new Sef(proj);
        sef.Kreiraj();
    }

    void Kreiraj() {
        EkranskaForma ef = proj.kreirajEkranskuFormu();
        BrokerBazePodataka bbp = proj.kreirajBrokerBazePodataka();
        Kontroler kon = proj.kreirajKontroler(ef, bbp);
        ss = new SoftverskiSistem(ef, bbp, kon);
        ss.prikaziEkranskuFormu();
        napraviKompoziciju();
    }

    void napraviKompoziciju() {
        List l1 = new CPanel(ss.ef.getPanel());
        List l2 = new CBrokerBazePodataka(ss.bbp);
        Kompozicija kom1 = new CEkranskaForma(ss.ef);
        kom1.add(l1);
        Kompozicija kom2 = new CKontroler(ss.kon);
        kom2.add(kom1);
        kom2.add(l2);
        kom3 = new CProjektant(proj);
        kom3.add(kom1);
        kom3.add(l2);
        kom3.add(kom2);

        System.out.println(kom3.getTipKomponente());
        prikaziKompoziciju(kom3, 0);

        ps = new PanelStablo();
        // U deteCvor se nalazi kom3.
        DefaultMutableTreeNode deteCvor = ps.getTreePanel().addObject(null, kom3.getTipKomponente(), true);
        napuniStablo(ps, deteCvor, kom3, 0);
        ps.showFrame();

        /*DefaultMutableTreeNode p1, p2,p3;
   
   p1 = dtd.getTreePanel().addObject(null,kom3.getTipKomponente());
   p2 = dtd.getTreePanel().addObject(p1,kom1.getTipKomponente());
   p3 = dtd.getTreePanel().addObject(p1,kom2.getTipKomponente());
   dtd.getTreePanel().addObject(p1,l2.getTipKomponente());
   dtd.getTreePanel().addObject(p3,kom1.getTipKomponente());
   dtd.getTreePanel().addObject(p3,l2.getTipKomponente());
   dtd.getTreePanel().addObject(p2,l1.getTipKomponente());*/
    }

    void prikaziKompoziciju(Komponenta kom, int nivo) {
        nivo++;
        for (int i = 0; i < kom.getBrojKomponenti(); i++) {
            Komponenta km = kom.getKomponenta(i);
            for (int j = 0; j < nivo * 3; j++) {
                System.out.print("*");
            }
            System.out.print((i + 1) + ".");
            System.out.println(km.getTipKomponente());
            prikaziKompoziciju(km, nivo);

        }
    }

    void napuniStablo(PanelStablo ps, DefaultMutableTreeNode roditeljCvor, Komponenta kom, int nivo) {
        nivo++;
        for (int i = 0; i < kom.getBrojKomponenti(); i++) {
            Komponenta km = kom.getKomponenta(i);
            // U deteCvor se nalazi km.
            DefaultMutableTreeNode deteCvor = ps.getTreePanel().addObject(roditeljCvor, km.getTipKomponente(), true);
            napuniStablo(ps, deteCvor, km, nivo);
        }
    }

}
