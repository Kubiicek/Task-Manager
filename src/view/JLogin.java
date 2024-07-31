package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entites.Consultas;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField JTextUsuario;
	private JPasswordField JTextPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
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
	public JLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 531);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 204));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Acesso");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 24));
		lblNewLabel.setBounds(167, 11, 100, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setIcon(new ImageIcon(JLogin.class.getResource("/images/userInicio.png")));
		lblNewLabel_1.setBounds(147, 41, 140, 143);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Usuario:");
		lblNewLabel_2.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(186, 181, 67, 30);
		contentPane.add(lblNewLabel_2);
		
		JTextUsuario = new JTextField();
		JTextUsuario.setColumns(10);
		JTextUsuario.setForeground(new Color(255, 255, 255));
		JTextUsuario.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		JTextUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		JTextUsuario.setBackground(new Color(0, 102, 204));
		JTextUsuario.setBounds(111, 221, 219, 20);
		JTextUsuario.setBorder(null);
		contentPane.add(JTextUsuario);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 255, 255));
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(111, 240, 219, 20);
		contentPane.add(separator);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setIcon(new ImageIcon(JLogin.class.getResource("/images/userLabel.png")));
		lblNewLabel_3.setBounds(89, 225, 23, 14);
		contentPane.add(lblNewLabel_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(111, 340, 219, 20);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Senha:");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(192, 271, 67, 30);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel(" ");
		lblNewLabel_3_1.setIcon(new ImageIcon(JLogin.class.getResource("/images/key.png")));
		lblNewLabel_3_1.setBounds(89, 316, 23, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JButton JBtnEntrar = new JButton("Entrar");
		JBtnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultas con = new Consultas();
				if (con.consultarUsuario(JTextUsuario.getText(), JTextPass.getText()) == true) {
					        Consultas conn = new Consultas();
					        int userId = conn.consultarInt(JTextUsuario.getText(), new String(JTextPass.getPassword()));
					        if (userId != -1) { 
					            dispose();
					            JAplicativo jApp = new JAplicativo(userId);
					            jApp.setLocationRelativeTo(null);
					            jApp.setVisible(true);
					        } else {
					            JOptionPane.showMessageDialog(contentPane, "Usuário ou senha inválidos.");
					        }
				}
			}
		});
		JBtnEntrar.setForeground(new Color(255, 255, 255));
		JBtnEntrar.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		JBtnEntrar.setBackground(new Color(0, 51, 102));
		JBtnEntrar.setBounds(167, 362, 100, 23);
		contentPane.add(JBtnEntrar);
		
		JButton JBtnRegistrar = new JButton("Registrar");
		JBtnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JRegistro jRegister = new JRegistro();
				jRegister.setLocationRelativeTo(jRegister);
				jRegister.setVisible(true);
			}
		});
		JBtnRegistrar.setForeground(Color.WHITE);
		JBtnRegistrar.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		JBtnRegistrar.setBackground(new Color(0, 51, 102));
		JBtnRegistrar.setBounds(167, 411, 100, 23);
		contentPane.add(JBtnRegistrar);
		
		JTextPass = new JPasswordField();
		JTextPass.setHorizontalAlignment(SwingConstants.CENTER);
		JTextPass.setForeground(new Color(255, 255, 255));
		JTextPass.setBackground(new Color(0, 102, 204));
		JTextPass.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		JTextPass.setBounds(111, 313, 219, 20);
		JTextPass.setBorder(null);
		contentPane.add(JTextPass);
		
		JButton btnNewButton = new JButton("Esqueceu sua senha?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JRecover jRecover = new JRecover(JTextUsuario.getText());
				jRecover.setLocationRelativeTo(jRecover);
				jRecover.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		btnNewButton.setBounds(127, 452, 203, 23);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		contentPane.add(btnNewButton);
	}
}
