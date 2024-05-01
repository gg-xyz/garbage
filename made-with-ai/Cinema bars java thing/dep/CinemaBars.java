import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CinemaBars extends JFrame {
    private static final int BAR_HEIGHT = 100; // Adjust this value as needed
    private static final int ANIMATION_SPEED = 10; // Pixels per frame
    private int topYPosition = -BAR_HEIGHT; // Initial Y position for top bar
    private int bottomYPosition; // Initial Y position for bottom bar
    private final JFrame bottomBar = new JFrame(); // Separate window for bottom bar
    private final Timer animationTimer;

    public CinemaBars() {
        // Set up the frame to be undecorated, no borders
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Transparent background
        setAlwaysOnTop(true); // Always on top of other windows

        // Set the window to cover the full screen width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, BAR_HEIGHT);
        setLocation(0, topYPosition);

        bottomYPosition = screenSize.height;

        JPanel barPanel = new JPanel();
        barPanel.setBackground(Color.BLACK); // Set the color of the bar
        barPanel.setPreferredSize(new Dimension(screenSize.width, BAR_HEIGHT));

        add(barPanel);
        pack(); // Adjust the frame size based on panel

        // Set up the bottom bar
        bottomBar.setUndecorated(true);
        bottomBar.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        bottomBar.setAlwaysOnTop(true);
        bottomBar.setSize(screenSize.width, BAR_HEIGHT);
        bottomBar.setLocation(0, bottomYPosition);

        JPanel bottomBarPanel = new JPanel();
        bottomBarPanel.setBackground(Color.BLACK);
        bottomBarPanel.setPreferredSize(new Dimension(screenSize.width, BAR_HEIGHT));
        bottomBarPanel.setLayout(new BorderLayout());

        // Create a JTextField with scrolling for editable text
        JTextField textField = new JTextField("Editable Text");
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.BLACK);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(null);
        textField.setHorizontalAlignment(JTextField.LEFT); // Align to left to see scrolling

        // Add the text field directly to the bottom panel
        bottomBarPanel.add(textField, BorderLayout.CENTER);

        // Add the GIF to the bottom right corner
        ImageIcon gifIcon = new ImageIcon("animation.gif"); // Load your GIF
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setPreferredSize(new Dimension(BAR_HEIGHT, BAR_HEIGHT)); // Set preferred size for 1:1 aspect ratio
        bottomBarPanel.add(gifLabel, BorderLayout.EAST);

        bottomBar.add(bottomBarPanel);
        bottomBar.pack();

        // Timer for animation
        animationTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (topYPosition < 0) {
                    topYPosition += ANIMATION_SPEED;
                    setLocation(0, topYPosition);
                }
                if (bottomYPosition > screenSize.height - BAR_HEIGHT) {
                    bottomYPosition -= ANIMATION_SPEED;
                    bottomBar.setLocation(0, bottomYPosition);
                }

                // Stop the animation when bars reach the target position
                if (topYPosition >= 0 && bottomYPosition <= screenSize.height - BAR_HEIGHT) {
                    animationTimer.stop();
                }
            }
        });
        animationTimer.start();

        // Close the application on window close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        bottomBar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
        bottomBar.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CinemaBars::new);
    }
}
