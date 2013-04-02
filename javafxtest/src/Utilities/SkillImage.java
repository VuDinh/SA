package Utilities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/2/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SkillImage {

    ArrayList<BufferedImage[]> sprites;
    ArrayList<BufferedImage>    icons;
    public SkillImage() {
        sprites=new ArrayList<BufferedImage[]>();
        icons=new ArrayList<BufferedImage>();
    }

    public void addSprite(BufferedImage[] sprite){
        sprites.add(sprite);
    }
    public void addIcon(BufferedImage icon){
        icons.add(icon);
    }
    public BufferedImage getIcon(int index){
        return icons.get(index);
    }
    public BufferedImage[] getSprite(int index){
        return sprites.get(index);
    }
}
