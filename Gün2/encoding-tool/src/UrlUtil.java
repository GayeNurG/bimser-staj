import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlUtil {

    public static String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    public static String decode(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }
}