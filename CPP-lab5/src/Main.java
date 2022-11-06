import Modifiers.StringModifier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст для опрацювання.");

        String text = scanner.nextLine();

        String modifiedText = StringModifier.UppercaseLastCharInWords(text);

        System.out.println(modifiedText);
    }
}