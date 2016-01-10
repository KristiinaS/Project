package Project;

import javafx.scene.control.TextField;

import static Project.CaesarTranslator.Translate;
import static Project.GlobalMethods.*;
import static Project.GlobalVariables.*;
import static Project.InfoWindow.*;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class CaesarCipher {
    public static void ShowCaesarCipher() {
        CreateWindow.NewWindow("Caesar Cipher");

        String StepText = "Insert the shift number (use minus (-) for left shift)";
        String InputText = "Insert the text you want to encipher here";

        TextField Alphabet = CreateWindow.AddTextFieldWithPromptText(ABCinputText);
        TextField Step = CreateWindow.AddTextFieldWithPromptText(StepText);
        TextField Input = CreateWindow.AddTextFieldWithPromptText(InputText);
        TextField Answer = CreateWindow.AddTextFieldWithNormalText(AnswerText);

        Buttons Clear = new Buttons("Clear fields");
        Buttons Insert = new Buttons("Try the cipher!");
        Buttons Info = new Buttons("Info");
        Buttons Swap = new Buttons("Answer to input");

        FocusOnField(Clear);
        ChangeABC(Alphabet);

        CreateWindow.AddButtonsToWindow(true,Clear,null);
        CreateWindow.AddButtonsToWindow(false,Insert,Info);
        CreateWindow.AddButtonsToWindow(false,Swap,ReturnButton);

        Swap.setOnAction(event2 -> {
            SwapWords(Answer, Input);
        });

        Clear.setOnAction(event1 -> {
            ClearFields(ABCchoiceBox, Alphabet, Step, StepText, Input, InputText, Answer, AnswerText);

        });

        Info.setOnAction(event2 -> {
            String cHeaderText = "Caesar Cipher";
            ShowInfo(cHeaderText, ReadTextFromFile("CaesarInfo.txt"), true, null);
        });

        Insert.setOnAction(event1 -> {
            Translate(Alphabet, Input, Step, Answer);
        });
    }
}
