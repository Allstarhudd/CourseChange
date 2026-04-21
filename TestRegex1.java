
import java.util.Scanner;
import java.util.regex.*;

public class TestRegex1 {

    public static void main(String args[]) {
        Pattern p = Pattern.compile("([^aeiou]*)(.*)");
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter a word");
            String word = sc.nextLine();
            Matcher m = p.matcher(word);
            if (m.matches()) {
                System.out.println("match for group 1: " + m.group(1));
                System.out.println("match for group 2: " + m.group(2));
                System.out.println("The whole match: " + m.group());
                System.out.println("Display a Pig Latin: " + m.group(2) + m.group(1));
            } else {
                System.out.println("No match");
            }
        }
    }
}
