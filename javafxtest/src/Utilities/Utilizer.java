package Utilities;

import View.Ingame.Cell;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.audio.AudioPlayer;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

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
    public static final String HERO_1 = "Images/Hero/hero01.png";
    public static final String HERO_2 = "Images/Hero/hero02.png";
    public static final String HERO_3 = "Images/Hero/hero03.png";
    public static final String DIE = "Images/Hero/graves.png";
    public static final String MONSTER_1 = "Images/Hero/monster01.png";
    public static final String MONSTER_2 = "Images/Hero/monster02.png";
    public static final String MONSTER_3 = "Images/Hero/monster03.png";
    public static final String MAP_FILE = "map.txt";
    public static final String EARTH_SKILL = "Images/Skills/Earth.png";
    public static final String FIRE_SKILL = "Images/Skills/Fire.png";
    public static final String ICE_SKILL = "Images/Skills/Ice.png";
    public static final String THUNDER_SKILL = "Images/Skills/Thunder.png";
    public static final String WIND_SKILL = "Images/Skills/Wind.png";
    public static final String DEFAULT_SKILL = "Images/Skills/default.png";
    public static final int SPRITE_ROWS = 6;
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

    public static final String AVATAR1 = "Images/Hero/avatar1.png";
    public static final String AVATAR2 = "Images/Hero/avatar2.png";
    public static final String AVATAR3 = "Images/Hero/avatar3.png";

    public static final String SKILL1 = "Images/Animation/icon2.png";
    public static final String SKILL2 = "Images/Animation/skill3.png";
    public static final String TURN = "Images/Animation/turn.png";
    public static final String BAR = "Images/Animation/bar.png";
    public static final String CHAT_BACK = "Images/Animation/chatBack.png";

    public static final String SKILL_ICON_FIRE = "Images/Skills/fire icon.png";
    public static final String SKILL_ICON_THUNDER = "Images/Skills/thunder icon.png";
    public static final String SKILL_ICON_WIND = "Images/Skills/wind icon.png";
    public static final String SKILL_ICON_EARTH = "Images/Skills/earth icon.png";

    public static final String SOUND_ATTACK = "Sounds/attack.wav";
    public static final String SOUND_FIRE = "Sounds/fire.wav";
    public static final String SOUND_EARTH = "Sounds/earth.wav";
    public static final String SOUND_WIND = "Sounds/wind.wav";
    public static final String SOUND_THUNDER = "Sounds/thunder.wav";
    public static final String SOUND_VICTORY = "Sounds/Victory.mid";
    public static final String SOUND_DEFEAT = "Sounds/Defeated.mid";
    public static final String SOUND_THEME1 = "Sounds/RPGTheme.mid";
    public static final String SOUND_THEME2 = "Sounds/TacticsTheme.mid";

    public static BufferedImage IMG_CONTROL1 = null;
    public static BufferedImage IMG_CONTROL2 = null;
    public static BufferedImage IMG_FACE1 = null;
    public static BufferedImage IMG_FACE2 = null;
    public static BufferedImage IMG_FACE3 = null;
    public static BufferedImage IMG_FACE4 = null;
    public static BufferedImage IMG_FACE5 = null;
    public static BufferedImage IMG_MINI_MAP = null;
    public static BufferedImage IMG_AVATAR1 = null;
    public static BufferedImage IMG_AVATAR2 = null;
    public static BufferedImage IMG_AVATAR3 = null;
    public static BufferedImage IMG_SKILL1 = null;
    public static BufferedImage IMG_SKILL2 = null;
    public static BufferedImage IMG_TURN = null;
    public static BufferedImage IMG_BAR = null;
    public static BufferedImage IMG_CHAT_BACK = null;
    public static BufferedImage IMG_SKILL_ICON_FIRE = null;
    public static BufferedImage IMG_SKILL_ICON_THUNDER = null;
    public static BufferedImage IMG_SKILL_ICON_WIND = null;
    public static BufferedImage IMG_SKILL_ICON_EARTH = null;

    public static ApplicationContext factoryContext;
    public static int MAP[][];
    public static int MOVEMAP[][] = {
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
    public static final int MAP_ROWS = 40;
    public static final int MAP_COLS = 40;
    public static BufferedImage[] normalArray, selectArray, hoverArray, fogArray, rangeArray, dieArray, hero1Array, hero2Array, hero3Array, monster1Array,monster2Array, monster3Array, earthSkillArray, thunderSkillArray, windSkillArray, fireSkillArray, iceSkillArray, defaultSkillArray;
    public static int GRASS = 0, TREE = 1, FIRE = 2;
    public static ArrayList<HeroImage> HEROIMAGEPACK;
    public static ArrayList<SkillImage> SKILLIMAGEPACK;
    public static void load() {
        initializeContent();
        loadMap();
        saveMap();
        importTile();
        importHero();
        importSkill();
        prepareImg();
        addingHeroImage();
        addingSkillImage();
    }

    public static void initializeContent() {
        factoryContext = new ClassPathXmlApplicationContext("factory-context.xml");
    }
    public static void prepareImg(){
        try {
            IMG_AVATAR1 = ImageIO.read(new File(AVATAR1));
            IMG_AVATAR2 = ImageIO.read(new File(AVATAR2));
            IMG_AVATAR3 = ImageIO.read(new File(AVATAR3));
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
            IMG_BAR = ImageIO.read(new File(BAR));
            IMG_CHAT_BACK = ImageIO.read(new File(CHAT_BACK));
            IMG_SKILL_ICON_FIRE = ImageIO.read(new File(SKILL_ICON_FIRE));
            IMG_SKILL_ICON_THUNDER = ImageIO.read(new File(SKILL_ICON_THUNDER));
            IMG_SKILL_ICON_WIND = ImageIO.read(new File(SKILL_ICON_WIND));
            IMG_SKILL_ICON_EARTH = ImageIO.read(new File(SKILL_ICON_EARTH));

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
            BufferedImage monster1 = ImageIO.read(new File(MONSTER_1));
            BufferedImage monster2 = ImageIO.read(new File(MONSTER_2));
            BufferedImage monster3 = ImageIO.read(new File(MONSTER_3));
            BufferedImage die = ImageIO.read(new File(DIE));
            hero1Array = new BufferedImage[SPRITE_COLS * SPRITE_ROWS];
            hero2Array = new BufferedImage[SPRITE_COLS * SPRITE_ROWS];
            hero3Array = new BufferedImage[SPRITE_COLS * SPRITE_ROWS];
            monster1Array = new BufferedImage[SPRITE_COLS * SPRITE_COLS];
            monster2Array = new BufferedImage[SPRITE_COLS * SPRITE_COLS];
            monster3Array = new BufferedImage[SPRITE_COLS * SPRITE_COLS];
            dieArray = new BufferedImage[SPRITE_COLS * SPRITE_COLS];
            for (int i = 0; i < SPRITE_ROWS; i++)
                for (int j = 0; j < SPRITE_COLS; j++) {
                    int index = (i * SPRITE_COLS) + j;
                    hero1Array[index] = hero1.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    hero2Array[index] = hero2.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    hero3Array[index] = hero3.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            for (int i = 0; i < SPRITE_COLS; i++)
                for (int j = 0; j < SPRITE_COLS; j++) {
                    int index = (i * SPRITE_COLS) + j;
                    monster1Array[index] = monster1.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    monster2Array[index] = monster2.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    monster3Array[index] = monster3.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    dieArray[index]      =      die.getSubimage(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);

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
            for (int i = 0; i < SPRITE_COLS; i++) {
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
            MAP=new int[MAP_ROWS][MAP_COLS];
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
            //generate movemap
            Integer[] a={1,826,1489,1491,1497,1505,1506,1507};
            List<Integer> movableTiles= Arrays.asList(a);
            MOVEMAP = new int[MAP_ROWS][MAP_COLS];
            for(int i=0;i<MAP_ROWS;i++)
                for(int j=0;j<MAP_COLS;j++)
                    if(movableTiles.contains(MAP[i][j]))
                        MOVEMAP[i][j]=0;
                    else
                        MOVEMAP[i][j]=MAP[i][j];


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

    public static void playWAV(String fileName, int loopCount) {
        AudioPlayer BGP = AudioPlayer.player;
        ContinuousAudioDataStream loop = null;
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            //repeat times
            clip.loop(loopCount);
            clip.start();

        } catch (Exception e) {
            //e.printStackTrace();
        }
        BGP.start(loop);

    }

    //Play.mid files
    public static void playMIDI(String fileName, int loopCount) {
        AudioPlayer BGP = AudioPlayer.player;
        ContinuousAudioDataStream loop = null;
        try {
            File inputFile = new File(fileName);
            Sequence seq;
            Sequencer sequencer;
            sequencer = MidiSystem.getSequencer();
            seq = MidiSystem.getSequence(inputFile);
            sequencer.setSequence(seq);
            //repeat times
            sequencer.setLoopCount(loopCount);
            sequencer.open();
            sequencer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        BGP.start(loop);
    }

    public static boolean inRange(Cell c, ArrayList<Cell> cells){
        Iterator i = cells.iterator();
        while(i.hasNext()){
            Cell cell = (Cell)i.next();
            if(c.getRowPos()==cell.getRowPos() && c.getColPos()==cell.getColPos())return true;
        }
        return false;
    }
    public static void addingHeroImage(){
        HEROIMAGEPACK =new ArrayList<HeroImage>();
        HEROIMAGEPACK.add(new HeroImage(hero1Array,IMG_AVATAR1,IMG_FACE1));
        HEROIMAGEPACK.add(new HeroImage(hero2Array,IMG_AVATAR2,IMG_FACE2));
        HEROIMAGEPACK.add(new HeroImage(hero3Array,IMG_AVATAR3,IMG_FACE3));
        HEROIMAGEPACK.add(new HeroImage(monster1Array,null,null));
        HEROIMAGEPACK.add(new HeroImage(monster2Array,null,null));
        HEROIMAGEPACK.add(new HeroImage(monster3Array,null,null));
        HEROIMAGEPACK.add(new HeroImage(dieArray,null,null));
    }
    public static void addingSkillImage(){
        SKILLIMAGEPACK=new ArrayList<SkillImage>();
        SkillImage skill1=new SkillImage();
        skill1.addIcon(IMG_SKILL_ICON_FIRE);
        skill1.addIcon(IMG_SKILL_ICON_FIRE);
        skill1.addIcon(IMG_SKILL_ICON_THUNDER);
        skill1.addIcon(IMG_SKILL_ICON_WIND);
        skill1.addIcon(IMG_SKILL_ICON_EARTH);
        skill1.addSprite(defaultSkillArray);
        skill1.addSprite(fireSkillArray);
        skill1.addSprite(thunderSkillArray);
        skill1.addSprite(windSkillArray);
        skill1.addSprite(earthSkillArray);
        SKILLIMAGEPACK.add(skill1);
    }

    public static ArrayList<Cell> straightPath(Cell from, Cell to){
        ArrayList<Cell> path = new ArrayList<Cell>();
        if(from.getRowPos() == to.getRowPos()){
            int range = Math.abs(from.getColPos()-to.getColPos());
            if(from.getColPos()<to.getColPos())
                for(int i = 0; i<=range; i++)path.add(new Cell(from.getColPos()+i,from.getRowPos()));
            else
                for(int i = 0; i<=range; i++)path.add(new Cell(from.getColPos()-i,from.getRowPos()));
            return path;
        }else if(from.getColPos() == to.getColPos()){
            int range = Math.abs(from.getRowPos()-to.getRowPos());
            if(from.getRowPos()<to.getRowPos())
                for(int i = 0; i<=range; i++)path.add(new Cell(from.getColPos(),from.getRowPos()+i));
            else
                for(int i = 0; i<=range; i++)path.add(new Cell(from.getColPos(),from.getRowPos()-i));
            return path;
        }else return null;
    }

}
