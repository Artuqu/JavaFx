package pl.zone.factory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.zone.handler.InfoPopupOkHandler;

public class PopupFactory {

    public Stage Popup(String text) {
        return null;
    }

    public Stage createWaitingPopup(String text) {
        Stage stage = new Stage();
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.setStyle(paneStyle());
        Label label = new Label(text);
        label.setStyle(labelStyle());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(pane, 200, 100));
        stage.initModality(Modality.APPLICATION_MODAL);

        ProgressBar progressBar = new ProgressBar();
        pane.getChildren().addAll(label, progressBar);
        return stage;
    }

    private String labelStyle() {
        return " -fx-text-fill: #003366;";
    }

    private String paneStyle() {
        return "-fx-background: #a7a9ac;  -fx-border-color: #003366;";
    }

    private String okButtonStyle() {
        return " -fx-text-fill: #003366; -fx-background-color: #a7a9ac; -fx-border-color: #003366; -fx-background-radius: 0;";
    }

    private String okButtonHoverStyle() {
        return "-fx-background-color: #caced1; -fx-background-radius: 0; -fx-border-color: #003366; -fx-text-fill: #003366;";
    }


    public Stage createInfoPopup(String text, InfoPopupOkHandler infoPopupOkHandler) {
        Stage stage = new Stage();
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.setStyle(paneStyle());
        Label label = new Label(text);
        label.setStyle(labelStyle());

        Button okButton = new Button("OK");
        okButton.setStyle(okButtonStyle());
        okButton.setOnMouseEntered(x -> okButton.setStyle(okButtonHoverStyle()));
        okButton.setOnMouseExited(x -> okButton.setStyle(okButtonStyle()));
        okButton.setOnAction(x -> {
            stage.close();
            infoPopupOkHandler.handler();
        });
        pane.getChildren().addAll(label, okButton);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(pane, 200, 100));
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }
}
