package pl.coderslab.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ErpApplication extends Application {

//    public static final String title = "Enterprise App";
    public static final int width = 600;
    public static final int height = 400;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ErpApplication.class.getResource("/javafx/login.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/javafx/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
//        stage.setTitle(title);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}