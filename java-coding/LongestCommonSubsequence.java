
import java.util.Arrays;

class LongestCommonSubsequence {

    private static enum DIRECTIONS {
        UP, 
        LEFT, 
        UPANDLEFT;
    };

    private static void computeLongestSequence(char[] x, char[] y, int[][] c, Enum[][] b){
        for(int i = 0; i <= x.length; i++){
            c[i][0] = 0;
        }
        for(int j = 0; j <= y.length; j++){
            c[0][j] = 0;
        }

        for(int i = 1; i <= x.length; i++){
           for(int j = 1; j <= y.length; j++){
                if(x[i-1] == y[j-1]){
                    System.out.println(x[i-1] + "UPANDLEFT");
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = DIRECTIONS.UPANDLEFT;
                }
                else{
                    if(c[i-1][j] > c[i][j-1]){
                        System.out.println("UP");
                        c[i][j] = c[i-1][j];
                        b[i][j] = DIRECTIONS.UP;
                    }
                    else{
                        System.out.println("LEFT");
                        c[i][j] = c[i][j-1];
                        b[i][j] = DIRECTIONS.LEFT;
                    }
                }
            }
        }
    }

    private static void printLongestCommonSubsequence(char[] x, Enum[][] b, int i, int j){
        if(i == 0 || j == 0) return;
        if(b[i][j] == DIRECTIONS.UPANDLEFT){
            printLongestCommonSubsequence(x, b, i-1, j-1);
            System.out.print(x[i-1] + ", ");
        }
        else if(b[i][j] == DIRECTIONS.UP){
            printLongestCommonSubsequence(x, b, i-1, j);
        }
        else {
            printLongestCommonSubsequence(x, b, i, j-1);
        }
    }

    public static void main(String args[]){
        char[] x = {'a', 'c', 'd', 'f', 'j', 'k', 'p', 'z', 'f', 'l', 'a', 'i'};
        char[] y = {'b', 'd', 'f', 'k', 'm', 'a', 'z', 'f', 'i', 'm', 'n', 'p'};

        int[][] c = new int[x.length + 1][y.length + 1];
        Enum[][] b = new Enum[x.length + 1][y.length + 1];

        computeLongestSequence(x, y, c, b);
        for(int i = 0; i < c.length; i++){
            Arrays.stream(c[i]).forEach(k -> {System.out.print(k + ", ");});
            System.out.println();
        }
        for(int i=0; i< b.length; i++){
            Arrays.stream(b[i]).forEach(k -> {System.out.print(k + ", ");});
            System.out.println();
        }
        printLongestCommonSubsequence(x, b, x.length, y.length);
        System.out.println();
    }

}