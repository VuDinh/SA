package View.Ingame;

import Utilities.Utilizer;
import model.Facade.Facade;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/26/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class TurnPanel extends JPanel {
    private Facade facade;
    private String statusMessage;
    private String location;
    private String heroName;
    public TurnPanel(){
        setPreferredSize(new Dimension(1280,50));
    }
    public void setFacade(Facade facade){
        this.facade = facade;
    }
    public void paintComponent(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(Utilizer.IMG_TURN,getWidth()/2 - 528/2, 0, this);
        g.drawImage(Utilizer.IMG_FACE1,getWidth()/2 - 48*1 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE2,getWidth()/2 - 48*2 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE3,getWidth()/2 - 48*3 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE4,getWidth()/2 - 48*4 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE5,getWidth()/2 - 48*5 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE1,getWidth()/2 + 48*1 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE2,getWidth()/2 + 48*2 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE3,getWidth()/2 + 48*3 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE4,getWidth()/2 + 48*4 -24, 2, this);
        g.drawImage(Utilizer.IMG_FACE5,getWidth()/2 + 48*5 -24, 2, this);
        g.setColor(Color.white);
        g.drawString("30",getWidth()/2-2,31);

        //draw Player Name
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.setColor(Color.WHITE);
        g.drawString(facade.getUsername(),10,31);
    }
}
