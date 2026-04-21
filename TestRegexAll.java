
import java.util.Scanner;
import java.util.regex.*;

public class TestRegexAll {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a line (words, numbers, etc):");
        String input = sc.nextLine();

        // 1) matches() - full-string match using original pig-latin pattern
        Pattern fullPattern = Pattern.compile("([^aeiouAEIOU]*)(.*)");
        Matcher fullM = fullPattern.matcher(input);
        System.out.println("\n== matches() demo ==");
        if (fullM.matches()) {
            System.out.println("Full match OK. groupCount=" + fullM.groupCount());
            System.out.println("group(1): " + fullM.group(1));
            System.out.println("group(2): " + fullM.group(2));
        } else {
            System.out.println("matches() returned false for entire input.");
        }

        // 2) lookingAt() - checks prefix of the input
        Pattern digitsPrefix = Pattern.compile("\\d+");
        Matcher prefM = digitsPrefix.matcher(input);
        System.out.println("\n== lookingAt() demo ==");
        if (prefM.lookingAt()) {
            System.out.println("Input starts with digits: " + prefM.group()
                    + " (start=" + prefM.start() + ", end=" + prefM.end() + ")");
        } else {
            System.out.println("Input does not start with digits.");
        }

        // 3) find() - find all digit sequences and words
        Pattern digits = Pattern.compile("\\d+");
        Matcher dMatcher = digits.matcher(input);
        System.out.println("\n== find() demo (digits) ==");
        while (dMatcher.find()) {
            System.out.println("Found digits='" + dMatcher.group() + "' start=" + dMatcher.start() + " end=" + dMatcher.end());
        }

        Pattern words = Pattern.compile("\\b\\w+\\b");
        Matcher wMatcher = words.matcher(input);
        System.out.println("\n== find() demo (words + group info) ==");
        while (wMatcher.find()) {
            String w = wMatcher.group();
            System.out.println("Word: '" + w + "' at [" + wMatcher.start() + "," + wMatcher.end() + "]");
            // you can create an inner matcher to test matches() on the single word
            Matcher single = fullPattern.matcher(w);
            if (single.matches()) {
                System.out.println("  (as single word) group1='" + single.group(1) + "', group2='" + single.group(2) + "'");
            }
        }

        // 4) replaceAll() - simple vowel masking
        Pattern vowels = Pattern.compile("[aeiouAEIOU]");
        String masked = vowels.matcher(input).replaceAll("*");
        System.out.println("\n== replaceAll() demo ==");
        System.out.println("Vowels masked: " + masked);

        // 5) Transform words to Pig Latin using replaceAll on a word pattern
        // pattern: word starting with consonant cluster then rest of word
        Pattern pigPattern = Pattern.compile("\\b([^aeiouAEIOU\\W]+)([a-zA-Z]+)\\b");
        Matcher pigMatcher = pigPattern.matcher(input);
        String pigAll = pigMatcher.replaceAll("$2$1ay"); // $2 = rest, $1 = leading consonants
        System.out.println("\n== pig latin via replaceAll ==");
        System.out.println(pigAll);

        // 6) appendReplacement/appendTail (same pig latin but showing the API)
        Matcher m2 = pigPattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (m2.find()) {
            String repl = m2.group(2) + m2.group(1) + "ay";
            m2.appendReplacement(sb, repl);
        }
        m2.appendTail(sb);
        System.out.println("\n== pig latin via appendReplacement/appendTail ==");
        System.out.println(sb.toString());

        sc.close();
    }
}
