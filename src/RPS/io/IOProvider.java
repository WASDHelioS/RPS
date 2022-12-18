package RPS.io;

import RPS.game.objects.Choice;
import RPS.rules.RuleSet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOProvider {
    private final Scanner inputScanner;

    public IOProvider() {
        this.inputScanner = new Scanner(System.in);
    }

    public Choice readChoicePromptFromString(String write, RuleSet ruleSet) {
        this.write(write);
        try {
            Choice c = Choice.get(readSecretString());
            if(!ruleSet.getChoices().contains(c)) {
                throw new IllegalArgumentException();
            }
            return c;
        } catch (IllegalArgumentException e) {
            this.write("Invalid choice!");
            return readChoicePromptFromString(write, ruleSet);
        }
    }

    public Integer writeReadPromptForInt(String write) {
        this.write(write);
        try {
            return readInt();
        } catch (InputMismatchException e) {
            this.write("Not a number!");
            inputScanner.next();
            return writeReadPromptForInt(write);
        }
    }

    public String readString() {
        return inputScanner.next();
    }

    public String readSecretString() {
        if(System.console() == null) {
            System.out.println("Type your move : ");
            return readString();
        } else {
            return new String(System.console().readPassword("Type your move: "));
        }
    }

    public Integer readInt() {
        return inputScanner.nextInt();
    }

    public void write(String s) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);
        System.out.flush();
    }
}
