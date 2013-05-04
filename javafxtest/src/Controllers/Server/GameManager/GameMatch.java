package Controllers.Server.GameManager;

import Controllers.Communicator;
import Controllers.Requests.*;
import Controllers.Server.AccountDao;
import Controllers.Server.GameManager.Comparators.ScoreComparator;
import Utilities.Utilizer;

import View.Ingame.Cell;
import View.Ingame.GameMap;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroFactory;
import model.HeroSystem.HeroStatus;
import model.MessageSystem.Message;
import model.MonsterSystem.Monster;
import model.MonsterSystem.MonsterFactory;
import model.Skills.Skill;
import org.springframework.util.SerializationUtils;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/11/13
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameMatch implements Serializable, Cloneable {
    private ArrayList<Player> team1;
    private ArrayList<Player> team2;
    private ArrayList<Monster> monsters;
    int counter;
    private boolean isFull;
    private transient AccountDao dao;
    private int turnIndex;
    private int gameIndex;

    public GameMatch(int index) {
        counter = 0;
        team1 = new ArrayList<Player>();
        team2 = new ArrayList<Player>();
        monsters = new ArrayList<Monster>();
        isFull = false;
        this.gameIndex = index;
        turnIndex = 0;

    }

    public GameMatch(GameMatch one) {
        this.team1 = new ArrayList<Player>();
        this.team2 = new ArrayList<Player>();
        this.monsters=new ArrayList<Monster>();
        for (Player player : one.team1) {
            this.team1.add(new Player(player));
        }
        for (Player player : one.team2) {
            this.team2.add(new Player(player));
        }
        for(Monster monster:one.monsters){
            try {
                this.monsters.add(monster.clone());
            } catch (CloneNotSupportedException e) {

            }
        }
        this.counter = one.counter;
        this.isFull = one.isFull;
        this.gameIndex = one.gameIndex;
        turnIndex = 0;

    }

    public void setDao(AccountDao dao) {
        this.dao = dao;
        createMonsters();
    }

    public void addPlayer(Communicator com) {
        if (counter < Utilizer.MAXPLAYER) {
            if (counter < Utilizer.MAXPLAYER / 2) {
                team1.add(new Player(com, counter, Team.team1));
            } else {
                team2.add(new Player(com, counter, Team.team2));
            }
            counter++;
            announceFingdingMatchRequest();
        }
        if (counter == Utilizer.MAXPLAYER) {
            isFull = true;
            announceHeroChoosingRequest();
            initiateCountDown();
        }

    }
    public int getTurnIndex(){
        return turnIndex;
    }

    public void announceFingdingMatchRequest() {
        MatchMakingRequest m = new MatchMakingRequest(counter);
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(m);
        }
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(m);
        }
    }

    public void announceHeroChoosingRequest() {
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            HeroChoosingRequest m = new HeroChoosingRequest();
            Player player = (Player) it.next();
            m.setMatchIndex(gameIndex);
            m.setHeroSlotIndex(player.getSlotIndex());
            player.getCom().write(m);
        }
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            HeroChoosingRequest m = new HeroChoosingRequest();
            Player player = (Player) it.next();
            m.setMatchIndex(gameIndex);
            m.setHeroSlotIndex(player.getSlotIndex());
            player.getCom().write(m);
        }
    }

    public void chooseHero(Communicator com, int heroSlot, int heroIndex) {
        //announce to the others about the other hero picked
        HeroPickedRequest request = new HeroPickedRequest(heroIndex, heroSlot);
        Player temp = getPlayer(heroSlot);
        temp.setPicked(true);
        temp.setHeroIndex(heroIndex);
        request.setPlayerName(com.getAccount().getUsername());
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(request);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(request);
        }
    }

    public void startPlayingGame() {
        //set initial position for each player and create Hero
        HeroFactory hF = new HeroFactory();
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            Cell c = dao.getHeroBeginPosition(player.getSlotIndex());
            player.setPosition(c);
            System.out.println("hero Index:" + player.getHeroIndex());
            player.setHero(hF.createHero(player.getHeroIndex()));
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            Cell c = dao.getHeroBeginPosition(player.getSlotIndex());
            player.setPosition(c);
            System.out.println("hero Index:" + player.getHeroIndex());
            player.setHero(hF.createHero(player.getHeroIndex()));
        }
        PlayingGameRequest request = new PlayingGameRequest();
        request.setGame(this);

        //send Game request
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(new GameMatch(this));
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(new GameMatch(this));
        }

    }

    public void initiateCountDown() {
        CountdownThread t = new CountdownThread(this);
        t.start();
    }

    public boolean isFull() {
        return isFull;
    }

    public ArrayList<Player> getTeam1() {
        return team1;
    }

    public ArrayList<Player> getTeam2() {
        return team2;
    }

    public int getCurrentNumOfPlayers() {
        return counter;
    }

    public Player getPlayer(int heroSlotIndex) {
        if (heroSlotIndex < Utilizer.MAXPLAYER / 2) {
            return team1.get(heroSlotIndex);
        } else {
            return team2.get(heroSlotIndex - (Utilizer.MAXPLAYER / 2));
        }
    }

    public void drawHeroes(Graphics g, int scrollX, int scrollY) {
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getHero().draw(g, scrollX, scrollY);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getHero().draw(g, scrollX, scrollY);
        }
    }
    public void drawHeroEffects(Graphics g, int scrollX,int scrollY,Cell selectedCell,Cell rangeCell){
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            Hero tempHero=player.getHero();
            if(tempHero.getIsChosen() && tempHero.getStatus().equals(HeroStatus.standing)){
                tempHero.drawRange(g, scrollX, scrollY);

            } else if (tempHero.getIsChosen() && tempHero.getStatus().equals(HeroStatus.attacking)) {
                tempHero.getCurrentSkill().drawSkill(g, selectedCell, scrollX, scrollY, tempHero.getPanel());
                tempHero.getSkill(tempHero.getCurrentSkillIndex()).drawPath(g, rangeCell, scrollX, scrollY, tempHero.getPanel());
                tempHero.getSkill(tempHero.getCurrentSkillIndex()).drawPathOnHero(g,tempHero,rangeCell, scrollX, scrollY, tempHero.getPanel());
            }
            if (tempHero.getIsChosen()) {
                g.drawImage(tempHero.getCurrentSprite(), tempHero.getX() - scrollX, tempHero.getY() - scrollY, tempHero.getPanel());
            }
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            Hero tempHero=player.getHero();
            if(tempHero.getIsChosen() && tempHero.getStatus().equals(HeroStatus.standing)){
                tempHero.drawRange(g, scrollX, scrollY);

            } else if (tempHero.getIsChosen() && tempHero.getStatus().equals(HeroStatus.attacking)) {
                tempHero.getCurrentSkill().drawSkill(g, selectedCell, scrollX, scrollY, tempHero.getPanel());
                tempHero.getSkill(tempHero.getCurrentSkillIndex()).drawPath(g, rangeCell, scrollX, scrollY, tempHero.getPanel());
                tempHero.getSkill(tempHero.getCurrentSkillIndex()).drawPathOnHero(g,tempHero,rangeCell, scrollX, scrollY, tempHero.getPanel());
            }
            if (tempHero.getIsChosen()) {
                g.drawImage(tempHero.getCurrentSprite(), tempHero.getX() - scrollX, tempHero.getY() - scrollY, tempHero.getPanel());
            }
        }

    }

    public void drawMonsters(Graphics g,int scrollX,int scrollY){
        for(Iterator it=monsters.iterator();it.hasNext();){
            Monster monster=(Monster) it.next();
            monster.draw(g,scrollX, scrollY);
        }
    }

    public void setGameMap(GameMap gameMap) {
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getHero().setPanel(gameMap);
            for(Skill skill:player.getHero().getAllSkills()){
                skill.setPanel(gameMap);
            }
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getHero().setPanel(gameMap);
            for(Skill skill:player.getHero().getAllSkills()){
                skill.setPanel(gameMap);
            }
        }
    }
    public Hero getHeroByCord(int row,int col){
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(player.getHero().isThere(row,col)){
                return player.getHero();
            }
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(player.getHero().isThere(row,col)){
                return player.getHero();
            }
        }
        return null;
    }
    public Player getPlayerByCord(int row,int col){
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(player.getHero().isThere(row,col)){
                return player;
            }
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(player.getHero().isThere(row,col)){
                return player;
            }
        }
        return null;
    }
    public void handleHeroMoveRequest(HeroMoveRequest request){
        int slot=request.getSlotIndex();
        //set Hero new position
        getPlayer(slot).getHero().setRow(request.getSelectedCell().getRowPos());
        getPlayer(slot).getHero().setCol(request.getSelectedCell().getColPos());
        getPlayer(slot).getHero().setX(request.getSelectedCell().getColPos()* Utilizer.TILE_SIZE);
        getPlayer(slot).getHero().setY(request.getSelectedCell().getRowPos()* Utilizer.TILE_SIZE);
        //send move request to other player
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            player.getCom().write(request);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            player.getCom().write(request);
        }
    }
    public void handleHeroAttackRequest(HeroAttackRequest request){
        int slot=request.getSlotIndex();
        System.out.println("receive path:"+request.getHero().getCurrentSkill().getPath());
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            player.getCom().write(request);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            player.getCom().write(request);
        }
    }
    public void sendMessageToTeam(Message mes){
        Player p=getPlayer(mes.getSlotIndex());
        ArrayList<Player> team;
        if(p.getTeam().equals(Team.team1)) team=team1;
        else team=team2;
        for (Iterator it = team.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            if(player.getSlotIndex()!=mes.getSlotIndex())
            player.getCom().write(mes);
        }
    }
    public void sendMessageToAll(Message mes){
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            if(player.getSlotIndex()!=mes.getSlotIndex())
            player.getCom().write(mes);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            if(player.getSlotIndex()!=mes.getSlotIndex())
            player.getCom().write(mes);
        }
    }
    public void sendMessageToPlayer(Message mes){
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(player.getCom().getAccount().getUsername().equals(mes.getReceiver().getUsername()))
            player.getCom().write(mes);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            if(player.getCom().getAccount().getUsername().equals(mes.getReceiver().getUsername()))
            player.getCom().write(mes);
        }
    }

    public GameMatch clone() throws CloneNotSupportedException {
        return (GameMatch) super.clone();
    }

    public void nextTurn(){
        if(turnIndex<Utilizer.MAXPLAYER-1)
        turnIndex++;
        else turnIndex = 0;
        TurnControlRequest request=new TurnControlRequest(turnIndex,gameIndex);
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            player.getCom().write(request);
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            player.getCom().write(request);
        }
    }
    public void createMonsters(){
        MonsterFactory mF=new MonsterFactory();
        ArrayList<Cell> t=new ArrayList<Cell>(dao.getAllMonsterPositions());
        int index=1;
        for(Iterator it=t.iterator();it.hasNext();){
            Monster monster=mF.createMonster(index);
            index++;
            if(index>3) index = 1;
            Cell cell=(Cell)it.next();
            monster.setRow(cell.getRowPos());
            monster.setCol(cell.getColPos());
            monster.setX(cell.getColPos() * Utilizer.TILE_SIZE);
            monster.setY(cell.getRowPos()*Utilizer.TILE_SIZE);
            monsters.add(monster);

        }
    }
    public ArrayList<Player> getRankingList(){
        ArrayList<Player> players=new ArrayList<Player>();
        players.addAll(team1);
        players.addAll(team2);
        Collections.sort(players, new ScoreComparator());
        return players;
    }
    public void resetMoveMap(){
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            int row=player.getHero().getRow();
            int col=player.getHero().getCol();
            Utilizer.MOVEMAP[row][col]=12;
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            //if(player.getSlotIndex()!=request.getSlotIndex())
            int row=player.getHero().getRow();
            int col=player.getHero().getCol();
            Utilizer.MOVEMAP[row][col]=12;
        }
        for(Iterator it =monsters.iterator();it.hasNext();){
            Monster monster=(Monster) it.next();
            int row=monster.getRow();
            int col=monster.getCol();
            Utilizer.MOVEMAP[row][col]=12;
        }
    }

}
