/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Composition;

import AbstractProductC.Kontroler;

/**
 *
 * @author Sinisa
 */
public class CKontroler extends Kompozicija{
 Kontroler kon;
 public CKontroler(Kontroler kon1){kon=kon1;}
 @Override
 public Object getTipKomponente(){return kon.getClass();}
}
