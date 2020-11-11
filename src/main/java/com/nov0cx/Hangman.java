package com.nov0cx;

import com.nov0cx.utils.ConsoleColor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Getter
@Setter
public class Hangman {

    @SneakyThrows
    public Hangman() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //looking for the path of the text-file with the list
        String path = "C:/Users/nikla/Desktop/Hangman/depend/list.txt";
        System.out.println("Write the path to the list and after that 'next'");
        String line;
        while (true) {
            line = reader.readLine();
            if(line.equals("next"))
                break;
            path = (line);
        }

        //adding the list of the file in the list
        File file = new File(path);
        ArrayList<String> strings = new ArrayList<>();
        FileReader reader1 = new FileReader(file);
        BufferedReader reader2 = new BufferedReader(reader1);
        String l;
        while ((l = reader2.readLine()) != null) {
            strings.add(l);
        }

        //start of the game
        String c = strings.get(new Random().nextInt(strings.size() + 1)).toLowerCase();
        System.out.println(ConsoleColor.GREEN + "The possibilities are: " + Arrays.toString(strings.toArray()) + ConsoleColor.RESET);
        System.out.println(ConsoleColor.GREEN + "The word is " + c.toCharArray().length + " characters long." + ConsoleColor.RESET);

        //the number of wrong guesses
        int wrong = 0;
        //the maximum of wrong guesses
        int max = 10;

        //game loop
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
