/*
 * HandlerExecutionTask
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:10 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypercard.runtime;

import com.defano.hypercard.runtime.context.ExecutionContext;
import com.defano.hypertalk.ast.common.ExpressionList;
import com.defano.hypertalk.ast.common.NamedBlock;
import com.defano.hypertalk.ast.common.Value;
import com.defano.hypertalk.ast.containers.PartSpecifier;
import com.defano.hypertalk.exception.HtException;

import java.util.List;
import java.util.concurrent.Callable;

public class HandlerExecutionTask implements Callable<String> {

    private final NamedBlock handler;
    private final PartSpecifier me;
    private final ExpressionList arguments;

    public HandlerExecutionTask(PartSpecifier me, NamedBlock handler, ExpressionList arguments) {
        this.handler = handler;
        this.me = me;
        this.arguments = arguments;
    }

    @Override
    public String call() throws HtException {

        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        // Arguments passed to function must be evaluated in the context of the caller (i.e., before we push a new stack frame)
        List<Value> evaluatedArguments = arguments.evaluateDisallowingCoordinates();

        ExecutionContext.getContext().pushContext();
        ExecutionContext.getContext().setMe(me);
        ExecutionContext.getContext().setParams(evaluatedArguments);
        ExecutionContext.getContext().setMessage(handler.name);

        // Bind argument values to parameter variables in this context
        for (int index = 0; index < handler.parameters.list.size(); index++) {
            String theParam = handler.parameters.list.get(index);

            // Handlers may be invoked with missing arguments; assume empty for missing args
            Value theArg = index >= evaluatedArguments.size() ? new Value() : evaluatedArguments.get(index);

            ExecutionContext.getContext().set(theParam, theArg);
        }

        handler.statements.execute();

        return ExecutionContext.getContext().getPassedMessage();
    }
}
