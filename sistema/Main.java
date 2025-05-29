import java.time.LocalDate;
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
                        do {
                            System.out.print("Digite a quantidade (+/-): "); 
                            int quantidade = scanner.nextInt(); // Scanneia a quantidade
                            String data = LocalDate.now().toString(); // Registra o horário

                            // Passa por todas as validações e registra o movimento
                            MovimentacaoEstoque movRegistrada = estoque.registrarMovimentacao(quantidade,data);
                            registroBemSucedido = (movRegistrada != null); // RegistroBemSucedido recebe true caso movRegistrada for diferente de null
                            if(registroBemSucedido){ // caso for true
                            System.out.println("Movimentação registrada!");
                            System.out.println("Saldo atual: " + estoque.getSaldoAtual() + " unidades"); // Mostra o saldo
                            System.out.println("");
                            }
                        } while (!registroBemSucedido); // Repete enquanto o registro não for valido 
                    break;

                case 2:
                        System.out.println("[HISTÓRICO]");
                        MovimentacaoEstoque[] lista = estoque.getHistorico();
                        int i = 1;
                        for (MovimentacaoEstoque m : lista){ // A variável "m" vai percorrer todos os números dentro de "lista"
                            if(m != null){ // Variável "m" tem que ser diferente de null
                            String sinal = m.getTipo().equals("Entrada") ? "+" : "-"; // Cria uma variável para registrar o sinal da movimentação
                                System.out.println(i + ". " + sinal + m.getQuantidade() + " unidades (" + m.getTipo() + ")"); // Mostra o historico formatado
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
