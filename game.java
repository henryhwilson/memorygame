import java.util.Scanner; //allows connecting to input streams
import java.util.Random;


public class game
{
    private memoryCard[][] card;
    private String[] temporaryStrings = {"|Joy|","|Say|","|Run|","|Cat|","|Bat|","|Hat|","|Pit|","|Pop|","|Joy|","|Say|","|Run|","|Cat|","|Bat|","|Hat|","|Pit|","|Pop|"};
    private Scanner reader; // create variable of the Scanner class
    private Random r = new Random();
    private String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private int amountShowing;
    private String letterShowing = "";
    private int score;
    private int tilesLeft;
    Boolean entryError;
    
    /**
     * Constructor
     */
    public game()
    {
        card = new memoryCard[4][4];
        amountShowing = 0;
        score = 0;
        shuffle();
        tilesLeft = 16;
        
        int counter = 0;
        for (int x = 0; x<4; x++)
        {
            for (int y = 0; y<4; y++)
            {
                card[x][y] = new memoryCard(counter);
                card[x][y].setHiddenValue(temporaryStrings[counter]);
                counter++;
            }
        }
        
        printCards();
        System.out.println("Enter a letter to get started!");
        
        reader = new Scanner (System.in); // make a new Scanner object & connect it to the keyboard
        while (true)
           getInput();
    }

    public void printCards()
    {
        System.out.println("Java Matching Game! Type \"q\" to quit.");
        for (int x = 0; x<4; x++)
        {
            for (int y = 0; y<4; y++)
            System.out.print(card[x][y].getValue() + " ");
            System.out.println();
        }
        
    }
       
    public void getInput() 
    {       
            System.out.print("> "); // print prompt   
            String temp = reader.nextLine(); // take a line of input typed in by the user
            entryError = false;
            if (isError(temp))
                entryError = true;
            if (amountShowing == 1 && temp.equals(letterShowing))
                entryError = true;
            if (temp.equals("q"))
                System.exit(0);
            if (amountShowing == 1 && entryError == false)
            {
                toggle(temp);
                System.out.print('\u000C');
                printCards();
                if (isMatch(temp,letterShowing))
                {
                    erase(temp);
                    erase(letterShowing);
                    tilesLeft-=2;
                    System.out.print("Match! 3...");
                    score+=5;
                }
                else
                {
                System.out.print("No match! 3...");
                score-=1;
                }
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) { }
                System.out.print(" 2...");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) { }
                System.out.print(" 1...");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) { }
                hideAll();
                amountShowing = 0;
            }
            else if (entryError == false)
            {
                toggle(temp);
                amountShowing = 1;
                letterShowing = temp;
            }
            if (tilesLeft == 0)
            {
                System.out.print("You won with a score of " + score + "!");
                System.exit(0);
            }
            System.out.print('\u000C');
            printCards();
    }
    
    public void toggle(String letter)
    {
        int counter = 0;
        for (int x = 0; x<4; x++)
        {
            for (int y = 0; y<4; y++)
            {
                if (letter.equals(alphabet[counter]))
                {
                    card[x][y].toggle();
                }
                counter++;
            }
        }

    }
    
    public void erase(String letter)
    {
        int counter = 0;
        for (int x = 0; x<4; x++)
        {
            for (int y = 0; y<4; y++)
            {
                if (letter.equals(alphabet[counter]))
                {
                    card[x][y].erase();
                }
                counter++;
            }
        }

    }
    
    public void hideAll()
    {
        int counter = 0;
        for (int x = 0; x<4; x++)
        {
            for (int y = 0; y<4; y++)
            {
                if (card[x][y].showing())
                {
                    card[x][y].toggle();
                }
                counter++;
            }
        }
    }
    
       public Boolean isMatch(String letter1, String letter2)
    {        
        String value1 = "1"; //Arbitrary different values
        String value2 = "2";
        
        int counter3 = 0;
        for (int x = 0; x<4; x++)
        {
            for (int y = 0; y<4; y++)
            {
                if (letter1.equals(alphabet[counter3]))
                {
                    value1 = card[x][y].getValue();
                }
                counter3++;
            }
        }
        
        int counter4 = 0;
        for (int x = 0; x<4; x++)
        {
            for (int y = 0; y<4; y++)
            {
                if (letter2.equals(alphabet[counter4]))
                {
                    value2 = card[x][y].getValue();
                }
                counter4++;
            }
        }
        
        if (value1.equals(value2))
            return true;
        else
            return false;

    }
    
    public void shuffle()
    {
        //Shuffles the temporaryStrings array
        int pos = 0;
        for (int x=0;x<16;x++)
         {  
            pos = r.nextInt(16);
            String temp = temporaryStrings[x];
            temporaryStrings[x] = temporaryStrings[pos];
            temporaryStrings[pos] = temp;
         }  
    }
    
    public Boolean isError(String entry)
    {
        Boolean goodInput = false; //temporary
        
        for (int x = 0; x<17; x++)
        {
            if (alphabet[x].equals(entry))
                goodInput = true;
        }
        
        if (goodInput != true)
            return true;
        
        int counter = 0;
        for (int x = 0; x<4; x++)
        {
            for (int y = 0; y<4; y++)
            {
                if (entry.equals(alphabet[counter]))
                {
                    if (card[x][y].inGame() == false)
                        return true;
                }
                counter++;
            }
        }
        return false;
    }
    
   
}
