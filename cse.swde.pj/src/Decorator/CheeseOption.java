/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decorator;

/**
 *
 * @author user
 */
public class CheeseOption extends OptionDecorator {
    public CheeseOption(Menu menu){
        this.menu = menu;
    }
    
    public String getDescription(){
        return menu.getDescription();
    }
    public int price(){
        return 1000 + menu.price();
    }
}
