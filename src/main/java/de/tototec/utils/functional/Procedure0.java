package de.tototec.utils.functional;

public interface Procedure0 {
	public void apply();

	public static class AsRunnable implements Runnable {
		private final Procedure0 procedure;

		public AsRunnable(Procedure0 procedure) {
			this.procedure = procedure;
		}

		public void run() {
			procedure.apply();
		}
	}

	public static class FromRunnable implements Procedure0 {
		private final Runnable runnable;

		public FromRunnable(Runnable runnable) {
			this.runnable = runnable;
		}

		public void apply() {
			runnable.run();
		}
	}
}
