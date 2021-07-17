package boj._18258;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;

/*
 * Queue 기본 구현
 * 백준 18258번 풀이
 * 참고 : https://st-lab.tistory.com/186
 * LinkedList 방식
 * 
 * Coded by NANAEU	(2021.07.15)
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Deque<Integer> queue = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			switch(st.nextToken()) {
			case "push":
				queue.offer(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if(queue.size() == 0)
					sb.append(-1).append('\n');
				else
					sb.append(queue.poll()).append('\n');
				break;
			case "size":
				sb.append(queue.size()).append('\n');
				break;
			case "empty":
				if(queue.isEmpty())
					sb.append(1).append('\n');
				else
					sb.append(0).append('\n');
				break;
			case "front":
				if(queue.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(queue.peekFirst()).append('\n');
				break;
			case "back":
				if(queue.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(queue.peekLast()).append('\n');
				break;
				
			}
		}
		System.out.println(sb);
	}
	
}