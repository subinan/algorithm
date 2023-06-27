package boj;

import java.util.Scanner;
public class BJ_1541_잃어버린괄호_edit {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		solve(expression);
		
		sc.close();
	}

	public static void solve(String expression) {
		int i = 0;
		while (i < expression.length()) {
			if (expression.charAt(i++) == '-') {
				expression = new StringBuilder(expression).insert(i++, "(").toString();
				while (i < expression.length() && expression.charAt(i) != '-') ++i;
				expression = new StringBuilder(expression).insert(i, ")").toString();
			}
		}
		calc(expression);
	}
	
	public static void calc(String expression) {
		int i = 0;
		while (i < expression.length()) {
			if (expression.charAt(i++) == '-') {
				expression = new StringBuilder(expression).insert(i++, "(").toString();
				while (i < expression.length() && expression.charAt(i) != '-') ++i;
				expression = new StringBuilder(expression).insert(i, ")").toString();
			}
		}
		calc(expression);
	}

}
