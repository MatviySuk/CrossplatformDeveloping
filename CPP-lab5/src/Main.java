import Modifiers.StringModifier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введіть текст для опрацювання.\n");

        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();

        String modifiedText = StringModifier.UppercaseLastCharInWords(scanner.nextLine());

        System.out.println(modifiedText);
    }
}