package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.lang.Thread.sleep;

public class GameOfLife extends JFrame {

    int xSize, ySize;
    int[][] playingField;
    int generation = 0;
    int rule1_a = 2, rule1_b = 3;
    int rule2 = 3;

    boolean isStopped = true;
    boolean restarted = true;

    public GameOfLife(int length) throws InterruptedException {
        this.xSize = length;
        this.ySize = length;

        playingField = new int[xSize][ySize];

        setTitle("Game of Life");
        setSize(200+20*xSize, 300+20*ySize);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Random r = new Random();
        addButtons();
        while(true){

            //Fill with random cells
            if(restarted == true){
                for(int y = 0; y < playingField.length; y++){
                    for(int x = 0; x < playingField[0].length; x++){
                        playingField[y][x] = r.nextInt(2);
                    }
                }
                generation = 0;
            }
            restarted = false;




            //draw on canvas and put

            if(!isStopped){
                for(int y = 0; y < playingField.length; y++){
                    for(int x = 0; x < playingField[0].length; x++){
                        applyRules(x, y);
                    }
                }
                generation++;
                repaint();
                sleep(150);
            } else {
                repaint();
                sleep(150);
            }
        }
    }

    public void paint(Graphics g){
        for(int y = 0; y < playingField.length; y++){
            for(int x = 0; x < playingField[0].length; x++){
                g.setColor(Color.black);
                g.drawString(String.valueOf(x+1), 105+20*x, 95);
                if(playingField[y][x] == 0){
                    g.setColor(Color.GRAY);
                    g.fillRect(100+20*x, 100+20*y, 20,  20);
                } else if(playingField[y][x] == 1) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(100+20*x, 100+20*y, 20,  20);
                }
            }
            g.setColor(Color.black);
            g.drawString(String.valueOf(y+1), 80, 115+20*y);
        }
        g.clearRect(0, 110+ySize*20, 1000, 1000);
        g.drawString("Generation: " + generation, 100, 120+ySize*20);

