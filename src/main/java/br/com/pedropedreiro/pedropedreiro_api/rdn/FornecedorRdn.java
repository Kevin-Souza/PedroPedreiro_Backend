package br.com.pedropedreiro.pedropedreiro_api.rdn;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.pedropedreiro.pedropedreiro_api.Models.Endereco;
import br.com.pedropedreiro.pedropedreiro_api.Models.Fornecedor;

public class FornecedorRdn {
    // Inserir
    public int inserir(Fornecedor fornecedor) throws SQLException {

        StringBuilder sql = new StringBuilder();
        int linhasAfetadas = 0;
        sql.append("INSERT INTO fornecedor              ");
        sql.append("            (nome                   ");
        sql.append("            ,telefone               ");
        sql.append("            ,email                  ");
        sql.append("            ,documento              ");
        sql.append("            ,data_nascimento        ");
        sql.append("            ,razao_social           ");
        sql.append("            ,inscricao_estadual     ");
        sql.append("            ,categoria              ");
        sql.append("            ,tipo_produto           ");
        sql.append("            ,logradouro             ");
        sql.append("            ,bairro                 ");
        sql.append("            ,cep                    ");
        sql.append("            ,cidade                 ");
        sql.append("            ,complemento            ");
        sql.append("            ,numero                 ");
        sql.append("            ,uf)                    ");
        sql.append("        VALUES                      ");
        sql.append("              (?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?                    ");
        sql.append("              ,?)                   ");

        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql.toString());

        // Fornecedor
        stmt.setString(1, fornecedor.getNome());
        stmt.setString(2, fornecedor.getTelefone());
        stmt.setString(3, fornecedor.getEmail());
        stmt.setString(4, fornecedor.getDocumento());

        stmt.setDate(5, new java.sql.Date(fornecedor.getDataNascimento().getTimeInMillis())); //Data de Nascimento

        stmt.setString(6, fornecedor.getRazaoSocial());
        stmt.setString(7, fornecedor.getInscricaoEstadual());
        stmt.setString(8, fornecedor.getCategoria());
        stmt.setString(9, fornecedor.getTipoProduto());

        // Endereço
        stmt.setString(10, fornecedor.getEndereco().getLogradouro());
        stmt.setString(11, fornecedor.getEndereco().getBairro());
        stmt.setString(12, fornecedor.getEndereco().getCep());
        stmt.setString(13, fornecedor.getEndereco().getCidade());
        stmt.setString(14, fornecedor.getEndereco().getComplemento());
        stmt.setString(15, fornecedor.getEndereco().getNumero());
        stmt.setString(16, fornecedor.getEndereco().getUf());

        linhasAfetadas = stmt.executeUpdate();

        System.out.println("linhas afetadas: " + linhasAfetadas);

        stmt.close();
        conn.close();

