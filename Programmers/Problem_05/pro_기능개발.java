package Programmers.Problem_05;

import java.util.*;

public class pro_기능개발 {
	
	public static int[] solution(int[] progresses, int[] speeds) {
		Stack<Integer> day = new Stack<Integer>();
		
//		Queue<Integer> days = new LinkedList<>();
		int[] days = new int[progresses.length];	// 각 progress당 걸리는 시간
		List<Integer> result = new ArrayList<Integer>();
		
		/* day 배열 초기화  */
		for(int i=0 ; i<days.length ; i++)
			days[i] = 0;
		
		/* 걸리는 일수 계산 */
		int temp;
		for(int i=0 ; i<progresses.length ; i++) {
			temp = progresses[i];
			while(temp < 100) {
				temp += speeds[i];
				days[i]++;
			}
		}
		
		List<Integer> res = new ArrayList<Integer>();
		
		while(!day.isEmpty()) {
			int cnt = 1;
			
			int top = day.pop();
			
			while(!day.isEmpty() && day.peek() <= top) {
				cnt++;
				day.pop();
			}
			
			res.add(cnt);
		}
		
		int[] answer = new int[res.size()];
		
		for(int i=0 ; i<answer.length ; i++)
			answer[i] = res.get(i);
		
		return answer;
	}
	
	public static void main(String[] args) {
		
//		int[] progresses = {95, 90, 99 ,99 ,80, 99};
//		int[] speeds = {1,1,1,1,1,1};
		
//		int[] progresses = {93, 30, 55};
//		int[] speeds = {1,30,5};
		
		int[] progresses = {90, 30, 85, 45, 20};
		int[] speeds = {1,33,15, 40, 10};
		
		int[] answer = solution(progresses, speeds);
		for(int i=0 ; i<answer.length ; i++)
			System.out.println(answer[i]);
		
	}

}
