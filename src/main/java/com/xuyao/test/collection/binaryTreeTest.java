package com.xuyao.test.collection;


import java.util.LinkedList;
import java.util.Queue;

public class binaryTreeTest {

    public static void main(String[] args) throws Exception {
        BinaryTree<Integer> bt = new BinaryTree<>();
        bt.put(35);
        bt.put(20);
        bt.put(15);
        bt.put(16);
        bt.put(29);
        bt.put(28);
        bt.put(30);
        bt.put(40);
        bt.put(50);
        bt.put(45);
        bt.put(55);
        bt.preOrder(bt.getRoot());
        System.out.println();
        bt.preOrderLoop(bt.getRoot());
        System.out.println();
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.inOrderLoop(bt.getRoot());
        System.out.println();
        bt.postOrder(bt.getRoot());
        System.out.println();
        bt.postOrderLoop(bt.getRoot());
        System.out.println();
        bt.breadthFirst(bt.getRoot());
    }

    static class Node<E extends Comparable<E>> {
        E value;
        Node<E> left;
        Node<E> right;

        public Node(E value) {
            this.value = value;
        }
    }

    static class BinaryTree<E extends Comparable<E>> {
        Node<E> root;

        public void put(E value) {
            if (value == null) return;
            if (root == null) {
                root = new Node<E>(value);
                return;
            }
            Node<E> current = root;
            while (true) {
                if (value.compareTo(current.value) < 0) {
                    if (current.left == null) {
                        current.left = new Node<>(value);
                        break;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Node<>(value);
                        break;
                    }
                    current = current.right;
                }
            }
        }

        public Node<E> getRoot() {
            return root;
        }

        public void preOrder(Node<E> root){
            System.out.print(root.value + ", ");
            if(root.left != null){
                preOrder(root.left);
            }
            if(root.right != null){
                preOrder(root.right);
            }
        }

        public void inOrder(Node<E> root){
            if(root.left != null){
                inOrder(root.left);
            }
            System.out.print(root.value + ", ");
            if(root.right != null){
                inOrder(root.right);
            }
        }

        public void postOrder(Node<E> root){
            if(root.left != null){
                postOrder(root.left);
            }
            if(root.right != null){
                postOrder(root.right);
            }
            System.out.print(root.value + ", ");
        }

        public void preOrderLoop(Node<E> root){
            LinkedList<Node<E>> stack = new LinkedList<>();
            stack.push(root);
            while(!stack.isEmpty()){
                Node<E> pop = stack.pop();
                System.out.print(pop.value + ", ");
                if(pop.right != null)
                    stack.push(pop.right);
                if(pop.left != null)
                    stack.push(pop.left);
            }
        }

        public void inOrderLoop(Node<E> root){
            LinkedList<Node<E>> stack = new LinkedList<>();
            Node<E> current = root;
            while(current != null || !stack.isEmpty()){
                while(current != null){
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                System.out.print(current.value + ", ");
                current = current.right;
            }
        }

        public void postOrderLoop(Node<E> root){
//            LinkedList<Node<E>> stack = new LinkedList<Node<E>>();
//            Node<E> currentNode = root;
//            Node<E> rightNode = null;
//            while (currentNode != null || !stack.isEmpty()) {
//                while (currentNode != null) {
//                    stack.push(currentNode);
//                    currentNode = currentNode.left;
//                }
//                currentNode = stack.pop();
//                while (currentNode.right == null || currentNode.right == rightNode) {
//                    System.out.print(currentNode.value + ", ");
//                    rightNode = currentNode;
//                    if (stack.isEmpty()) {
//                        return;
//                    }
//                    currentNode = stack.pop();
//                }
//                stack.push(currentNode);
//                currentNode = currentNode.right;
//            }
        }

        public void breadthFirst(Node<E> root){
            Queue<Node<E>> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                Node<E> poll = queue.poll();
                System.out.print(poll.value + ", ");
                if(poll.left != null)
                    queue.offer(poll.left);
                if(poll.right != null)
                    queue.offer(poll.right);
            }
        }
    }

}

