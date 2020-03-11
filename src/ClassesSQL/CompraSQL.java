/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import camm.CAMM;
import camm.Cliente;
import camm.Compra;
import camm.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class CompraSQL {
    
    public void insertCompra(Compra c, String data) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO compra (ValorTotal,Data,Cod_Compra,ID_Fornecedor)VALUES(?,?,?,?)");
            stmt.setDouble(1, c.getValorTotal());
            stmt.setString(2, data);
            stmt.setInt(3, c.getCod_Compra());
            stmt.setInt(4, c.getFornecedor().getID_Fornecedor());
           

            stmt.executeUpdate();

           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<Compra> selectAllCompra() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM camm.compra inner join fornecedor on compra.ID_Fornecedor = fornecedor.ID_Fornecedor where data between date_sub(now(),interval 30 day) and now() order by cod_compra desc");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Compra c = new Compra();

                c.setValorTotal(rs.getDouble("ValorTotal"));
                c.setData(rs.getString("Data"));
                c.setCod_Compra(rs.getInt("Cod_Compra"));
                
                Fornecedor f = new Fornecedor();
                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                f.setContato(rs.getString("Contato"));
                f.setBairro(rs.getString("Bairro"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setNúmero(rs.getString("Número"));
                
                c.setFornecedor(f);
                compras.add(c);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return compras;

    }
    
     public List<Compra> selectAllCompraT() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM camm.compra inner join fornecedor on compra.ID_Fornecedor = fornecedor.ID_Fornecedor order by cod_compra desc");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Compra c = new Compra();

                c.setValorTotal(rs.getDouble("ValorTotal"));
                c.setData(rs.getString("Data"));
                c.setCod_Compra(rs.getInt("Cod_Compra"));
                
                Fornecedor f = new Fornecedor();
                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                f.setContato(rs.getString("Contato"));
                f.setBairro(rs.getString("Bairro"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setNúmero(rs.getString("Número"));
                
                c.setFornecedor(f);
                compras.add(c);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return compras;

    }
     
    
    public List<Compra> selectAllCompraSemana() throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from compra where YEARWEEK(data,1) = yearweek(CURDATE(),1)");
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Compra c = new Compra();

                c.setValorTotal(rs.getDouble("ValorTotal"));
                c.setData(dataFormatada);
                c.setCod_Compra(rs.getInt("Cod_Compra"));
                 
                compras.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return compras;

    }
    
    public List<Compra> selectAllCompraDia() throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM compra WHERE DATE(Data) = curdate()");
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Compra c = new Compra();

                c.setValorTotal(rs.getDouble("ValorTotal"));
                c.setData(dataFormatada);
                c.setCod_Compra(rs.getInt("Cod_Compra"));
                 
                compras.add(c);
            }

        } catch (SQLException ex) {
           //JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return compras;

    }
    
    
    
    public List<Compra> selectAllCompraMes() throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from compra where month(data) = month(curdate())");
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Compra c = new Compra();

                c.setValorTotal(rs.getDouble("ValorTotal"));
                c.setData(dataFormatada);
                c.setCod_Compra(rs.getInt("Cod_Compra"));
                 
                compras.add(c);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return compras;

    }
    
    public int selectLastInsert(){
        
        int ultima = 0;
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = con.prepareStatement("SELECT Cod_Compra FROM compra ORDER BY Cod_Compra DESC LIMIT 1");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ultima = rs.getInt("Cod_Compra");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }
        
      

        return ultima;
        
    }
    public List<Compra> selectForCompra(String nome,Date data1,Date data2) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();
        nome = "%"+nome+"%";
        String data11=data1.toString();
        String data22=data2.toString();
        try {
            stmt = con.prepareStatement("SELECT * from compra inner join fornecedor on compra.ID_fornecedor = fornecedor.ID_fornecedor where ((date_format(compra.data,\"%d/%m/%Y\")) like ? or compra.data like ? or compra.ValorTotal like ? or fornecedor.nome like ?) and ((DATE_FORMAT(compra.Data, '%Y-%m-%d') between ? and ?))");
            stmt.setString(1, nome);
            stmt.setString(2, nome);
            stmt.setString(3, nome);
            stmt.setString(4, nome);
            stmt.setString(5, data11);
            stmt.setString(6, data22);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
             java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data"));
             String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Compra c = new Compra();
                Fornecedor f = new Fornecedor();
                
                c.setCod_Compra(rs.getInt("Cod_Compra"));
                c.setData(dataFormatada);
                c.setValorTotal(rs.getDouble("ValorTotal"));
                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                c.setFornecedor(f);
                compras.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return compras;

    }
    
     public List<Compra> selectForCompraData(Date data1,Date data2) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();
        String data11=data1.toString();
        String data22=data2.toString();
        try {
            stmt = con.prepareStatement("SELECT * FROM compra inner join fornecedor WHERE (DATE_FORMAT(compra.Data, '%Y-%m-%d') between ? and ?) and fornecedor.Id_Fornecedor = compra.Id_Fornecedor order by Data desc");
            stmt.setString(1,data11);
            stmt.setString(2,data22);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Compra c = new Compra();
                Fornecedor f = new Fornecedor();
                
                c.setCod_Compra(rs.getInt("Cod_Compra"));
                c.setData(dataFormatada);
                c.setValorTotal(rs.getDouble("ValorTotal"));
                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                c.setFornecedor(f);
                compras.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return compras;

    }
    
    public List<Compra> selectForCompraT(String nome) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Compra> compras = new ArrayList<>();
        nome = "%"+nome+"%";
        try {
            stmt = con.prepareStatement("SELECT * from compra inner join fornecedor on compra.ID_fornecedor = fornecedor.ID_fornecedor where ((date_format(compra.data,\"%d/%m/%Y\")) like ? or compra.data like ? or compra.ValorTotal like ? or fornecedor.nome like ?)");
            stmt.setString(1, nome);
            stmt.setString(2, nome);
            stmt.setString(3, nome);
            stmt.setString(4, nome);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Compra c = new Compra();
                Fornecedor f = new Fornecedor();
                
                c.setCod_Compra(rs.getInt("Cod_Compra"));
                c.setData(dataFormatada);
                c.setValorTotal(rs.getDouble("ValorTotal"));
                f.setID_Fornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                c.setFornecedor(f);
                compras.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return compras;

    }

    public void updateCompra(Compra c) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE compra SET Data = ?,ValorTotal = ?,ID_Fornecedor = ? WHERE Cod_Compra = ?");
            
            stmt.setString(1, c.getData());
            stmt.setDouble(2, c.getValorTotal());
            stmt.setInt(3, c.getFornecedor().getID_Fornecedor());
            stmt.setInt(4, c.getCod_Compra());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    public void deleteCompra(Compra c) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM compra WHERE Cod_Compra = ?");
            stmt.setInt(1, c.getCod_Compra());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
}
