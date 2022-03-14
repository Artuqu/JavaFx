package pl.zone.factory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupFactory {

    public Stage createWaitingPopup(String text) {
        Stage stage = new Stage();
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.setStyle(paneStyle());

        Label label = new Label(text);
        label.setStyle(labelStyle());
        ProgressBar progressBar = new ProgressBar();
        pane.getChildren().addAll(label, progressBar);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(pane, 200, 100));
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private String labelStyle() {
        return " -fx-text-fill: #003366;";
    }

    private String paneStyle() {
        return "-fx-background: #a7a9ac;  -fx-border-color: #003366;";
    }
}
