
package camm;


public class Compra {
    
    private double ValorTotal;
    private String Data;
    private int Cod_Compra;
    private Fornecedor fornecedor;

    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public int getCod_Compra() {
        return Cod_Compra;
    }

    public void setCod_Compra(int Cod_Compra) {
        this.Cod_Compra = Cod_Compra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    
    
    
}
