package cm.supptic.subclass;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Noumodong
 */
public class StageManager {

    public void changerStage(String toCharge) {
        try {
            Pane root = FXMLLoader.load(getClass().getResource("/cm/supptic/fxml/" + toCharge));
            Stage stageInit = new Stage();

            stageInit.initStyle(StageStyle.UNDECORATED);
            stageInit.initStyle(StageStyle.TRANSPARENT);
            stageInit.getIcons().add(new Image(getClass().getResource("/cm/supptic/icons/Eye_48px.png").toString()));
            stageInit.setTitle("Gestions des vacasions");
            Scene scene = new Scene(root);
            scene.setFill(Paint.valueOf("transparent"));
            stageInit.setScene(scene);
            stageInit.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
