package br.com.zup.proposta.utils;


import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@Service
public class UtilMethods {

    public static void validacaoCartaoId(String id) throws MalformedURLException {
        if(id.length() < 19){
            throw new MalformedURLException("Número do cartão inválido");
        }
    }

    public static String validaIp(String ipAddress, HttpServletRequest servletRequest) {
        if (ipAddress == null) {
            ipAddress = servletRequest.getRemoteAddr();
            return ipAddress;
        }
        return "0.0.0.0";
    }

}
