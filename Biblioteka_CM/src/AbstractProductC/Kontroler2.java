/* Kontroler2.java
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Timer;
import java.util.TimerTask;
import DomainClasses.Knjiga;


public final class Kontroler2 extends Kontroler{
    
    
      
    public Kontroler2(EkranskaForma ef1,BrokerBazePodataka bbp1){ef=ef1;bbp=bbp1; Povezi(); osveziFormu();}
    
    void Povezi()
    {javax.swing.JButton Kreiraj = ef.getPanel().getDodaj();
     javax.swing.JButton Promeni = ef.getPanel().getIzmeni();
     javax.swing.JButton Obrisi = ef.getPanel().getObrisi();
     javax.swing.JButton Nadji = ef.getPanel().getIzdaj();
     Kreiraj.addActionListener( new OsluskivacKreiraj1(this));
     Promeni.addActionListener( new OsluskivacPromeni1(this));
     Obrisi.addActionListener( new OsluskivacObrisi1(this));
     Nadji.addActionListener( new OsluskivacNadji11(this));
     
     javax.swing.JTextField SifraPrijave = ef.getPanel().getIDKnjige1(); // Promenljivo!!!
     SifraPrijave.addFocusListener( new OsluskivacNadji12(this));
    }
    
// Promenljivo!!!    
void napuniDomenskiObjekatIzGrafickogObjekta()   {
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
void napuniGrafickiObjekatIzDomenskogObjekta(Knjiga k){
       ef.getPanel().setIDKnjige(Integer.toString(k.getKnjiga_id()));
        ef.getPanel().setIzdavac(k.getIzdavac());
        ef.getPanel().setNaziv(k.getNaziv());
        ef.getPanel().setAutori(k.getAutori());
        ef.getPanel().setDatum(k.getDatum_izdavanja());
    }

// Promenljivo!!!
void isprazniGrafickiObjekat(){

 ef.getPanel().setIDKnjige("");
 ef.getPanel().setIzdavac("");
 ef.getPanel().setNaziv("");
 ef.getPanel().setAutori("");
 ef.getPanel().setDatum(new java.util.Date());
}


void prikaziPoruku() 
{ ef.getPanel().setPoruka(poruka);
      
  Timer timer = new Timer();
  
  timer.schedule(new TimerTask() {
  @Override
  public void run() {
    ef.getPanel().setPoruka(""); 
  }
}, 3000);
  
}


void prikaziPoruku(String poruka) 
{ ef.getPanel().setPoruka(poruka);
      
  Timer timer = new Timer();
  
  timer.schedule(new TimerTask() {
  @Override
  public void run() {
    ef.getPanel().setPoruka(""); 
  }
}, 1000);
  
}

void osveziFormu() 
{    
  Timer timer = new Timer();
  
  timer.scheduleAtFixedRate(new TimerTask() {
  @Override
  public void run() {
      napuniDomenskiObjekatIzGrafickogObjekta();
      nadjiDomenskiObjekat();
      prikaziPoruku("Osvezavam...");
  }
}, 0,10000);
  
}

public int getInteger(String s) {
    int broj = 0;
    try
        {
            if(s != null)
                broj = Integer.parseInt(s);
        }
            catch (NumberFormatException e)
            { broj = 0;}
   
    return broj;
}


 
boolean zapamtiDomenskiObjekat(){ 
    
    bbp.makeConnection();
    boolean signal = bbp.insertRecord(knjiga);
    if (signal==true) 
        { bbp.commitTransation();
          poruka ="Publikacija je uspesno sacuvana.";
        }
        else
        { bbp.rollbackTransation();
          poruka ="Dogodila se greska prilikom cuvanja publikacije."; // Promenljivo!!!  
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal; 
       
    }
    
boolean kreirajDomenskiObjekat(){
    boolean signal;
    knjiga= new Knjiga(); // Promenljivo!!!
    AtomicInteger counter = new AtomicInteger(0);
    
    bbp.makeConnection();
    if (!bbp.getCounter(knjiga,counter)) return false;
    if (!bbp.increaseCounter(knjiga,counter)) return false;
          
    knjiga.setKnjiga_id(counter.get()); // Promenljivo!!!
    signal = bbp.insertRecord(knjiga);
    if (signal==true) 
        { bbp.commitTransation();
          napuniGrafickiObjekatIzDomenskogObjekta(knjiga);
            poruka = "Publikacija je uspesno kreirana."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
        isprazniGrafickiObjekat();
            poruka = "Dogodila se greska prilikom kreiranja publikacije."; // Promenljivo!!!  
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;
}

boolean obrisiDomenskiObjekat(){
    bbp.makeConnection();
    boolean signal = bbp.deleteRecord(knjiga);
    if (signal==true) 
        { bbp.commitTransation();
            poruka = "Publikacija je uspesno obrisana."; // Promenljivo!!!
            isprazniGrafickiObjekat();
        }
        else
        { bbp.rollbackTransation();
            poruka = "Dogodila se greska prilikom brisanja publikacije."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;   
}

boolean promeniDomenskiObjekat(){
    bbp.makeConnection();
    boolean signal = bbp.updateRecord(knjiga);
    if (signal==true) 
        { bbp.commitTransation();
            poruka = "Publikacija je uspesno izmenjena."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
          isprazniGrafickiObjekat();
            poruka = "Dogodila se greska prilikom izmene publikacije."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;   
}


boolean nadjiDomenskiObjekat(){
    boolean signal;
    bbp.makeConnection();
    knjiga = (Knjiga)bbp.findRecord(knjiga); // Promenljivo!!!
    if (knjiga != null) 
        { napuniGrafickiObjekatIzDomenskogObjekta(knjiga);
            poruka = "Publikacija je pronadjena."; // Promenljivo!!!
          signal = true;
        }
        else
        { isprazniGrafickiObjekat();
            poruka = "Dogodila se greska prilikom trazenja publikacije."; // Promenljivo!!!
          signal = false;
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;   
}

}

class OsluskivacZapamti1 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacZapamti1(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.zapamtiDomenskiObjekat();
        
    }
}

class OsluskivacKreiraj1 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacKreiraj1(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.kreirajDomenskiObjekat();
         
        
    }
}

class OsluskivacObrisi1 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacObrisi1(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.obrisiDomenskiObjekat();
        
    }
}

class OsluskivacPromeni1 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacPromeni1(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.promeniDomenskiObjekat();
        
    }
}

class OsluskivacNadji11 implements ActionListener
{   Kontroler2 kon;
 
    OsluskivacNadji11(Kontroler2 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.nadjiDomenskiObjekat();
        
    }
}

class OsluskivacNadji12 implements FocusListener
{   Kontroler2 kon;
 
    OsluskivacNadji12(Kontroler2 kon1) {kon = kon1;}
    

    public void focusLost(java.awt.event.FocusEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.nadjiDomenskiObjekat();
        
    }

    @Override
    public void focusGained(FocusEvent e) {
       
    }
}