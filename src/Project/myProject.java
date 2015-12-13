package Project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.xml.soap.Text;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kristiina on 30.11.2015.
 */
public class myProject extends Application {
    Button AtbashButton, CaesarButton, VigenereButton, MorseButton, ReturnButton;
    Scene mainScene, AtbashScene, CaesarScene, VigenereScene, MorseScene;
    VBox mainvbox, Atbashvbox, Caesarvbox, Vigenerevbox, Morsevbox;
    Stage mainStage;
    Tooltip Choose = new Tooltip("Choose alphabet");
    int width = 600;
    int height = 350;
    int buttonWidth = width/3;

    String ABCinputText = "Insert the alphabet here or choose it from the list below";

    String[] Alphabets = {"A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y",
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
        mainStage.setTitle("Ciphers"); // naming the window
        mainStage.setScene(mainScene);
        mainStage.getIcons().add(new Image("file:FolderIcon.png")); // different icon for the window
        mainStage.show();

        Label welcomeText = new Label("Choose a cipher from the list below.");
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
            AtbashScene = new Scene(Atbashvbox, width, height);
            mainStage.setScene(AtbashScene);
            mainStage.setTitle("Atbash Cipher");
            mainStage.show();

            Label aWarning = new Label("At the moment all letters are converted to lower case!");
            aWarning.setFont(Font.font(null, FontPosture.ITALIC,14));
            aWarning.setTextFill(Color.RED);

            String aWordInputText = "Insert the word you want to encipher here";
            String aNewWordText = "Your answer will be displayed here";
            TextField aABCinput = new TextField(ABCinputText);
            TextField aWordInput = new TextField(aWordInputText);
            TextField aNewWord = new TextField(aNewWordText);

            Button aEstonianABC = new Button("Use Estonian alphabet");
            Button aInsert = new Button("Try the cipher!");
            Button aClear = new Button("Clear fields");
            Button aInfo = new Button("Info");

            //alphabets for drop down menu
            String[] aABCinputTextArray = {ABCinputText}; //string to array
            String[] aAlphabets = combine(aABCinputTextArray,Alphabets); //add alphabets together

            //Defining the drop down menu
            ChoiceBox aLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));
            aLanguageABC.setTooltip(Choose); //dispay text if mouse is hovering over the button
            aLanguageABC.getSelectionModel().selectFirst(); //dispays the first selectin by default

            //replace alphabet if language is chosen from drop down menu
            aLanguageABC.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    aABCinput.setText(aAlphabets[newValue.intValue()]);
                }
            });

            //buttons to same size
            aEstonianABC.setMaxWidth(buttonWidth);
            aInsert.setMaxWidth(buttonWidth);
            aClear.setMaxWidth(buttonWidth);
            aInfo.setMaxWidth(buttonWidth);
            aLanguageABC.setMaxWidth(buttonWidth);


            Atbashvbox.getChildren().addAll(aWarning,aABCinput,aWordInput,aNewWord,aLanguageABC,aInsert,aClear,aInfo,ReturnButton);

            // button that resets the fields to their original state
            aClear.setOnAction(event1 -> {
                aABCinput.setText(ABCinputText);
                aWordInput.setText(aWordInputText);
                aNewWord.setText(aNewWordText);
            });

            // button with information about Atbash
            aInfo.setOnAction(event3 ->{
                VBox aHelpPane = new VBox();
                ScrollPane aScrollPane = new ScrollPane(aHelpPane); //pane that can be scrolled
                aScrollPane.setFitToWidth(true);
                Scene aHelpScene = new Scene(aScrollPane,width, height/2);
                Stage aHelpStage = new Stage(); // info opens in new window
                aHelpStage.setTitle("Info");
                aHelpStage.setScene(aHelpScene);
                aHelpStage.getIcons().add(new Image("file:questionmark.png"));
                aHelpStage.show();

                Label aHelpTextHeader = new Label("Atbash Cipher");
                aHelpTextHeader.setFont(Font.font(null, FontWeight.BOLD,20));
                Label aHelpText = new Label();
                aHelpText.setText("The Atbash Cipher was originally a monoalphabetic substitution cipher used " +
                        "for the Hebrew alphabet. It is one of the earliest known subtitution ciphers to have " +
                        "been used, and is very simple.\n \nThe Atbash Cipher simply reverses the plaintext " +
                        "alphabet to create the ciphertext alphabet. That is, the first letter of the alphabet " +
                        "is encrypted to the last letter of the alphabet, the second letter to the second last " +
                        "letter and so forth.");
                aHelpText.setPrefWidth(width);
                aHelpText.setWrapText(true);
                aHelpText.setTextAlignment(TextAlignment.JUSTIFY);


                aHelpPane.getChildren().addAll(aHelpTextHeader,aHelpText);

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
            CaesarScene = new Scene(Caesarvbox, width, height);
            mainStage.setScene(CaesarScene);
            mainStage.setTitle("Caesar Shift Cipher");
            mainStage.show();

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

            Button cEstonianABC = new Button("Use Estonian alphabet");
            Button cInsert = new Button("Try the cipher!");
            Button cClear = new Button("Clear fields");
            Button cInfo = new Button("Info");

            //alphabets for drop down menu
            String[] cABCinputTextArray = {ABCinputText}; //string to array
            String[] cAlphabets = combine(cABCinputTextArray,Alphabets); //add alphabets together

            //Defining the drop down menu
            ChoiceBox cLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));
            cLanguageABC.setTooltip(Choose); //dispay text if mouse is hovering over the button
            cLanguageABC.getSelectionModel().selectFirst(); //dispays the first selectin by default

            //replace alphabet if language is chosen from drop down menu
            cLanguageABC.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    cABCinput.setText(cAlphabets[newValue.intValue()]);
                }
            });

            //buttons to same size
            cEstonianABC.setMaxWidth(buttonWidth);
            cInsert.setMaxWidth(buttonWidth);
            cClear.setMaxWidth(buttonWidth);
            cInfo.setMaxWidth(buttonWidth);
            cLanguageABC.setMaxWidth(buttonWidth);

            Caesarvbox.getChildren().addAll(cWarning,cABCinput,cStepInput,cWordInput,cNewWord,cLanguageABC,cInsert,cClear,cInfo,ReturnButton);

            cClear.setOnAction(event1 -> {
                cABCinput.setText(ABCinputText);
                cStepInput.setText(cStepInputText);
                cWordInput.setText(cWordInputText);
                cNewWord.setText(cNewWordText);
            });

            cInfo.setOnAction(event2 -> {
                VBox cHelpPane = new VBox();
                ScrollPane cScrollPane = new ScrollPane(cHelpPane); //pane that can be scrolled
                cScrollPane.setFitToWidth(true);
                Scene cHelpScene = new Scene(cScrollPane,width, height/2);
                Stage cHelpStage = new Stage(); // info opens in new window
                cHelpStage.setTitle("Info");
                cHelpStage.setScene(cHelpScene);
                cHelpStage.getIcons().add(new Image("file:questionmark.png"));
                cHelpStage.show();

                Label cHelpTextHeader = new Label("Atbash Cipher");
                cHelpTextHeader.setFont(Font.font(null, FontWeight.BOLD,20));
                Label cHelpText = new Label();
                cHelpText.setText("The Caesar cipher is one of the earliest known and simplest ciphers. The " +
                        "method is named after Julius Caesar, who apparently used it to communicate with his " +
                        "generals.\n \nIt is a type of substitution cipher in which each letter in the plaintext " +
                        "is 'shifted' a certain number of places down the alphabet. For example, with a shift " +
                        "of 1, A would be replaced by B, B would become C, and so on.\n \nIn order to reverse " +
                        "the encrypted text, you need to use the opposite value of the shift. For example, if " +
                        "the original shift is 2, you can encode the text with shift -2");
                cHelpText.setPrefWidth(width);
                cHelpText.setWrapText(true);
                cHelpText.setTextAlignment(TextAlignment.JUSTIFY);


                cHelpPane.getChildren().addAll(cHelpTextHeader, cHelpText);
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
            VigenereScene = new Scene(Vigenerevbox, width, height);
            mainStage.setScene(VigenereScene);
            mainStage.setTitle("Vigenère Cipher");
            mainStage.show();

            Label vWarning = new Label("At the moment only one word can be enciphered at a time!");
            vWarning.setFont(Font.font(null, FontPosture.ITALIC,14));
            vWarning.setTextFill(Color.RED);

            String vKeyInputText = "Enter the keyword";
            String vWordInputText = "Insert the word you want to encipher here";
            String vNewWordText = "Your answer will be displayed here";
            TextField vABCinput = new TextField(ABCinputText);
            TextField vKeyInput = new TextField(vKeyInputText);
            TextField vWordInput = new TextField(vWordInputText);
            TextField vNewWord = new TextField(vNewWordText);

            Button vEstonianABC = new Button("Use Estonian alphabet");
            Button vEnglishABC = new Button("Use English alphabet");
            Button vInsert = new Button("Try the cipher!");
            Button vClear = new Button("Clear fields");
            Button vInfo = new Button("Info");

            //alphabets for drop down menu
            String[] vABCinputTextArray = {ABCinputText}; //string to array
            String[] vAlphabets = combine(vABCinputTextArray,Alphabets); //add alphabets together

            //Defining the drop down menu
            ChoiceBox vLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));
            vLanguageABC.setTooltip(Choose); //dispay text if mouse is hovering over the button
            vLanguageABC.getSelectionModel().selectFirst(); //dispays the first selectin by default

            //replace alphabet if language is chosen from drop down menu
            vLanguageABC.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    vABCinput.setText(vAlphabets[newValue.intValue()]);
                }
            });


            //buttons to same size
            vEstonianABC.setMaxWidth(buttonWidth);
            vEnglishABC.setMaxWidth(buttonWidth);
            vInsert.setMaxWidth(buttonWidth);
            vClear.setMaxWidth(buttonWidth);
            vInfo.setMaxWidth(buttonWidth);
            vLanguageABC.setMaxWidth(buttonWidth);

            Vigenerevbox.getChildren().addAll(vWarning,vABCinput,vKeyInput,vWordInput,vNewWord,vLanguageABC,vInsert,vClear,vInfo,ReturnButton);

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
                        if (vABCindex2 > vLettersInABC) { //reduces length of step by abc length (step < abc !)
                            vABCindex2 = vABCindex2 - vLettersInABC;
                        }
                        vOutputList.add(i,vABClist.get(vABCindex2)); //add output letter to output list
                    }
                }

                String vOutput = String.join("",vOutputList); //output list to string
                vNewWord.setText(vOutput);

            });

            vClear.setOnAction(event1 -> {
                vABCinput.setText(ABCinputText);
                vKeyInput.setText(vKeyInputText);
                vWordInput.setText(vWordInputText);
                vNewWord.setText(vNewWordText);
            });

            vInfo.setOnAction(event2 -> {
                VBox vHelpPane = new VBox();
                ScrollPane vScrollPane = new ScrollPane(vHelpPane); //pane that can be scrolled
                vScrollPane.setFitToWidth(true);
                Scene vHelpScene = new Scene(vScrollPane,width, height/2);
                Stage vHelpStage = new Stage(); // info opens in new window
                vHelpStage.setTitle("Info");
                vHelpStage.setScene(vHelpScene);
                vHelpStage.getIcons().add(new Image("file:questionmark.png"));
                vHelpStage.show();

                Label vHelpTextHeader = new Label("Atbash Cipher");
                vHelpTextHeader.setFont(Font.font(null, FontWeight.BOLD,20));
                Label vHelpText = new Label();
                vHelpText.setText("The Vigenère cipher was invented by a Frenchman, Blaise de Vigenère in " +
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
                        "been enciphered. In this case the enciphered text would be WSRRIP.");
                vHelpText.setPrefWidth(width);
                vHelpText.setWrapText(true);
                vHelpText.setTextAlignment(TextAlignment.JUSTIFY);

                Button vTable = new Button("Vigenére table");

                vHelpPane.getChildren().addAll(vHelpTextHeader,vHelpText,vTable);

                vTable.setOnAction(event1 -> {
                    Pane vImagePane = new Pane();
                    Scene vImageScene = new Scene(vImagePane,565,481);
                    Stage vImageStage = new Stage();
                    vImageStage.setScene(vImageScene);
                    vImageStage.setTitle("Vigenére Table");
                    vImageStage.getIcons().add(new Image("file:questionmark.png"));
                    vImageStage.show();

                    ImageView vImageView = new ImageView();
                    Image VigenereTable = new Image("file:tabularecta.jpg");
                    vImageView.setImage(VigenereTable);

                    vImagePane.getChildren().addAll(vImageView);
                });
            });
        });

        MorseButton.setOnAction(event -> {
            Morsevbox = new VBox();
            MorseScene = new Scene(Morsevbox, width, height);
            mainStage.setScene(MorseScene);
            mainStage.setTitle("Morse code");
            mainStage.show();

            Morsevbox.getChildren().addAll(ReturnButton);
        });

    }





}
