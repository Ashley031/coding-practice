package boj._2078;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int A, B;
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int left = 0, right = 0; // count

        while(true){
            if(A == 1){
                right += B - 1;
                break;
            } else if(B == 1){
                left += A - 1;
                break;
            }

            if(A > B) {
                left += A / B;
                A %= B;
            }
            else {
                right += B / A;
                B %= A;
            }
        }

        System.out.println(left + " " + right);
        br.close();
    }

}
