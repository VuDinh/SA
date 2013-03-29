package View.Ingame;

import Utilities.Utilizer;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/5/13
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cell implements Comparable{
    BufferedImage back;
    int status,x,y,rowPos,colPos;
    Cell previous;
    int minPath;

    public Cell getPrevious() {
        return previous;
    }
    public Cell(){
        back=null;
        status=0;
        minPath= Utilizer.MAP_COLS * Utilizer.MAP_ROWS;
        previous=null;
    }
    public Cell(int row,int col){
        rowPos=row;
        colPos=col;
        back=null;
        status=0;
        minPath= Utilizer.MAP_COLS * Utilizer.MAP_ROWS;
        previous=null;
    }
    public void setPrevious(Cell previous) {
        this.previous = previous;
    }

    public int getMinPath() {
        return minPath;
    }

    public void setMinPath(int minPath) {
        this.minPath = minPath;
    }



    public int getRowPos() {
        return rowPos;
    }

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public int getColPos() {
        return colPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    public void setImage(BufferedImage image){
        back=image;
    }
    public BufferedImage getImage(){
        return back;
    }
    public void setStatus(int status){
        this.status=status;
    }
    public int getStatus(){
        return status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        Cell temp=(Cell) o;
        if(temp.colPos - colPos == 0 && temp.rowPos-rowPos ==0) return 0;
        else return -1;
    }
    public String toString(){
        return rowPos+","+colPos;
    }
    public boolean equals(Object o){
        Cell temp=(Cell) o;
        if(temp.colPos - colPos == 0 && temp.rowPos-rowPos ==0) return true;
        else return false;
    }
}
