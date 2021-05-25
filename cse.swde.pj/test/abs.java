
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
    JButton button;
    JLabel label;
    
    public final void Join(JButton a, JLabel b){
        getData(a, b);
        setLabel();
        printt();
    }
    
    public void getData(JButton a, JLabel b){
        this.button = a;
        this.label = b;
    }
    
    public void setLabel(){
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == button){
                label.setText("ㅋㅋㄹㅃㅃ");
                }
            }
        });
        
    };
    public abstract void printt();
}
