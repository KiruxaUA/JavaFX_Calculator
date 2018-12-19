package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.math.BigDecimal;

public class Main extends Application {
    protected BigDecimal left;
    protected String selectedOperator;
    protected boolean numberInputting;
    protected boolean dotPressed;

    public Main() {
        this.left = BigDecimal.ZERO;
        this.selectedOperator = "";
        this.numberInputting = false;
        this.dotPressed = false;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Views/Simple_Calculator.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
