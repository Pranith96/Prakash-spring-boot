
public class MainMethod {

	public static void main(String[] args) {

		Addition ad = () -> {
			System.out.println("hi");
		};
		ad.add();
		
		Addition.helo();
		Addition.hi();
		ad.print();
	}
}
