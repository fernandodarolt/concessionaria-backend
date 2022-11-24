package br.com.senac.concessionaria.concessionariabackendapi.dto;

public class PlacaRequest {
    private Long id;
    private String numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
