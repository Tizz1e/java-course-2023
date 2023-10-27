package edu.project1;

@SuppressWarnings("RegexpSinglelineJava")
public record Defeat(String answer) implements GuessResult {
    @Override
    public void print() {
        System.out.println("> You lost!");
    }
}
