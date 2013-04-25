package View.Ingame;

import Utilities.Utilizer;
import Controllers.listeners.MapListener;
import Controllers.listeners.ScrollListener;
import model.Facade.Facade;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroFactory;
import model.HeroSystem.HeroStandThread;
import model.HeroSystem.HeroStatus;
import model.MonsterSystem.Monster;
import model.MonsterSystem.MonsterStandThread;
import model.Skills.AOESkill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/5/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameMap extends JPanel {


    Graphics graphic;
    String status = "normal";
    Cell selectedCell , rangeCell;
    int startX, startY, maxX, maxY, scrollX, scrollY;
    int damage = 0;
    Hero hero;
    Monster monster;
    private Facade facade;
    public int getDamage() {
        return damage;
    }
    public Facade getFacade(){
        return facade;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setFacade(Facade facade){
        this.facade=facade;
        facade.setGameMap(this);
        repaint();
    }
    public GameMap(Hero hero, Monster monster) {
//Utilizer.playMIDI(Utilizer.SOUND_THEME2,1000);
        //set the start viewing position
        scrollX = 0;
        scrollY = 0;
        //test a hero
        HeroFactory hF = new HeroFactory();
        //hero = hF.createHero(1);
        this.hero = hero;
        this.monster= monster;
        hero.setPanel(this);
        HeroStandThread t = new HeroStandThread(hero, this);
        t.start();
        MonsterStandThread t2 = new MonsterStandThread(monster, this);
        t2.start();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Graphics getG() {
        return graphic;
    }

    @Override
    public void paintComponent(Graphics g) {
        graphic = g;

        //paint the map
        paintMap(g);
        paintHovered(g);
        paintSelected(g);
        paintMonster(g);
        paintHero(g);
        paintDamage(g);
    }

    public void paintDamage(Graphics g){
        if(selectedCell != null){
        int x = selectedCell.getColPos()*Utilizer.TILE_SIZE+10-this.getScrollX();
        int y = selectedCell.getRowPos()*Utilizer.TILE_SIZE+20-this.getScrollY();
        g.setFont(Utilizer.FONT2);
        g.setColor(Color.white);
        String s = new String("");
        if(damage!=0)s= damage+"";
        else s = "";
        g.drawString(s,x,y);
        }
    }

    public void paintMap(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        startX = Math.max(scrollX / Utilizer.TILE_SIZE, 0);
        startY = Math.max(scrollY / Utilizer.TILE_SIZE, 0);
        maxX = Math.min((scrollX + getWidth()) / Utilizer.TILE_SIZE + 1, Utilizer.MAP_COLS);
        maxY = Math.min((scrollY + getHeight()) / Utilizer.TILE_SIZE + 1, Utilizer.MAP_ROWS);
        for (int i = startX; i < maxX; i++) {
            for (int j = startY; j < maxY; j++) {
                g.drawImage(Utilizer.normalArray[0], i * Utilizer.TILE_SIZE - scrollX, j * Utilizer.TILE_SIZE - scrollY, this);
                g.drawImage(Utilizer.normalArray[Utilizer.MAP[j][i] - 1], i * Utilizer.TILE_SIZE - scrollX, j * Utilizer.TILE_SIZE - scrollY, this);
            }
        }

    }

    public void paintHero(Graphics g) {
        hero.draw(g, scrollX, scrollY);
        if(facade!=null){
            if(facade.getGame()!=null){
                facade.drawHeroes(g,scrollX, scrollY);
            }
        }
    }

    public void paintMonster(Graphics g){
        monster.draw(g,scrollX,scrollY);
    }

    public void increaseScrollX() {
        if (scrollX < ((Utilizer.MAP_COLS) * Utilizer.TILE_SIZE - getWidth() - Utilizer.TILE_SIZE))
            scrollX += Utilizer.TILE_SIZE;
        else scrollX = (Utilizer.MAP_COLS) * Utilizer.TILE_SIZE - getWidth();
    }

    public void decreaseScrollX() {
        if (scrollX > Utilizer.TILE_SIZE)
            scrollX -= Utilizer.TILE_SIZE;
        else scrollX = 0;
    }

    public void increaseScrollY() {
        if (scrollY < ((Utilizer.MAP_ROWS) * Utilizer.TILE_SIZE - getHeight() - Utilizer.TILE_SIZE))
            scrollY += Utilizer.TILE_SIZE;
        else scrollY = (Utilizer.MAP_ROWS) * Utilizer.TILE_SIZE - getHeight();
    }

    public void decreaseScrollY() {
        if (scrollY > Utilizer.TILE_SIZE)
            scrollY -= Utilizer.TILE_SIZE;
        else scrollY = 0;
    }

    public int getScrollX() {
        return scrollX;
    }

    public int getScrollY() {
        return scrollY;
    }

    void paintSelectedNormal(Graphics g){
        g.clearRect(selectedCell.getX() - scrollX, selectedCell.getY() - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
        g.drawImage(Utilizer.selectArray[0], selectedCell.getX() - scrollX, selectedCell.getY() - scrollY, this);
        g.drawImage(Utilizer.selectArray[Utilizer.MAP[selectedCell.getRowPos()][selectedCell.getColPos()] - 1], selectedCell.getX() - scrollX, selectedCell.getY() - scrollY, this);
    }

    public void paintSelected(Graphics g) {
        if (status.equals("selected")) {
            if(hero.getIsChosen() && hero.getStatus().equals(HeroStatus.standing)){
                hero.drawRange(g,scrollX,scrollY);
            } else if (hero.getIsChosen() && hero.getStatus().equals(HeroStatus.attacking)) {
                hero.getCurrentSkill().drawSkill(g, selectedCell, scrollX, scrollY, this);
                getHero().getSkill(hero.getCurrentSkillIndex()).drawPath(g, rangeCell, scrollX, scrollY, this);
                getHero().getSkill(hero.getCurrentSkillIndex()).drawPathOnHero(g,getHero(),rangeCell, scrollX, scrollY, this);
            } else {
                //paintSelectedNormal(g);
            }
            if (hero.getIsChosen()) {
                g.drawImage(hero.getCurrentSprite(), hero.getX() - scrollX, hero.getY() - scrollY, this);
            }
        }
    }
    void paintHoveredNormal(Graphics g){
        g.clearRect(rangeCell.getX() - scrollX, rangeCell.getY() - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
        g.drawImage(Utilizer.hoverArray[0], rangeCell.getX() - scrollX, rangeCell.getY() - scrollY, this);
        g.drawImage(Utilizer.hoverArray[Utilizer.MAP[rangeCell.getRowPos()][rangeCell.getColPos()] - 1], rangeCell.getX() - scrollX, rangeCell.getY() - scrollY, this);
    }

    public void paintHovered(Graphics g) {
        if (true) {
            if (rangeCell != null) {
                if (getHero().getIsChosen() && getHero().getStatus().equals(HeroStatus.attacking)) {
                    //getHero().getSkill(hero.getCurrentSkillIndex()).drawPath(g, rangeCell, scrollX, scrollY, this);
                    //getHero().getSkill(hero.getCurrentSkillIndex()).drawPathOnHero(g,getHero(),rangeCell, scrollX, scrollY, this);

                    if(getHero().getCurrentSkill() instanceof AOESkill){
                        ((AOESkill) getHero().getCurrentSkill()).drawRange( g,scrollX,scrollY);
                        //System.out.println(((AOESkill) getHero().getCurrentSkill()).getRangeCell());
                    }
                } else if(!getHero().getIsChosen() ) {
                    paintHoveredNormal(g);
                } else if(getHero().getIsChosen()){
                    //paintHoveredInRange(g);
                }
                if (rangeCell.getColPos() == hero.getCol() && rangeCell.getRowPos() == hero.getRow()) {
                    g.drawImage(hero.getCurrentSprite(), hero.getX() - scrollX, hero.getY() - scrollY, this);
                }

            }
        }
    }

    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void setRangedCell(Cell rangeCell) {
        this.rangeCell = rangeCell;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }
    public void addMapListener(MouseListener e){
        this.addMouseListener(e);
    }
    public void addScrollListener(KeyListener e){
        this.addKeyListener(e);
    }


}
