import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/24/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutTest1 extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Played as");
        GridPane gp=new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(25,25,25,25));
        //add component to the grid
        //add title
        Text loginText=new Text("Played as");
        loginText.setId("login-text");
        loginText.setFont(Font.font("Times New Romans", FontWeight.BOLD, 20));
        gp.add(loginText,0,0,2,1);
        //add name fields
        Label lblUser=new Label("Username:");
        gp.add(lblUser, 0,1);
        TextField txtUser=new TextField();
        gp.add(txtUser,1,1);
        //add Password fields
        Label lblPassword=new Label("Password:");
        gp.add(lblPassword, 0,2);
        PasswordField txtPass=new PasswordField();
        gp.add(txtPass, 1,2);
        //add login Button
        Button btnLogin=new Button("Sign In");
        HBox hb=new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().add(btnLogin);
        gp.add(hb,1,4);
        //add action text
        final Text actionText=new Text();
        actionText.setId("action-text");
        gp.add(actionText,1,6);
        //add action event
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                actionText.setFill(Color.RED);
                actionText.setText("Signing in ...");
            }
        });
        Scene scene=new Scene(gp,300,275);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(LayoutTest1.class.getResource("/css/layout1.css").toExternalForm());

    }
}
