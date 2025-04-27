package Part2;

import javax.swing.*;
import java.awt.*;

//This class creates a dropdown menu
public class SelectList extends JPanel {
    private JLabel label;
    private JComboBox<String> comboBox;

    public SelectList(String labelText, String[] items) {
        setLayout(new BorderLayout(0, 0));

        label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(label, BorderLayout.NORTH); 

        comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox.setFocusable(false);
        comboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); 
        add(comboBox, BorderLayout.CENTER); 
    }

    public String getSelectedItem() {
        return (String) comboBox.getSelectedItem();
    }

    public void setSelectedItem(String item) {
        comboBox.setSelectedItem(item);
    }
}
