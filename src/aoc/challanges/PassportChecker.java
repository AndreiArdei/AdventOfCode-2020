package aoc.challanges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PassportChecker {
    private static final String[] CODES = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private static final String[] POSSIBLE_CODES = { "cid"};
    private static final String[] EYE_COLORS = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
    private final List<Map<String, String>> passports = new ArrayList<>();


    public static void main(String[] args) {
        PassportChecker pck = new PassportChecker();
        pck.readPassports("C:\\Users\\Andrei-PC\\IdeaProjects\\AOC2020\\src\\aoc\\util\\day4-1.txt");
        System.out.println(pck.countValidExtended());
    }

    public void readPassports(String filename){
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            Map<String, String > pass = new HashMap<>();
            while (reader.hasNext()) {
                String codesString = reader.nextLine();
                if(codesString.isEmpty()) {
                    passports.add(pass);
                    pass = new HashMap<>();
                } else {
                    String[] codes = codesString.split(" ");
                    for(String s : codes){
                        String[] code = s.split(":");
                        pass.put(code[0], code[1]);
                    }
                }
            }
            passports.add(pass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int countValid(){
        int passportNr = 0;

        for(Map<String, String> pass : passports) {
            //If there are 6 or less codes, denote as invalid.
            if (pass.size() < CODES.length) {
                continue;
            }

            //If there are exactly 8, max number of codes, passport is good to go
            if(pass.size() == CODES.length + POSSIBLE_CODES.length) {
                passportNr++;
                continue;
            }

            //If there are 7 codes, check to see that they are all the correct ones.
            boolean valid = true;
            for (String code : CODES){
                if (!pass.containsKey(code)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                passportNr++;
            }
        }
        return passportNr;
    }

    public  int countValidExtended() {
        int passportNr = 0;

        for(Map<String, String> pass : passports) {
            //If there are 6 or less codes, denote as invalid.
            if (pass.size() < CODES.length) {
                continue;
            }

            //If there are 7 codes, check to see that they are all the correct ones.
            boolean valid = true;
            for (String code : CODES){
                if (!pass.containsKey(code)) {
                    valid = false;
                    break;
                } else {
                    if (!validate(code, pass.get(code))) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid) {
                passportNr++;
            }
        }
        return passportNr;
    }

    public boolean validate(String key, String value) {
        switch (key) {
            case "byr" : {
                return value.matches("[0-9]+") && value.length() == 4 && Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002;
            }
            case "iyr" : {
                return value.matches("[0-9]+") && value.length() == 4 && Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;
            }
            case "eyr" : {
                return value.matches("[0-9]+") && value.length() == 4 && Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;
            }
            case "ecl" : {
                return Arrays.asList(EYE_COLORS).contains(value);
            }
            case "pid" : {
                return value.matches("[0-9]+") && value.length() == 9;
            }
            case "hcl" : {
                return value.matches("^#(?:[0-9a-f]{3}){1,2}$");
            }
            case "hgt" : {
                if(!value.contains("cm") && !value.contains("in")) {
                    return false;
                }
                if(value.contains("cm")) {
                    String height = value.replaceAll("cm", "");
                    return (Integer.parseInt(height) >= 150 && Integer.parseInt(height) <= 193);
                }
                if(value.contains("in")) {
                    String height = value.replaceAll("in", "");
                    return (Integer.parseInt(height) >= 59 && Integer.parseInt(height) <= 76);
                }
            }
            default: return false;
        }
    }
}
