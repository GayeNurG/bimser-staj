import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Java Charset: " + java.nio.charset.Charset.defaultCharset());

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Encoding Tool =====");
            System.out.println("1 - Base64 Encode");
            System.out.println("2 - Base64 Decode");
            System.out.println("3 - URL Encode");
            System.out.println("4 - URL Decode");
            System.out.println("0 - Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {

                case 1:
                    System.out.print("Metin: ");
                    String encodeText = scanner.nextLine();

                    System.out.println("Sonuç: "
                            + Base64Util.encode(encodeText));
                    break;

                case 2:
                    System.out.print("Base64 Metni: ");
                    String decodeText = scanner.nextLine();

                    System.out.println("Sonuç: "
                            + Base64Util.decode(decodeText));
                    break;

                case 3:
                    System.out.print("Metin: ");
                    String urlEncode = scanner.nextLine();

                    System.out.println("Girdi: " + urlEncode);

                    for (char c : urlEncode.toCharArray()) {
                        System.out.println(c + " -> " + (int) c);
                    }

                    System.out.println("Sonuç: " + UrlUtil.encode(urlEncode));
                    break;

                case 4:
                    System.out.print("Encoded URL: ");
                    String urlDecode = scanner.nextLine();

                    System.out.println("Sonuç: "
                            + UrlUtil.decode(urlDecode));
                    break;

                case 0:
                    System.out.println("Program kapatılıyor...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Hatalı seçim!");
            }
        }
    }
}