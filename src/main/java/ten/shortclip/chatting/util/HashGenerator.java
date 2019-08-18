package ten.shortclip.chatting.util;

import javax.xml.bind.*;
import java.security.*;
//싱글톤까지는 할 필요 없다고 생각(
public class HashGenerator {
    private HashGenerator() { }

    public static String generate(String Algorithm, byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(Algorithm);
        md.update(bytes);
        return DatatypeConverter.printHexBinary(md.digest())
                                .toLowerCase();
    }
}
