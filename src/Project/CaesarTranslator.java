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
public class CaesarTranslator {
    public static TextField Translate(TextField ABCinput, TextField WordInput, TextField StepInput, TextField NewWord) {
        TextNotEditableStyle(NewWord);

        String cABC = ABCinput.getText(); //ABC input to string
        cABC = cABC.trim().replaceAll("\\s","").replaceAll(",","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
        List<String> cABClist = new ArrayList<String>(Arrays.asList(cABC.split(""))); //ABC string to list
        int cLettersInABC = cABClist.size();

        String cUserInput = WordInput.getText();
        String cWord = cUserInput.toLowerCase();
        List<String> cWordList = new ArrayList<String>(Arrays.asList(cWord.split(""))); // word string to list
        int cLettersInWord = cWordList.size();


        ArrayList<String> cOutputList = new ArrayList<String>(); //list for new (output) word

        if (cABC.equals("") || StepInput.getText().isEmpty() || cWord.equals("")){
            NewWord.setText("");
        } else {
            String StepString = StepInput.getText(); // save Step as string
            String FirstLetter = StepString.substring(0, 1); //save first letter of Step string
            String OtherLetters = StepString.substring(1); // remove first letter of Step string

            if (OtherLetters.equals("")){
                OtherLetters = "NotEmpty"; //adds some string for next comparison to return false
            }

            if (FirstLetter.matches("-")){
                if (!OtherLetters.matches("[0-9]*")){
                    String cErrorText = "The step must be an integer!"; //error displayed if step is not a number
                    WarningTextToTextfield(NewWord, cErrorText);
                } else {
                    if (Float.parseFloat(StepString) > Integer.MAX_VALUE ||
                            Float.parseFloat(StepString) < Integer.MIN_VALUE) {
                        String cErrorText = "The absalute value of the step is too big!"; //error displayed if step is too big to be integer (java)
                        WarningTextToTextfield(NewWord, cErrorText);
                    } else {
                        int step = Math.round(Float.parseFloat(StepString)); //save Step as int
                        for (int i = 0; i < cLettersInWord; i++) {
                            String cLetter = cWordList.get(i);
                            if (!cABClist.contains(cLetter)) { //adding characters that are not in ABC
                                cOutputList.add(i, cLetter);
                            } else {
                                int cABCindex = cABClist.indexOf(cLetter); //find the index of letter (from word) in ABC
                                int cABCindex2 = (cABCindex + step) % cLettersInABC;

                                if (cABCindex2 < 0) {
                                    if (Math.abs(step) > cLettersInABC) { //reduces the length of step (step <= ABC)
                                        step = step % cLettersInABC;
                                    }
                                    cABCindex2 = cLettersInABC + cABCindex2;
                                }
                                cOutputList.add(i, cABClist.get(cABCindex2));
                            }
                        }
                        CheckUppercase(cLettersInWord, cOutputList, cUserInput);
                        String cOutput = String.join("", cOutputList);
                        NewWord.setText(cOutput);
                    }
                }
            } else {
                if (StepString.length() < 2 ){
                    int step = Math.round(Float.parseFloat(StepString)); //save Step as int
                    for (int i = 0; i < cLettersInWord; i++) {
                        String cLetter = cWordList.get(i);
                        if (!cABClist.contains(cLetter)) { //adding characters that are not in ABC
                            cOutputList.add(i, cLetter);
                        } else {
                            int cABCindex = cABClist.indexOf(cLetter); //find the index of letter (from word) in ABC
                            int cABCindex2 = (cABCindex + step) % cLettersInABC;

                            if (cABCindex2 < 0) {
                                if (Math.abs(step) > cLettersInABC) { //reduces the length of step (step <= ABC)
                                    step = step % cLettersInABC;
                                }
                                cABCindex2 = cLettersInABC + cABCindex2;
                            }
                            cOutputList.add(i, cABClist.get(cABCindex2));
                        }
                    }
                    CheckUppercase(cLettersInWord, cOutputList, cUserInput);
                    String cOutput = String.join("", cOutputList);
                    NewWord.setText(cOutput);
                } else if (!OtherLetters.matches("[0-9]*")) {
                    String cErrorText = "The step must be an integer!"; //error displayed if step is not a number
                    WarningTextToTextfield(NewWord, cErrorText);
                } else {
                    if (Float.parseFloat(StepString) > Integer.MAX_VALUE ||
                            Float.parseFloat(StepString) < Integer.MIN_VALUE) {
                        String cErrorText = "The absalute value of the step is too big!"; //error displayed if step is too big to be integer (java)
                        WarningTextToTextfield(NewWord, cErrorText);
                    } else {
                        int step = Math.round(Float.parseFloat(StepString)); //save Step as int
                        for (int i = 0; i < cLettersInWord; i++) {
                            String cLetter = cWordList.get(i);
                            if (!cABClist.contains(cLetter)) { //adding characters that are not in ABC
                                cOutputList.add(i, cLetter);
                            } else {
                                int cABCindex = cABClist.indexOf(cLetter); //find the index of letter (from word) in ABC
                                int cABCindex2 = (cABCindex + step) % cLettersInABC;

                                if (cABCindex2 < 0) {
                                    if (Math.abs(step) > cLettersInABC) { //reduces the length of step (step <= ABC)
                                        step = step % cLettersInABC;
                                    }
                                    cABCindex2 = cLettersInABC + cABCindex2;
                                }
                                cOutputList.add(i, cABClist.get(cABCindex2));
                            }
                        }
                        CheckUppercase(cLettersInWord, cOutputList, cUserInput);
                        String cOutput = String.join("", cOutputList);
                        NewWord.setText(cOutput);
                    }
                }
            }
        }
        return NewWord;
    }
}
