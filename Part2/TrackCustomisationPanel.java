package Part2;

import javax.swing.*;
import java.awt.*;

//This class is for customising the shape of the tracks
public class TrackCustomisationPanel extends JPanel {
    private JComboBox<String> trackShapeList;  // JComboBox for track shape
    private JComboBox<Integer> laneCountList; // JComboBox for lane count
    private JLabel trackShapeLabel;
    private JLabel laneCountLabel;

    public TrackCustomisationPanel() {
        setLayout(new GridLayout(2, 2, 10, 10));  // Updated layout to hold both elements
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Track Shape Selection (Replaced SelectList with JComboBox)
        trackShapeLabel = new JLabel("Select Track Shape:");
        String[] trackShapes = {"Straight", "Circular", "Oval"};  // Track shape options
        trackShapeList = new JComboBox<>(trackShapes);  // Create combo box for track shapes

        // Lane Count Selection (JComboBox for lane count)
        laneCountLabel = new JLabel("Select Lane Count:");
        Integer[] laneOptions = {2, 3, 4};  // Options for lane count (2 to 4)
        laneCountList = new JComboBox<>(laneOptions); // Create combo box for lane count

        // Add components to the panel
        add(trackShapeLabel);
        add(trackShapeList);
        add(laneCountLabel);
        add(laneCountList);
    }

    public String getTrackShape() {
        return (String) trackShapeList.getSelectedItem();  // Get the selected track shape
    }

    // Getter method for lane count
    public int getLaneCount() {
        return (Integer) laneCountList.getSelectedItem();  // Get the selected lane count
    }
}
