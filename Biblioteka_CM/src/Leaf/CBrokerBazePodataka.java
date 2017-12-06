/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leaf;

import AbstractProductB.BrokerBazePodataka;

/**
 *
 * @author Sinisa
 */
public class CBrokerBazePodataka extends List{
   BrokerBazePodataka bbp;
   public CBrokerBazePodataka(BrokerBazePodataka bbp1){bbp = bbp1;}
   @Override
   public Object getTipKomponente(){return bbp.getClass();}
}
