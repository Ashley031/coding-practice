package boj._5464;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;

/*
 * Queue 활용
 * 백준 5464번 풀이
 * 
 * 대기차량 - 큐
 * 주차장의 주차여부 - int 배열[N]
 * 주차료 = 차량무게 * 주차공간별 단위무게당 요금
 * 총 M대의 차량이 정해진 순서에 따라 주차장 이용 예정 (1번 ~ M번)
 * 
 * 주어지는 조건 : 주차공간별 요금, 차량들의 무게, 출입순서
 * 결과 : 오늘 총 수입액
 * 
 * 
 * Coded by NANAEU	(2021.07.15)
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int sum = 0; // 총 수입
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 주차장 - 주차된 차량번호 저장 예정
		int[] parked = new int[N]; 
		for(int i=0 ; i<N ; i++)
			parked[i] = 0;
		
		// 주차 공간들의 단위무게당 요금
		int[] cost = new int[N];
		for(int i=0 ; i<N ; i++) {
			str = br.readLine();
			cost[i] = Integer.parseInt(str);
		}
		
		// 차량들의 무게
		int[] weight = new int[M];
		for(int i=0 ; i<M ; i++) {
			str = br.readLine();
			weight[i] = Integer.parseInt(str);
		}
		
		Deque<Integer> q = new LinkedList<>();			// 주차장 출입순서
		Deque<Integer> waiting = new LinkedList<>();	// 대기차량
		
		for(int i=0 ; i<2*M ; i++) {
			str = br.readLine();
			int value = Integer.parseInt(str);
			
			q.add(value);
		}
		
		int cnt = 0;	// 현재 주차된 개수
		
		while(!q.isEmpty()) {
			
			if(q.peekFirst() > 0) {
				
				if(cnt < N) {
					for(int i=0 ; i<N ; i++) {
						if(parked[i] == 0) {
							// 주차
							/* TEST */
//							System.out.println("<< 주차 >> : " + q.peekFirst());
							
							parked[i] = q.poll();
							break;
						}
					}
					cnt++;
				}
				else {
					// 주차장이 꽉 찼을 경우 대기 큐로 빼자
					waiting.add(q.poll());
				}
			}
			
			if(q.peekFirst() < 0) {
				
				/* TEST */
//				System.out.println(">> 주차해제 << : " + q.peekFirst());
				
				int num = q.peekFirst() * (-1);
				for(int i=0 ; i<N ; i++) {
					if(parked[i] == num) {
						
						// 주차장에서 빠져나감
						parked[i] = 0;
						
						// 주차요금 계산
						sum += weight[num-1] * cost[i];
						
						q.poll();
					}
				}
				
				cnt--;
				
				// 대기차량 주차
				if(!waiting.isEmpty()) {
					for(int i=0 ; i<N ; i++) {
						if(parked[i] == 0) {
							/* TEST */
//							System.out.println("<< 대기차량 주차 >> : " + waiting.peekFirst());
							parked[i] = waiting.poll();
							break;
						}
					}
					cnt++;
				}
			}
			
			/* TEST */
//			System.out.println("------TEST------");
//			Object[] remain = q.toArray();
//			for(int i=0 ; i< q.size() ; i++)
//				System.out.println(remain[i]);
			
		}
		
		
		System.out.println(sum);
		
	}
	
	
	
	

}
