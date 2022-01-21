package generals.frontend.ui;

import generals.frontend.HelpChessContainer;

import javax.swing.*;

/**
 * @author Yidi Chen
 * @date 2022-01-19
 */
public class HelpPanel extends JPanel {

    private HelpChessContainer chessContainer = new HelpChessContainer();

    public HelpPanel() {
        super(null);
        setSize(900, 720);

        JLabel labelDrag = new JLabel("Try to move the piece by dragging");
        labelDrag.setSize(250, 30);
        labelDrag.setLocation(50, 300);

        JLabel labelConnect = new JLabel("If you want to start, please connect to the server");
        labelConnect.setSize(300, 30);
        labelConnect.setLocation(50, 350);

        add(labelDrag);
        add(labelConnect);

        HelpChessPanel helpChessPanel1 = new HelpChessPanel(chessContainer);
        HelpChessPanel helpChessPanel2 = new HelpChessPanel(chessContainer, helpChessPanel1);
        helpChessPanel1.setNext(helpChessPanel2);
        helpChessPanel1.setLocation(400, 250);
        helpChessPanel2.setLocation(400, 340);
        helpChessPanel1.setChessOn(true);

        add(helpChessPanel1);
        add(helpChessPanel2);
    }
}
