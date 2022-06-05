package br.com.pedropedreiro.pedropedreiro_api.rdn;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.pedropedreiro.pedropedreiro_api.Models.Endereco;
import br.com.pedropedreiro.pedropedreiro_api.Models.Fornecedor;

public class PrincipalFornecedor {
    public static void main(String[] args) throws SQLException {
        //INSTANCIAR A CLASSE DE REGRA DE NEGÓCIOS
        // ===== Inserir
        FornecedorRdn forRdn = new FornecedorRdn();

        Calendar dtNascimento2 = Calendar.getInstance();
        dtNascimento2.set(Calendar.YEAR, 1999);
        dtNascimento2.set(Calendar.MONTH, Calendar.FEBRUARY);
        dtNascimento2.set(Calendar.DATE, 15);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("Cleitinho Alexandre");
        fornecedor.setTelefone("11 97598-9126");
        fornecedor.setDataNascimento(dtNascimento2);
        fornecedor.setEmail("cleitinho@hotmail.com");
        fornecedor.setDocumento("69128959733");
        fornecedor.setRazaoSocial("972103");
        fornecedor.setInscricaoEstadual("61.41.2021");
        fornecedor.setCategoria("Tecnologia");
        fornecedor.setTipoProduto("Eletroeletrônico");
        fornecedor.setId(1);

        Endereco endereco = new Endereco();
        endereco.setBairro("Jardim Ipiranga");
        endereco.setCep("1450032");
        endereco.setCidade("Ribeirão Preto");
        endereco.setComplemento("Casa");
        endereco.setNumero("302");
        endereco.setLogradouro("Rua Teodoro Sampaio");
        endereco.setUf("SP");

        fornecedor.setEndereco(endereco);

        System.out.println("-----------------------");
        System.out.println("Testando inserir fornecedor");

        forRdn.inserir(fornecedor);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirFornecedor();

        System.out.println("-----------------------");

        // ===== Alterar
        System.out.println("Testando alterar");

        //OBJETO CLIENTE
        Fornecedor forAlterar = forRdn.obterPorId(1);

        forAlterar.setNome("Alfredinho");
        forAlterar.getEndereco().setLogradouro("Rua Logo Ali");

        //preencher com todos os atributos
        int retAlterar = forRdn.alterar(forAlterar);

        System.out.println("Número de linhas afetadas: " + retAlterar);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirFornecedor();

        // ===== Deletar
        System.out.println("TESTANDO DELETAR");

        System.out.println("-----------------------");
        int retorno = forRdn.excluir(2);
        System.out.println("Número de linhas pelo delete: " + retorno);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirFornecedor();
        }
    // ===== Imprimir
    public static void imprimirFornecedor() {
        ArrayList<Fornecedor> lstFornecedor = new ArrayList<Fornecedor>();

        // ArrayList<Fornecedor> lstFornecedor = new ArrayList<Fornecedor>();

        lstFornecedor = new FornecedorRdn().obterTodos();

        //PARA CADA CLIENTE DA LISTA DE CLIENTES
        for (Fornecedor forn : lstFornecedor) {
            System.out.println("Fornecedor:" + forn.getId());
            System.out.println("Nome: " + forn.getNome());
            System.out.println("Telefone:" + forn.getTelefone());
            System.out.println("Email:" + forn.getEmail());
            System.out.println("Documento: " + forn.getDocumento());
            System.out.println("Razão Social: " + forn.getRazaoSocial());
            System.out.println("Inscrição Estadual: " + forn.getInscricaoEstadual());
            System.out.println("Categoria: " + forn.getCategoria());
            System.out.println("Tipo do Produto: " + forn.getTipoProduto());

            DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Data de nascimento: " + formataData.format(forn.getDataNascimento().getTime()));


            //ACESSAR OS ATRIBUTOS DO ENDEREÇO
            System.out.println("Logradouro: " + forn.getEndereco().getLogradouro());
            System.out.println("Nº:" + forn.getEndereco().getNumero());
            System.out.println("Bairro: " + forn.getEndereco().getBairro());
            System.out.println("Complemento: " + forn.getEndereco().getComplemento());
            System.out.println("Cidade: " + forn.getEndereco().getCidade());
            System.out.println("UF: " + forn.getEndereco().getUf());

            System.out.println("-----------------------");
        }
    }
}