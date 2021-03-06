package View.HeroChoosing;

import Utilities.Utilizer;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/9/13
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlliedHeroPane extends GridPane {
    ImageView[] images;
    ImageView imgUnknown;
    public AlliedHeroPane(){
        Image unknown=SwingFXUtils.toFXImage(Utilizer.IMG_FACE8,new WritableImage(Utilizer.IMG_FACE8.getWidth()
                ,Utilizer.IMG_FACE8.getHeight()));
        Image vs     =SwingFXUtils.toFXImage(Utilizer.IMG_VS,new WritableImage(Utilizer.IMG_VS.getWidth()
                ,Utilizer.IMG_VS.getHeight()));
        imgUnknown=new ImageView(unknown);
        setAlignment(Pos.CENTER);
        images=new ImageView[4];
        for(int i=0;i<2;i++){
            images[i]=new ImageView(unknown);
            add(images[i],0,i);
        }
        ImageView imgVs = new ImageView(vs);
        add(imgVs,0,2);
        for(int i=2;i<4;i++){
            images[i]=new ImageView(unknown);
            add(images[i],0,i+1);
        }
    }
    public void setAllyUnknown(int index){
        images[index].setImage(imgUnknown.getImage());
    }
    public void setAllyIcon(int index,BufferedImage img){
        Image temp=SwingFXUtils.toFXImage(img,new WritableImage(img.getWidth(),img.getHeight()));
        int actualIndex=index;
        if(index>=Utilizer.MAXPLAYER/2){
            actualIndex++;
        }
        images[actualIndex].setImage(temp);
    }
}
