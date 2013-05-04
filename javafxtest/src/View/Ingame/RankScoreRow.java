package View.Ingame;

import Utilities.Utilizer;
import model.HeroSystem.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 5/4/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class RankScoreRow extends JPanel {
    int rank;
    String name;
    BufferedImage avatar;
    int score;

    JLabel lblRank;
    JLabel lblName ;
    JLabel lblAvatar;
    ImageIcon imgAvatar;
    JLabel lblScore;

    public RankScoreRow(int rank, int score, Hero hero){
        if(hero != null){
        this.rank = rank;
        this.score = score;
        this.name = hero.getName();
        this.avatar = Utilizer.HEROIMAGEPACK.get(hero.getImageIndex()).getIcon();

        lblRank= new JLabel(rank+"");
        lblName = new JLabel(name);
        lblAvatar = new JLabel();
        imgAvatar = new ImageIcon(avatar);
        lblAvatar.setIcon(imgAvatar);
        lblScore = new JLabel(score+"");
        }
        else{
            lblRank= new JLabel("Rank");
            lblName = new JLabel("Name");
            lblAvatar = new JLabel("Avatar");
            lblScore = new JLabel("Score");
        }
        //this.setLayout(new GridLayout(1,4));
        this.add(lblRank);
        this.add(lblName);
        this.add(lblAvatar);
        this.add(lblScore);

        lblRank.setPreferredSize(new Dimension(50,44));
        lblName.setPreferredSize(new Dimension(100,44));
        lblAvatar.setPreferredSize(new Dimension(60,44));
        lblScore.setPreferredSize(new Dimension(100,44));
        this.setPreferredSize(new Dimension(310,50));

        lblRank.setOpaque(true);
        lblName.setOpaque(true);
        lblAvatar.setOpaque(true);
        lblScore.setOpaque(true);
        this.setOpaque(true);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
        this.avatar = avatar;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public JLabel getLblRank() {
        return lblRank;
    }

    public void setLblRank(JLabel lblRank) {
        this.lblRank = lblRank;
    }

    public JLabel getLblName() {
        return lblName;
    }

    public void setLblName(JLabel lblName) {
        this.lblName = lblName;
    }

    public JLabel getLblAvatar() {
        return lblAvatar;
    }

    public void setLblAvatar(JLabel lblAvatar) {
        this.lblAvatar = lblAvatar;
    }

    public ImageIcon getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(ImageIcon imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public JLabel getLblScore() {
        return lblScore;
    }

    public void setLblScore(JLabel lblScore) {
        this.lblScore = lblScore;
    }
}
