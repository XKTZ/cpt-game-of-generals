package generals.frontend;

import generals.frontend.ui.HelpChessPanel;

/**
 * Help chess container
 *
 * @author Yidi Chen
 * @date 2022-01-19
 */
public class HelpChessContainer {

    /**
     * Chess panel saved
     */
    private HelpChessPanel panel;

    /**
     * Constructor
     */
    public HelpChessContainer() {
    }

    /**
     * Set panel
     *
     * @param panel panel
     */
    public void setPanel(HelpChessPanel panel) {
        this.panel = panel;
    }

    /**
     * Get panel
     *
     * @return panel
     */
    public HelpChessPanel getPanel() {
        return panel;
    }
}
