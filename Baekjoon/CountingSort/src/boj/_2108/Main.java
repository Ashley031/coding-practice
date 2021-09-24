package boj._2108;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int avg, median = 10000, mode = 10000, sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int[] arr = new int[8001];

        int N = Integer.parseInt(br.readLine());

        // INSERTION
        for(int i=0 ; i<N ; i++){
            int num = Integer.parseInt(br.readLine());
            arr[num+4000]++;
            sum += num;

            if(max < num) max = num;
            if(min > num) min = num;
        }

        avg = (int) Math.round((double)sum / N);

        // COUNTING SORT
        int count = 0;
        int mode_max = 0;

        boolean first = false;   // 이전의 동일한 최빈값이 1번만 등장했을 경우 true, 아니면 false

        for(int i=min+4000 ; i<=max+4000 ; i++){
            if(arr[i] > 0){
                if(count < (N+1)/2){
                    count += arr[i];
                    median = i - 4000;
                }

                if(mode_max < arr[i]){
                    mode_max = arr[i];
                    mode = i - 4000;
                    first = true;
                }

                else if(mode_max == arr[i] && first == true){
                    mode = i - 4000;
                    first = false;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(avg).append('\n');
        sb.append(median).append('\n');
        sb.append(mode).append('\n');
        sb.append(max - min);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
