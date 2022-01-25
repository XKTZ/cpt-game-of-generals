package generals.frontend.ui;

import generals.frontend.HelpChessContainer;

import javax.swing.*;

/**
 * Help panel
 * @author Yidi Chen
 * @date 2022-01-19
 */
public class HelpPanel extends JPanel {

    /**
     * chess container, storing the place to move to
     */
    private HelpChessContainer chessContainer = new HelpChessContainer();

    /**
     * Constructor
     */
    public HelpPanel() {
        super(null);
        // size
        setSize(900, 720);

        // set drag label
        JLabel labelDrag = new JLabel("Try to move the piece by dragging");
        labelDrag.setSize(250, 30);
        labelDrag.setLocation(50, 300);

        // set connect label
        JLabel labelConnect = new JLabel("If you want to start, please connect to the server");
        labelConnect.setSize(300, 30);
        labelConnect.setLocation(50, 350);

        // create two chess panels, cyclic reference
        HelpChessPanel helpChessPanel1 = new HelpChessPanel(chessContainer);
        HelpChessPanel helpChessPanel2 = new HelpChessPanel(chessContainer, helpChessPanel1);
        helpChessPanel1.setNext(helpChessPanel2);
        helpChessPanel1.setLocation(400, 250);
        helpChessPanel2.setLocation(400, 340);
        helpChessPanel1.setChessOn(true);

        // add all
        add(helpChessPanel1);
        add(helpChessPanel2);
        add(labelDrag);
        add(labelConnect);
    }
}
