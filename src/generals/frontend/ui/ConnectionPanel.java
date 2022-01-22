package generals.frontend.ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author Yidi Chen
 * @date 2022-01-22
 */
public class ConnectionPanel extends JPanel {

    private static final Font FONT = new Font("Time New Roman", Font.PLAIN, 25);

    private BiFunction<String, String, Void> then;

    private JLabel lblIP;

    private JTextField txtIP;

    private JLabel lblName;

    private JTextField txtName;

    private JButton btnConnect;

    public ConnectionPanel() {
        super(new GridLayout(5, 1));
        this.setSize(380, 400);

        lblIP = new JLabel("Please Enter Your IP", SwingConstants.CENTER);
        lblIP.setFont(FONT);
        add(lblIP);

        txtIP = new JTextField();
        txtIP.setFont(FONT);
        txtIP.setHorizontalAlignment(SwingConstants.CENTER);
        add(txtIP);

        lblName = new JLabel("Please Enter Your Name", SwingConstants.CENTER);
        lblName.setFont(FONT);
        add(lblName);

        txtName = new JTextField();
        txtName.setFont(FONT);
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        add(txtName);

        btnConnect = new JButton("Connect");
        btnConnect.setFont(FONT);
        btnConnect.setHorizontalTextPosition(SwingConstants.CENTER);
        add(btnConnect);
    }

    public void setThen(BiFunction<String, String, Void> then) {
        this.then = then;
        btnConnect.addActionListener((e) -> {
            then.apply(txtIP.getText(), txtName.getText());
        });
    }
}
