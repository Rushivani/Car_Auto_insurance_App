package utility;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

public class HashPassword {
    
    private static final int itrns = 15*1500;
    private static final int sltLen = 32;
    private static final int dsrKey = 256;

    
    public static String getHashedPasswordString(String pwd) throws Exception {
        byte[] slt = SecureRandom.getInstance("SHA1PRNG").generateSeed(sltLen);
        // store the salt with the password
        return Base64.encodeBase64String(slt) + "$" + hash(pwd, slt);
    }

    
    public static boolean CheckIfPasswordCorrect(String pwd, String fromDb) throws Exception{
        String[] sltNdHash = fromDb.split("\\$");
        if (sltNdHash.length != 2) {
            throw new IllegalStateException(
                "The password in the database has to be in certain format 'salt$hash'");
        }
        String hashOfInput = hash(pwd, Base64.decodeBase64(sltNdHash[0]));
        return hashOfInput.equals(sltNdHash[1]);
    }

    
    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, itrns, dsrKey));
        return Base64.encodeBase64String(key.getEncoded());
    }
}
