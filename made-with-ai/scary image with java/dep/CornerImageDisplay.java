import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CornerImageDisplay extends JFrame {
    public CornerImageDisplay() {
        // Load the image
        ImageIcon icon = new ImageIcon("image.png");

        // Create a label to hold the image
        JLabel label = new JLabel(icon);

        // Set up the frame to be undecorated, no borders
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Transparent background
        setAlwaysOnTop(true); // Always on top of other windows

        // Add the label to the frame
        add(label);

        // Set the size of the frame based on the image
        setSize(icon.getIconWidth(), icon.getIconHeight());

        // Adjust position to ensure it's in the bottom-right corner
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int x = screenSize.width - getWidth() - screenInsets.right;
        int y = screenSize.height - getHeight() - screenInsets.bottom;

        setLocation(x, y);

        // Close the application on window close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Set the window visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CornerImageDisplay::new);
    }
}
