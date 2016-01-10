package Project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import static Project.GlobalVariables.*;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class GlobalMethods {

    public static void ReturnToMainView() {
        mainStage.setScene(mainScene);
        mainStage.setTitle("Ciphers");
        mainStage.show();
    }

    public static void CloseWindow() {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    public static ChoiceBox<String> DefineABC() {
        ABCchoiceBox.setTooltip(Choose); //dispay text if mouse is hovering over the button
        ABCchoiceBox.getSelectionModel().selectFirst(); //dispays the first selectin by default
        return ABCchoiceBox;
    }

    public static void SetPromptText(TextField TextField, String Text){
        TextField.clear();
        TextField.setPromptText(Text);
    }

    public static void WarningLableStyle(Label Warning) {
        Warning.setFont(Font.font(null, FontPosture.ITALIC, 14));
        Warning.setTextFill(Color.RED);
    }

    public static void TextNotEditableStyle(TextField NewWord) {
        NewWord.setStyle("-fx-text-inner-color: black; -fx-background-color:" + TextBackgroundColor);
    }
}
