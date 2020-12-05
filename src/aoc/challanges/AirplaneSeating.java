package aoc.challanges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AirplaneSeating {
    private final List<Integer> seatIDs = new ArrayList<>();
    private final List<String> boardings = new ArrayList<>();

    public static void main(String[] args) {
        AirplaneSeating as = new AirplaneSeating();
        as.readBoardings("..\\src\\aoc\\util\\day5-1.txt");
        System.out.println(as.findSeat());
    }

    /**
     * Reads all boardings from a given file and inserts them into an array list
     * @param filename - the name or path to the file
     */
    public void readBoardings(String filename) {
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while(reader.hasNext()) {
                boardings.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the row given a boarding pass
     * @param boardingPass - the boarding pass string
     * @return - the row where this seat is located
     */
    public int getRow(String boardingPass) {
        int start = 0, stop = 127, mid = (start+stop)/2;

        for(char c : boardingPass.toCharArray()) {
            if(c == 'R' || c == 'L')
                continue;

            if(c == 'B') {
                start = mid;
                mid = (start+stop)/2;
            }

            if(c == 'F') {
                stop = mid;
                mid = (start+stop)/2;
            }
        }
        //System.out.println("Boarding " + boardingPass +" ,row : " + (mid+1));
        return mid+1;
    }

    /**
     * Calculates the column given a boarding pass
     * @param boardingPass - the boarding pass string
     * @return - the column where this seat is located
     */
    public int getColumn(String boardingPass) {
        int start = 0, stop = 8, mid = (start+stop)/2;

        for(char c : boardingPass.toCharArray()) {
            if(c == 'B' || c == 'F')
                continue;

            if(c == 'R') {
                start = mid;
                mid = (start+stop)/2;
            }

            if(c == 'L') {
                stop = mid;
                mid = (start+stop)/2 ;
            }
        }
        //System.out.println("Boarding " + boardingPass +" ,column : " + mid);
        return mid;
    }

    /**
     * Calculates the seat ID for a given boarding pass
     * @param boardingPass - the boarding pass string
     * @return - the ID of the seat.
     */
    public int getID(String boardingPass) {
        int row = getRow(boardingPass);
        int column = getColumn(boardingPass);

        return row * 8 + column;
    }

    /**
     * Calculates all the seat IDs and returns the highest seat ID
     * @return - the highes seat ID
     */
    public int doBoardings() {
        for(String board : boardings) {
            seatIDs.add(getID(board));
            //System.out.println(" ");
        }

        return Collections.max(seatIDs);
    }

    /**
     * Loops through the list of seats and finds the missing seat
     * @return - the missing seat of the "player"
     */
    public int findSeat() {
        doBoardings();
        Collections.sort(seatIDs);

        int prevID = seatIDs.get(0);
        for(int i = 1; i < seatIDs.size(); i++) {
            int id = seatIDs.get(i);

            if(id - prevID != 1) {
                return (id+prevID)/2;
            }
            prevID = id;
        }
        return 0;
    }


}
