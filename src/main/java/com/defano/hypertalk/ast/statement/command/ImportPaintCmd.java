package com.defano.hypertalk.ast.statement.command;

import com.defano.wyldcard.paint.ArtVandelay;
import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.statement.Command;
import com.defano.hypertalk.exception.HtException;
import com.defano.wyldcard.runtime.ExecutionContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.File;

public class ImportPaintCmd extends Command {

    private final Expression fileExpression;

    public ImportPaintCmd(ParserRuleContext context, Expression fileExpression) {
        super(context, "import");
        this.fileExpression = fileExpression;
    }

    @Override
    protected void onExecute(ExecutionContext context) throws HtException {
        ArtVandelay.importPaint(new File(fileExpression.evaluate(context).toString()));
    }
}
