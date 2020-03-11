/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import camm.CAMM;
import camm.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class FornecedorSQL {
    
    public void insertFornecedor(Fornecedor f) {
        
       
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO fornecedor (Nome,Contato,Bairro,Logradouro,Número)VALUES(?,?,?,?,?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getContato());
            stmt.setString(3, f.getBairro());
            stmt.setString(4, f.getLogradouro());
            stmt.setString(5, f.getNúmero());

            stmt.executeUpdate();

            
            
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            
        } finally {
            CAMM.closeConnection(con, stmt);
        }
        
    }

    public List<Fornecedor> selectAllFornecedor() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor where ativo = 1 order by nome");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedor f = new Fornecedor();

                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                f.setContato(rs.getString("Contato"));
                f.setBairro(rs.getString("Bairro"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setNúmero(rs.getString("Número"));
                fornecedores.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return fornecedores;

    }
    
    public List<Fornecedor> selectAllFornecedorInativo() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor where ativo = 0 order by nome");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedor f = new Fornecedor();

                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                f.setContato(rs.getString("Contato"));
                f.setBairro(rs.getString("Bairro"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setNúmero(rs.getString("Número"));
                fornecedores.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return fornecedores;

    }
    public List<Fornecedor> selectForFornecedor(String pesquisa) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Fornecedor> fornecedores = new ArrayList<>();
        pesquisa = "%"+pesquisa+"%";
        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE ativo = 1 AND (Nome like ? OR Bairro like ? OR Contato like ? OR Logradouro like ?)");
            stmt.setString(1, pesquisa);
            stmt.setString(2, pesquisa);
            stmt.setString(3, pesquisa);
            stmt.setString(4,pesquisa);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Fornecedor f = new Fornecedor();

                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                f.setContato(rs.getString("Contato"));
                f.setBairro(rs.getString("Bairro"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setNúmero(rs.getString("Número"));
                fornecedores.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return fornecedores;

    }
    
    public List<Fornecedor> selectForFornecedorInativo(String pesquisa) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Fornecedor> fornecedores = new ArrayList<>();
        pesquisa = "%"+pesquisa+"%";
        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE ativo = 0 AND (Nome like ? OR Bairro like ? OR Contato like ? OR Logradouro like ?)");
            stmt.setString(1, pesquisa);
            stmt.setString(2, pesquisa);
            stmt.setString(3, pesquisa);
            stmt.setString(4,pesquisa);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Fornecedor f = new Fornecedor();

                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                f.setContato(rs.getString("Contato"));
                f.setBairro(rs.getString("Bairro"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setNúmero(rs.getString("Número"));
                fornecedores.add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return fornecedores;

    }

    public void updateFornecedor(Fornecedor f) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE fornecedor SET Nome = ?,Contato = ?,Bairro = ?,Logradouro = ?,Número = ? WHERE ID_Fornecedor = ?");
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getContato());
            stmt.setString(3, f.getBairro());
            stmt.setString(4, f.getLogradouro());
            stmt.setString(5, f.getNúmero());
            stmt.setInt(6, f.getID_Fornecedor());

            stmt.executeUpdate();

           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
    public void updateDeleteFornecedor(Fornecedor f) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE fornecedor SET ativo = 0 WHERE ID_Fornecedor = ?");
            
            
            stmt.setInt(1, f.getID_Fornecedor());

            stmt.executeUpdate();

         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    public void updateReativaFornecedor(Fornecedor f) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE fornecedor SET ativo = 1 WHERE ID_Fornecedor = ?");
            
            
            stmt.setInt(1, f.getID_Fornecedor());

            stmt.executeUpdate();

         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    public void deleteFornecedor(Fornecedor f) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM fornecedor WHERE ID_Fornecedor = ?");
            stmt.setInt(1, f.getID_Fornecedor());

            stmt.executeUpdate();

          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
}
