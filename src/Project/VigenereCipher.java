package Project;

import javafx.scene.control.TextField;

import static Project.GlobalMethods.*;
import static Project.GlobalVariables.*;
import static Project.InfoWindow.*;
import static Project.VigenereTranslator.*;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class VigenereCipher {
    public static void ShowVigenereCipher() {
        CreateWindow.NewWindow("Vigenére Cipher");

        String KeywordText = "Enter the keyword";
        String InputText = "Insert the text you want to encipher here";

        TextField Alphabet = CreateWindow.AddTextFieldWithPromptText(ABCinputText);
        TextField Keyword = CreateWindow.AddTextFieldWithPromptText(KeywordText);
        TextField Input = CreateWindow.AddTextFieldWithPromptText(InputText);
        TextField Answer = CreateWindow.AddTextFieldWithNormalText(AnswerText);

        Buttons Clear = new Buttons("Clear fields");
        Buttons Encode = new Buttons("Encode");
        Buttons Decode = new Buttons("Decode");
        Buttons Swap = new Buttons("Answer to input");
        Buttons Info = new Buttons("Info");

        CreateWindow.AddButtonsToWindow(true,Clear,null);
        CreateWindow.AddButtonsToWindow(false,Encode,Decode);
        CreateWindow.AddButtonsToWindow(false,Swap,Info);
        CreateWindow.AddButtonsToWindow(false,ReturnButton,null);

        FocusOnField(Clear);
        ChangeABC(Alphabet);

        Swap.setOnAction(event2 -> {
            SwapWords(Answer, Input);
        });

        Encode.setOnAction(event3 -> {
            Translate(true, Alphabet, Keyword, Input, Answer);
        });

        Decode.setOnAction(event3 -> {
            Translate(false, Alphabet, Keyword, Input, Answer);
        });

        Clear.setOnAction(event1 -> {
            ClearFields(ABCchoiceBox, Alphabet, Keyword, KeywordText, Input, InputText, Answer, AnswerText);
        });

        Info.setOnAction(event2 -> {
            String vHeaderText = "Vigenère Cipher";
            String vButtonTitle = "Vigenère table";
            Buttons vTable = new Buttons(vButtonTitle);
            ShowInfo(vHeaderText, ReadTextFromFile("VigenereInfo.txt"), false, vTable);

            vTable.setOnAction(event1 -> {
                ShowInfoPicture(vButtonTitle, "tabularecta.jpg");
            });
        });
    }
}
