package s.pen.sorest_pen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Screen screen = Screen.getPrimary();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root,screen.getBounds().getWidth(),screen.getBounds().getHeight());
        scene.setFill(new Color(0,0,0,0.01));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(E -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}