package de.tototec.utils.functional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Optional<T> implements Iterable<T>, Serializable {

	public static <S> Optional<S> some(final S some) {
		return new Optional<S>(some);
	}

	public static <S> Optional<S> lift(final S someOrNull) {
		if (someOrNull == null) {
			return new Optional<S>();
		} else {
			return new Optional<S>(someOrNull);
		}
	}

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private static final Optional<?> NONE = new Optional();

	private final boolean isNone;
	private final T optional;

	private Optional() {
		this.isNone = true;
		this.optional = null;
	}

	private Optional(final T optional) {
		this.isNone = false;
		this.optional = optional;
	}

	@SuppressWarnings("unchecked")
	public static <N> Optional<N> none() {
		return (Optional<N>) NONE;
	}

	public T get() {
		if (isDefined()) {
			return optional;
		} else {
			throw new NullPointerException("Optional value not defined.");
		}
	}

	public T getOrElse(final F0<T> f) {
		if (isDefined()) {
			return optional;
		} else {
			return f.apply();
		}
	}

	public T orNull() {
		return isDefined() ? optional : null;
	}

	public <L> Either<L, T> toRight(final F0<L> left) {
		if (isDefined()) {
			return Either.right(get());
		} else {
			return Either.left(left.apply());
		}
	}

	public <R> Either<T, R> toLeft(final F0<R> right) {
		if (isDefined()) {
			return Either.left(get());
		} else {
			return Either.right(right.apply());
		}
	}

	public boolean isDefined() {
		return !isNone;
	}

	public boolean isEmpty() {
		return isNone;
	}

	public Optional<?> adapt() {
		return isDefined() ? Optional.some(get()) : Optional.none();
	}

	@SuppressWarnings("unchecked")
	public List<T> toList() {
		return isDefined() ? Arrays.<T> asList(optional) : Collections.<T> emptyList();
	}

	public Iterator<T> iterator() {
		return toList().iterator();
	}

	@SuppressWarnings("unchecked")
	public <R> Optional<R> map(final F1<? super T, ? extends R> f) {
		return (Optional<R>) (isEmpty() ? Optional.none() : Optional.some(f.apply(get())));
	}

	@SuppressWarnings("unchecked")
	public <R> Optional<R> flatMap(final F1<? super T, ? extends Optional<R>> f) {
		return (Optional<R>) (isEmpty() ? Optional.none() : f.apply(get()));
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + (isDefined() ? optional : "") + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isNone ? 1231 : 1237);
		result = prime * result + ((optional == null) ? 0 : optional.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Optional<?> other = (Optional<?>) obj;
		if (isNone != other.isNone) {
			return false;
		}
		if (optional == null) {
			if (other.optional != null) {
				return false;
			}
		} else if (!optional.equals(other.optional)) {
			return false;
		}
		return true;
	}

}
