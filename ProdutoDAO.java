package gerenciamentodeEstoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void adicionarProduto(Produto produto) {
        produto.setNome(produto.getNome().toLowerCase());
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO produtos (nome, quantidade, preco, marca, descricao) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, produto.getNome());
                statement.setInt(2, produto.getQuantidade());
                statement.setDouble(3, produto.getPreco());
                statement.setString(4, produto.getMarca());
                statement.setString(5, produto.getDescricao());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean produtoExiste1(String nomeProduto) {
        return obterProduto(nomeProduto) != null;
    }

    public void atualizarQuantidade(String nomeProduto, int quantidade, boolean isAdicionar) {
        nomeProduto = nomeProduto.toLowerCase();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = isAdicionar ? "UPDATE produtos SET quantidade = quantidade + ? WHERE nome = ?" :
                                       "UPDATE produtos SET quantidade = quantidade - ? WHERE nome = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, quantidade);
                statement.setString(2, nomeProduto);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProduto(String nomeProduto, String novoNome, Double novoPreco, String novaMarca, String novaDescricao) {
        nomeProduto = nomeProduto.toLowerCase();
        novoNome = (novoNome != null) ? novoNome.toLowerCase() : null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE produtos SET nome = COALESCE(?, nome), preco = COALESCE(?, preco), marca = COALESCE(?, marca), descricao = COALESCE(?, descricao) WHERE nome = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, novoNome);
                statement.setDouble(2, novoPreco);
                statement.setString(3, novaMarca);
                statement.setString(4, novaDescricao);
                statement.setString(5, nomeProduto);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarProduto(String nomeProduto) {
        nomeProduto = nomeProduto.toLowerCase();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM produtos WHERE nome = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeProduto);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM produtos";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    int quantidade = resultSet.getInt("quantidade");
                    double preco = resultSet.getDouble("preco");
                    String marca = resultSet.getString("marca");
                    String descricao = resultSet.getString("descricao");
                    Produto produto = new Produto(nome, quantidade, preco, marca, descricao);
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
    public boolean produtoExiste(String nomeProduto) {
        boolean existe = false;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM produtos WHERE nome = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeProduto.toLowerCase());
                try (ResultSet resultSet = statement.executeQuery()) {
          
                	//resultSet.beforeFirst();
                    existe = resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
    
    public Produto obterProduto(String nomeProduto) {
        Produto produto = new Produto();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM produtos WHERE nome = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomeProduto.toLowerCase());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    int quantidade = resultSet.getInt("quantidade");
                    double preco = resultSet.getDouble("preco");
                    String marca = resultSet.getString("marca");
                    String descricao = resultSet.getString("descricao");
                   produto = new Produto(nome, quantidade, preco, marca, descricao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }
}
