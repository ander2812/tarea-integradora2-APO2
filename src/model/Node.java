package model;

public class Node {
    private int row;
    private int col;
    private String charNode = " ";
    private String shoot = "";

    private Node next;
    private Node prev;
    private Node up;
    private Node down;
    private String mirror = "";

    public String getCharNode() {
        return charNode;
    }

    public void setCharNode(String charNode) {
        this.charNode = charNode;
    }

    public String getMirror() {
        return mirror;
    }

    public void setMirror(String mirror) {
        this.mirror = mirror;
    }

    public Node(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getNameCol() {
        return (char)('A'+col);
    }

    public Node getNext() {
        return next;
    }

    public String getPosition() {
        return ""+(getRow()+1)+getNameCol()+"";
    }

    public Node getPrev() {
        return prev;
    }

    public Node getUp() {
        return up;
    }

    public Node getDown() {
        return down;
    }

    public void setPrev(Node p) {
        prev = p;
    }

    public void addMirror(String mirrorType){
        mirror = mirrorType;

    }

    public void setNext(Node n) {
        next = n;
    }

    public void setUp(Node u) {
        up = u;
    }

    public void setDown(Node d) {
        down = d;
    }

    public String toString() {
        return "["+charNode+shoot+"]";
    }
}
