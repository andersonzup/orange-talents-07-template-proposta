package br.com.zup.proposta.utils;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.math.BigInteger;

public class Criptografia {

    AES256TextEncryptor textEncryptor = new AES256TextEncryptor();

    public String encode(@NotBlank String entrada){
        textEncryptor.setPassword("${criptografia.sucurity.password}");
        String meuTextoCriptografado = textEncryptor.encrypt(entrada);
        return meuTextoCriptografado;
    }

    public String decode(String entrada){
        textEncryptor.setPassword("${criptografia.sucurity.password}");
        String meuTextoCriptografado = textEncryptor.decrypt(entrada);
        return meuTextoCriptografado;
    }

    public static String getSHA512(@NotBlank String entrada){
        Assert.hasText(entrada, "Texto nao pode estar em branco");
        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(entrada.getBytes(StandardCharsets.UTF_8));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

}
