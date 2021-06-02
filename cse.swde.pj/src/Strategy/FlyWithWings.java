/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import User.OptionValue;
import User.Store;
import User.StoreCategory;
import javax.swing.DefaultComboBoxModel;

public class FlyWithWings implements FlyBehavior {

    public void fly() {
        Store st = new Store();
        StoreCategory sc = new StoreCategory();
        String storeCategory = sc.getStoreCategory();
        String bOption[] = {"치즈"};
        if (storeCategory.equals("분식")) {
            st.Size_jComboBox.setModel(new DefaultComboBoxModel(bOption));
        }
    }
}
