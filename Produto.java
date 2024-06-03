package gerenciamentodeEstoque;

public class Produto {
    private String nome;
    private int quantidade;
    private double preco;
    private String marca;
    private String descricao;

    public Produto() {}

    public Produto(String nome, int quantidade, double preco, String marca, String descricao) {
        this.nome = nome.toLowerCase();
        this.quantidade = quantidade;
        this.preco = preco;
        this.marca = marca;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toLowerCase();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("| Produto Nome: %s | Quantidade: %d | Preço: %.2f | Marca: %s | Descrição: %s |", 
                             nome, quantidade, preco, marca, descricao);
    }
}
