/* Kontroler1.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 06.11.2017
 */
package AbstractProductC;

import AbstractProductA.*;
import AbstractProductB.*;
import DomainClasses.Clan;
import DomainClasses.ClanKnjiga;
import DomainClasses.GeneralDObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Timer;
import java.util.TimerTask;
import DomainClasses.Knjiga;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public final class Kontroler1 extends Kontroler {

    public Kontroler1(EkranskaForma ef1, BrokerBazePodataka bbp1) {
        ef = ef1;
        bbp = bbp1;
        Povezi();
    }

    void Povezi() {
        javax.swing.JButton Kreiraj = ef.getPanel().getDodaj();
        javax.swing.JButton Promeni = ef.getPanel().getIzmeni();
        javax.swing.JButton Obrisi = ef.getPanel().getObrisi();
        javax.swing.JButton Nadji = ef.getPanel().getIzdaj();
        Kreiraj.addActionListener(new OsluskivacKreiraj(this));
        Promeni.addActionListener(new OsluskivacPromeni(this));
        Obrisi.addActionListener(new OsluskivacObrisi(this));
        Nadji.addActionListener(new OsluskivacNadji(this));

        javax.swing.JTextField jBtnID = ef.getPanel().getIDKnjige1(); // Promenljivo!!!
        jBtnID.addFocusListener(new OsluskivacNadji1(this));
    }

// Promenljivo!!!  
    void napuniDomenskiObjekatIzGrafickogObjekta() {
        knjiga = new Knjiga();
        knjiga.setKnjiga_id(getInteger(ef.getPanel().getIDKnjige()));
        knjiga.setIzdavac(ef.getPanel().getIzdavac());
        knjiga.setNaziv(ef.getPanel().getNaziv());
        knjiga.setAutori(ef.getPanel().getAutori());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date Datum = java.sql.Date.valueOf(sdf.format(ef.getPanel().getDatum()));
        knjiga.setDatum_izdavanja(Datum);
    }

// Promenljivo!!!
    void napuniGrafickiObjekatIzDomenskogObjekta(Knjiga k) {
        ef.getPanel().setIDKnjige(Integer.toString(k.getKnjiga_id()));
        ef.getPanel().setIzdavac(k.getIzdavac());
        ef.getPanel().setNaziv(k.getNaziv());
        ef.getPanel().setAutori(k.getAutori());
        ef.getPanel().setDatum(k.getDatum_izdavanja());
    }

// Promenljivo!!!
    void isprazniGrafickiObjekat() {

        ef.getPanel().setIDKnjige("");
        ef.getPanel().setIzdavac("N/A");
        ef.getPanel().setNaziv("N/A");
        ef.getPanel().setAutori("N/A");
        ef.getPanel().setDatum(new java.util.Date());
    }

    void prikaziPoruku() {
        ef.getPanel().setPoruka(poruka);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ef.getPanel().setPoruka("");
            }
        }, 3000);

    }

    public int getInteger(String s) {
        int broj = 0;
        try {
            if (s != null) {
                broj = Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            broj = 0;
        }

        return broj;
    }

    boolean zapamtiDomenskiObjekat() {

        bbp.makeConnection();
        boolean signal = bbp.insertRecord(knjiga);
        if (signal == true) {
            bbp.commitTransation();
            poruka = "Knjiga uspesno sacuvana."; // Promenljivo!!!
        } else {
            bbp.rollbackTransation();
            poruka = "Dogodila se greska prilikom cuvanja knjige."; // Promenljivo!!!  
        }
        prikaziPoruku();
        bbp.closeConnection();
        return signal;

    }

    boolean kreirajDomenskiObjekat() {
        boolean signal;
        knjiga = new Knjiga(); // Promenljivo!!!
        AtomicInteger counter = new AtomicInteger(0);

        bbp.makeConnection();
        if (!bbp.getCounter(knjiga, counter)) {
            return false;
        }
        if (!bbp.increaseCounter(knjiga, counter)) {
            return false;
        }

        knjiga.setKnjiga_id(counter.get()); // Promenljivo!!!
        signal = bbp.insertRecord(knjiga);
        if (signal == true) {
            bbp.commitTransation();
            napuniGrafickiObjekatIzDomenskogObjekta(knjiga);
            poruka = "Publikacija je uspesno kreirana."; // Promenljivo!!!
        } else {
            bbp.rollbackTransation();
            isprazniGrafickiObjekat();
            poruka = "Dogodila se greska prilikom kreiranja publikacije."; // Promenljivo!!!  
        }
        prikaziPoruku();
        bbp.closeConnection();
        return signal;
    }

    boolean obrisiDomenskiObjekat() {
        bbp.makeConnection();
        boolean signal = bbp.deleteRecord(knjiga);
        if (signal == true) {
            bbp.commitTransation();
            poruka = "Publikacija je uspesno obrisana."; // Promenljivo!!!
            isprazniGrafickiObjekat();
        } else {
            bbp.rollbackTransation();
            poruka = "Dogodila se greska prilikom brisanja publikacije."; // Promenljivo!!!
        }
        prikaziPoruku();
        bbp.closeConnection();
        return signal;
    }

    boolean promeniDomenskiObjekat() {
        bbp.makeConnection();
        boolean signal = bbp.updateRecord(knjiga);
        if (signal == true) {
            bbp.commitTransation();
            poruka = "Publikacija je uspesno izmenjena."; // Promenljivo!!!
        } else {
            bbp.rollbackTransation();
            isprazniGrafickiObjekat();
            poruka = "Dogodila se greska prilikom izmene publikacije."; // Promenljivo!!!
        }
        prikaziPoruku();
        bbp.closeConnection();
        return signal;
    }

    boolean nadjiDomenskiObjekat() {
        boolean signal;
        bbp.makeConnection();
        knjiga = (Knjiga) bbp.findRecord(knjiga); // Promenljivo!!!
        if (knjiga != null) {
            napuniGrafickiObjekatIzDomenskogObjekta(knjiga);
            poruka = "Publikacija je pronadjena."; // Promenljivo!!!
            signal = true;
        } else {
            isprazniGrafickiObjekat();
            poruka = "Dogodila se greska prilikom trazenja publikacije."; // Promenljivo!!!
            signal = false;
        }
        prikaziPoruku();
        bbp.closeConnection();
        return signal;
    }

    /*
    void prikaziUserDialog() {
        UserSelector selector = new UserSelector(ef, true);
        JTable tabela = selector.getTable();

        bbp.makeConnection();
        LinkedList<GeneralDObject> listaClanova = bbp.getList(new Clan());
        DefaultTableModel noviModel = new DefaultTableModel(new Object[][]{},
                new String[]{
                    "ID", "Ime Clana"
                });

        for (GeneralDObject clan : listaClanova) {
            Clan c = (Clan) clan;
            Object[] red = {c.getClan_id(), c.getIme_prezime()};
            noviModel.addRow(red);
        }

        tabela.setModel(noviModel);
        tabela.repaint();

        JButton izdaj = selector.getIzdaj();
        izdaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = tabela.getSelectedRow();
                int uid = (int) tabela.getValueAt(red, 0);
                bbp.makeConnection();
                boolean signal;
                ClanKnjiga ck = new ClanKnjiga(0, uid, knjiga.getKnjiga_id());
                AtomicInteger counter = new AtomicInteger(0);

                bbp.makeConnection();
                if (!bbp.getCounter(ck, counter)) {
                    return;
                }
                if (!bbp.increaseCounter(ck, counter)) {
                    return;
                }

                ck.setCk_id(counter.get()); // Promenljivo!!!
                signal = bbp.insertRecord(ck);
                if (signal == true) {
                    bbp.commitTransation();
                } else {
                    bbp.rollbackTransation();
                }
                bbp.closeConnection();
                selector.dispose();
            }
        });
        
        JButton otkazi = selector.getOtkazi();
        otkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poruka = "Iznajmljivanje knjige ponisteno";
                prikaziPoruku();
                selector.dispose();
            }
        });
        selector.setVisible(true);
    }
 */
}

