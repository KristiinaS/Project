package Project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static Project.CreateWindow.*;
import static Project.GlobalMethods.*;
import static Project.GlobalVariables.*;

/**
 * Created by Kristiina on 30.11.2015.
 */

public class myProject extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        VBox mainvbox = new VBox();
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
        Buttons AtbashButton = new Buttons("Atbash Cipher");
        Buttons CaesarButton = new Buttons("Caesar Shift Cipher");
        Buttons VigenereButton = new Buttons("Vigenère Cipher");
        Buttons MorseButton = new Buttons("Morse Code");

        PaneStyle(mainvbox); //background & alignment
        mainvbox.getChildren().addAll(welcomeText,AtbashButton,CaesarButton,VigenereButton,MorseButton);

        ReturnButton.setOnAction(event1 -> { // Button which takes back to the main view
            ReturnToMainView();
        });

        AtbashButton.setOnAction(event -> { // Atbach Cipher content
            AtbashCipher.ShowAtbashCipher();
        });

        CaesarButton.setOnAction(event -> { //Caesar Cipher content
            CaesarCipher.ShowCaesarCipher();
        });

        VigenereButton.setOnAction(event -> { //Vigenére Cipher content
            VigenereCipher.ShowVigenereCipher();
        });

        MorseButton.setOnAction(event -> { //Morse Code content
            MorseCode.ShowMorseCode();
        });
    }
}


