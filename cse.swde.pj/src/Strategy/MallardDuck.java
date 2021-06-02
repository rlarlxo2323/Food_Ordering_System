/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import User.OptionValue;

/**
 *
 * @author 김두현
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        OptionValue ov = new OptionValue();
        String sizeBox = ov.getSizeComboBox();

    }

    public void display() {
        if () {
        }
    }
}
