package Baekjoon.Queue.BOJ5464;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;

/*
 * Queue Ȱ��
 * ���� 5464�� Ǯ��
 * 
 * ������� - ť
 * �������� �������� - int �迭[N]
 * ������ = �������� * ���������� �������Դ� ���
 * �� M���� ������ ������ ������ ���� ������ �̿� ���� (1�� ~ M��)
 * 
 * �־����� ���� : ���������� ���, �������� ����, ���Լ���
 * ��� : ���� �� ���Ծ�
 * 
 * 
 * Coded by NANAEU	(2021.07.15)
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int sum = 0; // �� ����
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// ������ - ������ ������ȣ ���� ����
		int[] parked = new int[N]; 
		for(int i=0 ; i<N ; i++)
			parked[i] = 0;
		
		// ���� �������� �������Դ� ���
		int[] cost = new int[N];
		for(int i=0 ; i<N ; i++) {
			str = br.readLine();
			cost[i] = Integer.parseInt(str);
		}
		
		// �������� ����
		int[] weight = new int[M];
		for(int i=0 ; i<M ; i++) {
			str = br.readLine();
			weight[i] = Integer.parseInt(str);
		}
		
		Deque<Integer> q = new LinkedList<>();			// ������ ���Լ���
		Deque<Integer> waiting = new LinkedList<>();	// �������
		
		for(int i=0 ; i<2*M ; i++) {
			str = br.readLine();
			int value = Integer.parseInt(str);
			
			q.add(value);
		}
		
		int cnt = 0;	// ���� ������ ����
		
		while(!q.isEmpty()) {
			
			if(q.peekFirst() > 0) {
				
				if(cnt < N) {
					for(int i=0 ; i<N ; i++) {
						if(parked[i] == 0) {
							// ����
							/* TEST */
//							System.out.println("<< ���� >> : " + q.peekFirst());
							
							parked[i] = q.poll();
							break;
						}
					}
					cnt++;
				}
				else {
					// �������� �� á�� ��� ��� ť�� ����
					waiting.add(q.poll());
				}
			}
			
			if(q.peekFirst() < 0) {
				
				/* TEST */
//				System.out.println(">> �������� << : " + q.peekFirst());
				
				int num = q.peekFirst() * (-1);
				for(int i=0 ; i<N ; i++) {
					if(parked[i] == num) {
						
						// �����忡�� ��������
						parked[i] = 0;
						
						// ������� ���
						sum += weight[num-1] * cost[i];
						
						q.poll();
					}
				}
				
				cnt--;
				
				// ������� ����
				if(!waiting.isEmpty()) {
					for(int i=0 ; i<N ; i++) {
						if(parked[i] == 0) {
							/* TEST */
//							System.out.println("<< ������� ���� >> : " + waiting.peekFirst());
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
