package BOJ9012;
import java.util.Scanner;
import java.util.Stack;

/*
 * Stack Ȱ�� - VPS(��ȣ���ڿ�)
 * Stack Ŭ���� ���
 * ���� 9012�� Ǯ��
 * 
 * Coded by NANAEU	(2021.07.16)
 */

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		for(int i=0 ; i<n ; i++)
			System.out.println(solution(scan.next()));
		
	}
	
	public static String solution(String s) {
		
		Stack<Character> stack = new Stack<>();
			
		for(int i=0 ; i<s.length() ; i++) {
			char c = s.charAt(i);
			if(c == '(') {
				stack.push('(');
			}
			else if(c == ')') {
				if(stack.isEmpty()) return "NO";
				else stack.pop();
			}
		}
		
		if(stack.isEmpty()) return "YES";
		else return "NO";
		
	}
}
