/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

/**
 *
 * @author user
 */
public class Memento {

    private String pMenuName;
    private String pOption;
    private int pPrice ;
     

    public Memento(String pMenuName, String pOption, int pPrice) {
        this.pMenuName = pMenuName;
        this.pOption = pOption;
        this.pPrice = pPrice;
    }

    public String getData1() {
        return this.pMenuName;
    }

    public String getData2() {
        return this.pOption;
    }
    public int getData3() {
        return this.pPrice;
    }
}
