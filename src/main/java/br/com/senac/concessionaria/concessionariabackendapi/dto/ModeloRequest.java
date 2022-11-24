package br.com.senac.concessionaria.concessionariabackendapi.dto;

public class ModeloRequest {
    private Long id;
    private String nome;
    private Long idMarca;
    private Long idPlaca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public Long getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(Long idPlaca) {
        this.idPlaca = idPlaca;
    }
}
