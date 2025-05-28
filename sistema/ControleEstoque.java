public class ControleEstoque {
    private MovimentacaoEstoque[] hitorico = new MovimentacaoEstoque[100];
    private int indiceAtual = 0;
    private int indiceAtual = 0;

    public MovimentacaoEstoque registrarMovimentacao(int quantidade, String data) {
        MovimentacaoEstoque mov;
        if (quantidade > 0) {
            mov = new Entrada(quantidade, data);
            saldoAtual += quantidade;
        } else {
            mov = new Saida(-quantidade, data); // transformar para positivo
            saldoAtual -= -quantidade;
        }

        if (((Processavel) mov).validarMovimentacao()) {
            ((Processavel) mov).processarMovimentacao();
            adicionarAoHistorico(mov);
        }

        return mov;
    }

    public MovimentacaoEstoque[] getHistorico() {
        MovimentacaoEstoque[] copia = new MovimentacaoEstoque[indiceAtual];
        System.arraycopy(historico, 0, copia, 0, indiceAtual);
        return copia;
    }

    public void adicionarAoHistorico(MovimentacaoEstoque mov) {
        if (indiceAtual < historico.length) {
            historico[indiceAtual++] = mov;
        }
    }

    public String getEstatisticas() {
        return "Total de movimentações: " + indiceAtual + "\nSaldo atual: " + saldoAtual;
    }

}
