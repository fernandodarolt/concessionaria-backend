package br.com.senac.concessionaria.concessionariabackendapi.dto;

import java.util.List;

public class MarcaResponse {
    private Long id;
    private String nome;
    private String descricao;
    private List<ModeloResponse> modelos;

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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<ModeloResponse> getModelos() {
        return modelos;
    }
    public void setModelos(List<ModeloResponse> modelos) {
        this.modelos = modelos;
    }

    public MarcaResponse() {

    }
    public MarcaResponse(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    public MarcaResponse(Long id, String nome, String descricao, List<ModeloResponse> modelos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.modelos = modelos;
    }

}
