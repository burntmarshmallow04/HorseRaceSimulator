package Part2;

import javax.swing.*;
import java.awt.*;

//This class is for customising the shape of the tracks
public class TrackCustomisationPanel extends JPanel {
    private SelectList trackShapeList;

    public TrackCustomisationPanel() {
        setLayout(new GridLayout(1, 1, 10, 10)); 
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        trackShapeList = new SelectList("Select Track Shape", new String[]{"Straight", "Circular", "Oval"});
        add(trackShapeList);
    }

    public String getTrackShape() {
        return trackShapeList.getSelectedItem();
    }
}
