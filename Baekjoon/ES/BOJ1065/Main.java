package Baekjoon.ES.BOJ1065;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 한자리수, 두자리수 - 무조건 한수
* 세자리수, 네자리수 - 차이가 일정해야함
*
* 브루트 포스
* */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N < 100) {
            // 한 자리 또는 두 자리 수 -> 무조건 한수
            System.out.println(N);
        }
        else {  // 세 자리 도는 네 자리 수
            int count = 99; // 한자리수, 두자리수 카운트해서 시작
            // N = a*10^2 + b*10 + c
            int a, b, c;

            for(int i = 100 ; i<=N ; i++){
                a = i / 100;
                b = (i - a*100) / 10;
                c = i % 10;
                if(b - c == a - b)
                    count++;
            }

            System.out.println(count);
        }

        br.close();

    }
}