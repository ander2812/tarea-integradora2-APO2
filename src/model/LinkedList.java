package model;

import java.util.Random;

public class LinkedList {
    private Node first;
    private Node sNode;
    private Node laser;
    private Node currentNode;
    private int numRows;
    private int winnerAnswer;
    private String columns = " ";
    Random random = new Random();
    private final String L_MIRROR = "\\";
    private final String R_MIRROR = "/";
    private final String START = "S";
    private final String HORIZONTAL = "H";
    private final String VERTICAL = "V";
    private final String END = "E";
    private final String INCORRECT = "X";
    private int numCols;
    private int mirrors;

    public int getWinnerAnswer() {
        return winnerAnswer;
    }

    public LinkedList(int m, int n, int mirrors) {
        numRows = m;
        numCols = n;
        createMatrix();
        this.mirrors = mirrors;
        addMirrors(first,first.getDown(),mirrors);
    }

    public int getMirrors() {
        return mirrors;
    }

    public void setMirrors(int mirrors) {
        this.mirrors = mirrors;
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

    private void cols(int columnPosition) {
        if (columnPosition < numCols) {
            columns += " " + (char) ('A' + columnPosition) + " ";
            cols(columnPosition + 1);
        } else {
            columns += "\n";
        }
    }

    public void addMirrors(Node current, Node nextRowFirstColumn, int mirrors) {
        if (mirrors > 0) {
            int totalMirrors = mirrors;
            if (containsMirror() && current.getMirror().equals("")) {
                totalMirrors = mirrors - 1;
                current.addMirror(mirrorType());
            }
            if (current.getNext() != null) {
                addMirrors(current.getNext(), nextRowFirstColumn, totalMirrors);
            } else if (current.getDown() != null) {
                addMirrors(nextRowFirstColumn, nextRowFirstColumn.getDown(), totalMirrors);
            } else {
                addMirrors(first, first.getDown(), totalMirrors);
            }
        }
    }

    public void contentNodes(Node current, Node nextRowFirstColumn, String nodeContent) {
        if (current != null) {
            if (!current.getPosition().equals(nodeContent)) {
                if (current.getNext() != null) {
                    contentNodes(current.getNext(), nextRowFirstColumn, nodeContent);
                } else if (current.getDown() != null) {
                    contentNodes(nextRowFirstColumn, nextRowFirstColumn.getDown(), nodeContent);
                } else {
                    contentNodes(first, first.getDown(), nodeContent);
                }
            } else {
                sNode = current;
            }
        } else {
            sNode = null;
        }
    }
    public void selectNode(String id) {
        String idRow = id.substring(0, 1);
        String idColumn = id.substring(1, 2);
        contentNodes(first, first.getDown(), idRow + idColumn);
        if (currentNode !=null) {
            if (currentNode.getCharNode().equals(INCORRECT)) {
                currentNode.setCharNode(" ");
            }
        }
        currentNode = sNode;
        if (sNode.getMirror().equals("")) {
            sNode.setCharNode(INCORRECT);
        } else {
            winnerAnswer ++;
            sNode.setCharNode(sNode.getMirror());
        }
    }

    public void shootToLeft(Node current, Node back) {
        if (current == null) {
            laser = back;
            back.setCharNode(END);
        } else {
            String mirror = current.getMirror();
            if (mirror.equals(L_MIRROR) || mirror.equals(R_MIRROR)) {
                laser = current;
                if (mirror.equals(L_MIRROR)) {
                    shootToUp(current.getUp(), current);
                } else {
                    shootToDown(current.getDown(), current);
                }
            } else {
                shootToLeft(current.getPrev(), current);
            }
        }
    }

    public void shootToUp(Node current, Node back) {
        if (current == null) {
            laser = back;
            back.setCharNode(END);
        } else {
            String mirror = current.getMirror();
            if (mirror.equals(L_MIRROR) || mirror.equals(R_MIRROR)) {
                laser = current;
                if (mirror.equals(L_MIRROR)) {
                    shootToLeft(current.getPrev(), current);
                } else {
                    shootToRight(current.getNext(), current);
                }
            } else {
                shootToUp(current.getUp(), current);
            }
        }
    }

    public void shootToDown(Node current, Node back) {
        if (current == null) {
            laser = back;
            back.setCharNode(END);
        } else {
            String mirror = current.getMirror();
            if (mirror.equals(L_MIRROR) || mirror.equals(R_MIRROR)) {
                laser = current;
                if (mirror.equals(L_MIRROR)) {
                    shootToRight(current.getNext(), current);
                } else {
                    shootToLeft(current.getPrev(), current);
                }
            } else {
                shootToDown(current.getDown(), current);
            }
        }
    }

    public void shootToRight(Node current, Node back) {
        if (current == null) {
            laser = back;
            back.setCharNode(END);
        } else {
            String mirror = current.getMirror();
            if (mirror.equals(L_MIRROR) || mirror.equals(R_MIRROR)) {
                laser = current;
                if (mirror.equals(L_MIRROR)) {
                    shootToDown(current.getDown(), current);
                } else {
                    shootToUp(current.getUp(), current);
                }
            } else {
                shootToRight(current.getNext(), current);
            }
        }
    }

    private void shootLaser(String nodeId, String direction, Node current) {
        contentNodes(current, current.getDown(), nodeId);
        sNode.setCharNode(START);
        if ( !direction.equals("") &&
                (sNode.getRow() == 0 && sNode.getCol() ==0)
                || (sNode.getRow() == numRows && sNode.getCol() == numCols)
                || (sNode.getRow() == 0 && sNode.getCol() == numCols)
                || (sNode.getRow() == numRows && sNode.getCol() == 0)
        )  {
            System.out.println(direction);
            switch (direction) {
                case VERTICAL:
                    if (sNode.getUp() == null){
                        shootToDown(sNode, sNode);
                    } else {
                        shootToUp(sNode, sNode);
                    }
                    break;
                case HORIZONTAL:
                    if (sNode.getPrev() == null){
                        shootToRight(sNode, sNode);
                    } else {
                        shootToLeft(sNode, sNode);
                    }
                    break;
            }
        } else {
            System.out.println(sNode.getCol());
            System.out.println(sNode.getRow());
            if (sNode.getCol() == 0) {
                shootToRight(sNode, sNode);
            } else if (current.getCol() == numCols) {
                shootToLeft(sNode, sNode);
            }else if (current.getRow() == 0) {
                shootToDown(sNode, sNode);
            } else if (current.getRow() == numRows) {
                shootToUp(sNode, sNode);
            }
        }
    }

    public void shootLaserId(String id) {
        String idRow = id.substring(0, 1);
        String idColumn = id.substring(1, 2);
        String direction = "";
        try {
            direction = id.substring(2, 3);
        } catch (Exception ignored) {
        }
        shootLaser(idRow + idColumn, direction, first);
    }




    public String mirrorType(){
        return random.nextInt(2) == 0 ? R_MIRROR : L_MIRROR;

    }

    public String toString() {
        String msg;
        msg = toStringRow(first);
        cols(first.getCol());
        return columns + msg;

    }

    public boolean containsMirror() {
        return random.nextInt(((numCols * numRows) / 2)) == 0;
    }

    private String toStringRow(Node firstRow) {
        String msg = "";
        if (firstRow != null) {
            msg = row(firstRow.getRow()) + toStringCol(firstRow) + "\n";

            msg += toStringRow(firstRow.getDown());
        }
        return msg;
    }

    public String row(int row){
        return (row + 1) + " ";
    }

    private String toStringCol(Node current) {
        String msg = "";
        if(current!=null) {
            msg = current.toString();
            msg += toStringCol(current.getNext());
        }
        return msg;
    }
}
