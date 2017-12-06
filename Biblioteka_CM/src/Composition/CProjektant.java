/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Composition;

import AbstractFactory.Projektant;

/**
 *
 * @author Sinisa
 */
public class CProjektant extends Kompozicija{
  Projektant proj;
  public CProjektant(Projektant proj1){proj = proj1;} 
  @Override
  public Object getTipKomponente(){return proj.getClass();}
}
