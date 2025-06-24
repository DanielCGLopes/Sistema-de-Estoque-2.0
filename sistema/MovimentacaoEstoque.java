public abstract class MovimentacaoEstoque {
    
    private int quantidade;
    private String data;
    private String tipo;
    
    // Construtor
    public MovimentacaoEstoque (int quantidade, String data,String tipo){
        this.quantidade = quantidade;
        this.data = data;
        this.tipo = tipo;
    }

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
