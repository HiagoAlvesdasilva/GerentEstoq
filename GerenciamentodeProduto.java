package gerenciamentodeEstoque;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciamentodeProduto {
    static ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void main(String[] args) throws OpcaoInvalidaException {
        ProdutoService produtoService = new ProdutoService();
        
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("=====Menu de Gerenciamento de Estoque=====");
            System.out.println("|Escolha uma opção:                       |");
            System.out.println("|1. Adicionar Produto                     |");
            System.out.println("|2. Atualizar Quantidade                  |");
            System.out.println("|3. Atualizar Produto                     |");
            System.out.println("|4. Listar Produtos                       |");
            System.out.println("|5. Excluir Produto                       |");
            System.out.println("|6. Sair                                  |");
            System.out.println("==========================================");
            
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Consome a nova linha
                switch (escolha) {
                    case 1:
                        adicionarProduto(scanner, produtoService);
                        break;
                    case 2:
                        atualizarQuantidade(scanner, produtoService);
                        break;
                    case 3:
                        produtoService.editarProduto(scanner);
                        break;
                    case 4:
                        listarProdutos(produtoService);
                        break;
                    case 5:
                        deletarProduto(scanner, produtoService);
                        break;
                    case 6:
                        continuar = false;
                        System.out.println("Encerrando o programa.");
                        break;
                    default:
                        throw new OpcaoInvalidaException("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); 
            } catch (OpcaoInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    private static void adicionarProduto(Scanner scanner, ProdutoService produtoService) {
        String nome;
        do {
            System.out.println("Digite o nome do produto:");
            nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("O nome do produto não pode estar vazio. Tente novamente.");
            } else if (produtoDAO.produtoExiste(nome)) {
                System.out.println("Produto já existe. Digite outro nome para o produto.");
                nome = ""; 
            }
        } while (nome.isEmpty());

        System.out.println("Digite a quantidade:");
        int quantidade = scanner.nextInt();
        System.out.println("Digite o preço:");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consome a nova linha
        System.out.println("Digite a marca:");
        String marca = scanner.nextLine();
        System.out.println("Digite a descrição:");
        String descricao = scanner.nextLine();

        Produto produto = new Produto(nome, quantidade, preco, marca, descricao);
        produtoDAO.adicionarProduto(produto);
        System.out.println("Produto adicionado com sucesso.");
    }


    private static void atualizarQuantidade(Scanner scanner, ProdutoService produtoService) {
        System.out.println("Digite o nome do produto:");
        String nomeProduto = scanner.nextLine();
        System.out.println("Digite '1' para adicionar ou '2' para subtrair a quantidade:");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha
        boolean isAdicionar = opcao == 1;
        System.out.println("Digite a quantidade a ser atualizada:");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        produtoDAO.atualizarQuantidade(nomeProduto, quantidade, isAdicionar);
        System.out.println("Quantidade atualizada com sucesso.");
    }

    private static void listarProdutos(ProdutoService produtoService) {
        for (Produto produto : produtoDAO.listarProdutos()) { // Chamando o mÃ©todo correto
            System.out.println(produto);
        }
    }

    private static void deletarProduto(Scanner scanner, ProdutoService produtoService) {
        System.out.println("Digite o Nome do Produto a Ser Excluido:");
        String nomeProduto = scanner.nextLine();
        produtoDAO.deletarProduto(nomeProduto);
        System.out.println("Produto Excuido com sucesso.");
    }
}
