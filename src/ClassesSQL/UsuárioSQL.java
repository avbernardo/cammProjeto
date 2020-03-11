
package ClassesSQL;

import Interfaces.ManterUsuário;
import camm.CAMM;
import camm.Usuário;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class UsuárioSQL {
    
    public void insertUsuário(Usuário usuário) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = md.digest(usuário.getSenha().getBytes("UTF-8"));
        byte messageDigest1[] = md.digest(usuário.getRespostaEspecial().getBytes("UTF-8"));
        
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        
        for(byte b:messageDigest){
            sb.append(String.format("%02X", 0xFF & b));
        }
        for(byte b:messageDigest1){
            sb1.append(String.format("%02X", 0xFF & b));
        }
        
        String senhaHex = sb.toString();
        String respostaHex = sb1.toString();
        try {
            stmt = con.prepareStatement("INSERT INTO usuário (login,senha,perguntaEspecial,respostaEspecial)VALUES(?,?,?,?)");
            stmt.setString(1, usuário.getLogin());
            stmt.setString(2, senhaHex);
            stmt.setString(3, usuário.getPerguntaEspecial());
            stmt.setString(4, respostaHex);
       
            stmt.executeUpdate();

            
            ManterUsuário.confirmação = true;
              
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1062)
            JOptionPane.showMessageDialog(null, "Nome de Usuário já utilizado");
            else{
                System.out.println(ex);
            }
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }

    public List<Usuário> selectAllUsuário() {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuário> usuários = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuário");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuário usuário = new Usuário();

                usuário.setCod_Usuário(rs.getInt("Cod_Usuário"));
                usuário.setLogin(rs.getString("login"));
                usuário.setSenha(rs.getString("senha"));
                usuário.setPerguntaEspecial(rs.getString("perguntaEspecial"));
                usuário.setRespostaEspecial(rs.getString("respostaEspecial"));
       
                usuários.add(usuário);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuárioSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return usuários;

    }
    public List<Usuário> selectForUsuário(String login) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuário> usuários = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuário WHERE login = ?");
            stmt.setString(1, "%"+login+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Usuário usuário = new Usuário();

                usuário.setCod_Usuário(rs.getInt("Cod_Usuário"));
                usuário.setLogin(rs.getString("login"));
                usuário.setSenha(rs.getString("senha"));
                usuário.setPerguntaEspecial(rs.getString("perguntaEspecial"));
                usuário.setRespostaEspecial(rs.getString("respostaEspecial"));
                usuários.add(usuário);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuárioSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return usuários;

    }

    public void updateUsuário(Usuário usuário) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        
         MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = md.digest(usuário.getSenha().getBytes("UTF-8"));
        byte messageDigest1[] = md.digest(usuário.getRespostaEspecial().getBytes("UTF-8"));
        
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        
        for(byte b:messageDigest){
            sb.append(String.format("%02X", 0xFF & b));
        }
        
        for(byte b:messageDigest1){
            sb.append(String.format("%02X", 0xFF & b));
        }
        
        String senhaHex = sb.toString();
        String respostaHex = sb1.toString();

        try {
            stmt = con.prepareStatement("UPDATE usuário SET login = ?,senha = ?,perguntaEspecial = ?,respostaEspecial = ? WHERE Cod_Usuário = ?");
            
            stmt.setString(1, usuário.getLogin());
            stmt.setString(2, senhaHex);
            stmt.setString(3, usuário.getPerguntaEspecial());
            stmt.setString(4, respostaHex);
            stmt.setInt(5, usuário.getCod_Usuário());
            

            stmt.executeUpdate();

           
        } catch (SQLException ex) {
            
             if(ex.getErrorCode()==1062)
            JOptionPane.showMessageDialog(null, "Nome de Usuário já utilizado");
            else{
                System.out.println(ex);
            }
            
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
    public void updateUsuárioLogin(String login,String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;
        
         MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
        
        StringBuilder sb = new StringBuilder();
        
        for(byte b:messageDigest){
            sb.append(String.format("%02X", 0xFF & b));
        }
        
        String senhaHex = sb.toString();

        try {
            stmt = con.prepareStatement("UPDATE usuário SET senha = ? WHERE login = ?");
            
            stmt.setString(1, senhaHex);
            stmt.setString(2, login);
                        

            stmt.executeUpdate();

     
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    public void deleteUsuário(Usuário usuário) {

        Connection con = CAMM.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM usuário WHERE Cod_Usuário = ?");
            stmt.setInt(1, usuário.getCod_Usuário());

            stmt.executeUpdate();

          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            CAMM.closeConnection(con, stmt);
        }

    }
    
    public boolean checkLogin(String login, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Connection con = CAMM.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;
        
         MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
        
        StringBuilder sb = new StringBuilder();
        
        for(byte b:messageDigest){
            sb.append(String.format("%02X", 0xFF & b));
        }
        
        String senhaHex = sb.toString();

        try {

            stmt = con.prepareStatement("SELECT * FROM usuário WHERE login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senhaHex);

            rs = stmt.executeQuery();

            if (rs.next()) {

                
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuárioSQL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CAMM.closeConnection(con, stmt, rs);
        }

        return check;

    }
    
}
