/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import Interfaces.AtualizarMaterial;
import camm.CAMM;
import camm.Estoque;
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


public class MaterialSQL {
    
     public void insertMaterial(Material m) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO material (Nome,Preço_Kilo_Unidade,porQuantidade)VALUES(?,?,?)");
            stmt.setString(1, m.getNome());
            stmt.setDouble(2, m.getPreço_Kilo_Unidade());
            stmt.setBoolean(3, m.isPorQuantidade());
           
         
            stmt.executeUpdate();

           
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1062){
            JOptionPane.showMessageDialog(null, "Material já cadastrado, consulte materiais inativos");
            }
            else{
                System.out.println(ex);
            }
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<Material> selectAllMaterial() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Material> materiais = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM material where ativo = 1 order by cod_Material");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Material m = new Material();

                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setTotal_estoque(rs.getDouble("total_estoque"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
            
                materiais.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return materiais;

    }
    
    public List<Material> selectAllMaterialInativo() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Material> materiais = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM material where ativo = 0 order by cod_Material");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Material m = new Material();

                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setTotal_estoque(rs.getDouble("total_estoque"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
            
                materiais.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return materiais;

    }
    
    public List<Material> selectAllMaterialNome() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Material> materiais = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM material where ativo = 1 order by nome");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Material m = new Material();

                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setTotal_estoque(rs.getDouble("total_estoque"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
            
                materiais.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return materiais;

    }
    public List<Material> selectForMaterial(String nome) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Material> materiais = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM material WHERE ativo = 1 AND(Nome like ? or Preço_Kilo_Unidade like ?)");
            stmt.setString(1, "%"+nome+"%");
            stmt.setString(2, "%"+nome+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Material m = new Material();

                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setTotal_estoque(rs.getDouble("total_estoque"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
                materiais.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return materiais;

    }
    
     public List<Material> selectForMaterialInativo(String nome) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Material> materiais = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM material WHERE ativo = 0 AND(Nome like ? or Preço_Kilo_Unidade like ?)");
            stmt.setString(1, "%"+nome+"%");
            stmt.setString(2, "%"+nome+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Material m = new Material();

                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setTotal_estoque(rs.getDouble("total_estoque"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
                materiais.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return materiais;

    }
    
    
    
    public List<Material> selectForMaterialCod(int cod) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Material> materiais = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM material WHERE Cod_Material = ?");
            stmt.setInt(1,cod);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Material m = new Material();

                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setTotal_estoque(rs.getDouble("total_estoque"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
                materiais.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return materiais;

    }
    
     public List<Material> selectForMaterialNome(String nome) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Material> materiais = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM material WHERE nome = ?");
            stmt.setString(1,nome);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Material m = new Material();

                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setTotal_estoque(rs.getDouble("total_estoque"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
                materiais.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MaterialSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return materiais;

    }
    
    public void updateDeleteMaterial(Material m) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE material SET ativo = 0 WHERE Cod_Material = ?");
            
         
            stmt.setInt(1, m.getCod_Material());

            stmt.executeUpdate();

         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
    public void updateReativaMaterial(Material m) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE material SET ativo = 1 WHERE Cod_Material = ?");
            
         
            stmt.setInt(1, m.getCod_Material());

            stmt.executeUpdate();

         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public void updateMaterial(Material m) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE material SET Nome = ?,Preço_Kilo_Unidade = ?,total_estoque = ?, porQuantidade = ? WHERE Cod_Material = ?");
            
            stmt.setString(1, m.getNome());
            stmt.setDouble(2, m.getPreço_Kilo_Unidade());
            stmt.setDouble(3, m.getTotal_estoque());
            stmt.setBoolean(4, m.isPorQuantidade());
            stmt.setInt(5, m.getCod_Material());
            
      
            stmt.executeUpdate();

          
        } catch (SQLException ex) {
             if(ex.getErrorCode()==1062)
            JOptionPane.showMessageDialog(null, "Material já cadastrado");
             
            else{
                System.out.println(ex);
            }
             
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
    public void updateMaterialReativa(Material m) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE material SET Nome = ?,Preço_Kilo_Unidade = ?,total_estoque = ?, porQuantidade = ?,ativo = 1 WHERE Cod_Material = ?");
            
            stmt.setString(1, m.getNome());
            stmt.setDouble(2, m.getPreço_Kilo_Unidade());
            stmt.setDouble(3, m.getTotal_estoque());
            stmt.setBoolean(4, m.isPorQuantidade());
            stmt.setInt(5, m.getCod_Material());
            
      
            stmt.executeUpdate();

           
        } catch (SQLException ex) {
             if(ex.getErrorCode()==1062)
            JOptionPane.showMessageDialog(null, "Material já cadastrado");
             
            else{
                System.out.println(ex);
            }
             
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
     public void updateMaterialInventario(Material m) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE material set total_estoque = ? WHERE Cod_Material = ?");
            
            stmt.setDouble(1, m.getTotal_estoque());
            stmt.setInt(2, m.getCod_Material());
            
        
            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    public void deleteMaterial(Material m) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM material WHERE Cod_Material = ?");
            stmt.setInt(1, m.getCod_Material());

            stmt.executeUpdate();

 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
}
