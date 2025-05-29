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
                        boolean registroBemSucedido;
                        String dataMov = null;
                        MovimentacaoEstoque movRegistrada;
                        do {
                            // Inserir a quantidade
                            System.out.print("Digite a quantidade (+/-): "); 
                            int quantidade = scanner.nextInt(); // Scanneia a quantidade inserida
                            // Escolhe se vai ter data ou não
                            System.out.print("Deseja inserir a data da movimentação? (s/n): ");
                            scanner.nextLine(); // Limpando o buffer do teclado
                            String escolhaData = scanner.nextLine().toLowerCase(); // Scaneia o (s/n)

                            if (escolhaData.equals("s")) { // Caso (s), inserir a data
                                System.out.print("Digite a data (formato DD/MM/AAAA): ");
                                dataMov = scanner.nextLine(); // Scaneia a data
                                movRegistrada = estoque.registrarMovimentacao(quantidade, dataMov); // Chama o método
                            } else {
                                // Chama o método que passa null para a data
                                movRegistrada = estoque.registrarMovimentacao(quantidade);
                            }
                            
                            registroBemSucedido = (movRegistrada != null); // RegistroBemSucedido recebe true caso movRegistrada for diferente de null
                            if(registroBemSucedido){ // caso for true
                            System.out.println("Saldo atual: " + estoque.getSaldoAtual() + " unidades"); // Mostra o saldo
                            System.out.println("");
                            }
                        } while (!registroBemSucedido); // Repete enquanto o registro não for valido 
                    break;

                case 2:
                        System.out.println("[HISTÓRICO]");
                        MovimentacaoEstoque[] lista = estoque.getHistorico();
                        int i = 1; // Para não dar erro no método adcionarHistorico
                        for (MovimentacaoEstoque m : lista){ // A variável "m" vai percorrer todos os números dentro de "lista"
                            if(m != null){ // Variável "m" tem que ser diferente de null
                            String infoData = "";
                            if (m.getData() != null && !m.getData().isEmpty()) { // Verifica se não é nula e não está vazia
                                infoData = " em " + m.getData();
                            }
                            String sinal = m.getTipo().equals("Entrada") ? "+" : "-"; // Cria uma variável para registrar o sinal da movimentação
                                System.out.println(i + ". " + sinal + m.getQuantidade() + " unidades (" + m.getTipo() + ")" + infoData); // Mostra o historico formatado
                            }
                        }
                        System.out.println("Saldo Total: " + estoque.getSaldoAtual() + " unidades");
                        System.out.println("");
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
