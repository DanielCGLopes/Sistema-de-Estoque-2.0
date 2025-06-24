import java.util.ArrayList;
public class Estoque {

    private ArrayList<MovimentacaoEstoque> historico = new ArrayList<>();
    private int saldoAtual = 0;

    // Construtor
    public Estoque(){
        // Inicialização padrão
    }

    public MovimentacaoEstoque registrarMovimentacao(int quantidade) throws EstoqueException {
        return registrarMovimentacao(quantidade, null); // Passa null para a data
    }

    public MovimentacaoEstoque registrarMovimentacao(int quantidade, String data) throws EstoqueException{

        // 1º Passo: Validação da Quantidade(não pode ser zero)
        if (quantidade == 0) {
            throw new EstoqueException("A quantidade da movimentação não pode ser zero.");
        }

        MovimentacaoEstoque mov;

        // 2° Passo: Determinar se é Entrada ou Saida
        if (quantidade > 0){
            mov = new Entrada(quantidade, data); // Variável mov pertence à Entrada
        } else { 
            mov = new Saida(-quantidade, data); // Variável mov pertence à Saída
        }
        
        // 3° Passo: Validar a transação individual (min: 0,máx: 999)
        if (!((Processavel) mov).validarMovimentacao()) {
            throw new EstoqueException("Quantidade da movimentação inválida (fora do limite da transação).");
        }

        // 4° Passo: Não permitir o saldo ficar negativo
        if(mov instanceof Saida){ // mov pertence à Saida
            if (this.saldoAtual < mov.getQuantidade()) {
                throw new EstoqueException("Saldo insuficiente para realizar esta saída.");
            }
        }

        // 5° Passo: Não permitir o saldo ultrapassar 999 unidades
        if (mov instanceof Entrada) { // Verifica se é uma instância de Entrada
            // Se a quantidade adicionada exceder o limite máximo do estoque
            int limiteMaxEstoque = 999;
            if ((this.saldoAtual + mov.getQuantidade()) > limiteMaxEstoque) {
                throw new EstoqueException("Não é possível adicionar esta quantidade. O estoque excederia o limite máximo de " + limiteMaxEstoque + " unidades.");
            }
        }

        // 6° Passo: Caso passe por todas validações adiciona ao historico e atualiza o saldo
        ((Processavel) mov).processarMovimentacao();
        historico.add(mov);
        atualizarSaldo(mov);
        return mov;
    }

    public void mostrarHistorico(){
        if (historico.isEmpty()) { // Para evitar mostrar uma lista vazia
        System.out.println("Nenhuma movimentação registrada.");
        return;
        }
        int i = 1; 
        for (MovimentacaoEstoque mov : historico){ // A variável "mov" vai percorrer todos os números dentro de "lista"
            String infoData = "";
            if (mov.getData() != null && !mov.getData().isEmpty()) { // Verifica se não é nula e não está vazia
            infoData = " em " + mov.getData();
            }      

            String sinal = mov.getTipo().equals("Entrada") ? "+" : "-"; // Cria uma variável para registrar o sinal da movimentação
            System.out.println(i + ". " + sinal + mov.getQuantidade() + " unidades (" + mov.getTipo() + ")" + infoData); // Mostra o historico formatado
            i++;
        }
        System.out.println("Saldo Total: " + this.getSaldoAtual() + " unidades");
        System.out.println("");
    }

    // Métodos getters para acessar o historico e o saldo atual
    public ArrayList<MovimentacaoEstoque> getHistorico() {
        return historico;
    }
    public int getSaldoAtual(){
        return saldoAtual;
    }
    
    // Método para atualizar o saldo
    private void atualizarSaldo(MovimentacaoEstoque mov){
        if (mov instanceof Entrada) { // mov pertence à Entrada
            saldoAtual += mov.getQuantidade(); // Soma se for Entrada
        } else {
            saldoAtual -= mov.getQuantidade(); // Subtrai se for Saida
        }
    }

    // Método para calcular e mostrar as estatisticas
    public String getEstatisticas() {
        int total = 0, soma = 0, maiorEntrada = 0, maiorSaida = 0;
        for (MovimentacaoEstoque mov : historico) { // A variável "mov" vai percorrer todos os números dentro de "historico"
            if (mov != null) {
                soma += mov.getQuantidade(); 
                total++;

                // Caso "mov" pertence à Entrada verá qual transação for maior
                if (mov instanceof Entrada && mov.getQuantidade() > maiorEntrada) {
                    maiorEntrada = mov.getQuantidade();
                }
                // Caso "mov" pertence à Saida verá qual transação for menor 
                if (mov instanceof Saida && mov.getQuantidade() > maiorSaida) {
                    maiorSaida = mov.getQuantidade();
                }
            }
        }
        double media = total > 0 ? (double) soma / total : 0; // Calcula a média 
        return "Total movimentações: " + total +
               "\nMaior entrada: " + maiorEntrada +
               "\nMaior saída: " + maiorSaida +
               "\nMédia movimentações: " + String.format("%.2f",media) +
               "\nSaldo atual: " + saldoAtual + "\n";
    }

}
