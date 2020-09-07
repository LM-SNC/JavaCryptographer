package sample;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

class CryptMethods {

    String crypt(String text, String cryptKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");

        SecretKeySpec key = new SecretKeySpec(cryptKey.getBytes(), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = new byte[0];

        bytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(bytes);
    }

    String decrypt(byte[] crypt, String decryptKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher decryptCipher = Cipher.getInstance("AES");

        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "AES");

        decryptCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] dbytes = new byte[0];
        dbytes = decryptCipher.doFinal(Base64.getDecoder().decode(crypt));
        return new String(dbytes, StandardCharsets.UTF_8);
    }
}
