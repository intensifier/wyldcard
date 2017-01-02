package hypercard.gui.window;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import hypercard.context.ToolsContext;
import hypercard.gui.HyperCardWindow;
import hypercard.paint.model.ProvidedValueObserver;

import javax.swing.*;
import java.awt.*;

public class LinesPalette extends HyperCardWindow implements ProvidedValueObserver {
    private JPanel linesPanel;

    private JButton px1;
    private JButton px2;
    private JButton px3;
    private JButton px5;
    private JButton px6;
    private JButton px4;

    private JButton[] allLines;

    public LinesPalette() {
        px1.addActionListener(l -> select(1));
        px2.addActionListener(l -> select(2));
        px3.addActionListener(l -> select(3));
        px4.addActionListener(l -> select(4));
        px5.addActionListener(l -> select(5));
        px6.addActionListener(l -> select(6));

        allLines = new JButton[]{px1, px2, px3, px4, px5, px6};
        ToolsContext.getInstance().getLineStrokeProvider().addObserver(this);
    }

    @Override
    public JPanel getWindowPanel() {
        return linesPanel;
    }

    @Override
    public void bindModel(Object data) {
        // Nothing to do
    }

    @Override
    public void onChanged(Object oldValue, Object newValue) {

        if (newValue instanceof BasicStroke) {
            int newWidth = (int) ((BasicStroke) newValue).getLineWidth();

            for (JButton thisButton : allLines) {
                thisButton.setSelected(false);
            }

            JButton button = getButtonForWidth(newWidth);
            if (button != null) {
                button.setSelected(true);
            }
        }
    }

    private void select(int width) {
        ToolsContext.getInstance().setLineWidth(width);
    }

    private JButton getButtonForWidth(int width) {
        switch (width) {
            case 1:
                return px1;
            case 2:
                return px2;
            case 3:
                return px3;
            case 4:
                return px4;
            case 5:
                return px5;
            case 6:
                return px6;
        }

        return null;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        linesPanel = new JPanel();
        linesPanel.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), 0, 0));
        px1 = new JButton();
        px1.setIcon(new ImageIcon(getClass().getResource("/lines/1.png")));
        px1.setText("");
        linesPanel.add(px1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        px2 = new JButton();
        px2.setIcon(new ImageIcon(getClass().getResource("/lines/2.png")));
        px2.setText("");
        linesPanel.add(px2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        px3 = new JButton();
        px3.setIcon(new ImageIcon(getClass().getResource("/lines/3.png")));
        px3.setText("");
        linesPanel.add(px3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        px4 = new JButton();
        px4.setIcon(new ImageIcon(getClass().getResource("/lines/4.png")));
        px4.setText("");
        linesPanel.add(px4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        px5 = new JButton();
        px5.setIcon(new ImageIcon(getClass().getResource("/lines/5.png")));
        px5.setText("");
        linesPanel.add(px5, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        px6 = new JButton();
        px6.setIcon(new ImageIcon(getClass().getResource("/lines/6.png")));
        px6.setText("");
        linesPanel.add(px6, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return linesPanel;
    }
}
