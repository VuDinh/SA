package model.HeroSystem;

import Controllers.Server.GameManager.Team;
import Utilities.Utilizer;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 4/26/13
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class Teleport implements Serializable {
    int index;
    int teleport;
    int col,row;
    Team team;
    public Teleport(int row, int col,Team team,int teleport){
        this.row = row;
        this.col = col;
        this.team = team;
        this.teleport = teleport;
    }

    BufferedImage [] animation = Utilizer.teleportArray;
    public int getIndex(){
        return index;
    }
    public void setIndex(int index){
        this.index = index;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getTeleport() {
        return teleport;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public BufferedImage[] getAnimation() {
        return animation;
    }

    public void nextSprite(){
        if(index==7)index=0;
        else index++;
    }

    public Teleport getDestination(Teleport t, ArrayList<Teleport> a){
        int row = t.getRow();
        int col = t.getCol();
        int te = t.getTeleport();
        for(Iterator i = a.iterator(); i.hasNext();){
            Teleport tele = (Teleport) i.next();
            if( te==tele.getTeleport() && row!=tele.getRow() && col!=tele.getCol()){
                return tele;
            }
        }
        return null;
    }

    public String toString(){
        return row+":"+col+":"+team+":"+teleport;
    }
}
