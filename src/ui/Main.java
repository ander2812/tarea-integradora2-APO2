package ui;

import model.LinkedList;

public class Main {
    Menu menu;
    public Main() {
        messenger();
        new Menu();
    }

    public void messenger(){
        System.out.println("=======================================");
        System.out.println("==============Laser game===============");
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        new Main();


    }

}
