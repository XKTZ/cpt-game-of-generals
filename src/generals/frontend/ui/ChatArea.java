package generals.frontend.ui;

import generals.frontend.GameService;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yidi Chen
 * @date 2022-01-12
 */
public class ChatArea extends JPanel {

    private static final Font fnt = new Font("Times New Roman", Font.PLAIN, 15);

    /**
     * Scroll pane
     */
    private JScrollPane scrollPane;

    /**
     * Text field
     */
    private JTextArea text;

    /**
     * Input
     */
    private JTextField input;

    /**
     * Game Service
     */
    private GameService gameService;

    /**
     * Create the chat area
     */
    public ChatArea(GameService gameService) {
        super(null);

        this.gameService = gameService;

        setSize(380, 300);

        // set text
        text = new JTextArea();
        text.setSize(380, 260);
        text.setLocation(0, 0);
        text.setLineWrap(true);
        text.setFont(fnt);
        text.setEditable(false);
        scrollPane = new JScrollPane(text);
        scrollPane.setSize(380, 260);
        scrollPane.setLocation(0, 0);

        // set input
        input = new JTextField();
        input.setSize(380, 40);
        input.setLocation(0, 260);
        input.setFont(fnt);
        input.addActionListener((e) -> {
            gameService.message(input.getText());
        });

        this.add(scrollPane);
        this.add(input);
    }

    /**
     * Print a line in chat area
     */
    public void println(String strMsg) {
        text.append(strMsg);
        text.append("\n");
        input.setText("");
    }
}
