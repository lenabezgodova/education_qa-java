package ru.qa.test.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args){
        //String[] langs = new String[4];
        //langs[0] = "Java";
        //langs = new String[]{"Java", "Python", "PHP", "1C"};

        String[]langs = {"Java", "Python", "PHP", "1C"};

        for (String recordFromMassiv : langs){
            System.out.println(recordFromMassiv);
        }

        List<String> languages = new ArrayList<String>();
        languages.add("new lang");
        languages.add(0, "java");

        for (String recordFromMassiv : languages){
            System.out.println(recordFromMassiv);
        }


        List<String> listNew = Arrays.asList("Java", "Python", "PHP", "1C"); //list read only
        //listNew.add("Kotlin");
        listNew.size();
        listNew.get(0);
        System.out.println(listNew.get(0));


    }





}
