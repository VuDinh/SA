package View.HeroChoosing;

import Controllers.listeners.HeroChoosingFactory;
import Utilities.Utilizer;
import View.MainMenu.ChatPane2;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.AccountSystem.Account;
import model.Facade.Facade;
import model.HeroSystem.Hero;
import model.Skills.AOESkill;
import model.Skills.Skill;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroPane extends BorderPane {
    TextArea taHeroDesc;
    ImageView imgHero;
    Text txtHeroName;
    Text descText;
    ChatPane2 chatPane;
    ImageView[] skills;
    Button[] skillButtons;
    Button[] heroButtons;
    Facade facade;
    int heroIndex;
    Font font;

    public HeroPane(Facade facade) {
        this.facade = facade;
        //set hero description Panel
        setId("heroPane");
        heroIndex = 0;
        VBox pnlRight = new VBox();
        setPadding(new Insets(10, 12, 10, 12));
        pnlRight.setPadding(new Insets(12, 10, 12, 10));
        pnlRight.setSpacing(10);
        pnlRight.setAlignment(Pos.TOP_CENTER);
        taHeroDesc = new TextArea();
        taHeroDesc.setPrefColumnCount(20);
        taHeroDesc.setPrefRowCount(1);
        taHeroDesc.setWrapText(false);
        taHeroDesc.setMaxSize(300, 250);
        taHeroDesc.setMinSize(300, 250);
        taHeroDesc.setEditable(false);
        taHeroDesc.setMouseTransparent(false);
        taHeroDesc.setWrapText(true);
        descText = new Text("Description");
        BufferedImage buffImgHero = facade.getLibraryHero(0).getChoosing();
        imgHero = new ImageView(SwingFXUtils.toFXImage(buffImgHero, new WritableImage(buffImgHero.getWidth()
                , buffImgHero.getHeight())));
        imgHero.setFitHeight(200);
        imgHero.setFitWidth(150);
        ;
        txtHeroName = new Text(facade.getLibraryHero(0).getName());
        pnlRight.getChildren().addAll(txtHeroName, imgHero, descText, taHeroDesc);
        setRight(pnlRight);

        //set center Panel
        VBox pnlCenter = new VBox();
        //pnlCenter.setPadding(new Insets(5,120,5,120));
        pnlCenter.setSpacing(20);
        pnlCenter.setAlignment(Pos.TOP_CENTER);
        //set hero list panel
        GridPane pnlHeroList = new GridPane();
        pnlHeroList.setId("heroList");
        //pnlHeroList.setPadding(new Insets(5,100,5,100));
        pnlHeroList.setHgap(4);
        pnlHeroList.setVgap(4);
        pnlHeroList.setAlignment(Pos.CENTER);
        ImageView[] heroes = new ImageView[facade.getNumberOfHeroes() + 1];
        heroButtons = new Button[facade.getNumberOfHeroes() + 1];
        for (int i = 0; i < heroes.length; i++) {
            BufferedImage temp;
            if (i != heroes.length - 1) {
                temp = this.facade.getLibraryHero(i).getIcon();
            } else {
                temp = Utilizer.IMG_FACE8;
            }
            heroes[i] = new ImageView(SwingFXUtils.toFXImage(temp, new WritableImage(temp.getWidth(), temp.getHeight())));
            heroButtons[i] = new Button();
            heroButtons[i].setGraphic(heroes[i]);
            pnlHeroList.add(heroButtons[i], i % 3, i / 3);
        }
        //set skill Panel
        FlowPane pnlSkill = new FlowPane();
        pnlSkill.setPrefWrapLength(240);
        pnlSkill.setHgap(10);
        pnlSkill.setVgap(10);
        pnlSkill.setAlignment(Pos.CENTER);
        skills = new ImageView[3];
        skillButtons = new Button[3];
        for (int i = 0; i < 3; i++) {
            BufferedImage temp = facade.getLibraryHero(0).getSkill(i).getIcon();
            skills[i] = new ImageView(SwingFXUtils.toFXImage(temp, new WritableImage(temp.getWidth(), temp.getHeight())));
            skillButtons[i] = new Button();
            skillButtons[i].setGraphic(skills[i]);
            pnlSkill.getChildren().add(skillButtons[i]);
        }
        Text lblHero = new Text("Heroes");
        Text lblSkill = new Text("Skills");
        chatPane = new ChatPane2();
        pnlCenter.getChildren().addAll(lblHero, pnlHeroList, lblSkill, pnlSkill, chatPane);
        setCenter(pnlCenter);
        chatPane.getTxtChat().setId("chatField");
        chatPane.getTaDialog().setId("chatArea");
        taHeroDesc.setId("description");
        font = new Font("font/OldLondon",16);
        lblHero.setFont(font);
        lblSkill.setFont(font);
        txtHeroName.setFont(font);
        descText.setFont(font);
    }

    //set Hero selected to the image, description
    public void setHero(Hero hero) {

    }

    public void setHeroIcon(int index) {

    }

    public void setUnknownHero() {

    }

    public void addHeroChoosingListener(HeroChoosingFactory heroChoosingFactory) {
        for (int i = 0; i < heroButtons.length; i++) {
            heroButtons[i].setOnAction(heroChoosingFactory.heroChoosingListener(i));
        }
        chatPane.addChatListener(heroChoosingFactory.chatListener());
    }

    public void addHeroHoveringListener(HeroChoosingFactory heroChoosingFactory) {
        for (int i = 0; i < heroButtons.length; i++) {
            heroButtons[i].setOnMouseEntered(heroChoosingFactory.heroHoveringListener(i));
        }
        for (int i = 0; i < 3; i++) {
            skillButtons[i].setOnMouseEntered(heroChoosingFactory.skillHoveringListener(i));
        }
    }

    public void setHoveredHero(int index) {
        if (index != facade.getNumberOfHeroes()) {
            heroIndex = index;
            Hero hero = facade.getLibraryHero(index);
            //set avatar for hero
            BufferedImage buffImgHero = hero.getChoosing();
            imgHero.setImage(SwingFXUtils.toFXImage(buffImgHero, new WritableImage(buffImgHero.getWidth()
                    , buffImgHero.getHeight())));
            //set Name for hero
            txtHeroName.setText(hero.getName());
            //set Skill for hero
            for (int i = 0; i < 3; i++) {
                BufferedImage temp = hero.getSkill(i).getIcon();
                skillButtons[i].setGraphic(new ImageView(SwingFXUtils.toFXImage(temp, new WritableImage(temp.getWidth(), temp.getHeight()))));
            }
            //set description for hero
            String desText = "";
            desText += "Hero: " + hero.getName() + "\n";
            desText += "Attack: " + hero.getAttk() + "\n";
            desText += "HP: " + hero.getMaxHP() + "\n";
            desText += "AP: " + hero.getMaxAP() + "\n" + "\n";
            desText += hero.getDescription() + "\n";
            taHeroDesc.setText(desText);
        }
    }
    public ChatPane2 getChatPane(){
        return chatPane;
    }

    public void setHoveredSkill(int index) {
        if (heroIndex != facade.getNumberOfHeroes()) {
            String desc = "Skill: ";
            desc = desc + facade.getLibraryHero(heroIndex).getSkill(index).getName()+"\n";
            desc = desc + "Multiplier: ";
            desc = desc +  facade.getLibraryHero(heroIndex).getSkill(index).getMultiplier()+"\n";
            desc = desc + "AP: ";
            desc = desc +  facade.getLibraryHero(heroIndex).getSkill(index).getAP()+"\n";
            desc = desc + "Range: ";
            desc = desc +  facade.getLibraryHero(heroIndex).getSkill(index).getRange()+"\n";
            if(facade.getLibraryHero(heroIndex).getSkill(index) instanceof AOESkill){
                desc = desc + "AOE: ";
                desc = desc +  ((AOESkill) facade.getLibraryHero(heroIndex).getSkill(index)).getAoe()+"\n";
            }
                    taHeroDesc.setText(desc);
        }
    }
    public void announceSelectedHero(String account,String heroName){
        chatPane.announceSelectedHero(account,heroName);
    }
}
