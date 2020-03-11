/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import camm.CAMM;
import camm.Cliente;
import camm.Compra;
import camm.Venda;
import camm.ItemVenda;
import camm.Fornecedor;
import camm.ItemCompra;
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

/**
 *
 * @author Andre
 */
public class ItemVendaSQL {
    
    public void insertItemVenda(ItemVenda c) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO ItemVenda (ValorUnitário,Observacao,Peso_Quantidade,ValorTotal,Cod_Venda,Cod_Material)VALUES(?,?,?,?,?,?)");
            stmt.setDouble(1, c.getValorUnitário());
            stmt.setString(2, c.getObservação());
            stmt.setDouble(3, c.getPeso_Quantidade());
            stmt.setDouble(4, c.getValorTotal());
            stmt.setInt(5, c.getVenda().getCod_Venda());
            stmt.setInt(6, c.getMaterial().getCod_Material());
      
           

            stmt.executeUpdate();

           
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<ItemVenda> selectAllItemVenda() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ItemVenda> ivendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from itemvenda inner join material on itemvenda.Cod_Material = material.Cod_Material\n"
                    + "inner join venda on itemvenda.Cod_Venda = venda.Cod_Venda");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ItemVenda ic = new ItemVenda();

                ic.setCod_ItemVenda(rs.getInt("Cod_ItemVenda"));
                ic.setObservação(rs.getString("Observacao"));
                ic.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                ic.setValorUnitário(rs.getDouble("ValorUnitário"));
                ic.setValorTotal(rs.getDouble("ValorTotal"));
                
                Material m = new Material();
                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
               
                ic.setMaterial(m);
      
                ivendas.add(ic);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return ivendas;

    }
    
    public List<ItemVenda> selectForItemVenda(int nome) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ItemVenda> ivendas = new ArrayList<>();
       
        try {
            stmt = con.prepareStatement("SELECT * FROM itemvenda inner join material on itemvenda.Cod_Material = material.cod_material\n" +
"inner join venda on itemvenda.cod_venda = ? group by cod_ItemVenda");
            stmt.setInt(1, nome);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                ItemVenda ic = new ItemVenda();
                Material m = new Material();
                Venda c = new Venda();
                
                m.setNome(rs.getString("Nome"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
                ic.setCod_ItemVenda(rs.getInt("Cod_ItemVenda"));
                ic.setMaterial(m);
                ic.setObservação(rs.getString("Observacao"));
                ic.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                ic.setValorUnitário(rs.getDouble("ValorUnitário"));
                ic.setValorTotal(rs.getDouble("ValorTotal"));
                
                ivendas.add(ic);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return ivendas;

    }
    
   
    
}
