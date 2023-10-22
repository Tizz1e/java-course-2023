package edu.project1;

@SuppressWarnings("RegexpSinglelineJava")
public record FailedGuess(char[] state, int attempt, int maxAttempts) implements GuessResult {
    @Override
    public void print() {
        System.out.println("> Missed, mistake " + attempt + " of " + maxAttempts);
        System.out.println("> The word: " + new String(state));
    }
}
