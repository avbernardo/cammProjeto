
package camm;


public class Estoque {
    
    private String dataMovimetação;
    private int Cod_Estoque;
    private double Peso_Quantidade;
    private String tipoMovimentação;
    private Material material;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getDataMovimetação() {
        return dataMovimetação;
    }

    public void setDataMovimetação(String dataMovimetação) {
        this.dataMovimetação = dataMovimetação;
    }

    public int getCod_Estoque() {
        return Cod_Estoque;
    }

    public void setCod_Estoque(int Cod_Estoque) {
        this.Cod_Estoque = Cod_Estoque;
    }

    public double getPeso_Quantidade() {
        return Peso_Quantidade;
    }

    public void setPeso_Quantidade(double Peso_Quantidade) {
        this.Peso_Quantidade = Peso_Quantidade;
    }

    public String getTipoMovimentação() {
        return tipoMovimentação;
    }

    public void setTipoMovimentação(String tipoMovimentação) {
        this.tipoMovimentação = tipoMovimentação;
    }
    
    
}
