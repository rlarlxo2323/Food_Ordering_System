/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import GetSet.OptionValue;
import User.Store;
import static User.Store.Size_jComboBox;
import GetSet.StoreCategory;
import javax.swing.DefaultComboBoxModel;

public class CategoyBox implements PriceInterface {

    public void fly() {
        String cafeOption[] = {"소", "중", "대"};
        String bunsickOption[] = {"치즈추가", "사이즈업"};
        String chinaOption[] = {"곱배기", "짬뽕국물추가"};
        String chickenOption[] = {"순살", "뼈"};
        String pizzaOption[] = {"R", "L", "F"};
        String jokbalOption[] = {"앞다리살", "뒷다리살"};
        String westernOption[] = {"Small", "Medium", "Large"};
        String fastfoodOption[] = {"단품", "세트"};
        String japanOption[] = {"미니우동", "주먹밥"};
        StoreCategory sc = new StoreCategory();
        String storeCategory = sc.getStoreCategory();

        if (storeCategory.equals("카페")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(cafeOption));
        } else if (storeCategory.equals("분식")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(bunsickOption));
        } else if (storeCategory.equals("중국집")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(chinaOption));
        } else if (storeCategory.equals("치킨")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(chickenOption));
        } else if (storeCategory.equals("피자")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(pizzaOption));
        } else if (storeCategory.equals("족발")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(jokbalOption));
        } else if (storeCategory.equals("양식")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(westernOption));
        } else if (storeCategory.equals("패스트푸드")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(fastfoodOption));
        } else if (storeCategory.equals("일식")) {
            Size_jComboBox.setModel(new DefaultComboBoxModel(japanOption));
        }

    }
}