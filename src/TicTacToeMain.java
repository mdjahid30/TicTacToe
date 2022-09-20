import java.lang.reflect.Array;
import java.util.*;

public class TicTacToeMain {
    static ArrayList<Integer> userPos = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPos = new ArrayList<Integer>();

    public static void main(String [] args) {
        System.out.println(">>>>>Tic Tac Toe Game<<<<<");

        // This represent the board
        char [][] board= {{'1','|','2','|','3'},
            {'-','+','-','+','-'},
            {'4','|','5','|','6'},
            {'-','+','-','+','-'},
            {'7','|','8','|','9'}};
        //This prints the board
        printBoard(board);
        Scanner sc = new Scanner(System.in);
        int posUser;
        int posCpu;
        boolean z = true;
        String result ="";
        while(true) {
            System.out.println("Enter your mark(1-9):");
            posUser = sc.nextInt();
            while(userPos.contains(posUser) || cpuPos.contains(posUser)){
                System.out.println("Position occupied. Enter your position again");
                posUser = sc.nextInt();
            }
            if (posUser >= 1 && posUser <= 9) {
                z = false;
                placeMove(board, posUser, "User");
                userPos.add(posUser);
                printBoard(board);
                result = checkResult();
            }
            if(posUser < 1 || posUser > 9) {
                System.out.println("Out of board!!!!");
                System.out.print("Again ");
                //System.out.println("Again Enter Within range(1-9):");
                z=true;
            }
            if(!z) {
                Random rand = new Random();
                posCpu = 1 + rand.nextInt(9);
                while(userPos.contains(posCpu) || cpuPos.contains(posCpu)){
                    posCpu = 1 + rand.nextInt(9);
                }
                placeMove(board, posCpu, "CPU");
                cpuPos.add(posCpu);
                System.out.println("Cpu's turn:");
                System.out.println(posCpu);
                printBoard(board);
                result = checkResult();
            }
            System.out.println(result);
            if(result.length()>0){
                System.out.println("Press \"X\" to Exit.");
                System.out.println("Press \"P\" to Play Again.");
                char ch = sc.next().charAt(0);
                char choice = Character.toUpperCase(ch);
                if(choice == 'X') {
                    System.out.println("Thanks for playing!");
                    break;
                }
                else if(choice == 'P')
                    System.out.println("New Game");
                    restart();
            }
        }

    }//end of main

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // For printing board
    public static void printBoard(char[][] gb){
        for (char[] row: gb) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }//End of printBoard

    //For placing move in the board
    public static void placeMove(char [][] gb,int pos, String player){
        char sign = 'D';
        if(player.equals("User")) sign = 'X';
        else if(player.equals("CPU")) sign = 'O';

        switch (pos) {
            case 1 -> gb[0][0] = sign;
            case 2 -> gb[0][2] = sign;
            case 3 -> gb[0][4] = sign;
            case 4 -> gb[2][0] = sign;
            case 5 -> gb[2][2] = sign;
            case 6 -> gb[2][4] = sign;
            case 7 -> gb[4][0] = sign;
            case 8 -> gb[4][2] = sign;
            case 9 -> gb[4][4] = sign;
            default -> gb[0][1] = '|';
        }
    }//End of placeMove

    public static String checkResult(){
        List<Integer> tr = Arrays.asList(1,2,3);
        List<Integer> mr = Arrays.asList(4,5,6);
        List<Integer> br = Arrays.asList(7,8,9);
        List<Integer> lc = Arrays.asList(1,4,7);
        List<Integer> mc = Arrays.asList(2,5,8);
        List<Integer> rc = Arrays.asList(3,6,9);
        List<Integer> d1 = Arrays.asList(1,5,9);
        List<Integer> d2 = Arrays.asList(3,5,7);

        List<List> condition = new ArrayList<List>();
        condition.add(tr);
        condition.add(mr);
        condition.add(br);
        condition.add(lc);
        condition.add(mc);
        condition.add(rc);
        condition.add(d1);
        condition.add(d2);

        for (List l:condition){
            if(userPos.containsAll(l)) return "Congratulations! You Won :)";
            else if (cpuPos.containsAll(l)) return "Sorry! You Lose :(";
            else if (userPos.size()+cpuPos.size()==9) return "Its a tie";
        }
        return "";
    }//End of check result

    public static void restart(){
        ListIterator<Integer> iterateUser = userPos.listIterator();
        ListIterator<Integer> iterateCpu = cpuPos.listIterator();
        while(iterateUser.hasNext()){
            userPos.remove(0);
        }
        while(iterateCpu.hasNext()){
            cpuPos.remove(0);
        }
        //System.out.println("Checking restart user: "+userPos);
        //System.out.println("Checking restart cpu: "+cpuPos);
    }
}//End of class

