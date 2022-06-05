package br.com.pedropedreiro.pedropedreiro_api.Models;

import java.util.Calendar;
// import java.util.Date;

public class Fornecedor extends Pessoa{
    // ========== Atributos
    private String razaoSocial;
    private String inscricaoEstadual;
    private String categoria;
    private String tipoProduto;

    // ========== Construtor
    public Fornecedor(){}
    
    public Fornecedor(String pNome,  String pDocumento){
        super(pNome, pDocumento);
    };

    public Fornecedor(String pNome, String pTelefone, String pDocumento, Calendar pDataNascimento, Endereco pEndereco){
        super(pNome, pTelefone, pDocumento, pDataNascimento, pEndereco);
    };

    // ========== Gets e Sets
    // ===== Razão Social
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    // ===== Inscrição Estadual
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    // ===== Categoria
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // ===== Tipo Produto
    public String getTipoProduto() {
        return tipoProduto;
    }
    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
