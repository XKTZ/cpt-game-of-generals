package generals.frontend.ui;

import generals.frontend.GameService;

import javax.swing.*;
import java.awt.*;

/**
 * Chat area
 * @author Yidi Chen
 * @date 2022-01-12
 */
public class ChatArea extends JPanel {

    /**
     * Font
     */
    private static final Font FONT = new Font("Times New Roman", Font.PLAIN, 15);

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
     *
     * @param gameService game service
     */
    public ChatArea(GameService gameService) {
        super(null);

        // game service
        this.gameService = gameService;

        // set size
        setSize(380, 300);

        // create text show
        // at {0, 0}, 300x260, line wrap, font as FONT, not editable
        text = new JTextArea();
        text.setSize(380, 260);
        text.setLocation(0, 0);
        text.setLineWrap(true);
        text.setFont(FONT);
        text.setEditable(false);

        // scroll pane of text
        scrollPane = new JScrollPane(text);
        scrollPane.setSize(380, 260);
        scrollPane.setLocation(0, 0);

        // set input
        // 300x40, at {0, 260}, font as FONT, action listener as sending message
        input = new JTextField();
        input.setSize(380, 40);
        input.setLocation(0, 260);
        input.setFont(FONT);
        input.addActionListener((e) -> {
            // send it
            gameService.message(input.getText());
            // clear
            input.setText("");
        });

        // add scroll pane
        this.add(scrollPane);
        // add into input
        this.add(input);
    }

    /**
     * Print a line in chat area
     * @param strMsg
     */
    public void println(String strMsg) {
        // add it in text
        text.append(strMsg);
        text.append("\n");
    }
}
