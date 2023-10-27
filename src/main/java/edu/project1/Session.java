package edu.project1;

import java.util.Arrays;

public class Session {
    private final String answer;
    private final char[] state;
    private static final int MAX_ATTEMPTS = 8;
    private int attempts;

    private int guessed = 0;

    public Session() {
        answer = new GameDictionary().randomWord();
        state = new char[answer.length()];
        Arrays.fill(state, '*');
        attempts = 0;
        guessed = 0;
    }

    public char[] getState() {
        return state;
    }

    public GuessResult guess(char guess) {
        int index = answer.indexOf(guess);

        if (index == -1 || state[index] != '*') {
            attempts++;
            if (attempts == MAX_ATTEMPTS) {
                return new Defeat(answer);
            } else {
                return new FailedGuess(state, attempts, MAX_ATTEMPTS);
            }
        }

        while (index != -1) {
            state[index] = guess;
            guessed++;
            index = answer.indexOf(guess, index + 1);
        }

        if (guessed == answer.length()) {
            return new Win(state);
        } else {
            return new SuccessfulGuess(state);
        }
    }

    public GuessResult giveUp() {
        return new Defeat(answer);
    }

    @Override
    public int hashCode() {
        return attempts * 1337 + (guessed ^ answer.hashCode()) * 5;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Session) {
            Session otherSession = (Session) other;
            return answer.equals(otherSession.answer) && attempts == otherSession.attempts
                && guessed == otherSession.guessed;
        }
        return false;
    }
}
