package ru.ryu.service;

import ru.ryu.entity.Word;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PackageScanner {
    public List<Word> scan() {
        List<Word> words = new ArrayList<>();
        File myFolder = new File("C:\\Тексты\\");
        File[] files = myFolder.listFiles();
        if (files != null) fork(files, words);
        return words;
    }

    private void fork(File[] files, List<Word> words) {
        for (File file : files) {
            if (file.isFile()) {
                fileReader(file, words);
            } else if (file.listFiles() != null) fork(file.listFiles(), words);
        }
    }

    private void fileReader(File file, List<Word> words){
        try (FileReader reader = new FileReader(file)) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                WorkWithWord.splitText(scanner.nextLine())
                        .iterator()
                        .forEachRemaining(s -> setFork(words, file, s.toLowerCase()));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void setFork(List<Word> words, File file, String string) {
        if (words.isEmpty()) {
            setArray(words, file, string);
        } else {
            int count = 0;
            for (Word element : words) {
                if(element.getWord().equals(string)) {
                    count++;
                    element.setCount(element.getCount() + 1);
                }
            }

            if (count == 0) setArray(words, file, string);
        }
    }

    private void setArray(List<Word> words, File file, String newWord) {
        Word word = new Word(file.getParent(), file.getName(), newWord);
        words.add(word);
    }
}
