package View.HeroChoosing;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 2/27/13
 * Time: 12:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeroChoosingHeader extends HBox {
    Text lblCountdown;
    public HeroChoosingHeader()
    {
          setId("header");
          setPadding(new Insets(15,12,15,12));
          setSpacing(10);
          setAlignment(Pos.CENTER);
          lblCountdown=new Text("0:30");
          lblCountdown.setFont(Font.font("Arial", FontWeight.BOLD,20));
          getChildren().addAll(lblCountdown);
    }
    public void setCountdownTime(int t){
        lblCountdown.setText("0:"+t);
    }
}
