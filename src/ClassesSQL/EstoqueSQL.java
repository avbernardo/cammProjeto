/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesSQL;

import camm.CAMM;
import camm.Cliente;
import camm.Estoque;
import camm.Estoque;
import camm.Fornecedor;
import camm.Material;
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

public class EstoqueSQL {
    
      public void insertEstoque(Estoque e) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO estoque (dataMovimentação,Peso_Quantidade,tipoMovimentação,Cod_Material,Cod_Estoque)VALUES(?,?,?,?,?)");
            stmt.setString(1, e.getDataMovimetação());
            stmt.setDouble(2, e.getPeso_Quantidade());
            stmt.setString(3, e.getTipoMovimentação());
            stmt.setInt(4, e.getMaterial().getCod_Material());
             stmt.setInt(5, e.getCod_Estoque());

            stmt.executeUpdate();

        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<Estoque> selectAllEstoque() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estoque> estoques = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from estoque inner join material on estoque.Cod_Material = material.Cod_Material where dataMovimentação between date_sub(now(),interval 30 day) and now() order by cod_estoque desc");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Estoque e = new Estoque();

                e.setDataMovimetação(rs.getString("dataMovimentação"));
                e.setTipoMovimentação(rs.getString("tipoMovimentação"));
                e.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                e.setCod_Estoque(rs.getInt("Cod_Estoque"));
                
                Material m = new Material();
                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
               
 
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
    
    public List<Estoque> selectAllEstoqueT() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estoque> estoques = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from estoque inner join material where estoque.Cod_Material = material.Cod_Material order by cod_estoque desc");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Estoque e = new Estoque();

                e.setDataMovimetação(rs.getString("dataMovimentação"));
                e.setTipoMovimentação(rs.getString("tipoMovimentação"));
                e.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                e.setCod_Estoque(rs.getInt("Cod_Estoque"));
                
                Material m = new Material();
                m.setCod_Material(rs.getInt("Cod_Material"));
                m.setNome(rs.getString("Nome"));
                m.setPreço_Kilo_Unidade(rs.getDouble("Preço_Kilo_Unidade"));
               
                
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
    
    public List<Estoque> selectForEstoqueT(String nome) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estoque> estoques = new ArrayList<>();
        nome = "%"+nome+"%";
        try {
            stmt = con.prepareStatement("select * from estoque inner join material on estoque.Cod_Material = material.cod_material where (date_format(dataMovimentação,\"%d/%m/%Y\")) like ? or dataMovimentação like ? or TipoMovimentação like ? or peso_quantidade like ? or nome like ?");
            stmt.setString(1, nome);
            stmt.setString(2, nome);
            stmt.setString(3, nome);
            stmt.setString(4, nome);
            stmt.setString(5, nome);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                 java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("dataMovimentação"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Estoque e = new Estoque();
                Material m = new Material();
                e.setDataMovimetação(dataFormatada);
                e.setTipoMovimentação(rs.getString("tipoMovimentação"));
                e.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                e.setCod_Estoque(rs.getInt("Cod_Estoque"));
                m.setNome(rs.getString("Nome"));
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
    public List<Estoque> selectForEstoque(String nome,Date data1,Date data2) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estoque> estoques = new ArrayList<>();
        nome = "%"+nome+"%";
        String data11=data1.toString();
        String data22=data2.toString();
        try {
            stmt = con.prepareStatement("select * from estoque inner join material on estoque.Cod_Material = material.cod_material where ((date_format(dataMovimentação,\"%d/%m/%Y\")) like ? or dataMovimentação like ? or TipoMovimentação like ? or peso_quantidade like ? or nome like ?) and ((DATE_FORMAT(estoque.dataMovimentação, '%Y-%m-%d') between ? and ?))");
            stmt.setString(1, nome);
            stmt.setString(2, nome);
            stmt.setString(3, nome);
            stmt.setString(4, nome);
            stmt.setString(5, nome);
            stmt.setString(6, data11);
            stmt.setString(7, data22);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                 java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("dataMovimentação"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Estoque e = new Estoque();
                Material m = new Material();
                e.setDataMovimetação(dataFormatada);
                e.setTipoMovimentação(rs.getString("tipoMovimentação"));
                e.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                e.setCod_Estoque(rs.getInt("Cod_Estoque"));
                m.setNome(rs.getString("Nome"));
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
    
    public List<Estoque> selectForEstoqueData(Date data1, Date data2) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Estoque> estoques = new ArrayList<>();
       
       String data11=data1.toString();
       String data22=data2.toString();
   
        try {
            stmt = con.prepareStatement("SELECT * FROM estoque inner join material WHERE (DATE_FORMAT(estoque.dataMovimentação, '%Y-%m-%d') between ? and ?) and material.Cod_Material = estoque.Cod_Material order by dataMovimentação desc");
            stmt.setString(1,data11);
            stmt.setString(2,data22);
            rs = stmt.executeQuery();

            while (rs.next()) {
                 java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("dataMovimentação"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Estoque e = new Estoque();
                Material m = new Material();
                e.setDataMovimetação(dataFormatada);
                e.setTipoMovimentação(rs.getString("tipoMovimentação"));
                e.setPeso_Quantidade(rs.getDouble("Peso_Quantidade"));
                e.setCod_Estoque(rs.getInt("Cod_Estoque"));
                m.setNome(rs.getString("Nome"));
                m.setPorQuantidade(rs.getBoolean("porQuantidade"));
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

    public void updateEstoque(Estoque e) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE estoque SET dataMovimentação = ?,TipoMovimentação = ?,Peso_Quantidade = ?,Cod_Material = ? WHERE Cod_Estoque = ?");
            
            stmt.setString(1, e.getDataMovimetação());
            stmt.setString(2, e.getTipoMovimentação());
            stmt.setDouble(3, e.getPeso_Quantidade());
            stmt.setInt(4, e.getMaterial().getCod_Material());
            stmt.setInt(5, e.getCod_Estoque());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    public void deleteEstoque(Estoque e) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM estoque WHERE Cod_Estoque = ?");
            stmt.setInt(1, e.getCod_Estoque());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
}
