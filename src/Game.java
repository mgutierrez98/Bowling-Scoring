
//Author: Manuel Gutierrez

import java.util.*;
public class Game
{
    private static final Scanner keyboard = new Scanner(System.in);
    private static final int[][] allFrame = new int[2][12];
    private static final int[] frame = new int[10];

    public static void main(String[] args) {
        int round = 0,
                pins1,
                pins2,
                pins3,
                pins4,
                pins5 = 0;

        //initialize score array
        for(int i = 0; i < 12; i++) {
            allFrame[0][i] = allFrame[1][i] = 0;
        }

        //begin game
        for(int i = 0; i < 10; i++) {
            round += 1;

            System.out.println("Frame " + round + "\n");
            System.out.println("Ball 1: \n");
            pins1 = roll(keyboard.nextInt());

            //calls roll to make sure rolls are correct then saves score to 2d array
            if(roll(pins1) == 10) {
                System.out.println("Strike! \n");
                allFrame[0][i] = pins1;
            } else {
                allFrame[0][i] = pins1;
                System.out.println("Ball 2: \n");
                pins2 = roll(keyboard.nextInt());
                if(pins1 + pins2 == 10) {
                    System.out.println("Spare! \n");
                }
                allFrame[1][i] = pins2;
            }
        }

        //if 10th frame is strike or spare let player roll again
        if(allFrame[0][9] == 10) {
            System.out.println("Extra Ball 1: \n");
            pins3 = roll(keyboard.nextInt());
            allFrame[0][10] = pins3;

            if(allFrame[0][10] == 10) {
                System.out.println("Extra Ball 2: \n");
                pins4 = roll(keyboard.nextInt());
                allFrame[0][11] = pins4;
            } else {
                System.out.println("Extra Ball 2: \n");
                pins4 = roll(keyboard.nextInt());
                allFrame[1][10] = pins4;
            }
        } else if(allFrame[0][9] + allFrame[1][9] == 10) {
            System.out.println("Extra Ball 1: \n");
            pins3 = roll(keyboard.nextInt());
            allFrame[0][10] = pins3;
        }

        //System.out.println(Arrays.deepToString(allFrame));

        //prints score board
        System.out.println(score());
    }

    public static int roll(int pins) {
        int pins1 = pins;
        int total = 0;
        //determines if number is valid for pins
        if(pins1 > 10 || pins1 < 0) {
            System.out.println("Number of pins entered incorrectly!");
            System.out.println("Ball 1: \n");

            pins1 = roll(keyboard.nextInt());

            roll(pins1);
        }
        return pins1;
    }

    public static String score() {
        int score;
        String board;
        StringBuilder row1 = new StringBuilder();
        StringBuilder row2 = new StringBuilder();
        StringBuilder row3 = new StringBuilder();
        StringBuilder row4 = new StringBuilder();

        //scoring for beginning frame
        if(allFrame[0][0] == 10) {
            score = allFrame[0][0] + allFrame[0][1] + allFrame[1][1];
            if(allFrame[1][1] == 0) {
                score = score + allFrame[0][2];
            }
        } else if(allFrame[0][0] + allFrame[1][0] == 10){
            score = allFrame[0][0] + allFrame[1][0] + allFrame[0][1];
        } else {
            score = allFrame[0][0] + allFrame[1][0];
        }
        frame[0] = score;

        //scoring for middle frames
        for(int i = 1; i < 10; i++){

            if(allFrame[0][i] == 10) {
                score = allFrame[0][i] + allFrame[0][i+1] + allFrame[1][i];
                if(allFrame[1][i] == 0) {
                    score = score + allFrame[0][i+1];
                }
                frame[i] = score;
            } else if(allFrame[0][i] + allFrame[1][i] == 10) {
                score = allFrame[0][i] + allFrame[1][i] + allFrame[0][i+1];
            } else {
                score = allFrame[0][i] + allFrame[1][i];
            }

            frame[i] = frame[i-1] + score;
        }

        //scoring for end/extra frames
        if(allFrame[0][9] == 10){
            if(allFrame[1][10] == 0) {
                score = allFrame[0][9] + allFrame[0][10] + allFrame[0][11];
            } else{
                score = allFrame[0][9] + allFrame[0][10]+ allFrame[1][10];
            }
        }else if(allFrame[0][9] + allFrame[1][9] == 10) {
            score = allFrame[0][9] + allFrame[1][9] + allFrame[0][10];
        } else {
            score = allFrame[0][9] + allFrame[1][9];
        }

        frame[9] = frame[8] + score;

        //title for frame row
        row1.append("Frames:   ");

        //prints title for each frame
        for(int i = 0; i < 10; i++) {
            if(i != 9) {
                row1.append("F").append(i + 1).append("   ");
            } else {
                row1.append("F").append(i + 1);
            }
        }

        //print extra frames for row 1
        if(allFrame[0][11] != 0) {
            row1.append("   ").append("Xtra1").append("   ").append("Xtra2");
        } else {
            row1.append("   ").append("Xtra1");
        }

        //title for ball 1 row
        row2.append("Ball 1:   ");

        //print ball 1 for each frame
        for(int i = 0; i < 10; i++) {
            if(i !=9) {
                if(allFrame[0][i] < 10) {
                    row2.append(allFrame[0][i]).append("    ");
                } else {
                    row2.append(allFrame[0][i]).append("   ");
                }
            } else {
                row2.append(allFrame[0][i]).append("   ");
            }
        }

        //print ball 1 for extra frames
        if(allFrame[0][11] != 0) {
            if(allFrame[0][10] < 10) {
                if(allFrame[0][11] < 10) {
                    row2.append("   ").append(allFrame[0][10]).append("      ").append(allFrame[0][11]);
                }else {
                    row2.append("   ").append(allFrame[0][10]).append("     ").append(allFrame[0][11]);
                }
            }else {
                row2.append(" ").append(allFrame[0][10]).append("      ").append(allFrame[0][11]);
            }
        } else {
            row2.append("  ").append(allFrame[0][10]);
        }

        //title for ball 2 row
        row3.append("Ball 2:   ");

        //print ball 2 for each frame
        for(int i = 0; i < 10; i++) {
            if(i !=9) {
                if(allFrame[1][i] < 10) {
                    row3.append(allFrame[1][i]).append("    ");
                }else {
                    row3.append(allFrame[1][i]).append("   ");
                }
            }else {
                row3.append(allFrame[1][i]);
            }
        }

        //print ball 2 for extra frames
        if(allFrame[0][11] != 0) {
            row3.append("     ").append(allFrame[1][10]);
        }

        //title for score
        row4.append("Score :   ");

        //print score for each frame
        for(int i = 0; i < 10; i++) {
            String num = Integer.toString(frame[i]);
            if(i != 9) {
                if(num.length() < 2) {
                    row4.append(frame[i]).append("    ");
                } else if(num.length() < 3) {
                    row4.append(frame[i]).append("   ");
                } else {
                    row4.append(frame[i]).append("  ");
                }
            }else {
                row4.append(frame[i]);
            }
        }

        //stores rows for return statement
        board = row1 + "\n" +
                row2 + "\n" +
                row3 + "\n" +
                row4 + "\n";

        //returns score board
        return board;
    }
}
