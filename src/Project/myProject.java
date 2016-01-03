package Project;

import com.sun.javafx.scene.control.skin.ChoiceBoxSkin;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.IfNode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * Created by Kristiina on 30.11.2015.
 */
public class myProject extends Application {
    Button AtbashButton, CaesarButton, VigenereButton, MorseButton, ReturnButton;
    Scene mainScene, AtbashScene, CaesarScene, VigenereScene, MorseScene;
    VBox mainvbox, Atbashvbox, Caesarvbox, Vigenerevbox, Morsevbox;
    Stage mainStage;
    Tooltip Choose = new Tooltip("Choose alphabet");
    int width = 771;
    int height = 706;
    int buttonWidth = width/3;
    int vBoxPadding = 5;
    String TextBackgroundColor = "#F2F2F2";
    String BackgroundPicture = "http://www.webdesignhot.com/wp-content/uploads/2012/09/Abstract-Green-Bokeh-Light-Background-Vector-Graphic.jpg";

    String ABCinputText = "Insert the alphabet here or choose it from the list below";
    String[] Alphabets = {ABCinputText,
            "A B C D E F G H I J K L M N O P Q R S Š Z Ž T U V W Õ Ä Ö Ü X Y",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z",
            "А Б В Г Д Е Ё Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Æ Ø Å",
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Å Ä Ö"};

    List<String> ABClanguages = new ArrayList<String>(Arrays.asList("User defined alphabet","Estonian",
            "English", "Russian","Danish/Norwegian","Swedish"));

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        mainvbox = new VBox();
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
        AtbashButton = new Button("Atbash Cipher");
        CaesarButton = new Button("Caesar Shift Cipher");
        VigenereButton = new Button("Vigenère Cipher");
        MorseButton = new Button("Morse Code");
        ReturnButton = new Button("Return to menu");

        ButtonsSameSize(null,AtbashButton,CaesarButton,VigenereButton,MorseButton);// making the buttons same size

        PaneStyle(mainvbox); //background & alignment
        mainvbox.getChildren().addAll(welcomeText,AtbashButton,CaesarButton,VigenereButton,MorseButton);

        // Button which takes back to the main view
        ReturnButton.setOnAction(event1 -> {
            ReturnToMainView();
        });

        // Atbach Cipher content
        AtbashButton.setOnAction(event -> {
            Atbashvbox = new VBox();
            Atbashvbox.setPadding(new Insets(vBoxPadding));
            AtbashScene = new Scene(Atbashvbox, width, height);
            mainStage.setScene(AtbashScene);
            mainStage.setTitle("Atbash Cipher");
            mainStage.show();

            Label aWelcomeText = new Label("Atbash Cipher");
            WelcomeTextStyle(aWelcomeText);

            Label aWarning = new Label("At the moment all letters are converted to lower case!");
            WarningTextStyle(aWarning);

            String aWordInputText = "Insert the word you want to encipher here";
            String aNewWordText = "Your answer will be displayed here";
            TextField aABCinput = new TextField();
            TextField aWordInput = new TextField();
            SetPromptText(aABCinput,ABCinputText,aWordInput,aWordInputText,null,null);
            TextField aNewWord = new TextField(aNewWordText);
            WordNotEditable(aNewWord);//cannot change text in TextField


            ChoiceBox aLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));//Defining the drop down menu
            DefineABC(aLanguageABC);
            ChangeABC(aLanguageABC,aABCinput);//replace alphabet if different ABC is chosen from drop down menu

            Button aInsert = new Button("Try the cipher!");
            Button aClear = new Button("Clear fields");
            Button aSwap = new Button("Answer to input");
            Button aInfo = new Button("Info");

            ButtonsSameSize(aLanguageABC,aClear,aInsert,aInfo,aSwap);

            PaneStyle(Atbashvbox);
            Atbashvbox.getChildren().addAll(aWelcomeText,aABCinput,aWordInput,aNewWord,
                    ButtonsToVbox(aLanguageABC,aClear,aInsert,aInfo,aSwap));

            aSwap.setOnAction(event2 -> {
                Swap(aNewWord,aWordInput);
            });


            // button that resets the fields to their original state
            aClear.setOnAction(event1 -> {
                Clear(aLanguageABC,aABCinput,null,null,aWordInput,aWordInputText,aNewWord,aNewWordText);
            });

            // button with information about Atbash
            aInfo.setOnAction(event3 ->{
                String aHeaderText = "Atbash Cipher";
                String aInfoText = "The Atbash Cipher was originally a monoalphabetic substitution cipher used " +
                        "for the Hebrew alphabet. It is one of the earliest known subtitution ciphers to have " +
                        "been used, and is very simple.\n \nThe Atbash Cipher simply reverses the plaintext " +
                        "alphabet to create the ciphertext alphabet. That is, the first letter of the alphabet " +
                        "is encrypted to the last letter of the alphabet, the second letter to the second last " +
                        "letter and so forth.";
                Info(aHeaderText,aInfoText,false,null);
            });

            // actions after the alphabet & word have been inserted
            aInsert.setOnAction(event1 -> {
                String aABC = aABCinput.getText(); // save inserted ABC to string
                aABC = aABC.trim().replaceAll("\\s", "").replaceAll(",","").toLowerCase(); // Remove spaces & commas from ABC, convert to lower case
                java.util.List<String> aABClist = new ArrayList<String>(Arrays.asList(aABC.split(""))); // ABC string to list
                int LettersInABC = aABClist.size(); // get length of the ABC

                String aUserInput = aWordInput.getText(); // save inserted word to string
                String aWord = aUserInput.toLowerCase(); // Convert string to lower case
                List<String> aWordList = new ArrayList<String>(Arrays.asList(aWord.split(""))); // Word string to list
                int aLettersInWord = aWordList.size(); // get length of the word

                ArrayList<String> aOutputList = new ArrayList<String>(); // Empty list for the new word

                for (int i = 0; i < aLettersInWord; i++) {
                    String aLetter = aWordList.get(i); // find the letter from word
                    if (aABClist.contains(aLetter)== false) { //adding characters that are not in ABC
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
                aNewWord.setText(aOutput);

            });
        });

        //Caesar Cipher content
        CaesarButton.setOnAction(event -> {
            Caesarvbox = new VBox();
            Caesarvbox.setPadding(new Insets(vBoxPadding));
            CaesarScene = new Scene(Caesarvbox, width, height);
            mainStage.setScene(CaesarScene);
            mainStage.setTitle("Caesar Cipher");
            mainStage.show();

            Label cWelcomeText = new Label("Caesar Cipher");
            WelcomeTextStyle(cWelcomeText);

            Label cWarning = new Label("At the moment all letters are converted to lower case!");
            WarningTextStyle(cWarning);

            String cStepInputText = "Insert the shift number (use minus (-) for left shift)";
            String cWordInputText = "Insert the word you want to encipher here";
            String cNewWordText = "Your answer will be displayed here";
            TextField cABCinput = new TextField();
            TextField cStepInput = new TextField();
            TextField cWordInput = new TextField();
            SetPromptText(cABCinput,ABCinputText,cStepInput,cStepInputText,cWordInput,cWordInputText);
            TextField cNewWord = new TextField(cNewWordText);
            WordNotEditable(cNewWord);//cannot change text in TextField

            Button cInsert = new Button("Try the cipher!");
            Button cClear = new Button("Clear fields");
            Button cSwap = new Button("Answer to input");
            Button cInfo = new Button("Info");

            ChoiceBox cLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));//Defining the drop down menu
            DefineABC(cLanguageABC);
            ChangeABC(cLanguageABC, cABCinput);//replace alphabet if language is chosen from drop down menu

            ButtonsSameSize(cLanguageABC,cClear,cInsert,cInfo,cSwap);

            PaneStyle(Caesarvbox);
            Caesarvbox.getChildren().addAll(cWelcomeText,cABCinput,cStepInput,cWordInput,cNewWord,
                    ButtonsToVbox(cLanguageABC,cClear,cInsert,cInfo,cSwap));

            cSwap.setOnAction(event2 -> {
                Swap(cNewWord,cWordInput);
            });

            cClear.setOnAction(event1 -> {
                Clear(cLanguageABC,cABCinput, cStepInput, cStepInputText, cWordInput, cWordInputText, cNewWord, cNewWordText);

            });

            cInfo.setOnAction(event2 -> {
                String cHeaderText = "Caesar Cipher";
                String cInfoText = "The Caesar cipher is one of the earliest known and simplest ciphers. The " +
                        "method is named after Julius Caesar, who apparently used it to communicate with his " +
                        "generals.\n \nIt is a type of substitution cipher in which each letter in the plaintext " +
                        "is 'shifted' a certain number of places down the alphabet. For example, with a shift " +
                        "of 1, A would be replaced by B, B would become C, and so on.\n \nIn order to reverse " +
                        "the encrypted text, you need to use the opposite value of the shift. For example, if " +
                        "the original shift is 2, you can encode the text with shift -2.";
                Info(cHeaderText,cInfoText,false,null);
            });

            cInsert.setOnAction(event1 -> {
                String cABC = cABCinput.getText(); //ABC input to string
                cABC = cABC.trim().replaceAll("\\s","").replaceAll(",","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
                List<String> cABClist = new ArrayList<String>(Arrays.asList(cABC.split(""))); //ABC string to list
                int cLettersInABC = cABClist.size();

                String cUserInput = cWordInput.getText();
                String cWord = cUserInput.toLowerCase();
                List<String> cWordList = new ArrayList<String>(Arrays.asList(cWord.split(""))); // word string to list
                int cLettersInWord = cWordList.size();


                ArrayList<String> cOutputList = new ArrayList<String>(); //list for new (output) word

                if (cABC.equals("") || cStepInput.getText().isEmpty() || cWord.equals("")){
                    cNewWord.setText("");
                } else {
                    int step = Integer.parseInt(cStepInput.getText()); // string input to int
                    for (int i = 0; i < cLettersInWord; i++) {
                        String cLetter = cWordList.get(i);
                        if (cABClist.contains(cLetter)== false) { //adding characters that are not in ABC
                            cOutputList.add(i, cLetter);
                        }
                        else {
                            int cABCindex = cABClist.indexOf(cLetter); //find the index of letter (from word) in ABC
                            int cABCindex2 = (cABCindex + step) % cLettersInABC;

                            if (cABCindex2 < 0) {
                                if (Math.abs(step) > cLettersInABC){ //reduces the length of step (step <= ABC)
                                    step =  step % cLettersInABC;
                                }
                                cABCindex2 = cLettersInABC + cABCindex2;
                            }
                            cOutputList.add(i,cABClist.get(cABCindex2));
                        }
                    }
                    CheckUppercase(cLettersInWord,cOutputList,cUserInput);
                    String cOutput = String.join("",cOutputList);
                    cNewWord.setText(cOutput);
                }
            });
        });

