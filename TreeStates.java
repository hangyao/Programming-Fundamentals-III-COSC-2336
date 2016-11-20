/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treestates;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author HangYao
 */
public class TreeStates {
    public static void main(String [] args) {
        final String FILENAME = "states.txt";
        BinarySearchTree statesTree = new BinarySearchTree();
        
        // Read the data file
        int reply = JOptionPane.showConfirmDialog(null,
                "The program is reading the file " + FILENAME + ".",
                "Reading the file.",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION)
            System.exit(0);
        else {
            // Read the data from file.
            try {
                statesTree = readTextToTree(FILENAME);
            } catch (IOException e) {
            }
        }
        JOptionPane.showMessageDialog(null, 
            "The in-order traversal of the binary tree will be outputed in console.");
        statesTree.inorder();
        System.exit(0);
    }
    
    /**
     * The readData method reads the data from a text file 'filename' and save 
     * Strings in an ArrayList.
     * @param filename The file name of the text file to be read.
     * @return The ArrayList contains all Strings from the text file.
     * @throws IOException 
     */
    public static BinarySearchTree readTextToTree(String filename) 
            throws IOException {
        BinarySearchTree binTree = new BinarySearchTree();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            binTree.add(line);
        }
        br.close();
        return binTree;
    }
}
