package Project;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class GlobalVariables {
    public static String TextBackgroundColor = "#F2F2F2";
    public static Buttons ReturnButton = new Buttons("Return to menu");
    public static Buttons CloseButton = new Buttons("Close window");
    public static Scene mainScene;
    public static Stage mainStage;

    //Background for the application
    private static Image BackgroundPicture = new Image("file:Background.jpg");
    private static BackgroundImage BackgroundImage = new BackgroundImage(BackgroundPicture,null,null,
            BackgroundPosition.CENTER,null);
    public static Background Background = new Background(BackgroundImage);

    //Different sizes
    public static int width = (int) BackgroundPicture.getWidth();
    public static int height = (int) BackgroundPicture.getHeight();
    public static int buttonWidth = width/3;
    public static int vBoxPadding = 5;

    //All that is needed for the alphabet
    public static String ABCinputText = "Insert the alphabet here or choose it from the list below";
    public static String[] Alphabets = {ABCinputText,
            "A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z",
            "А Б В Г Д Е Ё Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Æ Ø Å",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Å Ä Ö"};
    public static Tooltip Choose = new Tooltip("Choose alphabet");
    private static List<String> ABClanguages = new ArrayList<String>(Arrays.asList("User defined alphabet", "Estonian",
            "English", "Russian", "Danish/Norwegian", "Swedish"));
    public static MyChoiceBox ABCchoiceBox = new MyChoiceBox(FXCollections.observableArrayList(ABClanguages));
}
