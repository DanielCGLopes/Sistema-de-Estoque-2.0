public class Saida extends MovimentacaoEstoque implements Processavel {

    // Construtor
    public Saida(int quantidade, String data) {
        super(quantidade, data, "Saída");
    }

    // Métodos implementados
    @Override
    public boolean processarMovimentacao() {
        // Retorna a true para indicar que pode ser processado
        return true;
    }

    @Override
    public boolean validarMovimentacao() {
        // Mínimo 0, Máximo 999 (Não leva em consideração o sinal)
        return getQuantidade() > 0 && getQuantidade() <= 999;
    }

    @Override
    public boolean validarQuantidade() {
        return validarMovimentacao();
    }

}
