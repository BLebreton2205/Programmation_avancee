package Eval;

import java.util.Scanner;

public class MainEval {
	public static void main(String[] args) throws Exception {
		boolean encore = true;
		while(encore) {
			calculate();
			System.out.println("Encore une ? (O/N)");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			if(s.equals("N")) {
				encore = !encore;
				System.out.println("Très bien, au revoir");
			}
		}
	}
	
	private static void calculate() throws Exception {
		int index;
		float x = 0;
		System.out.println("Entrer une fonction :");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		index = s.indexOf("x");
		if (index !=-1) {
			System.out.println("x = ");
			x = sc.nextFloat();
		}
		Evaluateur eval = new Evaluateur(s);
		
		System.out.println(eval.eval(x));
	}
}
