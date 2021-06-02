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
public abstract class OptionDecorator extends Menu {
    protected Menu menu;
    
    @Override
    public abstract String getDescription();
}
