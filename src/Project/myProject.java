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

            Label warning = new Label("At the moment only one word can be encrypted at a time!");
            warning.setFont(Font.font(null, FontPosture.ITALIC,14));
            warning.setTextFill(Color.RED);

            TextField aABCinput = new TextField("Insert the alphabet here");
            TextField aWordInput = new TextField("Insert the word you want to cipher here");
            TextField aNewWord = new TextField("Your answer will be displayed here");

            Button EstonianABC = new Button("Use Estonian alphabet");
            Button insert = new Button("Try the cipher!");
            Button clear = new Button("Clear fields");
            Button info = new Button("Info");

            //buttons to same size
            EstonianABC.setMaxWidth(width/3);
            insert.setMaxWidth(width/3);
            clear.setMaxWidth(width/3);
            info.setMaxWidth(width/3);


            Atbashvbox.getChildren().addAll(warning,aABCinput,aWordInput,aNewWord,EstonianABC,insert,clear,info,ReturnButton);

            // button that resets the fields to their original state
            clear.setOnAction(event1 -> {
                aABCinput.setText("Insert the alphabet here");
                aWordInput.setText("Insert the word you want to cipher here");
                aNewWord.setText("Your answer will be displayed here");
            });

            // button with information about Atbash
            info.setOnAction(event3 ->{
                VBox HelpPane = new VBox();
                ScrollPane sp = new ScrollPane(HelpPane); //pane that can be scrolled
                sp.setFitToWidth(true);
                Scene HelpScene = new Scene(sp,width, height/2);
                Stage HelpStage = new Stage(); // info opens in new window
                HelpStage.setTitle("Info");
                HelpStage.setScene(HelpScene);
                HelpStage.getIcons().add(new Image("file:questionmark.png"));
                HelpStage.show();

                Label HelpTextHeader = new Label("Atbash Cipher");
                HelpTextHeader.setFont(Font.font(null, FontWeight.BOLD,20));
                Label HelpText = new Label();
                HelpText.setText("The Atbash Cipher was originally a monoalphabetic substitution cipher used " +
                        "for the Hebrew alphabet. It is one of the earliest known subtitution ciphers to have " +
                        "been used, and is very simple.\n \nThe Atbash Cipher simply reverses the plaintext " +
                        "alphabet to create the ciphertext alphabet. That is, the first letter of the alphabet " +
                        "is encrypted to the last letter of the alphabet, the second letter to the second last " +
                        "letter and so forth.");
                HelpText.setPrefWidth(width);
                HelpText.setWrapText(true);
                HelpText.setTextAlignment(TextAlignment.JUSTIFY);


                HelpPane.getChildren().addAll(HelpTextHeader, HelpText);

            });

            EstonianABC.setOnAction(event2 -> {
                aABCinput.setText("A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y");
            });

            // actions after the alphabet & word have been inserted
            insert.setOnAction(event1 -> {
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
                    int aABCindex = aABClist.indexOf(aWordList.get(i)); //find index of the letter (from word) in ABC
                    int aABCindex2 = (LettersInABC - 1) - aABCindex; //find index of the encrypted letter
                    aOutputList.add(i, aABClist.get(aABCindex2)); //encrypted letter to output list
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

            TextField cABCinput = new TextField("Insert the alphabet here");
            TextField cStepInput = new TextField("Insert the shift number (use minus (-) for left shift)");
            TextField cWordInput = new TextField("Insert the word you want to cipher here");
            TextField cNewWord = new TextField("Your answer will be displayed here");

            Button EstonianABC = new Button("Use Estonian alphabet");
            Button insert = new Button("Try the cipher!");
            Button clear = new Button("Clear fields");
            Button info = new Button("Info");

            //buttons to same size
            EstonianABC.setMaxWidth(width/3);
            insert.setMaxWidth(width/3);
            clear.setMaxWidth(width/3);
            info.setMaxWidth(width/3);

            Caesarvbox.getChildren().addAll(cABCinput,cStepInput,cWordInput,cNewWord,EstonianABC,insert,clear,info,ReturnButton);

            clear.setOnAction(event1 -> {
                cABCinput.setText("Insert the alphabet here");
                cStepInput.setText("Insert the shift number (use minus (-) for left shift)");
                cWordInput.setText("Insert the word you want to cipher here");
                cNewWord.setText("Your answer will be displayed here");
            });

            EstonianABC.setOnAction(event1 -> {
                cABCinput.setText("A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y");
            });

            insert.setOnAction(event1 -> {
                String cABC = cABCinput.getText(); //ABC input to string
                cABC = cABC.trim().replaceAll("\\s","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
                List<String> cABClist = new ArrayList<String>(Arrays.asList(cABC.split(""))); //ABC string to list
                int cLettersInABC = cABClist.size();

                int step = Integer.parseInt(cStepInput.getText()); // string input to int

                String cWord = cWordInput.getText();
                cWord = cWord.toLowerCase();
                System.out.println(cWord);
                List<String> cWordList = new ArrayList<String>(Arrays.asList(cWord.split(""))); // word string to list
                int cLettersInWord = cWordList.size();


                List<String> cOutputList = new ArrayList<String>(); //list for new (output) word

                for (int i = 0; i < cLettersInWord; i++) {
                    int cABCindex = cABClist.indexOf(cWordList.get(i)); //find the index of letter (from word) in ABC
                    int cABCindex2 = (cABCindex + step) % cLettersInABC;

                    if (cABCindex2 < 0) {
                        if (Math.abs(step) > cLettersInABC){ //if abs value of neg. step is bigger than ABC size
                            step =  step % cLettersInABC;
                        }
                        cABCindex2 = cLettersInABC + step;
                    }
                    cOutputList.add(i,cABClist.get(cABCindex2));
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