        VigenereButton.setOnAction(event -> {
            Vigenerevbox = new VBox();
            Vigenerevbox.setPadding(new Insets(vBoxPadding));
            VigenereScene = new Scene(Vigenerevbox, width, height);
            mainStage.setScene(VigenereScene);
            mainStage.setTitle("Vigenère Cipher");
            mainStage.show();

            Label vWelcomeText = new Label("Vigenère Cipher");
            WelcomeTextStyle(vWelcomeText);

            Label vWarning = new Label("At the moment only one word can be enciphered at a time and all " +
                    "letters are converted to lower case!");
            WarningTextStyle(vWarning);

            String vKeyInputText = "Enter the keyword";
            String vWordInputText = "Insert the word you want to encipher here";
            String vNewWordText = "Your answer will be displayed here";
            TextField vABCinput = new TextField();
            TextField vKeyInput = new TextField();
            TextField vWordInput = new TextField();
            SetPromptText(vABCinput,ABCinputText,vKeyInput,vKeyInputText,vWordInput,vWordInputText);
            TextField vNewWord = new TextField(vNewWordText);
            WordNotEditable(vNewWord); //cannot change text in TextField

            Button vInsert = new Button("Try the cipher!");
            Button vClear = new Button("Clear fields");
            Button vSwap = new Button("Answer to input");
            Button vInfo = new Button("Info");


            //Defining the drop down menu
            ChoiceBox vLanguageABC = new ChoiceBox(FXCollections.observableArrayList(ABClanguages));
            DefineABC(vLanguageABC);
            ChangeABC(vLanguageABC,vABCinput);//replace alphabet if language is chosen from drop down menu

            ButtonsSameSize(vLanguageABC,vClear,vInsert,vInfo,vSwap);

            PaneStyle(Vigenerevbox);
            Vigenerevbox.getChildren().addAll(vWelcomeText,vABCinput,vKeyInput,vWordInput,vNewWord,
                    ButtonsToVbox(vLanguageABC,vClear,vInsert,vInfo,vSwap));

            vSwap.setOnAction(event2 -> {
                Swap(vNewWord,vWordInput);
            });

            vInsert.setOnAction(event3 -> {
                String vABC = vABCinput.getText(); //ABC input to string
                vABC = vABC.trim().replaceAll("\\s","").replaceAll(",","").toLowerCase(); //Remove spaces, commas from ABC + to lower case
                List<String> vABClist = new ArrayList<String>(Arrays.asList(vABC.split(""))); //ABC string to list
                int vLettersInABC = vABClist.size(); //Length of ABC

                String vKey = vKeyInput.getText(); //Keyword input to string
                vKey = vKey.toLowerCase(); //Keyword to lower case
                List<Character> vKeyList = new ArrayList<Character>();
                for (char c : vKey.toCharArray()) { //String characters to list
                    vKeyList.add(c);
                }
                int vLettersInKey = vKeyList.size(); //Length of keyword

                String vUserInput = vWordInput.getText(); //Word input to string
                String vWord = vUserInput.toLowerCase(); //word to lower case
                List<String> vWordList = new ArrayList<String>(Arrays.asList(vWord.split(""))); //Word string to list
                int vLettersInWord = vWordList.size(); //Length of word

                ArrayList<String> vOutputList = new ArrayList<String>(); //List for new (output) word

                if (vABC.equals("") || vKey.equals("") || vWord.equals("")) {
                    vNewWord.setText("");
                } else {
                    if (vLettersInWord > vLettersInKey){
                        int vMissingLetters = vLettersInWord - vLettersInKey;//how many letters are missing from keyword
                        for (int j = 0; j < vMissingLetters; j++) {
                            char vChar = vKeyList.get(j);
                            vKeyList.add(vChar); //adding missing letters to list
                        }
                    }

                    for (int i = 0; i < vLettersInWord; i++) {
                        String vWordLetter = vWordList.get(i); //check letters in the word
                        char vKeyLetter = vKeyList.get(i);
                        //Character.toString(vKeyLetter); //converting character to string
                        int vStep = vABClist.indexOf(Character.toString(vKeyLetter));

                        if (vABClist.contains(vWordLetter) == false) { //adding characters which are not in ABC to output
                            vOutputList.add(i,vWordLetter);
                        } else {
                            int vABCindex = vABClist.indexOf(vWordLetter); //find index of word letter in ABC
                            int vABCindex2 = vABCindex + vStep;
                            if (vABCindex2 >= vLettersInABC) { //reduces length of step by abc length (step < abc !)
                                vABCindex2 = vABCindex2 - vLettersInABC;
                            }
                            vOutputList.add(i,vABClist.get(vABCindex2)); //add output letter to output list
                        }
                    }
                    CheckUppercase(vLettersInWord,vOutputList,vUserInput);
                    String vOutput = String.join("",vOutputList); //output list to string
                    vNewWord.setText(vOutput);
                }
            });

            vClear.setOnAction(event1 -> {
                Clear(vLanguageABC,vABCinput,vKeyInput,vKeyInputText,vWordInput,vWordInputText,vNewWord,vNewWordText);
            });

            vInfo.setOnAction(event2 -> {
                String vHeaderText = "Vigenère Cipher";
                String vInfoText = "The Vigenère cipher was invented by a Frenchman, Blaise de Vigenère in " +
                        "the 16th century. It is a simple form of polyalphabetic cipher which uses a series of " +
                        "different Caesar ciphers to encrypt the data based on the letters of a keyword. " +
                        "Blaise de Vigenère developed a square to help encode messages (see below - " +
                        "Vigenére Table). A different row of the square can be used to encrypt each letter " +
                        "of the message. In other words, the sender might encrypt the first letter according " +
                        "to row 5, the second according to row 14, and the third letter according to row 21," +
                        " and so on. In order to unscramble the message, it is important that the intended " +
                        "receiver knows which row of the Vigenère Square has been used to encipher each letter, " +
                        "and so there must be an agreed system of switching between rows. This agreement is " +
                        "achieved via the keyword.\n \nTo illustrate how the keyword is used, let's encipher " +
                        "a word MOTHER with keyword KEY. First, the keyword is spelt out above the message " +
                        "and repeated until each letter in the message is associated with a letter from the " +
                        "keyword. Above first letter of the message, M, is the key letter, K. To encrypt this " +
                        "first letter, we go to the column headed by M and then see where it intersects the row " +
                        "starting with K, which is at the letter W. Consequently, the letter M in the plaintext " +
                        "is represented by W in the ciphertext. This step is continued until the whole text has " +
                        "been enciphered. In this case the enciphered text would be WSRRIP.";
                String vButtonTitle = "Vigenère table";
                Button vTable = new Button(vButtonTitle);
                Info(vHeaderText, vInfoText,true,vTable);


                vTable.setOnAction(event1 -> {
                    InfoButton(vButtonTitle,"tabularecta.jpg");
                });
            });
        });

