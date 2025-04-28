package Part2;

import javax.swing.*;
import java.awt.*;

public class HorseCustomisationPanel extends JPanel {
    private JTextField horseNameField;    
    private JComboBox<String> symbolList;
    private JComboBox<String> breedList;
    private JComboBox<String> equipmentList;

    public HorseCustomisationPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        add(contentPanel, BorderLayout.CENTER);

        //name 
        JPanel field1Panel = new JPanel();
        contentPanel.add(field1Panel);

        JLabel horseNameLabel = new JLabel("Horse Name:");
        horseNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        field1Panel.add(horseNameLabel);

        horseNameField = new JTextField("PIPPI LOCKSTOCKING", 20);
        field1Panel.add(horseNameField);

        //symbol
        JPanel field2Panel = new JPanel();
        contentPanel.add(field2Panel);

        JLabel symbolLabel = new JLabel("Symbol:");
        symbolLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        field2Panel.add(symbolLabel);

        symbolList = new JComboBox<>(new String[] {"\u2658", "\u265E", "\u2655", "\u265B", "\u2654"});
        symbolList.setSelectedItem("\u2658");
        field2Panel.add(symbolList);

        // JPanel field2Panel = new JPanel();
        // contentPanel.add(field2Panel);

        // JLabel colourLabel = new JLabel("Colour:");
        // colourLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        // field2Panel.add(colourLabel);

        // colourList = new SelectList(new String[] {"Orange", "Black", "White"});
        // colourList.setSelectedItem(defaultColour);
        // field2Panel.add(colourList);

        //equipment
        JPanel field3Panel = new JPanel();
        contentPanel.add(field3Panel);

        JLabel equipmentLabel = new JLabel("Equipment:");
        equipmentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        field3Panel.add(equipmentLabel);

        equipmentList = new JComboBox<>(new String[] {"Saddle", "Hat", "Horseshoe"});
        equipmentList.setSelectedItem("Saddle");
        field3Panel.add(equipmentList);

        //breed
        JPanel field4Panel = new JPanel();
        contentPanel.add(field4Panel);

        JLabel breedLabel = new JLabel("Breed:");
        breedLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        field4Panel.add(breedLabel);

        breedList = new JComboBox<>(new String[] {"Arabian", "Thoroughbred", "Quarter Horse"});
        breedList.setSelectedItem("Arabian");
        field4Panel.add(breedList);
    }

    public String getHorseName() {
        return horseNameField.getText();
    }

    public String getHorseSymbol() {
        return (String) symbolList.getSelectedItem();
    }
    public String getHorseBreed() {
        return (String) breedList.getSelectedItem();
    }

    public String getHorseEquipment() {
        return (String) equipmentList.getSelectedItem();
    }
}
