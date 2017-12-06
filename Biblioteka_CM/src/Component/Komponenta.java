/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Component;

//import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Sinisa
 */
public abstract class Komponenta {
   public void add(Komponenta kom){} 
   public int getBrojKomponenti() {return 0;}
   public Komponenta getKomponenta(int i) {return null;}
   public Object getTipKomponente(){return null;}
}
