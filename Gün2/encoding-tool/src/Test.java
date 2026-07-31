import java.util.Scanner;
import java.nio.charset.Charset;

public class Test {

    public static void main(String[] args) {

        System.out.println("Java Charset: " + Charset.defaultCharset());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Bir şey yaz: ");

        String text = scanner.nextLine();

        System.out.println("Okunan: " + text);

        scanner.close();
    }
}