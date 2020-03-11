/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import camm.CAMM;
import camm.Estoque;
import camm.Relatório;
import camm.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class RelatórioSQL {
    
    /**
     *
     * @param f
     */
    public void insertRelatório(Relatório f) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO relatório (totalCompras,totalVendas,dataRelatório,resultado,tipoRelatório)VALUES(?,?,?,?,?)");
            stmt.setDouble(1, f.getTotalCompras());
            stmt.setDouble(2, f.getTotalVendas());
            stmt.setString(3, f.getDataRelatório());
            stmt.setDouble(4, f.getLucro());
             stmt.setString(5, f.getTipoRelatório());

            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<Relatório> selectAllRelatório() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Relatório> relatórios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from relatório");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Relatório e = new Relatório();
                e.setCod_Relatório(rs.getInt("Cod_Relatório"));
                e.setTotalCompras(rs.getDouble("totalCompras"));
                e.setTotalVendas(rs.getDouble("totalVendas"));
                e.setTipoRelatório(rs.getString("tipoRelatório"));
                e.setDataRelatório(rs.getString("dataRelatório"));
                e.setLucro(rs.getDouble("resultado"));
                
                
                relatórios.add(e);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return relatórios;

    }
    
    public List<Estoque> selectAllEstoqueM() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        

        List<Estoque> estoques = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select Cod_Material, sum(Peso_Quantidade) as total\n" +
"from estoque\n" +
"group by Cod_Material");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Estoque e = new Estoque();
                
                Material m = new Material();
                m.setCod_Material(rs.getInt("Cod_Material"));
                
                e.setPeso_Quantidade(rs.getDouble("total"));
               
                
                e.setMaterial(m);
                estoques.add(e);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return estoques;

    }
    public List<Relatório> selectForRelatório(String nome) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Relatório> relatórios = new ArrayList<>();
        nome = "%"+nome+"%";
        try {
            stmt = con.prepareStatement("select * from relatório where dataRelatório like ? or totalCompras like ? or totalVendas like ? or resultado like ? or tipoRelatório like ?");
            stmt.setString(1, nome);
            stmt.setString(2, nome);
            stmt.setString(3, nome);
            stmt.setString(4, nome);
            stmt.setString(5, nome);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                 java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("dataRelatório"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Relatório e = new Relatório();
                
                e.setCod_Relatório(rs.getInt("Cod_Relatório"));
                e.setDataRelatório(dataFormatada);
                e.setLucro(rs.getDouble("resultado"));
                e.setTipoRelatório(rs.getString("tipoRelatório"));
                e.setTotalCompras(rs.getDouble("totalCompras"));
                e.setTotalVendas(rs.getDouble("totalVendas"));
               
                relatórios.add(e);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return relatórios;

    }

}