        return linhasAfetadas;
    }

    // Obter Todos
    public ArrayList<Fornecedor> obterTodos() {
        ArrayList<Fornecedor> retorno = new ArrayList<Fornecedor>();
        try {
            StringBuilder str = new StringBuilder();
            str.append(" select  f.id_fornecedor        ");
            str.append("        ,f.nome                 ");
            str.append("        ,f.telefone             ");
            str.append("        ,f.email                ");
            str.append("        ,f.documento            ");
            str.append("        ,f.data_nascimento      ");
            str.append("        ,f.razao_social         ");
            str.append("        ,f.inscricao_estadual   ");
            str.append("        ,f.categoria            ");
            str.append("        ,f.tipo_produto         ");
            str.append("        ,f.logradouro           ");
            str.append("        ,f.bairro               ");
            str.append("        ,f.cep                  ");
            str.append("        ,f.cidade               ");
            str.append("        ,f.complemento          ");
            str.append("        ,f.numero               ");
            str.append("        ,f.uf                   ");
            str.append("from fornecedor f               ");

            // Abre a conexão
            Connection conn = new ConnectionFactory().getConnection();

            // Criar nosso Statement
            Statement stmt = conn.createStatement();

            // Receber os dados no ResultSet
            ResultSet rs = stmt.executeQuery(str.toString());

            // Percorre todos os registros do ResultSet
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(rs.getInt("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setDocumento(rs.getString("documento"));

                // Converter SQL Date to Calendar
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getDate("data_nascimento"));
                fornecedor.setDataNascimento(calendar);

                fornecedor.setRazaoSocial(rs.getString("razao_social"));
                fornecedor.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                fornecedor.setCategoria(rs.getString("categoria"));
                fornecedor.setTipoProduto(rs.getString("tipo_produto"));

                // Popular/Carregar os atributos do endereço
                Endereco end = new Endereco();
                end.setLogradouro(rs.getString("logradouro"));
                end.setBairro(rs.getString("bairro"));
                end.setCep(rs.getString("cep"));
                end.setCidade(rs.getString("cidade"));
                end.setComplemento(rs.getString("complemento"));
                end.setNumero(rs.getString("numero"));
                end.setUf(rs.getString("uf"));

                // Incluir o objeto endereço no atributo do fornecedor
                fornecedor.setEndereco(end);

                // Adiciona o objeto fornecedor na lista de fornecedor
                retorno.add(fornecedor);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return retorno;
    }

    // Excluir
    public int excluir(int id) {
        int numeroLinhasAfetadas = 0;

        try {
            String str = "delete from fornecedor where id_fornecedor = ?";

            // Recupera a Conexão
            Connection conn = new ConnectionFactory().getConnection();

            // Instancia o Comando
            PreparedStatement statement = conn.prepareStatement(str);
            statement.setInt(1, id);

            // Executando o Delete
            numeroLinhasAfetadas = statement.executeUpdate();

            // Fecha a Conexão e o Statement
            conn.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return numeroLinhasAfetadas;
    }

    // Alterar
    public int alterar(Fornecedor fornecedor) {
        StringBuilder str = new StringBuilder();
        int numeroLinhasAfetadas = 0;

        try {
            str.append(" update fornecedor set nome = ?      ");
            str.append("          ,telefone = ?              ");
            str.append("          ,email = ?                 ");
            str.append("          ,documento = ?             ");
            str.append("          ,data_nascimento = ?       ");
            str.append("          ,razao_social = ?          ");
            str.append("          ,inscricao_estadual = ?    ");
            str.append("          ,categoria = ?             ");
            str.append("          ,tipo_produto = ?          ");
            str.append("          ,logradouro = ?            ");
            str.append("          ,bairro = ?                ");
            str.append("          ,cep = ?                   ");
            str.append("          ,cidade  = ?               ");
            str.append("          ,complemento = ?           ");
            str.append("          ,numero = ?                ");
            str.append("          ,uf = ?                    ");
            str.append(" where id_fornecedor = ?             ");

            // Recuperar a Conexão
            Connection conn = new ConnectionFactory().getConnection();

            // Instanciar o Comando
            PreparedStatement stmt = conn.prepareStatement(str.toString());

            /// Fornecedor
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getDocumento());
            stmt.setDate(5, new java.sql.Date(fornecedor.getDataNascimento().getTimeInMillis()));
            stmt.setString(6, fornecedor.getRazaoSocial());
            stmt.setString(7, fornecedor.getInscricaoEstadual());
            stmt.setString(8, fornecedor.getCategoria());
            stmt.setString(9, fornecedor.getTipoProduto());

            // Endereço
            stmt.setString(10, fornecedor.getEndereco().getLogradouro());
            stmt.setString(11, fornecedor.getEndereco().getBairro());
            stmt.setString(12, fornecedor.getEndereco().getCep());
            stmt.setString(13, fornecedor.getEndereco().getCidade());
            stmt.setString(14, fornecedor.getEndereco().getComplemento());
            stmt.setString(15, fornecedor.getEndereco().getNumero());
            stmt.setString(16, fornecedor.getEndereco().getUf());

            // Executar o Comando
            numeroLinhasAfetadas = stmt.executeUpdate();

            // Fechar os Recursos
            stmt.close();
            conn.close();

        }catch (SQLException e){
            System.out.println(e);
        }

        return numeroLinhasAfetadas;
    }

    // Obter por ID
    public Fornecedor obterPorId(int id) {
        Fornecedor fornecedor = new Fornecedor();

        try {
            StringBuilder str = new StringBuilder();

            str.append(" select  f.id_fornecedor        ");
            str.append("        ,f.nome                 ");
            str.append("        ,f.telefone             ");
            str.append("        ,f.email                ");
            str.append("        ,f.documento            ");
            str.append("        ,f.data_nascimento      ");
            str.append("        ,f.razao_social         ");
            str.append("        ,f.inscricao_estadual   ");
            str.append("        ,f.categoria            ");
            str.append("        ,f.tipo_produto         ");
            str.append("        ,f.logradouro           ");
            str.append("        ,f.bairro               ");
            str.append("        ,f.cep                  ");
            str.append("        ,f.cidade               ");
            str.append("        ,f.complemento          ");
            str.append("        ,f.numero               ");
            str.append("        ,f.uf                   ");
            str.append("from fornecedor f               ");
            str.append("where f.id_fornecedor = ?       ");

            // Abre a conexão
            Connection conn = new ConnectionFactory().getConnection();

            // Criar o Statement
            PreparedStatement stmt = conn.prepareStatement(str.toString());

            // Passar o parametro de ID
            stmt.setInt(1, id);

            // Receber os dados no ResultSet
            ResultSet rs = stmt.executeQuery();

            // Percorre todos os registros do ResultSet
            while (rs.next())
            {
                // Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(rs.getInt("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setDocumento(rs.getString("documento"));

                // Converter SQL Date to Calendar
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getDate("data_nascimento"));
                fornecedor.setDataNascimento(calendar);

                fornecedor.setRazaoSocial(rs.getString("razao_social"));
                fornecedor.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                fornecedor.setCategoria(rs.getString("categoria"));
                fornecedor.setTipoProduto(rs.getString("tipo_produto"));

                // Popular/Carregar os atributos do endereço
                Endereco end = new Endereco();
                end.setLogradouro(rs.getString("logradouro"));
                end.setBairro(rs.getString("bairro"));
                end.setCep(rs.getString("cep"));
                end.setCidade(rs.getString("cidade"));
                end.setComplemento(rs.getString("complemento"));
                end.setNumero(rs.getString("numero"));
                end.setUf(rs.getString("uf"));

                // Incluir o objeto endereço no atributo do fornecedor
                fornecedor.setEndereco(end);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return fornecedor;
    }

}