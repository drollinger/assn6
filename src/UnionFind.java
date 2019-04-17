public class UnionFind {

    private int[] setList;

    /**********************************
     * Constructor to set up the original number of sets
     **********************************/
    public UnionFind(int listSize) {
        this.setList = new int[listSize];
        for(int i = 0; i < setList.length; i++) {
            setList[i] = -1;
        }
    }

    /**********************************
     * Unionizes two roots
     **********************************/
    public void union(int root1, int root2) {
        if(root1 == root2) {
            System.out.println("ARGGGGGG!!!!!!");
        }
        if(this.setList[root1] <= this.setList[root2]) {
            this.setList[root1] += this.setList[root2];
            this.setList[root2] = root1;
        }
        else {
            this.setList[root2] += this.setList[root1];
            this.setList[root1] = root2;
        }
    }

    /**********************************
     * finds and returns value at location given.
     * If not found it returns the location.
     **********************************/
    public int find(int location) {
        if(this.setList[location] < 0){
            return location;
        }
        else {
            return this.setList[location] = find(this.setList[location]);
        }
    }

    public void printArray() {
        System.out.println("Item #:\t| Parent/Size: ");
        System.out.println("========|============");
        for(int i = 0; i < this.setList.length; i++)
            System.out.println(i + "\t\t|\t" + this.setList[i]);
    }

    //The Main function to test things out
    public static void main(String[] args) {
        UnionFind unionFindTest = new UnionFind(30);
        System.out.println("Array initialized...");
        unionFindTest.printArray();
        System.out.println("Normal Union Test:");
        System.out.println("Building up the first two sets with 2 and 4, and 6 and 8...");
        unionFindTest.union(unionFindTest.find(2), unionFindTest.find(4));
        unionFindTest.union(unionFindTest.find(6), unionFindTest.find(8));
        unionFindTest.printArray();
        System.out.println("Smart Union by size Test:");
        System.out.println("Adding 3 to the 2 set and combining it with the 6 set...");
        unionFindTest.union(unionFindTest.find(2), unionFindTest.find(3));
        unionFindTest.union(unionFindTest.find(2), unionFindTest.find(6));
        unionFindTest.printArray();
        System.out.println("Path Compression Test:");
        System.out.println("Performing a find on 6...");
        unionFindTest.find(6);
        unionFindTest.printArray();
    }
}