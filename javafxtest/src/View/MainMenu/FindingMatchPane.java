package View.MainMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/8/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindingMatchPane extends VBox {
    Button btnFindingMatch;
    Text txtStatus;
    public FindingMatchPane(){
        setAlignment(Pos.CENTER);
        setId("findingBorder");
        setSpacing(5);
        btnFindingMatch=new Button("Find Match");
        btnFindingMatch.setFont(Font.font("Arial", FontWeight.BOLD,20));
        btnFindingMatch.setPrefSize(300,200);
        txtStatus=new Text("Finding ...");
        this.getChildren().addAll(btnFindingMatch,txtStatus);

    }
    public void setStatusText(String text){
        txtStatus.setText(text);
    }
    public void addFindMatchListener(EventHandler<ActionEvent> e){
        btnFindingMatch.setOnAction(e);
    }
}
