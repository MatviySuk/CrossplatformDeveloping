import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class StringModifier {
    @Test
    public void testUppercaseLastCharInWords() {
        String text = "This is a sample text. This. text will be used as a dummy? " +
                "for, \"various\" RegEx \"operations\" using Java";
        final String expectedText = "ThiS iS A samplE texT. ThiS. texT wilL bE useD aS A dummY? " +
                "foR, \"variouS\" RegEX \"operationS\" usinG JavA";

        String resultText = Modifiers.StringModifier.UppercaseLastCharInWords(text);

        Assertions.assertEquals(expectedText, resultText);
    }
}
