package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("add gap");
        String xx = in.next();
        try {
            if (Integer.valueOf(xx) <= 0) {
                throw new IllegalArgumentException("number>0");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("enter number");
            xx=in.next();
        }
        System.out.println("to");
        String yy = in.next();
        try {
            if (Integer.valueOf(yy) <= 0) {
                throw new IllegalArgumentException("last number>0");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("enter number");
            yy=in.next();
        }
        in.close();
        int x = Integer.valueOf(xx);
        int y = Integer.valueOf(yy);
        for (int i=x; i <= y; i++)
        {
            if (i == 0)
            {
                i++;
            }
            int k=0;
            for (int ii=1; ii <= i; ii++)
            {
                if (i % ii == 0) k++;
            }
            if (k == 2)
            {
                System.out.println(i);
            }
        }
    }
}