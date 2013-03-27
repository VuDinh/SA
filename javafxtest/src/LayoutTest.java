import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/24/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutTest extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }
     @Override
    public void start(Stage primaryStage)
    {
            primaryStage.setTitle("Testing javafx");
            Button btn=new Button("hello");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    System.out.println("Hello world");
                }

            });
            StackPane root=new StackPane();
            root.getChildren().add(btn);
            primaryStage.setScene(new Scene(root,200,200));
            primaryStage.show();
    }
}
