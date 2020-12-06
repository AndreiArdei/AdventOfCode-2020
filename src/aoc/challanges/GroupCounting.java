package aoc.challanges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GroupCounting {

    public Map<Integer, List<String>> groups = new HashMap<>();

    public static void main(String[] args) {
        GroupCounting gc = new GroupCounting();
        gc.readLines("C:\\Users\\Andrei-PC\\IdeaProjects\\AOC2020\\src\\aoc\\util\\day6-1.txt");
        System.out.println(gc.getSum());

        System.out.println(gc.getSumEveryone());
    }

    /**
     * Read all answers. Each answer is added to a list of answers per group.
     * @param filename - the name of the file to look after.
     */
    public void readLines(String filename) {
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            List<String> answersPerGroup = new ArrayList<>();
            while (reader.hasNext()) {
                String answer = reader.nextLine();

                if(answer.isEmpty()) {
                    groups.put(groups.size()+1, answersPerGroup);
                    answersPerGroup = new ArrayList<>();
                    continue;
                }

                answersPerGroup.add(answer);
            }
            groups.put(groups.size()+1, answersPerGroup);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Concatenate together every string in the list and then select distinct chars and count them
     * @param input - list of all separate answers.
     * @return - the number of distinct answers given.
     */
    public int  countAnswers(List<String> input) {
        return (int) String.join("", input).chars().distinct().count();
    }

    /**
     * Go through each line and keep track of how many instances of a char you found
     * @param input - List of strings
     * @return - the number of chars.
     */
    public int countAnswersEveryone(List<String> input) {
        int[] charsAnswered = new int[Character.MAX_VALUE];
        for(String s : input) {
            for (int i = 0; i < s.length(); i++) {
                charsAnswered[s.charAt(i)]++;
            }
        }

        int count = 0;
        for (int j : charsAnswered) {
            if (j == input.size()) {
                count++;
            }
        }

        return count;
    }

    /**
     * Goes through each line for each group and counts distinct chars.
     * @return - sum of all chars
     */
    public int getSum() {
        int sum = 0;
        for(List<String> list : groups.values()) {
            sum += countAnswers(list);
        }

        return sum;
    }

    /**
     * Same as above but for second challenge
     * @return
     */
    public int getSumEveryone() {
        int sum = 0;
        for(List<String> list : groups.values()) {
            sum += countAnswersEveryone(list);
        }

        return sum;
    }

}
