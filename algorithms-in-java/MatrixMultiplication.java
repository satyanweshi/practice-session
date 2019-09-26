import java.util.Arrays;

class MatrixMultiplication {

    private static void findOptimizedSolution(int[] p, int[][] m, int[][] s){
        int numMatrices = p.length - 1;

        for(int l = 2; l<= numMatrices; l++){
            for(int i=1; i<= numMatrices-l+1;i++){
                int j = i + l - 1;
                for(int k=i; k<j; k++){
                    int q = m[i-1][k-1] + m[k][j-1] + p[i-1]*p[k]*p[j];
                    if(m[i-1][j-1] == 0 || q < m[i-1][j-1]) {
                        m[i-1][j-1] = q;
                        s[i-1][j-1] = k-1;
                    }
                }
            }
        }
    }

    private static void printOptimizedSolution(int[][] s, int m, int n){
        if(m == n){
            System.out.print("A" + m);
        }
        else{
            System.out.print("(");
            printOptimizedSolution(s, m, s[m][n]);
            printOptimizedSolution(s, s[m][n]+1, n);
            System.out.print(")");
        }
    }
    
    public static void main(String args[]){
        int[] p = {100, 20, 30, 45, 67, 83, 32, 40, 21, 20};

        int[][] m = new int[p.length-1][p.length-1];
        int[][] s = new int[p.length-1][p.length-1];

        for(int i=0; i<m.length;i++){
            for(int j=0; j<m[i].length;j++){
                m[i][j] = 0;
            }
        }

        findOptimizedSolution(p, m, s);
        // for(int i = 0; i<s.length;i++){
        //     Arrays.stream(s[i]).forEach(k -> {System.out.print(k + ",");});
        //     System.out.println();
        // }
        // for(int i = 0; i<m.length;i++){
        //     Arrays.stream(m[i]).forEach(k -> {System.out.print(k + ",");});
        //     System.out.println();
        // }
        printOptimizedSolution(s, 0, s.length-2);
        System.out.println();
    }
}