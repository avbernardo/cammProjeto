
package camm;


public class Relatório {
    
    private int Cod_Relatório;
    private double totalCompras;
    private double totalVendas;
    private String dataRelatório;
    private String tipoRelatório;
    private double lucro;

    public int getCod_Relatório() {
        return Cod_Relatório;
    }

    public void setCod_Relatório(int Cod_Relatório) {
        this.Cod_Relatório = Cod_Relatório;
    }

    public double getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(double totalCompras) {
        this.totalCompras = totalCompras;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(double totalVendas) {
        this.totalVendas = totalVendas;
    }

    public String getDataRelatório() {
        return dataRelatório;
    }

    public void setDataRelatório(String dataRelatório) {
        this.dataRelatório = dataRelatório;
    }

    public String getTipoRelatório() {
        return tipoRelatório;
    }

    public void setTipoRelatório(String tipoRelatório) {
        this.tipoRelatório = tipoRelatório;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
    
    
    
}
