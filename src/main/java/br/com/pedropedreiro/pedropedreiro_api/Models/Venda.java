package br.com.pedropedreiro.pedropedreiro_api.Models;

import javax.swing.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Venda {
    // ========== Atributos
    private int id;
    private String notaFiscal;
    private Calendar dataVenda;
    private Cliente cliente;
    private double valorVenda;
    private Funcionario funcionario;
    private ArrayList<ItemVenda> itens;

    // Construtor
    public Venda(String notaFiscal, Calendar dataVenda, Cliente cliente, double valorVenda, Funcionario funcionario,
                 ArrayList<ItemVenda>itens){
        this.notaFiscal = notaFiscal;
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.valorVenda = valorVenda;
        this.funcionario = funcionario;
        this.itens = itens;
    }

    // ========== Gets e Sets
    // ===== ID
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // ===== Nota Fiscal
    public String getNotaFiscal() {
        return notaFiscal;
    }
    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    // ===== Data Venda
    public Calendar getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(Calendar dataVenda) {
        this.dataVenda = dataVenda;
    }

    // ===== Cliente
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // ===== Valor Venda
    public double getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    // ===== Funcionário
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    // ===== Item Venda
    public ArrayList<ItemVenda> getItens() {
        return itens;
    }
    public void setItens(ArrayList<ItemVenda> itens) {
        this.itens = itens;
    }

    //  soma do total da venda
    public double TotalVenda(){
        double total = 0;
        for (int i = 0; i < itens.size(); i++) {
            total += itens.get(i).getSubTotal();
        }
        return total;
    }

    // Resumo da conta
    public void imprimirResumo(){
        JOptionPane.showMessageDialog(null,"Pedro Pedreiro \nMateriais para construção" +
                "\nResumo da compra");

        //CONVERTE A DATA NO FORMATO DD/MM/YYYY. EX.: 25/06/2021
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String D = formatter.format(this.getDataVenda());

        /*/*JOptionPane.showMessageDialog(null,"Numero do cupom fiscal: "+this.notaFiscal+"\n" +
                "Data da venda: " + D +"\n Atendente: "+this.funcionario.getNome()+"\n Cliente: "+this.cliente.getNome()
        + "   Fidelidade: "+this.getCliente().getNumeroCartaoFidelidade()+"\n==================================\n" +
                "ITENS\n"+itens.get().getProduto().getDescricaoReduzida());*/

        System.out.println("\nResumo da Compra\n" +
                "\n=========================================\n" +
                "Número do cupom fiscal: "+this.notaFiscal+"\n" +
                "Data da venda: " + D +
                "\nAtendente: "+this.funcionario.getNome() +
                "\nCliente: "+this.cliente.getNome() +
                "   Fidelidade: "+this.getCliente().getNumeroCartaoFidelidade() +
                "\n=========================================\n" +
                "\nITENS");

        // For que percorre o Array de itens e traz as informações dos atributos Produto e DescricaoReduzida
            System.out.println("\n------------------------------------------\n");
        for (int i = 0; i < itens.size(); i++){
            System.out.println("Item: "+itens.get(i).getProduto().getDescricaoReduzida());

            // For que percorre o Array de itens e traz as informações dos atributos nome e Documento do fornecedor
            for(int g = 0; g < itens.get(i).getProduto().getFornecedores().size(); g++){
                Fornecedor f = itens.get(i).getProduto().getFornecedores().get(g);
                System.out.println("Fornecedor: "+f.getNome()+" - "+f.getDocumento());
            }

            System.out.println("Quantidade: "+itens.get(i).getQtd());
            System.out.println("Valor Unitário: "+itens.get(i).getProduto().getPrecoVenda());
            System.out.println("SubTotal: "+itens.get(i).getSubTotal());
            System.out.println("\n------------------------------------------\n");
        }

        System.out.println("=========================================");
        System.out.println("\nTotal: "+this.TotalVenda());
    }
}