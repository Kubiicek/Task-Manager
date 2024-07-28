package entites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Consultas {
	
	public void salvarUsuario(String usuario, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowsAffected = 0;
		try {
			conn = DB.getConnection();
			
			String sql = "INSERT INTO usuarios (nome, senha) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, usuario);
			pstmt.setString(2, password);
			
			rowsAffected = pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo corretamente");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void consultarUsuario(String user, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String usuarioCorreto = null;
		String passCorreto = null;
		
		 try {
			 conn = DB.getConnection();
			 
			 String sql = "SELECT nome, senha FROM usuarios WHERE nome = ? AND senha = ?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, user);
			 pstmt.setString(2, pass);
			 
			 rs = pstmt.executeQuery();
			 
			 if (rs.next()) {
				 usuarioCorreto = rs.getString("nome");
				 passCorreto = rs.getString("senha");
			 }
			 
			 if (user.equals(usuarioCorreto) && pass.equals(passCorreto)) {
				 JOptionPane.showMessageDialog(null, "Login correto. Bem-vindo(a) " + user);
			 } else {
				 JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
			 }
			 
		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		 } finally {
			 if (rs != null) {
				 try {
					 rs.close();
				 } catch (Exception e) {
					 e.printStackTrace();
				 }
			 }
			 if (pstmt != null) {
				 try {
					 pstmt.close();
				 } catch (Exception e) {
					 e.printStackTrace();
				 }
			 }
			 if (conn != null) {
				 DB.closeConnection(conn);
			 }
		 }
	}
}
