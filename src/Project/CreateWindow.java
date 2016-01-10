package Project;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static Project.GlobalMethods.*;
import static Project.GlobalVariables.*;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class CreateWindow {
    private static VBox NewVbox;
    private static VBox SubVbox;

    public static void NewWindow(String Title) {
        NewVbox = new VBox();
        NewVbox.setPadding(new Insets(vBoxPadding));
        Scene VigenereScene = new Scene(NewVbox, width, height);
        mainStage.setScene(VigenereScene);
        mainStage.setTitle(Title);
        mainStage.show();

        PaneStyle(NewVbox);
        Label WelcomeText = new Label(Title);
        WelcomeTextStyle(WelcomeText);

        NewVbox.getChildren().addAll(WelcomeText);
    }

    public static void AddTextFieldWithPromptText(String TextFieldText) {
        TextField TextField = new TextField();
        SetPromptText(TextField, TextFieldText);
        NewVbox.getChildren().add(TextField);
    }

    public static void AddTextFieldWithNormalText(String TextFieldText) {
        TextField TextField = new TextField(TextFieldText);
        TextNotEditable(TextField);
        NewVbox.getChildren().add(TextField);
    }

    public static Buttons MakeButtons(Buttons Button,String ButtonTitle) {
        Button = new Buttons(ButtonTitle);
        return Button;
    }

    public static void AddButtonsToWindow (boolean ChoiceBox, Buttons Button1, Buttons Button2) {
        HBox NewHbox = new HBox(vBoxPadding);
        NewHbox.setAlignment(Pos.CENTER);
        NewHbox.setTranslateY(vBoxPadding * 5);

        if (ChoiceBox) {
            NewHbox.getChildren().addAll(DefineABC(), Button1);
        } else if(Button2 == null){
            NewHbox.getChildren().addAll(Button1);
        } else {
            NewHbox.getChildren().addAll(Button1, Button2);
        }

        NewVbox.getChildren().add(NewHbox);
    }

    public static void TextNotEditable(TextField NewWord) {
        NewWord.setEditable(false);
        NewWord.setFocusTraversable(false);
        NewWord.setStyle("-fx-text-inner-color: black; -fx-background-color:" + TextBackgroundColor);
    }


    public static void FocusOnField(Buttons Button) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Button.requestFocus();
                }
            });
    }

    public static void WelcomeTextStyle(Label WelcomeText) {
        WelcomeText.setFont(Font.font(null, FontWeight.BOLD, 20));
    }

    public static void PaneStyle(VBox Pane){
        Pane.setBackground(Background);
        Pane.setAlignment(Pos.CENTER); // aligning main view text to center of the window
    }
}
