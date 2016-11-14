/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacksandqueues;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * This is a program for Assignment 6: Stacks, Queues, and Files of COSC-2336
 * @author HangYao
 */
public class StacksAndQueues {
    public static void main(String [] args) {
        String input;
        int op;
        
        String filename = JOptionPane.showInputDialog(null, 
                   "Enter the file name to read:");
        String menu = "Operation Options:\n" +
                      "1 - Reverse;\n" +
                      "2 - Convert;\n" +
                      "3 - Compare;\n" +
                      "Other - Quit the program.";
        
        input = JOptionPane.showInputDialog(null, menu);
        while (input != null) {
            try {
                op = Integer.parseInt(input);
                switch (op) {
                    case 1: // Reverse
                        try {
                            ArrayStack text = readTextToStack(filename);
                            String output = JOptionPane.showInputDialog(null,
                                    "Enter the file name to write:");
                            String extension = output.substring(output.lastIndexOf(".") + 1, output.length());
                            if (extension != "txt")
                                output.concat(".txt");
                            writeStackToText(output, text);
                        } catch(IOException e) {
                            JOptionPane.showMessageDialog(null, "Can't find the file.");
                        }
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 2: // Convert
                        try {
                            ArrayQueue text = readTextToQueue(filename);
                            ArrayQueue textConv = new ArrayQueue(text.capacity());
                            while (!text.empty()){
                                textConv.enqueue(Character.toUpperCase(text.dequeue()));
                            }
                            String output = JOptionPane.showInputDialog(null,
                                    "Enter the file name to write:");
                            String extension = output.substring(output.lastIndexOf(".") + 1, output.length());
                            if (extension != "txt")
                                output.concat(".txt");
                            writeQueueToText(output, textConv);
                        } catch(IOException e) {
                            JOptionPane.showMessageDialog(null, "Can't find the file.");
                        }
                        input = JOptionPane.showInputDialog(null, menu);
                        break;
                    case 3: // Compare
                        try {
                            String filename2 = JOptionPane.showInputDialog(null,
                                    "Enter the second file name to read:");
                            ArrayQueue text1 = readTextToQueue(filename);
                            ArrayQueue text2 = readTextToQueue(filename2);
                            compareQueues(text1, text2);
                        } catch(IOException e) {
                            JOptionPane.showMessageDialog(null, "Can't find the file.");
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
    
    /**
     * The readTextToStack method reads a text file to a stack of characters.
     * @param filename The file name to read.
     * @return The stack of characters in the text file.
     * @throws IOException 
     */
    public static ArrayStack readTextToStack(String filename)
            throws IOException {
        int r;
        long len;
        
        len = new File(filename).length();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        ArrayStack charStack = new ArrayStack((int) len);
        while ((r = br.read()) != -1) {
            char ch = (char) r;
            charStack.push(ch);
        }
        br.close();
        return charStack;
    }
    
    /**
     * The writeStackToText method writes a stack of characters to a text file
     * with reversed order.
     * @param filename The file name to write.
     * @param stack The stack of characters.
     * @throws IOException 
     */
    public static void writeStackToText(String filename, ArrayStack stack)
            throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        while (!stack.empty()) {
            char ch = stack.pop();
            bw.write(ch);
        }
        bw.close();
    }
    
    /**
     * The readTextToQueue method reads a text file to a queue of characters.
     * @param filename The file name to read.
     * @return The queue of characters in the text file.
     * @throws IOException 
     */
    public static ArrayQueue readTextToQueue(String filename)
            throws IOException {
        long len = new File(filename).length();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        ArrayQueue charQueue = new ArrayQueue((int) len);
        int r;
        while ((r = br.read()) != -1) {
            char ch = (char) r;
            charQueue.enqueue(ch);
        }
        br.close();
        return charQueue;
    }
    
    /**
     * The writeQueueToText method writes a queue of characters to a text file.
     * @param filename The file name to write.
     * @param queue The queue of characters.
     * @throws IOException 
     */
    public static void writeQueueToText(String filename, ArrayQueue queue)
            throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        while (!queue.empty()) {
            char ch = queue.dequeue();
            bw.write(ch);
        }
        bw.close();
    }
    
    /**
     * The compareQueues method compares two queues of characters.
     * @param q1 The first queue of characters.
     * @param q2 The second queue of characters.
     * @return true if two queues are identical; false if two queues are not the same.
     */
    public static boolean compareQueues(ArrayQueue q1, ArrayQueue q2){
        boolean identical = true;
        if (q1.capacity() != q2.capacity()) {
            JOptionPane.showMessageDialog(null, "The files are not the same.");
            identical = false;
        }
        else {
            while ((!q1.empty()) && (identical == true)) {
                if (q1.dequeue() != q2.dequeue()) {
                    JOptionPane.showMessageDialog(null, "The files are not the same.");
                    identical = false;
                }
            }
        }
        if (identical == true)
            JOptionPane.showMessageDialog(null, "The files are identical.");
        return identical;
    }
}
