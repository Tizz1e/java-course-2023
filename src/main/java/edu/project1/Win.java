package edu.project1;

@SuppressWarnings("RegexpSinglelineJava")
public record Win(char[] state) implements GuessResult {
    @Override
    public void print() {
        System.out.println("> Hit!");
        System.out.println("> The word: " + new String(state));
        System.out.println("> You won!");
    }
}
