package com.defano.hypertalk.ast.statement.command;

import com.defano.hypertalk.ast.model.Destination;
import com.defano.wyldcard.WyldCard;
import com.defano.wyldcard.part.bkgnd.BackgroundModel;
import com.defano.wyldcard.part.card.CardModel;
import com.defano.wyldcard.part.model.PartModel;
import com.defano.hypertalk.ast.expression.Expression;
import com.defano.hypertalk.ast.statement.Command;
import com.defano.hypertalk.exception.HtException;
import com.defano.hypertalk.exception.HtSemanticException;
import com.defano.wyldcard.runtime.ExecutionContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class PushCardCmd extends Command {

    private final Expression destinationExp;

    public PushCardCmd(ParserRuleContext context) {
        this(context, null);
    }

    public PushCardCmd(ParserRuleContext context, Expression destinationExp) {
        super(context, "push");
        this.destinationExp = destinationExp;
    }

    @Override
    protected void onExecute(ExecutionContext context) throws HtException {
        if (destinationExp == null) {
            push(new Destination(context.getCurrentStack().getStackModel(), context.getCurrentStack().getDisplayedCard().getId(context)));
        } else {

            Integer pushCardId = null;
            PartModel destinationModel = destinationExp.partFactor(context, CardModel.class);
            if (destinationModel == null) {
                destinationExp.partFactor(context, BackgroundModel.class);
            }

            if (destinationModel != null) {
                pushCardId = evaluateAsCardId(context, destinationModel);
            }

            if (pushCardId != null) {
                push(new Destination(context.getCurrentStack().getStackModel(), pushCardId));
            } else {
                throw new HtSemanticException("Can't push that.");
            }
        }
    }

    private Integer evaluateAsCardId(ExecutionContext context, PartModel model) {
        if (model instanceof CardModel) {
            return model.getId();
        } else if (model instanceof BackgroundModel) {
            int cardIndex = context.getCurrentStack().getStackModel().getIndexOfBackground(model.getId());
            return context.getCurrentStack().getStackModel().getCardModel(cardIndex).getId();
        } else {
            return null;
        }
    }

    private void push(Destination destination) {
        WyldCard.getInstance().getNavigationManager().push(destination);
    }
}
