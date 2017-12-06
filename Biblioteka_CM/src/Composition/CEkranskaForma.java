/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Composition;

import AbstractProductA.EkranskaForma;

/**
 *
 * @author Sinisa
 */
public class CEkranskaForma extends Kompozicija{
 EkranskaForma ef;
 public CEkranskaForma(EkranskaForma ef1){ef=ef1;}
 @Override
 public Object getTipKomponente(){return ef.getClass();}
}
