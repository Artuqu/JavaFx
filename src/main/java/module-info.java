module pl.zone.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires spring.web;


    opens pl.zone.javafx to javafx.fxml;
    opens pl.zone.controller to javafx.fxml;
    exports pl.zone.javafx;
    exports pl.zone.controller;
    exports pl.zone;
    exports pl.zone.table;
    exports pl.zone.dto;
    opens pl.zone to javafx.fxml;
}
