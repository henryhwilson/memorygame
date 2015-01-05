public class memoryCard
{
    // instance variables - replace the example below with your own
    private String hiddenString; //Hidden value that turns over
    private String defaultString; //Initial value that displays by default
    private String showingString; //The String that is showing
    private Boolean inGame; //Keeps track if it was already guessed and out of the game
    private Boolean showingDefault; //Keeps track if the top, default String is showing


    /**
     * Constructor for objects of class memoryCard
     */
    public memoryCard(int number)
    {
        hiddenString = "";
        String letter = getLetter(number);
        defaultString = "|-" + letter + "-|";
        showingString = defaultString;
        showingDefault = true;
        inGame = true;
    }
    
    public String getValue()
    {
        return showingString;
    }
    
    public Boolean showing()
    {
        if (showingDefault)
            return false;
        else
            return true;
    }
    
    public Boolean inGame()
    {
        return inGame;
    }
    
    public void setHiddenValue(String value)
    {
        hiddenString = value;
    }

    public void toggle()
    {
        if (inGame == true)
        {
           if (showingDefault)
            {
            showingDefault = false;
            showingString = hiddenString;
            }
           else
            {
            showingDefault = true;
            showingString = defaultString;
            } 
        }
    }

    public void erase()
    {
        inGame = false;
        showingString = "     ";
        defaultString = "     ";
        hiddenString = "     ";
    }
    
    public String getLetter(int number)
    {
        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        return alphabet[number];
    }
}
