package ru.stqa.pft.sandboxRennovative;

import java.util.Arrays;
import java.util.List;

public class Collection {

    public static void main (String[] args) {
        String[] langs = {"Java", "CH", "Python", "PHP"};
        List<String> languages = Arrays.asList("Java", "C#", "Python");

        for (String l: languages) {
            System.out.println("Print language: --  " + l);

        }
    }
}
