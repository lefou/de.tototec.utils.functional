package de.tototec.utils.functional;

import java.io.Serializable;

public class Tuple2<A, B> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static <A, B> Tuple2<A, B> of(final A a, final B b) {
		return new Tuple2<A, B>(a, b);
	}

	private final A a;
	private final B b;

	private Tuple2(final A a, final B b) {
		this.a = a;
		this.b = b;
	}

	public A a() {
		return a;
	}

	public B b() {
		return b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a() == null) ? 0 : a().hashCode());
		result = prime * result + ((b() == null) ? 0 : b().hashCode());
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
		final Tuple2<?, ?> other = (Tuple2<?, ?>) obj;
		if (a() == null) {
			if (other.a() != null) {
				return false;
			}
		} else if (!a().equals(other.a())) {
			return false;
		}
		if (b() == null) {
			if (other.b() != null) {
				return false;
			}
		} else if (!b().equals(other.b())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + a() + "," + b() + ")";
	}
}
