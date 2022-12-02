import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        System.out.println(Pattern.matches("(\\s+\\w+)", "        "));
        System.out.println(Pattern.matches("(\\s+\\w+)", "        a"));
        System.out.println(Pattern.matches("(\\s*\\w*)", "        a"));
        System.out.println(Pattern.matches("(\\s*\\w*)", "        "));
    }
}
