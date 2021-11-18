package BOJ10989;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        int[] counting = new int[10001];
        int[] result = new int[N];

        for(int i=0 ; i<N ; i++){
            num[i] = Integer.parseInt(br.readLine());
            counting[num[i]]++;
        }

        for(int i=0 ; i<10000 ; i++) {
            counting[i+1] += counting[i];
        }

        int idx = 0;
        for(int i=N-1 ; i>=0 ; i--){
            idx = counting[num[i]] - 1;
            result[idx] = num[i];
            counting[num[i]]--;
        }

        for(int i=0 ; i<N ; i++)
            bw.write(result[i] + "\n");

        br.close();
        bw.close();

    }
}
