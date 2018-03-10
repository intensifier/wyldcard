package com.defano.hypertalk.ast.expressions.containers;

import com.defano.wyldcard.parts.field.AddressableSelection;
import com.defano.wyldcard.parts.model.PartModel;
import com.defano.wyldcard.runtime.context.SelectionContext;
import com.defano.wyldcard.util.ThreadUtils;
import com.defano.hypertalk.ast.model.Preposition;
import com.defano.hypertalk.ast.model.Value;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.utils.Range;
import org.antlr.v4.runtime.ParserRuleContext;

public class SelectionExp extends ContainerExp {

    public SelectionExp(ParserRuleContext context) {
        super(context);
    }

    @Override
    public Value onEvaluate() throws HtException {
        return chunkOf(SelectionContext.getInstance().getSelection(), getChunk());
    }

    @Override
    public void putValue(Value value, Preposition preposition) throws HtException {

        Value oldSelection = SelectionContext.getInstance().getSelection();
        Range range = SelectionContext.getInstance().getSelectionRange();
        AddressableSelection field = SelectionContext.getInstance().getManagedSelection();
        PartModel partModel = SelectionContext.getInstance().getSelectedPart();

        // Create the new selectedText
        Value newSelection;
        if (getChunk() != null)
            newSelection = Value.setChunk(oldSelection, preposition, getChunk(), value);
        else
            newSelection = Value.setValue(oldSelection, preposition, value);

        // Replace the current selection with the new selection
        partModel.setValue(Value.setChunk(partModel.getValue(), Preposition.INTO, range.asChunk(), newSelection));

        // Select the new range of text in the destination
        int newSelectionLength = newSelection.toString().length();
        ThreadUtils.invokeAndWaitAsNeeded(() -> field.setSelection(range.start, range.start + newSelectionLength));
    }

}
