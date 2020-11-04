package ui;
import model.LinkedList;
import model.Gamer;
import java.util.Scanner;

public class Menu {
    private LinkedList lm;
    private Gamer gamer;
    Scanner sc = new Scanner(System.in);
    public Menu() {
        pMenu();
        gamer = new Gamer("",0);
    }
    public void pMenu(){
        System.out.println("Please enter a option \n\n(1) play game \n(2) see board \n(3) exit");
        int option = Integer.parseInt(sc.nextLine());
        switch (option){
            case 1:
                option1();
                pMenu();




                break;
            case 2:
                System.out.println(lm);
                pMenu();
                break;
            case 3:
                sc.close();
                break;
        }
    }

    public void option1(){
        System.out.println("please enter the nick name, the brown number, column number and mirror number after one space\n");
        String[] array = sc.nextLine().split(" ");
        String name = array[0];
        int n = Integer.parseInt(array[1]);
        int m = Integer.parseInt(array[2]);
        int k = Integer.parseInt(array[3]);
        lm = new LinkedList(n,m);
        System.out.println(lm);

    }
}
