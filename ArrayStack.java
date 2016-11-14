/**
   Array Implementation of a stack.
*/
package stacksandqueues;

public class ArrayStack
{
   private char [] s;  // Holds stack elements
	private int top;   // Stack top pointer
   
   /**
      Constructor.
      @param capacity The capacity of the stack.
   */
	
   public ArrayStack (int capacity)
   {
       s = new char[capacity];
       top = 0;
   }
   
   /**
      The empty method checks for an empty stack.
      @return true if stack is empty.
   */
   
   public boolean empty() 
   { 
       return top == 0; 
   }
   
   /**
       The capacity method returns the length of 
       the array used to implement the queue.
       @return The capacity of the queue.
    */
    
    public int capacity()
    {
        return s.length;
    }
   
   /**
      The push method pushes a value onto the stack.
      @param x The value to push onto the stack.
		@exception StackOverflowException When the 
		stack is full.
   */
   
   public void push(char x) 
   {
       if (top == s.length)  
           throw new StackOverFlowException();
       else
       {
          s[top] = x;
          top ++;           
       }         
   }
   
   /** 
      The pop method pops a value off the stack.
      @return The value popped.
		@exception EmptyStackException When the 
		stack is empty.
   */
   
   public char pop()
   {
       if (empty())
           throw new EmptyStackException();
       else
       {
          top--;
          return s[top];
       }
   }
   
   /** 
      The peek method returns the value at the
      top of the stack.
      @return value at top of the stack.
		@exception EmptyStackException When the 
		stack is empty.
   */
   
   char peek()
   {
       if (empty())
           throw new EmptyStackException();
       else
       {
           return s[top-1];
       }
   }
   
   /**
       The toString method returns a 
		 readable representation of the 
		 contents of the stack.
       @return  The string representation
		 of the contents of the stack.
     */
    
    public String toString()
    {
      StringBuilder sBuilder = new StringBuilder();
      sBuilder.append("top = " + top + "\n");
      for (int k = 0; k < s.length; k++)
      {
          //if (q[k] != null)
          sBuilder.append(k + " " + s[k]);
          //else 
          //   sBuilder.append(k + " ?");
          if (k != s.length - 1)
	     sBuilder.append("\n");
      }
      return sBuilder.toString();        
    }    
}