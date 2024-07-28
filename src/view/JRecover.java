package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entites.Consultas;
import entites.DB;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JRecover extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String perguntaSeguranca;
	private JPanel contentPane;
	private JTextField textResposta;
	private String username;
	private String password;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRecover frame = new JRecover("exampleUser");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JRecover(String username) {
		
		String nomeUsuario = JOptionPane.showInputDialog(this, "Digite o nome do usuario:");
		
		this.username = username;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblParaRecuperaoDe = new JLabel("Quando você criou a conta você colocou uma");
		lblParaRecuperaoDe.setBounds(48, 8, 499, 22);
		lblParaRecuperaoDe.setForeground(Color.WHITE);
		lblParaRecuperaoDe.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		contentPane.add(lblParaRecuperaoDe);

		JLabel lblEUmaResposta = new JLabel("pergunta e uma resposta para responder");
		lblEUmaResposta.setForeground(Color.WHITE);
		lblEUmaResposta.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		lblEUmaResposta.setBounds(75, 40, 499, 22);
		contentPane.add(lblEUmaResposta);

		JLabel lbPergunta = new JLabel("Pergunta:");
		lbPergunta.setForeground(Color.WHITE);
		lbPergunta.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lbPergunta.setBounds(6, 91, 89, 30);
		contentPane.add(lbPergunta);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.WHITE);
		separator.setBounds(85, 120, 424, 20);
		contentPane.add(separator);

		JLabel lbResposta = new JLabel("Resposta:");
		lbResposta.setForeground(Color.WHITE);
		lbResposta.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lbResposta.setBounds(108, 171, 89, 30);
		contentPane.add(lbResposta);

		textResposta = new JTextField();
		textResposta.setHorizontalAlignment(SwingConstants.CENTER);
		textResposta.setForeground(Color.WHITE);
		textResposta.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		textResposta.setColumns(10);
		textResposta.setBorder(null);
		textResposta.setBackground(new Color(0, 102, 204));
		textResposta.setBounds(187, 177, 219, 20);
		contentPane.add(textResposta);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(187, 196, 219, 20);
		contentPane.add(separator_1);

		JButton JBtnSalvar = new JButton("Verificar");
		JBtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultas con = new Consultas();
					con.verificarRecuperacao(nomeUsuario, textResposta.getText());
			}
		});
		JBtnSalvar.setForeground(Color.WHITE);
		JBtnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JBtnSalvar.setBackground(new Color(0, 51, 102));
		JBtnSalvar.setBounds(364, 267, 89, 23);
		contentPane.add(JBtnSalvar);

		JLabel lblNewLabel = new JLabel("Carregando...");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblNewLabel.setBounds(85, 95, 424, 24);
		contentPane.add(lblNewLabel);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JLogin jlogin = new JLogin();
				jlogin.setLocationRelativeTo(jlogin);
				jlogin.setVisible(true);
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBackground(new Color(0, 51, 102));
		btnVoltar.setBounds(78, 268, 89, 23);
		contentPane.add(btnVoltar);

		if (nomeUsuario != null && !nomeUsuario.trim().isEmpty()) {
			new Thread(() -> {
				Consultas consultas = new Consultas();
				String perguntaSeguranca = consultas.obterPergunta(nomeUsuario);
				
				SwingUtilities.invokeLater(() -> lblNewLabel.setText(perguntaSeguranca));
			}).start();
		} else {
			JOptionPane.showMessageDialog(this, "Nome do usuario nao fornecido.");
		}
	}
}
