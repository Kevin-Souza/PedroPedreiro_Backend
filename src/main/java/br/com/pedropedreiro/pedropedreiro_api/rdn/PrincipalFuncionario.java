package br.com.pedropedreiro.pedropedreiro_api.rdn;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.pedropedreiro.pedropedreiro_api.Models.Endereco;
import br.com.pedropedreiro.pedropedreiro_api.Models.Funcionario;

public class PrincipalFuncionario {
    public static void main(String[] args) throws SQLException {
        //INSTANCIAR A CLASSE DE REGRA DE NEGÓCIOS
        // ===== Inserir
        FuncionarioRdn funRdn = new FuncionarioRdn();

        Calendar dtNascimento3 = Calendar.getInstance();
        dtNascimento3.set(Calendar.YEAR, 2000);
        dtNascimento3.set(Calendar.MONTH, Calendar.MARCH);
        dtNascimento3.set(Calendar.DATE, 10);

        Funcionario funcionario = new Funcionario();

        funcionario.setNome("Amilton Queiroz");
        funcionario.setTelefone("11 979126");
        funcionario.setEmail("amilton@hotmail.com");
        funcionario.setDocumento("69128959733");
        funcionario.setDataNascimento(dtNascimento3);
        funcionario.setSalario(4.000);
        funcionario.setPis("456412");
        funcionario.setId(1);

        Endereco endereco = new Endereco();
        endereco.setBairro("Jardim Oliveira");
        endereco.setCep("1450032");
        endereco.setCidade("Ribeirão Preto");
        endereco.setComplemento("Casa");
        endereco.setNumero("362");
        endereco.setLogradouro("Rua da Consolação");
        endereco.setUf("SP");

        funcionario.setEndereco(endereco);

        System.out.println("-----------------------");
        System.out.println("Testando inserir funcionário");

        funRdn.inserir(funcionario);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirFuncionario();

        System.out.println("-----------------------");

        // ===== Alterar
        System.out.println("Testando alterar");

        //OBJETO CLIENTE
        Funcionario forAlterar = funRdn.obterPorId(1);

        forAlterar.setNome("Almilto Queiroz");
        forAlterar.getEndereco().setLogradouro("Rua Logo Ali");

        //preencher com todos os atributos
        int retAlterar = funRdn.alterar(forAlterar);

        System.out.println("Número de linhas afetadas: " + retAlterar);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirFuncionario();

        // ===== Deletar
        System.out.println("TESTANDO DELETAR");

        System.out.println("-----------------------");
        int retorno = funRdn.excluir(2);
        System.out.println("Número de linhas pelo delete: " + retorno);
        System.out.println("-----------------------");

        //IMPRIMIR CLIENTE
        imprimirFuncionario();
    }
    // ===== Imprimir
    public static void imprimirFuncionario() {
        ArrayList<Funcionario> lstFuncionario = new ArrayList<Funcionario>();

        lstFuncionario = new FuncionarioRdn().obterTodos();

        //PARA CADA CLIENTE DA LISTA DE CLIENTES
        for (Funcionario forn : lstFuncionario) {
            System.out.println("Funcionario:" + forn.getId());
            System.out.println("Nome: " + forn.getNome());
            System.out.println("Telefone:" + forn.getTelefone());
            System.out.println("Email:" + forn.getEmail());
            System.out.println("Documento: " + forn.getDocumento());
            System.out.println("Salário: " + forn.getSalario());
            System.out.println("PIS: " + forn.getPis());

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
