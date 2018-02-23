package com.defano.hypercard.paint;

import com.defano.jmonet.tools.builder.StrokeBuilder;

import java.awt.*;

public enum PaintBrush {

    SQUARE_16X16(StrokeBuilder.withShape().ofSquare(16).build()),
    SQUARE_12X12(StrokeBuilder.withShape().ofSquare(12).build()),
    SQUARE_8X8(StrokeBuilder.withShape().ofSquare(8).build()),
    SQUARE_4X4(StrokeBuilder.withShape().ofSquare(4).build()),

    ROUND_16X16(StrokeBuilder.withBasicStroke().ofWidth(16).withRoundCap().withRoundJoin().build()),
    ROUND_12X12(StrokeBuilder.withBasicStroke().ofWidth(12).withRoundCap().withRoundJoin().build()),
    ROUND_8X8(StrokeBuilder.withBasicStroke().ofWidth(8).withRoundCap().withRoundJoin().build()),
    ROUND_4X4(StrokeBuilder.withBasicStroke().ofWidth(4).withRoundCap().withRoundJoin().build()),

    LINE_16(StrokeBuilder.withShape().ofVerticalLine(16).build()),
    LINE_12(StrokeBuilder.withShape().ofVerticalLine(12).build()),
    LINE_8(StrokeBuilder.withShape().ofVerticalLine(8).build()),
    LINE_4(StrokeBuilder.withShape().ofVerticalLine(4).build());

    public final Stroke stroke;

    PaintBrush(Stroke stroke) {
        this.stroke = stroke;
    }
}