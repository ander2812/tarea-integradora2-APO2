package model;

public class Score {

    private Gamer root;

    public Score() {
        root = null;
    }

    public Gamer getRoot() {
        return root;
    }

    public void setRoot(Gamer root) {
        this.root = root;
    }

    public void addGamer(int score, String name){
        Gamer newGamer = new Gamer(name, score);
        if (root==null){
            root = newGamer;
        }else{
            addGamer(root,newGamer);

        }



    }

    private void addGamer(Gamer currentGamer,Gamer newGamer){
        if (newGamer.getScore() < currentGamer.getScore() && currentGamer.getLeft() == null){
            currentGamer.setLeft(newGamer);
        }else if (newGamer.getScore() > currentGamer.getScore() && currentGamer.getRight() == null){
            currentGamer.setRight(newGamer);
        }else{
            if (newGamer.getScore()<currentGamer.getScore() & currentGamer.getLeft()!=null){
                addGamer(currentGamer.getLeft(),newGamer);

            }else{
                addGamer(currentGamer.getRight(),newGamer);
            }
        }



    }

}
