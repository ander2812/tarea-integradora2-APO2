package model;

public class Score {

    private Gamer root;

    public Score() {
        root = null;
    }

    public void addGamer(int score, String name) throws Exception{
        Gamer gamer = new Gamer(name, score);


    }

    private Gamer insertGamer(Gamer temp, int score, String name) throws Exception{

        if (temp == null){
            temp = new Gamer(name, score);
        }else if (score < temp.getScore()){
            Gamer left = insertGamer(temp.getLeft(), score, name);
            temp.setLeft(left);
        }else if (score > temp.getScore()){
            Gamer right = insertGamer(temp.getRight(), score, name);
            temp.setRight(right);
        }else{
            throw new Exception("Duplicate Gamer");
        }

        return temp;



    }

}
