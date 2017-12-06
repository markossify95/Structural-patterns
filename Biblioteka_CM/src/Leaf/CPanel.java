/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leaf;

import AbstractProductA.Panel;

/**
 *
 * @author Sinisa
 */
public class CPanel extends List{
 Panel pan;
 public CPanel(Panel pan1) {pan = pan1;}
 @Override
 public Object getTipKomponente(){return pan.getClass();}
}
