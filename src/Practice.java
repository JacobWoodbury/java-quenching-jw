import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Practice {
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) {
        if(nums == null || nums.length < 1){
            return 0;
        }
        int sum = 0;
        for(int i =0; i<= nums.length-1; i++){
            if(nums[i]%2 != 0){
                sum += nums[i];
            }
        }
        return sum;
    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException if words is null
     */
    public static String shortestWord(Set<String> words) {
        if(words.isEmpty()){throw new IllegalArgumentException();}
        if(words == null){throw new NullPointerException();}
        String shortest = null;
        for(String word: words){
            if(shortest == null) shortest = word;
            if(word.length() < shortest.length()){
                shortest = word;
            }
            if(word.length() == shortest.length()){
                if(word.compareTo(shortest) <0){
                    shortest = word;
                }
            }
        }
        return shortest;
    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) {
        if (ages == null) {throw new NullPointerException();}
        Set<String> adult = new HashSet<>();
        for(String name: ages.keySet()){
            if(ages.get(name)>=18){
                adult.add(name);
            }
        }
        return adult;
    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {
        if( head == null) throw new IllegalArgumentException();
        ListNode<Integer> temp = head;
        int biggest = Integer.MIN_VALUE;
        
        while(temp != null){
            if(temp.data > biggest)  biggest = temp.data;
            temp = temp.next;
        }
        return biggest;
    }

    /**
     * Returns a frequency map counting how frequently items appear in a linked list.
     * 
     * Example:
     *   Input: a -> x -> a -> a -> x -> y
     *   Output: {a:3, x:2, y: 1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T> the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) {
        Map<T, Integer> freqMap= new HashMap<>();
        if(head == null) return freqMap;
        ListNode<T> temp = head;
        int count = 0;
        while(temp != null){
            if(freqMap.containsKey(temp.data)){
                count = freqMap.get(temp.data) +1;
                freqMap.put(temp.data, count);
            }else{
                freqMap.put(temp.data, 1);
            }
            
            temp = temp.next;
        }
        return freqMap;
    }


    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) {
        if(root == null) return 0;
        return levelCount(root, 0);
        
    }
    public static int levelCount(BinaryTreeNode<?> temp, int lvlCount){
        if(temp == null) return lvlCount;
        lvlCount++;
        int leftCount = levelCount(temp.left, lvlCount);
        int rightCount = levelCount(temp.right, lvlCount);
        int max = Math.max(leftCount, rightCount);
        return max;
    }


    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     *       5
     *     /   \
     *    8     4
     *   / \   / 
     *  7  9  2
     *    /
     *   1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18 
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) {
        if (root == null) return 0;
        return sumAtLevelRecur(root, level, 1, 0);
    }
    public static int sumAtLevelRecur(BinaryTreeNode<Integer> currentNode, int level, int currentLvl, int sum){
        if(currentLvl < level){
            if(currentNode.left != null) sum = sumAtLevelRecur(currentNode.left, level, currentLvl+1, sum);
            if(currentNode.right != null) sum = sumAtLevelRecur(currentNode.right, level, currentLvl+1, sum);
        }
        if(currentLvl == level){
            sum += currentNode.data;
        }
        return sum;
    }


    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list. 
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) {
        int rootSum = sumRoot(root, 0);
        int headSum = sumHead(head, 0);
        System.out.println(rootSum + " " + headSum);
        if(rootSum == headSum) return true;
        return false;
       
        
    }
    public static int sumRoot(BinaryTreeNode<Integer> temp, int sum){
        if(temp == null) return sum;
        sum += temp.data;
        sum = sumRoot(temp.left, sum);
        sum = sumRoot(temp.right, sum);
        
        return sum;
    }
    public static int sumHead(ListNode<Integer> head, int sum){
        if(head == null) return sum;
        sum += head.data;
        sum = sumHead(head.next, sum);
        
        return sum;
    }

    /**
     * Returns the sum of all the vertices in a graph that are reachable from a given
     * starting vertex.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the starting vertex
     * @return the sum of all the vertices
     */
    public static int graphSum(Vertex<Integer> start) {
        HashSet<Vertex<Integer>> visited = new HashSet<>();
        return graphSum(start, visited, 0);
       
    }

    public static int graphSum(Vertex<Integer> current, HashSet<Vertex<Integer>> visited, int sum){
        if(current == null || visited.contains(current)) return sum;
        if (!visited.contains(current)) {
            visited.add(current);
            sum += current.data;
            for(Vertex<Integer> neighbor: current.neighbors){
                sum = graphSum(neighbor, visited, sum);
            }
        }
        return sum;
    }
    /**
     * Returns the count of vertices in a graph that have an outdegree of 0.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the entrypoint to the graph
     * @return the count of vertices with outdegree 0
     */
    public static int sinkCount(Vertex<Integer> start) {
        return 0;
    }
}