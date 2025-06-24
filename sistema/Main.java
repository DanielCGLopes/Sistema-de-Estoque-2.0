import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        
        Estoque estoque = new Estoque();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("== CONTROLE DE ESTOQUE (POO) ==");
        System.out.println("1. Registrar movimentação");
        System.out.println("2. Ver histórico");
        System.out.println("3. Ver estatísticas");
        System.out.println("4. Sair");
        System.out.print("ESCOLHA UMA OPÇÃO: ");
        int opcao = scanner.nextInt();
        System.out.println("");
        
        while (opcao != 4) { // Repetir enquanto não for 4
            
            switch (opcao) { // "switch" ligado à variável "opcao"

                case 1:
                        System.out.println("[MOVIMENTAÇÃO]");     
                        boolean sucesso = false;

                        while (!sucesso){
                            try{
                                // Inserir a quantidade
                                System.out.print("Digite a quantidade (+/-): "); 
                                int quantidade = scanner.nextInt(); // Scanneia a quantidade inserida

                                // Escolhe se vai ter data ou não
                                System.out.print("Deseja inserir a data da movimentação? (s/n): ");
                                scanner.nextLine(); // Limpando o buffer do teclado
                                String escolhaData = scanner.nextLine().toLowerCase(); // Scaneia o (s/n)

                                String data = null;
                                if (escolhaData.equals("s")) { // Caso (s), inserir a data
                                    System.out.print("Digite a data (formato DD/MM/AAAA): ");
                                    data = scanner.nextLine(); // Scaneia a data
                                } 

                                MovimentacaoEstoque mov= estoque.registrarMovimentacao(quantidade, data); // Chama o método

                                if (mov != null) {
                                    sucesso = true; // sai do laço
                                }

                            } catch (EstoqueException e) {
                                System.out.println("Erro ao registrar a movimentação: " + e.getMessage());
                            }
                            System.out.println("Saldo atual: " + estoque.getSaldoAtual() + " unidades"); // Mostra o saldo
                            System.out.println("");
                            
                        }
                    break;

                case 2:
                        System.out.println("[HISTÓRICO]");
                        estoque.mostrarHistorico(); // Chama o método que mostra o histórico
                    break;

                case 3:
                        System.out.println("[ESTATÍSTICAS]");
                        System.out.println(estoque.getEstatisticas()); // Chama o método Estatísticas
                    break;

                default:
                    System.out.println("Opção Inválida!");
                    System.out.println("");
            }
        System.out.println("== CONTROLE DE ESTOQUE (POO) ==");
        System.out.println("1. Registrar movimentação");
        System.out.println("2. Ver histórico");
        System.out.println("3. Ver estatísticas");
        System.out.println("4. Sair");
        System.out.print("ESCOLHA UMA OPÇÃO: ");
        opcao = scanner.nextInt();
        System.out.println("");
            
        }
        System.out.println("Programa finalizado!");
            
        scanner.close();    
    }
}
