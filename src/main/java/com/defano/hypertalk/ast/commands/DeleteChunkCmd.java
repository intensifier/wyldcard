package com.defano.hypertalk.ast.commands;

import com.defano.hypertalk.ast.common.Value;
import com.defano.hypertalk.ast.containers.Container;
import com.defano.hypertalk.ast.common.Preposition;
import com.defano.hypertalk.ast.statements.Command;
import com.defano.hypertalk.exception.HtException;

public class DeleteChunkCmd extends Command {

    private final Container container;

    public DeleteChunkCmd(Container container) {
        super("delete");
        this.container = container;
    }

    @Override
    public void onExecute() throws HtException {
        container.putValue(new Value(), Preposition.INTO);
    }
}
