/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindromes;
import java.io.*;

/**
 * This program is for Module 2: Recursion Assignment - Palindromes of 
 * Programming Fundamental III - COSC 2336
 * @author HangYao
 */
public class Palindromes {
    public static void main(String[] args) throws IOException {
        String inputString; // The palindrome
        
        // Create the necessary objects to read keyboard input.
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(reader);
        
        // Ask the user to enter the palindrome
        System.out.print("Enter a string:");
        inputString = keyboard.readLine();
        
        // Print the result
        if (isPalindrome(inputString))
            System.out.println(inputString + " is a palindrome!");
        else 
            System.out.println(inputString + " is NOT a palindrome.");
    }
    
    
    public static boolean isPalindrome(String s) {
        // Check if the length of string is 0 or 1, then return true
        if (s.length() <= 1)
            return true;
        
        // Check if the first and last characters of the string are the same,
        // then do the recursion on the substring with the first and last 
        // characters removed, if eventrually they are the same all the way,
        // then return true
        if (s.charAt(0) == s.charAt(s.length() - 1))
            return isPalindrome(s.substring(1, s.length() - 1));
            
        // Otherwise return false
        return false;
    }
}
