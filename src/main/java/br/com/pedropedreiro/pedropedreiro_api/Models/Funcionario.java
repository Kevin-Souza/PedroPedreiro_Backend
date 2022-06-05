package br.com.pedropedreiro.pedropedreiro_api.Models;

import java.util.Calendar;

public class Funcionario extends Pessoa{
    // ========== Atributos
    private Double salario;
    private String pis;

    // ========== Construtor
    public Funcionario(){}

    public Funcionario(String pNome,  String pDocumento){
        super(pNome, pDocumento);
    };

    public Funcionario(String pNome, String pTelefone, String pDocumento, Calendar pDataNascimento, Endereco pEndereco){
        super(pNome, pTelefone, pDocumento, pDataNascimento, pEndereco);
    };

    // ========== Gets e Sets
    // ===== Salário
    public double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    // ===== Pis
    public String getPis() {
        return pis;
    }
    public void setPis(String pis) {
        this.pis = pis;
    }
}
