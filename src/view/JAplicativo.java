package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;

import entites.DB;

public class JAplicativo extends JFrame {

    private static final long serialVersionUID = 1L;
    private static int buttons = 3;
    private JPanel contentPane;
    private JTextArea taskDisplay;
    private int userId;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JAplicativo frame = new JAplicativo(1); // Substitua 1 pelo ID real do usuário
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        UIManager.put("ScrollBar.thumb", new ColorUIResource(64, 64, 64));
        UIManager.put("ScrollBar.track", new ColorUIResource(32, 32, 32));
        UIManager.put("ScrollBar.foreground", new ColorUIResource(255, 255, 255));
        UIManager.put("ScrollBar.background", new ColorUIResource(32, 32, 32));
        UIManager.put("ScrollPane.background", new ColorUIResource(32, 32, 32));
    }

    public JAplicativo(int userId) {
        this.userId = userId; 
        setTitle("Aplicativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 479, 582);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(32, 32, 32));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(new Color(32, 32, 32));
        panel.setBounds(0, 502, 453, 41);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton Hoje = new JButton("Hoje");
        Hoje.setBackground(new Color(32, 32, 32));
        Hoje.setForeground(Color.WHITE);
        Hoje.setFocusable(false);
        Hoje.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        Hoje.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        Hoje.setBounds(24, 11, 89, 23);
        panel.add(Hoje);

        JButton Habitos = new JButton("Hábitos");
        Habitos.setForeground(Color.WHITE);
        Habitos.setFocusable(false);
        Habitos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        Habitos.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        Habitos.setBackground(new Color(32, 32, 32));
        Habitos.setBounds(134, 11, 89, 23);
        panel.add(Habitos);

        JButton Tarefas = new JButton("Tarefas");
        Tarefas.setForeground(Color.WHITE);
        Tarefas.setFocusable(false);
        Tarefas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        Tarefas.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        Tarefas.setBackground(new Color(32, 32, 32));
        Tarefas.setBounds(354, 11, 89, 23);
        panel.add(Tarefas);

        JButton Agenda = new JButton("Agenda");
        Agenda.setForeground(Color.WHITE);
        Agenda.setFocusable(false);
        Agenda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        Agenda.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        Agenda.setBackground(new Color(32, 32, 32));
        Agenda.setBounds(244, 11, 89, 24);
        panel.add(Agenda);

        Hoje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTarefasParaHoje(userId);
            }
        });

        Habitos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        Tarefas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Adicionar", "Remover", "Cancelar"};
                int choice = JOptionPane.showOptionDialog(
                        contentPane,
                        "O que você gostaria de fazer?",
                        "Escolher Ação",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == 0) {
                    JTaskFrame jTask = new JTaskFrame(userId); 
                    jTask.setLocationRelativeTo(null);
                    jTask.setVisible(true);
                    buttons = 1;
                } else if (choice == 1) {
                    String taskName = JOptionPane.showInputDialog("Digite o nome da tarefa a ser removida:");
                    if (taskName != null && !taskName.trim().isEmpty()) {
                        removerTarefa(taskName, userId);
                    }
                }
            }
        });

        Agenda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTodasTarefas(userId);
            }
        });

        taskDisplay = new JTextArea();
        taskDisplay.setEditable(false);
        taskDisplay.setBackground(new Color(32, 32, 32));
        taskDisplay.setForeground(Color.WHITE);
        taskDisplay.setFont(new Font("Lucida Sans", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(taskDisplay);
        scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setBounds(10, 11, 443, 480);
        contentPane.add(scrollPane);

        displayTarefasParaHoje(userId);
    }

    public static void setButton(int value) {
        buttons = value;
    }
    
    public static void adicionarTarefa(String nome, String data, String descricao, int userId) {
        String sql = "INSERT INTO tarefas (idUsuario, task_name, task_date, task_description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, nome);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            java.util.Date utilDate = sdf.parse(data);
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());

            stmt.setTimestamp(3, sqlTimestamp);
            stmt.setString(4, descricao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static void removerTarefa(String nome, int userId) {
        String sql = "DELETE FROM tarefas WHERE task_name = ? AND idUsuario = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, userId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Tarefa não encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void displayTarefasParaHoje(int userId) {
        String sql = "SELECT task_name, task_date, task_description FROM tarefas WHERE idUsuario = ? AND DATE(task_date) = CURDATE()";
        StringBuilder message = new StringBuilder("Tarefas para hoje:\n\n");

        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                message.append("Nome: ").append(rs.getString("task_name")).append("\n");
                message.append("Data: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rs.getTimestamp("task_date"))).append("\n");
                message.append("Descrição: ").append(rs.getString("task_description")).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (message.length() == 0) {
            message.append("Nenhuma tarefa encontrada.");
        }

        taskDisplay.setText(message.toString());
    }

    private void displayTodasTarefas(int userId) {
        String sql = "SELECT task_name, task_date, task_description FROM tarefas WHERE idUsuario = ?";
        StringBuilder message = new StringBuilder("Todas as tarefas:\n\n");

        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                message.append("Nome: ").append(rs.getString("task_name")).append("\n");
                message.append("Data: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rs.getTimestamp("task_date"))).append("\n");
                message.append("Descrição: ").append(rs.getString("task_description")).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (message.length() == 0) {
            message.append("Nenhuma tarefa encontrada.");
        }

        taskDisplay.setText(message.toString());
    }

}
