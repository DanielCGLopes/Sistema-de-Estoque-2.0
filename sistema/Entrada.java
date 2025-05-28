public class Entrada extends MovimentacaoEstoque implements Processavel{

    public Entrada (int quantidade, String data){
        super(quantidade,data);
        this.tipo = "Entrada";
    }

    @Override
    public boolean processarMovimentacao() {
    }

    @Override
    public boolean validarMovimentacao() {
    }

    

}
