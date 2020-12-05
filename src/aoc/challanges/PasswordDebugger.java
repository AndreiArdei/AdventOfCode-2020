package aoc.challanges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordDebugger {
    public ArrayList<String> combinations = new ArrayList<>();

    public static void main(String[] args) {
        PasswordDebugger pwd = new PasswordDebugger();
        pwd.readLines("C:\\Users\\Andrei-PC\\IdeaProjects\\AOC2020\\src\\aoc\\util\\day2-1.txt");
        System.out.println(pwd.counter());
        System.out.println(pwd.counter2());
    }

    public void readLines(String filename) {
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNext()) {
                combinations.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean parser(String input) {
        String[] parsed = input.split(" ");
        String[] possibleLines = parsed[0].split("-");

        int happened = 0;
        char[] chars = parsed[2].toCharArray();
        for(char c : chars) {
            if (c == parsed[1].charAt(0)) {
                happened++;
            }
        }

        return happened >= Integer.parseInt(possibleLines[0]) && happened <= Integer.parseInt(possibleLines[1]);
    }

    public boolean parser2(String input) {
        String[] parsed = input.split(" ");
        String[] possibleLines = parsed[0].split("-");
        int start = Integer.parseInt(possibleLines[0])-1;
        int stop = Integer.parseInt(possibleLines[1])-1;
        if(start >= parsed[2].length() || stop >= parsed[2].length())
            return false;
        char c = parsed[1].charAt(0);
        char one = parsed[2].charAt(start);
        char two = parsed[2].charAt(stop);


        return (c == one && c != two) || (c != one && c == two);
    }

    public int counter() {
        return (int) combinations.stream().filter(this::parser).count();
    }

    public int counter2() {
        return (int) combinations.stream().filter(this::parser2).count();
    }
}
