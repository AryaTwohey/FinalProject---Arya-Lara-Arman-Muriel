package zork;

public class CommandWords {
  // a constant array that holds all valid command words
  private static final String validCommands[] = { "go", "quit", "help", "eat", "climb", "up", "down", "n", "north", "e", "east", "s", "south", "west", "w", "take", "drop", "kill", "search", "read", "run", "shoot", "hit", "stab"};

  /**
   * Constructor - initialise the command words.
   */
  public CommandWords() {
    // nothing to do at the moment...
  }

  /**
   * Check whether a given String is a valid command word. Return true if it is,
   * false if it isn't.
   **/
  public boolean isCommand(String aString) {
    for (String c : validCommands) {
      if (c.equals(aString))
        return true;
    }
    // if we get here, the string was not found in the commands
    //fred is hot
    return false;
  }

  /*
   * Print all valid commands to System.out.
   */
  public void showAll() {
    for (String c : validCommands) {
      System.out.print(c + "  ");
    }
    System.out.println();
  }
}
