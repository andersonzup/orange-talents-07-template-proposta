package br.com.zup.proposta.exception;

public class ErroFormResponseArgument {


    private String erro;

    public ErroFormResponseArgument(String erro) {

        this.erro = erro;
    }


    public String getErro() {
        return erro;
    }
}
