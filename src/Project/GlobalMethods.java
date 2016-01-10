package Project;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    public static ChoiceBox<String> DefineABC() {
        ABCchoiceBox.setTooltip(Choose); //dispay text if mouse is hovering over the button
        ABCchoiceBox.getSelectionModel().selectFirst(); //dispays the first selectin by default
        return ABCchoiceBox;
    }

    public static void ChangeABC(TextField ABCinput) {
        //replace alphabet if language is chosen from drop down menu
        ABCchoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (Alphabets[newValue.intValue()].equals(ABCinputText)){
                    SetPromptText(ABCinput,ABCinputText);
                } else {
                    ABCinput.setText(Alphabets[newValue.intValue()]);
                }
            }
        });
    }

    public static void SetPromptText(TextField TextField, String Text){
        TextField.clear();
        TextField.setPromptText(Text);
    }

    public static void TextNotEditableStyle(TextField NewWord) {
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

    public static void SwapWords(TextField NewWord, TextField WordInput) {
        String CipherWord = NewWord.getText();
        WordInput.setText(CipherWord);
    }

    public static void ClearFields(ChoiceBox<String> ABCchoiceBox,TextField ABCinput,TextField StepInput,
                                   String StepInputText,TextField WordInput,String WordInputText,
                                   TextField NewWord,String NewWordText) {
        TextNotEditableStyle(NewWord);

        if (StepInput == null) {
            if (ABCinput == null){ //if step and alphabet don't exist
                SetPromptText(WordInput,WordInputText);
                NewWord.setText(NewWordText);
            } else { //if step doesn't exist
                SetPromptText(ABCinput,ABCinputText);
                SetPromptText(WordInput,WordInputText);
                NewWord.setText(NewWordText);
                ABCchoiceBox.getSelectionModel().selectFirst();
            }
        } else { //if everything exists
            SetPromptText(ABCinput,ABCinputText);
            SetPromptText(WordInput,WordInputText);
            SetPromptText(StepInput,StepInputText);
            NewWord.setText(NewWordText);
            ABCchoiceBox.getSelectionModel().selectFirst();
        }
    }

    public static String ReadTextFromFile(String FileName) {
        String InfoText = "";
        try {
            InfoText = new String(Files.readAllBytes(Paths.get(FileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return InfoText;
    }

    public static ArrayList<String> CheckUppercase(int LettersInWord, ArrayList<String> OutputList,
                                                   String UserInput) {
        for (int i = 0; i < LettersInWord; i++) {
            char Char = OutputList.get(i).charAt(0);
            if (Character.isUpperCase(UserInput.charAt(i))){
                OutputList.remove(i);
                char NewChar = Character.toUpperCase(Char);
                String aNewString = Character.toString(NewChar);
                OutputList.add(i,aNewString);
            }
        }
        return OutputList;
    }
    public static void WarningTextToTextfield(TextField NewWord, String ErrorText) {
        NewWord.setStyle("-fx-text-inner-color: red; -fx-background-color:" + TextBackgroundColor + "; " +
                "-fx-font-style: italic");
        NewWord.setText(ErrorText);
    }
}
