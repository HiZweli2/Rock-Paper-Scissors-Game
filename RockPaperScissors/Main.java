package RockPaperScissors;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        
        System.out.println("Lets play Rock Paper Scissors!");
        //Get player Name
        System.out.print("Enter Player Name: ");
        Scanner userInput = new Scanner(System.in);
        String player = userInput.nextLine();
        // Enter number of rounds
        System.out.print("Enter number Of Rounds: ");    
        int numberOfRounds = userInput.nextInt();
        int playedRounds = 1;
        //Player starts the game
        Game gamePlay = new Game(player,numberOfRounds);

        while(playedRounds <= numberOfRounds)
        {
          //Player picks a move
          String playerChoice = gamePlay.player.play();
          //Computer picks a move
          String computerChoice = gamePlay.player.computerPlay();
          //Compare choices to see who won
          String roundWinner = gamePlay.applyRules(playerChoice, computerChoice);
          //commentate on game proceedings 
          if(playedRounds == numberOfRounds)
          {
            //End game
            endGame(gamePlay);
          }else
          {
            gamePlay.commentator(roundWinner);
          }
          playedRounds++;
        } 
        userInput.close();
        System.exit(0);
    }

        static void endGame(Game gamePlay)
        {
          if(gamePlay.player.computerScore < gamePlay.player.score)
          {
            gamePlay.player.closeScanner();
            System.out.println("Congratulations!!! You Won!!!!!!!!!");
          }else if(gamePlay.player.computerScore > gamePlay.player.score)
          {
            gamePlay.player.closeScanner();
            System.out.println("Sorry It Seems The Computer Got The Better Of you :(");
            System.out.println("You Lost!");
          }else if(gamePlay.player.computerScore == gamePlay.player.score)
          {
            gamePlay.player.closeScanner();
            System.out.println("And Yet Another Game Ends In A Tie :-");
          }else{
            gamePlay.player.closeScanner();
            System.out.println("Sorry For Some reason we couldn`t calculate the result");
          }
        }
}

class Players
{
    String playerName = "";
    String [] dice;
    int score = 0;
    int computerScore = 0;
    int round  = 1;
    
    //This allows computer to select move at random
    public String computerPlay()
    {
        // Create a Random object
        Random random = new Random();
        // Generate a random index within the array's bounds
        int randomIndex = random.nextInt(this.dice.length);
        // Retrieve the random item from the array using the generated index
        String diceRoll = this.dice[randomIndex];
        System.out.println("Computer chose: "+ diceRoll);
        return diceRoll;
    }

    //Prompts play to select a move
    
    Scanner userInput = new Scanner(System.in);
    String userchoice;
    public String play()
    {
        System.out.print("Choose your weapon[Rock,Paper,Scissors]? : ");
        userchoice = userInput.nextLine();
        //Check if string is not empty and that it is a string
        String lowerUserchoice = userchoice.toLowerCase();
        while(lowerUserchoice.equals("rock" ) == false && lowerUserchoice.equals("paper") == false && lowerUserchoice.equals("scissors") == false)
        {
          System.out.println("invalid userchoice");
          System.out.print("Choose your weapon[Rock,Paper,Scissors]? : ");
          userchoice = userInput.nextLine();
        }
        System.out.println("You chose: "+ lowerUserchoice);
        round++;
        return lowerUserchoice;
    }
    
      public void closeScanner()
      {
        userInput.close();
      }
    

    void increaseScore()
    {
        score++;
    }

    void increaseComputerScore()
    {
        computerScore++;
    }

    Players(String playerName, String [] dice)
    {
        this.playerName = playerName;
        this.dice = dice;
    }
}

class Game
{
    String [] dice = {"rock","paper","scissors"};
    Players player ;
    
    Game(String playerName ,int numberOfRounds )
    {
        Players player = new Players(playerName,dice);
        this.player = player;
    }

    public String applyRules(String playerChoice , String computerChoice)
    {
        switch(playerChoice) {
            case "rock":
              if(computerChoice == "scissors")
              {
                player.increaseScore();
                return player.playerName;

              }else if(computerChoice == "paper")
              {
                player.increaseComputerScore();
                return "computer";
              }
              break;
            case "paper":
              if(computerChoice == "rock")
              {
                player.increaseScore();
                return player.playerName;

              }else if(computerChoice == "scissors")
              {
                player.increaseComputerScore();
                return "computer";
              }
              break;
            case "scissors":
              if(computerChoice == "paper")
              {
                player.increaseScore();
                return player.playerName;

              }else if(computerChoice == "rock")
              {
                player.increaseComputerScore();
                return "computer";
              }
              break;
            default:
              return "draw";
          }
          return "invalid input";
    }

    public void commentator(String roundWinner)
    {
      boolean isComAlreadyInLead = false;
      boolean isPlayerAlreadyInLead = false;

      if( roundWinner == player.playerName && player.score > player.computerScore && isPlayerAlreadyInLead == false)
      {
        System.out.println(player.playerName + " takes the lead!!");
        isComAlreadyInLead = false;
        isPlayerAlreadyInLead = true;
      }else if(roundWinner == "computer" && player.score < player.computerScore && isComAlreadyInLead == false)
      {
        System.out.println("Computer takes the lead!!");
        isPlayerAlreadyInLead = false;
        isComAlreadyInLead = true;
      }else if(roundWinner == player.playerName && player.score > player.computerScore && isPlayerAlreadyInLead == true)
      {
        System.out.println("And " + player.playerName + " is still in the lead :)");
        isComAlreadyInLead = false;
        isPlayerAlreadyInLead = true;
      }else if(roundWinner == "computer" && player.score < player.computerScore && isComAlreadyInLead == true)
      {
        System.out.println("And Computer is still in the lead :(");
        isPlayerAlreadyInLead = false;
        isComAlreadyInLead = true;
      }else if(roundWinner == "draw")
      {
        System.out.println("Draw!!");
      }
    }
}