/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Decorator.Basic;
import Decorator_Option.BunsickcheeseOption;
import Decorator.Menu;
import Decorator_Option.BunsicksizeOption;
import Decorator_Option.CafelargeOption;
import Decorator_Option.CafemediumOption;
import Decorator_Option.CafesmallOption;
import Decorator_Option.ChickenboneOption;
import Decorator_Option.ChickennoboneOption;
import Decorator_Option.ChinadoubleOption;
import Decorator_Option.ChinasoupOption;
import Decorator_Option.FastfoodsetOption;
import Decorator_Option.FastfoodsingleOption;
import Decorator_Option.JapanriceballOption;
import Decorator_Option.JapanudonOption;
import Decorator_Option.JokbalbackOption;
import Decorator_Option.JokbalfrontOption;
import Decorator_Option.PizzafamilyOption;
import Decorator_Option.PizzalargeOption;
import Decorator_Option.PizzaregularOption;
import Decorator_Option.WestlargeOption;
import Decorator_Option.WestmediumOption;
import Decorator_Option.WestsmallOption;
import static User.Store.Size_jComboBox;

/**
 *
 * @author 김두현
 */
public class OptionPrice extends Price {

    String optionBox = (String) Size_jComboBox.getSelectedItem();
    //String optionBox2 = (String)Size_jComboBoxH.getSelectedItem();

    public OptionPrice() {
        myoption = new CategoyBox();
    }

    public int display() {
        Menu m = new Basic();
        if (optionBox.equals("기본")) {
            m.price();
        } else if (optionBox.equals("소")) {
            m = new CafesmallOption(m);
        } else if (optionBox.equals("중")) {
            m = new CafemediumOption(m);
        } else if (optionBox.equals("대")) {
            m = new CafelargeOption(m);
        } else if (optionBox.equals("치즈추가")) {
            m = new BunsickcheeseOption(m);
        } else if (optionBox.equals("사이즈업")) {
            m = new BunsicksizeOption(m);
        } else if (optionBox.equals("곱배기")) {
            m = new ChinadoubleOption(m);
        } else if (optionBox.equals("짬뽕국물추가")) {
            m = new ChinasoupOption(m);
        } else if (optionBox.equals("순살")) {
            m = new ChickennoboneOption(m);
        } else if (optionBox.equals("뼈")) {
            m = new ChickenboneOption(m);
        } else if (optionBox.equals("R")) {
            m = new PizzaregularOption(m);
        } else if (optionBox.equals("L")) {
            m = new PizzalargeOption(m);
        } else if (optionBox.equals("F")) {
            m = new PizzafamilyOption(m);
        } else if (optionBox.equals("앞다리살")) {
            m = new JokbalfrontOption(m);
        } else if (optionBox.equals("뒷다리살")) {
            m = new JokbalbackOption(m);
        } else if (optionBox.equals("Small")) {
            m = new WestsmallOption(m);
        } else if (optionBox.equals("Medium")) {
            m = new WestmediumOption(m);
        } else if (optionBox.equals("Large")) {
            m = new WestlargeOption(m);
        } else if (optionBox.equals("단품")) {
            m = new FastfoodsingleOption(m);
        } else if (optionBox.equals("세트")) {
            m = new FastfoodsetOption(m);
        } else if (optionBox.equals("미니우동")) {
            m = new JapanudonOption(m);
        } else if (optionBox.equals("주먹밥")) {
            m = new JapanriceballOption(m);
        }
        return m.price();
    }
}
