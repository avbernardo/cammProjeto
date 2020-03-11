
package ClassesSQL;

import camm.CAMM;
import camm.Cliente;
import camm.Venda;
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


public class VendaSQL {
    
     public void insertVenda(Venda v, String data) {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO venda (ValorTotal,Data,Cod_Venda,ID_Cliente)VALUES(?,?,?,?)");
            stmt.setDouble(1, v.getValorTotal());
            stmt.setString(2, data);
            stmt.setInt(3, v.getCod_Venda());
            stmt.setInt(4, v.getCliente().getID_Cliente());
           

            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
     
     public int selectLastInsert(){
        
        int ultima = 0;
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = con.prepareStatement("SELECT Cod_Venda FROM venda ORDER BY Cod_Venda DESC LIMIT 1");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ultima = rs.getInt("Cod_Venda");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }
        
      

        return ultima;
        
    }

    public List<Venda> selectAllVenda() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM camm.venda inner join cliente on venda.ID_Cliente = cliente.ID_Cliente where data between date_sub(now(),interval 30 day) and now() order by cod_venda desc");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Venda v = new Venda();

                v.setValorTotal(rs.getDouble("ValorTotal"));
                v.setData(rs.getString("Data"));
                v.setCod_Venda(rs.getInt("Cod_Venda"));
                
                Cliente f = new Cliente();
                f.setID_Cliente(rs.getInt("ID_Cliente"));
                f.setNome(rs.getString("Nome"));
                f.setContato(rs.getString("Contato"));
                f.setBairro(rs.getString("Bairro"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setNúmero(rs.getString("Número"));
                
                v.setCliente(f);
                vendas.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return vendas;

    }
    
    public List<Venda> selectAllVendaT() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM camm.venda inner join cliente on venda.ID_Cliente = cliente.ID_Cliente order by cod_venda desc");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Venda v = new Venda();

                v.setValorTotal(rs.getDouble("ValorTotal"));
                v.setData(rs.getString("Data"));
                v.setCod_Venda(rs.getInt("Cod_Venda"));
                
                Cliente f = new Cliente();
                f.setID_Cliente(rs.getInt("ID_Cliente"));
                f.setNome(rs.getString("Nome"));
                f.setContato(rs.getString("Contato"));
                f.setBairro(rs.getString("Bairro"));
                f.setLogradouro(rs.getString("Logradouro"));
                f.setNúmero(rs.getString("Número"));
                
                v.setCliente(f);
                vendas.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return vendas;

    }
    
    public List<Venda> selectAllVendaSemana() throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from venda where YEARWEEK(data,1) = yearweek(CURDATE(),1)");
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);

                Venda v = new Venda();

                v.setValorTotal(rs.getDouble("ValorTotal"));
                v.setData(dataFormatada);
                v.setCod_Venda(rs.getInt("Cod_Venda"));
                 
                vendas.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return vendas;

    }
    
    public List<Venda> selectAllVendaDia() throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM venda WHERE DATE(Data) = curdate()");
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);

                Venda v = new Venda();

                v.setValorTotal(rs.getDouble("ValorTotal"));
                v.setData(dataFormatada);
                v.setCod_Venda(rs.getInt("Cod_Venda"));
                 
                vendas.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return vendas;

    }
    
    
    
     public List<Venda> selectAllVendaMes() throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from venda where month(data) = month(curdate())");
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);

                Venda v = new Venda();

                v.setValorTotal(rs.getDouble("ValorTotal"));
                v.setData(dataFormatada);
                v.setCod_Venda(rs.getInt("Cod_Venda"));
                 
                vendas.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return vendas;

    }
    public List<Venda> selectForVenda(String nome,Date data1,Date data2) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();
        String data11=data1.toString();
        String data22=data2.toString();
        try {
            stmt = con.prepareStatement("SELECT * from venda inner join cliente on venda.ID_Cliente = cliente.ID_Cliente where ((date_format(venda.data,\"%d/%m/%Y\")) like ? or venda.data like ? or venda.ValorTotal like ? or cliente.nome like ?) and ((DATE_FORMAT(venda.Data, '%Y-%m-%d') between ? and ?))");
            stmt.setString(1, "%"+nome+"%");
            stmt.setString(2, "%"+nome+"%");
            stmt.setString(3, "%"+nome+"%");
            stmt.setString(4, "%"+nome+"%");
            stmt.setString(5, data11);
            stmt.setString(6, data22);
       
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Venda v = new Venda();

              
                Cliente c = new Cliente();

                v.setCod_Venda(rs.getInt("Cod_Venda"));
                v.setData(dataFormatada);
                v.setValorTotal(rs.getDouble("ValorTotal"));
                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setNome(rs.getString("Nome"));
                v.setCliente(c);
                vendas.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return vendas;

    }
    
    public List<Venda> selectForVendaData(Date data1,Date data2) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();
        String data11=data1.toString();
        String data22=data2.toString();
        try {
            stmt = con.prepareStatement("SELECT * FROM venda inner join cliente WHERE (DATE_FORMAT(venda.Data, '%Y-%m-%d') between ? and ?) and cliente.id_cliente = venda.id_cliente order by Data desc");
            stmt.setString(1,data11);
            stmt.setString(2,data22);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Venda c = new Venda();
                Cliente f = new Cliente();
                
                c.setCod_Venda(rs.getInt("Cod_Venda"));
                c.setData(dataFormatada);
                c.setValorTotal(rs.getDouble("ValorTotal"));
                f.setID_Cliente(rs.getInt("ID_Cliente"));
                f.setNome(rs.getString("Nome"));
                c.setCliente(f);
                vendas.add(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return vendas;

    }
    
    
    public List<Venda> selectForVendaT(String nome) throws ParseException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from venda inner join cliente on venda.ID_Cliente = cliente.ID_Cliente where ((date_format(venda.data,\"%d/%m/%Y\")) like ? or venda.data like ? or venda.ValorTotal like ? or cliente.nome like ?)");
            stmt.setString(1, "%"+nome+"%");
            stmt.setString(2, "%"+nome+"%");
            stmt.setString(3, "%"+nome+"%");
            stmt.setString(4, "%"+nome+"%");
       
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("Data"));
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy, HH:mm").format(date);
                Venda v = new Venda();

              
                Cliente c = new Cliente();

                v.setCod_Venda(rs.getInt("Cod_Venda"));
                v.setData(dataFormatada);
                v.setValorTotal(rs.getDouble("ValorTotal"));
                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setNome(rs.getString("Nome"));
                v.setCliente(c);
                vendas.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return vendas;

    }

    
}
