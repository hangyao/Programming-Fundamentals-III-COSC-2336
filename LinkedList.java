/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * This is a program for Assignment 5: Linked List Workout of COSC-2336
 * @author HangYao
 */
public class LinkedList<T> {
    
    /**
     * The Node class stores a list element
     * and a reference to the next node.
     */
    private class Node {
        T value;
        Node next;
        
        /**
         * Constructor.
         * @param val The element to store in the node.
         * @param n The reference to the successor node.
         */
        Node(T val, Node n) {
            value = val;
            next = n;
        }
        
        /**
         * Constructor.
         * @param val The element to store in the node.
         */
        Node(T val) {
            this(val, null);
        }  
    }
    
    private Node first; // list head
    private Node last; // last element in list
    
    /**
     * Constructor.
     */
    public LinkedList() {
        first = null;
        last = null;
    }
    
    /**
     * The isEmpty method checks to see if the list is empty.
     * @return  true if list is empty; false otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }
    
    /**
     * The size method returns the length of the list.
     * @return The number of elements in the list.
     */
    public int size() {
        int count = 0;
        Node p = first;
        while (p != null) {
            count ++;
            p = p.next;
        }
        return count;
    }
    
    /**
     * The add method adds an element to the end of the list.
     * @param e The value to add to the end of the list.
     */
    public void add(T e) {
        if (isEmpty()) {
            first = new Node(e);
            last = first;
        }
        else {
            last.next = new Node(e);
            last = last.next;
        }
    }
    
