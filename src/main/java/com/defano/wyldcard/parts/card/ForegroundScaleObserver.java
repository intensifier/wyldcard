package com.defano.wyldcard.parts.card;

import com.defano.hypertalk.ast.model.Owner;
import com.defano.wyldcard.runtime.context.ExecutionContext;
import com.defano.wyldcard.util.ThreadUtils;
import io.reactivex.functions.Consumer;

class ForegroundScaleObserver implements Consumer<Double> {
    private CardPart cardPart;

    public ForegroundScaleObserver(CardPart cardPart) {
        this.cardPart = cardPart;
    }

    @Override
    public void accept(Double scale) {
        ThreadUtils.invokeAndWaitAsNeeded(() -> {
            cardPart.setPartsOnLayerVisible(new ExecutionContext(), Owner.CARD, scale == 1.0);
            cardPart.setPartsOnLayerVisible(new ExecutionContext(), Owner.BACKGROUND, scale == 1.0);
            cardPart.setBackgroundVisible(new ExecutionContext(), scale == 1.0);
        });
    }
}