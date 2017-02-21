/*
 * FontUtils
 * hypertalk-java
 *
 * Created by Matt DeFano on 2/19/17 3:10 PM.
 * Copyright © 2017 Matt DeFano. All rights reserved.
 */

package com.defano.hypercard.fonts;

import com.defano.hypertalk.ast.common.Value;

import javax.swing.*;
import java.awt.*;

public class FontUtils {

    public static int getAlignmentForValue(Value v) {
        switch (v.stringValue().trim().toLowerCase()) {
            case "left":
                return SwingUtilities.LEFT;
            case "right":
                return SwingUtilities.RIGHT;

            default:
            case "center":
                return SwingUtilities.CENTER;
        }
    }

    public static int getStyleForValue(Value v) {
        int style = Font.PLAIN;

        for (Value thisValue : v.getItems()) {
            switch (thisValue.stringValue().trim().toLowerCase()) {
                case "plain":
                    style = Font.PLAIN;
                    break;
                case "italic":
                    style |= Font.ITALIC;
                    break;
                case "bold":
                    style |= Font.BOLD;
                    break;
            }
        }

        return style;
    }

    public static Value getValueForStyle(int fontStyle) {
        if (fontStyle == Font.ITALIC) {
            return new Value("italic");
        } else if (fontStyle == Font.BOLD) {
            return new Value("bold");
        } else if (fontStyle == (Font.BOLD | Font.ITALIC)) {
            return new Value("bold, italic");
        }

        return new Value("plain");
    }

}