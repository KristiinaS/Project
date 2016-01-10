package Project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static Project.CreateWindow.*;
import static Project.GlobalVariables.*;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class InfoWindow {
    public static void ShowInfo(String HeaderText,String InfoText, boolean Small,Button Picture) {
        VBox HelpPane = new VBox();
        HelpPane.setPadding(new Insets(vBoxPadding));
        HelpPane.setBackground(Background);
        Scene HelpScene;
        if (Small){
            HelpScene = new Scene(HelpPane,width,height/3);
        } else {
            HelpScene = new Scene(HelpPane,width,height/3*2);
        }

        Stage HelpStage = new Stage();
        HelpStage.setTitle("Info");
        HelpStage.sizeToScene();
        HelpStage.setScene(HelpScene);
        HelpStage.setResizable(false);
        HelpStage.getIcons().add(new Image("file:questionmark.png"));
        HelpStage.show();

        Label HelpTextHeader = new Label(HeaderText);
        WelcomeTextStyle(HelpTextHeader);
        Label HelpText = new Label();
        HelpText.setText(InfoText);
        HelpText.setWrapText(true);
        HelpText.setTextAlignment(TextAlignment.JUSTIFY);

        if (Picture == null){ //content of pane without buttons
            HelpPane.getChildren().addAll(HelpTextHeader, HelpText);
        } else { //content of pane with buttons
            HBox PictureHbox = new HBox(Picture);
            PictureHbox.setAlignment(Pos.CENTER);
            HelpPane.getChildren().addAll(HelpTextHeader, HelpText, PictureHbox);
        }
    }

    public static void ShowInfoPicture(String Title, String Filename){
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
}
