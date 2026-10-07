import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ToDoApp extends JFrame {
    private JTextField taskInputField;
    private JButton addButton;
    private JButton deleteButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;

    public ToDoApp(){
        setTitle("Task Manager");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(taskList);

        JPanel topPanel = new JPanel(new BorderLayout(5,5));
        taskInputField = new JTextField();
        addButton = new JButton("Add");

        topPanel.add(taskInputField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButton = new JButton("delete selected task");
        bottomPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewTask();
            }
        });

        taskInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1){
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(
                            ToDoApp.this,
                            "please select a task to delete",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
    }

    private void addNewTask() {
        String taskText = taskInputField.getText().trim();
        if(!taskText.isEmpty()) {
            listModel.addElement(taskText);
            taskInputField.setText("");
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "task name cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new ToDoApp().setVisible(true);
            }
        });
    }
}
