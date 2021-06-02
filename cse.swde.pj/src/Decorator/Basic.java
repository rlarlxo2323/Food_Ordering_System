/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decorator;

import User.OptionValue;

public class Basic extends Menu {

    public Basic() {
        description = "Basic";
    }

    public int price() {
        OptionValue sv = new OptionValue();
        return sv.getMenuPrice();
    }
}
