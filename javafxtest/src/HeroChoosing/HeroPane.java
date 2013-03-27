package HeroChoosing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Tooltip;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroPane extends BorderPane {
    public HeroPane()
    {
        //set hero description Panel
         VBox pnlLeft=new VBox();
         setPadding(new Insets(10, 12, 10, 12));
         pnlLeft.setPadding(new Insets(12, 10, 12, 10));
         pnlLeft.setSpacing(10);
         pnlLeft.setAlignment(Pos.TOP_CENTER);
         TextArea taHeroDesc=new TextArea();
         taHeroDesc.setPrefColumnCount(20);
         taHeroDesc.setPrefRowCount(1);
         taHeroDesc.setWrapText(false);
         taHeroDesc.setMaxSize(200, 200);
         taHeroDesc.setMinSize(200,200);
         Text descText=new Text("Description");
         pnlLeft.getChildren().addAll(descText, taHeroDesc);
         setLeft(pnlLeft);
         //set right description Panel
         VBox pnlRight=new VBox();
         pnlRight.setPadding(new Insets(10,12,10,12));
         pnlRight.setSpacing(10);
         pnlRight.setAlignment(Pos.TOP_CENTER);
         ImageView imgHero=new ImageView(new Image(HeroPane.class.getResourceAsStream("/css/lich.jpg")));
         imgHero.setFitHeight(200);
         imgHero.setFitWidth(150);;
         Text heroText=new Text("Lich");
         pnlRight.getChildren().addAll(heroText, imgHero);
         setRight(pnlRight);
        //set center Panel
         VBox pnlCenter=new VBox();
         pnlCenter.setPadding(new Insets(5,120,5,120));
         pnlCenter.setSpacing(20);
         pnlCenter.setAlignment(Pos.TOP_CENTER);
         //set hero list panel
         FlowPane pnlHeroList=new FlowPane();
         //pnlHeroList.setPadding(new Insets(5,100,5,100));
         pnlHeroList.setHgap(4);
         pnlHeroList.setVgap(4);
         pnlHeroList.setPrefWrapLength(240);
         ImageView[] heroes=new ImageView[9];
         for(int i=0;i<9;i++)
         {
             heroes[i]=new ImageView(new Image(HeroPane.class.getResourceAsStream("/css/chart_"+(i+1)+".png")));
             pnlHeroList.getChildren().add(heroes[i]);
         }
         //set skill Panel
         FlowPane pnlSkill =new FlowPane();
         pnlSkill.setPrefWrapLength(240);
         pnlSkill.setHgap(10);
         pnlSkill.setVgap(10);
         ImageView[] skills=new ImageView[3];
         for(int i=0;i<3;i++)
         {
            skills[i]=new ImageView(new Image(HeroPane.class.getResourceAsStream("/css/chart_"+(i+1)+".png")));
            Tooltip t=new Tooltip("haha");

            Tooltip.install(skills[i],t);
            pnlSkill.getChildren().add(skills[i]);
         }
         Text lblHero=new Text("Heroes");
         Text lblSkill=new Text("model/Skills");
         pnlCenter.getChildren().addAll(lblHero,pnlHeroList,lblSkill,pnlSkill);
         setCenter(pnlCenter);
    }
}
