

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String str= "Dhruv";
        System.out.println(str);
        System.out.println(reverse(str));

        char  grid [][] = {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}};
        char  grid2 [][] = {{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}};
        System.out.println(numIslands(grid));
        System.out.println(numIslands(grid2));
    }


    public static String reverse(String str){
        String temp="";
        for(int i=str.length()-1;i>=0;i-- ){
            temp+=str.charAt(i);
        }

        return temp;
    }



    public static int numIslands(char[][] grid) {
        return num_helper_2(grid,0,0,0);
    }


    public  static int num_helper(char[][]grid, int row, int col, int num_found){


        if((row==3)&&(col==4))
            return num_found+grid[3][4];//base case return statement

        if(grid[row][col]==1){
            // we found land now we get rid of the land if it is surrounded with land

            if((row!=0)){
                if(grid[row-1][col]==1)
                    grid[row][col]=0;


            }
            if((col!=0)){
                if(grid[row][col-1]==1)
                    grid[row][col]=0;



            }
            if((row!=3)){
                if(grid[row+1][col]==1)
                    grid[row][col]=0;


            }
            if((col!=4)){
                if(grid[row][col+1]==1)
                    grid[row][col]=0;


            }
            //if there was a neighbour the land has been converted to water
            //if still land, it is an island

            if(grid[row][col]==1){
                grid[row][col]=0;
                num_found++;

            }

        }

        //now we need to check which rows and col num to pass back

        col=col+1;

        if(col==5){
            col=0;
            row=row+1;

        }
        return num_helper(grid, row, col, num_found);
        }





    public  static int num_helper_2(char[][]grid, int row, int col, int num_found){


        if((row==3)&&(col==4)){
            if(grid[3][4]==1)
                return num_found+1;

            return num_found;//base case return statement
        }


        if(grid[row][col]!=0){
            // we found land now we get rid of the land and mark all adjacent land as visited
            if(grid[row][col]==1)
            num_found++;

            grid[row][col]=0;

            if((row!=0)){
                if(grid[row-1][col]==1)
                    grid[row-1][col]=2;


            }
            if((col!=0)){
                if(grid[row][col-1]==1)
                    grid[row][col-1]=2;



            }
            if((row!=3)){
                if(grid[row+1][col]==1)
                    grid[row+1][col]=2;


            }
            if((col!=4)){
                if(grid[row][col+1]==1)
                    grid[row][col+1]=2;


            }


        }

        //now we need to check which rows and col num to pass back

        col=col+1;

        if(col==5){
            col=0;
            row=row+1;

        }
        return num_helper_2(grid, row, col, num_found);
    }

}





