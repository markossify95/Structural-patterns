/* KonkretniDekoratorKontroleraA.java
 * @autor  prof. dr Sinisa Vlajic,
 * Univerzitet u Beogradu
 * Fakultet organizacionih nauka 
 * Katedra za softversko inzenjerstvo
 * Laboratorija za softversko inzenjerstvo
 * 19.11.2017
 */
package ConcreteDecorators;

import Component.Kontroler;
import Decorator.DekoratorKontroler;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class KonkretniDekoratorKontroleraA extends DekoratorKontroler { // ConcretreDecoratorA

    // Dadaje se novo stanje postojecim stanjima.
    JLabel loperacija;

    public KonkretniDekoratorKontroleraA(Kontroler osnovni) {
        super(osnovni);
        ef = osnovni.getEkranskaForma();
        loperacija = new JLabel("");
        loperacija.setLocation(ef.getPanel().getX(), ef.getPanel().getY());
        loperacija.setSize(ef.getWidth(), ef.getHeight());
        ImageIcon ic = new ImageIcon(
                new ImageIcon(getClass().getResource("/Media/img/edit.png")).
                getImage().getScaledInstance(loperacija.getWidth(),
                        loperacija.getHeight(), Image.SCALE_DEFAULT));
        loperacija.setIcon(ic);
        ef.getPanel().add(loperacija);
        ef.getPanel().revalidate();
        ef.getPanel().repaint();
    }

    @Override
    public void setIkonu(String nazivIkone) {
        ImageIcon ii = new ImageIcon(
                new ImageIcon(getClass().getResource("/Media/img/" + nazivIkone)).
                getImage().getScaledInstance(loperacija.getWidth(),
                        loperacija.getHeight(), Image.SCALE_DEFAULT));
        loperacija.setIcon(ii);
        ef.getPanel().revalidate();
        ef.getPanel().repaint();
    }

    public void startAudioKlip(String nazivKlipa) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = getClass().getResource("/Media/audio/" + nazivKlipa);
                    System.out.println(url.getPath());
                    AudioInputStream audioIn;
                    audioIn = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip(null);
                    clip.open(audioIn);
                    clip.start();
                    clip.drain();
                    clip.close();
                } catch (Exception ex) {
                    System.out.println("Greska:" + ex);

                }
            }
        }).start();
    }

}
