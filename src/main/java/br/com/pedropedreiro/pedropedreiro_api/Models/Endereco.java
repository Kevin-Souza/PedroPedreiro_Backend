package br.com.pedropedreiro.pedropedreiro_api.Models;

public class Endereco {
    // ========== Atributos
    private int id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String complemento;
    private String numero;
    private String uf;

    public Endereco(){}

    public Endereco(String logradouro, String bairro, String cep, String cidade, String complemento, String numero, String uf) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;
        this.numero = numero;
        this.uf = uf;
    }

    // ========== Gets e Sets
    // ===== ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // ===== Logradouro
    public void setLogradouro(String pLogradouro){
        this.logradouro = pLogradouro;
    }
    public String getLogradouro(){
        return this.logradouro;
    }

    // ===== Bairro
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // ===== CEP
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    // ===== Cidade
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    // ===== Complemento
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    // ===== Numero
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    // ===== UF
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
}
