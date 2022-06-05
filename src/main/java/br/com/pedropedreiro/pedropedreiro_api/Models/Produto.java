package br.com.pedropedreiro.pedropedreiro_api.Models;

import java.util.ArrayList;

public class Produto {
    // ========== Atributos
    private int id;
    private String nomeProduto;
    private String descricaoReduzida;
    private String descricaoCompleta;
    private String marca;
    private double precoCusto;
    private double precoVenda;
    private double qtd;
    private ArrayList<Fornecedor> fornecedores;

    public Produto(String nomeProduto, String descricaoReduzida, String descricaoCompleta, String marca, double precoCusto, double precoVenda, double qtd, ArrayList<Fornecedor> fornecedores) {
        this.nomeProduto = nomeProduto;
        this.descricaoReduzida = descricaoReduzida;
        this.descricaoCompleta = descricaoCompleta;
        this.marca = marca;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.qtd = qtd;
        this.fornecedores = fornecedores;
    }

    // ========== Gets e Sets
    // ===== ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // ===== Nome do Produto
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    // ===== Descrição Reduzida
    public String getDescricaoReduzida() {
        return descricaoReduzida;
    }
    public void setDescricaoReduzida(String descricaoReduzida) {
        this.descricaoReduzida = descricaoReduzida;
    }

    // ===== Descrição Completa
    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }
    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    // ===== Marca
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    // ===== Preço Custo
    public double getPrecoCusto() {
        return precoCusto;
    }
    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    // ===== Preço Venda
    public double getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    // ===== Quantidade
    public double getQtd() {
        return qtd;
    }
    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    // ===== Fornecedor
    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
