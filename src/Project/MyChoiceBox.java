package Project;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static Project.GlobalVariables.buttonWidth;

public class MyChoiceBox extends ChoiceBox {
    MyChoiceBox(ObservableList List) {
        this.setItems(List);
        this.setMaxWidth(buttonWidth);
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}
