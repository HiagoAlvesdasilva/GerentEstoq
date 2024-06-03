package gerenciamentodeEstoque;

import java.util.List;
import java.util.Scanner;

public class ProdutoService {
    private ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void editarProduto(Scanner scanner) {
        System.out.println("Digite o nome do produto a ser editado:");
        String nomeProduto = scanner.nextLine();

        boolean continuarEditando = true;
        while (continuarEditando) {
            System.out.println("=====Menu de Atualização de Produto======");
            System.out.println("|Escolha uma opção:                     |");
            System.out.println("|1. Atualizar nome                      |");
            System.out.println("|2. Atualizar preço                     |");
            System.out.println("|3. Atualizar marca                     |");
            System.out.println("|4. Atualizar descrição                 |");
            System.out.println("|5. Voltar ao menu principal            |");
            System.out.println("=========================================");
            int escolhaAtualizacao = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaAtualizacao) {
                case 1:
                    atualizarNomeProduto(nomeProduto, scanner);
                    break;
                case 2:
                    atualizarPrecoProduto(nomeProduto, scanner);
                    break;
                case 3:
                    atualizarMarcaProduto(nomeProduto, scanner);
                    break;
                case 4:
                    atualizarDescricaoProduto(nomeProduto, scanner);
                    break;
                case 5:
                    continuarEditando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
            }
        }

    private void atualizarNomeProduto(String nomeProduto, Scanner scanner) {
        String novoNome;
        do {
            System.out.println("Digite o novo nome do produto:");
            novoNome = scanner.nextLine().trim();
            if (novoNome.isEmpty()) {
                System.out.println("O nome do produto não pode estar vazio. Tente novamente.");
            } else if (produtoDAO.produtoExiste(novoNome)) {
                System.out.println("Já existe um produto com esse nome. Tente novamente.");
                novoNome = "";
            }
        } while (novoNome.isEmpty());

        Produto produto = produtoDAO.obterProduto(nomeProduto);
        if (produto != null) {
            produto.setNome(novoNome);
            produtoDAO.atualizarProduto(nomeProduto, novoNome, produto.getPreco(), produto.getMarca(), produto.getDescricao());
            System.out.println("Nome atualizado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void atualizarPrecoProduto(String nomeProduto, Scanner scanner) {
        System.out.println("Digite o novo preço do produto:");
        double novoPreco = scanner.nextDouble();
        scanner.nextLine();
        Produto produto = produtoDAO.obterProduto(nomeProduto);
        if (produto != null) {
            produto.setPreco(novoPreco);
            produtoDAO.atualizarProduto(nomeProduto, produto.getNome(), novoPreco, produto.getMarca(), produto.getDescricao());
            System.out.println("Preço do produto atualizado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void atualizarMarcaProduto(String nomeProduto, Scanner scanner) {
        System.out.println("Digite a nova marca do produto:");
        String novaMarca = scanner.nextLine().trim();
        if (novaMarca.isEmpty()) {
            System.out.println("A marca do produto não pode estar vazia. Tente novamente.");
            return;
        }
        Produto produto = produtoDAO.obterProduto(nomeProduto);
        if (produto != null) {
            produto.setMarca(novaMarca);
            produtoDAO.atualizarProduto(nomeProduto, produto.getNome(), produto.getPreco(), novaMarca, produto.getDescricao());
            System.out.println("Marca do produto atualizada com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void atualizarDescricaoProduto(String nomeProduto, Scanner scanner) {
        System.out.println("Digite a nova descrição do produto:");
        String novaDescricao = scanner.nextLine().trim();
        if (novaDescricao.isEmpty()) {
            System.out.println("A descrição do produto não pode estar vazia. Tente novamente.");
            return;
        }
        Produto produto = produtoDAO.obterProduto(nomeProduto);
        if (produto != null) {
            produto.setDescricao(novaDescricao);
            produtoDAO.atualizarProduto(nomeProduto, produto.getNome(), produto.getPreco(), produto.getMarca(), novaDescricao);
            System.out.println("Descrição do produto atualizada com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    
    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }
  }