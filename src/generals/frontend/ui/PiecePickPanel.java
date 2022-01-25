package generals.frontend.ui;

import generals.frontend.GameService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Piece pick panel
 *
 * @author Yidi Chen
 * @date 2022-01-19
 */
public class PiecePickPanel extends JPanel {

    /**
     * Padding
     */
    private static final Border PADDING = new EmptyBorder(20, 20, 20, 20);

    /**
     * number of types
     */
    private static final int[] intAllTypes = {
            0, 1, 2, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };

    /**
     * scroll pane for piece saving panel
     */
    private JScrollPane piecePickingPanelContainer;

    /**
     * piece saving panel
     */
    private JPanel piecePickingPanel;

    /**
     * Constructor of piece pick panel
     *
     * @param chessBoardPanel chess board panel
     * @param gameService     game service
     */
    public PiecePickPanel(ChessBoardPanel chessBoardPanel, GameService gameService) {
        // set appearance
        super(new BorderLayout());
        setSize(380, 380);
        setBorder(PADDING);
        // add all things into pane
        piecePickingPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        piecePickingPanel.setPreferredSize(new Dimension(320, 1000));
        int intType = 0;
        for (var intNum : intAllTypes) {
            for (int intCnt = 0; intCnt < intNum; intCnt++) {
                piecePickingPanel.add(new NotPutChessPanel(intType, chessBoardPanel, gameService));
            }
            intType++;
        }
        // piece picking container
        piecePickingPanelContainer = new JScrollPane(piecePickingPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // set size
        piecePickingPanelContainer.setPreferredSize(new Dimension(320, 340));
        // set location
        piecePickingPanelContainer.setLocation(0, 0);

        this.add(piecePickingPanelContainer, BorderLayout.CENTER);
    }
}
