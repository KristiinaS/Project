package Project;

import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kristiina on 10.01.2016.
 */
public class MorseTranslator {
    public static TextField Translate(TextField WordInput, TextField NewWord) {
        String mWord = WordInput.getText(); //save input from TextField
        List<String> mOutputList = new ArrayList<String>(); // Empty list for the new word
        File mMorseInternational = new File("MorseInternational.txt"); //ERROR!! "A" doesn't work with UTF-8.
        HashMap<String,String> mList = new HashMap<String, String>(); //HashMap for Morse alphabet

        try {
            BufferedReader mBR = new BufferedReader(new FileReader(mMorseInternational)); //read file
            String mLine = mBR.readLine(); //read line from file

            if (mWord.equals("")){
                NewWord.setText("");
            } else if (mWord.matches("^[/. -]+$")){ //Translate Morse to Latin
                String[] mWordList = mWord.split("\\s+"); //Words from sentence to list
                int mLettersInWord = mWordList.length; //how many elements in list

                FileToHashMap(true,mLine,mList,mBR);

                for (int i = 0; i < mLettersInWord; i++) {
                    String mLetter = mWordList[i];
                    /*if (mWordList[i].contains("/") && !mWordList[i].matches("/")){
                        String[] mTempWordList = mLetter.split("/");
                        List<String> mTempOutputList = new ArrayList<>();
                        for (int j = 0; j < 2; j++) {
                            if (!mList.containsKey(mTempWordList[j])){
                                mOutputList.add(j,"#");
                            } else {
                                mTempOutputList.add(j,mList.get(mTempWordList[j]));
                            }
                        }
                        mTempOutputList.add(1," ");
                        String mTempOutput = String.join("",mTempOutputList);
                        mOutputList.add(mTempOutput);
                    } else*/ if (mWordList[i].equals("/")) {
                        mOutputList.add(i," "); //Replace "/" with a whitespace
                    } else if (!mList.containsKey(mLetter)){
                        mOutputList.add(i,"#");
                    } else {
                        String mValue = mList.get(mLetter); //find value of key
                        mOutputList.add(i,mValue); //letter of morse code to output list
                    }
                }
            } else { // Translate Latin to Morse
                mWord = mWord.toUpperCase(); //convert input word to upper case
                List<String> mWordList = new ArrayList<String>(Arrays.asList(mWord.split(""))); // Word string to list
                int mLettersInWord = mWordList.size(); // get length of the word

                FileToHashMap(false, mLine, mList, mBR);

                for (int i = 0; i < mLettersInWord; i++) {
                    String mLetter = mWordList.get(i); // find the letter from word
                    if (!mList.containsKey(mLetter)) { //adding characters that are not in Morse code
                        if (Character.isWhitespace(mLetter.charAt(0))){
                            mOutputList.add(i, "/ ");
                        } else {
                            mOutputList.add(i, "# ");
                        }
                    } else {
                        String mValue = mList.get(mLetter);
                        mOutputList.add(i, mValue + " "); //morse code of letter to output list
                    }
                }
            }

            String mOutput = String.join("",mOutputList);
            NewWord.setText(mOutput);

            mBR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NewWord;
    }

    private static HashMap<String, String> FileToHashMap(boolean isMorse,String Line,HashMap<String, String> List,BufferedReader BR)
            throws IOException {
        while (Line != null) {
            String[] mTempList = Line.split("\\s"); //save key + value to array
            String Letter = mTempList[0]; //save letter (key)
            String Morse = mTempList[1]; //save morse code of letter (value)
            if (isMorse){
                List.put(Morse, Letter); //add key + value to HashMap
            } else {
                List.put(Letter,Morse); //add key + value to HashMap
            }
            Line = BR.readLine(); //read next line
        }
        return List;
    }
}
