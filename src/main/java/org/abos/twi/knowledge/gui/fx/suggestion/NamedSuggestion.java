package org.abos.twi.knowledge.gui.fx.suggestion;

import org.abos.common.Named;
import org.controlsfx.control.textfield.AutoCompletionBinding;

import java.util.Collection;
import java.util.List;

public final class NamedSuggestion<T extends Named> extends Suggestion<T> {

    public NamedSuggestion(Collection<T> collection) {
        super(collection);
    }

    @Override
    public Collection<T> call(AutoCompletionBinding.ISuggestionRequest request) {
        if (request.isCancelled()) {
            return List.of();
        }
        return collection.stream().filter(named -> named.getName().contains(request.getUserText())).toList();
    }
}
