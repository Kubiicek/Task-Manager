package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entites.Consultas;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JRecoverPass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPergunta;
	private JTextField textResposta;
	private String username;
	private String password;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRecoverPass frame = new JRecoverPass("exampleUser", "examplePass");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JRecoverPass(String username, String password) {
		this.username = username;
		this.password = password;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblParaRecuperaoDe = new JLabel("Para recuperação de conta coloque uma pergunta");
		lblParaRecuperaoDe.setBounds(20, 8, 499, 22);
		lblParaRecuperaoDe.setForeground(Color.WHITE);
		lblParaRecuperaoDe.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		contentPane.add(lblParaRecuperaoDe);

		JLabel lblEUmaResposta = new JLabel("e uma resposta para responder");
		lblEUmaResposta.setForeground(Color.WHITE);
		lblEUmaResposta.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		lblEUmaResposta.setBounds(108, 40, 499, 22);
		contentPane.add(lblEUmaResposta);

		JLabel lbPergunta = new JLabel("Pergunta:");
		lbPergunta.setForeground(Color.WHITE);
		lbPergunta.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lbPergunta.setBounds(108, 84, 89, 30);
		contentPane.add(lbPergunta);

		textPergunta = new JTextField();
		textPergunta.setHorizontalAlignment(SwingConstants.CENTER);
		textPergunta.setForeground(Color.WHITE);
		textPergunta.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		textPergunta.setColumns(10);
		textPergunta.setBorder(null);
		textPergunta.setBackground(new Color(0, 102, 204));
		textPergunta.setBounds(187, 90, 219, 20);
		contentPane.add(textPergunta);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.WHITE);
		separator.setBounds(187, 109, 219, 20);
		contentPane.add(separator);

		JLabel lbResposta = new JLabel("Resposta:");
		lbResposta.setForeground(Color.WHITE);
		lbResposta.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lbResposta.setBounds(108, 156, 89, 30);
		contentPane.add(lbResposta);

		textResposta = new JTextField();
		textResposta.setHorizontalAlignment(SwingConstants.CENTER);
		textResposta.setForeground(Color.WHITE);
		textResposta.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		textResposta.setColumns(10);
		textResposta.setBorder(null);
		textResposta.setBackground(new Color(0, 102, 204));
		textResposta.setBounds(187, 162, 219, 20);
		contentPane.add(textResposta);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(187, 181, 219, 20);
		contentPane.add(separator_1);

		JButton JBtnSalvar = new JButton("Salvar");
		JBtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultas con = new Consultas();
				con.salvarUsuario(username, password, textPergunta.getText(), textResposta.getText());
				dispose();
				JLogin jlogin = new JLogin();
				jlogin.setLocationRelativeTo(jlogin);
				jlogin.setVisible(true);
			}
		});
		JBtnSalvar.setForeground(Color.WHITE);
		JBtnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JBtnSalvar.setBackground(new Color(0, 51, 102));
		JBtnSalvar.setBounds(226, 227, 89, 23);
		contentPane.add(JBtnSalvar);
	}
}
