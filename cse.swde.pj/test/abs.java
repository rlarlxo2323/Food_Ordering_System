
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rlarl
 */
public abstract class abs {
    JFrame Frame;
    boolean flag = true;
    JButton button;
    JLabel label;
    
    
    public final void Join(){
        //getData(JButton a, JLabel b);
        setLabel();
        printt();
    }
    public void getFrame(boolean a){
        if(a){
            this.Frame = new Test_Frame();
        }
    }
    public void setLabel(){
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == button){
                //this.e = e;
                label.setText("ㅋㅋㄹㅃㅃ");
                }
            }
        });
        
    };
    public abstract void printt();
}
