package br.com.zup.proposta.exception;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
@RestControllerAdvice
public class ErroValidacaoHeandler {

    private MessageSource messageSource;

    public ErroValidacaoHeandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormResponse> handle(MethodArgumentNotValidException exception){

        List<ErroFormResponse> responseList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(fieldErrors, responseList);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<ErroFormResponse> handle(BindException exception){

        List<ErroFormResponse> responseList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(fieldErrors, responseList);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalBase64Exception.class)
    public List<ErroFormResponseArgument> handle(IllegalBase64Exception exception){

        List<ErroFormResponseArgument> responseList = new ArrayList<>();
        responseList.add(new ErroFormResponseArgument(exception.getMessage()));
        return responseList;

    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(PropostaNotValidException.class)
    public List<ErroFormResponseArgument> handle(PropostaNotValidException exception){

        List<ErroFormResponseArgument> responseList = new ArrayList<>();
        responseList.add(new ErroFormResponseArgument(exception.getMessage()));
        return responseList;

    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public List<ErroFormResponseArgument> handle(UnprocessableEntityException exception){

        List<ErroFormResponseArgument> responseList = new ArrayList<>();
        responseList.add(new ErroFormResponseArgument(exception.getMessage()));
        return responseList;

    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SystemNotAvailableException.class)
    public List<ErroFormResponseArgument> handle(SystemNotAvailableException exception){

        List<ErroFormResponseArgument> responseList = new ArrayList<>();
        responseList.add(new ErroFormResponseArgument(exception.getMessage()));
        return responseList;

    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BloqueioNotValidException.class)
    public List<ErroFormResponseArgument> handle(BloqueioNotValidException exception){

        List<ErroFormResponseArgument> responseList = new ArrayList<>();
        responseList.add(new ErroFormResponseArgument(exception.getMessage()));
        return responseList;

    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MalformedURLException.class)
    public List<ErroFormResponseArgument> handle(MalformedURLException exception){

        List<ErroFormResponseArgument> responseList = new ArrayList<>();
        responseList.add(new ErroFormResponseArgument(exception.getMessage()));
        return responseList;

    }

    private List<ErroFormResponse> buildValidationErrors(List<FieldError> fieldErrors, List<ErroFormResponse> responseList) {
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormResponse erro = new ErroFormResponse(e.getField(), mensagem);
            responseList.add(erro);
        });

        return responseList;
    }
}
