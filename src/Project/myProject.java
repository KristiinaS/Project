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

import java.awt.event.ActionEvent;

/**
 * Created by Kristiina on 30.11.2015.
 */
public class myProject extends Application {
    Button AtbashButton, CaesarButton, VigenereButton, MorseButton, ReturnButton;
    Scene mainScene, AtbashScene, CaesarScene, VigenereScene, MorseScene;
    VBox mainvbox, Atbashvbox, Caesarvbox, Vigenerevbox, Morsevbox;
    Stage mainStage;
    int height = 600;
    int width = 200;


    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        mainvbox = new VBox();
        mainScene = new Scene(mainvbox, height, width);
        mainStage.setTitle("Ciphers");
        mainStage.setScene(mainScene);
        mainStage.getIcons().add(new Image("file:FolderIcon.png"));
        mainStage.show();

        Label welcomeText = new Label("Choose the cipher from below.");

        AtbashButton = new Button("Atbash Cipher");
        CaesarButton = new Button("Caesar Shift Cipher");
        VigenereButton = new Button("Vigenère Cipher");
        MorseButton = new Button("Morse Code");
        ReturnButton = new Button("Return to menu");


        mainvbox.getChildren().addAll(welcomeText,AtbashButton,CaesarButton,VigenereButton,MorseButton);

        ReturnButton.setOnAction(event1 -> {
            mainStage.setScene(mainScene);
            mainStage.setTitle("Ciphers");
            mainStage.show();
        });

        AtbashButton.setOnAction(event -> {
            Atbashvbox = new VBox();
            AtbashScene = new Scene(Atbashvbox, height, width);
            mainStage.setScene(AtbashScene);
            mainStage.setTitle("Atbash Cipher");
            mainStage.show();

            Atbashvbox.getChildren().addAll(ReturnButton);
        });

        CaesarButton.setOnAction(event -> {
            Caesarvbox = new VBox();
            CaesarScene = new Scene(Caesarvbox, height, width);
            mainStage.setScene(CaesarScene);
            mainStage.setTitle("Caesar Shift Cipher");
            mainStage.show();

            Caesarvbox.getChildren().addAll(ReturnButton);
        });

        VigenereButton.setOnAction(event -> {
            Vigenerevbox = new VBox();
            VigenereScene = new Scene(Vigenerevbox, height, width);
            mainStage.setScene(VigenereScene);
            mainStage.setTitle("Vigenère Cipher");
            mainStage.show();

            Vigenerevbox.getChildren().addAll(ReturnButton);
        });

        MorseButton.setOnAction(event -> {
            Morsevbox = new VBox();
            MorseScene = new Scene(Morsevbox, height, width);
            mainStage.setScene(MorseScene);
            mainStage.setTitle("Morse code");
            mainStage.show();

            Morsevbox.getChildren().addAll(ReturnButton);
        });

    }





}
