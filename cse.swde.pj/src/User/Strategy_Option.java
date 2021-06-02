/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author 김두현
 */
public class Strategy_Option {
    protected Strategy_OptionPrice sizePrice;
    
    public Strategy_Option(Strategy_OptionPrice sizePrice) {
        this.sizePrice = sizePrice;
    }
    
    public void move(){
        sizePrice.action();
    }
    
    public void setPrice(Strategy_OptionPrice sizePrice){
        this.sizePrice = sizePrice;
    }
}
