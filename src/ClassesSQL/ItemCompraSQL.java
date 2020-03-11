/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import camm.CAMM;
import camm.Cliente;
import camm.Compra;
import camm.ItemCompra;
import camm.Fornecedor;
import camm.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ItemCompraSQL {
    
    public void insertItemCompra(ItemCompra c) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO ItemCompra (ValorUnitário,Observacao,Peso_Quantidade,ValorTotal,Cod_Compra,Cod_Material)VALUES(?,?,?,?,?,?)");
            stmt.setDouble(1, c.getValorUnitário());
            stmt.setString(2, c.getObservação());
            stmt.setDouble(3, c.getPeso_Quantidade());
            stmt.setDouble(4, c.getValorTotal());
            stmt.setInt(5, c.getCompra().getCod_Compra());
            stmt.setInt(6, c.getMaterial().getCod_Material());
      
           

            stmt.executeUpdate();

        
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<ItemCompra> selectAllItemCompra() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ItemCompra> icompras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from itemcompra inner join material on itemcompra.Cod_Material = material.Cod_Material\n"
                    + "inner join compra on itemcompra.Cod_Compra = compra.Cod_Compra");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ItemCompra ic = new ItemCompra();

                ic.setCod_ItemCompra(rs.getInt("Cod_ItemCompra"));
                ic.setObservação(rs.getString("Observacao"));
                ic.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                ic.setValorUnitário(rs.getDouble("ValorUnitário"));
                ic.setValorTotal(rs.getDouble("ValorTotal"));
                
                Material m = new Material();
                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
               
                ic.setMaterial(m);
      
                icompras.add(ic);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return icompras;

    }
    
    public List<ItemCompra> selectForItemCompra(int nome) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ItemCompra> icompras = new ArrayList<>();
       
        try {
            stmt = con.prepareStatement("SELECT * FROM camm.itemcompra inner join material on itemcompra.Cod_Material = material.cod_material\n" +
"inner join compra on itemcompra.cod_compra = ? group by cod_ItemCompra");
            stmt.setInt(1, nome);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                ItemCompra ic = new ItemCompra();
                Material m = new Material();
                Compra c = new Compra();
                
                m.setNome(rs.getString("Nome"));
                ic.setCod_ItemCompra(rs.getInt("Cod_ItemCompra"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
                ic.setMaterial(m);
                ic.setObservação(rs.getString("Observacao"));
                ic.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                ic.setValorUnitário(rs.getDouble("ValorUnitário"));
                ic.setValorTotal(rs.getDouble("ValorTotal"));
                
                icompras.add(ic);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return icompras;

    }

}
