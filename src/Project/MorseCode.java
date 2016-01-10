package Project;

import javafx.scene.control.TextField;

import static Project.GlobalMethods.*;
import static Project.GlobalVariables.*;
import static Project.MorseTranslator.*;
import static Project.InfoWindow.*;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class MorseCode {
    public static void ShowMorseCode() {
        CreateWindow.NewWindow("Morse Code");

        String InputText = "Insert the text or Morse code you want to translate here";
        TextField Input = CreateWindow.AddTextFieldWithPromptText(InputText);
        TextField Answer = CreateWindow.AddTextFieldWithNormalText(AnswerText);

        Buttons Translate = new Buttons("Translate!");
        Buttons Clear = new Buttons("Clear fields");
        Buttons Swap = new Buttons("Answer to Input");
        Buttons Info = new Buttons("Info");

        CreateWindow.AddButtonsToWindow(false,Translate,Clear);
        CreateWindow.AddButtonsToWindow(false,Swap,Info);
        CreateWindow.AddButtonsToWindow(false,ReturnButton,null);

        FocusOnField(Clear);

        Swap.setOnAction(event1 -> {
            SwapWords(Answer, Input);
        });

        Clear.setOnAction(event1 -> {
            ClearFields(null, null, null, null, Input, InputText, Answer, AnswerText);
        });

        Translate.setOnAction(event2 -> {
            Translate(Input, Answer);
        });

        Info.setOnAction(event1 -> {
            String mHeaderText = "Morse Code";
            String mButtonTitle = "International Morse alphabet";
            Buttons mAlphabet = new Buttons(mButtonTitle);
            ShowInfo(mHeaderText, ReadTextFromFile("MorseInfo.txt"), false, mAlphabet);

            mAlphabet.setOnAction(event2 -> {
                ShowInfoPicture(mButtonTitle, "LatinMorse.jpg");
            });
        });
    }
}
