/*
 * ButtonPropertyEditor
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:10 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypercard.gui.window;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.defano.hypercard.gui.HyperCardWindow;
import com.defano.hypercard.parts.buttons.ButtonStyle;
import com.defano.hypercard.parts.model.ButtonModel;
import com.defano.hypercard.parts.model.AbstractPartModel;
import com.defano.hypercard.runtime.WindowManager;
import com.defano.hypertalk.ast.common.Value;

import javax.swing.*;
import java.awt.*;

public class ButtonPropertyEditor extends HyperCardWindow {
    private AbstractPartModel model;

    private JButton saveButton;
    private JButton cancelButton;
    private JButton editScriptButton;
    private JTextField buttonName;
    private JTextField buttonHeight;
    private JTextField buttonWidth;
    private JTextField buttonTop;
    private JTextField buttonLeft;
    private JCheckBox isVisible;
    private JCheckBox isShowTitle;
    private JCheckBox isEnabled;
    private JLabel buttonId;
    private JPanel buttonEditor;
    private JComboBox style;
    private JComboBox family;
    private JButton contents;
    private JCheckBox autoHilite;

    public ButtonPropertyEditor() {
        editScriptButton.addActionListener(e -> {
            dispose();
            WindowBuilder.make(new ScriptEditor())
                    .withTitle("Script of button " + model.getKnownProperty(ButtonModel.PROP_NAME).stringValue())
                    .withModel(model)
                    .resizeable(true)
                    .withLocationCenteredOver(WindowManager.getStackWindow().getWindowPanel())
                    .build();
        });

        cancelButton.addActionListener(e -> dispose());
        contents.addActionListener(e -> showContentsEditor());

        saveButton.addActionListener(e -> {
            updateProperties();
            dispose();
        });

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (ButtonStyle thisStyle : ButtonStyle.values()) {
            model.addElement(thisStyle.getName());
        }
        style.setModel(model);
    }

    @Override
    public JPanel getWindowPanel() {
        return buttonEditor;
    }

    @Override
    public void bindModel(Object data) {
        this.model = (AbstractPartModel) data;

        buttonName.setText(model.getKnownProperty(ButtonModel.PROP_NAME).stringValue());
        buttonId.setText(model.getKnownProperty(ButtonModel.PROP_ID).stringValue());
        buttonTop.setText(model.getKnownProperty(ButtonModel.PROP_TOP).stringValue());
        buttonLeft.setText(model.getKnownProperty(ButtonModel.PROP_LEFT).stringValue());
        buttonHeight.setText(model.getKnownProperty(ButtonModel.PROP_HEIGHT).stringValue());
        buttonWidth.setText(model.getKnownProperty(ButtonModel.PROP_WIDTH).stringValue());
        isEnabled.setSelected(model.getKnownProperty(ButtonModel.PROP_ENABLED).booleanValue());
        isShowTitle.setSelected(model.getKnownProperty(ButtonModel.PROP_SHOWNAME).booleanValue());
        isVisible.setSelected(model.getKnownProperty(ButtonModel.PROP_VISIBLE).booleanValue());
        style.setSelectedItem(model.getKnownProperty(ButtonModel.PROP_STYLE).stringValue());
        family.setSelectedItem(model.getKnownProperty(ButtonModel.PROP_FAMILY).stringValue());
        autoHilite.setSelected(model.getKnownProperty(ButtonModel.PROP_AUTOHILIGHT).booleanValue());
    }

    private void updateProperties() {
        model.setKnownProperty(ButtonModel.PROP_NAME, new Value(buttonName.getText()));
        model.setKnownProperty(ButtonModel.PROP_TOP, new Value(buttonTop.getText()));
        model.setKnownProperty(ButtonModel.PROP_LEFT, new Value(buttonLeft.getText()));
        model.setKnownProperty(ButtonModel.PROP_HEIGHT, new Value(buttonHeight.getText()));
        model.setKnownProperty(ButtonModel.PROP_WIDTH, new Value(buttonWidth.getText()));
        model.setKnownProperty(ButtonModel.PROP_ENABLED, new Value(isEnabled.isSelected()));
        model.setKnownProperty(ButtonModel.PROP_SHOWNAME, new Value(isShowTitle.isSelected()));
        model.setKnownProperty(ButtonModel.PROP_VISIBLE, new Value(isVisible.isSelected()));
        model.setKnownProperty(ButtonModel.PROP_STYLE, new Value(style.getSelectedItem().toString()));
        model.setKnownProperty(ButtonModel.PROP_FAMILY, new Value(family.getSelectedItem().toString()));
        model.setKnownProperty(ButtonModel.PROP_AUTOHILIGHT, new Value(autoHilite.isSelected()));
    }

    private void showContentsEditor() {
        String result = (String) JOptionPane.showInputDialog(
                getWindowPanel(),
                "Edit the contents of this button.",
                "Contents",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                model.getKnownProperty(ButtonModel.PROP_CONTENTS).stringValue());

        if (result != null) {
            model.setKnownProperty(ButtonModel.PROP_CONTENTS, new Value(result));
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        buttonEditor = new JPanel();
        buttonEditor.setLayout(new GridLayoutManager(3, 5, new Insets(10, 10, 10, 10), -1, -1));
        buttonEditor.setMaximumSize(new Dimension(587, 257));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 4, new Insets(5, 5, 5, 5), -1, -1));
        buttonEditor.add(panel1, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Identification"));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(4);
        label1.setText("Button Name:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(90, -1), null, new Dimension(90, -1), 0, false));
        buttonName = new JTextField();
        panel1.add(buttonName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(2);
        label2.setInheritsPopupMenu(false);
        label2.setText("ID:");
        panel1.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonId = new JLabel();
        buttonId.setText("id");
        panel1.add(buttonId, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 5, new Insets(5, 5, 5, 5), -1, -1));
        buttonEditor.add(panel2, new GridConstraints(1, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Location"));
        final JLabel label3 = new JLabel();
        label3.setText("Height:");
        panel2.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Width:");
        panel2.add(label4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonHeight = new JTextField();
        panel2.add(buttonHeight, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), new Dimension(50, -1), 0, false));
        buttonWidth = new JTextField();
        panel2.add(buttonWidth, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Top:");
        panel2.add(label5, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Left:");
        panel2.add(label6, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonTop = new JTextField();
        panel2.add(buttonTop, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), new Dimension(50, -1), 0, false));
        buttonLeft = new JTextField();
        panel2.add(buttonLeft, new GridConstraints(1, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, 0));
        buttonEditor.add(panel3, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        panel3.add(saveButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel3.add(cancelButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(4, 3, new Insets(5, 5, 5, 5), -1, -1));
        buttonEditor.add(panel4, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Style"));
        style = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        style.setModel(defaultComboBoxModel1);
        panel4.add(style, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        family = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("None");
        defaultComboBoxModel2.addElement("1");
        defaultComboBoxModel2.addElement("2");
        defaultComboBoxModel2.addElement("3");
        defaultComboBoxModel2.addElement("4");
        defaultComboBoxModel2.addElement("5");
        defaultComboBoxModel2.addElement("6");
        defaultComboBoxModel2.addElement("7");
        defaultComboBoxModel2.addElement("8");
        defaultComboBoxModel2.addElement("9");
        defaultComboBoxModel2.addElement("10");
        defaultComboBoxModel2.addElement("11");
        defaultComboBoxModel2.addElement("12");
        defaultComboBoxModel2.addElement("13");
        defaultComboBoxModel2.addElement("14");
        defaultComboBoxModel2.addElement("15");
        family.setModel(defaultComboBoxModel2);
        panel4.add(family, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Style");
        panel4.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Family");
        panel4.add(label8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        isVisible = new JCheckBox();
        isVisible.setText("Visible");
        panel4.add(isVisible, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        isShowTitle = new JCheckBox();
        isShowTitle.setText("Show Name");
        panel4.add(isShowTitle, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        isEnabled = new JCheckBox();
        isEnabled.setText("Enabled");
        panel4.add(isEnabled, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        autoHilite = new JCheckBox();
        autoHilite.setText("Auto Hilight");
        panel4.add(autoHilite, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        buttonEditor.add(spacer1, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        buttonEditor.add(spacer2, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        editScriptButton = new JButton();
        editScriptButton.setText("Edit Script...");
        buttonEditor.add(editScriptButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contents = new JButton();
        contents.setText("Contents...");
        buttonEditor.add(contents, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return buttonEditor;
    }
}