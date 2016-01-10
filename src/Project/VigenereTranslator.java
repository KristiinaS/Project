package Project;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Project.GlobalMethods.CheckUppercase;
import static Project.GlobalMethods.TextNotEditableStyle;
import static Project.GlobalMethods.WarningTextToTextfield;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class VigenereTranslator {
    public static TextField Translate(boolean Encoder,TextField ABCinput, TextField KeyInput,
                                      TextField WordInput, TextField NewWord) {
        TextNotEditableStyle(NewWord);

        String vABC = ABCinput.getText(); //ABC input to string
        vABC = vABC.trim().replaceAll("\\s","").replaceAll(",","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
        List<String> vABClist = new ArrayList<String>(Arrays.asList(vABC.split(""))); //ABC string to list
        int vLettersInABC = vABClist.size(); //Length of ABC

        String vKey = KeyInput.getText(); //Keyword input to string
        vKey = vKey.toLowerCase().trim(); //Keyword to lower case + remove spaces
        List<Character> vKeyList = new ArrayList<Character>();
        for (char c : vKey.toCharArray()) { //String characters to list
            vKeyList.add(c);
        }
        int vLettersInKey = vKeyList.size(); //Length of keyword

        String vUserInput = WordInput.getText(); //Word input to string
        String vWord = vUserInput.toLowerCase(); //word to lower case
        List<String> vWordList = new ArrayList<String>(Arrays.asList(vWord.split(""))); //Word string to list
        int vLettersInWord = vWordList.size(); //Length of word

        ArrayList<String> vOutputList = new ArrayList<String>(); //List for new (output) word


        if (vABC.equals("") || vKey.equals("") || vWord.equals("")) {
            NewWord.setText("");
        } else if (!vKey.matches("^["+ vABC+"]+$")){
            String vErrorText = "Inserted key contains letters that are missing from the alphabet!";
            WarningTextToTextfield(NewWord,vErrorText);
        } else {
            if (vLettersInWord > vLettersInKey){
                int vMissingLetters = vLettersInWord - vLettersInKey;//how many letters are missing from keyword
                for (int j = 0; j < vMissingLetters; j++) {
                    char vChar = vKeyList.get(j);
                    vKeyList.add(vChar); //adding missing letters to keyword list
                }
            }

            for (int i = 0; i < vLettersInWord; i++) {
                String vWordLetter = vWordList.get(i); //check letters in the word
                char vKeyLetter = vKeyList.get(i);
                int vStep = vABClist.indexOf(Character.toString(vKeyLetter)); //index of keyword letter in ABC

                if (!vABClist.contains(vWordLetter)) { //adding characters which are not in ABC to output
                    vOutputList.add(i, vWordLetter);
                } else {
                    int vABCindex = vABClist.indexOf(vWordLetter); //find index of word letter in ABC
                    int vABCindex2 = 0;

                    if (!Encoder){
                        vABCindex2 = vABCindex - vStep;
                    } else {
                        vABCindex2 = vABCindex + vStep; //index on the final letter
                    }

                    if (vABCindex2 >= vLettersInABC) { //reduces length of step by abc length (step < abc !)
                        vABCindex2 = vABCindex2 - vLettersInABC;
                    } else if (vABCindex2 < 0){
                        vABCindex2 = vLettersInABC + vABCindex2;
                    }
                    vOutputList.add(i,vABClist.get(vABCindex2)); //add output letter to output list
                }
            }
            CheckUppercase(vLettersInWord,vOutputList,vUserInput);
            String vOutput = String.join("",vOutputList); //output list to string
            NewWord.setText(vOutput);
        }
        return NewWord;
    }
}
