/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import camm.CAMM;
import camm.Inventário;
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

public class InventárioSQL {
    
     public void insertInventário(Inventário i) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO inventário (dataMovimentação,Peso_Quantidade,tipoMovimentação,Cod_Material,IdInventário)VALUES(?,?,?,?,?)");
            stmt.setString(1, i.getDataMovimentação());
            stmt.setDouble(2, i.getPeso_Quantidade());
            stmt.setString(3, i.getTipoMovimentação());
            stmt.setInt(4, i.getMaterial().getCod_Material());
             stmt.setInt(5, i.getIdInventário());

            stmt.executeUpdate();

          
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<Inventário> selectAllInventário() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Inventário> inventários = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from inventário inner join material on inventário.Cod_Material = material.Cod_Material order by dataMovimentação desc");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Inventário i = new Inventário();

                i.setDataMovimentação(rs.getString("dataMovimentação"));
                i.setTipoMovimentação(rs.getString("tipoMovimentação"));
                i.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                i.setIdInventário(rs.getInt("IdInventário"));
                
                Material m = new Material();
                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
               
                
                i.setMaterial(m);
                inventários.add(i);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return inventários;

    }
    public List<Inventário> selectForInventário(String nome) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Inventário> inventários = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM inventário inner join material on inventário.Cod_Material = material.cod_material where dataMovimentação like ? or tipoMovimentação like ? or Peso_Quantidade like ? or nome like ? ");
            stmt.setString(1, "%"+nome+"%");
            stmt.setString(2, "%"+nome+"%");
            stmt.setString(3, "%"+nome+"%");
            stmt.setString(4, "%"+nome+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Inventário i = new Inventário();
                Material m = new Material();
                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
               i.setDataMovimentação(rs.getString("dataMovimentação"));
                i.setTipoMovimentação(rs.getString("tipoMovimentação"));
                i.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                i.setIdInventário(rs.getInt("IdInventário"));
                i.setMaterial(m);
                inventários.add(i);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return inventários;

    }

    public void updateInventário(Inventário i) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE inventário SET dataMovimentação = ?,TipoMovimentação = ?,Peso_Quantidade = ?,Cod_Material = ? WHERE IdInventário = ?");
            
            stmt.setString(1, i.getDataMovimentação());
            stmt.setString(2, i.getTipoMovimentação());
            stmt.setDouble(3, i.getPeso_Quantidade());
            stmt.setInt(4, i.getMaterial().getCod_Material());
            stmt.setInt(5, i.getIdInventário());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    public void deleteInventário(Inventário i) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM inventário WHERE IdInventário = ?");
            stmt.setInt(1, i.getIdInventário());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
}
