package br.com.pedropedreiro.pedropedreiro_api.rdn;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.pedropedreiro.pedropedreiro_api.Models.Endereco;
import br.com.pedropedreiro.pedropedreiro_api.Models.Funcionario;

public class FuncionarioRdn {
    public int inserir(Funcionario funcionario) throws SQLException{

        StringBuilder sql = new StringBuilder();
        int linhasAfetadas = 0;

        sql.append("INSERT INTO funcionario                           ");
        sql.append("            (nome                                 ");
        sql.append("            ,telefone                             ");
        sql.append("            ,email                                ");
        sql.append("            ,documento                            ");
        sql.append("            ,data_nascimento                      ");
        sql.append("            ,salario                              ");
        sql.append("            ,pis                                  ");
        sql.append("            ,logradouro                           ");
        sql.append("            ,bairro                               ");
        sql.append("            ,cep                                  ");
        sql.append("            ,cidade                               ");
        sql.append("            ,complemento                          ");
        sql.append("            ,numero                               ");
        sql.append("            ,uf)                                  ");
        sql.append("        VALUES                                    ");
        sql.append("              (?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?                                  ");
        sql.append("              ,?)                                 ");

        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql.toString());

        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getTelefone());
        stmt.setString(3, funcionario.getEmail());
        stmt.setString(4, funcionario.getDocumento());

        stmt.setDate(5, new java.sql.Date(funcionario.getDataNascimento().getTimeInMillis()));

        stmt.setDouble(6, funcionario.getSalario());
        stmt.setString(7, funcionario.getPis());

        stmt.setString(8, funcionario.getEndereco().getLogradouro());
        stmt.setString(9, funcionario.getEndereco().getBairro());
        stmt.setString(10, funcionario.getEndereco().getCep());
        stmt.setString(11, funcionario.getEndereco().getCidade());
        stmt.setString(12, funcionario.getEndereco().getComplemento());
        stmt.setString(13, funcionario.getEndereco().getNumero());
        stmt.setString(14, funcionario.getEndereco().getUf());

        linhasAfetadas = stmt.executeUpdate();

        System.out.print("linhas afetadas: " + linhasAfetadas);

        stmt.close();
        conn.close();

        return linhasAfetadas;
    }

    public ArrayList<Funcionario> obterTodos() {

        ArrayList<Funcionario> retorno = new ArrayList<Funcionario>();

        try {

            StringBuilder str = new StringBuilder();

            str.append("select  a.id_funcionario               ");
            str.append("        ,a.nome                        ");
            str.append("        ,a.telefone                    ");
            str.append("        ,a.email                       ");
            str.append("        ,a.documento                   ");
            str.append("        ,a.data_nascimento             ");
            str.append("        ,a.salario                     ");
            str.append("        ,a.pis                         ");
            str.append("        ,a.logradouro                  ");
            str.append("        ,a.bairro                      ");
            str.append("        ,a.cep                         ");
            str.append("        ,a.complemento                 ");
            str.append("        ,a.numero                      ");
            str.append("        ,a.uf                          ");
            str.append("        ,a.cidade                      ");
            str.append(" from funcionario a                    ");

            //ABRE A CONEXÃO
            Connection conn = new ConnectionFactory().getConnection();

            //CRIAR NOSSO STATEMENT
            Statement stmt = conn.createStatement();

            //RECEBER OS DADOS NO RESULTSET
            ResultSet rs = stmt.executeQuery(str.toString());

            //PERCORRE TODOS OS REGISTROS DO RESULT SET
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));

                //CONVERTER SQL DATE TO CALENDAR
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getDate("data_nascimento"));

                funcionario.setDataNascimento(calendar);
                funcionario.setEmail(rs.getString("email"));
                funcionario.setDocumento(rs.getString("documento"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setPis(rs.getString("pis"));

                //POPULAR/CARREGAR OS ATRIBUTOS DO ENDEREÇO
                Endereco end = new Endereco();
                end.setLogradouro(rs.getString("logradouro"));
                end.setNumero(rs.getString("numero"));
                end.setBairro(rs.getString("bairro"));
                end.setCep(rs.getString("cep"));
                end.setCidade(rs.getString("cidade"));
                end.setUf(rs.getString("uf"));
                end.setComplemento(rs.getString("complemento"));

                //INCLUIR O OBJETO ENDEREÇO NA ATRIBUTO DO FUNCIONARIO
                funcionario.setEndereco(end);

                //ADICIONAR O OBJETO FUNCIONARIO NA LISTA DE FUNCIONARIOS
                retorno.add(funcionario);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retorno;
    }

    public Funcionario obterPorId(int id) {

        Funcionario funcionario = new Funcionario();

        try {

            StringBuilder str = new StringBuilder();

            str.append("select  a.id_funcionario                   ");
            str.append("        ,a.nome                        ");
            str.append("        ,a.telefone                    ");
            str.append("        ,a.data_nascimento             ");
            str.append("        ,a.email                       ");
            str.append("        ,a.documento                   ");
            str.append("        ,a.salario                     ");
            str.append("        ,a.pis                         ");
            str.append("        ,a.logradouro                  ");
            str.append("        ,a.bairro                      ");
            str.append("        ,a.cep                         ");
            str.append("        ,a.complemento                 ");
            str.append("        ,a.numero                      ");
            str.append("        ,a.uf                          ");
            str.append("        ,a.cidade                      ");
            str.append(" from funcionario a                        ");
            str.append(" where a.id_funcionario = ?                ");

            //ABRE A CONEXÃO
            Connection conn = new ConnectionFactory().getConnection();

            //CRIAR NOSSO STATEMENT
            PreparedStatement stmt = conn.prepareStatement(str.toString());

            //PASSAR O PARAMETRO DE ID
            stmt.setInt(1, id);

            //RECEBER OS DADOS NO RESULTSET
            ResultSet rs = stmt.executeQuery();

            //PERCORRE TODOS OS REGISTROS DO RESULT SET
            while (rs.next()) {
                //Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));

                //CONVERTER SQL DATE TO CALENDAR
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getDate("data_nascimento"));

                funcionario.setDataNascimento(calendar);
                funcionario.setEmail(rs.getString("email"));
                funcionario.setDocumento(rs.getString("documento"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setPis(rs.getString("pis"));

                //POPULAR/CARREGAR OS ATRIBUTOS DO ENDEREÇO
                Endereco end = new Endereco();
                end.setLogradouro(rs.getString("logradouro"));
                end.setNumero(rs.getString("numero"));
                end.setBairro(rs.getString("bairro"));
                end.setCep(rs.getString("cep"));
                end.setCidade(rs.getString("cidade"));
                end.setUf(rs.getString("uf"));
                end.setComplemento(rs.getString("complemento"));

                //INCLUIR O OBJETO ENDEREÇO NA ATRIBUTO DO Funcionario
                funcionario.setEndereco(end);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return funcionario;
    }

    public int alterar(Funcionario funcionario) {

        StringBuilder str = new StringBuilder();
        int numeroLinhasAfetadas = 0;

        try {

            str.append(" update funcionario set nome = ?                       ");
            str.append("                    ,telefone = ?                  ");
            str.append("                    ,data_nascimento = ?           ");
            str.append("                    ,email = ?                     ");
            str.append("                    ,documento                     ");
            str.append("                    ,salario = ?                   ");
            str.append("                    ,pis = ?                       ");
            str.append("                    ,logradouro = ?                ");
            str.append("                     ,bairro = ?                   ");
            str.append("                     ,cep = ?                      ");
            str.append("                     ,cidade = ?                   ");
            str.append("                     ,complemento = ?              ");
            str.append("                     ,numero = ?                   ");
            str.append("                      ,uf = ?                      ");
            str.append("where id_funcionario = ?                               ");

            //RECUPERAR A CONEXÃO
            Connection conn = new ConnectionFactory().getConnection();

            //INSTANCIAR O COMANDO
            PreparedStatement stmt = conn.prepareStatement(str.toString());

            //CRIAÇÃO DE PARAMETROS
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getTelefone());
            stmt.setDate(3, new java.sql.Date(funcionario.getDataNascimento().getTimeInMillis()));

            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getDocumento());
            stmt.setDouble(6, funcionario.getSalario());
            stmt.setString(7, funcionario.getPis());
            stmt.setString(8, funcionario.getEndereco().getLogradouro());
            stmt.setString(9, funcionario.getEndereco().getBairro());
            stmt.setString(10, funcionario.getEndereco().getCep());
            stmt.setString(11, funcionario.getEndereco().getCidade());
            stmt.setString(12, funcionario.getEndereco().getComplemento());
            stmt.setString(13, funcionario.getEndereco().getNumero());
            stmt.setString(14, funcionario.getEndereco().getUf());
            stmt.setInt(15, funcionario.getId());

            //EXECUTAR O COMANDO
            //numeroLinhasAfetadas = stmt.executeUpdate(str.toString());
            numeroLinhasAfetadas = stmt.executeUpdate();

            //FECHAR OS RECURSOS
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(e);
        }

        return numeroLinhasAfetadas;

    }

    public int excluir(int id) {

        int numeroLinhasAfetadas = 0;

        try {

            String str = "delete from funcionario where id_funcionario = ?";

            //RECUPERAR A CONEXÃO
            Connection conn = new ConnectionFactory().getConnection();

            //INSTANCIA O COMANDO
            PreparedStatement statement = conn.prepareStatement(str);
            statement.setInt(1, id);

            //EXECUTA O DELETE
            numeroLinhasAfetadas = statement.executeUpdate();

            //FECHA A CONEXÃO E O STATEMENT
            conn.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return numeroLinhasAfetadas;
    }
}