/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetSet;

/**
 *
 * @author 김두현
 */
public class OptionValue {

    public static int menuPrice;
    public static String myOption = null;
    
    public static int getMenuPrice() {
        return menuPrice;
    }
    public static String getMyOption() {
        return myOption;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }
    public void setMyOption(String myOption) {
        this.myOption = myOption;
    }
}
