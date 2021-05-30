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
public class User_Login implements State{
    
    private static User_Login user = new User_Login();
    
    public User_Login(){
        
    }

    @Override
    public void compare_Login(Login_Information login_information) {
        System.out.println("유저 화면 출력");
    }

    @Override
    public void compare_Identity(Login_Information login_information) {
        login_information.setState(new User_Login());
    }
    
}
