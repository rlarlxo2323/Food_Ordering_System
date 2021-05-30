/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State_Pattern;

/**
 *
 * @author 김기태
 */
public class Admin_Login implements State {
    private static Admin_Login admin = new Admin_Login();
    
    public Admin_Login(){
        
    }

    @Override
    public void compare_Login(Login_Information login_information) {
        System.out.println("관리자 화면 출력");
    }

    @Override
    public void compare_Identity(Login_Information login_information) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
