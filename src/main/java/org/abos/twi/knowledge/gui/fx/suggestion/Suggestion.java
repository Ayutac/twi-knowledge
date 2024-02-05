package org.abos.twi.knowledge.gui.fx.suggestion;

import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;

import java.util.Collection;
import java.util.Objects;

public abstract class Suggestion<T> implements Callback<AutoCompletionBinding.ISuggestionRequest, Collection<T>> {

    protected final Collection<T> collection;

    public Suggestion(Collection<T> collection) {
        this.collection = Objects.requireNonNull(collection);
    }

}
