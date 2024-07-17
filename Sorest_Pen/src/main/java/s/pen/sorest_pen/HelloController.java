package s.pen.sorest_pen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class HelloController implements Initializable {
    @FXML
    private AnchorPane myPane;
    @FXML
    private ImageView myPen;
    @FXML
    private ImageView thePen;
    @FXML
    private ImageView clearBtn;
    @FXML
    private ColorPicker renkSec;
    @FXML
    private Slider sizeSlider;
    @FXML
    private ImageView view;
    private boolean isPaneVisible = false;
    private double initialMouseX;
    private double initialMouseY;
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean showing = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        canvas.setWidth(Screen.getPrimary().getBounds().getWidth());
        canvas.setHeight(Screen.getPrimary().getBounds().getHeight());

        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(5);
        canvas.setOnMousePressed(event -> {
            gc.setStroke(renkSec.getValue());
            initialMouseX = event.getX();
            initialMouseY = event.getY();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            gc.beginPath();
            gc.moveTo(event.getX(), event.getY());
            gc.stroke();
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (event) -> {
            gc.lineTo(event.getX(), event.getY());
            gc.stroke();
        });


        myPen.setOnMousePressed(event -> {
            initialMouseX = event.getX();
            initialMouseY = event.getY();
        });


        myPen.setOnMouseDragged(event -> {
            double position = event.getScreenY() - initialMouseY;
            if (position < 10) {
                position += 50;
            }
            myPen.setLayoutX(event.getScreenX() - initialMouseX);
            myPen.setLayoutY(position + 16);
            myPane.setLayoutX(event.getScreenX() - initialMouseX + 20);
            myPane.setLayoutY(position + 86);
        });


        myPen.setOnMouseClicked(event -> {
            if (Math.abs(event.getScreenX() - initialMouseX) < 5 &&
                    Math.abs(event.getScreenY() - initialMouseY) < 5) {
                myPenClicked();
            }
        });

        thePen.setOnMouseDragged(event -> {
            initialMouseX = event.getScreenX();
            initialMouseY = event.getScreenY();
        });

        clearBtn.setOnMouseClicked(event -> {
            clear(gc, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        });

        sizeSlider.setOnMouseReleased(event -> {
            gc.setLineWidth(sizeSlider.getValue());
        });

        view.setOnMouseClicked(event -> {
            if (showing) {
                canvas.setDisable(true);
                canvas.setOpacity(0);
                showing = false;
            } else {
                canvas.setDisable(false);
                canvas.setOpacity(1);
                showing = true;
            }
        });
    }

    public void myPenClicked() {
        isPaneVisible = !isPaneVisible;
        myPane.setVisible(isPaneVisible);
    }

    private void drawRectangle(GraphicsContext gc, double x, double y, double size) {

    }

    private void drawLine(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.setFill(Color.WHITE);
        gc.setLineWidth(4);
        gc.strokeLine(startX, startY, endX, endY);
    }

    private void clear(GraphicsContext gc, double width, double height) {
        gc.clearRect(0, 0, width, height);
    }
}

