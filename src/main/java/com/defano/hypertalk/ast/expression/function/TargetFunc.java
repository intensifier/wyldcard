package com.defano.hypertalk.ast.expression.function;

import com.defano.wyldcard.runtime.ExecutionContext;
import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.model.Value;
import org.antlr.v4.runtime.ParserRuleContext;

public class TargetFunc extends Expression {

    public TargetFunc(ParserRuleContext context) {
        super(context);
    }

    @Override
    protected Value onEvaluate(ExecutionContext context) {
        return new Value(context.getTarget().getHyperTalkIdentifier(context));
    }
}
