package br.com.zup.proposta.biometria;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodeDecodeService {

    public static String encode(String value){
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    public static String decode(String encoded){
        String decoded = new String(Base64.getDecoder().decode(encoded));
        return decoded;
    }

}
