package br.com.zup.proposta.biometria;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cartaoid;

    private String fingerprint;

    private LocalDateTime localDateTime = LocalDateTime.now();

    public Biometria() {
    }

    public Biometria(String cartaoid, String fingerprint, LocalDateTime localDateTime) {
        this.cartaoid = cartaoid;
        this.fingerprint = fingerprint;
        this.localDateTime = localDateTime;
    }
    public Biometria(BiometriaRequest request, String cartaoid) {
        this.fingerprint = request.getBiometria();
        this.cartaoid = cartaoid;
    }

    public Long getId() {
        return id;
    }
}
