package model;

import java.util.Random;

public class LinkedList {
    private Node first;
    private int numRows;
    Random random = new Random();
    private int numCols;

    public LinkedList(int m, int n) {
        numRows = m;
        numCols = n;
        createMatrix();
    }

    private void createMatrix() {
        first = new Node(0,0);
        createRow(0,0,first);
    }

    private void createRow(int i, int j, Node currentFirstRow) {
        createCol(i,j+1,currentFirstRow,currentFirstRow.getUp());
        if(i+1<numRows) {
            Node downFirstRow = new Node(i+1,j);
            downFirstRow.setUp(currentFirstRow);
            currentFirstRow.setDown(downFirstRow);
            createRow(i+1,j,downFirstRow);
        }
    }

    private void createCol(int i, int j, Node prev, Node rowPrev) {
        if(j<numCols) {
            Node current = new Node(i, j);
            current.setPrev(prev);
            prev.setNext(current);

            if(rowPrev!=null) {
                rowPrev = rowPrev.getNext();
                current.setUp(rowPrev);
                rowPrev.setDown(current);
            }

            createCol(i,j+1,current,rowPrev);
        }
    }

    public void addMirrors(Node current, Node nextRowFirstColumn, int mirrors) {
        if (mirrors > 0) {
            int totalMirrorsAvailable = mirrors;
            if (hasMirror() && current.getMirror().equals("")) {
                totalMirrorsAvailable = mirrors - 1;
                current.addMirror(mirrorType());
            }
            if (current.getNext() != null) {
                addMirrors(current.getNext(), nextRowFirstColumn, totalMirrorsAvailable);
            } else if (current.getDown() != null) {
                addMirrors(nextRowFirstColumn, nextRowFirstColumn.getDown(), totalMirrorsAvailable);
            } else {
                addMirrors(first, first.getDown(), totalMirrorsAvailable);
            }
        }
    }

    public String toString() {
        String msg;
        msg = toStringRow(first);
        return msg;
    }

    public boolean hasMirror() {
        return random.nextInt(((numColumn * numRows) / 2)) == 0;
    }

    private String toStringRow(Node firstRow) {
        String msg = "";
        if(firstRow!=null) {
            msg = toStringCol(firstRow) + "\n";
            msg += toStringRow(firstRow.getDown());
        }
        return msg;
    }

    private String toStringCol(Node current) {
        String msg = "";
        if(current!=null) {
            msg = current.toString();
            msg += toStringCol(current.getNext());
        }
        return msg;
    }

    public String toString2() {
        String msg = "";

        return msg;
    }
}