        g.drawString("Rule 1 a: " + rule1_a, 150, 150+ySize*20);
        g.drawString("Rule 1 b: " + rule1_b, 250, 150+ySize*20);
        g.drawString("Rule 2 value: " + rule2, 350, 150+ySize*20);
    }

    public void applyRules(int x, int y){
        int[] neighbours = new int[8];

        //"Edge" cells
        if(x == 0 || x == xSize-1 || y == 0 || y == ySize-1){
            if(x == 0 && y == 0){ //left up corner
                neighbours[0] = 0;
                neighbours[1] = 0;
                neighbours[2] = 0;

                neighbours[3] = 0;
                neighbours[4] = playingField[y][x+1];

                neighbours[5] = 0;
                neighbours[6] = playingField[y+1][x];
                neighbours[7] = playingField[y+1][x+1];
            } else if(x == xSize-1 && y == 0){ //right up corner
                neighbours[0] = 0;
                neighbours[1] = 0;
                neighbours[2] = 0;

                neighbours[3] = playingField[y][x-1];
                neighbours[4] = 0;

                neighbours[5] = playingField[y+1][x-1];
                neighbours[6] = playingField[y+1][x];
                neighbours[7] = 0;
            } else if(x == 0 && y == ySize-1){ //left low corner
                neighbours[0] = 0;
                neighbours[1] = playingField[y-1][x];
                neighbours[2] = playingField[y-1][x+1];

                neighbours[3] = 0;
                neighbours[4] = playingField[y][x+1];

                neighbours[5] = 0;
                neighbours[6] = 0;
                neighbours[7] = 0;
            } else if(x == xSize-1 && y == ySize-1){ //right low corner
                neighbours[0] = playingField[y-1][x-1];
                neighbours[1] = playingField[y-1][x];
                neighbours[2] = 0;

                neighbours[3] = playingField[y][x-1];
                neighbours[4] = 0;

                neighbours[5] = 0;
                neighbours[6] = 0;
                neighbours[7] = 0;
            } else if(y == 0 && (x != xSize-1 || x != 0)){
                neighbours[0] = 0;
                neighbours[1] = 0;
                neighbours[2] = 0;

                neighbours[3] = playingField[y][x-1];
                neighbours[4] = playingField[y][x+1];

                neighbours[5] = playingField[y+1][x-1];
                neighbours[6] = playingField[y+1][x];
                neighbours[7] = playingField[y+1][x+1];

            } else if(y == ySize-1 && (x != xSize-1 || x != 0)){
                neighbours[0] = playingField[y-1][x-1];
                neighbours[1] = playingField[y-1][x];
                neighbours[2] = playingField[y-1][x+1];

                neighbours[3] = playingField[y][x-1];
                neighbours[4] = playingField[y][x+1];

                neighbours[5] = 0;
                neighbours[6] = 0;
                neighbours[7] = 0;
            } else if(x == 0 && (y != 0 || y != ySize-1)){
                neighbours[0] = 0;
                neighbours[1] = playingField[y-1][x];
                neighbours[2] = playingField[y-1][x+1];

                neighbours[3] = 0;
                neighbours[4] = playingField[y][x+1];

                neighbours[5] = 0;
                neighbours[6] = playingField[y+1][x];
                neighbours[7] = playingField[y+1][x+1];

            } else if(x == xSize-1 && (y != 0 || y != ySize-1)){
                neighbours[0] = playingField[y-1][x-1];
                neighbours[1] = playingField[y-1][x];
                neighbours[2] = 0;

                neighbours[3] = playingField[y][x-1];
                neighbours[4] = 0;

                neighbours[5] = playingField[y+1][x-1];
                neighbours[6] = playingField[y+1][x];
                neighbours[7] = 0;


            }

            /*

             */
        } else { //non-edge cells
            neighbours[0] = playingField[y-1][x-1];
            neighbours[1] = playingField[y-1][x];
            neighbours[2] = playingField[y-1][x+1];

            neighbours[3] = playingField[y][x-1];
            neighbours[4] = playingField[y][x+1];

            neighbours[5] = playingField[y+1][x-1];
            neighbours[6] = playingField[y+1][x];
            neighbours[7] = playingField[y+1][x+1];
        }
        int aliveCells = 0;
        for (int i:
                neighbours) {
            aliveCells += i;
        }


        if(playingField[y][x] == 1 && (aliveCells == rule1_a || aliveCells == rule1_b)){
            playingField[y][x] = 1;
        } else if(playingField[y][x] == 0 && aliveCells == rule2){
            playingField[y][x] = 1;
        } else {
            playingField[y][x] = 0;
        }
    }

    public void addButtons(){
        setLayout(null);

        Button b1 = new Button("Start and Stop");
        b1.setBounds(50,20,100,30);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStopped){
                    isStopped = false;
                } else {
                    isStopped = true;
                }

            }
        });
        add(b1);

        Button restartButton = new Button("Restart");
        restartButton.setBounds(150,20,100,30);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restarted = true;

            }
        });
        add(restartButton);

        Button b2 = new Button("Increment");
        b2.setBounds(150,175+ySize*20,100,30);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStopped){
                    rule1_a++;
                }

            }
        });
        add(b2);


        Button b3 = new Button("Decrement");
        b3.setBounds(150,200+ySize*20,100,30);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStopped){
                    rule1_a--;
                }

            }
        });
        add(b3);

        Button b4 = new Button("Increment");
        b4.setBounds(250,175+ySize*20,100,30);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStopped){
                    rule1_b++;
                }

            }
        });
        add(b4);


        Button b5 = new Button("Decrement");
        b5.setBounds(250,200+ySize*20,100,30);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStopped){
                    rule1_b--;
                }

            }
        });
        add(b5);

        Button b6 = new Button("Increment");
        b6.setBounds(350,175+ySize*20,100,30);
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStopped){
                    rule2++;
                }

            }
        });
        add(b6);


        Button b7 = new Button("Decrement");
        b7.setBounds(350,200+ySize*20,100,30);
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStopped){
                    rule2--;
                }

            }
        });
        add(b7);
    }
}
