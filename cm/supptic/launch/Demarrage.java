package cm.supptic.launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Noumodong
 */
public class Demarrage extends Application {
    //thanks :)
    //Démarrage de l'application
    public static void main(String[] args) {
        Application.launch(args);
    }
    public Stage stageStatic;

    @Override
    public void start(Stage stage) throws Exception {

        stageStatic = stage;
        AnchorPane pan = FXMLLoader.load(getClass().getResource("/cm/supptic/fxml/login.fxml"));

        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image(getClass().getResource("/cm/supptic/icons/Eye_48px.png").toString()));
        stage.setTitle("Gestions des vacasions");
        stage.setResizable(true);
        Scene scene = new Scene(pan);
        scene.setFill(Paint.valueOf("transparent"));
        stage.setScene(scene);
        stage.show();

    }

}
