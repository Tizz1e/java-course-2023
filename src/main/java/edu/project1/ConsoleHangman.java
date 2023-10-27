package edu.project1;

import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleHangman {
    private ConsoleHangman() {
    }

    public static void run(Session session) {
        char[] initialState = session.getState();
        System.out.println("> The word: " + new String(initialState));

        Scanner in = new Scanner(System.in);
        GuessResult lastGuess = null;

        do {
            if (in.hasNextLine()) {

                String guess = in.nextLine().strip();

                if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
                    continue;
                }

                lastGuess = session.guess(guess.charAt(0));
                lastGuess.print();

            } else {
                lastGuess = session.giveUp();
                lastGuess.print();
                break;
            }
        } while (!(lastGuess instanceof Defeat) && !(lastGuess instanceof Win));
    }

    public static void run() {
        run(new Session());
    }
}
