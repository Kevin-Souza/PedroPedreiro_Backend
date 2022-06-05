package br.com.pedropedreiro.pedropedreiro_api.rdn;

// import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.pedropedreiro.pedropedreiro_api.Models.Cliente;
import br.com.pedropedreiro.pedropedreiro_api.Models.Endereco;

public class PrincipalCliente {
    public static void main (String[] args) throws SQLException{
        //TESTAR CONEXÃO -- Deixar comentado
//            Connection conn = (Connection) new ConnectionFactory().getConnection();
//            System.out.println("Conexão aberta");
//            conn.close();

        //INSTANCIAR A CLASSE DE REGRA DE NEGÓCIOS
        // ===== Inserir
        ClienteRdn cliRdn = new ClienteRdn();

        Calendar dtNascimento1 = Calendar.getInstance();
        dtNascimento1.set(Calendar.YEAR, 1990);
        dtNascimento1.set(Calendar.MONTH, Calendar.DECEMBER);
        dtNascimento1.set(Calendar.DATE, 25);

        Cliente cliente = new Cliente();
        cliente.setNome("Alfredo Constancio");
        cliente.setTelefone("18 96548-9546");
        cliente.setDataNascimento(dtNascimento1);
        cliente.setEmail("alfredinho@hotmail.com");
        cliente.setDocumento("56548951232");
        cliente.setNumeroCartaoFidelidade("654897");
        cliente.setId(1);

        Endereco endereco = new Endereco();
        endereco.setBairro("Ribeirania");
        endereco.setCep("1450032");
        endereco.setCidade("Ribeirão Preto");
        endereco.setComplemento("Casa");
        endereco.setNumero("302");
        endereco.setLogradouro("Rua Tudo Junto e Misturado");
        endereco.setUf("SP");

        cliente.setEndereco(endereco);

        System.out.println("-----------------------");
        System.out.println("Testando inserir");

        cliRdn.inserir(cliente);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirCliente();

        System.out.println("-----------------------");

        // ===== Alterar
        System.out.println("Testando alterar");

        //OBJETO CLIENTE
        Cliente cliAlterar = cliRdn.obterPorId(1);

        cliAlterar.setNome("Alfredinho");
        cliAlterar.getEndereco().setLogradouro("Rua Logo Ali");

        //preencher com todos os atributos
        int retAlterar = cliRdn.alterar(cliAlterar);

        System.out.println("Número de linhas afetadas: " + retAlterar);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirCliente();

        // ===== Deletar
        System.out.println("TESTANDO DELETAR");

        System.out.println("-----------------------");
        int retorno = cliRdn.excluir(2);
        System.out.println("Número de linhas pelo delete: " + retorno);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirCliente();

    }

    // ===== Imprimir
    public static void imprimirCliente() {

        ArrayList<Cliente> lstCliente = new ArrayList<Cliente>();

        lstCliente = new ClienteRdn().obterTodos();

        //PARA CADA CLIENTE DA LISTA DE CLIENTES
        for (Cliente cli : lstCliente) {
            System.out.println("Cliente:" + cli.getId());
            System.out.println("Nome: " + cli.getNome());
            System.out.println("Telefone:" + cli.getTelefone());
            System.out.println("Email:" + cli.getEmail());
            System.out.println("Documento: " + cli.getDocumento());

            DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Data de nascimento: " + formataData.format(cli.getDataNascimento().getTime()));
            System.out.println("Nº Cartão Fidelidade:" + cli.getNumeroCartaoFidelidade());

            //ACESSAR OS ATRIBUTOS DO ENDEREÇO
            System.out.println("Logradouro: " + cli.getEndereco().getLogradouro());
            System.out.println("Nº:" + cli.getEndereco().getNumero());
            System.out.println("Bairro: " + cli.getEndereco().getBairro());

            System.out.println("Complemento: " + cli.getEndereco().getComplemento());
            System.out.println("Cidade: " + cli.getEndereco().getCidade());
            System.out.println("UF: " + cli.getEndereco().getUf());

            System.out.println("-----------------------");

        }
    }
}
