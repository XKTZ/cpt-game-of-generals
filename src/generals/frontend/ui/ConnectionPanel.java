package generals.frontend.ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiFunction;

/**
 * Connection panel
 *
 * @author Yidi Chen
 * @date 2022-01-22
 */
public class ConnectionPanel extends JPanel {

    /**
     * The font
     */
    private static final Font FONT = new Font("Time New Roman", Font.PLAIN, 25);

    /**
     * What need do after connection
     */
    private BiFunction<String, String, Void> then;

    /**
     * Label for IP
     */
    private JLabel lblIP;

    /**
     * Text field for IP
     */
    private JTextField txtIP;

    /**
     * Label for name
     */
    private JLabel lblName;

    /**
     * Text field for name
     */
    private JTextField txtName;

    /**
     * Connection button
     */
    private JButton btnConnect;

    /**
     * Create connection panel
     */
    public ConnectionPanel() {
        // grid layout
        super(new GridLayout(5, 1));

        // set size
        this.setSize(380, 400);

        // create label for ip
        lblIP = new JLabel("Please Enter Your IP", SwingConstants.CENTER);
        lblIP.setFont(FONT);
        add(lblIP);

        // create text field for ip
        txtIP = new JTextField();
        txtIP.setFont(FONT);
        txtIP.setHorizontalAlignment(SwingConstants.CENTER);
        add(txtIP);

        // create label for name
        lblName = new JLabel("Please Enter Your Name", SwingConstants.CENTER);
        lblName.setFont(FONT);
        add(lblName);

        // create text field for name
        txtName = new JTextField();
        txtName.setFont(FONT);
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        add(txtName);

        // connection button
        btnConnect = new JButton("Connect");
        btnConnect.setFont(FONT);
        btnConnect.setHorizontalTextPosition(SwingConstants.CENTER);
        add(btnConnect);
    }

    /**
     * Set what do next
     *
     * @param then what do next
     */
    public void setThen(BiFunction<String, String, Void> then) {
        this.then = then;
        btnConnect.addActionListener((e) -> {
            then.apply(txtIP.getText(), txtName.getText());
        });
    }
}
