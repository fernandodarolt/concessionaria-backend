package br.com.senac.concessionaria.concessionariabackendapi.dto;

public class ModeloResponse {
    private Long id;
    private String nome;
    private MarcaResponse marca;

    private PlacaResponse placa;

    public ModeloResponse() {
    }

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

    public MarcaResponse getMarca() {
        return marca;
    }

    public void setMarca(MarcaResponse marca) {
        this.marca = marca;
    }

    public PlacaResponse getPlaca() {
        return placa;
    }

    public void setPlaca(PlacaResponse placa) {
        this.placa = placa;
    }

    public ModeloResponse(Long id, String nome, MarcaResponse marca, PlacaResponse placa) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.placa = placa;
    }

    public ModeloResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
