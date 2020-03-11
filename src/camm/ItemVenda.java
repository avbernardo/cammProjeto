
package camm;


public class ItemVenda {
    
    private String Observação;
    private double Peso_Quantidade;
    private double valorUnitário;
    private int Cod_ItemVenda;
    private double valorTotal;
    private Venda venda;
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

    public int getCod_ItemVenda() {
        return Cod_ItemVenda;
    }

    public void setCod_ItemVenda(int Cod_ItemVenda) {
        this.Cod_ItemVenda = Cod_ItemVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    
    
    
    
    
    
}
