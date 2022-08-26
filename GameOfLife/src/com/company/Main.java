package com.company;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Select size of x and y: ");
        int values = in.nextInt();
        new GameOfLife(values);

    }
}
