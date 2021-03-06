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
import model.HeroSystem.Teleport;
import model.MessageSystem.Message;
import model.MonsterSystem.Monster;
import model.MonsterSystem.MonsterFactory;
import model.MonsterSystem.Tower;
import model.Skills.Skill;
import model.Character;

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
    private transient int quitNum;
    private ArrayList<Teleport> teleport = new ArrayList<Teleport>(){{
        add(new Teleport(35,1,Team.team1,1));
        add(new Teleport(33,1,Team.team1,2));
        add(new Teleport(38,4,Team.team2,3));
        add(new Teleport(38,6,Team.team2,4));
    }};
    private static  ArrayList<Tower> tower = new ArrayList<Tower>(){{
        add(new Tower(1000,1000,0,0,100,35,4,12,"","",new Cell(33,3), new Cell(36,5),Team.team1,true,false));
        add(new Tower(1000,1000,0,0,100,5,35,12,"","",new Cell(3,34), new Cell(6,36),Team.team2,true,false));
        add(new Tower(1000,1000,0,0,100,3,7,12,"","",new Cell(3,7), new Cell(4,7),Team.team2,false,false));
        add(new Tower(1000,1000,0,0,100,6,3,12,"","",new Cell(6,3), new Cell(7,3),Team.team1,false,false));
        add(new Tower(1000,1000,0,0,100,4,19,12,"","",new Cell(4,19), new Cell(5,19),Team.team2,false,false));
        add(new Tower(1000,1000,0,0,100,20,5,12,"","",new Cell(20,5), new Cell(21,5),Team.team1,false,false));
        add(new Tower(1000,1000,0,0,100,20,17,12,"","",new Cell(20,17), new Cell(21,17),Team.team1,false,false));
        add(new Tower(1000,1000,0,0,100,18,22,12,"","",new Cell(18,22), new Cell(19,22),Team.team2,false,false));
        add(new Tower(1000,1000,0,0,100,26,11,12,"","",new Cell(26,11), new Cell(27,11),Team.team1,false,false));
        add(new Tower(1000,1000,0,0,100,13,27,12,"","",new Cell(13,27), new Cell(14,27),Team.team2,false,false));
        add(new Tower(1000,1000,0,0,100,34,18,12,"","",new Cell(34,18), new Cell(35,18),Team.team1,false,false));
        add(new Tower(1000,1000,0,0,100,19,34,12,"","",new Cell(19,34), new Cell(20,34),Team.team2,false,false));
        add(new Tower(1000,1000,0,0,100,35,32,12,"","",new Cell(35,32), new Cell(36,32),Team.team1,false,false));
        add(new Tower(1000,1000,0,0,100,32,36,12,"","",new Cell(32,36), new Cell(33,36),Team.team2,false,false));
    }};
    private ArrayList<Cell> sightTower1 = new ArrayList<Cell>();
    private ArrayList<Cell> sightTower2 = new ArrayList<Cell>();
    int counter;
    private boolean isFull;
    private transient AccountDao dao;
    private int turnIndex;
    private int gameIndex;

    public ArrayList<Teleport> getTeleport() {
        return teleport;
    }

    public GameMatch(int index) {
        counter = 0;
        team1 = new ArrayList<Player>();
        team2 = new ArrayList<Player>();
        monsters = new ArrayList<Monster>();
        isFull = false;
        this.gameIndex = index;
        turnIndex = 0;
        quitNum=0;
        sightTower1 = sightTower(Team.team1);
        sightTower2 = sightTower(Team.team2);
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

        sightTower1 = sightTower(Team.team1);
        sightTower2 = sightTower(Team.team2);
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
            System.out.println("slot Index:"+player.getSlotIndex());
            System.out.println("dao:"+ dao);
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
            monster.draw(g, scrollX, scrollY);
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
    public Character getCharacterByCord(int row,int col){
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
        for(Iterator it=monsters.iterator();it.hasNext();){
            Monster monster=(Monster) it.next();
            if(monster.getRow()== row && monster.getCol()==col){
                return monster;
            }
        }
        for(Iterator it = tower.iterator(); it.hasNext();){
            Tower t = (Tower)it.next();
            if(t.getSelf().contains(new Cell(row,col))){
                return t;
            }
        }
        return null;
    }
    public Monster getMonsterByCord(int row,int col){
        for(Iterator it=monsters.iterator();it.hasNext();){
            Monster monster=(Monster) it.next();
            if(monster.getRow()==row && monster.getCol()==col){
                return monster;
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

    public ArrayList<Cell> sightHero(ArrayList a){
        ArrayList<Cell> c = new ArrayList<Cell>();
        Iterator i = a.iterator();
        while (i.hasNext()){
            c.addAll(((Player) (i.next())).getHero().getSight());
        }
        return c;
    }

    public ArrayList<Cell> sightTower(Team team){
        ArrayList<Cell> c = new ArrayList<Cell>();
        Iterator i = tower.iterator();
        while (i.hasNext()){
            Tower t = (Tower) i.next();
            if(t.getTeam()==team && t.isBroken()==false) c.addAll(t.getSight());
        }
        return c;
    }
    public ArrayList<Cell> seenCells(Team team){
        ArrayList<Cell> c = new ArrayList<Cell>();
        if(team.equals(Team.team1)){
            c.addAll(sightTower1);
            c.addAll(sightHero(team1));
        }
        else{
            c.addAll(sightTower2);
            c.addAll(sightHero(team2));
        }
        return  c;
    }

    public void setTurnIndex(int index) {
        this.turnIndex=index;
    }

    public void resetHeroSight(Team team){

    }

    public void handleHeroRespawnRequest(HeroRespawnRequest request) {
        Cell cell=dao.getHeroBeginPosition(request.getHeroSlot());
        cell.setX(cell.getColPos()*Utilizer.TILE_SIZE);
        cell.setY(cell.getRowPos()*Utilizer.TILE_SIZE);
        try {
            request.setRespawnPos(cell.clone());
            for (Iterator it = team2.iterator(); it.hasNext(); ) {
                Player player = (Player) it.next();
                player.getCom().write(request);
            }
            for (Iterator it = team1.iterator(); it.hasNext(); ) {
                Player player = (Player) it.next();
                //if(player.getSlotIndex()!=request.getSlotIndex())
                player.getCom().write(request);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public ArrayList<Cell> getSightTower1() {
        return sightTower1;
    }

    public void resetSightTeam1() {
        this.sightTower1 = sightTower(Team.team1);
    }

    public ArrayList<Cell> getSightTower2() {
        return sightTower2;
    }

    public void resetSightTeam2() {
        this.sightTower2 = sightTower(Team.team2);
    }

    public ArrayList<Cell> getSightTeam(Team team){
        if(team==Team.team1)return sightTower1;
        else return sightTower2;
    }

    public ArrayList<Tower> getTower() {
        return tower;
    }

    public void handleMatchResultRequest(MatchResultRequest request) {
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

    public void handleQuitRequest(QuitRequest request) {
        Player p=getPlayer(request.getHeroSlot());
        p.getCom().close();
        for (Iterator it = team2.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(!player.getCom().isClosed()){
                player.getCom().write(request);
                System.out.println("announce quit to:"+player.getCom().getAccount().getUsername());
            }
        }
        for (Iterator it = team1.iterator(); it.hasNext(); ) {
            Player player = (Player) it.next();
            if(!player.getCom().isClosed()){
                player.getCom().write(request);
                System.out.println("announce quit to:"+player.getCom().getAccount().getUsername());
            }
        }
        //getPlayer(request.getHeroSlot()).getCom().close();
        quitNum++;

    }
    public boolean isAllQuit(){
        return quitNum==Utilizer.MAXPLAYER;
    }

}
