package com.defano.hypertalk.ast.statement.command;

import com.defano.wyldcard.part.model.PartModel;
import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.statement.Command;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.exception.HtSemanticException;
import com.defano.wyldcard.runtime.ExecutionContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class EditScriptCmd extends Command {

    private final Expression partExpression;

    public EditScriptCmd(ParserRuleContext context, Expression partExpression) {
        super(context, "edit");
        this.partExpression = partExpression;
    }

    @Override
    protected void onExecute(ExecutionContext context) throws HtException {
        partExpression.partFactor(context, PartModel.class, new HtSemanticException("No such part.")).editScript(context);
    }
}
