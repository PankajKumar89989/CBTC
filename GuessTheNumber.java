import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumber {
    private static int maxAttempts = 10;
    private static int minRange;
    private static int maxRange;
    private static int randomNumber;
    private static int attempts;
    private static int score = 0;

    public static void main(String[] args) {
        promptRange();
    }

    private static void promptRange() {
        JFrame rangeFrame = new JFrame("Set Range");
        rangeFrame.setSize(400, 200);
        rangeFrame.setLocationRelativeTo(null);
        rangeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel rangePanel = new JPanel();
        rangeFrame.add(rangePanel);
        placeRangeComponents(rangePanel, rangeFrame);

        rangeFrame.setVisible(true);
    }

    private static void placeRangeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel minLabel = new JLabel("Enter minimum range:");
        minLabel.setBounds(10, 20, 150, 25);
        panel.add(minLabel);

        JTextField minText = new JTextField(20);
        minText.setBounds(160, 20, 200, 25);
        panel.add(minText);

        JLabel maxLabel = new JLabel("Enter maximum range:");
        maxLabel.setBounds(10, 60, 150, 25);
        panel.add(maxLabel);

        JTextField maxText = new JTextField(20);
        maxText.setBounds(160, 60, 200, 25);
        panel.add(maxText);

        JButton setRangeButton = new JButton("Set Range");
        setRangeButton.setBounds(130, 100, 100, 25);
        panel.add(setRangeButton);

        setRangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    minRange = Integer.parseInt(minText.getText());
                    maxRange = Integer.parseInt(maxText.getText());
                    if (minRange >= maxRange) {
                        JOptionPane.showMessageDialog(frame, "Minimum range should be less than maximum range.");
                    } else {
                        frame.dispose();
                        setupGame();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.");
                }
            }
        });
    }

    private static void setupGame() {
        randomNumber = new Random().nextInt(maxRange - minRange + 1) + minRange;
        attempts = 0;

        JFrame frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, frame);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, JFrame frame) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Enter your guess:");
        userLabel.setBounds(10, 20, 160, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 20, 100, 25);
        panel.add(userText);

        JButton guessButton = new JButton("Guess");
        guessButton.setBounds(10, 50, 80, 25);
        panel.add(guessButton);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(10, 80, 300, 25);
        panel.add(resultLabel);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(100, 50, 150, 25);
        playAgainButton.setVisible(false);
        panel.add(playAgainButton);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(userText.getText());
                    attempts++;
                    if (userGuess == randomNumber) {
                        resultLabel.setText("Congratulations! You guessed the correct number.");
                        score += (maxAttempts - attempts + 1);  // Points based on attempts
                        guessButton.setEnabled(false);
                        playAgainButton.setVisible(true);
                    } else if (userGuess < randomNumber) {
                        resultLabel.setText("The number is higher than your guess.");
                    } else {
                        resultLabel.setText("The number is lower than your guess.");
                    }

                    if (attempts == maxAttempts && userGuess != randomNumber) {
                        resultLabel.setText("Sorry, you've used all your attempts. The correct number was " + randomNumber);
                        guessButton.setEnabled(false);
                        playAgainButton.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                }
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                promptRange();
            }
        });
    }
}
