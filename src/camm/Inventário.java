
package camm;


public class Inventário {
    
    private String tipoMovimentação;
    private double Peso_Quantidade;
    private String dataMovimentação;
    private int IdInventário;
    private Material material;

    public String getTipoMovimentação() {
        return tipoMovimentação;
    }

    public void setTipoMovimentação(String tipoMovimentação) {
        this.tipoMovimentação = tipoMovimentação;
    }

    public double getPeso_Quantidade() {
        return Peso_Quantidade;
    }

    public void setPeso_Quantidade(double Peso_Quantidade) {
        this.Peso_Quantidade = Peso_Quantidade;
    }

    public String getDataMovimentação() {
        return dataMovimentação;
    }

    public void setDataMovimentação(String dataMovimentação) {
        this.dataMovimentação = dataMovimentação;
    }

    public int getIdInventário() {
        return IdInventário;
    }

    public void setIdInventário(int IdInventário) {
        this.IdInventário = IdInventário;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    

    
    
    
    
    
}
