/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import camm.CAMM;
import camm.Cliente;
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
public class ClienteSQL {
    
     public void insertCliente(Cliente c) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (Nome,Contato,Bairro,Logradouro,Número,Cidade)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getContato());
            stmt.setString(3, c.getBairro());
            stmt.setString(4, c.getLogradouro());
            stmt.setString(5, c.getNúmero());
            stmt.setString(6,c.getCidade());

            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<Cliente> selectAllCliente() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente where ativo = 1 order by nome");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setNome(rs.getString("Nome"));
                c.setContato(rs.getString("Contato"));
                c.setBairro(rs.getString("Bairro"));
                c.setLogradouro(rs.getString("Logradouro"));
                c.setNúmero(rs.getString("Número"));
                c.setCidade(rs.getString("Cidade"));
                clientes.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return clientes;

    }
    
    public List<Cliente> selectAllClienteInativo() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente where ativo = 0 order by nome");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setNome(rs.getString("Nome"));
                c.setContato(rs.getString("Contato"));
                c.setBairro(rs.getString("Bairro"));
                c.setLogradouro(rs.getString("Logradouro"));
                c.setNúmero(rs.getString("Número"));
                c.setCidade(rs.getString("Cidade"));
                clientes.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return clientes;

    }
    public List<Cliente> selectForCliente(String pesquisa) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();
        pesquisa = "%"+pesquisa+"%";
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE ativo = 1 AND (Nome like ? OR Bairro like ? OR Contato like ? OR Logradouro like ? OR Cidade like ?)");
            stmt.setString(1,pesquisa);
            stmt.setString(2, pesquisa);
            stmt.setString(3, pesquisa);
            stmt.setString(4,pesquisa);
            stmt.setString(5,pesquisa);
            
           
            
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Cliente c = new Cliente();

                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setNome(rs.getString("Nome"));
                c.setContato(rs.getString("Contato"));
                c.setBairro(rs.getString("Bairro"));
                c.setLogradouro(rs.getString("Logradouro"));
                c.setNúmero(rs.getString("Número"));
                c.setCidade(rs.getString("Cidade"));
                clientes.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return clientes;

    }
    
    public List<Cliente> selectForClienteInativo(String pesquisa) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();
        pesquisa = "%"+pesquisa+"%";
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE ativo = 0 AND (Nome like ? OR Bairro like ? OR Contato like ? OR Logradouro like ? OR Cidade like ?)");
            stmt.setString(1,pesquisa);
            stmt.setString(2, pesquisa);
            stmt.setString(3, pesquisa);
            stmt.setString(4,pesquisa);
            stmt.setString(5,pesquisa);
           
            
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Cliente c = new Cliente();

                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setNome(rs.getString("Nome"));
                c.setContato(rs.getString("Contato"));
                c.setBairro(rs.getString("Bairro"));
                c.setLogradouro(rs.getString("Logradouro"));
                c.setNúmero(rs.getString("Número"));
                c.setCidade(rs.getString("Cidade"));
                clientes.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return clientes;

    }

    public void updateCliente(Cliente c) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET Nome = ?,Contato = ?,Bairro = ?,Logradouro = ?,Número = ?,Cidade = ? WHERE ID_Cliente = ?");
            
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getContato());
            stmt.setString(3, c.getBairro());
            stmt.setString(4, c.getLogradouro());
            stmt.setString(5, c.getNúmero());
            stmt.setString(6, c.getCidade());
            stmt.setInt(7, c.getID_Cliente());

            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    public void deleteCliente(Cliente c) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE ID_Cliente = ?");
            stmt.setInt(1, c.getID_Cliente());

            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
     public void updateDeleteCliente(Cliente c) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET ativo = ? WHERE ID_Cliente = ?");
            
            stmt.setBoolean(1, c.isAtivo());
            stmt.setInt(2, c.getID_Cliente());

            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
          public void updateReativaCliente(Cliente c) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET ativo = 1 WHERE ID_Cliente = ?");
            
            
            stmt.setInt(1, c.getID_Cliente());

            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

}
    

