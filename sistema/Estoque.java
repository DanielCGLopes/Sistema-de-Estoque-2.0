public class Estoque {

    private MovimentacaoEstoque[] historico = new MovimentacaoEstoque[10]; // Array de 10 números
    private int saldoAtual = 0;
    private int indiceAtual = 0; // Usado para gerenciar o Array 

    // Construtor
    public Estoque(){
        // Inicialização padrão
    }
    // Caso o usuario não insira a data 
    public MovimentacaoEstoque registrarMovimentacao(int quantidade){
        // Retorna ao método que possua toda a lógica
        return registrarMovimentacao(quantidade, null);
    }
    public MovimentacaoEstoque registrarMovimentacao(int quantidade, String data){
        MovimentacaoEstoque mov;
        
        // 1º Passo: Validação da Quantidade(não pode ser zero)
        if (quantidade == 0) {
            System.out.println("Erro: A quantidade da movimentação não pode ser zero.");
            return null;
        }

        // 2° Passo: Determinar se é Entrada ou Saida
        if (quantidade > 0){
            mov = new Entrada(quantidade, data); // Variável mov pertence à Entrada
        } else { 
            mov = new Saida(-quantidade, data); // Variável mov pertence à Saída
        }
        
        // 3° Passo: Validar a transação individual (min: 0,máx: 999)
        if (!mov.validarMovimentacao()) {
            System.out.println("Erro: Quantidade da movimentação inválida (fora do limite da transação).");
            return null; // Retorna a null se a validação falhar
        }

        // 4° Passo: Não permitir o saldo ficar negativo
        if(mov instanceof Saida){ // mov pertence à Saida
            if (this.saldoAtual < mov.getQuantidade()) {
                System.out.println("Erro: Saldo insuficiente para realizar esta saída.");
                return null; // Retorna a null se o saldo for negativo
            }
        }

        // 5° Passo: Não permitir o saldo ultrapassar 999 unidades
        if (mov instanceof Entrada) { // Verifica se é uma instância de Entrada
            // Se a quantidade adicionada exceder o limite máximo do estoque
            int limiteMaxEstoque = 999;
            if ((this.saldoAtual + mov.getQuantidade()) > limiteMaxEstoque) {
                System.out.println("Erro: Não é possível adicionar esta quantidade. O estoque excederia o limite máximo de " + limiteMaxEstoque + " unidades.");
                return null; // Retorna null se exceder o limite
            }
        }

        // 6° Passo: Caso passe por todas validações adiciona ao historico e atualiza o saldo
        mov.processarMovimentacao();
        adicionarHistorico(mov);
        atualizarSaldo(mov);
        return mov;
    }

    public void adicionarHistorico(MovimentacaoEstoque mov){
        // Para criar um loop de 1 a 10 (Ao chegar em 10 volta para o 1)
        historico[indiceAtual % 10] = mov;
        indiceAtual++;
    }

    // Métodos getters para acessar o historico e o saldo atual
    public MovimentacaoEstoque[] getHistorico() {
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
