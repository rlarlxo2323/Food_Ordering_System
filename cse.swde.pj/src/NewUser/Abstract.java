package NewUser;

/**
 *
 * @author rlarl
 */
public abstract class Abstract {
    public final void Join(){
//        RRN_FieldFocusLost();
//        Overlap_TestAcionPerformed();
//        PW_FieldCaretUpdate();
//        RePW_FieldCaretUpdate();
//        Address_ButtonActionPerformed();
//        jButton1ActionPerformed(); //추상 
//        End_ButtonActionPerformed(); //추상
    }
    public abstract void jButton1ActionPerformed(java.awt.event.ActionEvent evt);
    public abstract void End_ButtonActionPerformed(java.awt.event.ActionEvent evt);

    /*
    public void RRN_FieldFocusLost(){
        String years = RRN_Field.getText();
        years = years.substring(0, 2);
        if(Integer.parseInt(years)>50){
            years="19"+years;
        }
        else{
            years="20"+years;
        }
        int year = Integer.parseInt(years);
        this.Age_Field.setText(Integer.toString(2021-year+1));
    }*/
}
