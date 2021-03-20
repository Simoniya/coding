import java.io.*;
import java.util.*; //importing  all the classes of the input output package //
class Item {
  String name;// we assign name as string data type//
  int price; // price as integer data type//

  public Item(String name, int price) {
    this.name = name; // constructors - The purpose of constructor is to initialize the object of a class //
    this.price = price;// intializing the object of class Item//
  }

  public String toString() { 
      return this.name + ": " + this.price; //coverts into string and returns the name and the price//
  }
}
// main class starts is the  entry point to the application//
public class Main { 
  public static void main(String[] args) throws Exception // Exception handling  whenever an error occurs // 
  {
    FileInputStream fis=new FileInputStream("input.txt");   // opening the input file and reading //    
    Scanner sc=new Scanner(fis); // you make a new object of the Scanner class //
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]); // converts its first argument to a string, parses that string, then returns an integer and read the new line character//
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> goodies_items = new ArrayList<Item>(); // giving  item as an array//

    while(sc.hasNextLine())  //  method returns true if there is another line in the input of this scanner
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close(); // else close //

    Collections.sort(goodies_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.price - b.price; // comparing the price //
      } 
    });

    int min_diff = goodies_items.get(goodies_items.size()-1).price; // intializing minimum difference //
    int min_index = 0; // intializing the minimum index as 0//
    for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) // finds the actual minimum price using for loop //
    {
      int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price; // intializing difference and comparing with above given min_diff//

      if(diff<=min_diff) // if new diff < min_diff then change the min_diff values as diff value . we repaet this till we get min diff//
      { 
        min_diff = diff;
        min_index = i; // change index also //
      }
    }
    
    

    FileWriter fw = new FileWriter("output.txt"); //  opening the file for writing the result into  the output file//
    fw.write(" Here the goodies that are  selected for distribution are:\n");
    for(int i=min_index;i<min_index + number_of_employees; i++) // returning the goodies ( within the minimum range) starting with for loop we intialize i with min-index//
    //and  we repeat the for loop uptill value of i becomes summation of both minimum index and number of employees // 
    {
      fw.write(goodies_items.get(i).toString() + "\n"); // writing the result to file and coverting into string //
    }

    fw.write("\n\n And the difference between the chosen goodie with highest price and the lowest price is " + min_diff); // result is printing//
	  fw.close(); // closes the file//
  }
}