        MorseButton.setOnAction(event -> {
            Morsevbox = new VBox();
            MorseScene = new Scene(Morsevbox, width, height);
            mainStage.setScene(MorseScene);
            mainStage.setTitle("Morse Code");
            mainStage.show();

            Label mWelcomeText = new Label("Morse Code");
            WelcomeTextStyle(mWelcomeText);

            Label mWarning = new Label("At the moment translating from Morse to Latin alphabet doesn't work!");
            WarningTextStyle(mWarning);

            String mWordInputText = "Insert the word you want to translate into Morse code here";
            String mNewWordText = "Your answer will be displayed here";
            TextField mWordInput = new TextField();
            SetPromptText(mWordInput,mWordInputText,null,null,null,null);
            TextField mNewWord = new TextField(mNewWordText);
            WordNotEditable(mNewWord);//cannot change text in TextField

            Button mTranslate = new Button("Translate!");
            Button mClear = new Button("Clear fields");
            Button mInfo = new Button("Info");
            Button mSwap = new Button("Answer to input");

            ButtonsSameSize(null,mTranslate,mClear,mSwap,mInfo);

            PaneStyle(Morsevbox);
            Morsevbox.getChildren().addAll(mWelcomeText,mWordInput,mNewWord,
                    ButtonsToVbox(null,mTranslate,mClear,mSwap,mInfo));


            mSwap.setOnAction(event1 -> {
                Swap(mNewWord,mWordInput);
            });

            mClear.setOnAction(event1 -> {
                Clear(null,null,null,null,mWordInput,mWordInputText,mNewWord,mNewWordText);
            });

            mTranslate.setOnAction(event2 -> {
                String mWord = mWordInput.getText(); //save input from TextField
                List<String> mOutputList = new ArrayList<String>(); // Empty list for the new word
                File mMorseInternational = new File("MorseInternational.txt"); //ERROR!! "A" doesn't work with UTF-8.
                HashMap <String,String> mList = new HashMap<String, String>(); //HashMap for Morse alphabet

                try {
                    BufferedReader mBR = new BufferedReader(new FileReader(mMorseInternational)); //read file
                    String mLine = mBR.readLine(); //read line from file

                    if (mWord.equals("")){
                        mNewWord.setText("");
                    } else if (mWord.matches("^[/. -]+$")){ //Translate Morse to Latin
                        String[] mWordList = mWord.split("\\s+"); //Words from sentence to list
                        int mLettersInWord = mWordList.length; //elements in list

                        FileToHashMap(true,mLine,mList,mBR);

                        for (int i = 0; i < mLettersInWord; i++) {
                            String mLetter = mWordList[i];
                            if (mWordList[i].equals("/")) {
                                mOutputList.add(i," "); //Replace "/" with a whitespace
                            } else if (mList.containsKey(mLetter)==false){
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

                        FileToHashMap(false,mLine,mList,mBR);

                        for (int i = 0; i < mLettersInWord; i++) {
                            String mLetter = mWordList.get(i); // find the letter from word
                            if (mList.containsKey(mLetter)== false) { //adding characters that are not in Morse code
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
                    mNewWord.setText(mOutput);

                    mBR.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            mInfo.setOnAction(event1 -> {
                String mHeaderText = "Morse Code";
                String mInfoText = "Morse code is a method of transmitting text information as a series of " +
                        "on-off tones, lights, or clicks that can be directly understood by a skilled listener " +
                        "or observer without special equipment. The International Morse Code encodes the ISO " +
                        "basic Latin alphabet, some extra Latin letters, the Arabic numerals and a small set " +
                        "of punctuation and procedural signals (prosigns) as standardized sequences of short and " +
                        "long signals called \"dots\" " +
                        "and \"dashes\", or \"dits\" and \"dahs\". Because many non-English natural languages " +
                        "use more than the 26 Roman letters, extensions to the Morse alphabet exist for those " +
                        "languages.\n \nEach Morse code symbol represents either a text character (letter or " +
                        "numeral) or a prosign and is represented by a unique sequence of dots and dashes. The " +
                        "duration of a dash is three times the duration of a dot. Each dot or dash is followed " +
                        "by a short silence, equal to the dot duration. The letters of a word are separated by a " +
                        "space equal to three dots (one dash), and the words are separated by a space equal to " +
                        "seven dots. The dot duration is the basic unit of time measurement in code transmission. " +
                        "To increase the speed of the communication, the code was designed so that the length of " +
                        "each character in Morse varies approximately inversely to its frequency of occurrence in " +
                        "English. Thus the most common letter in English, the letter \"E\", has the shortest code, " +
                        "a single dot.\n \nThis translator uses the international Morse alphabet. The letters are " +
                        "separated by a whitespace \" \" and words are separated by a slash \"/\". If any letters " +
                        "don't exist in the alphabet, the translator will replace it with \"#\".";
                String mButtonTitle = "International Morse alphabet";
                Button mAlphabet = new Button(mButtonTitle);
                Info(mHeaderText,mInfoText,true,mAlphabet);

                mAlphabet.setOnAction(event2 -> {
                    InfoButton(mButtonTitle, "LatinMorse.jpg");
                });
            });
        });
    }

    private ArrayList<String> CheckUppercase(int LettersInWord, ArrayList<String> OutputList, String UserInput) {
        for (int i = 0; i < LettersInWord; i++) {
            char Char = OutputList.get(i).charAt(0);
            if (Character.isUpperCase(UserInput.charAt(i))){
                OutputList.remove(i);
                char NewChar = Character.toUpperCase(Char);
                String aNewString = Character.toString(NewChar);
                OutputList.add(i,aNewString);
            }
        }
        return OutputList;
    }

    private void SetPromptText(TextField Field1,String Text1, TextField Field2,String Text2,
                               TextField Field3, String Text3) {
        Field1.setFocusTraversable(false);
        Field1.clear();
        if (Field3 == null){
            if (Field2 == null){
                Field1.setPromptText(Text1);
            } else {
                Field2.clear();
                Field2.setFocusTraversable(false);
                Field1.setPromptText(Text1);
                Field2.setPromptText(Text2);
            }
        } else {
            Field2.clear();
            Field3.clear();
            Field2.setFocusTraversable(false);
            Field3.setFocusTraversable(false);
            Field1.setPromptText(Text1);
            Field2.setPromptText(Text2);
            Field3.setPromptText(Text3);
        }
    }

    private HashMap<String, String> FileToHashMap(boolean isMorse,String Line, HashMap<String, String> List,BufferedReader BR)
            throws IOException {
        while (Line != null) {
            String[] mTempList = Line.split("\\s"); //save key + value to array
            String Letter = mTempList[0]; //save letter (key)
            String Morse = mTempList[1]; //save morse code of letter (value)
            if (isMorse == true){
                List.put(Morse, Letter); //add key + value to HashMap
            } else {
                List.put(Letter,Morse); //add key + value to HashMap
            }
            Line = BR.readLine(); //read next line
        }
        return List;
    }

    private void WelcomeTextStyle(Label WelcomeText) {
        WelcomeText.setFont(Font.font(null, FontWeight.BOLD,20));
    }

    private void WarningTextStyle(Label Warning) {
        Warning.setFont(Font.font(null, FontPosture.ITALIC,14));
        Warning.setTextFill(Color.RED);
    }

    private void WordNotEditable(TextField NewWord) {
        NewWord.setEditable(false);
        NewWord.setStyle("-fx-background-color:" + TextBackgroundColor);
    }

    private void PaneStyle(VBox pane) {
        pane.setStyle("-fx-background-image: url("+ BackgroundPicture + ")");
        pane.setAlignment(Pos.CENTER); // aligning main view text to center of the window
    }

    private void ReturnToMainView() {
        mainStage.setScene(mainScene);
        mainStage.setTitle("Ciphers");
        mainStage.show();
    }

    private void ChangeABC(ChoiceBox LanguageABC,TextField ABCinput) {
        //replace alphabet if language is chosen from drop down menu
        LanguageABC.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (Alphabets[newValue.intValue()].equals(ABCinputText)){
                    SetPromptText(ABCinput,ABCinputText,null,null,null,null);
                } else {
                    ABCinput.setText(Alphabets[newValue.intValue()]);
                }
            }
        });
    }

    private ChoiceBox DefineABC(ChoiceBox LanguageABC) {
        LanguageABC.setTooltip(Choose); //dispay text if mouse is hovering over the button
        LanguageABC.getSelectionModel().selectFirst(); //dispays the first selectin by default
        return LanguageABC;
    }

    private VBox ButtonsToVbox(ChoiceBox Button1,Button Button2,Button Button3,Button Button4,Button Button5) {
        VBox subPane = new VBox();

        HBox Hbox1 = new HBox(vBoxPadding);
        HBox Hbox2 = new HBox(vBoxPadding);
        HBox Hbox3 = new HBox(vBoxPadding);

        if (Button1 == null) {
            Hbox1.getChildren().addAll(Button2,Button3);
            Hbox2.getChildren().addAll(Button4,Button5);
            Hbox3.getChildren().addAll(ReturnButton);
        } else {
            Hbox1.getChildren().addAll(Button1,Button2);
            Hbox2.getChildren().addAll(Button3,Button4);
            Hbox3.getChildren().addAll(Button5,ReturnButton);
        }

        Hbox1.setAlignment(Pos.CENTER);
        Hbox1.setTranslateY(vBoxPadding*5);
        Hbox2.setAlignment(Pos.CENTER);
        Hbox2.setTranslateY(vBoxPadding*5);
        Hbox3.setAlignment(Pos.CENTER);
        Hbox3.setTranslateY(vBoxPadding*5);

        subPane.getChildren().addAll(Hbox1,Hbox2,Hbox3);
        return subPane;
    }

    private void ButtonsSameSize(ChoiceBox Button1,Button Button2,Button Button3,Button Button4,Button Button5) {
        ReturnButton.setMaxWidth(buttonWidth);
        HBox.setHgrow(ReturnButton,Priority.ALWAYS);

        if (Button1 == null) {
            Button[] Buttons = new Button[4];
            Buttons[0] = Button2;
            Buttons[1] = Button3;
            Buttons[2] = Button4;
            Buttons[3] = Button5;

            for (int i = 0; i < 4; i++) {
                Buttons[i].setMaxWidth(buttonWidth);
                HBox.setHgrow(Buttons[i],Priority.ALWAYS);
            }
        } else {
            Button1.setMaxWidth(buttonWidth);
            Button2.setMaxWidth(buttonWidth);
            Button3.setMaxWidth(buttonWidth);
            Button4.setMaxWidth(buttonWidth);
            Button5.setMaxWidth(buttonWidth);

            HBox.setHgrow(Button1,Priority.ALWAYS);
            HBox.setHgrow(Button2,Priority.ALWAYS);
            HBox.setHgrow(Button3,Priority.ALWAYS);
            HBox.setHgrow(Button4,Priority.ALWAYS);
            HBox.setHgrow(Button5,Priority.ALWAYS);
        }
    }

    private void InfoButton(String Title, String Filename) {
        Image Picture = new Image("file:" + Filename);
        double PictureWidth = Picture.getWidth();
        double PictureHeigth = Picture.getHeight();

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

    private void Info(String HeaderText,String InfoText,boolean Scroll, Button Picture) {
        VBox HelpPane = new VBox();
        HelpPane.setPadding(new Insets(vBoxPadding));
        ScrollPane ScrollPane = new ScrollPane(HelpPane); //pane that can be scrolled
        ScrollPane.setFitToWidth(true);
        Scene HelpScene;

        if (Scroll == true) { //check if window needs to be scrolled or not
            HelpScene = new Scene(ScrollPane,width, height/2);
        } else {
            HelpScene = new Scene(HelpPane,width, height/2);
        }

        Stage HelpStage = new Stage(); // info opens in new window
        HelpStage.setTitle("Info");
        HelpStage.setScene(HelpScene);
        HelpStage.setResizable(false);
        HelpStage.getIcons().add(new Image("file:questionmark.png"));
        HelpStage.show();

        Label HelpTextHeader = new Label(HeaderText);
        WelcomeTextStyle(HelpTextHeader);
        Label HelpText = new Label();
        HelpText.setText(InfoText);
        HelpText.setPrefWidth(width);
        HelpText.setWrapText(true);
        HelpText.setTextAlignment(TextAlignment.JUSTIFY);

        HelpPane.setStyle("-fx-background-image: url("+ BackgroundPicture + ")"); //add background

        if (Picture == null){ //content of pane without buttons
            HelpPane.getChildren().addAll(HelpTextHeader, HelpText);
        } else { //content of pane with buttons
            HelpPane.getChildren().addAll(HelpTextHeader, HelpText, Picture);
        }
    }

    private void Clear(ChoiceBox LanguageABC,TextField ABCinput,TextField StepInput,String StepInputText,
                       TextField WordInput,String WordInputText,TextField NewWord,String NewWordText) {
        if (StepInput == null) {
            if (ABCinput == null){ //if step and alphabet don't exist
                SetPromptText(WordInput,WordInputText,null,null,null,null);
                NewWord.setText(NewWordText);
            } else { //if step doesn't exist
                SetPromptText(ABCinput,ABCinputText,WordInput,WordInputText,null,null);
                NewWord.setText(NewWordText);
                LanguageABC.getSelectionModel().selectFirst();
            }
        } else { //if everything exists
            SetPromptText(ABCinput,ABCinputText,WordInput,WordInputText,StepInput,StepInputText);
            NewWord.setText(NewWordText);
            LanguageABC.getSelectionModel().selectFirst();
        }
    }

    private void Swap(TextField NewWord, TextField WordInput) {
        String CipherWord = NewWord.getText();
        WordInput.setText(CipherWord);
    }
}
