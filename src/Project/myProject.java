package Project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Kristiina on 30.11.2015.
 */

public class myProject extends Application {
    Button AtbashButton, CaesarButton, VigenereButton, MorseButton, ReturnButton;
    Scene mainScene, AtbashScene, CaesarScene, VigenereScene, MorseScene;
    VBox mainvbox, Atbashvbox, Caesarvbox, Vigenerevbox, Morsevbox;
    Stage mainStage;
    Tooltip Choose = new Tooltip("Choose alphabet");

    String BackgroundPictureURL = "http://www.webdesignhot.com/wp-content/uploads/2012/09/" +
            "Abstract-Green-Bokeh-Light-Background-Vector-Graphic.jpg";
    Image BackgroundPicture = new Image("url:" + BackgroundPictureURL);
    int width = (int) BackgroundPicture.getWidth();
    int height = (int) BackgroundPicture.getHeight();
    int buttonWidth = width/3;
    int vBoxPadding = 5;
    String TextBackgroundColor = "#F2F2F2";

    String ABCinputText = "Insert the alphabet here or choose it from the list below";
    String[] Alphabets = {ABCinputText,
            "A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z",
            "А Б В Г Д Е Ё Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Æ Ø Å",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Å Ä Ö"};

    List<String> ABClanguages = new ArrayList<String>(Arrays.asList("User defined alphabet","Estonian",
            "English", "Russian","Danish/Norwegian","Swedish"));

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        mainvbox = new VBox();
        mainScene = new Scene(mainvbox, width, height);
        mainStage.setResizable(false);
        mainStage.sizeToScene();
        mainStage.setTitle("Ciphers and Codes"); // naming the window
        mainStage.setScene(mainScene);
        mainStage.getIcons().add(new Image("file:FolderIcon.png")); // different icon for the window
        mainStage.show();

        Label welcomeText = new Label("Choose a cipher or code from the list below.");
        WelcomeTextStyle(welcomeText);

        // defining buttons on main page
        AtbashButton = new Button("Atbash Cipher");
        CaesarButton = new Button("Caesar Shift Cipher");
        VigenereButton = new Button("Vigenère Cipher");
        MorseButton = new Button("Morse Code");
        ReturnButton = new Button("Return to menu");

        ButtonsSameSize(null,AtbashButton,CaesarButton,null,VigenereButton,MorseButton);// making the buttons same size

        PaneStyle(mainvbox); //background & alignment
        mainvbox.getChildren().addAll(welcomeText,AtbashButton,CaesarButton,VigenereButton,MorseButton);

        ReturnButton.setOnAction(event1 -> { // Button which takes back to the main view
            ReturnToMainView();
        });

        AtbashButton.setOnAction(event -> { // Atbach Cipher content
            AtbashWindow();
        });

        CaesarButton.setOnAction(event -> { //Caesar Cipher content
            CaesarWindow();
        });

        VigenereButton.setOnAction(event -> { //Vigenére Cipher content
            VigenereWindow();
        });

        MorseButton.setOnAction(event -> { //Morse Code content
            MorseWindow();
        });
    }

