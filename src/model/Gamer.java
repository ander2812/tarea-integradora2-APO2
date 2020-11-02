package model;

public class Gamer {
    private Gamer left;
    private Gamer right;

    public Gamer getLeft() {
        return left;
    }

    public void setLeft(Gamer left) {
        this.left = left;
    }

    public Gamer getRight() {
        return right;
    }

    public void setRight(Gamer right) {
        this.right = right;
    }

    private String fullName;
    private int score;
    public Gamer(String fullName, int score) {
        this.fullName = fullName;
        this.score = score;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
