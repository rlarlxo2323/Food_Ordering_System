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
public class OptionValue {

    public static String menuLabel = null;
    public static String sizeComboBox = null;
    public static int menuPrice;
    
    public static String getMenuLabel() {
        return menuLabel;
    }
    public static String getSizeComboBox() {
        return sizeComboBox;
    }
    public static int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }
    public void setSizeComboBox(String sizeComboBox) {
        this.sizeComboBox = sizeComboBox;
    }
    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }
}
