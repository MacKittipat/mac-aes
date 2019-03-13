package com.mackittipat.aesjava;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

public class App {

    private static int keySize = 128;
    private static int iterationCount = 1000;

    // Ref : https://github.com/mpetersen/aes-example
    public static void main( String[] args ) throws Exception {

        String ciphertext = "PKGKc52M83Ou7WISVJ7OJQAeDS6zvmccZVh2PxdLMWs=";
        String passphrase = "jdsongkran123456";
        String iv = "4b8a08af9adec01996d639fcc41247a3";
        String salt = "a00a42e043b86535483808f847ebb53c";

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), Hex.decodeHex(salt.toCharArray()), iterationCount, keySize);
        SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(Hex.decodeHex(iv.toCharArray())));
        byte[] decrypted = cipher.doFinal(Base64.decodeBase64(ciphertext));

        System.out.println(new String(decrypted, "UTF-8"));

    }

}
