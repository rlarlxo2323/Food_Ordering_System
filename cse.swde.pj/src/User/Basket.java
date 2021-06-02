/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author 김두현
 */
public class Basket {

    private String Data1;
    private int Data2;

    public Basket(String Data1, int Data2) {
        this.Data1 = Data1;
        this.Data2 = Data2;
    }

    public Memento CreateMemento() //Memento (상태저장) 
    {
        return new Memento(this.Data1, this.Data2);
    }

    public void RestorMemento(Memento memento) //Memento (상태복원)
    {
        this.Data1 = memento.getData1();
        this.Data2 = memento.getData2();
    }

    public void set_Data1(String Data1) {
        this.Data1 = Data1;
    }

    public void set_Data2(int Data2) {
        this.Data2 = Data2;
    }

    public String get_Data1() {
        return this.Data1;
    }

    public int get_Data2() {
        return this.Data2;
    }
}
