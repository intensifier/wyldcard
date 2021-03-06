package com.defano.wyldcard.part.builder;

import com.defano.hypertalk.ast.model.enums.Owner;
import com.defano.hypertalk.ast.model.Value;
import com.defano.wyldcard.part.field.FieldModel;
import com.defano.wyldcard.part.model.PartModel;

public class FieldModelBuilder extends PartModelBuilder<FieldModel, FieldModelBuilder> {

    private final FieldModel fieldModel;

    public FieldModelBuilder(Owner owner, PartModel parentPartModel) {
        this.fieldModel = new FieldModel(owner, parentPartModel);
    }

    @Override
    public FieldModelBuilder withPartStyle(Object v) {
        super.withPartStyle(v);

        if (String.valueOf(v).equalsIgnoreCase("scrolling")) {
            this.fieldModel.set(context, FieldModel.PROP_SCROLLING, new Value(true));
        }

        return this;
    }

    public FieldModelBuilder withText(Object v) {
        if (v != null) {
            this.fieldModel.set(context, FieldModel.PROP_TEXT, new Value(v));
        }
        return this;
    }

    public FieldModelBuilder withDontWrap(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_DONTWRAP, new Value(v));
        return this;
    }

    public FieldModelBuilder withDontSearch(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_DONTSEARCH, new Value(v));
        return this;
    }

    public FieldModelBuilder withSharedText(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_SHAREDTEXT, new Value(v));
        return this;
    }

    public FieldModelBuilder withAutoTab(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_AUTOTAB, new Value(v));
        return this;
    }

    public FieldModelBuilder withLockText(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_LOCKTEXT, new Value(v));
        return this;
    }

    public FieldModelBuilder withAutoSelect(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_AUTOSELECT, new Value(v));
        return this;
    }

    public FieldModelBuilder withShowLines(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_SHOWLINES, new Value(v));
        return this;
    }

    public FieldModelBuilder withWideMargins(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_WIDEMARGINS, new Value(v));
        return this;
    }

    public FieldModelBuilder withMultipleLines(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_MULTIPLELINES, new Value(v));
        return this;
    }

    public FieldModelBuilder withPartNumber(Object v) {
        this.fieldModel.set(context, FieldModel.PROP_ZORDER, new Value(v));
        return this;
    }

    @Override
    public FieldModel build() {
        return fieldModel;
    }

    @Override
    public FieldModelBuilder getBuilder() {
        return this;
    }
}
