package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class JAplicativo extends JFrame {

    private static final long serialVersionUID = 1L;
    private static int buttons = 3;
    private JPanel contentPane;
    private static List<String[]> tasks = new ArrayList<>();
    private JTextArea taskDisplay;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JAplicativo frame = new JAplicativo();
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

    public JAplicativo() {
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
                displayTarefasParaHoje();
            }
        });

        Habitos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (buttons != 2) {
                    
                }
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
                    JTaskFrame jTask = new JTaskFrame();
                    jTask.setLocationRelativeTo(null);
                    jTask.setVisible(true);
                    buttons = 1;
                } else if (choice == 1) {
                    String taskName = JOptionPane.showInputDialog("Digite o nome da tarefa a ser removida:");
                    if (taskName != null && !taskName.trim().isEmpty()) {
                        removerTarefa(taskName);
                    }
                }
            }
        });

        Agenda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayTodasTarefas();
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

        displayTarefasParaHoje();
    }

    public static void setButton(int value) {
        buttons = value;
    }

    public static void adicionarTarefa(String nome, String data, String descricao) {
        tasks.add(new String[]{nome, data, descricao});
    }

    public static void removerTarefa(String nome) {
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i)[0].equalsIgnoreCase(nome)) {
                tasks.remove(i);
                found = true;
                break;
            }
        }
        if (found) {
            JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Tarefa não encontrada.");
        }
    }

    private void displayTarefasParaHoje() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String todayDate = dateFormat.format(new Date());

        StringBuilder message = new StringBuilder("Tarefas para hoje:\n\n"); 
        boolean hasTasks = false;

        for (String[] task : tasks) {
            if (task[1].startsWith(todayDate)) {
                hasTasks = true;
                message.append("Nome: ").append(task[0]).append("\n");
                message.append("Data: ").append(task[1]).append("\n");
                message.append("Descrição: ").append(task[2]).append("\n\n");
            }
        }

        if (!hasTasks) {
        	message.setLength(0);
            message.append("Sem tarefas para hoje.");
        }

        taskDisplay.setText(message.toString());
    }

    private void displayTodasTarefas() {
        StringBuilder message = new StringBuilder("Todas as tarefas:\n\n");

        for (String[] task : tasks) {
            message.append("Nome: ").append(task[0]).append("\n");
            message.append("Data: ").append(task[1]).append("\n");
            message.append("Descrição: ").append(task[2]).append("\n\n");
        }

        taskDisplay.setText(message.toString());
    }
}
