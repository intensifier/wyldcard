package com.defano.hypertalk.ast.statements.commands;

import com.defano.hypertalk.ast.preemptions.Preemption;
import com.defano.hypertalk.ast.statements.Command;
import com.defano.hypertalk.exception.HtException;
import com.defano.wyldcard.WyldCard;
import com.defano.wyldcard.runtime.context.ExecutionContext;
import com.defano.wyldcard.util.ThreadUtils;
import org.antlr.v4.runtime.ParserRuleContext;

import javax.swing.*;

public class MenuBarVisibleCmd extends Command {

    private final boolean visible;

    public MenuBarVisibleCmd(ParserRuleContext context, boolean visible) {
        super(context, visible ? "show" : "hide");
        this.visible = visible;
    }

    @Override
    protected void onExecute(ExecutionContext context) throws HtException, Preemption {
        ThreadUtils.invokeAndWaitAsNeeded(() -> {
            WyldCard.getInstance().getWyldCardMenuBar().setVisible(visible);

            JFrame frame = context.getCurrentStack().getOwningStackWindow();
            frame.dispose();
            frame.pack();
            frame.setVisible(true);
        });
    }
}