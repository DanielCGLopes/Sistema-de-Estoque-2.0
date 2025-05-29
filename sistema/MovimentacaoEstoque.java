public abstract class MovimentacaoEstoque implements Processavel {
    
    private int quantidade;
    private String data;
    private String tipo;
    
    // Construtor
    public MovimentacaoEstoque (int quantidade, String data,String tipo){
        this.quantidade = quantidade;
        this.data = data;
        this.tipo = tipo;
    }

    // Método abstrato que as subclasses vão implementar
    @Override
    public abstract boolean validarMovimentacao();

    @Override
    public abstract boolean processarMovimentacao();

    // Getters 
    public int getQuantidade(){
        return quantidade;
    }

    public String getData(){
        return data;
    }

    public String getTipo(){
        return tipo;
    }

}
