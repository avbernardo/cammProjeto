
package camm;


public class Venda {
    
    private double ValorTotal;
    private String Data;
    private int Cod_Venda;
    private Cliente cliente;

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

    public int getCod_Venda() {
        return Cod_Venda;
    }

    public void setCod_Venda(int Cod_Venda) {
        this.Cod_Venda = Cod_Venda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    
    
}
