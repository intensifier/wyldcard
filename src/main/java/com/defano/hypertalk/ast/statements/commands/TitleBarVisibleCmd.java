package com.defano.hypertalk.ast.statements.commands;

import com.defano.wyldcard.window.WindowManager;
import com.defano.hypertalk.ast.breakpoints.Breakpoint;
import com.defano.hypertalk.ast.statements.Command;
import com.defano.hypertalk.exception.HtException;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.swing.*;

public class TitleBarVisibleCmd extends Command {

    private final boolean visibility;

    public TitleBarVisibleCmd(ParserRuleContext context, boolean visibility) {
        super(context, visibility ? "show" : "hide");
        this.visibility = visibility;
    }

    @Override
    protected void onExecute() throws HtException, Breakpoint {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = WindowManager.getInstance().getStackWindow().getWindow();

            frame.dispose();
            frame.setUndecorated(!visibility);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
