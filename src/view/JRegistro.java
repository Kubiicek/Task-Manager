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
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField JTextUser;
	private JTextField JTextSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRegistro frame = new JRegistro();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Usuario:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblNewLabel_2.setBounds(119, 66, 67, 30);
		contentPane.add(lblNewLabel_2);
		
		JTextUser = new JTextField();
		JTextUser.setHorizontalAlignment(SwingConstants.CENTER);
		JTextUser.setForeground(Color.WHITE);
		JTextUser.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		JTextUser.setColumns(10);
		JTextUser.setBorder(null);
		JTextUser.setBackground(new Color(0, 102, 204));
		JTextUser.setBounds(196, 72, 219, 20);
		contentPane.add(JTextUser);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.WHITE);
		separator.setBounds(196, 91, 219, 20);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("Registrar");
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(196, 11, 136, 30);
		contentPane.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(196, 163, 219, 20);
		contentPane.add(separator_1);
		
		JTextSenha = new JTextField();
		JTextSenha.setHorizontalAlignment(SwingConstants.CENTER);
		JTextSenha.setForeground(Color.WHITE);
		JTextSenha.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		JTextSenha.setColumns(10);
		JTextSenha.setBorder(null);
		JTextSenha.setBackground(new Color(0, 102, 204));
		JTextSenha.setBounds(196, 144, 219, 20);
		contentPane.add(JTextSenha);
		
		JLabel lblNewLabel_2_1 = new JLabel("Senha:");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(119, 138, 67, 30);
		contentPane.add(lblNewLabel_2_1);
		
		JButton JBtnVoltar = new JButton("Voltar");
		JBtnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JLogin jlogin = new JLogin();
				jlogin.setLocationRelativeTo(jlogin);
				jlogin.setVisible(true);
			}
		});
		JBtnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JBtnVoltar.setIcon(new ImageIcon(JRegistro.class.getResource("/images/previous.png")));
		JBtnVoltar.setForeground(new Color(255, 255, 255));
		JBtnVoltar.setBackground(new Color(0, 51, 102));
		JBtnVoltar.setBounds(109, 209, 89, 23);
		contentPane.add(JBtnVoltar);
		
		JButton JBtnSalvar = new JButton("Salvar");
		JBtnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultas con = new Consultas();
				con.salvarUsuario(JTextUser.getText(), JTextSenha.getText());
			}
		});
		JBtnSalvar.setForeground(Color.WHITE);
		JBtnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JBtnSalvar.setBackground(new Color(0, 51, 102));
		JBtnSalvar.setBounds(332, 209, 89, 23);
		contentPane.add(JBtnSalvar);
	}

}
