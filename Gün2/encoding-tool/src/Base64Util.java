import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    public static String encode(String text) {

        return Base64.getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String encodedText) {

        byte[] decodedBytes =
                Base64.getDecoder().decode(encodedText);

        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

}