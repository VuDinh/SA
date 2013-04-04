package View.Login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created with IntelliJ IDEA.
 * User: long
 * Date: 3/28/13
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPane extends GridPane {
    TextField txtUser;
    PasswordField txtPass;
    Button btnLogin;
    Text actionText;
    public LoginPane(){
        setAlignment(Pos.TOP_LEFT);

        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        //add component to the grid
        //add title
        /*Text loginText=new Text("Log in");
        loginText.setId("login-text");
        loginText.setFont(Font.font("Times New Romans", FontWeight.BOLD, 20));
        add(loginText, 0, 0, 2, 1);*/
        //add name fields
        Label lblUser=new Label("Username:");
        add(lblUser, 0, 1);
        txtUser=new TextField();
        add(txtUser, 1, 1);
        //add Password fields
        Label lblPassword=new Label("Password:");
        add(lblPassword, 0, 2);
        txtPass=new PasswordField();
        add(txtPass, 1, 2);
        //add login Button
        btnLogin=new Button("Sign In");
        HBox hb=new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().add(btnLogin);
        add(hb, 1, 4);
        //add action text
        actionText=new Text();
        actionText.setId("action-text");
        add(actionText, 1, 6);
    }
    public void addLoginListener(EventHandler<ActionEvent> event){
        btnLogin.setOnAction(event);
    }
    public String getUsername(){
        return txtUser.getText();
    }
    public String getPassword(){
        return txtPass.getText();
    }
    public void setActionText(String text){
        actionText.setText(text);
    }
}
