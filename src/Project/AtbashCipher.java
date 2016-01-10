package Project;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import static Project.GlobalVariables.ABCinputText;
import static Project.GlobalVariables.ReturnButton;


/**
 * Created by Kristiina on 10.01.2016.
 */
public class AtbashCipher {
    static VBox AtbashVbox = new VBox();

    public static void AtbashCipher() {
        CreateWindow.NewWindow("Atbash Cipher");
        CreateWindow.AddTextFieldWithPromptText(ABCinputText);
        CreateWindow.AddTextFieldWithPromptText("Insert the text you want to encipher here");
        CreateWindow.AddTextFieldWithNormalText("Your answer will be displayed here");

        /*
        Button aInsert = new Button("Try the cipher!");
        Button aClear = new Button("Clear fields");
        Button aSwap = new Button("Answer to input");
        Button aInfo = new Button("Info");
         */
        Buttons Clear = null;
        Buttons Insert = null;
        Buttons Info = null;
        Buttons Swap = null;
        CreateWindow.MakeButtons(Clear,"Clear fields");
        CreateWindow.MakeButtons(Insert,"Try the cipher!");
        CreateWindow.MakeButtons(Info,"Info");
        CreateWindow.MakeButtons(Swap,"Answer to input");

        CreateWindow.AddButtonsToWindow(true,Clear,null);
        CreateWindow.AddButtonsToWindow(false,Insert,Info);
        CreateWindow.AddButtonsToWindow(false,Swap, ReturnButton);
    }
/*
    VBox Atbashvbox = new VBox();
        Atbashvbox.setPadding(new Insets(vBoxPadding));
        Scene AtbashScene = new Scene(Atbashvbox, width, height);
        mainStage.setScene(AtbashScene);
        mainStage.setTitle("Atbash Cipher");
        mainStage.show();

        Label aWelcomeText = new Label("Atbash Cipher");
        WelcomeTextStyle(aWelcomeText);

        String aWordInputText = "Insert the text you want to encipher here";
        String aNewWordText = "Your answer will be displayed here";
        TextField aABCinput = new TextField();
        TextField aWordInput = new TextField();
        SetPromptText(aABCinput,ABCinputText,aWordInput,aWordInputText,null,null);
        TextField aNewWord = new TextField(aNewWordText);
        TextNotEditable(aNewWord);//cannot change text in TextField

        //Defining the drop down menu
        ChoiceBox<String> aLanguageABC = new ChoiceBox<String>(FXCollections.observableArrayList(ABClanguages));
        DefineABC(aLanguageABC);
        ChangeABC(aLanguageABC,aABCinput);//replace alphabet if different ABC is chosen from drop down menu

        Button aInsert = new Button("Try the cipher!");
        Button aClear = new Button("Clear fields");
        Button aSwap = new Button("Answer to input");
        Button aInfo = new Button("Info");

        FocusOnField(aClear);
        ButtonsSameSize(aLanguageABC,aClear,aInsert,null,aInfo,aSwap);

        PaneStyle(Atbashvbox);
        Atbashvbox.getChildren().addAll(aWelcomeText,aABCinput,aWordInput,aNewWord,
                ButtonsToVbox(aLanguageABC,aClear,aInsert,null,aInfo,aSwap));

        aSwap.setOnAction(event2 -> {
            SwapWords(aNewWord, aWordInput);
        });


        // button that resets the fields to their original state
        aClear.setOnAction(event1 -> {
            ClearFields(aLanguageABC, aABCinput, null, null, aWordInput, aWordInputText, aNewWord, aNewWordText);
        });

        // button with information about Atbash
        aInfo.setOnAction(event3 ->{
            String aHeaderText = "Atbash Cipher";
            DisplayInfo(aHeaderText, ReadTextFromFile("AtbashInfo.txt"),true,null);
        });

        // actions after the alphabet & word have been inserted
        aInsert.setOnAction(event1 -> {
            AtbashTranslator(aABCinput,aWordInput,aNewWord);
        });
    */
}
