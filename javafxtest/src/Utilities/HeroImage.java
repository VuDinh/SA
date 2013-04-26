package Utilities;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/2/13
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroImage {
    BufferedImage[] sprite;
    BufferedImage avatar;
    BufferedImage choosing;
    BufferedImage icon;

    public HeroImage(BufferedImage[] sprite, BufferedImage avatar, BufferedImage icon, BufferedImage choosing) {
        this.sprite = sprite;
        this.avatar = avatar;
        this.icon = icon;
        this.choosing = choosing;
    }

    public BufferedImage[] getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage[] sprite) {
        this.sprite = sprite;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
        this.avatar = avatar;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public BufferedImage getChoosing() {
        return choosing;
    }

    public void setChoosing(BufferedImage choosing) {
        this.choosing = choosing;
    }
}
