package Project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
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
    int height = 300;


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

        // defining buttons on main page
        AtbashButton = new Button("Atbash Cipher");
        CaesarButton = new Button("Caesar Shift Cipher");
        VigenereButton = new Button("Vigenère Cipher");
        MorseButton = new Button("Morse Code");
        ReturnButton = new Button("Return to menu");


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

            TextField ABCinput = new TextField("Insert the alphabet here");
            TextField WordInput = new TextField("Insert the word you want to cipher here");
            TextField NewWord = new TextField("Your answer will be displayed here");

            Button EstonianABC = new Button("Use Estonian alphabet");
            Button insert = new Button("Try the cipher!");
            Button clear = new Button("Clear fields");

            Atbashvbox.getChildren().addAll(ABCinput,WordInput,NewWord,EstonianABC,insert,clear,ReturnButton);

            // button that resets the fields to their original state
            clear.setOnAction(event1 -> {
                ABCinput.setText("Insert the alphabet here");
                WordInput.setText("Insert the word you want to cipher here");
                NewWord.setText("Your answer will be displayed here");
            });

            EstonianABC.setOnAction(event2 -> {
                ABCinput.setText("A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y");
            });

            // what happens after the alphabet & word have been inserted
            insert.setOnAction(event1 -> {
                String ABC = ABCinput.getText(); // save inserted ABC to string
                ABC = ABC.trim().replaceAll("\\s", "").toLowerCase(); // Remove spaces from ABC, convert to lower case
                java.util.List<String> ABClist = new ArrayList<String>(Arrays.asList(ABC.split(""))); // ABC string to list
                int LettersInABC = ABClist.size(); // get length of the ABC

                String Word = WordInput.getText(); // save inserted word to string
                Word = Word.toLowerCase(); // Convert string to lower case
                List<String> WordList = new ArrayList<String>(Arrays.asList(Word.split(""))); // Word string to list
                int LettersInWord = WordList.size(); // get length of the word

                List<String> OutputList = new ArrayList<String>(); // Empty list for the new word

                for (int i = 0; i < LettersInWord; i++) {
                    int ABCindex = ABClist.indexOf(WordList.get(i));
                    int ABCindex2 = (LettersInABC - 1) - ABCindex;
                    OutputList.add(i, ABClist.get(ABCindex2));
                }

                String Output = String.join("",OutputList);
                NewWord.setText(Output);

            });
        });

        CaesarButton.setOnAction(event -> {
            Caesarvbox = new VBox();
            CaesarScene = new Scene(Caesarvbox, width, height);
            mainStage.setScene(CaesarScene);
            mainStage.setTitle("Caesar Shift Cipher");
            mainStage.show();

            Caesarvbox.getChildren().addAll(ReturnButton);
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
