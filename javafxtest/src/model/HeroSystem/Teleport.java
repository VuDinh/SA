package model.HeroSystem;

import Utilities.Utilizer;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 4/26/13
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class Teleport {
    int index=0;
    int teleport;
    public Teleport(int teleport){
        this.teleport = teleport;
    }

    BufferedImage [] animation = Utilizer.teleportArray;
    public int getIndex(){
        return index;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public void nextSprite(){
        if(index==7)index=0;
        else index++;
    }
}
