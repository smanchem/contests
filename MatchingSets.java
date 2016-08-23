/**
 * Created by sandeepmanchem on 8/22/16.
 * https://www.hackerrank.com/contests/w22/challenges/box-moving
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MatchingSets {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long x[] = new long[n];
        long y[] = new long[n];
        long sum_x = 0, sum_y = 0;
        for(int i=0; i < n; i++){
            x[i] = in.nextInt();
            sum_x = sum_x + x[i];
        }
        for(int i=0; i < n; i++){
            y[i] = in.nextInt();
            sum_y = sum_y + y[i];
        }

        if(sum_x != sum_y){
            System.out.println("-1");
            return;
        }

        Arrays.sort(x);
        Arrays.sort(y);

        int j;
        long ops = 0;
        int i = 0;
        for(i = 0; i < n; i++){
            if(x[i] == y[i]){
                continue;
            } else{
                if(x[i] < y[i]){
                    j = n-1;
                    while(x[i] != y[i] && j > i){
                        if(x[j] > y[j]){
                            if((x[j]-y[j]) >= (y[i]-x[i])){
                                x[j] = x[j] - (y[i]-x[i]);
                                x[i] = y[i];
                                ops = ops + Math.abs(y[i] - x[i]);
                            } else{
                                ops = ops + Math.abs(x[j] - y[j]);
                                x[i] = x[i] + x[j] - y[j];
                                x[j] = y[j];
                                j--;
                            }
                        } else {
                            j--;
                        }
                    }
                } else {
                    j = i + 1;
                    while(x[i] != y[i] && j < n){
                        if(x[j] >= y[j]){
                            j++;
                        } else{
                            if((y[j]-x[j]) >= (x[i]-y[i])){
                                x[j] = x[j] + (x[i]-y[i]);
                                ops = ops + Math.abs(x[i] - y[i]);
                                x[i] = y[i];
                            } else{
                                x[i] = x[i] + x[j] - y[j];
                                ops = ops + Math.abs(y[j] - x[j]);
                                x[j] = y[j];
                                j++;
                            }
                        }
                    }
                }
            }
        }

        for(i = 0; i < n; i++){
            if(x[i] != y[i]){
                System.out.println("Not Matching at: " + i);
            }
        }
        System.out.println(ops);
    }
}
