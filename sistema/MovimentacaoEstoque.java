public abstract class MovimentacaoEstoque {
    
    private int quantidade;
    private String data;
    private String tipo;
    
    //Construtor 
    public MovimentacaoEstoque (int quantidade, String data,String tipo){
        this.quantidade = quantidade;
        this.data = data;
        this.tipo = tipo;
    }

    


    //Getters e Setters
    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }
    
    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }


}
