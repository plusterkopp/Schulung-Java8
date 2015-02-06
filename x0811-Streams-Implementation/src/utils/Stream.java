package utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Stream<T> {
    Stream<T> filter(Predicate<? super T> predicate);
    <R> Stream<R> map(Function<? super T, ? extends R> mapper);
    void forEach(Consumer<? super T> action);
}
