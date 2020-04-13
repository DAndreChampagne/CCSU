package cs506.HW4;

public class mystery {

	public static int mystery(int max) {
		int a = 0;
		while (a < 10) {
			for (int i=0; i<=max; i++) {
				if (i%2 == 0)
					a = a + i;
			}
		}
		
		return a;
	}
	
	public static void main(String[] args) {

		for (int i=2; i<=10; ++i) {
			System.out.println(String.format("mystery(%d) = %d", i, mystery(i)));
		}

	}

}
