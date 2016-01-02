package Project;

import javafx.application.Application;
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

import java.io.*;
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
    int width = 771;
    int height = 706;
    int buttonWidth = width/3;
    int vBoxPadding = 5;
    String TextBackgroundColor = "#F2F2F2";
    String BackgroundPicture = "http://www.webdesignhot.com/wp-content/uploads/2012/09/Abstract-Green-Bokeh-Light-Background-Vector-Graphic.jpg";

    String ABCinputText = "Insert the alphabet here or choose it from the list below";
    String[] Alphabets = {ABCinputText,
            "A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z",
            "А Б В Г Д Е Ё Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Æ Ø Å",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Å Ä Ö"};

    List<String> ABClanguages = new ArrayList<String>(Arrays.asList("User defined alphabet","Estonian",
            "English", "Russian","Danish/Norwegian","Swedish"));

    //combining 2 arrays - for alphabet
    public static String [] combine(String[] a, String[] b) {
        int length = a.length + b.length;
        String[] result = new String[length];
        System.arraycopy(a,0,result,0,a.length);
        System.arraycopy(b,0,result,a.length,b.length);
        return result;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        mainvbox = new VBox();
        mainScene = new Scene(mainvbox, width, height);
        mainStage.setTitle("Ciphers and Codes"); // naming the window
        mainStage.setScene(mainScene);
        mainStage.getIcons().add(new Image("file:FolderIcon.png")); // different icon for the window
        mainStage.show();

        Label welcomeText = new Label("Choose a cipher or code from the list below.");
        welcomeText.setFont(Font.font(null, FontWeight.BOLD,20));

        // defining buttons on main page
        AtbashButton = new Button("Atbash Cipher");
        CaesarButton = new Button("Caesar Shift Cipher");
        VigenereButton = new Button("Vigenère Cipher");
        MorseButton = new Button("Morse Code");
        ReturnButton = new Button("Return to menu");

        // making the buttons same size
        AtbashButton.setMaxWidth(buttonWidth);
        CaesarButton.setMaxWidth(buttonWidth);
        VigenereButton.setMaxWidth(buttonWidth);
        MorseButton.setMaxWidth(buttonWidth);
        ReturnButton.setMaxWidth(buttonWidth);

        mainvbox.setStyle("-fx-background-image: url("+ BackgroundPicture + ")");
        mainvbox.setAlignment(Pos.CENTER); // aligning main view text to center of the window
        mainvbox.getChildren().addAll(welcomeText,AtbashButton,CaesarButton,VigenereButton,MorseButton);

        // Button which takes back to the main view
        ReturnButton.setOnAction(event1 -> {
            mainStage.setScene(mainScene);
            mainStage.setTitle("Ciphers");
            mainStage.show();
        });

        // Atbach Cipher content
        AtbashButton.setOnAction(event -> {
            Atbashvbox = new VBox();
            Atbashvbox.setPadding(new Insets(vBoxPadding));
            AtbashScene = new Scene(Atbashvbox, width, height);
            mainStage.setScene(AtbashScene);
            mainStage.setTitle("Atbash Cipher");
            mainStage.show();

            Label aWelcomeText = new Label("Atbash Cipher");
            aWelcomeText.setFont(Font.font(null, FontWeight.BOLD,20));

            Label aWarning = new Label("At the moment all letters are converted to lower case!");
            aWarning.setFont(Font.font(null, FontPosture.ITALIC,14));
            aWarning.setTextFill(Color.RED);

            String aWordInputText = "Insert the word you want to encipher here";
            String aNewWordText = "Your answer will be displayed here";
            TextField aABCinput = new TextField(ABCinputText);
            TextField aWordInput = new TextField(aWordInputText);
            TextField aNewWord = new TextField(aNewWordText);

            aNewWord.setEditable(false);
            aNewWord.setStyle("-fx-background-color:" + TextBackgroundColor);

            Button aInsert = new Button("Try the cipher!");
            Button aClear = new Button("Clear fields");
            Button aSwap = new Button("Answer to input");
            Button aInfo = new Button("Info");

            //Defining the drop down menu
            ChoiceBox aLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));
            aLanguageABC.setTooltip(Choose); //dispay text if mouse is hovering over the button
            aLanguageABC.getSelectionModel().selectFirst(); //dispays the first selectin by default

            //replace alphabet if language is chosen from drop down menu
            aLanguageABC.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    aABCinput.setText(Alphabets[newValue.intValue()]);
                }
            });

            HBox aHbox1 = new HBox(vBoxPadding,aLanguageABC,aClear);
            HBox aHbox2 = new HBox(vBoxPadding,aInsert,aInfo);
            HBox aHbox3 = new HBox(vBoxPadding,aSwap,ReturnButton);

            //buttons to same size
            aInsert.setMaxWidth(buttonWidth);
            aClear.setMaxWidth(buttonWidth);
            aSwap.setMaxWidth(buttonWidth);
            aInfo.setMaxWidth(buttonWidth);
            aLanguageABC.setMaxWidth(buttonWidth);

            aHbox1.setAlignment(Pos.CENTER);
            aHbox1.setTranslateY(vBoxPadding*5);
            aHbox2.setAlignment(Pos.CENTER);
            aHbox2.setTranslateY(vBoxPadding*5);
            aHbox3.setAlignment(Pos.CENTER);
            aHbox3.setTranslateY(vBoxPadding*5);

            HBox.setHgrow(aLanguageABC,Priority.ALWAYS);
            HBox.setHgrow(aClear,Priority.ALWAYS);
            HBox.setHgrow(aInsert,Priority.ALWAYS);
            HBox.setHgrow(aInfo,Priority.ALWAYS);
            HBox.setHgrow(aSwap,Priority.ALWAYS);
            HBox.setHgrow(ReturnButton,Priority.ALWAYS);

            Atbashvbox.setAlignment(Pos.CENTER);
            Atbashvbox.setStyle("-fx-background-image: url(" + BackgroundPicture + ")");
            Atbashvbox.getChildren().addAll(aWelcomeText,aWarning,aABCinput,aWordInput,aNewWord,aHbox1,aHbox2,aHbox3);

            aSwap.setOnAction(event2 -> {
                Swap(aNewWord,aWordInput);
            });


            // button that resets the fields to their original state
            aClear.setOnAction(event1 -> {
                Clear(aABCinput,null,null,aWordInput,aWordInputText,aNewWord,aNewWordText);
            });

            // button with information about Atbash
            aInfo.setOnAction(event3 ->{
                String aHeaderText = "Atbash Cipher";
                String aInfoText = "The Atbash Cipher was originally a monoalphabetic substitution cipher used " +
                        "for the Hebrew alphabet. It is one of the earliest known subtitution ciphers to have " +
                        "been used, and is very simple.\n \nThe Atbash Cipher simply reverses the plaintext " +
                        "alphabet to create the ciphertext alphabet. That is, the first letter of the alphabet " +
                        "is encrypted to the last letter of the alphabet, the second letter to the second last " +
                        "letter and so forth.";
                Info(aHeaderText,aInfoText,false,null);
            });

            // actions after the alphabet & word have been inserted
            aInsert.setOnAction(event1 -> {
                String aABC = aABCinput.getText(); // save inserted ABC to string
                aABC = aABC.trim().replaceAll("\\s", "").replaceAll(",","").toLowerCase(); // Remove spaces & commas from ABC, convert to lower case
                java.util.List<String> aABClist = new ArrayList<String>(Arrays.asList(aABC.split(""))); // ABC string to list
                int LettersInABC = aABClist.size(); // get length of the ABC

                String aWord = aWordInput.getText(); // save inserted word to string
                aWord = aWord.toLowerCase(); // Convert string to lower case
                List<String> aWordList = new ArrayList<String>(Arrays.asList(aWord.split(""))); // Word string to list
                int aLettersInWord = aWordList.size(); // get length of the word

                List<String> aOutputList = new ArrayList<String>(); // Empty list for the new word

                for (int i = 0; i < aLettersInWord; i++) {
                    String aLetter = aWordList.get(i); // find the letter from word
                    if (aABClist.contains(aLetter)== false) { //adding characters that are not in ABC
                        aOutputList.add(i, aLetter);
                    }
                    else {
                        int aABCindex = aABClist.indexOf(aLetter); //find index of the letter (from word) in ABC
                        int aABCindex2 = (LettersInABC - 1) - aABCindex; //find index of the encrypted letter
                        aOutputList.add(i, aABClist.get(aABCindex2)); //encrypted letter to output list
                    }
                }

                String aOutput = String.join("",aOutputList);
                aNewWord.setText(aOutput);

            });
        });

        //Caesar Cipher content
        CaesarButton.setOnAction(event -> {
            Caesarvbox = new VBox();
            Caesarvbox.setPadding(new Insets(vBoxPadding));
            CaesarScene = new Scene(Caesarvbox, width, height);
            mainStage.setScene(CaesarScene);
            mainStage.setTitle("Caesar Cipher");
            mainStage.show();

            Label cWelcomeText = new Label("Caesar Cipher");
            cWelcomeText.setFont(Font.font(null, FontWeight.BOLD,20));

            Label cWarning = new Label("At the moment all letters are converted to lower case!");
            cWarning.setFont(Font.font(null, FontPosture.ITALIC,14));
            cWarning.setTextFill(Color.RED);


            String cStepInputText = "Insert the shift number (use minus (-) for left shift)";
            String cWordInputText = "Insert the word you want to encipher here";
            String cNewWordText = "Your answer will be displayed here";
            TextField cABCinput = new TextField(ABCinputText);
            TextField cStepInput = new TextField(cStepInputText);
            TextField cWordInput = new TextField(cWordInputText);
            TextField cNewWord = new TextField(cNewWordText);

            Button cInsert = new Button("Try the cipher!");
            Button cClear = new Button("Clear fields");
            Button cSwap = new Button("Answer to input");
            Button cInfo = new Button("Info");

            cNewWord.setEditable(false);
            cNewWord.setStyle("-fx-background-color:" + TextBackgroundColor);

            //Defining the drop down menu
            ChoiceBox cLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));
            cLanguageABC.setTooltip(Choose); //dispay text if mouse is hovering over the button
            cLanguageABC.getSelectionModel().selectFirst(); //dispays the first selectin by default

            //replace alphabet if language is chosen from drop down menu
            cLanguageABC.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    cABCinput.setText(Alphabets[newValue.intValue()]);
                }
            });

            //buttons to same size
            cInsert.setMaxWidth(buttonWidth);
            cClear.setMaxWidth(buttonWidth);
            cSwap.setMaxWidth(buttonWidth);
            cInfo.setMaxWidth(buttonWidth);
            cLanguageABC.setMaxWidth(buttonWidth);

            HBox cHbox1 = new HBox(vBoxPadding,cLanguageABC,cClear);
            HBox cHbox2 = new HBox(vBoxPadding,cInsert,cInfo);
            HBox cHbox3 = new HBox(vBoxPadding,cSwap,ReturnButton);
            cHbox1.setAlignment(Pos.CENTER);
            cHbox1.setTranslateY(vBoxPadding*5);
            cHbox2.setAlignment(Pos.CENTER);
            cHbox2.setTranslateY(vBoxPadding*5);
            cHbox3.setAlignment(Pos.CENTER);
            cHbox3.setTranslateY(vBoxPadding*5);

            HBox.setHgrow(cLanguageABC,Priority.ALWAYS);
            HBox.setHgrow(cClear,Priority.ALWAYS);
            HBox.setHgrow(cInsert,Priority.ALWAYS);
            HBox.setHgrow(cInfo,Priority.ALWAYS);
            HBox.setHgrow(cSwap,Priority.ALWAYS);
            HBox.setHgrow(ReturnButton,Priority.ALWAYS);

            Caesarvbox.setAlignment(Pos.CENTER);
            Caesarvbox.setStyle("-fx-background-image: url("+ BackgroundPicture + ")");
            Caesarvbox.getChildren().addAll(cWelcomeText,cWarning,cABCinput,cStepInput,cWordInput,cNewWord,cHbox1,cHbox2,cHbox3);

            cSwap.setOnAction(event2 -> {
                Swap(cNewWord,cWordInput);
            });

            cClear.setOnAction(event1 -> {
                Clear(cABCinput, cStepInput, cStepInputText, cWordInput, cWordInputText, cNewWord, cNewWordText);

            });

            cInfo.setOnAction(event2 -> {
                String cHeaderText = "Caesar Cipher";
                String cInfoText = "The Caesar cipher is one of the earliest known and simplest ciphers. The " +
                        "method is named after Julius Caesar, who apparently used it to communicate with his " +
                        "generals.\n \nIt is a type of substitution cipher in which each letter in the plaintext " +
                        "is 'shifted' a certain number of places down the alphabet. For example, with a shift " +
                        "of 1, A would be replaced by B, B would become C, and so on.\n \nIn order to reverse " +
                        "the encrypted text, you need to use the opposite value of the shift. For example, if " +
                        "the original shift is 2, you can encode the text with shift -2.";
                Info(cHeaderText,cInfoText,false,null);
            });

            cInsert.setOnAction(event1 -> {
                String cABC = cABCinput.getText(); //ABC input to string
                cABC = cABC.trim().replaceAll("\\s","").replaceAll(",","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
                List<String> cABClist = new ArrayList<String>(Arrays.asList(cABC.split(""))); //ABC string to list
                int cLettersInABC = cABClist.size();

                int step = Integer.parseInt(cStepInput.getText()); // string input to int

                String cWord = cWordInput.getText();
                cWord = cWord.toLowerCase();
                List<String> cWordList = new ArrayList<String>(Arrays.asList(cWord.split(""))); // word string to list
                int cLettersInWord = cWordList.size();


                List<String> cOutputList = new ArrayList<String>(); //list for new (output) word

                for (int i = 0; i < cLettersInWord; i++) {
                    String cLetter = cWordList.get(i);
                    if (cABClist.contains(cLetter)== false) { //adding characters that are not in ABC
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

                String cOutput = String.join("",cOutputList);
                cNewWord.setText(cOutput);
            });
        });

        // Õpetus - http://www.counton.org/explorer/codebreaking/vigenere-cipher.php
        VigenereButton.setOnAction(event -> {
            Vigenerevbox = new VBox();
            Vigenerevbox.setPadding(new Insets(vBoxPadding));
            VigenereScene = new Scene(Vigenerevbox, width, height);
            mainStage.setScene(VigenereScene);
            mainStage.setTitle("Vigenère Cipher");
            mainStage.show();

            Label vWelcomeText = new Label("Vigenère Cipher");
            vWelcomeText.setFont(Font.font(null, FontWeight.BOLD,20));

            Label vWarning = new Label("At the moment only one word can be enciphered at a time and all " +
                    "letters are converted to lower case!");
            vWarning.setFont(Font.font(null, FontPosture.ITALIC,14));
            vWarning.setTextFill(Color.RED);

            String vKeyInputText = "Enter the keyword";
            String vWordInputText = "Insert the word you want to encipher here";
            String vNewWordText = "Your answer will be displayed here";
            TextField vABCinput = new TextField(ABCinputText);
            TextField vKeyInput = new TextField(vKeyInputText);
            TextField vWordInput = new TextField(vWordInputText);
            TextField vNewWord = new TextField(vNewWordText);

            vNewWord.setEditable(false);
            vNewWord.setStyle("-fx-background-color:" + TextBackgroundColor);

            Button vInsert = new Button("Try the cipher!");
            Button vClear = new Button("Clear fields");
            Button vSwap = new Button("Answer to input");
            Button vInfo = new Button("Info");


            //Defining the drop down menu
            ChoiceBox vLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));
            vLanguageABC.setTooltip(Choose); //dispay text if mouse is hovering over the button
            vLanguageABC.getSelectionModel().selectFirst(); //dispays the first selectin by default

            //replace alphabet if language is chosen from drop down menu
            vLanguageABC.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    vABCinput.setText(Alphabets[newValue.intValue()]);
                }
            });


            //buttons to same size
            vInsert.setMaxWidth(buttonWidth);
            vClear.setMaxWidth(buttonWidth);
            vSwap.setMaxWidth(buttonWidth);
            vInfo.setMaxWidth(buttonWidth);
            vLanguageABC.setMaxWidth(buttonWidth);

            HBox vHbox1 = new HBox(vBoxPadding,vLanguageABC,vClear);
            HBox vHbox2 = new HBox(vBoxPadding,vInsert,vInfo);
            HBox vHbox3 = new HBox(vBoxPadding,vSwap,ReturnButton);
            vHbox1.setAlignment(Pos.CENTER);
            vHbox1.setTranslateY(vBoxPadding*5);
            vHbox2.setAlignment(Pos.CENTER);
            vHbox2.setTranslateY(vBoxPadding*5);
            vHbox3.setAlignment(Pos.CENTER);
            vHbox3.setTranslateY(vBoxPadding*5);

            HBox.setHgrow(vLanguageABC,Priority.ALWAYS);
            HBox.setHgrow(vClear,Priority.ALWAYS);
            HBox.setHgrow(vInsert,Priority.ALWAYS);
            HBox.setHgrow(vInfo,Priority.ALWAYS);
            HBox.setHgrow(vSwap,Priority.ALWAYS);
            HBox.setHgrow(ReturnButton,Priority.ALWAYS);

            Vigenerevbox.setAlignment(Pos.CENTER);
            Vigenerevbox.setStyle("-fx-background-image: url("+ BackgroundPicture + ")");
            Vigenerevbox.getChildren().addAll(vWelcomeText,vWarning,vABCinput,vKeyInput,vWordInput,vNewWord,vHbox1,vHbox2,vHbox3);

            vSwap.setOnAction(event2 -> {
                Swap(vNewWord,vWordInput);
            });

            vInsert.setOnAction(event3 -> {
                String vABC = vABCinput.getText(); //ABC input to string
                vABC = vABC.trim().replaceAll("\\s","").replaceAll(",","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
                List<String> vABClist = new ArrayList<String>(Arrays.asList(vABC.split(""))); //ABC string to list
                int vLettersInABC = vABClist.size(); //Length of ABC

                String vKey = vKeyInput.getText(); //Keyword input to string
                vKey = vKey.toLowerCase(); //Keyword to lower case
                List<Character> vKeyList = new ArrayList<Character>();
                for (char c : vKey.toCharArray()) { //String characters to list
                    vKeyList.add(c);
                }
                int vLettersInKey = vKeyList.size(); //Length of keyword

                String vWord = vWordInput.getText(); //Word input to string
                vWord = vWord.toLowerCase(); //word to lower case
                List<String> vWordList = new ArrayList<String>(Arrays.asList(vWord.split(""))); //Word string to list
                int vLettersInWord = vWordList.size(); //Length of word

                List<String> vOutputList = new ArrayList<String>(); //List for new (output) word

                if (vLettersInWord > vLettersInKey){
                    int vMissingLetters = vLettersInWord - vLettersInKey;//how many letters are missing from keyword
                    for (int j = 0; j < vMissingLetters; j++) {
                        char vChar = vKeyList.get(j);
                        vKeyList.add(vChar); //adding missing letters to list
                    }
                }


                for (int i = 0; i < vLettersInWord; i++) {
                    String vWordLetter = vWordList.get(i); //check letters in the word
                    char vKeyLetter = vKeyList.get(i);
                    //Character.toString(vKeyLetter); //converting character to string
                    int vStep = vABClist.indexOf(Character.toString(vKeyLetter));

                    if (vABClist.contains(vWordLetter) == false) { //adding characters which are not in ABC to output
                        vOutputList.add(i,vWordLetter);
                    } else {
                        int vABCindex = vABClist.indexOf(vWordLetter); //find index of word letter in ABC
                        int vABCindex2 = vABCindex + vStep;
                        if (vABCindex2 >= vLettersInABC) { //reduces length of step by abc length (step < abc !)
                            vABCindex2 = vABCindex2 - vLettersInABC;
                        }
                        vOutputList.add(i,vABClist.get(vABCindex2)); //add output letter to output list
                    }
                }

                String vOutput = String.join("",vOutputList); //output list to string
                vNewWord.setText(vOutput);

            });

            vClear.setOnAction(event1 -> {
                Clear(vABCinput,vKeyInput,vKeyInputText,vWordInput,vWordInputText,vNewWord,vNewWordText);
            });

            vInfo.setOnAction(event2 -> {
                String vHeaderText = "Vigenère Cipher";
                String vInfoText = "The Vigenère cipher was invented by a Frenchman, Blaise de Vigenère in " +
                        "the 16th century. It is a simple form of polyalphabetic cipher which uses a series of " +
                        "different Caesar ciphers to encrypt the data based on the letters of a keyword. " +
                        "Blaise de Vigenère developed a square to help encode messages (see below - " +
                        "Vigenére Table). A different row of the square can be used to encrypt each letter " +
                        "of the message. In other words, the sender might encrypt the first letter according " +
                        "to row 5, the second according to row 14, and the third letter according to row 21," +
                        " and so on. In order to unscramble the message, it is important that the intended " +
                        "receiver knows which row of the Vigenère Square has been used to encipher each letter, " +
                        "and so there must be an agreed system of switching between rows. This agreement is " +
                        "achieved via the keyword.\n \nTo illustrate how the keyword is used, let's encipher " +
                        "a word MOTHER with keyword KEY. First, the keyword is spelt out above the message " +
                        "and repeated until each letter in the message is associated with a letter from the " +
                        "keyword. Above first letter of the message, M, is the key letter, K. To encrypt this " +
                        "first letter, we go to the column headed by M and then see where it intersects the row " +
                        "starting with K, which is at the letter W. Consequently, the letter M in the plaintext " +
                        "is represented by W in the ciphertext. This step is continued until the whole text has " +
                        "been enciphered. In this case the enciphered text would be WSRRIP.";
                String vButtonTitle = "Vigenère table";
                Button vTable = new Button(vButtonTitle);
                Info(vHeaderText, vInfoText,true,vTable);


                vTable.setOnAction(event1 -> {
                    InfoButton(vButtonTitle,555,471,"tabularecta.jpg");
                });
            });
        });

        MorseButton.setOnAction(event -> {
            Morsevbox = new VBox();
            MorseScene = new Scene(Morsevbox, width, height);
            mainStage.setScene(MorseScene);
            mainStage.setTitle("Morse Code");
            mainStage.show();

            Label mWelcomeText = new Label("Morse Code");
            mWelcomeText.setFont(Font.font(null, FontWeight.BOLD,20));

            Label mWarning = new Label("At the moment translating from Morse to Latin alphabet doesn't work!");
            mWarning.setFont(Font.font(null, FontPosture.ITALIC,14));
            mWarning.setTextFill(Color.RED);

            String mWordInputText = "Insert the word you want to translate into Morse code here";
            String mNewWordText = "Your answer will be displayed here";
            TextField mWordInput = new TextField(mWordInputText);
            TextField mNewWord = new TextField(mNewWordText);

            mNewWord.setEditable(false);
            mNewWord.setStyle("-fx-background-color:" + TextBackgroundColor);

            Button mTranslate = new Button("Translate!");
            Button mClear = new Button("Clear fields");
            Button mInfo = new Button("Info");
            Button mSwap = new Button("Answer to input");

            mTranslate.setMaxWidth(buttonWidth);
            mClear.setMaxWidth(buttonWidth);
            mInfo.setMaxWidth(buttonWidth);
            mSwap.setMaxWidth(buttonWidth);

            HBox mHbox1 = new HBox(vBoxPadding,mTranslate,mClear);
            mHbox1.setAlignment(Pos.CENTER);
            mHbox1.setTranslateY(vBoxPadding*5);
            HBox mHbox2 = new HBox(vBoxPadding,mSwap,mInfo);
            mHbox2.setAlignment(Pos.CENTER);
            mHbox2.setTranslateY(vBoxPadding*5);
            HBox mHbox3 = new HBox(vBoxPadding,ReturnButton);
            mHbox3.setAlignment(Pos.CENTER);
            mHbox3.setTranslateY(vBoxPadding*5);

            HBox.setHgrow(ReturnButton,Priority.ALWAYS);
            HBox.setHgrow(mTranslate,Priority.ALWAYS);
            HBox.setHgrow(mInfo,Priority.ALWAYS);
            HBox.setHgrow(mClear,Priority.ALWAYS);
            HBox.setHgrow(mSwap,Priority.ALWAYS);

            Morsevbox.setAlignment(Pos.CENTER);
            Morsevbox.setStyle("-fx-background-image: url("+ BackgroundPicture + ")");
            Morsevbox.getChildren().addAll(mWelcomeText,mWarning,mWordInput,mNewWord,mHbox1,mHbox2,mHbox3);


            mSwap.setOnAction(event1 -> {
                Swap(mNewWord,mWordInput);
            });

            mClear.setOnAction(event1 -> {
                Clear(null,null,null,mWordInput,mWordInputText,mNewWord,mNewWordText);
            });

            mTranslate.setOnAction(event2 -> {
                String mWord = mWordInput.getText();
                mWord = mWord.toUpperCase(); //convert input word to upper case
                List<String> mWordList = new ArrayList<String>(Arrays.asList(mWord.split(""))); // Word string to list
                int mLettersInWord = mWordList.size(); // get length of the word
                //System.out.println("Tähti: " + mLettersInWord);

                List<String> mOutputList = new ArrayList<String>(); // Empty list for the new word

                File mMorseInternational = new File("MorseInternational.txt");
                HashMap <String,String> mList = new HashMap<String, String>();

                try {
                    BufferedReader mBR = new BufferedReader(new FileReader(mMorseInternational));
                    String mLine = mBR.readLine(); //read line from file
                    while (mLine != null) {
                        //System.out.println(mLine);
                        String[] mTempList = mLine.split("\\s");
                        //System.out.println("mTemplist = " + Arrays.toString(mTempList));
                        String mLetter = mTempList[0];
                        //System.out.println("mLetter = " + mLetter);
                        String mMorse = mTempList[1];
                        //System.out.println("mMorse = " + mMorse);
                        mList.put(mLetter, mMorse); //add key + value to HashMap
                        mLine = mBR.readLine(); //read next line
                    }


                    Set<Map.Entry<String,String>> hashSet = mList.entrySet();
                    int a = 0;
                    for (Map.Entry entry:hashSet){
                        System.out.println("Key="+entry.getKey()+", value="+entry.getValue()+ "(indeks: " + a + ")");
                        a=a+1;
                    }

                    String item = mList.keySet().toArray()[45].toString();
                    System.out.println("mis on kohal 45? " + item);


                    //ERROR!! "A" täht ei tööta UTF-8-ga.

                    for (int i = 0; i < mLettersInWord; i++) {
                        String mLetter = mWordList.get(i); // find the letter from word
                        /*
                        System.out.println("Täht sõnest: " + mLetter);
                        System.out.println("Kas on listis: " + mList.containsKey(mLetter));
                        String Value = mList.get(mLetter);
                        System.out.println("Võti = " + Value);
                        */
                        if (mList.containsKey(mLetter)== false) { //adding characters that are not in Morse code
                            if (Character.isWhitespace(mLetter.charAt(0))){
                                mOutputList.add(i, "/ ");
                            } else {
                                mOutputList.add(i, mLetter + " ");
                            }
                        } else {
                            String mValue = mList.get(mLetter);
                            //System.out.println("Võti = " + mValue);
                            mOutputList.add(i, mValue + " "); //encrypted letter to output list
                        }
                    }

                    String mOutput = String.join("",mOutputList);
                    mNewWord.setText(mOutput);

                    mBR.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            mInfo.setOnAction(event1 -> {
                String mHeaderText = "Morse Code";
                String mInfoText = "Morse code is a method of transmitting text information as a series of " +
                        "on-off tones, lights, or clicks that can be directly understood by a skilled listener " +
                        "or observer without special equipment. The International Morse Code encodes the ISO " +
                        "basic Latin alphabet, some extra Latin letters, the Arabic numerals and a small set " +
                        "of punctuation and procedural signals (prosigns) as standardized sequences of short and " +
                        "long signals called \"dots\" " +
                        "and \"dashes\", or \"dits\" and \"dahs\". Because many non-English natural languages " +
                        "use more than the 26 Roman letters, extensions to the Morse alphabet exist for those " +
                        "languages.\n \nEach Morse code symbol represents either a text character (letter or " +
                        "numeral) or a prosign and is represented by a unique sequence of dots and dashes. The " +
                        "duration of a dash is three times the duration of a dot. Each dot or dash is followed " +
                        "by a short silence, equal to the dot duration. The letters of a word are separated by a " +
                        "space equal to three dots (one dash), and the words are separated by a space equal to " +
                        "seven dots. The dot duration is the basic unit of time measurement in code transmission. " +
                        "To increase the speed of the communication, the code was designed so that the length of " +
                        "each character in Morse varies approximately inversely to its frequency of occurrence in " +
                        "English. Thus the most common letter in English, the letter \"E\", has the shortest code, " +
                        "a single dot.\n \nThis translator uses the international Morse alphabet. The letters are " +
                        "separated by a whitespace \" \" and words are separated by a slash \"\\\".";
                String mButtonTitle = "International Morse alphabet";
                Button mAlphabet = new Button(mButtonTitle);
                Info(mHeaderText,mInfoText,true,mAlphabet);

                mAlphabet.setOnAction(event2 -> {
                    InfoButton(mButtonTitle, 695, 584, "LatinMorse.jpg");
                });
            });
        });
    }

    private void InfoButton(String Title, int Width, int Heigth, String Filename) {
        Pane ImagePane = new Pane();
        Scene ImageScene = new Scene(ImagePane,Width,Heigth);
        Stage ImageStage = new Stage();
        ImageStage.setResizable(false);
        ImageStage.setScene(ImageScene);
        ImageStage.setTitle(Title);
        ImageStage.getIcons().add(new Image("file:questionmark.png"));
        ImageStage.show();

        ImageView vImageView = new ImageView();
        Image Picture = new Image("file:" + Filename);
        vImageView.setImage(Picture);

        ImagePane.getChildren().addAll(vImageView);
    }

    private void Info(String HeaderText,String InfoText,boolean Scroll, Button Picture) {
        VBox HelpPane = new VBox();
        HelpPane.setPadding(new Insets(vBoxPadding));
        ScrollPane ScrollPane = new ScrollPane(HelpPane); //pane that can be scrolled
        ScrollPane.setFitToWidth(true);
        Scene HelpScene;

        if (Scroll == true) { //check if window needs to be scrolled or not
            HelpScene = new Scene(ScrollPane,width, height/2);
        } else {
            HelpScene = new Scene(HelpPane,width, height/2);
        }

        Stage HelpStage = new Stage(); // info opens in new window
        HelpStage.setResizable(false);
        HelpStage.setTitle("Info");
        HelpStage.setScene(HelpScene);
        HelpStage.getIcons().add(new Image("file:questionmark.png"));
        HelpStage.show();

        Label HelpTextHeader = new Label(HeaderText);
        HelpTextHeader.setFont(Font.font(null, FontWeight.BOLD,20));
        Label HelpText = new Label();
        HelpText.setText(InfoText);
        HelpText.setPrefWidth(width);
        HelpText.setWrapText(true);
        HelpText.setTextAlignment(TextAlignment.JUSTIFY);

        //TO DO: Fix scrollpane background!!
        HelpPane.setStyle("-fx-background-image: url("+ BackgroundPicture + ")"); //add background

        if (Picture == null){ //content of pane without buttons
            HelpPane.getChildren().addAll(HelpTextHeader, HelpText);
        } else { //content of pane with buttons
            HelpPane.getChildren().addAll(HelpTextHeader, HelpText, Picture);
        }
    }

    private void Clear(TextField ABCinput,TextField StepInput,String StepInputText,TextField WordInput,String WordInputText,
                       TextField NewWord,String NewWordText) {
        if (StepInput == null) {
            if (ABCinput == null){ //if step and alphabet don't exist
                WordInput.setText(WordInputText);
                NewWord.setText(NewWordText);
            } else { //if step doesn't exist
                ABCinput.setText(ABCinputText);
                WordInput.setText(WordInputText);
                NewWord.setText(NewWordText);
            }
        } else { //if everything exists
            ABCinput.setText(ABCinputText);
            StepInput.setText(StepInputText);
            WordInput.setText(WordInputText);
            NewWord.setText(NewWordText);
        }
    }

    private void Swap(TextField NewWord, TextField WordInput) {
        String CipherWord = NewWord.getText();
        WordInput.setText(CipherWord);
    }
}
