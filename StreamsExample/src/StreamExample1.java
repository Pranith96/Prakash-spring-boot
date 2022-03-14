import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample1 {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(1, 2,22,11,15,20, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> newList = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
		System.out.println(newList);
		List<Integer> newList1 = list.stream().map(x -> x * 5).collect(Collectors.toList());
		System.out.println(newList1);
		list.stream().map(x -> x * 5).forEach(y -> System.out.println(y));
		System.out.println(".................");
		list.stream().filter(x -> x % 2 == 0).map(y -> y * 5).forEach(z -> System.out.println(z));
		System.out.println(".................");

		long count = list.stream().count();
		System.out.println(count);
		System.out.println(".................");
		list.stream().sorted().forEach(x -> System.out.print(x+" "));
		System.out.println(".................");

		Stream<Integer> data = list.stream().map(x -> x * 5);
		System.out.println(data);
	}
}
