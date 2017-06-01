package canady_bitparity;

import java.util.*;

public class Canady_BitParity {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    static Scanner s = new Scanner(System.in);
    static ArrayList<int[]> phrase = new ArrayList();
    static ArrayList<int[]> check = new ArrayList();

    public static void main(String[] args) {
        System.out.println("Would you like to A) create or B) insert a parity?");
        String input = s.nextLine().toUpperCase();
        if (input.equals("A")) {
            init();
            create();
            for (int[] letters : phrase) {
                print(letters);
            }
        } else if (input.equals("B")) {
            insert();
            check();
        }
    }

    public static void print(int[] letters) {
        for (int i : letters) {
            System.out.print(i + "");
        }
        System.out.println("");
    }

    public static void print(int[] letters, ArrayList<Integer> x) {
        for (int i = 0; i < letters.length; i++) {
            boolean out = false;
            for (Integer j : x) {
                if (i == j) {
                    out = true;
                }
            }
            if (!out) {
                System.out.print(letters[i] + "");
            } else {
                System.out.print(ANSI_RED + letters[i] + "" + ANSI_RESET);
            }
        }
        System.out.println("");
    }

    public static void init() {
        System.out.println("How many characters are there?");
        int chars = s.nextInt();
        for (int i = 0; i < chars; i++) {
            System.out.println("What is the ASCII value for character " + (i + 1) + "?");
            int value = s.nextInt();
            int[] letterParity = new int[11];
            letterParity[10] = value % 10;
            value /= 10;
            letterParity[9] = value % 10;
            value /= 10;
            letterParity[8] = value % 10;
            value /= 10;
            letterParity[6] = value % 10;
            value /= 10;
            letterParity[5] = value % 10;
            value /= 10;
            letterParity[4] = value % 10;
            value /= 10;
            letterParity[2] = value % 10;
            phrase.add(letterParity);
        }
    }

    public static void create() {
        for (int[] letter : phrase) {
            //Check bit 1
            if ((letter[2] + letter[4] + letter[6] + letter[8] + letter[10]) % 2 == 0) {
                letter[0] = 0;
            } else {
                letter[0] = 1;
            }
            //Check bit 2
            if ((letter[2] + letter[5] + letter[6] + letter[9] + letter[10]) % 2 == 0) {
                letter[1] = 0;
            } else {
                letter[1] = 1;
            }
            //Check bit 4
            if ((letter[5] + letter[6]) % 2 == 0) {
                letter[3] = 0;
            } else {
                letter[3] = 1;
            }
            //Check bit 8
            if ((letter[8] + letter[9] + letter[10]) % 2 == 0) {
                letter[7] = 0;
            } else {
                letter[7] = 1;
            }
        }
    }

    public static void insert() {
        System.out.println("How many characters are there?");
        int chars = s.nextInt();
        for (int i = 0; i < chars; i++) {
            System.out.println("What is the parity value for character " + (i + 1) + "?");
            int value = s.nextInt();
            int[] letterParity = new int[11];
            letterParity[10] = value % 10;
            value /= 10;
            letterParity[9] = value % 10;
            value /= 10;
            letterParity[8] = value % 10;
            value /= 10;
            letterParity[7] = value % 10;
            value /= 10;
            letterParity[6] = value % 10;
            value /= 10;
            letterParity[5] = value % 10;
            value /= 10;
            letterParity[4] = value % 10;
            value /= 10;
            letterParity[3] = value % 10;
            value /= 10;
            letterParity[2] = value % 10;
            value /= 10;
            letterParity[1] = value % 10;
            value /= 10;
            letterParity[0] = value % 10;
            phrase.add(letterParity);
        }
    }

    public static void check() {
        for (int i = 0; i < phrase.size(); i++) {
            check.add(new int[11]);
            for (int j : phrase.get(i)) {
                check.get(i)[j] = phrase.get(i)[j];
            }
            //Check bit 1
            if ((phrase.get(i)[2] + phrase.get(i)[4] + phrase.get(i)[6] + phrase.get(i)[8] + phrase.get(i)[10]) % 2 == 0) {
                check.get(i)[0] = 0;
            } else {
                check.get(i)[0] = 1;
            }
            //Check bit 2
            if ((phrase.get(i)[2] + phrase.get(i)[5] + phrase.get(i)[6] + phrase.get(i)[9] + phrase.get(i)[10]) % 2 == 0) {
                check.get(i)[1] = 0;
            } else {
                check.get(i)[1] = 1;
            }
            //Check bit 4
            if ((phrase.get(i)[4] + phrase.get(i)[5] + phrase.get(i)[6]) % 2 == 0) {
                check.get(i)[3] = 0;
            } else {
                check.get(i)[3] = 1;
            }
            //Check bit 8
            if ((phrase.get(i)[8] + phrase.get(i)[9] + phrase.get(i)[10]) % 2 == 0) {
                check.get(i)[7] = 0;
            } else {
                check.get(i)[7] = 1;
            }
        }

        for (int i = 0; i < phrase.size(); i++) {
            int wrong = 0;
            ArrayList<Integer> bbb = new ArrayList<Integer>();
            //Check bit 1
            if (phrase.get(i)[0] != check.get(i)[0]) {
                wrong++;
                bbb.add(0);
            }
            //Check bit 2
            if (phrase.get(i)[1] != check.get(i)[1]) {
                wrong++;
                bbb.add(1);
            }
            //Check bit 4
            if (phrase.get(i)[3] != check.get(i)[3]) {
                wrong++;
                bbb.add(3);
            }
            //Check bit 8
            if (phrase.get(i)[7] != check.get(i)[7]) {
                wrong++;
                bbb.add(7);
            }
            ArrayList<Integer> wrongs = new ArrayList<Integer>();
            ArrayList<Integer> checks = new ArrayList<Integer>();
            checks.add(0);
            checks.add(1);
            checks.add(3);
            checks.add(7);
            if (wrong == 0) {
                print(phrase.get(i));
            } else if (wrong == 1) {
                print(phrase.get(i), bbb);
            } else {
                if (bbb.contains(0) && bbb.contains(1)) {
                    wrongs.add(2);
                }
                if (bbb.contains(0) && bbb.contains(3)) {
                    wrongs.add(4);
                }
                if (bbb.contains(1) && bbb.contains(3)) {
                    wrongs.add(5);
                }
                if (bbb.contains(0) && bbb.contains(1) && bbb.contains(3)) {
                    wrongs.add(6);
                }
                if (bbb.contains(0) && bbb.contains(7)) {
                    wrongs.add(8);
                }
                if (bbb.contains(1) && bbb.contains(7)) {
                    wrongs.add(9);
                }
                if (bbb.contains(0) && bbb.contains(1) && bbb.contains(7)) {
                    wrongs.add(10);
                }
                print(phrase.get(i), wrongs);
            }
        }
    }
}
