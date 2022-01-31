package BOJ9184;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int a,b,c;
    static int[][][] cache;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            String str = br.readLine();
            if(str.equals("-1 -1 -1")) break;
            st = new StringTokenizer(str);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            int tmp = dp(a, b, c);
            System.out.println("w(" + a + ", " + b + ", " + c + ") = " + tmp);
        }

        br.close();
    }

    static int dp(int a, int b, int c){
        if(a <= 0 || b <= 0 || c <= 0) return 1;
        if(a > 20 || b > 20 || c > 20) return dp(20, 20, 20);

        int answer = 0;

        // a,b,c 중 하나라도 0 이하면 1 리턴 -> 여기서 계산할 a,b,c는 양수
        // a,b,c 중 하나라도 20보다 크다면 a=b=c=20 -> a,b,c는 최대 20까지
        cache = new int[21][21][21];

        for(int i=0 ; i<21 ; i++){
            for(int j=0 ; j<21 ; j++) {
                cache[0][i][j] = 1;
                cache[i][0][j] = 1;
                cache[i][j][0] = 1;
            }
        }

        for(int i=1 ; i<=a ; i++){
            for(int j=1 ; j<=b ; j++){
                for(int k=1 ; k<=c  ;k++){
                    int tmpI, tmpJ, tmpK;
                    if(i-1<=0) tmpI = 0; else tmpI = i-1;
                    if(j-1<=0) tmpJ = 0; else tmpJ = j-1;
                    if(k-1<=0) tmpK = 0; else tmpK = k-1;

                    if(i<j && j<k)
                        cache[i][j][k] = cache[i][j][tmpK] + cache[i][tmpJ][tmpK] - cache[i][tmpJ][k];
                    else
                        cache[i][j][k] = cache[tmpI][j][k] + cache[tmpI][tmpJ][k] + cache[tmpI][j][tmpK] - cache[tmpI][tmpJ][tmpK];

                    if(i == a && j == b && k == c) {
                        return cache[i][j][k];
                    }
                }
            }
        }
        return answer;
    }
}
