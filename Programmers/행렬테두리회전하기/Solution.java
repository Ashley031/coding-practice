package 행렬테두리회전하기;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] matrix = new int[rows][columns];

        // Init matrix
        int num = 1;
        for(int i=0 ; i<rows ; i++) {
            for(int j=0 ; j<columns ; j++) {
                matrix[i][j] = num++;
            }
        }

        // Rotate
        for(int i=0 ; i<queries.length ; i++) {

            int r1 = queries[i][0]-1;
            int c1 = queries[i][1]-1;
            int r2 = queries[i][2]-1;
            int c2 = queries[i][3]-1;
            int tmp = matrix[r1][c1];
            int min = tmp;

            // UP
            for(int r = r1+1 ; r<=r2 ; r++){
                min = Math.min(min, matrix[r][c1]);
                matrix[r-1][c1] = matrix[r][c1];
            }

            // LEFT
            for(int c = c1+1 ; c<=c2 ; c++){
                min = Math.min(min, matrix[r2][c]);
                matrix[r2][c-1] = matrix[r2][c];
            }

            // DOWN
            for(int r = r2-1 ; r>=r1 ; r--){
                min = Math.min(min, matrix[r][c2]);
                matrix[r+1][c2] = matrix[r][c2];
            }

            // RIGHT
            for(int c = c2-1 ; c>c1 ; c--){
                min = Math.min(min, matrix[r1][c]);
                matrix[r1][c+1] = matrix[r1][c];
            }

            matrix[r1][c1+1] = tmp;
            min = Math.min(min, tmp);

            // 정답 배열에 최솟값 넣기
            answer[i] = min;
        }

        return answer;
    }
}
