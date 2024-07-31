package entites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Consultas {

    public void salvarUsuario(String usuario, String password, String security_question, String security_answer) {
        String sql = "INSERT INTO usuarios (nome, senha, security_question, security_answer) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            pstmt.setString(3, security_question);
            pstmt.setString(4, security_answer);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo corretamente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int consultarInt(String username, String password) {
        int userId = -1;
        String query = "SELECT idUsuario FROM usuarios WHERE nome = ? AND senha = ?";

        try (Connection connection = DB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("idUsuario");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public boolean consultarUsuario(String user, String pass) {
        String sql = "SELECT nome, senha FROM usuarios WHERE nome = ? AND senha = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String usuarioCorreto = rs.getString("nome");
                    String passCorreto = rs.getString("senha");

                    if (user.equals(usuarioCorreto) && pass.equals(passCorreto)) {
                        JOptionPane.showMessageDialog(null, "Login correto. Bem-vindo(a) " + user);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
                    return false;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            return false;
        }
    }

    public void verificarRecuperacao(String usuario, String answer) {
        String sql = "SELECT security_question, security_answer, senha FROM usuarios WHERE nome = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario);

            try (ResultSet rs = pstmt.executeQuery()) {
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
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String obterPergunta(String usuario) {
        String pergunta = "";
        String sql = "SELECT security_question FROM usuarios WHERE nome = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pergunta = rs.getString("security_question");
                } else {
                    pergunta = "Usuário não encontrado";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pergunta;
    }
}
