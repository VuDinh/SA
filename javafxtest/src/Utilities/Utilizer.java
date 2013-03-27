package Utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 1/5/01
 * Time: 00:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utilizer {
    public static final String TILE = "Images/Map.png";
    public static final String SELECTED_TILE = "Images/MapAOE.png";
    public static final String RANGED_TILE = "Images/MapRange.png";
    public static final String MAP_FOG = "Images/MapFog.png";
    public static final String MAP_HOVER = "Images/MapHover.png";
    public static final String HERO_1 = "Images/hero01.png";
    public static final String HERO_2 = "Images/hero02.png";
    public static final String HERO_3 = "Images/hero03.png";
    public static final String MAP_FILE = "map.txt";
    public static final String EARTH_SKILL = "Images/Skills/Earth.png";
    public static final String FIRE_SKILL = "Images/Skills/Fire.png";
    public static final String ICE_SKILL = "Images/Skills/Ice.png";
    public static final String THUNDER_SKILL = "Images/Skills/Thunder.png";
    public static final String WIND_SKILL = "Images/Skills/Wind.png";
    public static final String DEFAULT_SKILL = "Images/Skills/default.png";
    public static final int SPRITE_ROWS = 4;
    public static final int SPRITE_COLS = 4;
    public static final int SKILL_SIZE = 4;
    public static final int TILE_SIZE = 48;
    public static final int TILE_ROWS = 397;
    public static final int TILE_COLS = 8;

    public static final String CONTROL1 = "Images/Animation/control1.png";
    public static final String CONTROL2 = "Images/Animation/control2.png";
    public static final String FACE1 = "Images/Hero/face1.png";
    public static final String FACE2 = "Images/Hero/face2.png";
    public static final String FACE3 = "Images/Hero/face3.png";
    public static final String FACE4 = "Images/Hero/face1.png";
    public static final String FACE5 = "Images/Hero/face2.png";
    public static final String MINI_MAP = "Images/Animation/miniMap.png";
    public static final String AVATAR = "Images/Hero/test.png";
    public static final String SKILL1 = "Images/Animation/icon2.png";
    public static final String SKILL2 = "Images/Animation/skill3.png";
    public static final String TURN = "Images/Animation/turn.png";

    public static BufferedImage IMG_CONTROL1 = null;
    public static BufferedImage IMG_CONTROL2 = null;
    public static BufferedImage IMG_FACE1 = null;
    public static BufferedImage IMG_FACE2 = null;
    public static BufferedImage IMG_FACE3 = null;
    public static BufferedImage IMG_FACE4 = null;
    public static BufferedImage IMG_FACE5 = null;
    public static BufferedImage IMG_MINI_MAP = null;
    public static BufferedImage IMG_AVATAR = null;
    public static BufferedImage IMG_SKILL1 = null;
    public static BufferedImage IMG_SKILL2 = null;
    public static BufferedImage IMG_TURN = null;

    public static ApplicationContext factoryContext;
    public static final int MAP[][] = {
            {1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1, 1, 1, 1, 1, 1, 1, 1489, 1490, 1491},
            {1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1, 1, 1, 1, 1, 1, 1, 1497, 1497, 1497},
            {1637, 1638, 826, 826, 826, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1505, 1506, 1507},
            {1645, 1646, 826, 826, 826, 826, 1, 1878, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1398, 1399, 1400, 1513, 1514, 1515},
            {1637, 1638, 826, 826, 826, 826, 826, 1886, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2406, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1406, 1407, 1408, 1, 1, 1},
            {1645, 1646, 1, 826, 826, 826, 826, 826, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2414, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1414, 1415, 1416, 1, 1, 1},
            {1637, 1638, 1, 1878, 826, 826, 826, 826, 826, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1422, 1423, 1424, 1, 1, 1},
            {1645, 1646, 1, 1886, 1, 826, 826, 826, 826, 826, 1, 1636, 1, 1636, 1636, 1636, 1636, 1, 1, 1636, 1636, 1636, 1636, 1636, 1636, 1636, 1636, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1637, 1638, 1, 1, 1, 1, 826, 826, 826, 826, 826, 1644, 1636, 1644, 1644, 1644, 1644, 1, 1, 1644, 1644, 1644, 1644, 1644, 1644, 1644, 1644, 1636, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1645, 1646, 1, 1, 1, 1, 1, 826, 826, 826, 826, 826, 1644, 1636, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1636, 1644, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1637, 1638, 1, 1, 1, 1, 1, 1636, 826, 826, 826, 826, 826, 1644, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1636, 1644, 826, 826, 826, 1, 1, 1, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1644, 1636, 826, 826, 826, 826, 826, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1644, 826, 826, 826, 826, 1, 1, 1, 1, 1, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 1, 1, 1636, 1644, 1636, 826, 826, 826, 826, 826, 1, 1636, 1, 1, 1, 1, 1, 1, 1, 1, 826, 826, 826, 826, 826, 1636, 1636, 1, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1644, 1, 1644, 1, 826, 826, 826, 826, 826, 1644, 1636, 1, 1, 1, 1, 1636, 1, 826, 826, 826, 2406, 826, 1636, 1644, 1644, 1636, 1, 1, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 1, 1, 1636, 1, 1, 1, 1, 826, 826, 826, 826, 826, 1644, 1636, 1, 1, 1636, 1644, 826, 826, 826, 826, 2414, 1636, 1644, 1, 1, 1644, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1644, 1, 1, 1, 1, 1636, 826, 826, 826, 826, 826, 1644, 1636, 1636, 1644, 826, 826, 826, 826, 826, 1, 1644, 1, 1, 1, 1636, 1, 1, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 1, 1, 1636, 1, 1, 1, 1, 1644, 1636, 826, 826, 826, 826, 826, 1644, 1644, 826, 826, 826, 826, 826, 1, 1, 1, 1, 1, 1, 1644, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1644, 1, 1, 1, 1, 1, 1644, 1636, 826, 826, 826, 826, 826, 826, 826, 826, 826, 826, 1636, 1, 1, 1, 1, 1, 1, 1636, 1, 1, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1644, 1636, 826, 826, 826, 826, 826, 826, 1878, 826, 1636, 1644, 1, 1, 1, 1, 1, 1, 1644, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1644, 1636, 826, 826, 826, 826, 826, 1886, 1636, 1644, 1, 1, 1, 1, 1, 1, 1, 1636, 1, 2406, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 2406, 1, 1636, 1, 1, 1, 1, 1, 1, 1, 1636, 1644, 1878, 826, 826, 826, 826, 826, 1644, 1636, 1, 1, 1, 1, 1, 1, 1, 1644, 1, 2414, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 2414, 1, 1644, 1, 1, 1, 1, 1, 1, 1636, 1644, 826, 1886, 826, 826, 826, 826, 826, 826, 1644, 1636, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 1, 1, 1636, 1, 1, 1, 1, 1, 1636, 1644, 826, 826, 826, 826, 826, 826, 826, 826, 826, 826, 1644, 1636, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1644, 1, 1, 1, 1, 1, 1644, 826, 826, 826, 826, 826, 1636, 1636, 826, 826, 826, 826, 826, 1644, 1, 1, 1, 1, 1, 1636, 1, 1, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 1, 1, 1636, 1, 1, 1, 1, 1, 826, 826, 826, 826, 826, 1636, 1644, 1644, 1636, 826, 826, 826, 826, 826, 1, 1, 1, 1, 1, 1644, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1644, 1, 1, 1636, 1, 826, 826, 826, 826, 826, 1636, 1644, 1, 1, 1644, 1636, 826, 826, 826, 826, 826, 1, 1636, 1, 1, 1636, 1, 1, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 1, 1, 1636, 1, 1636, 1644, 2406, 826, 826, 826, 826, 1636, 1644, 1, 1, 1, 1, 1644, 1636, 826, 826, 826, 826, 826, 1644, 1636, 1, 1644, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1644, 1636, 1644, 826, 2414, 826, 826, 826, 1, 1644, 1, 1, 1, 1, 1, 1, 1644, 1, 826, 826, 826, 826, 826, 1644, 1636, 1636, 1, 1, 1, 1, 1, 1645, 1646},
            {1637, 1638, 1, 1, 1, 1, 1, 1, 1644, 826, 826, 826, 826, 826, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 826, 826, 826, 826, 826, 1644, 1636, 1, 1, 1, 1, 1, 1637, 1638},
            {1645, 1646, 1, 1, 1, 1, 1, 1, 1, 826, 826, 826, 826, 1636, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1636, 826, 826, 826, 826, 826, 1644, 1, 1, 1, 1, 1, 1645, 1646},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1636, 1644, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1644, 1636, 826, 826, 826, 826, 826, 1, 1, 1, 1, 1, 1637, 1638},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1644, 1636, 1636, 1636, 1636, 1636, 1636, 1, 1, 1636, 1636, 1636, 1636, 1636, 1636, 1644, 1636, 826, 826, 826, 826, 826, 1, 1, 1, 1, 1645, 1646},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1644, 1644, 1644, 1644, 1644, 1644, 1, 1, 1644, 1644, 1644, 1644, 1644, 1644, 1, 1644, 1, 826, 826, 826, 826, 826, 1, 1878, 1, 1637, 1638},
            {1, 1, 1, 1398, 1399, 1400, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 826, 826, 826, 826, 826, 1886, 1, 1645, 1646},
            {1, 1, 1, 1406, 1407, 1408, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2406, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 826, 826, 826, 826, 826, 1, 1637, 1638},
            {1, 1, 1, 1414, 1415, 1416, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2414, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1878, 826, 826, 826, 826, 826, 1645, 1646},
            {1, 1, 1, 1422, 1423, 1424, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1886, 1, 826, 826, 826, 826, 1637, 1638},
            {1489, 1490, 1491, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 826, 826, 826, 1645, 1646},
            {1497, 1497, 1497, 1, 1, 1, 1, 1, 1, 1, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638},
            {1505, 1506, 1507, 1, 1, 1, 1, 1, 1, 1, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646}
    };
    public static final int MOVEMAP[][] = {
            {1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1637, 1638, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1645, 1646, 0, 0, 0, 0, 0, 1878, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1398, 1399, 1400, 0, 0, 0},
            {1637, 1638, 0, 0, 0, 0, 0, 1886, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2406, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1406, 1407, 1408, 0, 0, 1},
            {1645, 1646, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2414, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1414, 1415, 1416, 0, 0, 1},
            {1637, 1638, 0, 1878, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1422, 1423, 1424, 0, 0, 1},
            {1645, 1646, 0, 1886, 0, 0, 0, 0, 0, 0, 0, 1636, 0, 1636, 1636, 1636, 1636, 0, 0, 1636, 1636, 1636, 1636, 1636, 1636, 1636, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1637, 1638, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 1644, 1644, 1644, 1644, 0, 0, 1644, 1644, 1644, 1644, 1644, 1644, 1644, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1645, 1646, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1636, 1644, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1637, 1638, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1636, 1644, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 0, 0, 1636, 1644, 1636, 0, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1636, 1636, 0, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 1644, 0, 1644, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 2406, 0, 1636, 1644, 1644, 1636, 0, 0, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 1636, 1644, 0, 0, 0, 0, 2414, 1636, 1644, 0, 0, 1644, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 1644, 1636, 1636, 1644, 0, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 1644, 1644, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 1878, 0, 1636, 1644, 0, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 1886, 1636, 1644, 0, 0, 0, 0, 0, 0, 0, 1636, 0, 2406, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 2406, 0, 1636, 0, 0, 0, 0, 0, 0, 0, 1636, 1644, 1878, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 1644, 0, 2414, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 2414, 0, 1644, 0, 0, 0, 0, 0, 0, 1636, 1644, 0, 1886, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 1636, 1644, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 1636, 1636, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1636, 1644, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 1644, 0, 0, 1636, 0, 0, 0, 0, 0, 0, 1636, 1644, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 1636, 0, 0, 1636, 0, 0, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 0, 0, 1636, 0, 1636, 1644, 2406, 0, 0, 0, 0, 1636, 1644, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 1644, 1636, 0, 1644, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 1644, 1636, 1644, 0, 2414, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 0, 1644, 1636, 1636, 0, 0, 0, 0, 0, 1645, 1646},
            {1637, 1638, 0, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 1637, 1638},
            {1645, 1646, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1636, 0, 0, 0, 0, 0, 1644, 0, 0, 0, 0, 0, 1645, 1646},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1636, 1644, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1637, 1638},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1636, 1636, 1636, 1636, 1636, 1636, 0, 0, 1636, 1636, 1636, 1636, 1636, 1636, 1644, 1636, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1645, 1646},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1644, 1644, 1644, 1644, 1644, 1644, 0, 0, 1644, 1644, 1644, 1644, 1644, 1644, 0, 1644, 0, 0, 0, 0, 0, 0, 0, 1878, 0, 1637, 1638},
            {0, 0, 0, 1398, 1399, 1400, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1886, 0, 1645, 1646},
            {0, 0, 0, 1406, 1407, 1408, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2406, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1637, 1638},
            {0, 0, 0, 1414, 1415, 1416, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2414, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1878, 0, 0, 0, 0, 0, 1645, 1646},
            {0, 0, 0, 1422, 1423, 1424, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1886, 0, 0, 0, 0, 0, 1637, 1638},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1645, 1646},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638, 1637, 1638},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646, 1645, 1646}
    };
    public static final int MAP_ROWS = MAP.length;
    public static final int MAP_COLS = MAP[0].length;
    public static BufferedImage[] normalArray, selectArray, hoverArray, fogArray, rangeArray, hero1Array, hero2Array, hero3Array, earthSkillArray, thunderSkillArray, windSkillArray, fireSkillArray, iceSkillArray, defaultSkillArray;
    public static int GRASS = 0, TREE = 1, FIRE = 2;

    public static void load() {
        initializeContent();
        loadMap();
        saveMap();
        importTile();
        importHero();
        importSkill();
        prepareImg();
    }

    public static void initializeContent() {
        factoryContext = new ClassPathXmlApplicationContext("factory-context.xml");
    }
    public static void prepareImg(){
        try {
            IMG_AVATAR = ImageIO.read(new File(AVATAR));
            IMG_CONTROL1 = ImageIO.read(new File(CONTROL1));
            IMG_CONTROL2 = ImageIO.read(new File(CONTROL2));
            IMG_FACE1 = ImageIO.read(new File(FACE1));
            IMG_FACE2 = ImageIO.read(new File(FACE2));
            IMG_FACE3 = ImageIO.read(new File(FACE3));
            IMG_FACE4 = ImageIO.read(new File(FACE4));
            IMG_FACE5 = ImageIO.read(new File(FACE5));
            IMG_MINI_MAP = ImageIO.read(new File(MINI_MAP));
            IMG_SKILL1 = ImageIO.read(new File(SKILL1));
            IMG_SKILL2 = ImageIO.read(new File(SKILL2));
            IMG_TURN = ImageIO.read(new File(TURN));

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static void importTile() {

        try {
            //declaring big images
            BufferedImage imgNormal = ImageIO.read(new File(TILE));
            BufferedImage imgSelect = ImageIO.read(new File(SELECTED_TILE));
            BufferedImage imgRange = ImageIO.read(new File(RANGED_TILE));
            BufferedImage imgFog = ImageIO.read(new File(MAP_FOG));
            BufferedImage imgHover = ImageIO.read(new File(MAP_HOVER));
            //split big images into smaller tile arrays
            BufferedImage[][] normalGrid = new BufferedImage[TILE_COLS][TILE_ROWS];
            BufferedImage[][] selectGrid = new BufferedImage[TILE_COLS][TILE_ROWS];
            BufferedImage[][] hoverGrid = new BufferedImage[TILE_COLS][TILE_ROWS];
            BufferedImage[][] fogGrid = new BufferedImage[TILE_COLS][TILE_ROWS];
            BufferedImage[][] rangeGrid = new BufferedImage[TILE_COLS][TILE_ROWS];
            normalArray = new BufferedImage[TILE_COLS * TILE_ROWS];
            selectArray = new BufferedImage[TILE_COLS * TILE_ROWS];
            hoverArray = new BufferedImage[TILE_COLS * TILE_ROWS];
            fogArray = new BufferedImage[TILE_COLS * TILE_ROWS];
            rangeArray = new BufferedImage[TILE_COLS * TILE_ROWS];
            for (int i = 0; i < TILE_ROWS; i++)
                for (int j = 0; j < TILE_COLS; j++) {
                    int index = (i * TILE_COLS) + j;
                    normalArray[index] = imgNormal.getSubimage(j * Utilizer.TILE_SIZE, i * Utilizer.TILE_SIZE, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                    selectArray[index] = imgSelect.getSubimage(j * Utilizer.TILE_SIZE, i * Utilizer.TILE_SIZE, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                    hoverArray[index] = imgHover.getSubimage(j * Utilizer.TILE_SIZE, i * Utilizer.TILE_SIZE, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                    fogArray[index] = imgFog.getSubimage(j * Utilizer.TILE_SIZE, i * Utilizer.TILE_SIZE, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                    rangeArray[index] = imgRange.getSubimage(j * Utilizer.TILE_SIZE, i * Utilizer.TILE_SIZE, Utilizer.TILE_SIZE, Utilizer.TILE_SIZE);
                }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public static void importHero() {
        try {
            BufferedImage hero1 = ImageIO.read(new File(HERO_1));
            BufferedImage hero2 = ImageIO.read(new File(HERO_2));
            BufferedImage hero3 = ImageIO.read(new File(HERO_3));
            hero1Array = new BufferedImage[SPRITE_COLS * SPRITE_ROWS];
            hero2Array = new BufferedImage[SPRITE_COLS * SPRITE_ROWS];
            hero3Array = new BufferedImage[SPRITE_COLS * SPRITE_ROWS];
            for (int i = 0; i < SPRITE_ROWS; i++)
                for (int j = 0; j < SPRITE_COLS; j++) {
                    int index = (i * SPRITE_COLS) + j;
                    hero1Array[index] = hero1.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    hero2Array[index] = hero2.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    hero3Array[index] = hero3.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void importSkill() {
        try {
            BufferedImage def = ImageIO.read(new File(DEFAULT_SKILL));
            BufferedImage earth = ImageIO.read(new File(EARTH_SKILL));
            BufferedImage fire = ImageIO.read(new File(FIRE_SKILL));
            BufferedImage ice = ImageIO.read(new File(ICE_SKILL));
            BufferedImage thunder = ImageIO.read(new File(THUNDER_SKILL));
            BufferedImage wind = ImageIO.read(new File(WIND_SKILL));
            earthSkillArray = new BufferedImage[SKILL_SIZE];
            fireSkillArray = new BufferedImage[SKILL_SIZE];
            iceSkillArray = new BufferedImage[SKILL_SIZE];
            thunderSkillArray = new BufferedImage[SKILL_SIZE];
            windSkillArray = new BufferedImage[SKILL_SIZE];
            defaultSkillArray = new BufferedImage[SKILL_SIZE];
            for (int i = 0; i < SPRITE_ROWS; i++) {
                defaultSkillArray[i]= def.getSubimage(i * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
                earthSkillArray[i] = earth.getSubimage(i * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
                fireSkillArray[i] = fire.getSubimage(i * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
                iceSkillArray[i] = ice.getSubimage(i * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
                thunderSkillArray[i] = thunder.getSubimage(i * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
                windSkillArray[i] = wind.getSubimage(i * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void loadMap() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(MAP_FILE));
            for (int i = 0; i < MAP_ROWS; i++) {

                String fullString = reader.readLine();
                StringTokenizer toz = new StringTokenizer(fullString, ",");
                int counter = 0;
                while (toz.hasMoreTokens()) {
                    int temp = Integer.parseInt(toz.nextToken());
                    MAP[i][counter] = temp;
                    counter++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    public static void saveMap() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(MAP_FILE));
            for (int i = 0; i < MAP_ROWS; i++) {
                for (int j = 0; j < MAP_COLS; j++) {
                    writer.write(MAP[i][j] + ",");
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
