public class Saida extends MovimentacaoEstoque implements Processavel {

    public Saida(int quantidade, String data) {
        super(quantidade, data);
        this.tipo = "Saida";

    }

    @Override
    public boolean processarMovimentacao() {

    }

    @Override
    public boolean validarMovimentacao() {

    }

}
