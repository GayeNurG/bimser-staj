import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class EncodingUygulamasi {

    public static void main(String[] args) {

        String orjinalVeri = "Bu veri, sistem uyumluluğu için kodlanacaktır.";

        String encodedVeri = Base64.getEncoder()
                .encodeToString(orjinalVeri.getBytes(StandardCharsets.UTF_8));

        System.out.println("Orijinal Veri: " + orjinalVeri);
        System.out.println("Base64 Encoded Veri: " + encodedVeri);

        System.out.println("--------------------------------");

        byte[] decodedBytes = Base64.getDecoder().decode(encodedVeri);

        String decodedVeri = new String(decodedBytes, StandardCharsets.UTF_8);

        System.out.println("Decode Edilmiş Veri: " + decodedVeri);
    }
}