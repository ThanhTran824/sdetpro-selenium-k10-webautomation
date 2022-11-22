package utils;

public class Verifier {

    public static void equals(int num1, int num2) {
        if (num1 != num2) {
            throw new RuntimeException("Expected is " + num1 + " but actual result is " + num2);
        }

    }
}
