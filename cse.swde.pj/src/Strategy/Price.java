/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

public abstract class Price {
    protected PriceInterface myoption;
    public void myOption(){
        myoption.option();
    }
    public abstract int display();
}
