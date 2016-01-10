package Project;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static Project.GlobalVariables.buttonWidth;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class Buttons extends Button {
    Buttons(String Name) {
        this.setText(Name);
        this.setMaxWidth(buttonWidth);
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}


