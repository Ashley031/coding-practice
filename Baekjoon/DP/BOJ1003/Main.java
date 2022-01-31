package BOJ1003;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
* Bottom-up 방식으로 풀이
*
* 구하려는 답: N까지의 피보나치 수열 중 0과 1이 나타나는 횟수
* 변수의 개수: 2
* 수식: F(N) = F(N-1) + F(N-2) => 0과 1의 변수마다 각각 실행
*
* 자료 만들 때 갤노트 메모 참고
* */

class AppearNum {
    int zero;
    int one;

    AppearNum(int z, int o) {
        this.zero = z;
        this.one = o;
    }
}

public class Main {

    static int T, N;
    static AppearNum[] cache;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i=0 ; i<T ; i++){
            N = Integer.parseInt(br.readLine());
            AppearNum tmp = dp(N);
            System.out.println(tmp.zero + " " + tmp.one);
        }
    }

    static AppearNum dp(int num) {
        if(num == 0) return new AppearNum(1,0);
        else if(num == 1) return new AppearNum(0,1);

        int zero = 0, one = 0;
        cache = new AppearNum[num+1];

        // N = 0과 N = 1인 경우는 미리 저장 -> 가장 작은 두 단위
        cache[0] = new AppearNum(1, 0);    // N = 0
        cache[1] = new AppearNum(0, 1);    // N = 1

        for(int i=2 ; i<num+1 ; i++){
            cache[i] = new AppearNum(0, 0);
            cache[i].zero = cache[i-1].zero + cache[i-2].zero;
            cache[i].one = cache[i-1].one + cache[i-2].one;

            /*TEST*/
//            System.out.println("cache["+ i + "].zero = " + cache[i].zero + ", one = " + cache[i].one);
        }

        return cache[num];

    }
}
