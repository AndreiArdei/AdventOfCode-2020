package aoc.challanges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToboganTreeMap {
    private int mapWidth;
    private ArrayList<char[]> map = new ArrayList<>();

    public static void main(String[] args) {
        ToboganTreeMap ttm = new ToboganTreeMap();
        ttm.readLines("C:\\Users\\Andrei-PC\\IdeaProjects\\AOC2020\\src\\aoc\\util\\day3-1.txt");
        System.out.println(ttm.arborealStop());
    }

    public void readLines(String filename) {
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNext()) {
                char[] arr = reader.nextLine().toCharArray();
                map.add(arr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int traverse(int rightSteps, int downSteps) {
        int nrTrees = 0;
        int pos = 0;
        for(int i = downSteps; i < map.size(); i+=downSteps) {
            char[] chars = map.get(i);
            mapWidth = chars.length;
            pos += rightSteps;
            if(pos >= mapWidth){
                pos = pos - mapWidth;
            }
            if(chars[pos] == '#') {
                nrTrees++;
            }
        }

        return nrTrees;
    }

    public long arborealStop() {
        int sl1 = traverse(1,1);
        int sl2 = traverse(3,1);
        int sl3 = traverse(5,1);
        int sl4 = traverse(7,1);
        int sl5 = traverse(1,2);

        long res = 1;
        res *= sl1 != 0 ? sl1 : 1;
        res *= sl2 != 0 ? sl2 : 1;
        res *= sl3 != 0 ? sl3 : 1;
        res *= sl4 != 0 ? sl4 : 1;
        res *= sl5 != 0 ? sl5 : 1;

        return res;
    }

}
