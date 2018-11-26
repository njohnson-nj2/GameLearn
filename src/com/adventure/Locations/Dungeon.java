package com.adventure.Locations;

import com.adventure.Adventure;
import com.adventure.characters.Hero;
import com.adventure.characters.Monster;

import java.util.Scanner;

public class Dungeon extends MapSquare {

    //Variable for puzzle solved
    private static boolean puzzleSolved = false;
    //Variable for gate key
    public static boolean hasGateKey = false;

    public Dungeon(){ }

    public String toString(){ return "the entrance to Surter's lair"; }


    public void location(Hero hero){
        Scanner sc = new Scanner(System.in);
        String sel;
        boolean leaveDungeon = false;

        System.out.println("As you approach you see tall gates nearly the height of the wall, which seems to go on forever on either side\n" +
                "At the entrance you see another of Surter's demons lying in wait for you\n");
        //Lesser demon @ door
        Adventure.battle(hero, Monster.MonsterList.LESSER_DEMON);
        //Option to leave after everything
        while(!leaveDungeon) {

            System.out.println("The demon defeated you approach the gate to see a keyhole");
            //Need gate key from guard to open door
            if (!hasGateKey) {
                System.out.println("You attempt to open the gates to no avail, it seems you will need a key to enter, but where to find one?\n" +
                        "You turn back from the lair");
                leaveDungeon = true;
                continue;
            }

            System.out.println("Using the key from the guard you unlock the massive gates and pushing with all your might manage to make your way inside\n" +
                    "Before you is a very large courtyard, you see a door to either side and a large doorway straight ahead, in the center of the courtyard you see an onyx Pedestal\n" +
                    "Do you wish to proceed?\n");

            sel = sc.nextLine();
            if (sel.toUpperCase().contains("Y") || sel.toUpperCase().contains("LEAVE") || sel.toUpperCase().contains("EXIT")) {
                leaveDungeon = true;
                System.out.println("You turn and leave Surter's lair, as you leave the doors close ominously behind you");
                continue;
            }

            //Lesser demon (Or something bigger?) in courtyard
            System.out.println("You begin to walk out into the courtyard, before you've taken more than a few steps a massive demon falls to the ground in front of you!\n");
            Adventure.battle(hero, Monster.MonsterList.BRUTE_DEMON);

            System.out.println("After defeating the demon the courtyard falls silent");
            boolean courtyardChoice = false;
            while(!courtyardChoice){
                System.out.println("Do you want to examine the Pedestal, check the Left door, the Right door, the Main door, or Leave?");
                sel = sc.nextLine();

                if (sel.toUpperCase().contains("PEDESTAL") ) {
                    //something something approach puzzle
                    courtyardpuzzle(hero);
                } else if (sel.toUpperCase().contains("LEFT") ) {
                    System.out.println("You approach the door only to find it jammed beyond opening");
                } else if (sel.toUpperCase().contains("RIGHT") ) {
                    System.out.println("Through the door you find yourself in some sort of store room\n" +
                            "On a table you find several healing potions, would you like to drink one and recover?");
                    boolean potionChoice = false;
                    while(!potionChoice){
                        sel = sc.nextLine();
                        if(sel.toUpperCase().contains("Y")){
                            hero.rest();
                            System.out.println("You drink the potions and recover your HP & MP");
                            potionChoice = true;
                        }else if (sel.toUpperCase().contains("N")){
                            System.out.println("You decide not to drink the potions");
                            potionChoice = true;
                        }else{
                            System.out.println("Sorry I didn't get that: drink the potion?");
                        }
                    }
                } else if (sel.toUpperCase().contains("MAIN") ) {
                    if(!puzzleSolved){
                        System.out.println("You approach the Main door, it has a nondescript stone archway and the door is simply carved oak\n" +
                                "However the doors have no handles, keyholes, or anything else beside hinges\n" +
                                "You attempt to push on the doors however no matter how much you push they won't budge\n" +
                                "Perhaps that Pedestal has something to do with them");
                    }else{
                        //Enter doors
                        System.out.println("As you approach the open doors the darkness is in stark contrast to the open courtyard\n" +
                                "You carefully make your way inside\n");
                        courtyardChoice = true;
                    }
                } else if (sel.toUpperCase().contains("LEAVE") ) {
                    leaveDungeon = true;
                    courtyardChoice = true;
                } else {
                    System.out.println("Sorry I didn't get that:");
                }
            }
            if(leaveDungeon) {
                System.out.println("You turn and leave Surter's lair, as you leave the doors close ominously behind you");
                continue;
            }

            //Through main door is throne room w/ Surter kill him to win
            System.out.println("As you walk through the darkness ahead you begin to see torches lining the walls\n" +
                    "The torches become more frequent as you walk until suddenly the passageway opens into a great hall\n" +
                    "The hall is immense with massive chandeliers hanging from the ceiling, stain glass windows line the walls with illustrious drapes\n" +
                    "Between the windows are massive works of art, and the ground is polished marble tile which reflects the light and colors of the stained glass\n" +
                    "At the far end of the hall is a throne, upon which sits a strange conglomeration of man and lizard\n" +
                    "He wears shining armor and a gold crown, across his lap lies a great sword and in his hand is a staff with a large sapphire affixed to the top\n" +
                    "As you approach his voice booms\n" +
                    "Surter: I am impressed you have made it this far, I have been watching your progress with great interest\n" +
                    "        You see its been so long since I've had a decent fight and I will enjoy feasting upon your flesh after I burn you alive!");
            Adventure.battle(hero, Monster.MonsterList.SURTER);
            Adventure.gameOver = true;
        }
    }

    private void courtyardpuzzle(Hero hero){
        //Courtyard puzzle + bonus solve for loot (Lesser demon for puzzle fail??)
        if(!puzzleSolved){
            Scanner sc = new Scanner(System.in);
            String sel;
            boolean leavePuzzle = false;
            System.out.println("You approach the pedestal to find bone white letters etched into the onyx, they say:\n" +
                    "It occurs once in a minute\n" +
                    "Twice in a moment\n" +
                    "Yet not once in a hundred years\n" +
                    "Speak aloud what it is to enter\n");
            while(!leavePuzzle){
                System.out.println("What will you say?");
                sel = sc.nextLine();
                if(sel.trim().toUpperCase().equals("M") || ( sel.toUpperCase().contains("M") && sel.toUpperCase().contains("Letter")) ){
                    //Puzzle opens main door
                    System.out.println("When you say aloud the letter M you hear a large creaking as the doors ahead of you slowly creak open");
                    leavePuzzle = true;
                    puzzleSolved = true;
                } else if(sel.toUpperCase().contains("EXIT") || sel.toUpperCase().contains("LEAVE")){
                    System.out.println("You turn from the puzzle");
                    leavePuzzle = true;
                } else {
                    System.out.println("You loudly declare " + sel + ", but nothing seems to happen...");
                }
            }
        } else{
            //puzzle solved, nothing else to do
            System.out.println("The pedestal remains unchanged and you've already solved the riddle");
        }
    }

}
