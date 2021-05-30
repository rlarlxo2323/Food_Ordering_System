package State_Pattern;

/**
 *
 * @author rlarl
 */
public interface State {
    public void compare_Login(Login_Information login_information);
    public void compare_Identity(Login_Information login_information);
}
