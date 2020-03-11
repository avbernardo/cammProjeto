
package camm;


public class Fornecedor {
    
    private String nome;
    private int ID_Fornecedor;
    private String contato;
    private String bairro;
    private String logradouro;
    private String número;
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID_Fornecedor() {
        return ID_Fornecedor;
    }

    public void setID_Fornecedor(int ID_Fornecedor) {
        this.ID_Fornecedor = ID_Fornecedor;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNúmero() {
        return número;
    }

    public void setNúmero(String número) {
        this.número = número;
    }
    
    @Override
    public String toString(){
        return getNome();
    }
    
    
    
}
