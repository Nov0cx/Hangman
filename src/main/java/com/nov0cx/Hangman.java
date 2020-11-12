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
        String path = "C:/Users/nikla/Desktop/JavaExample/depend/list.txt";
        System.out.println("Write the path to the list and after that 'next'");
        String line;
        while (true) {
            line = reader.readLine();
            if (line.equals("next"))
                break;
            path = (line);
            System.out.println(ConsoleColor.GREEN + "Set path to the list as " + ConsoleColor.AQUA + line + ConsoleColor.GREEN + "." + ConsoleColor.RESET);
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
        System.out.println(ConsoleColor.GREEN + "The word is " + c.toCharArray().length + " characters long." + ConsoleColor.RESET);

        //the number of wrong guesses
        int wrong = 0;
        //the maximum of wrong guesses
        int max = 10;

        //guess chars
        ArrayList<Character> guessed = new ArrayList<>();
        ArrayList<Character> allGuessed = new ArrayList<>();

        int cLength = 0;
        ArrayList<Character> got = new ArrayList<>();
        for (Character car : c.toCharArray()) {
            if (!got.contains(car)) {
                cLength++;
                got.add(car);
            }

        }

        StringBuilder b = new StringBuilder();
        String realPattern = "";
        for(Character car : c.toCharArray()) {
            b.append("_");
        }
        realPattern = b.toString();
        System.out.println(ConsoleColor.GREEN + realPattern + ConsoleColor.RESET);

        //game loop
        while (guessed.size() != cLength) {
            String read = reader.readLine().toLowerCase();
            if (read.length() != 1) {
                System.out.println(ConsoleColor.RED + "You can only guess one character at once!" + ConsoleColor.RESET);
                continue;
            }
            if(allGuessed.contains(read.toCharArray()[0])) {
                System.out.println(ConsoleColor.GREEN + "You already guessed that character." + ConsoleColor.RESET);
                continue;
            }

            if (read.equals(c))
                break;
            if (c.contains(read)) {
                System.out.println(ConsoleColor.GREEN + read + " is part of the word." + ConsoleColor.RESET);
                if (!guessed.contains(read.toCharArray()[0]))
                    guessed.add(read.toCharArray()[0]);
                StringBuilder b2 = new StringBuilder();
                for(Character car : c.toCharArray()) {
                    if(car == read.toCharArray()[0] || guessed.contains(car))
                        b2.append(car);
                    else b2.append("_");
                }
                realPattern = b2.toString();
            } else {
                wrong++;
                System.out.println(ConsoleColor.RED + read
                        + " isn't part of the word. You have " + wrong
                        + " wrong guesses out of max " + max + " wrong guesses." + ConsoleColor.RESET);
            }
            System.out.println(ConsoleColor.GREEN + "You already guessed " + Arrays.toString(guessed.toArray()) + "." + ConsoleColor.RESET);
            System.out.println(ConsoleColor.GREEN + realPattern + ConsoleColor.RESET);
            allGuessed.add(read.toCharArray()[0]);
            if (wrong > max - 1)
                break;
        }
        if (wrong > max - 1)
            System.out.println(ConsoleColor.RED + "You are out of guesses. The word was " + c + "." + ConsoleColor.RESET);
        else
            System.out.println(ConsoleColor.GREEN + "You won! The word was " + c + ". You had " + wrong + " wrong guesses." + ConsoleColor.RESET);
    }

    public static void main(String[] args) {
        new Hangman();
    }


}
