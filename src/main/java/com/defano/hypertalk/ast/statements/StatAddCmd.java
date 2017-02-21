/*
 * StatAddCmd
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:12 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypertalk.ast.statements;

import com.defano.hypertalk.ast.containers.Container;
import com.defano.hypertalk.ast.containers.Preposition;
import com.defano.hypertalk.ast.expressions.Expression;
import com.defano.hypertalk.exception.HtException;

public class StatAddCmd extends Statement {

    private final Expression expression;
    private final Container container;

    public StatAddCmd (Expression source, Container container) {
        this.expression = source;
        this.container = container;
    }

    public void execute() throws HtException {
        container.putValue(container.getValue().add(expression.evaluate()), Preposition.INTO);
    }
}