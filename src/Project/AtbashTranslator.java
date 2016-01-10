package Project;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Project.GlobalMethods.CheckUppercase;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class AtbashTranslator {
    public static TextField Translate(TextField ABCinput,TextField WordInput,TextField NewWord) {
        String aABC = ABCinput.getText(); // save inserted ABC to string
        aABC = aABC.trim().replaceAll("\\s", "").replaceAll(",","").toLowerCase(); // Remove spaces & commas from ABC, convert to lower case
        java.util.List<String> aABClist = new ArrayList<String>(Arrays.asList(aABC.split(""))); // ABC string to list
        int LettersInABC = aABClist.size(); // get length of the ABC

        String aUserInput = WordInput.getText(); // save inserted word to string
        String aWord = aUserInput.toLowerCase(); // Convert string to lower case
        List<String> aWordList = new ArrayList<String>(Arrays.asList(aWord.split(""))); // Word string to list
        int aLettersInWord = aWordList.size(); // get length of the word

        ArrayList<String> aOutputList = new ArrayList<String>(); // Empty list for the new word

        if (aABC.equals("") || aWord.equals("")) {
            NewWord.setText("");
        } else {
            for (int i = 0; i < aLettersInWord; i++) {
                String aLetter = aWordList.get(i); // find the letter from word
                if (!aABClist.contains(aLetter)) { //adding characters that are not in ABC
                    aOutputList.add(i, aLetter);
                }
                else {
                    int aABCindex = aABClist.indexOf(aLetter); //find index of the letter (from word) in ABC
                    int aABCindex2 = (LettersInABC - 1) - aABCindex; //find index of the encrypted letter
                    aOutputList.add(i, aABClist.get(aABCindex2)); //encrypted letter to output list
                }
            }
            CheckUppercase(aLettersInWord,aOutputList,aUserInput);
            String aOutput = String.join("",aOutputList);
            NewWord.setText(aOutput);
        }
        return NewWord;
    }
}
