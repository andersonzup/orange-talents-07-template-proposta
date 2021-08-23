package br.com.zup.proposta.avisoviagem;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validoAte;
    private String destino;
    private String ipAddress;
    private String userAgent;


    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDate validoAte, String destino, String ipAddress, String userAgent) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }


    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Aviso{" +
                "id=" + id +
                ", validoAte=" + validoAte +
                ", destino='" + destino + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
