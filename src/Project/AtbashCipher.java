package Project;

import javafx.scene.control.TextField;

import static Project.AtbashTranslator.Translate;
import static Project.GlobalMethods.*;
import static Project.GlobalVariables.*;
import static Project.InfoWindow.*;


/**
 * Created by Kristiina on 10.01.2016.
 */
public class AtbashCipher {
    public static void ShowAtbashCipher() {
        CreateWindow.NewWindow("Atbash Cipher");

        String InputText = "Insert the text you want to encipher here";

        TextField Alphabet = CreateWindow.AddTextFieldWithPromptText(ABCinputText);
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

        // button that resets the fields to their original state
        Clear.setOnAction(event1 -> {
            ClearFields(ABCchoiceBox, Alphabet, null, null, Input, InputText, Answer, AnswerText);
        });

        // button with information about Atbash
        Info.setOnAction(event3 ->{
            String aHeaderText = "Atbash Cipher";
            ShowInfo(aHeaderText, ReadTextFromFile("AtbashInfo.txt"), true, null);
        });

        // actions after the alphabet & word have been inserted
        Insert.setOnAction(event1 -> {
            Translate(Alphabet, Input, Answer);
        });
    }
}
