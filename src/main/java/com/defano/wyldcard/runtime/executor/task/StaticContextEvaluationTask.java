package com.defano.wyldcard.runtime.executor.task;

import com.defano.hypertalk.ast.model.Script;
import com.defano.hypertalk.ast.model.Value;
import com.defano.hypertalk.ast.preemption.Preemption;
import com.defano.hypertalk.ast.statement.ExpressionStatement;
import com.defano.hypertalk.ast.statement.Statement;
import com.defano.hypertalk.ast.statement.StatementList;
import com.defano.hypertalk.ast.statement.command.MessageCmd;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.exception.HtSemanticException;
import com.defano.wyldcard.WyldCard;
import com.defano.wyldcard.runtime.compiler.CompilationUnit;
import com.defano.wyldcard.runtime.compiler.ScriptCompiler;
import com.defano.wyldcard.runtime.ExecutionContext;

import java.util.concurrent.Callable;

public class StaticContextEvaluationTask implements Callable<String>, MeteredTask {

    private final ExecutionContext context;
    private final String messageText;

    public StaticContextEvaluationTask(ExecutionContext staticContext, String messageText) {
        this.context = staticContext;
        this.messageText = messageText;
        this.context.setStaticContext(true);
    }

    @Override
    public String call() throws HtException {
        started();

        StatementList statements = ((Script) ScriptCompiler.blockingCompile(CompilationUnit.SCRIPTLET, messageText)).getStatements();

        if (context.getStackDepth() == 0) {
            context.pushStackFrame();
        }

        context.clearAbort();
        context.setTarget(WyldCard.getInstance().getStackManager().getFocusedCard().getPartModel().getPartSpecifier(context));

        try {
            statements.execute(context);
        } catch (Preemption e) {
            return null;            // Can't really exit from here; just return null
        } finally {
            stopped();
        }

        Statement lastStatement = statements.list.get(statements.list.size() - 1);

        // Special case: If last statement was an unknown literal (interpreted as a message), then return the variable-
        // evaluation of that literal
        if (lastStatement instanceof MessageCmd) {
            return context.getVariable(lastStatement.getToken().getText()).toString();
        }

        // When the last statement is an expression, return the result of evaluating the expression
        else if (lastStatement instanceof ExpressionStatement) {
            return context.getIt().toString();
        }

        // Special case: Command entered into the message provided a result; treat as error
        else if (!context.getResult().isEmpty()) {
            String resultError = context.getResult().toString();
            context.setResult(new Value());
            throw new HtSemanticException(resultError);
        }

        // No guesses.
        else {
            return null;
        }
    }

}
