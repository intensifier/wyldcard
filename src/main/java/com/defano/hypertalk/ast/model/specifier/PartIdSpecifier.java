package com.defano.hypertalk.ast.model.specifier;

import com.defano.hypertalk.ast.model.enums.Owner;
import com.defano.hypertalk.ast.model.enums.PartType;
import com.defano.hypertalk.exception.HtNoSuchPartException;
import com.defano.wyldcard.part.finder.FindInCollectionSpecifier;
import com.defano.wyldcard.part.model.PartModel;
import com.defano.wyldcard.runtime.ExecutionContext;

import java.util.List;
import java.util.Optional;

/**
 * Specifies a button, field, card or background by its ID. For example, 'card id 13' or 'bg field id 11'
 */
public class PartIdSpecifier implements FindInCollectionSpecifier {

    private final Owner layer;
    private final PartType type;
    private final int id;

    public PartIdSpecifier(Owner layer, PartType type, int id) {
        this.layer = layer;
        this.type = type;
        this.id = id;
    }

    public PartModel findInCollection(ExecutionContext context, List<PartModel> parts) throws HtNoSuchPartException {
        Optional<PartModel> foundPart = parts.stream()
                .filter(p -> getType() == null || p.getType() == getType())
                .filter(p -> getOwner() == null || p.getOwner() == getOwner())
                .filter(p -> p.getId() == getValue())
                .findFirst();

        if (foundPart.isPresent()) {
            return foundPart.get();
        } else {
            throw new HtNoSuchPartException("No " + getHyperTalkIdentifier(context) + " found.");
        }
    }

    @Override
    public Owner getOwner() {
        return layer;
    }

    @Override
    public PartType getType() {
        return type;
    }

    @Override
    public Integer getValue() {
        return id;
    }

    @Override
    public String getHyperTalkIdentifier(ExecutionContext context) {
        if (getOwner() == null || getOwner() == Owner.STACK) {
            return type.toString().toLowerCase() + " id " + id;
        } else {
            return getOwner().name().toLowerCase() + " " + type.toString().toLowerCase() + " id " + id;
        }
    }

    @Override
    public String toString() {
        return "PartIdSpecifier{" +
                "layer=" + layer +
                ", type=" + type +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartIdSpecifier that = (PartIdSpecifier) o;

        if (id != that.id) return false;
        if (layer != that.layer) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = layer != null ? layer.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
