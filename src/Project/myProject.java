package Project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.awt.event.ActionEvent;
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
    int width = 600;
    int height = 350;


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
        AtbashButton.setMaxWidth(width/3);
        CaesarButton.setMaxWidth(width/3);
        VigenereButton.setMaxWidth(width/3);
        MorseButton.setMaxWidth(width/3);
        ReturnButton.setMaxWidth(width/3);

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

            String aABCinputText = "Insert the alphabet here";
            String aWordInputText = "Insert the word you want to encrypt here";
            String aNewWordText = "Your answer will be displayed here";
            TextField aABCinput = new TextField(aABCinputText);
            TextField aWordInput = new TextField(aWordInputText);
            TextField aNewWord = new TextField(aNewWordText);

            Button aEstonianABC = new Button("Use Estonian alphabet");
            Button aInsert = new Button("Try the cipher!");
            Button aClear = new Button("Clear fields");
            Button aInfo = new Button("Info");

            //buttons to same size
            aEstonianABC.setMaxWidth(width/3);
            aInsert.setMaxWidth(width/3);
            aClear.setMaxWidth(width/3);
            aInfo.setMaxWidth(width/3);


            Atbashvbox.getChildren().addAll(aWarning,aABCinput,aWordInput,aNewWord,aEstonianABC,aInsert,aClear,aInfo,ReturnButton);

            // button that resets the fields to their original state
            aClear.setOnAction(event1 -> {
                aABCinput.setText(aABCinputText);
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

            aEstonianABC.setOnAction(event2 -> {
                aABCinput.setText("A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y");
            });

            // actions after the alphabet & word have been inserted
            aInsert.setOnAction(event1 -> {
                String aABC = aABCinput.getText(); // save inserted ABC to string
                aABC = aABC.trim().replaceAll("\\s", "").toLowerCase(); // Remove spaces from ABC, convert to lower case
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


            String cABCinputText = "Insert the alphabet here";
            String cStepInputText = "Insert the shift number (use minus (-) for left shift)";
            String cWordInputText = "Insert the word you want to encrypt here";
            String cNewWordText = "Your answer will be displayed here";
            TextField cABCinput = new TextField(cABCinputText);
            TextField cStepInput = new TextField(cStepInputText);
            TextField cWordInput = new TextField(cWordInputText);
            TextField cNewWord = new TextField(cNewWordText);

            Button cEstonianABC = new Button("Use Estonian alphabet");
            Button cInsert = new Button("Try the cipher!");
            Button cClear = new Button("Clear fields");
            Button cInfo = new Button("Info");

            //buttons to same size
            cEstonianABC.setMaxWidth(width/3);
            cInsert.setMaxWidth(width/3);
            cClear.setMaxWidth(width/3);
            cInfo.setMaxWidth(width / 3);

            Caesarvbox.getChildren().addAll(cWarning,cABCinput,cStepInput,cWordInput,cNewWord,cEstonianABC,cInsert,cClear,cInfo,ReturnButton);

            cClear.setOnAction(event1 -> {
                cABCinput.setText(cABCinputText);
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

            cEstonianABC.setOnAction(event1 -> {
                cABCinput.setText("A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y");
            });

            cInsert.setOnAction(event1 -> {
                String cABC = cABCinput.getText(); //ABC input to string
                cABC = cABC.trim().replaceAll("\\s","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
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

        VigenereButton.setOnAction(event -> {
            Vigenerevbox = new VBox();
            VigenereScene = new Scene(Vigenerevbox, width, height);
            mainStage.setScene(VigenereScene);
            mainStage.setTitle("Vigenère Cipher");
            mainStage.show();

            Vigenerevbox.getChildren().addAll(ReturnButton);
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
