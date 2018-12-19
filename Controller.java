package sample.Controllers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.util.ResourceBundle;
import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;

public class Controller extends Main {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button zero;

    @FXML
    private Button dot;

    @FXML
    private Button equals;

    @FXML
    private Button one;

    @FXML
    private Button second;

    @FXML
    private Button three;

    @FXML
    private Button four;

    @FXML
    private Button six;

    @FXML
    private Button five;

    @FXML
    private Button nine;

    @FXML
    private Button eight;

    @FXML
    private Button seven;

    @FXML
    private Button multiplication;

    @FXML
    private Button substraction;

    @FXML
    private Button addition;

    @FXML
    private Button division;

    @FXML
    private Button squareRoot;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private Button percent;

    @FXML
    private TextField display;

    @FXML
    void handleOnAnyButtonClicked(ActionEvent event) {
        Button button = (Button)event.getSource();
        String buttonText = button.getText();
        if (buttonText.equals("C/CE")) {
            left = BigDecimal.ZERO;
            selectedOperator = "";
            numberInputting = false;
            display.setText("0");
            return;
        }
        if (buttonText.matches("[0-9\\.]")) {
            if (!numberInputting) {
                numberInputting = true;
                dotPressed = false;
                display.clear();
            }
            if (buttonText.equals(".")) {
                if(!dotPressed) {
                    dotPressed = true;
                }
                else {
                    return;
                }
            }
            display.appendText(buttonText);
        }
        if (buttonText.matches("[-+*/%]")) {
            left = new BigDecimal(display.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            new JackInTheBox(button).play();
            return;
        }
        if (buttonText.equals("=")) {
            final BigDecimal right = numberInputting ? new BigDecimal(display.getText()) : left;
            left = calculate(selectedOperator, left, right);
            display.setText(left.toString());
            numberInputting = false;
            new Flash(display).play();
            new Pulse(button).play();
            return;
        }
        if (buttonText.equals("⬅")) {
            String left = display.getText();
            if (!(display.getText().length() == 1 && display.getText().matches("[0-9\\-]"))) {
                display.setText(left.substring(0, left.length() - 1));
                return;
            }
            display.setText("0");
            numberInputting = false;
        }
        if (buttonText.equals("√")) {
            left = new BigDecimal(display.getText());
            if (left.compareTo(BigDecimal.ZERO) >= 0) {
                display.setText(left.sqrt(new MathContext(6)).toString());
                numberInputting = false;
            }
            else {
                display.setText("Error");
            }
            new Flash(display).play();
            return;
        }
        if (buttonText.equals("-/+")) {
            left = new BigDecimal(display.getText());
            if (left.compareTo(BigDecimal.ZERO) >= 0) {
                display.setText(left.subtract(left.multiply(new BigDecimal(2))).toString());
            }
            else {
                display.setText(left.abs().toString());
            }
        }
        new JackInTheBox(button).play();
    }

    static BigDecimal calculate(String operator, BigDecimal left, BigDecimal right) {
        switch(operator) {
            case "+":
                return left.add(right);
            case "-":
                return left.subtract(right);
            case "*":
                return left.multiply(right);
            case "/":
                return left.divide(right, new MathContext(6));
            default:
        }
        return right;
    }
}
