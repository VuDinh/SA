import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/24/13
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutTest2 extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage stage)
    {
        stage.setTitle("Control stage");
        BorderPane bP=new BorderPane();

        bP.setTop(addHBox());
        bP.setLeft(addVBox());
        bP.setCenter(addGridPane());
        bP.setRight(addFlowPane());
        Scene scene=new Scene(bP,1080,800);
        stage.setScene(scene);
        scene.getStylesheets().add(LayoutTest2.class.getResource("/css/layout2.css").toExternalForm());
        stage.show();
    }
    public HBox addHBox()
    {
        HBox hB=new HBox();
        hB.setId("header");
        hB.setPadding(new Insets(15,12,15,12));
        hB.setSpacing(12);
        Button btnLogout=new Button("Log out");
        Button btnSettings=new Button("Settings");
        btnLogout.setPrefSize(100,20);
        btnSettings.setPrefSize(100,20);
        hB.getChildren().addAll(btnLogout,btnSettings);
        return hB;
    }
    public VBox addVBox()
    {
        VBox vB=new VBox();
        vB.setId("nav");
        vB.setPadding(new Insets(8));
        vB.setSpacing(20);
        Hyperlink options[] = new Hyperlink[] {
                new Hyperlink("Online Match"),
                new Hyperlink("Bot Match"),
        };

        for (int i=0; i<2; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vB.getChildren().add(options[i]);
        }
        return vB;
    }
    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(50,50,50,600));

//        // Category in column 2, row 1
//        Text category = new Text("Sales:");
//        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        grid.add(category, 1, 0);
//
//        // Title in column 3, row 1
//        Text chartTitle = new Text("Current Year");
//        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        grid.add(chartTitle, 2, 0);
//
//        // Subtitle in columns 2-3, row 2
//        Text chartSubtitle = new Text("Goods and Services");
//        grid.add(chartSubtitle, 1, 1, 2, 1);
//
//        // House icon in column 1, rows 1-2


        // Right label in column 4 (top), row 3
//        Text servicesPercent = new Text("Services\n20%");
//        GridPane.setValignment(servicesPercent, VPos.TOP);
//        grid.add(servicesPercent, 3, 2);

        return grid;
    }
    public FlowPane addFlowPane() {
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(50); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: DAE6F3;");
        return flow;
    }
}
