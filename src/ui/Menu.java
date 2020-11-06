package ui;
import model.LinkedList;
import model.Score;
import java.util.Scanner;

public class Menu {
     LinkedList lm;
     Score score = new Score();
    Scanner sc = new Scanner(System.in);
    private String name = " ";
    public Menu() {
        pMenu();
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
                System.out.println(score.toString());
                System.out.println(" ");
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
        name = array[0];
        int n = Integer.parseInt(array[1]);
        int m = Integer.parseInt(array[2]);
        int k = Integer.parseInt(array[3]);
        lm = new LinkedList(n,m,k);
        System.out.println(lm.toString());
        subMenu();

    }

    public void subMenu() throws NullPointerException{
        System.out.println("please enter \n (1) show to laser \n (2) guess mirror \n (3) exit");
        int option = Integer.parseInt(sc.nextLine());
        switch (option){
            case 1:
                System.out.println("please enter the column identification and row identification");
                String id = sc.nextLine();
                lm.shootLaserId(id);
                System.out.println(lm.toString());
                subMenu();
                break;

            case 2:

                System.out.println("please enter the column identification and row identification");
                id = sc.nextLine();
                lm.selectNode(id);
                System.out.println(lm.toString());
                System.out.println(lm.getWinnerAnswer());
                System.out.println(name);
                if (lm.getWinnerAnswer() ==  lm.getMirrors()) {
                    System.out.println("congratulation");
                    System.out.println(" ");
                }

                subMenu();
                break;
            case 3:
                try {
                    score.addGamer(lm.getWinnerAnswer(), name);
                } catch (Exception Ignored) {
                }
                pMenu();
                break;
        }

    }
}