    /**
     * The add method adds an element at a position.
     * @param index The position at which to add the element.
     * @param e The element to add to the list.
     */
    public void add(int index, T e) {
        if (index < 0 || index > size()) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }
        if (index == 0) {
            first = new Node(e, first);
            if (last == null)
                last = first;
            return;
        }
        Node pred = first;
        for (int k = 1; k <= index - 1; k++) {
            pred = pred.next;
        }
        pred.next = new Node(e, pred.next);
        if (pred.next.next == null)
            last = pred.next;
    }
    
    /**
     * The remove method removes the element at an index.
     * @param index The index of the element to remove.
     * @return The element removed.
     */
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }
        T element;
        if (index == 0) {
            element = first.value;
            first = first.next;
            if (first == null)
                last = null;
        }
        else {
            Node pred = first;
            for (int k = 1; k <= index - 1; k++)
                pred = pred.next;
            element = (T) pred.next.value;
            pred.next = pred.next.next;
            if (pred.next == null)
                last = pred;
        }
        return element;
    } 
    
    /**
     * The remove method removes an element.
     * @param element The element to remove.
     * @return true if the remove succeeded; false otherwise.
     */
    public boolean remove(T element) {
        if (isEmpty())
            return false;
        if (element.equals(first.value)) {
            first = first.next;
            if (first == null)
                last = null;
            return true;
        }
        Node pred = first;
        while (pred.next != null && !pred.next.value.equals(element)) {
            pred = pred.next;
        }
        if (pred.next == null)
            return false;
        pred.next = pred.next.next;
        if (pred.next == null)
            last = pred;
        return true;
    }
    
    /**
     * The removeAll method removes all elements from the list.
     */
    public void removeAll() {
        first = null;
        last = null;
    }
    
    /**
     * The get method gets the element at an index.
     * @param index The index of the element to get.
     * @return The element got.
     */
    public T get(int index) {
        if (index < 0 || index >= size()) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }
        T element;
        if (index == 0) {
            element = first.value;
        }
        else {
            Node pred = first;
            for (int k = 1; k <= index - 1; k++)
                pred = pred.next;
            element = (T) pred.next.value;
        }
        return element;
    }
    
    /**
     * The toString method computes the string representation of the list.
     * @return The string form of the list.
     */
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        Node p = first;
        while (p != null) {
            strBuilder.append(p.value + "\n");
            p = p.next;
        }
        return strBuilder.toString();
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String [] args)
    {
        // Read name list from dat file
        LinkedList ll = new LinkedList();
        boolean endOfFile = false;
        try {
            FileInputStream fstream = new FileInputStream("names.dat");
            DataInputStream inputFile = new DataInputStream(fstream);
            while (!endOfFile) {
                try {
                    ll.add(inputFile.readUTF());
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
            inputFile.close();
        } catch (IOException e) {
        }
        JOptionPane.showMessageDialog(null, "names.dat has been read as a linked list.");
        
        // Use a menu to demonstrate functions
        String input;
        int op;
        String menu = "Menu:\n" +
                "1 - Print the contents of the list;\n" +
                "2 - Check if the list is empty;\n" +
                "3 - Check the size of the list;\n" +
                "4 - Add data to the list;\n" +
                "5 - Add data to a specific location in the list;\n" +
                "6 - Remove an item from the list based on position;\n" +
                "7 - Remove an item from the list based on data value;\n" +
                "8 - Remove all elements from the list;\n" +
                "9 - Get an item from the list based on the position;\n" +
                "Other - Quit the program.";
        input = JOptionPane.showInputDialog(null, menu);
        while (input != null) {
            try {
                op = Integer.parseInt(input);
                switch (op) {
                    case 1:
                        // Print the contents of the list
                        JOptionPane.showMessageDialog(null, "The contents of the list:\n" + 
                                ll);
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 2:
                        // Check if the list is empty
                        if (ll.isEmpty())
                            JOptionPane.showMessageDialog(null, "Check if the list is empty:\n" +
                                    "The list is empty.");
                        else
                            JOptionPane.showMessageDialog(null, "Check if the list is empty:\n" +
                                    "The list is NOT empty.");
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 3:
                        // Check the size of the list
                        JOptionPane.showMessageDialog(null, "Check the size of the list:\n" +
                                "The size of the list is " + ll.size());
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 4:
                        // Add data to the list
                        input = JOptionPane.showInputDialog(null, "Add data to the list:");
                        ll.add(input);
                        JOptionPane.showMessageDialog(null, "The element " + input +
                                " has been added to the list:\n" + 
                                ll);
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 5:
                        // Add data to a specific (valid) location in the list
                        input = JOptionPane.showInputDialog(null, "Specific location to add data:");
                        try {
                            op = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "The input is not valid!");
                            input = JOptionPane.showInputDialog(null, menu);
                            break;
                        }
                        input = JOptionPane.showInputDialog(null, "Data to add:");
                        try {
                            ll.add(op, input);
                            JOptionPane.showMessageDialog(null, "The element " + input +
                                " has been added at #" + op + " of the list:\n" + 
                                ll);
                        } catch (IndexOutOfBoundsException e) {
                            JOptionPane.showMessageDialog(null, "Index out of bounds!");
                        }
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 6:
                        // Remove an item from the list based on position
                        input = JOptionPane.showInputDialog(null, "Remove an item from the list based on position:");
                        try {
                            op = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "The input is not valid!");
                            input = JOptionPane.showInputDialog(null, menu);
                            break;
                        }
                        try {
                            JOptionPane.showMessageDialog(null, "The element "+ (String) ll.remove(op) +
                                " has been removed from #" + op + " of the list:\n" + 
                                ll);
                        } catch (IndexOutOfBoundsException e) {
                            JOptionPane.showMessageDialog(null, "Index out of bounds!");
                        }
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 7:
                        // Remove an item from the list based on data value
                        input = JOptionPane.showInputDialog(null, "Remove an item from the list based on data value:");
                        if (ll.remove(input))
                            JOptionPane.showMessageDialog(null, "The element " + input + 
                                " has been removed from the list:\n" + ll);
                        else
                            JOptionPane.showMessageDialog(null, "The element " + input + 
                                " is NOT found in the list:\n" + ll);
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 8:
                        // Removes all elements from the list
                        int confirm = JOptionPane.showConfirmDialog(null, 
                                "Do you want to remove all elements from the list?",
                                null, JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            ll.removeAll();
                            JOptionPane.showMessageDialog(null, "All elements have been removed from the list.\n" +
                                    "The size of the list is " + ll.size());    
                        }
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 9:
                        // Get an item from the list based on the position
                        input = JOptionPane.showInputDialog(null, "Get an item from the list based on the position:");
                        try {
                            op = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "The input is not valid!");
                            input = JOptionPane.showInputDialog(null, menu);
                            break;
                        }
                        try {
                            JOptionPane.showMessageDialog(null, "The #" + op + 
                                " element of the list is:\n" + 
                                ll.get(op));
                        } catch (IndexOutOfBoundsException e) {
                            JOptionPane.showMessageDialog(null, "Index out of bounds!");
                        }
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "The program is quitting.");
                        System.exit(0);
                        break;
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The program is quitting.");
                System.exit(0);
            }
        }
    }
}
    
    



