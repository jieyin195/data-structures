package BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Jline on 2018/12/2.
 * 递归思想:从root开始，最终返回还是root
 */
public class Bst<E extends Comparable<E>>{
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left =null;
            right=null;
        }
    }

    private Node root;
    private int size;

    public Bst(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    } 

    public boolean isEmpty(){
        return size==0;
    }

    public void add(E e){
        root = add(root,e);

    }

    private Node add( Node node, E e ) {
        if(node == null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)<0)
            node.left = add(node.left,e);
        else if(e.compareTo(node.e)>0)
            node.right = add(node.right,e);
        return node;
    }

    public boolean find(E e){
        return find(root , e);
    }

    private boolean find( Node node, E e ) {
        if(node == null)
            return false;

        if(e.compareTo(node.e)==0)
            return true;
        else if(e.compareTo(node.e)<0)
           return find(node.left,e);
        else
            return find(node.right,e);
    }

    public void preorderTraversal(){
        preorderTraversal(root);
    }

    private void preorderTraversal( Node node ) {
        if(node == null)
            return;

        System.out.println(node.e);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public void inorderTraversal(){
        inorderTraversal(root);
    }

    private void inorderTraversal( Node node ) {
        if( node == null)
            return;

        inorderTraversal(node.left);
        System.out.println(node.e);
        inorderTraversal(node.right);
    }

    public void postorderTraversal(){
        postorderTraversal(root);
    }

    private void postorderTraversal( Node node ) {
        if(node == null)
            return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.e);
    }

    //非递归前序遍历
    public void Non_preorderTraversal(){
        if(root ==null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    //非递归中序遍历
    public void Non_inorderTraversal(){
        Stack<Node> stack = new Stack<>();
        Node node =root;
        while(node != null ||!stack.isEmpty()) {
             if(node != null) {
                 stack.push(node);
                 node = node.left;

             }
             else{
                 node = stack.pop();
                 System.out.println(node.e);
                 node = node.right;
             }
        }
    }

    //非递归后序遍历
    public void Non_postorderTraversal(){
        Stack<Node> stack = new Stack<>();
        Stack<Node> output = new Stack<>();

        Node node = root;
        while(node != null || !stack.isEmpty()){
            if(node!=null){
                output.push(node);
                stack.push(node);
                node = node.right;
            }else {
                node = stack.pop();
                node = node.left;
            }
        }
        while(!output.isEmpty())
            System.out.println(output.pop().e);
    }

    //层序遍历
    public void sequenceTraversal(){
        if(root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);

            if(cur.left != null)
                queue.add(cur.left);
            if(cur.right !=null)
                queue.add(cur.right);
        }
    }

    public E findMin() {
        if(size == 0 )
            throw new IllegalArgumentException("BST is empty!");
        return findMin(root).e;
    }

    private Node findMin( Node node ) {
        if(node.left == null)
            return node;

        return findMin(node.left);
    }

    public E findMax() {
        if(size == 0 )
            throw new IllegalArgumentException("BST is empty!");
        return findMax(root).e;
    }

    private Node findMax( Node node ) {
        if(node.right == null)
            return node;

        return findMax(node.right);
    }

    //删除最小值
    public E removeMin(){
        E cur = findMin();
        root = removeMin(root);
        return cur;
    }

    private Node removeMin( Node node ) {
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left =removeMin(node.left);
        return node;
    }

    //删除最大值
    public E removeMax(){
        E cur = findMax();
        root = removeMax(root);
        return cur;
    }

    private Node removeMax( Node node ) {
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right =removeMax(node.right);
        return node;
    }

    //删除某个值
    public void remove(E e){
        root = remove(root , e);
    }

    private Node remove( Node node, E e ) {
        if(node == null)
            return null;

        if(e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)>0) {
            node.right = remove(node.right, e);
            return node;
        }else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right=null;
                size--;
                return rightNode;
            }

            if(node.right == null){
                Node leftNode = node.left;
                node.left =null;
                size--;
                return leftNode;
            }

            Node successor = findMin(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right =null;
            return successor;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        generateBSTString(root , 0 ,sb);
        return sb.toString();
    }

    private void generateBSTString( Node node, int depth, StringBuilder sb ) {
        if(node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }

            sb.append(generateDepthString(depth)+node.e+"\n");
            generateBSTString(node.left,depth+1,sb);
            generateBSTString(node.right,depth+1,sb);

    }

    private String generateDepthString( int depth ) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();

    }
}
