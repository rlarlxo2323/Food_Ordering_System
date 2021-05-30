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
public class Login_Information {
    private static int user = 0;
    private static int owner = 1;
    private static int admin = 2;
    private State state;
    
    public Login_Information(){
        state = new User_Login();
    }
    
    public void setState(State state){
        this.state = state;
    }
    
    public void compare_Login(){
        state.compare_Login(this);
    }
    
    public void compare_Identity(){
        state.compare_Identity(this);
    }
}
