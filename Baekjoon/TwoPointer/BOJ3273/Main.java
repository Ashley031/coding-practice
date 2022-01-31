package Baekjoon.TwoPointer.BOJ3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Two Pointer
 * ���� 3273�� Ǯ��
 * 
 * Coded by NANAEU	(2021.07.22)
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int start = 0, end = 1, cnt = 0;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		for(int i=0 ; i<n ; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int x = Integer.parseInt(br.readLine());
		
		while(true) {
			
			if(arr[start]+arr[end] == x) cnt++;
			
			if(start == n-2) break;
			
			if(end < n-1) {
				end++;
			}
			else if(end == n-1){
				start++;
				end = start+1;
			}
		}

		
		System.out.println(cnt);
	}

}
