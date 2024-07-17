module s.pen.sorest_pen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens s.pen.sorest_pen to javafx.fxml;
    exports s.pen.sorest_pen;
}