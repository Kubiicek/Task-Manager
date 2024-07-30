package entites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Consultas {
	
	public void salvarUsuario(String usuario, String password, String security_question, String security_answer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowsAffected = 0;
		try {
			conn = DB.getConnection();
			
			String sql = "INSERT INTO usuarios (nome, senha, security_question, security_answer) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, usuario);
			pstmt.setString(2, password);
			pstmt.setString(3, security_question);
			pstmt.setString(4, security_answer);
			
			rowsAffected = pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo corretamente");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public boolean consultarUsuario(String user, String pass) {
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
				 return true;
			 } else {
				 JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
				 return false;
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
		}
		 return false;
	}

	public void verificarRecuperacao(String usuario, String answer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.getConnection();

			String sql = "SELECT security_question, security_answer, senha FROM usuarios WHERE nome = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usuario);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String question = rs.getString("security_question");
				String correctAnswer = rs.getString("security_answer");
				String password = rs.getString("senha");

				if (answer.equals(correctAnswer)) {
					JOptionPane.showMessageDialog(null, "Sua senha é: " + password);
				} else {
					JOptionPane.showMessageDialog(null, "Resposta incorreta");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Usuario não encontrado.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public String obterPergunta(String usuario) {
		String pergunta = "";
		try {
			Connection conn = DB.getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT security_question FROM usuarios WHERE nome = '" + usuario + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				pergunta = rs.getString("security_question");
			} else {
				pergunta = "Usuário nao encontrado";
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pergunta;
	}
}
