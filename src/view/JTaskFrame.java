package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JTaskFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldDescricao;
    private JTextField textFieldData;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private JButton btnVoltar;

    public JTaskFrame() {
        setTitle("Tarefas");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 520, 241);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(40, 40, 40));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        lblTitulo.setBounds(48, 10, 100, 14);
        contentPane.add(lblTitulo);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(208, 8, 250, 20);
        contentPane.add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblData = new JLabel("Data e Hora");
        lblData.setForeground(new Color(255, 255, 255));
        lblData.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        lblData.setBounds(48, 41, 89, 14);
        contentPane.add(lblData);

        textFieldData = new JTextField();
        textFieldData.setBounds(212, 56, 250, 20);
        contentPane.add(textFieldData);
        textFieldData.setColumns(10);

        JLabel lblDescricao = new JLabel("Descrição (opcional):");
        lblDescricao.setForeground(new Color(255, 255, 255));
        lblDescricao.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        lblDescricao.setBounds(35, 100, 150, 14);
        contentPane.add(lblDescricao);

        textFieldDescricao = new JTextField();
        textFieldDescricao.setBounds(211, 97, 250, 20);
        contentPane.add(textFieldDescricao);
        textFieldDescricao.setColumns(10);

        JButton btnSave = new JButton("Salvar");
        btnSave.setFocusable(false);
        btnSave.setBackground(new Color(32, 32, 32));
        btnSave.setForeground(new Color(255, 255, 255));
        btnSave.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        btnSave.setBounds(293, 155, 89, 23);
        contentPane.add(btnSave);

        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                JAplicativo.setButton(0);
            }
        });
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusable(false);
        btnVoltar.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        btnVoltar.setBackground(new Color(32, 32, 32));
        btnVoltar.setBounds(393, 155, 89, 23);
        contentPane.add(btnVoltar);
        
        JLabel lbldiamesanoHoraminuto = new JLabel("(dia/mes/ano hora:minuto):");
        lbldiamesanoHoraminuto.setForeground(Color.WHITE);
        lbldiamesanoHoraminuto.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        lbldiamesanoHoraminuto.setBounds(10, 58, 192, 14);
        contentPane.add(lbldiamesanoHoraminuto);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String data = textFieldData.getText();
                String descricao = textFieldDescricao.getText();

                if (nome.isEmpty() || data.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Título e data são obrigatórios.");
                    return;
                }

                try {
                    Date parsedDate = dateFormat.parse(data);
                    String formattedDate = dateFormat.format(parsedDate);

                    JAplicativo.adicionarTarefa(nome, formattedDate, descricao.isEmpty() ? "Sem descrição" : descricao);

                    JOptionPane.showMessageDialog(contentPane, "Tarefa adicionada com sucesso.");
                    textFieldNome.setText("");
                    textFieldData.setText("");
                    textFieldDescricao.setText("");

                    JAplicativo.setButton(0);
                    dispose(); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPane, "Formato de data inválido. Use o formato dd/MM/yyyy HH:mm.");
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                JAplicativo.setButton(0);
            }
        });
    }
}
