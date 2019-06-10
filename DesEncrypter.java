package desencrypter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DesEncrypter {
    
    Cipher ecipher,dcipher;
    
    DesEncrypter(SecretKey key) throws Exception{
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE,key);
        dcipher.init(Cipher.DECRYPT_MODE,key);
    }
    //This method will encrypt the text
    public String encrypt (String str) throws Exception{
        byte[] Data = str.getBytes("UTF8");
        byte[] enc = ecipher.doFinal(Data);
        return new sun.misc.BASE64Encoder().encode(enc);
    }
    
    //This method decrypts the text
    public String decrypt(String str) throws Exception{
        byte []dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
        byte []Data = dcipher.doFinal(dec);
        return new String(Data,"UTF8");
    }
    


    public static void main(String[] args) throws Exception {
        
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        
        DesEncrypter encrypter = new DesEncrypter(key);
        
        String encrypted = encrypter.encrypt("information");
        System.out.println("Encrypted text: "+encrypted);
        
        String decrypted = encrypter.decrypt(encrypted);
        System.out.println("Decryption: "+decrypted);
    }

}
