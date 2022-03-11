
public class MainMethod {

	public static void main(String[] args) {

		Addition ad = () -> {
			System.out.println("hi");
		};
		ad.add();

		Addition.helo();
		Addition.hi();
		ad.print();

		Addition2 ad2 = (x, y, z) -> {
			int d = x + y;
			int e = z * d;
			System.out.println(e);
		};
		ad2.add(5, 5, 5);
		
		
		Addition3 ad3 = (a,b,c) -> {
			int d = a+b;
			int e = d*c;
			return e;
		};
		int result = ad3.add(5, 5, 5);
		System.out.println(result);
	}
}
