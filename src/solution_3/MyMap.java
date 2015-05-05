/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution_3;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author R2-D2
 */
public class MyMap {

    private Map<String, ArrayList<Pair>> map = new TreeMap<String, ArrayList<Pair>>();
    private String[] key;
    // create special map
    //key = element our array
    //value = pair elemene 
    //              string = word which contains in this key
    //              int = count this word (which contains in this key)

    public MyMap(String[] ArrayWord) {
        key = ArrayWord;
        //init arraylist
        for (int i = 0; i < ArrayWord.length; i++) {
            map.put(ArrayWord[i], new ArrayList<Pair>());
        }
        //find word which containing key
        /*
         example:
         String[] mass = {
         "f",
         "five",
         "fivetwo",
         "one",
         "onefiveone",
         "two"
         };
         map[0] => f->[]
         map[1] => five->[(f:1)]
         map[2] => fivetwo->[(f:1)(five:1)(two:1)]
         map[3] => one->[]
         map[4] => onefiveone->[(f:1)(five:1)(one:2)]
         map[5] => two->[]*/
        for (int i = 0; i < ArrayWord.length; i++) {
            for (int j = 0; j < ArrayWord.length; j++) {
                if (i != j) {
                    int count = 0;
                    if (ArrayWord[i].contains(ArrayWord[j])) {
                        String str = ArrayWord[i];
                        // find count word which contains in this key
                        while (str.contains(ArrayWord[j])) {
                            str = str.replaceFirst(ArrayWord[j], "-");
                            count++;
                        }
                        Pair help = new Pair(ArrayWord[j], count);
                        map.get(ArrayWord[i]).add(help);
                    }

                }
            }
        }
    }

    public String getCompoundWord() {
        String word = "";
        //check have we compound word or not 
        if (isHaveCompoundWords()) {
            /*remove Unique Elements of the words are found*/
            deleteContainingWord();
            /* find index element*/
            int index = findIndexCompoundWord();
            //return -1 if we have no word which compound just with other words array
            try {
                word = key[findIndexCompoundWord()];
                return word;
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Have no word which compound just with other words array");
            }
        } else {
            return "Have no word which compound with other words array, just unique element";
        }

        return key[findIndexCompoundWord()];
    }

    private void deleteContainingWord() {
        /*
         update map
         after procedure we would have map like this           
         String[] mass = {
         "f",
         "five",
         "fivetwo",
         "one",
         "onefiveone",
         "two"
         };
         map[0] => f->[]
         map[1] => five->[(f:1)]
         map[2] => fivetwo->[(f:1)(ive:1)(two:1)]
         map[3] => one->[]
         map[4] => onefiveone->[(f:1)(ive:1)(one:2)]
         map[5] => two->[]
         */
        for (int i = 0; i < map.size(); i++) {
            if (map.get(key[i]).size() > 0) {
                ArrayList<Pair> tmp = map.get(key[i]);
                for (int j = tmp.size() - 1; j >= 0; j--) {
                    for (int k = tmp.size() - 1; k >= 0; k--) {
                        if (k != j) {
                            // if word contains other word2, remove part word
                            if (tmp.get(j).getName().contains(tmp.get(k).getName())) {
                                String s = tmp.get(j).getName().replace(tmp.get(k).getName(), "");
                                tmp.get(j).setName(s);
                            }
                        }
                    }
                }
                map.put(key[i], tmp);
            }
        }
    }
    //find max element
    private int findIndexCompoundWord() {
        int indexMaxCompaneWord = -1;
        int maxCompaneWordLenght = 0;
        for (int i = 0; i < map.size(); i++) {
            if (map.get(key[i]).size() > 0) {
                ArrayList<Pair> tmp = map.get(key[i]);
                int currentWordLenght = 0;
                //get element array list, and find lenght
                for (int j = 0; j < tmp.size(); j++) {
                    if (!tmp.get(j).getName().isEmpty()) {
                        currentWordLenght += tmp.get(j).getName().length() * tmp.get(j).getCount();
                    }
                }
                //if lenght < lenght key -> the word is not compoun just with the other words 
                if (currentWordLenght == key[i].length()) {
                    //find max compoun word
                    if (maxCompaneWordLenght < currentWordLenght) {
                        maxCompaneWordLenght = currentWordLenght;
                        indexMaxCompaneWord = i;
                    }
                }
            }
        }
        return indexMaxCompaneWord;
    }
    //if all elements unique return false, else true
    private boolean isHaveCompoundWords() {
        boolean isHaveCompoundWords = false;
        for (int i = 0; i < map.size(); i++) {
            if (map.get(key[i]).size() > 0) {
                isHaveCompoundWords = true;
                break;
            }
        }
        return isHaveCompoundWords;
    }
}
