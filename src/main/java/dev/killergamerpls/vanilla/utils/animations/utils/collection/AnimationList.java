package dev.killergamerpls.vanilla.utils.animations.utils.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class AnimationList<T> extends ArrayList<T> {

    public AnimationList() {
        super();
    }

    public AnimationList(int cap) {
        super(cap);
    }

    public AnimationList(Collection<T> collection) {
        super();
        if (collection == null) return;
        for (T t : collection) {
            add(t);
        }
    }

    @SafeVarargs
    public AnimationList(T... array) {
        super();
        if (array == null) return;
        for (T t : array) {
            add(t);
        }
    }

    @SafeVarargs
    public final void add(T... ts) {
        for (T t : ts) {
            add(t);
        }
    }

    public void add(Collection<T> collection) {
        for (T t : collection) {
            add(t);
        }
    }

    public void addFirst(T t) {
        add(0, t);
    }

    public T first() {
        if (size() < 1) return null;
        return get(0);
    }

    public T random() {
        return get(randomIndex());
    }

    public T last() {
        if (size() < 1) return null;
        return get(lastIndex());
    }

    /**
     * Pop the first item off this list and return it
     *
     * @return the item or null if the list is empty
     */
    public T pop() {
        if (isEmpty()) return null;
        return remove(0);
    }

    /**
     * Pop the last item off this list and return it
     *
     * @return the item or null if the list is empty
     */
    public T popLast() {
        if (isEmpty()) return null;
        return remove(lastIndex());
    }

    public T popRandom() {
        if (isEmpty()) return null;
        if (size() == 1) {
            return pop();
        }
        return remove(randomIndex());
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean hasElements() {
        return !isEmpty();
    }

    /**
     * Get the index of the last item.
     *
     * @return index of the last item.
     */
    public int lastIndex() {
        return size() - 1;
    }

    public int randomIndex() {
        return ThreadLocalRandom.current().nextInt(0, size() - 1);
    }

    public boolean hasDuplicates() {
        return size() != new LinkedHashSet<>(this).size();
    }

    public boolean hasIndex(int i) {
        return i >= 0 && i < size();
    }

    public AnimationList<T> copy() {
        return new AnimationList<>(this);
    }

    public AnimationList<T> sort() {
        sort(null);
        return this;
    }

    public AnimationList<T> sortCopy() {
        AnimationList<T> list = copy();
        list.sort(null);
        return list;
    }

    public AnimationList<T> shuffle() {
        Collections.shuffle(this);
        return this;
    }

    public AnimationList<T> shuffleCopy() {
        AnimationList<T> list = copy();
        Collections.shuffle(list);
        return list;
    }

    public AnimationList<T> reverse() {
        Collections.reverse(this);
        return this;
    }

    public AnimationList<T> reverseCopy() {
        AnimationList<T> list = copy();
        Collections.reverse(list);
        return list;
    }

    public AnimationList<String> toStringList() {
        AnimationList<String> list = new AnimationList<>();
        for (T t : this) {
            list.add(t.toString());
        }
        return list;
    }

    public AnimationList<T> subList(int start, int end) {
        AnimationList<T> list = new AnimationList<>();
        for (int i = start; i < Math.min(size(), end); i++) {
            list.add(get(i));
        }
        return list;
    }

    public <R> AnimationList<R> convert(Function<T, R> converter) {
        AnimationList<R> list = new AnimationList<>();
        for(T i : this) {
            R r = converter.apply(i);
            if(r != null) {
                list.add(r);
            }
        }
        return list;
    }

}
