package com.defano.hypertalk.ast.expression.function;

import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.model.Value;
import com.defano.wyldcard.runtime.ExecutionContext;
import com.defano.wyldcard.window.WindowManager;
import com.google.inject.Inject;
import org.antlr.v4.runtime.ParserRuleContext;

public class WindowsFunc extends Expression {

    @Inject
    private WindowManager windowManager;

    public WindowsFunc(ParserRuleContext context) {
        super(context);
    }

    @Override
    protected Value onEvaluate(ExecutionContext context) {
        return Value.ofLines(windowManager.getWindowNames());
    }
}
