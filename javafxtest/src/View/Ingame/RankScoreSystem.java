package View.Ingame;

import Utilities.Utilizer;
import model.HeroSystem.Hero;
import model.HeroSystem.HeroFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 5/4/13
 * Time: 9:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class RankScoreSystem extends JPanel{
    Hero[] heroes = new Hero[10];
    RankScoreRow[] rank = new RankScoreRow[10];

    RankScoreSystem(){
        //get heroes
        Utilizer.load();
        HeroFactory hf = new HeroFactory();
        Hero h = hf.createHero(1);
        for(int i =0; i<10 ;i++){
            this.heroes[i]=h;
        }

        this.setLayout(new GridLayout(11, 1));
        //1st row
        RankScoreRow head = new RankScoreRow(0,0,null);
        this.add(head);

        //10 ranks
        for(int i=0; i<10; i++){
            rank[i] = new RankScoreRow(i+1,1000-i*100,heroes[i]);
            rank[i].setOpaque(true);
            this.add(rank[i]);
        }
        //this.setPreferredSize(new Dimension(400,600));
        //this.setBackground(Color.CYAN);
        this.setOpaque(true);
    }
    public static void main (String args[]){

        RankScoreSystem sys = new RankScoreSystem();
        JFrame f = new JFrame();
        f.setSize(new Dimension(400, 600));
        f.add(sys);

        sys.setOpaque(true);
        //f.setBackground(Color.BLUE);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
