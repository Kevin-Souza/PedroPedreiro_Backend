package br.com.pedropedreiro.pedropedreiro_api.Models;

import java.util.Calendar;
// import java.util.Date;

public abstract class Pessoa {
    // ========== Atributos
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String documento;
    private Calendar dataNascimento;
    private Endereco endereco;

    // ========== Construtor
    public Pessoa(){}

    public Pessoa(String pNome,  String pDocumento){
        this.nome = pNome;
        this.documento = pDocumento;
    };

    public Pessoa(String pNome, String pTelefone, String pDocumento, Calendar pDataNascimento, Endereco pEndereco){
        this.nome = pNome;
        this.telefone = pTelefone;
        this.documento = pDocumento;
        this.dataNascimento = pDataNascimento;
        this.endereco = pEndereco;
    }

    // ========== Sets e Gets
    // ===== ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // ===== Nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // ===== Telefone
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // ===== E-mail
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // ===== Documento
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    // ===== Data Nascimento
    public Calendar getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // ===== Endereço
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
