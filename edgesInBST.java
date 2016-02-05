import java.lang.Math; 
import java.util.*;
public class Solution
{
  static class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
      this.val = val;
      left = null;
      right = null;
    }
  }
  
  public static TreeNode commonAncestor(TreeNode root,int a,int b){
    if(root == null){
      return null;
    }
    if(root.val == a || root.val == b){
      return root;
    }
    TreeNode left = commonAncestor(root.left,a,b);
    TreeNode right = commonAncestor(root.right,a,b);
    if(left != null && right != null){
      return root;
    }
    if(left != null){
      return left;
    }
    return right;
  }
  
  public static int getLevel(TreeNode root,int value,int level){
    if(root==null){
      return 0;
    }
    if(root.val == value){
      return level;
    }
    int resultLevel = getLevel(root.left,value,level+1);
    if(resultLevel == 0){
      resultLevel = getLevel(root.right,value,level+1);
    }
    return resultLevel;
  }
  
  public static int getNumberOfEdges(TreeNode root,int a, int b){
    if(root == null){
    	return 0;
    }
    int levela = getLevel(root,a,1);
    if(levela == 0 ){
      return 0;
    }
    int levelb = getLevel(root,b,1);
    if(levelb == 0 ){
      return 0;
    }
    TreeNode common = commonAncestor(root,a,b);
    if(common == null ){
      return 0;
    }else{
      int levelCommon = getLevel(root,common.val,1);
      return levela + levelb -2*levelCommon;
    }
  }
  
  public static TreeNode createBST(int values[]){
    boolean marked[] = new boolean[values.length];
    if(values.length==0){
      return null;
    }
    Queue<TreeNode> q = new LinkedList<>();
    int nextIndex = 0;
    marked[nextIndex] = true;
    TreeNode root = new TreeNode(values[0]);
    q.add(root);
    
    while(!q.isEmpty()){
      TreeNode node = q.remove();
      int start = nextIndex+1;
      boolean doneLeft = false;
      boolean doneRight = false;
      while(start<values.length && (!doneLeft || !doneRight)){
        if(!marked[start]){
          int value = values[start];
          if(value<=node.val && !doneLeft){
            TreeNode nextNode = new TreeNode(value);
            node.left = nextNode;
            doneLeft = true;
            q.add(nextNode);
            marked[start]=true;
            if(!doneRight){
              nextIndex = start;
            }
            nextIndex = start;
          }else if(value>node.val && !doneRight){
            TreeNode nextNode = new TreeNode(value);
            node.right = nextNode;
            doneRight = true;
            q.add(nextNode);
            marked[start]=true;
            if(!doneLeft){
              nextIndex = start;
            }
          }
        }
        start++;
      }
    }
    return root;
  }
  
  public static void preOrder(TreeNode root){
    if(root == null){
      return;
    }
    System.out.print(root.val);
    preOrder(root.left);
    preOrder(root.right);
  }
  
  public static void main(String[] args)
  {
    //4213
    /*TreeNode root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.left.left.left = new TreeNode(5);
    */
    int[] values = {4,2,1,3};
    TreeNode root = createBST(values);
    
    //preOrder(root); 
    //System.out.println();   
    int numEdges = getNumberOfEdges(root,1,3);

    if(numEdges == 0){
      System.out.println("Not found");
    }else{
      System.out.println(numEdges);
    }      
  }
}
