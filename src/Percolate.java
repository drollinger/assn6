import java.util.Scanner;
public class Percolate {
    private int size;
    private char[][] soil;
    private UnionFind unionFind;
    public Percolate() {
        this(20);
    }
    public Percolate(int squareSize) {
        this.size = squareSize;
        soil = new char[this.size][this.size];
        for(int i=0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                soil[i][j] = '\u25A0';
            }
        }
    }

    private boolean canPercolate() {
        boolean thing = true;
        for(int i = 0; i < this.size; i++) {
            unionFind = new UnionFind(this.size*this.size);
            if(soil[0][i] == '\u25A2') {
                if(uniteAdjact(0, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean uniteAdjact(int row, int col) {
        int root1 = this.unionFind.find((row*this.size)+(col%this.size));
        int root2;
        //up
        if(row > 0 && soil[row - 1][col] == '\u25A2') {
            root2 = this.unionFind.find(((row-1)*this.size)+(col%this.size));
            if(root1 != root2) {
                this.unionFind.union(root1, root2);
                if(uniteAdjact(row - 1, col)) return true;
            }
        }
        //down
        if(row < this.size - 1 && soil[row + 1][col] == '\u25A2') {
            if(row + 1 == this.size - 1) {
                return true;
            }
            else {
                root2 = this.unionFind.find(((row + 1) * this.size) + (col % this.size));
                if (root1 != root2) {
                    this.unionFind.union(root1, root2);
                    if(uniteAdjact(row + 1, col)) return true;
                }
            }
        }
        //left
        if(col > 0 && soil[row][col - 1] == '\u25A2') {
            root2 = this.unionFind.find((row * this.size) + ((col - 1) % this.size));
            if(root1 != root2) {
                this.unionFind.union(root1, root2);
                if(uniteAdjact(row, col - 1)) return true;
            }
        }
        //right
        if(col < this.size - 1 && soil[row][col + 1] == '\u25A2') {
            root2 = this.unionFind.find((row*this.size)+((col+1)%this.size));
            if(root1 != root2) {
                this.unionFind.union(root1, root2);
                if(uniteAdjact(row, col + 1)) return true;
            }
        }
        return false;
    }

    public int goUntilPercolate() {
        int count = 0;
        while(!canPercolate()) {
            int newOpen = (int) (Math.random() * this.size * this.size);
            if(this.soil[newOpen/this.size][newOpen%this.size] == '\u25A0') {
                if(count%50 == 0 && count != 0) {
                    System.out.println("--------" + count + "----------");
                    printSoil();
                }
                this.soil[newOpen/this.size][newOpen%this.size] = '\u25A2';
                count++;
            }
        }
        System.out.println("--------" + count + "----------");
        printSoil();
        return count;
    }

    public void printSoil() {
        for(int i=0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.soil[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    static private int badInputCheckerForInt() {
        Scanner in = new Scanner(System.in);
        do {
            try {
                return in.nextInt();
            }
            catch (java.util.InputMismatchException ex) {
                System.out.println("\nYou need to enter a NUMBER!");
                System.out.print("Please try again: ");
                in.nextLine();
            }
        } while (true);
    }

    public static void main(String[] args) {
        System.out.print("How many times do you want to run Percolation: ");
        int repeat = badInputCheckerForInt();
        int count = 0;
        for (int i = 0; i < repeat; i++) {
            Percolate soil = new Percolate();
            count += soil.goUntilPercolate();
        }
        System.out.println("Average Percolation Threshold: " + (float) count / (400*repeat));
    }
}