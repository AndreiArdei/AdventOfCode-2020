package aoc.challanges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseReport {
    private ArrayList<Integer> integers = new ArrayList<>();


    public static void main(String[] args) {
        ExpenseReport expenseReport = new ExpenseReport();
        expenseReport.readIntegers("..\\src\\aoc\\util\\day1-1.txt");
        System.out.println(expenseReport.comparator(2020));

        System.out.println(expenseReport.comparator2(2020));
    }

    /**
     * Reads and stores all integer values
     * @param filename - the file that should be used
     */
    public void readIntegers(String filename) {
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextInt()) {
                integers.add(reader.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loops through all integers and returns the product if it finds something equal to comparable
     * @param comparable - the number to be compared agains
     * @return - the product of the two numbers that add up
     */
    public int comparator(int comparable) {
        for (int i = 0; i < getInts().size() - 1; i++) {
            for(int j = i+1; j < getInts().size(); j++) {
                if (getInts().get(i) + getInts().get(j) == comparable) {
                    return getInts().get(i) * getInts().get(j);
                }
            }
        }
        return 0;
    }

    /**
     * Loops through all integers and returns the product if it finds something equal to comparable
     * @param comparable - the number to be compared agains
     * @return - the product of the three numbers that add up
     */
    public int comparator2(int comparable) {
        for(int i = 0; i < getInts().size() - 2; i++) {
            for(int j = i+1; j < getInts().size() - 1; j++) {
                for(int k = j+1; k < getInts().size(); k++) {
                    if (getInts().get(i) + getInts().get(k) + getInts().get(j) == comparable) {
                        return getInts().get(i) * getInts().get(k) * getInts().get(j);
                    }
                }
            }
        }
        return 0;
    }

    public ArrayList<Integer> getInts() {
        return this.integers;
    }
}
