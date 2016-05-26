package sortlist;

public class TestSortList {

	public static void main(String[] args) {

		SortList s = new SortList(10);
		s.insert(5);
		s.insert(5);
		s.insert(2);
		s.insert(7);
		System.out.println(s.sequentSearch(2));
		System.out.println(s.binarySearch(5));
	}

}
