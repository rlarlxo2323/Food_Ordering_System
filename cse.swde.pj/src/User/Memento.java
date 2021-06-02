/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author user
 */
public class Memento {
    
    //Information의 상태정보를 가지고 있음
    private String Data1;
    private int Data2;
    
    public Memento(String Data1,int Data2)
    {
        this.Data1 = Data1;
        this.Data2 = Data2;
    }
    public String getData1()
    {
        return this.Data1;
    }
    public int getData2()
    {
        return this.Data2;
    }
}
