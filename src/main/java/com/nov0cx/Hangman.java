package com.nov0cx;

import com.nov0cx.utils.ConsoleColor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

@Getter
@Setter
public class Hangman {

    @SneakyThrows
    public Hangman() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = new String[]{
                "Apfel",
                "Banane",
                "Zitrone",
                "Limette",
                "Wassermelone",
                "Drachenfrucht",
                "Blauebeere",
                "Himmbeere",
                "Johannisbeere",
                "Erdbeere",
                "Kirsche",
                "Badewanne",
                "Fahrt",
                "Protokolle",
                "Drehen",
                "Prophylaktisch",
                "Karikatur"
        };
        String c = strings[new Random().nextInt(strings.length)].toLowerCase();
        System.out.println(ConsoleColor.GREEN + "The possibilities are: " + Arrays.toString(strings) + ConsoleColor.RESET);
        System.out.println(ConsoleColor.GREEN + "The word is " + c.toCharArray().length + " characters long." + ConsoleColor.RESET);
        int wrong = 0;
        int max = 10;
        while (true) {
            String read = reader.readLine().toLowerCase();
            if (wrong > max)
                break;
            if (read.equals(c))
                break;
            if (c.contains(read)) {
                System.out.println(ConsoleColor.GREEN + read + " is part of the word." + ConsoleColor.RESET);
            } else {
                wrong++;
                System.out.println(ConsoleColor.RED + read
                        + " isn't part of the word. You have " + wrong
                        + " wrong guesses out of max " + max + " wrong guesses." + ConsoleColor.RESET);

            }
        }
        if (wrong > max)
            System.out.println(ConsoleColor.RED + "You are out of guesses." + ConsoleColor.RESET);
        else System.out.println(ConsoleColor.GREEN + "You won! The word was " + c + ". You needed " + wrong + " guesses." + ConsoleColor.RESET);
    }

    public static void main(String[] args) {
        new Hangman();
    }


}
