
@FunctionalInterface
public interface Addition {

	public void add();

	public static void hi() {
		System.out.println("HI");
	}

	public static void helo() {
		System.out.println("helo");
	}

	default void print() {
		System.out.println("print");
	}
}
