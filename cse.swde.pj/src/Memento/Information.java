/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

/**
 *
 * @author user
 */
public class Information {

    //Information의 상태정보를 가지고 있음
    private String pMenuName;
    private String pOption;
    private int pPrice;
    
    public Information(String pMenuName, String pOption, int pPrice) {
        this.pMenuName = pMenuName;
        this.pOption = pOption;
        this.pPrice = pPrice;
    }

    public Memento CreateMemento() {
        return new Memento(this.pMenuName, this.pOption, this.pPrice);
    }

    public void RestorMemento(Memento memento) {
        this.pMenuName = memento.getData1();
        this.pOption = memento.getData2();
        this.pPrice = memento.getData3();
    }

    public void setData1(String pMenuName) //데이터1의 값을 지정
    {
        this.pMenuName = pMenuName;
    }

    public void setData2(String pOption) //데이터2의 값을 지정
    {
        this.pOption = pOption;
    }
    
    public void setData3(int pPrice) //데이터2의 값을 지정
    {
        this.pPrice = pPrice;
    }

    public String getData1() {
        return this.pMenuName;
    }

    public String getData2() {
        return this.pOption;
    }

    public int getData3() {
        return this.pPrice;
    }

}
