package Part2;

import javax.swing.*;
import java.awt.*;

//This class is for customising the shape of the tracks
public class TrackCustomisationPanel extends JPanel {
    private JComboBox<String> trackShapeList;
    private JTextField laneCountField;
    private JLabel trackShapeLabel;
    private JLabel laneCountLabel;
    private JSpinner trackLengthSpinner;


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
        
        JPanel trackLengthPanel = new JPanel();
        trackLengthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel trackLengthLabel = new JLabel("Track Length (30-50): ");
        trackLengthSpinner = new JSpinner(new SpinnerNumberModel(30, 30, 50, 1));
        
        // Add components to the panel
        add(trackShapeLabel);
        add(trackShapeList);
        add(Box.createRigidArea(new Dimension(0,50))); 
        add(laneCountLabel);
        add(laneCountField);
        add(Box.createRigidArea(new Dimension(0,50))); 
        add(trackLengthLabel);
        add(trackLengthSpinner);
    }

    public String getTrackShape() {
        return (String) trackShapeList.getSelectedItem(); 
    }

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

    public int getRaceLength() {
        return (int) trackLengthSpinner.getValue();
    }
    
}