    private void VigenereWindow() {
        Vigenerevbox = new VBox();
        Vigenerevbox.setPadding(new Insets(vBoxPadding));
        VigenereScene = new Scene(Vigenerevbox, width, height);
        mainStage.setScene(VigenereScene);
        mainStage.setTitle("Vigenère Cipher");
        mainStage.show();

        Label vWelcomeText = new Label("Vigenère Cipher");
        WelcomeTextStyle(vWelcomeText);

        Label vWarning = new Label("At the moment only one word can be enciphered at a time and all " +
                "letters are converted to lower case!");
        WarningTextStyle(vWarning);

        String vKeyInputText = "Enter the keyword";
        String vWordInputText = "Insert the word you want to encipher here";
        String vNewWordText = "Your answer will be displayed here";
        TextField vABCinput = new TextField();
        TextField vKeyInput = new TextField();
        TextField vWordInput = new TextField();
        SetPromptText(vABCinput,ABCinputText,vKeyInput,vKeyInputText,vWordInput,vWordInputText);
        TextField vNewWord = new TextField(vNewWordText);
        WordNotEditable(vNewWord); //cannot change text in TextField

        Button vEncode = new Button("Encode");
        Button vDecode = new Button("Decode");
        Button vClear = new Button("Clear fields");
        Button vSwap = new Button("Answer to input");
        Button vInfo = new Button("Info");

        //Defining the drop down menu
        ChoiceBox<String> vLanguageABC = new ChoiceBox<String>(FXCollections.observableArrayList(ABClanguages));
        DefineABC(vLanguageABC);
        ChangeABC(vLanguageABC,vABCinput);//replace alphabet if language is chosen from drop down menu

        FocusOnField(vClear);
        ButtonsSameSize(vLanguageABC,vClear,vEncode,vDecode,vInfo,vSwap);

        PaneStyle(Vigenerevbox);
        Vigenerevbox.getChildren().addAll(vWelcomeText,vABCinput,vKeyInput,vWordInput,vNewWord,
                ButtonsToVbox(vLanguageABC,vClear,vEncode,vDecode,vInfo,vSwap));

        vSwap.setOnAction(event2 -> {
            SwapWords(vNewWord, vWordInput);
        });

        vEncode.setOnAction(event3 -> {
            VigenereTranslator(true, vABCinput, vKeyInput, vWordInput, vNewWord);
        });

        vDecode.setOnAction(event3 -> {
            VigenereTranslator(false,vABCinput,vKeyInput,vWordInput,vNewWord);
        });

        vClear.setOnAction(event1 -> {
            ClearFields(vLanguageABC, vABCinput, vKeyInput, vKeyInputText, vWordInput, vWordInputText,
                    vNewWord, vNewWordText);
        });

        vInfo.setOnAction(event2 -> {
            String vHeaderText = "Vigenère Cipher";
            String vButtonTitle = "Vigenère table";
            Button vTable = new Button(vButtonTitle);
            DisplayInfo(vHeaderText, ReadTextFromFile("VigenereInfo.txt"), true, vTable);

            vTable.setOnAction(event1 -> {
                ShowInfoPicture(vButtonTitle, "tabularecta.jpg");
            });
        });
    }

    private void MorseWindow() {
        Morsevbox = new VBox();
        MorseScene = new Scene(Morsevbox, width, height);
        mainStage.setScene(MorseScene);
        mainStage.setTitle("Morse Code");
        mainStage.show();

        Label mWelcomeText = new Label("Morse Code");
        WelcomeTextStyle(mWelcomeText);

        Label mWarning = new Label("At the moment translating from Morse to Latin alphabet doesn't work!");
        WarningTextStyle(mWarning);

        String mWordInputText = "Insert the word you want to translate into Morse code here";
        String mNewWordText = "Your answer will be displayed here";
        TextField mWordInput = new TextField();
        SetPromptText(mWordInput,mWordInputText,null,null,null,null);
        TextField mNewWord = new TextField(mNewWordText);
        WordNotEditable(mNewWord);//cannot change text in TextField

        Button mTranslate = new Button("Translate!");
        Button mClear = new Button("Clear fields");
        Button mInfo = new Button("Info");
        Button mSwap = new Button("Answer to input");

        FocusOnField(mClear);
        ButtonsSameSize(null,mTranslate,mClear, null,mSwap,mInfo);

        PaneStyle(Morsevbox);
        Morsevbox.getChildren().addAll(mWelcomeText,mWordInput,mNewWord,
                ButtonsToVbox(null,mTranslate,mClear,null,mSwap,mInfo));


        mSwap.setOnAction(event1 -> {
            SwapWords(mNewWord, mWordInput);
        });

        mClear.setOnAction(event1 -> {
            ClearFields(null, null, null, null, mWordInput, mWordInputText, mNewWord, mNewWordText);
        });

        mTranslate.setOnAction(event2 -> {
            MorseTranslator(mWordInput,mNewWord);
        });

        mInfo.setOnAction(event1 -> {
            String mHeaderText = "Morse Code";
            String mButtonTitle = "International Morse alphabet";
            Button mAlphabet = new Button(mButtonTitle);
            DisplayInfo(mHeaderText, ReadTextFromFile("MorseInfo.txt"), true, mAlphabet);

            mAlphabet.setOnAction(event2 -> {
                ShowInfoPicture(mButtonTitle, "LatinMorse.jpg");
            });
        });
    }

