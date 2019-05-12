package ru.ryu.service;

import java.util.Arrays;
import java.util.List;

public class WorkWithWord {
    public static List<String> splitText(String text) {
        text = text.replaceAll(",", "");
        text = text.replaceAll("\\.", "");

        return Arrays.asList(text.split(" "));
    }
}
