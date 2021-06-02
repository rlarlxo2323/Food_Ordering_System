/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decorator_Option;

import Decorator.Menu;
import Decorator.OptionDecorator;

/**
 *
 * @author user
 */
public class PizzafamilyOption extends OptionDecorator {
    public PizzafamilyOption(Menu menu){
        this.menu = menu;
    }
    public String getDescription(){
        return menu.getDescription();
    }
    public int price(){
        return 7000 + menu.price();
    }
}
