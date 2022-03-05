module pl.coderslab.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.coderslab.javafx to javafx.fxml;
    opens pl.coderslab.controller to javafx.fxml;
    exports pl.coderslab.javafx;
    exports pl.coderslab.controller;
}