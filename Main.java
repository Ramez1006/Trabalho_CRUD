// Main.java
import java.util.Scanner;

public class Main {
    private static CarroDAO carroDAO = new CarroDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha

            switch (opcao) {
                case 1:
                    criarCarro();
                    break;
                case 2:
                    listarCarros();
                    break;
                case 3:
                    atualizarCarro();
                    break;
                case 4:
                    deletarCarro();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Criar Carro");
        System.out.println("2. Listar Carros");
        System.out.println("3. Atualizar Carro");
        System.out.println("4. Deletar Carro");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarCarro() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();  // Consumir nova linha

        Carro carro = new Carro(id, nome, preco);
        carroDAO.adicionarCarro(carro);
        System.out.println("Carro criado com sucesso.");
    }

    private static void listarCarros() {
        System.out.println("Lista de Carros:");
        for (Carro carro : carroDAO.listarCarros()) {
            System.out.println(carro);
        }
    }

    private static void atualizarCarro() {
        System.out.print("ID do carro a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        Carro carro = carroDAO.buscarCarro(id);
        if (carro != null) {
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo preço: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();  // Consumir nova linha

            Carro carroAtualizado = new Carro(id, nome, preco);
            carroDAO.atualizarCarro(carroAtualizado);
            System.out.println("Carro atualizado com sucesso.");
        } else {
            System.out.println("Carro não encontrado.");
        }
    }

    private static void deletarCarro() {
        System.out.print("ID do carro a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        carroDAO.removerCarro(id);
        System.out.println("Carro deletado com sucesso.");
    }
}
