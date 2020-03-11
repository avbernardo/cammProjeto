
package camm;


public class ItemCompra {
    
    private String Observação;
    private double Peso_Quantidade;
    private double valorUnitário;
    private int Cod_ItemCompra;
    private double valorTotal;
    private Compra compra;
    private Material material;

    public String getObservação() {
        return Observação;
    }

    public void setObservação(String Observação) {
        this.Observação = Observação;
    }

    public double getPeso_Quantidade() {
        return Peso_Quantidade;
    }

    public void setPeso_Quantidade(double Peso_Quantidade) {
        this.Peso_Quantidade = Peso_Quantidade;
    }

    public double getValorUnitário() {
        return valorUnitário;
    }

    public void setValorUnitário(double valorUnitário) {
        this.valorUnitário = valorUnitário;
    }

    public int getCod_ItemCompra() {
        return Cod_ItemCompra;
    }

    public void setCod_ItemCompra(int Cod_ItemCompra) {
        this.Cod_ItemCompra = Cod_ItemCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    
    
    
    
}
