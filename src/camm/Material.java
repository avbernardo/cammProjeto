
package camm;


public class Material {
    
    private String nome;
    private int Cod_Material;
    private double Preço_Kilo_Unidade;
    private double total_estoque;
    private boolean porQuantidade;
    private boolean ativo;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isPorQuantidade() {
        return porQuantidade;
    }

    public void setPorQuantidade(boolean porQuantidade) {
        this.porQuantidade = porQuantidade;
    }

    public double getTotal_estoque() {
        return total_estoque;
    }

    public void setTotal_estoque(double total_estoque) {
        this.total_estoque = total_estoque;
    }
   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_Material() {
        return Cod_Material;
    }

    public void setCod_Material(int Cod_Material) {
        this.Cod_Material = Cod_Material;
    }

    public double getPreço_Kilo_Unidade() {
        return Preço_Kilo_Unidade;
    }

    public void setPreço_Kilo_Unidade(double Preço_Kilo_Unidade) {
        this.Preço_Kilo_Unidade = Preço_Kilo_Unidade;
    }

    @Override
    public String toString(){
        return getNome();
    }
    
}
