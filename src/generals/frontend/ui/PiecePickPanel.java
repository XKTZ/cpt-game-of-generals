package generals.frontend.ui;

import generals.frontend.GameService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @author Yidi Chen
 * @date 2022-01-19
 */
public class PiecePickPanel extends JPanel {

    private static final Border PADDING = new EmptyBorder(20, 20, 20, 20);

    private static final int[] intAllTypes = {
            0, 1, 2, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };

    private JScrollPane piecePickingPaneContainer;

    private JPanel piecePickingPane;

    public PiecePickPanel() {
        super(new BorderLayout());
        setSize(380, 380);
        setBorder(PADDING);
    }

    public void init(ChessBoardPanel chessBoardPanel, GameService gameService) {
        // add all things into pane
        piecePickingPane = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        piecePickingPane.setPreferredSize(new Dimension(320, 1000));
        int intType = 0;
        for (var intNum : intAllTypes) {
            for (int intCnt = 0; intCnt < intNum; intCnt++) {
                piecePickingPane.add(new NotPutChessPanel(intType, chessBoardPanel, gameService));
            }
            intType++;
        }
        // piece picking container
        piecePickingPaneContainer = new JScrollPane(piecePickingPane,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // set size
        piecePickingPaneContainer.setPreferredSize(new Dimension(320, 340));
        // set location
        piecePickingPaneContainer.setLocation(0, 0);

        this.add(piecePickingPaneContainer, BorderLayout.CENTER);
    }
}
