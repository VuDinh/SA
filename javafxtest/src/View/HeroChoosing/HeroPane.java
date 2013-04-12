package View.HeroChoosing;

import Utilities.Utilizer;
import View.MainMenu.ChatPane;
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
import javafx.scene.text.Text;
import javafx.scene.control.Tooltip;
import model.HeroSystem.Hero;

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
    Text heroText;
    Text descText;
    ChatPane2 chatPane;
    ImageView[] skills;
    ImageView[] heroes;
    public HeroPane()
    {
        //set hero description Panel
         setId("heroPane");
         VBox pnlRight=new VBox();
         setPadding(new Insets(10, 12, 10, 12));
         pnlRight.setPadding(new Insets(12, 10, 12, 10));
         pnlRight.setSpacing(10);
         pnlRight.setAlignment(Pos.TOP_CENTER);
         taHeroDesc=new TextArea();
         taHeroDesc.setPrefColumnCount(20);
         taHeroDesc.setPrefRowCount(1);
         taHeroDesc.setWrapText(false);
         taHeroDesc.setMaxSize(300, 250);
         taHeroDesc.setMinSize(300,250);
         taHeroDesc.setEditable(false);
         taHeroDesc.setMouseTransparent(false);
         descText=new Text("Description");
         imgHero=new ImageView(new Image(HeroPane.class.getResourceAsStream("/css/lich.jpg")));
         imgHero.setFitHeight(200);
         imgHero.setFitWidth(150);;
         heroText=new Text("Lich");
         pnlRight.getChildren().addAll(heroText, imgHero,descText, taHeroDesc);
         setRight(pnlRight);

        //set center Panel
         VBox pnlCenter=new VBox();
         //pnlCenter.setPadding(new Insets(5,120,5,120));
         pnlCenter.setSpacing(20);
         pnlCenter.setAlignment(Pos.TOP_CENTER);
         //set hero list panel
         GridPane pnlHeroList=new GridPane();
         pnlHeroList.setId("heroList");
         //pnlHeroList.setPadding(new Insets(5,100,5,100));
         pnlHeroList.setHgap(4);
         pnlHeroList.setVgap(4);
         pnlHeroList.setAlignment(Pos.CENTER);
         heroes=new ImageView[8];
         Utilizer.load();
         for(int i=0;i<heroes.length;i++)
         {
             BufferedImage temp=Utilizer.HEROIMAGEPACK.get(i).getIcon();
             heroes[i]=new ImageView(SwingFXUtils.toFXImage(temp,new WritableImage(temp.getWidth(),temp.getHeight())));
             Button button=new Button();
             button.setGraphic(heroes[i]);
             pnlHeroList.add(button, i / 3, i % 3);
         }
         //set skill Panel
         FlowPane pnlSkill =new FlowPane();
         pnlSkill.setPrefWrapLength(240);
         pnlSkill.setHgap(10);
         pnlSkill.setVgap(10);
         pnlSkill.setAlignment(Pos.CENTER);
         skills=new ImageView[3];
         for(int i=0;i<3;i++){
            skills[i]=new ImageView(new Image(HeroPane.class.getResourceAsStream("/css/chart_"+(i+1)+".png")));
            Tooltip t=new Tooltip("haha");

            Tooltip.install(skills[i],t);
            pnlSkill.getChildren().add(skills[i]);
         }
         Text lblHero=new Text("Heroes");
         Text lblSkill=new Text("model/Skills");
         chatPane=new ChatPane2();
         pnlCenter.getChildren().addAll(lblHero,pnlHeroList,lblSkill,pnlSkill,chatPane);
         setCenter(pnlCenter);
         chatPane.getTxtChat().setId("chatField");
         chatPane.getTaDialog().setId("chatArea");
         taHeroDesc.setId("description");
    }
    //set Hero selected to the image, description
    public void setHero(Hero hero){

    }

    public void setHeroIcon(int index){

    }
    public void setUnknownHero(){

    }
    //set already have heroes and their descriptions
    public void initialise(){

    }
}
