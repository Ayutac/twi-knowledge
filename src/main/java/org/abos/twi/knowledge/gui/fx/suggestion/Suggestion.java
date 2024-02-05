package org.abos.twi.knowledge.gui.fx.suggestion;

import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Suggestion<T> implements Callback<AutoCompletionBinding.ISuggestionRequest, Collection<T>> {

    protected final Collection<T> collection = new ArrayList<>();

    protected Suggestion(Collection<T> collection) {
        this.collection.addAll(collection);
    }

    public Collection<T> getCollection() {
        return collection;
    }

    public void setCollection(Collection<T> collection) {
        this.collection.clear();
        this.collection.addAll(collection);
    }
}