    private void CaesarWindow() {
        Caesarvbox = new VBox();
        Caesarvbox.setPadding(new Insets(vBoxPadding));
        CaesarScene = new Scene(Caesarvbox, width, height);
        mainStage.setScene(CaesarScene);
        mainStage.setTitle("Caesar Cipher");
        mainStage.show();

        Label cWelcomeText = new Label("Caesar Cipher");
        WelcomeTextStyle(cWelcomeText);

        Label cWarning = new Label("At the moment all letters are converted to lower case!");
        WarningTextStyle(cWarning);

        String cStepInputText = "Insert the shift number (use minus (-) for left shift)";
        String cWordInputText = "Insert the word you want to encipher here";
        String cNewWordText = "Your answer will be displayed here";
        TextField cABCinput = new TextField();
        TextField cStepInput = new TextField();
        TextField cWordInput = new TextField();
        SetPromptText(cABCinput,ABCinputText,cStepInput,cStepInputText,cWordInput,cWordInputText);
        TextField cNewWord = new TextField(cNewWordText);
        WordNotEditable(cNewWord);//cannot change text in TextField

        Button cInsert = new Button("Try the cipher!");
        Button cClear = new Button("Clear fields");
        Button cSwap = new Button("Answer to input");
        Button cInfo = new Button("Info");

        //Defining the drop down menu
        ChoiceBox<String> cLanguageABC = new ChoiceBox<String>(FXCollections.observableArrayList(ABClanguages));
        DefineABC(cLanguageABC);
        ChangeABC(cLanguageABC, cABCinput);//replace alphabet if language is chosen from drop down menu

        FocusOnField(cClear);
        ButtonsSameSize(cLanguageABC,cClear,cInsert,null,cInfo,cSwap);

        PaneStyle(Caesarvbox);
        Caesarvbox.getChildren().addAll(cWelcomeText,cABCinput,cStepInput,cWordInput,cNewWord,
                ButtonsToVbox(cLanguageABC,cClear,cInsert,null,cInfo,cSwap));

        cSwap.setOnAction(event2 -> {
            SwapWords(cNewWord, cWordInput);
        });

        cClear.setOnAction(event1 -> {
            ClearFields(cLanguageABC, cABCinput, cStepInput, cStepInputText, cWordInput, cWordInputText, cNewWord, cNewWordText);

        });

        cInfo.setOnAction(event2 -> {
            String cHeaderText = "Caesar Cipher";
            DisplayInfo(cHeaderText, ReadTextFromFile("CaesarInfo.txt"), false, null);
        });

        cInsert.setOnAction(event1 -> {
            CaesarTranslator(cABCinput,cWordInput,cStepInput,cNewWord);
        });
    }

