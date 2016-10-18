/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package names;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * This is a program for Assignment 3: Quicksort Names of COSC-2336
 * @author HangYao
 */
public class QuicksortNames {
    public static void main(String[] args) {
        // Define the data file to be read. 
        String fileName = "names.dat";
        ArrayList<String> nameList = new ArrayList<String>();
        String input; // To hold user input string
        
        // Read the data from file.
        try {
            nameList = readData(fileName);
        } catch (IOException e) {
        }
        
        // Display the message of sucessful reading of data.
        JOptionPane.showMessageDialog(null, "The data has been read " +
                "successfully.\nIt contains " + nameList.size() + " names.");
        
        // Display the unsorted name list.
        String output = "Here are the names:\n";
        int count = 0;
        for (String name : nameList) {
            output += name + ", ";
            count++;
            if (count%10 == 0)
                output += "\n";
        }
        JOptionPane.showMessageDialog(null, output);
        
        // Display the message of initialization of quicksort.
        JOptionPane.showMessageDialog(null, "Now, this name list will be " + 
                "quicksorted!");
        
        // Quicksort the name list.
        quicksort(nameList, 0, nameList.size() - 1);
        
        // Display the message of successful quicksort.
        JOptionPane.showMessageDialog(null, "The name list has been " +
                "quicksorted successfully!");
        
        // Display the sorted name list.
        output = "Here are the sorted names:\n";
        count = 0;
        for (String name : nameList) {
            output += name + ", ";
            count++;
            if (count%10 == 0)
                output += "\n";
        }
        JOptionPane.showMessageDialog(null, output);
        
        // Input the name to search
        input = JOptionPane.showInputDialog("Enter a name to search.");
        while ((input != null) && (input.length() > 0)) {
            input = input.toUpperCase();
            // Binary Search is the most efficient search method for a sorted array.
            int result = binSearch(nameList, 0, nameList.size() - 1, input);
            if (result != -1)
                JOptionPane.showMessageDialog(null, "The name " + input + 
                        " has been found in the list.\n" + "It is #" + result +
                        " in the list.");
            else
                JOptionPane.showMessageDialog(null, "The name " + input + 
                        " is NOT in the list.");
            input = JOptionPane.showInputDialog("Enter another name to search.");
        }
        System.exit(0);
    }
    
    /**
     * The readData method reads the data from a binary data file fileName
     * and save data objects in an ArrayList.
     * @param fileName The file name of the binary data to be read.
     * @return The ArrayList contains all objects from the binary data.
     * @throws IOException 
     */
    private static ArrayList<String> readData(String fileName) throws IOException {
        ArrayList<String> nameList = new ArrayList<String>();
        String name;
        boolean endOfFile = false;

        // Create the input objects.
        FileInputStream fstream = new FileInputStream("names.dat");
        DataInputStream inputFile = new DataInputStream(fstream);

        // Read the contents of the file.
        while (!endOfFile) {
             try {
                  name = inputFile.readUTF();
                  nameList.add(name);
             } catch (EOFException e) {
                  endOfFile = true;
             }
        }
        
        // Close the file.
        inputFile.close();     
        
        return nameList;
    }
    
    // Quicksort the Name List
    /**
     * The quicksort method uses the QuickSort algorithm to sort nameList, 
     * from the start element through the end element.
     * @param nameList The ArrayList to sort.
     * @param start The starting index of the region to sort.
     * @param end The ending index of the region to sort.
     */
    private static void quicksort(ArrayList<String> nameList, int start, int end) {
        int pivotPoint;
        
        if (start < end) {
            // Get the pivot point.
            pivotPoint = partition(nameList, start, end);
            
            // Sort the first sub list.
            quicksort(nameList, start, pivotPoint - 1);
            
            // Sort the second sub list.
            quicksort(nameList, pivotPoint + 1, end);
        }
    }
    
    /**
     * The partition method selects the value in the middle of the nameList as
     * the pivot. The list is rearranged so all the strings less than the pivot
     * are on its left and all the strings greater than pivot are on its right.
     * @param nameList The ArrayList being sorted.
     * @param start The starting index of the region to sort.
     * @param end The ending index of the region to sort.
     * @return The index of pivot.
     */
    private static int partition(ArrayList<String> nameList, int start, int end) {
        int pivotIndex, mid;
        String pivotValue;
        mid = (start + end) / 2;
        swap(nameList, start, mid);
        pivotIndex = start;
        pivotValue = nameList.get(start);
        for (int scan = start + 1; scan <= end; scan++) {
            if (nameList.get(scan).compareTo(pivotValue) < 0) {
                pivotIndex++;
                swap(nameList, pivotIndex, scan);
            }
        }
        swap(nameList, start, pivotIndex);
        
        return pivotIndex;
    }
    
    /**
     * The swap method swaps the a element in nameList with the b element.
     * @param nameList The ArrayList containing the elements.
     * @param a The first element to swap.
     * @param b The second element to swap.
     */
    private static void swap(ArrayList<String> nameList, int a, int b) {
        String temp;
        
        temp = nameList.get(a);
        nameList.set(a, nameList.get(b));
        nameList.set(b, temp);
    }
    
    /**
     * The binSearch method binary search the name in a sorted nameList from 
     * lower to upper.
     * @param nameList The ArrayList containing the elements.
     * @param lower The lower end of the search range.
     * @param upper The upper end of the search range.
     * @param name The name to be searched.
     * @return the index of name or -1 if not found.
     */
    private static int binSearch(ArrayList<String> nameList, 
            int lower, int upper, String name) {
        if (lower > upper)
            return -1;
        int middle = (lower + upper) / 2;
        if (nameList.get(middle).compareTo(name) == 0)
            return middle;
        if (nameList.get(middle).compareTo(name) < 0)
            return binSearch(nameList, middle + 1, upper, name);
        else
            return binSearch(nameList, lower, middle - 1, name);
    }
}
