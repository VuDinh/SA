package View.Ingame;

import Controllers.Requests.HeroAttackRequest;
import Controllers.Requests.HeroMoveRequest;
import Controllers.Requests.HeroRespawnRequest;
import Controllers.Server.GameManager.Player;
import Controllers.Server.GameManager.Team;
import Utilities.Utilizer;
import Controllers.listeners.MapListener;
import Controllers.listeners.ScrollListener;
import model.Animations.HeroAnimation;
import model.Facade.Facade;
import model.HeroSystem.*;
import model.MonsterSystem.Monster;
import model.MonsterSystem.MonsterStandThread;
import model.Skills.AOESkill;
import model.Skills.SkillStatus;
import sun.text.resources.BreakIteratorInfo_th;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

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
    Cell selectedCell, rangeCell;
    int startX, startY, maxX, maxY, scrollX, scrollY;
    int damage = 0;
    private Facade facade;

    public int getDamage() {
        return damage;
    }

    public Facade getFacade() {
        return facade;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
        facade.setGameMap(this);
        repaint();
    }

    public GameMap(Hero hero, Monster monster) {
//Utilizer.playMIDI(Utilizer.SOUND_THEME2,1000);
        //set the start viewing position
        scrollX = 0;
        scrollY = 0;
        //test a hero
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
        paintFog(g);
        //paintTeleportation(g);

    }

    public void paintDamage(Graphics g) {
        if (selectedCell != null) {
            int x = selectedCell.getColPos() * Utilizer.TILE_SIZE + 10 - this.getScrollX();
            int y = selectedCell.getRowPos() * Utilizer.TILE_SIZE + 20 - this.getScrollY();
            g.setFont(Utilizer.FONT2);
            g.setColor(Color.white);
            String s = new String("");
            if (damage != 0) s = damage + "";
            else s = "";
            g.drawString(s, x, y);
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

    public void paintFog(Graphics g) {
        //System.out.println(facade.getMatch().seenCells());
        ArrayList<Cell> cells = facade.getMatch().seenCells(facade.getClientPlayer().getTeam());
        //cells.addAll();
        /*try {
            for (Cell cell : cells) {
                g.clearRect(cell.getColPos() * Utilizer.TILE_SIZE - scrollX, cell.getRowPos() * Utilizer.TILE_SIZE - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                g.drawImage(Utilizer.normalArray[0], cell.getColPos() * Utilizer.TILE_SIZE - scrollX, cell.getRowPos() * Utilizer.TILE_SIZE - scrollY, this);
                g.drawImage(Utilizer.normalArray[Utilizer.MAP[cell.getRowPos()][cell.getColPos()] - 1], cell.getColPos() * Utilizer.TILE_SIZE - scrollX, cell.getRowPos() * Utilizer.TILE_SIZE - scrollY, this);
            }
        } catch (ConcurrentModificationException ex) {

        }*/
            for (int i = startX; i < maxX; i++) {
                for (int j = startY; j < maxY; j++) {
                    Cell tempCell = new Cell(j, i);
                    if (cells.contains(tempCell)) continue;
                    g.drawImage(Utilizer.normalArray[0], i * Utilizer.TILE_SIZE - scrollX, j * Utilizer.TILE_SIZE - scrollY, this);
                    g.drawImage(Utilizer.fogArray[Utilizer.MAP[j][i] - 1], i * Utilizer.TILE_SIZE - scrollX, j * Utilizer.TILE_SIZE - scrollY, this);
                }
            }

    }

    public void paintTeleportation(Graphics g){
        for(Iterator i = facade.getMatch().getTeleport().iterator(); i.hasNext();){
            Teleport t = (Teleport)i.next();
            //System.out.println(t);
            int row = t.getRow();
            int col = t.getCol();
            g.drawImage(Utilizer.IMG_TELEPORT, col*Utilizer.TILE_SIZE-scrollX, row*Utilizer.TILE_SIZE-scrollY, this);

        }
    }

    public void paintHero(Graphics g) {
        if (facade != null) {
            if (facade.getGame() != null) {
                facade.drawHeroes(g, scrollX, scrollY);
            }
        }
    }

    public void paintMonster(Graphics g) {
        //monster.draw(g,scrollX,scrollY);
        facade.getGame().drawMonsters(g, scrollX, scrollY);
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
    public void setScroll(int scrollX,int scrollY){
        this.scrollX=scrollX;
        this.scrollY=scrollY;
        repaint();
    }

    public void setCenterScreenByCord(int row, int col) {
        /*int scrollRow = Math.max(0, row - 4);
        int scrollCol = Math.max(0, col - 14);
        scrollRow=Math.min(scrollRow,Utilizer.MAP_ROWS-7);
        scrollCol=Math.min(scrollCol,Utilizer.MAP_COLS-13);
        scrollX = scrollCol * Utilizer.TILE_SIZE;
        scrollY = scrollRow * Utilizer.TILE_SIZE;*/
        scrollX=Math.max(0,col * Utilizer.TILE_SIZE - getWidth()/2);
        scrollY=Math.max(0,row * Utilizer.TILE_SIZE - getHeight()/2);
        scrollX=Math.min(scrollX,Utilizer.MAP_COLS*Utilizer.TILE_SIZE - getWidth());
        scrollY=Math.min(scrollY,Utilizer.MAP_ROWS*Utilizer.TILE_SIZE - getHeight());
    }

    public void paintSelected(Graphics g) {
        //if (status.equals("selected")) {
            /*if(facade.getMainHero().getIsChosen() && facade.getMainHero().getStatus().equals(HeroStatus.standing)){
                facade.getMainHero().drawRange(g, scrollX, scrollY);

            } else if (facade.getMainHero().getIsChosen() && facade.getMainHero().getStatus().equals(HeroStatus.attacking)) {
                facade.getMainHero().getCurrentSkill().drawSkill(g, selectedCell, scrollX, scrollY, this);
                facade.getMainHero().getCurrentSkill().drawPath(g, rangeCell, scrollX, scrollY, this);
                facade.getMainHero().getCurrentSkill().drawPathOnHero(g,facade.getMainHero(),rangeCell, scrollX, scrollY, this);
            }
            if (hero.getIsChosen()) {
                g.drawImage(hero.getCurrentSprite(), hero.getX() - scrollX, hero.getY() - scrollY, this);
            }*/
        facade.drawHeroEffects(g, scrollX, scrollY, selectedCell, rangeCell);
        //}
    }

    void paintHoveredNormal(Graphics g) {
        g.clearRect(rangeCell.getX() - scrollX, rangeCell.getY() - scrollY, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
        g.drawImage(Utilizer.hoverArray[0], rangeCell.getX() - scrollX, rangeCell.getY() - scrollY, this);
        g.drawImage(Utilizer.hoverArray[Utilizer.MAP[rangeCell.getRowPos()][rangeCell.getColPos()] - 1], rangeCell.getX() - scrollX, rangeCell.getY() - scrollY, this);
    }

    public void paintHovered(Graphics g) {
        if (true) {
            if (rangeCell != null) {
                if (facade.getMainHero().getIsChosen() && facade.getMainHero().getStatus().equals(HeroStatus.attacking)) {
                    //getHero().getSkill(hero.getCurrentSkillIndex()).drawPath(g, rangeCell, scrollX, scrollY, this);
                    //getHero().getSkill(hero.getCurrentSkillIndex()).drawPathOnHero(g,getHero(),rangeCell, scrollX, scrollY, this);

                    if (facade.getMainHero().getCurrentSkill() instanceof AOESkill) {
                        ((AOESkill) facade.getMainHero().getCurrentSkill()).drawRange(g, scrollX, scrollY);
                        //System.out.println(((AOESkill) getHero().getCurrentSkill()).getRangeCell());
                    }
                } else if (!facade.getMainHero().getIsChosen()) {
                    paintHoveredNormal(g);
                } else if (facade.getMainHero().getIsChosen()) {
                    //paintHoveredInRange(g);
                }
                if (rangeCell.getColPos() == facade.getMainHero().getCol() && rangeCell.getRowPos() == facade.getMainHero().getRow()) {
                    g.drawImage(facade.getMainHero().getCurrentSprite(), facade.getMainHero().getX() - scrollX, facade.getMainHero().getY() - scrollY, this);
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

    public void addMapListener(MouseListener e) {
        this.addMouseListener(e);
    }

    public void addScrollListener(KeyListener e) {
        this.addKeyListener(e);
    }

    //handle requests
    //hero move


    public void handleHeroAttackRequest(HeroAttackRequest request) {
        Hero temp = facade.getHeroBySlotIndex(request.getSlotIndex());
        Player attackingPlayer = facade.getMatch().getPlayer(request.getSlotIndex());
        temp.setIsChosen(true);
        temp.setStatus(HeroStatus.attacking);
        temp.setCurrentSkill(request.getHero().getCurrentSkillIndex());
        temp.getCurrentSkill().setPath(request.getPath());
        temp.getCurrentSkill().setDamageCell(request.getDmgCell());
        temp.getCurrentSkill().setStatus(SkillStatus.after);
        temp.setCurrentSprite(request.getHero().getCurrentSpriteIndex());
        selectedCell = request.getSelectedCell();
        HeroAnimation.attack(temp, this);
        //look for affected character
        for (Iterator it = request.getDmgCell().iterator(); it.hasNext(); ) {
            Cell cell = (Cell) it.next();
            Player player = facade.getPlayerByCord(cell.rowPos, cell.colPos);
            if (player != null) {
                if (!player.getTeam().equals(attackingPlayer.getTeam())) {
                    Hero attackedHero = player.getHero();
                    attackedHero.setHP(attackedHero.getHP() - temp.getCurrentSkill().getDamage(temp));
                    if (attackedHero.getHP() <= 0 && !attackedHero.getStatus().equals(HeroStatus.dead)) {
                        //set dead status
                        attackedHero.setHP(0);
                        attackedHero.setStatus(HeroStatus.dead);
                        attackedHero.setRespawnTurnNum(1);
                    }
                }
            }
            Monster attackedMonster = facade.getMonsterByCord(cell.getRowPos(), cell.getColPos());
            if (attackedMonster != null) {
                attackedMonster.setHP(attackedMonster.getHP() - temp.getCurrentSkill().getDamage(temp));
                if (attackedMonster.getHP() <= 0) {
                    //set dead status
                    attackedMonster.setHP(0);
                    attackedMonster.setImageIndex(11);

                }
            }
        }
        repaint();
    }

    public void tele(){
        ArrayList<Teleport> teleport =facade.getMatch().getTeleport();

    }

    public void handleHeroRespawnRequest(HeroRespawnRequest request) {
        Hero hero=facade.getHeroBySlotIndex(request.getHeroSlot());
        hero.setStatus(HeroStatus.standing);
        hero.resetHP();
        hero.resetAP();
        Utilizer.MOVEMAP[hero.getRow()][hero.getCol()]=0;
        hero.setRow(request.getRespawnPos().getRowPos());
        hero.setCol(request.getRespawnPos().getColPos());
        hero.setX(request.getRespawnPos().getX());
        hero.setY(request.getRespawnPos().getY());
        Utilizer.MOVEMAP[hero.getRow()][hero.getCol()]=12;
        repaint();
    }
}