class OsluskivacZapamti implements ActionListener {

    Kontroler1 kon;

    OsluskivacZapamti(Kontroler1 kon1) {
        kon = kon1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kon.napuniDomenskiObjekatIzGrafickogObjekta();
        kon.zapamtiDomenskiObjekat();

    }
}

class OsluskivacKreiraj implements ActionListener {

    Kontroler1 kon;

    OsluskivacKreiraj(Kontroler1 kon1) {
        kon = kon1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kon.kreirajDomenskiObjekat();

    }
}

class OsluskivacObrisi implements ActionListener {

    Kontroler1 kon;

    OsluskivacObrisi(Kontroler1 kon1) {
        kon = kon1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kon.napuniDomenskiObjekatIzGrafickogObjekta();
        kon.obrisiDomenskiObjekat();

    }
}

class OsluskivacPromeni implements ActionListener {

    Kontroler1 kon;

    OsluskivacPromeni(Kontroler1 kon1) {
        kon = kon1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kon.napuniDomenskiObjekatIzGrafickogObjekta();
        kon.promeniDomenskiObjekat();

    }
}

class OsluskivacNadji implements ActionListener {

    Kontroler1 kon;

    OsluskivacNadji(Kontroler1 kon1) {
        kon = kon1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kon.napuniDomenskiObjekatIzGrafickogObjekta();
        kon.nadjiDomenskiObjekat();
//        kon.prikaziUserDialog();
    }
}

class OsluskivacNadji1 implements FocusListener {

    Kontroler1 kon;

    OsluskivacNadji1(Kontroler1 kon1) {
        kon = kon1;
    }

    public void focusLost(java.awt.event.FocusEvent e) {
        kon.napuniDomenskiObjekatIzGrafickogObjekta();
        kon.nadjiDomenskiObjekat();

    }

    @Override
    public void focusGained(FocusEvent e) {

    }
}
