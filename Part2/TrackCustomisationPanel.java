package Part2;

import javax.swing.*;
import java.awt.*;

//This class is for customising the shape of the tracks
public class TrackCustomisationPanel extends JPanel {
    private JComboBox<String> trackShapeList;
    private JTextField laneCountField;
    private JLabel trackShapeLabel;
    private JLabel laneCountLabel;

    public TrackCustomisationPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Track Shape Selection (Replaced SelectList with JComboBox)
        trackShapeLabel = new JLabel("Select Track Shape:");
        String[] trackShapes = {"Straight", "Circular", "Oval"};  // Track shape options
        trackShapeList = new JComboBox<>(trackShapes);  // Create combo box for track shapes
        
        // Lane Count Selection (JComboBox for lane count)
        laneCountLabel = new JLabel("Enter Lane Count (>= 2):");
        laneCountField = new JTextField("2", 1); 
        

        // Add components to the panel
        add(trackShapeLabel);
        add(trackShapeList);
        add(Box.createRigidArea(new Dimension(0,50))); 
        add(laneCountLabel);
        add(laneCountField);
    }

    public String getTrackShape() {
        return (String) trackShapeList.getSelectedItem();  // Get the selected track shape
    }

    // Getter method for lane count
    public int getLaneCount() {
        try {
            int lanes = Integer.parseInt(laneCountField.getText());
            if (lanes < 2) {
                return 2;
            }
            return lanes;
        } catch (NumberFormatException e) {
            return 2;
        }
    }
    
}
