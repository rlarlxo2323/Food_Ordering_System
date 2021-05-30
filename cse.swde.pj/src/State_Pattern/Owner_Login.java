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
public class Owner_Login implements State{
    private static Owner_Login owner = new Owner_Login();
    
    public Owner_Login(){
        
    }

    @Override
    public void compare_Login(Login_Information login_information) {
        System.out.println("오너 화면 출력");
    }

    @Override
    public void compare_Identity(Login_Information login_information) {
        
    }
}