    private void AtbashWindow() {
        Atbashvbox = new VBox();
        Atbashvbox.setPadding(new Insets(vBoxPadding));
        AtbashScene = new Scene(Atbashvbox, width, height);
        mainStage.setScene(AtbashScene);
        mainStage.setTitle("Atbash Cipher");
        mainStage.show();

        Label aWelcomeText = new Label("Atbash Cipher");
        WelcomeTextStyle(aWelcomeText);

        Label aWarning = new Label("At the moment all letters are converted to lower case!");
        WarningTextStyle(aWarning);

        String aWordInputText = "Insert the word you want to encipher here";
        String aNewWordText = "Your answer will be displayed here";
        TextField aABCinput = new TextField();
        TextField aWordInput = new TextField();
        SetPromptText(aABCinput,ABCinputText,aWordInput,aWordInputText,null,null);
        TextField aNewWord = new TextField(aNewWordText);
        WordNotEditable(aNewWord);//cannot change text in TextField

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
            DisplayInfo(aHeaderText, ReadTextFromFile("AtbashInfo.txt"), false, null);
        });

        // actions after the alphabet & word have been inserted
        aInsert.setOnAction(event1 -> {
            AtbashTranslator(aABCinput,aWordInput,aNewWord);
        });
    }

    private String ReadTextFromFile(String FileName) {
        String InfoText = "";
        try {
            InfoText = new String(Files.readAllBytes(Paths.get(FileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return InfoText;
    }

    private void FocusOnField(Button Button) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Button.requestFocus();
            }
        });
    }

    private TextField MorseTranslator(TextField WordInput, TextField NewWord) {
        String mWord = WordInput.getText(); //save input from TextField
        List<String> mOutputList = new ArrayList<String>(); // Empty list for the new word
        File mMorseInternational = new File("MorseInternational.txt"); //ERROR!! "A" doesn't work with UTF-8.
        HashMap <String,String> mList = new HashMap<String, String>(); //HashMap for Morse alphabet

        try {
            BufferedReader mBR = new BufferedReader(new FileReader(mMorseInternational)); //read file
            String mLine = mBR.readLine(); //read line from file

            if (mWord.equals("")){
                NewWord.setText("");
            } else if (mWord.matches("^[/. -]+$")){ //Translate Morse to Latin
                String[] mWordList = mWord.split("\\s+"); //Words from sentence to list
                int mLettersInWord = mWordList.length; //elements in list

                FileToHashMap(true,mLine,mList,mBR);

                for (int i = 0; i < mLettersInWord; i++) {
                    String mLetter = mWordList[i];
                    if (mWordList[i].equals("/")) {
                        mOutputList.add(i," "); //Replace "/" with a whitespace
                    } else if (!mList.containsKey(mLetter)){
                        mOutputList.add(i,"#");
                    } else {
                        String mValue = mList.get(mLetter); //find value of key
                        mOutputList.add(i,mValue); //letter of morse code to output list
                    }
                }
            } else { // Translate Latin to Morse
                mWord = mWord.toUpperCase(); //convert input word to upper case
                List<String> mWordList = new ArrayList<String>(Arrays.asList(mWord.split(""))); // Word string to list
                int mLettersInWord = mWordList.size(); // get length of the word

                FileToHashMap(false,mLine,mList,mBR);

                for (int i = 0; i < mLettersInWord; i++) {
                    String mLetter = mWordList.get(i); // find the letter from word
                    if (!mList.containsKey(mLetter)) { //adding characters that are not in Morse code
                        if (Character.isWhitespace(mLetter.charAt(0))){
                            mOutputList.add(i, "/ ");
                        } else {
                            mOutputList.add(i, "# ");
                        }
                    } else {
                        String mValue = mList.get(mLetter);
                        mOutputList.add(i, mValue + " "); //morse code of letter to output list
                    }
                }
            }

            String mOutput = String.join("",mOutputList);
            NewWord.setText(mOutput);

            mBR.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NewWord;
    }

    private TextField VigenereTranslator(boolean Encoder,TextField ABCinput, TextField KeyInput,
                                         TextField WordInput, TextField NewWord) {
        String vABC = ABCinput.getText(); //ABC input to string
        vABC = vABC.trim().replaceAll("\\s","").replaceAll(",","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
        List<String> vABClist = new ArrayList<String>(Arrays.asList(vABC.split(""))); //ABC string to list
        int vLettersInABC = vABClist.size(); //Length of ABC

        String vKey = KeyInput.getText(); //Keyword input to string
        vKey = vKey.toLowerCase(); //Keyword to lower case
        List<Character> vKeyList = new ArrayList<Character>();
        for (char c : vKey.toCharArray()) { //String characters to list
            vKeyList.add(c);
        }
        int vLettersInKey = vKeyList.size(); //Length of keyword

        String vUserInput = WordInput.getText(); //Word input to string
        String vWord = vUserInput.toLowerCase(); //word to lower case
        List<String> vWordList = new ArrayList<String>(Arrays.asList(vWord.split(""))); //Word string to list
        int vLettersInWord = vWordList.size(); //Length of word

        ArrayList<String> vOutputList = new ArrayList<String>(); //List for new (output) word

        if (vABC.equals("") || vKey.equals("") || vWord.equals("")) {
            NewWord.setText("");
        } else {
            if (vLettersInWord > vLettersInKey){
                int vMissingLetters = vLettersInWord - vLettersInKey;//how many letters are missing from keyword
                for (int j = 0; j < vMissingLetters; j++) {
                    char vChar = vKeyList.get(j);
                    vKeyList.add(vChar); //adding missing letters to keyword list
                }
            }

            for (int i = 0; i < vLettersInWord; i++) {
                String vWordLetter = vWordList.get(i); //check letters in the word
                char vKeyLetter = vKeyList.get(i);
                int vStep = vABClist.indexOf(Character.toString(vKeyLetter)); //index of keywork letter in ABC

                if (!vABClist.contains(vWordLetter)) { //adding characters which are not in ABC to output
                    vOutputList.add(i,vWordLetter);
                } else {
                    int vABCindex = vABClist.indexOf(vWordLetter); //find index of word letter in ABC
                    int vABCindex2 = 0;

                    if (!Encoder){
                        vABCindex2 = vABCindex - vStep;
                    } else {
                        vABCindex2 = vABCindex + vStep; //index on the final letter
                    }

                    if (vABCindex2 >= vLettersInABC) { //reduces length of step by abc length (step < abc !)
                        vABCindex2 = vABCindex2 - vLettersInABC;
                    } else if (vABCindex2 < 0){
                        vABCindex2 = vLettersInABC + vABCindex2;
                    }
                    vOutputList.add(i,vABClist.get(vABCindex2)); //add output letter to output list
                }
            }
            CheckUppercase(vLettersInWord,vOutputList,vUserInput);
            String vOutput = String.join("",vOutputList); //output list to string
            NewWord.setText(vOutput);
        }
        return NewWord;
    }

    private TextField CaesarTranslator(TextField ABCinput, TextField WordInput, TextField StepInput,
                                       TextField NewWord) {
        String cABC = ABCinput.getText(); //ABC input to string
        cABC = cABC.trim().replaceAll("\\s","").replaceAll(",","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
        List<String> cABClist = new ArrayList<String>(Arrays.asList(cABC.split(""))); //ABC string to list
        int cLettersInABC = cABClist.size();

        String cUserInput = WordInput.getText();
        String cWord = cUserInput.toLowerCase();
        List<String> cWordList = new ArrayList<String>(Arrays.asList(cWord.split(""))); // word string to list
        int cLettersInWord = cWordList.size();


        ArrayList<String> cOutputList = new ArrayList<String>(); //list for new (output) word

        if (cABC.equals("") || StepInput.getText().isEmpty() || cWord.equals("")){
            NewWord.setText("");
        } else {
            int step = Integer.parseInt(StepInput.getText()); // string input to int
            for (int i = 0; i < cLettersInWord; i++) {
                String cLetter = cWordList.get(i);
                if (!cABClist.contains(cLetter)) { //adding characters that are not in ABC
                    cOutputList.add(i, cLetter);
                }
                else {
                    int cABCindex = cABClist.indexOf(cLetter); //find the index of letter (from word) in ABC
                    int cABCindex2 = (cABCindex + step) % cLettersInABC;

                    if (cABCindex2 < 0) {
                        if (Math.abs(step) > cLettersInABC){ //reduces the length of step (step <= ABC)
                            step =  step % cLettersInABC;
                        }
                        cABCindex2 = cLettersInABC + cABCindex2;
                    }
                    cOutputList.add(i,cABClist.get(cABCindex2));
                }
            }
            CheckUppercase(cLettersInWord,cOutputList,cUserInput);
            String cOutput = String.join("",cOutputList);
            NewWord.setText(cOutput);
        }
        return NewWord;
    }

    private TextField AtbashTranslator(TextField ABCinput,TextField WordInput,TextField NewWord) {
        String aABC = ABCinput.getText(); // save inserted ABC to string
        aABC = aABC.trim().replaceAll("\\s", "").replaceAll(",","").toLowerCase(); // Remove spaces & commas from ABC, convert to lower case
        java.util.List<String> aABClist = new ArrayList<String>(Arrays.asList(aABC.split(""))); // ABC string to list
        int LettersInABC = aABClist.size(); // get length of the ABC

        String aUserInput = WordInput.getText(); // save inserted word to string
        String aWord = aUserInput.toLowerCase(); // Convert string to lower case
        List<String> aWordList = new ArrayList<String>(Arrays.asList(aWord.split(""))); // Word string to list
        int aLettersInWord = aWordList.size(); // get length of the word

        ArrayList<String> aOutputList = new ArrayList<String>(); // Empty list for the new word

        for (int i = 0; i < aLettersInWord; i++) {
            String aLetter = aWordList.get(i); // find the letter from word
            if (!aABClist.contains(aLetter)) { //adding characters that are not in ABC
                aOutputList.add(i, aLetter);
            }
            else {
                int aABCindex = aABClist.indexOf(aLetter); //find index of the letter (from word) in ABC
                int aABCindex2 = (LettersInABC - 1) - aABCindex; //find index of the encrypted letter
                aOutputList.add(i, aABClist.get(aABCindex2)); //encrypted letter to output list
            }
        }

        CheckUppercase(aLettersInWord,aOutputList,aUserInput);
        String aOutput = String.join("",aOutputList);
        NewWord.setText(aOutput);
        return NewWord;
    }

    private ArrayList<String> CheckUppercase(int LettersInWord, ArrayList<String> OutputList, String UserInput) {
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

    private void SetPromptText(TextField Field1,String Text1, TextField Field2,String Text2,
                               TextField Field3, String Text3) {
        Field1.clear();
        if (Field3 == null){
            if (Field2 == null){
                Field1.setPromptText(Text1);
            } else {
                Field2.clear();
                Field2.isFocusTraversable();
                Field1.setPromptText(Text1);
                Field2.setPromptText(Text2);
            }
        } else {
            Field2.clear();
            Field3.clear();
            Field1.setPromptText(Text1);
            Field2.setPromptText(Text2);
            Field3.setPromptText(Text3);
        }
    }

    private HashMap<String, String> FileToHashMap(boolean isMorse,String Line, HashMap<String, String> List,BufferedReader BR)
            throws IOException {
        while (Line != null) {
            String[] mTempList = Line.split("\\s"); //save key + value to array
            String Letter = mTempList[0]; //save letter (key)
            String Morse = mTempList[1]; //save morse code of letter (value)
            if (isMorse){
                List.put(Morse, Letter); //add key + value to HashMap
            } else {
                List.put(Letter,Morse); //add key + value to HashMap
            }
            Line = BR.readLine(); //read next line
        }
        return List;
    }

    private void WelcomeTextStyle(Label WelcomeText) {
        WelcomeText.setFont(Font.font(null, FontWeight.BOLD,20));
    }

    private void WarningTextStyle(Label Warning) {
        Warning.setFont(Font.font(null, FontPosture.ITALIC,14));
        Warning.setTextFill(Color.RED);
    }

    private void WordNotEditable(TextField NewWord) {
        NewWord.setEditable(false);
        NewWord.setStyle("-fx-background-color:" + TextBackgroundColor);
    }

    private void PaneStyle(VBox pane) {
        pane.setStyle("-fx-background-image: url("+ BackgroundPictureURL + ")");
        pane.setAlignment(Pos.CENTER); // aligning main view text to center of the window
    }

    private void ReturnToMainView() {
        mainStage.setScene(mainScene);
        mainStage.setTitle("Ciphers");
        mainStage.show();
    }

    private void ChangeABC(ChoiceBox<String> LanguageABC,TextField ABCinput) {
        //replace alphabet if language is chosen from drop down menu
        LanguageABC.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (Alphabets[newValue.intValue()].equals(ABCinputText)){
                    SetPromptText(ABCinput,ABCinputText,null,null,null,null);
                } else {
                    ABCinput.setText(Alphabets[newValue.intValue()]);
                }
            }
        });
    }

    private ChoiceBox<String> DefineABC(ChoiceBox<String> LanguageABC) {
        LanguageABC.setTooltip(Choose); //dispay text if mouse is hovering over the button
        LanguageABC.getSelectionModel().selectFirst(); //dispays the first selectin by default
        return LanguageABC;
    }

    private VBox ButtonsToVbox(ChoiceBox<String> Button1,Button Button2,Button Button3,Button Button4,Button Button5,
                               Button Button6) {
        VBox subPane = new VBox();

        HBox Hbox1 = new HBox(vBoxPadding);
        HBox Hbox2 = new HBox(vBoxPadding);
        HBox Hbox3 = new HBox(vBoxPadding);
        HBox Hbox4 = new HBox(vBoxPadding);

        if (Button1 == null) {
            Hbox1.getChildren().addAll(Button2,Button3);
            Hbox2.getChildren().addAll(Button5,Button6);
            Hbox3.getChildren().addAll(ReturnButton);
        } else if (Button4 == null) {
            Hbox1.getChildren().addAll(Button1,Button2);
            Hbox2.getChildren().addAll(Button3,Button5);
            Hbox3.getChildren().addAll(Button6,ReturnButton);
        } else {
            Hbox1.getChildren().addAll(Button1,Button2);
            Hbox2.getChildren().addAll(Button3,Button4);
            Hbox3.getChildren().addAll(Button6,Button5);
            Hbox4.getChildren().addAll(ReturnButton);
        }

        Hbox1.setAlignment(Pos.CENTER);
        Hbox1.setTranslateY(vBoxPadding*5);
        Hbox2.setAlignment(Pos.CENTER);
        Hbox2.setTranslateY(vBoxPadding*5);
        Hbox3.setAlignment(Pos.CENTER);
        Hbox3.setTranslateY(vBoxPadding*5);
        Hbox4.setAlignment(Pos.CENTER);
        Hbox4.setTranslateY(vBoxPadding*5);

        subPane.getChildren().addAll(Hbox1,Hbox2,Hbox3,Hbox4);
        return subPane;
    }

    private void ButtonsSameSize(ChoiceBox<String> Button1,Button Button2,Button Button3,Button Button4,Button Button5,
                                 Button Button6) {
        ReturnButton.setMaxWidth(buttonWidth);
        HBox.setHgrow(ReturnButton,Priority.ALWAYS);

        if (Button1 == null) {
            Button[] Buttons = new Button[4];
            Buttons[0] = Button2;
            Buttons[1] = Button3;
            Buttons[2] = Button5;
            Buttons[3] = Button6;

            for (int i = 0; i < 4; i++) {
                Buttons[i].setMaxWidth(buttonWidth);
                HBox.setHgrow(Buttons[i],Priority.ALWAYS);
            }
        } else if (Button4 == null) {
            Button1.setMaxWidth(buttonWidth);
            Button2.setMaxWidth(buttonWidth);
            Button3.setMaxWidth(buttonWidth);
            Button5.setMaxWidth(buttonWidth);
            Button6.setMaxWidth(buttonWidth);

            HBox.setHgrow(Button1,Priority.ALWAYS);
            HBox.setHgrow(Button2,Priority.ALWAYS);
            HBox.setHgrow(Button3,Priority.ALWAYS);
            HBox.setHgrow(Button5,Priority.ALWAYS);
            HBox.setHgrow(Button6,Priority.ALWAYS);
        } else {
            Button1.setMaxWidth(buttonWidth);
            Button2.setMaxWidth(buttonWidth);
            Button3.setMaxWidth(buttonWidth);
            Button4.setMaxWidth(buttonWidth);
            Button5.setMaxWidth(buttonWidth);
            Button6.setMaxWidth(buttonWidth);

            HBox.setHgrow(Button1,Priority.ALWAYS);
            HBox.setHgrow(Button2,Priority.ALWAYS);
            HBox.setHgrow(Button3,Priority.ALWAYS);
            HBox.setHgrow(Button4,Priority.ALWAYS);
            HBox.setHgrow(Button5,Priority.ALWAYS);
            HBox.setHgrow(Button6,Priority.ALWAYS);
        }
    }

    private void ShowInfoPicture(String Title, String Filename) {
        Image Picture = new Image("file:" + Filename);
        int PictureWidth = (int) Picture.getWidth();
        int PictureHeigth = (int) Picture.getHeight();

        Pane ImagePane = new Pane();
        Scene ImageScene = new Scene(ImagePane,PictureWidth,PictureHeigth);
        Stage ImageStage = new Stage();
        ImageStage.setResizable(false);
        ImageStage.sizeToScene();
        ImageStage.setScene(ImageScene);
        ImageStage.setTitle(Title);
        ImageStage.getIcons().add(new Image("file:questionmark.png"));
        ImageStage.show();

        ImageView vImageView = new ImageView();
        vImageView.setImage(Picture);

        ImagePane.getChildren().addAll(vImageView);
    }

    private void DisplayInfo(String HeaderText,String InfoText,boolean Scroll, Button Picture) {
        VBox HelpPane = new VBox();
        HelpPane.setPadding(new Insets(vBoxPadding));
        ScrollPane ScrollPane = new ScrollPane(HelpPane); //pane that can be scrolled
        ScrollPane.setFitToWidth(true);
        Scene HelpScene;

        if (Scroll) { //check if window needs to be scrolled or not
            HelpScene = new Scene(ScrollPane,width, height/2);
        } else {
            HelpScene = new Scene(HelpPane,width, height/2);
        }

        Stage HelpStage = new Stage(); // info opens in new window
        HelpStage.setTitle("Info");
        HelpStage.setScene(HelpScene);
        HelpStage.setResizable(false);
        HelpStage.getIcons().add(new Image("file:questionmark.png"));
        HelpStage.show();

        Label HelpTextHeader = new Label(HeaderText);
        WelcomeTextStyle(HelpTextHeader);
        Label HelpText = new Label();
        HelpText.setText(InfoText);
        HelpText.setPrefWidth(width);
        HelpText.setWrapText(true);
        HelpText.setTextAlignment(TextAlignment.JUSTIFY);

        HelpPane.setStyle("-fx-background-image: url("+ BackgroundPictureURL + ")"); //add background

        if (Picture == null){ //content of pane without buttons
            HelpPane.getChildren().addAll(HelpTextHeader, HelpText);
        } else { //content of pane with buttons
            VBox PictureVbox = new VBox(Picture);
            PictureVbox.setAlignment(Pos.CENTER);
            HelpPane.getChildren().addAll(HelpTextHeader, HelpText, PictureVbox);
        }
    }

    private void ClearFields(ChoiceBox<String> LanguageABC,TextField ABCinput,TextField StepInput,String StepInputText,
                       TextField WordInput,String WordInputText,TextField NewWord,String NewWordText) {
        if (StepInput == null) {
            if (ABCinput == null){ //if step and alphabet don't exist
                SetPromptText(WordInput,WordInputText,null,null,null,null);
                NewWord.setText(NewWordText);
            } else { //if step doesn't exist
                SetPromptText(ABCinput,ABCinputText,WordInput,WordInputText,null,null);
                NewWord.setText(NewWordText);
                LanguageABC.getSelectionModel().selectFirst();
            }
        } else { //if everything exists
            SetPromptText(ABCinput,ABCinputText,WordInput,WordInputText,StepInput,StepInputText);
            NewWord.setText(NewWordText);
            LanguageABC.getSelectionModel().selectFirst();
        }
    }

    private void SwapWords(TextField NewWord, TextField WordInput) {
        String CipherWord = NewWord.getText();
        WordInput.setText(CipherWord);
    }
}
