import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Bankers_Algorithm{

    public static void main(String[] args) throws IOException {
    File file = new File("E://lab6//input.txt");
        
        
        Scanner sc = new Scanner(file);
        x = sc.nextInt();
        y = sc.nextInt();
        int max[][] = new int[x][y];



        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        int alloc[][] = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                alloc[i][j] = sc.nextInt();
            }
        }

        int avail[] = new int[y];
        for (int i = 0; i < y; i++) {
            avail[i] = sc.nextInt();
        }

        int need[][] = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        int[][] afterChange = new int[x][y];
        System.out.println("Need matrix :");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(need[i][j] + "  ");
            }
            System.out.println();
        }

        if (checkSafe(x, y, avail, alloc, need, afterChange)) {
            System.out.println("Safe sequence is :");
               for (int i = 0; i < x; i++) {
                System.out.print((char) ('A' + safe[i]) + " ");
            }


            
            System.out.println();
            System.out.println("Change in available resource matrix :");


            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    System.out.print(afterChange[i][j] + "  ");
                }
                System.out.println();
            }
        } 
        
        else {
            System.out.println("System is in unsafe state");
        }

    }



    static int x, y, safe[] = new int[x + 10];

    public static boolean checkSafe(int x, int y, int avail[], int alloc[][], int need[][], int afterChange[][]) {
        boolean safeFlag[] = new boolean[x];


        for (int i = 0; i < x; i++) {
            safeFlag[i] = false;
        }

        int check1 = 0;
        int check2 = 0;


        do {
         for (int i = 0; i < x; i++) {
           boolean flag = true;

            if (safeFlag[i] == false) {
             for (int j = 0; j < y; j++) {

                   if (avail[j] < need[i][j]) {
                            flag = false;
                        }
                    }
                    if (flag == true) {
                        
                        for (int j = 0; j < y; j++) {
                            avail[j] = avail[j] + alloc[i][j];
                            afterChange[check1][j] = avail[j];

                        }
                        safe[check1] = i;
                        check1++;
                        safeFlag[i] = true;
                    }
                }
            }
            check2++;


        }
        
        
        while (check1 < x && check2 < x);

        if (check1 > x) {
            return false;
        } else {
            return true;
        }
    }
}

