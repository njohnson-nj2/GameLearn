package com.adventure;

import com.adventure.Locations.Dungeon;
import com.adventure.Locations.MapSquare;
import com.adventure.Locations.Town;
import com.adventure.characters.Hero;
import com.adventure.characters.Monster;
import com.adventure.equip.Armor;
import com.adventure.equip.Weapon;

import java.util.Scanner;

//Game play:
//
//        You create your character, that character has stats and abilities and equipment and experience and level ups
//
//        You explore places, find things, fight monsters, meet NPCs, visit shops

//System in turns shit into an array list of strings from spaces not all one thing, so i need to turn sel into a single string before parsing through the selector


public class Adventure {

    public static boolean gameOver = false;


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Adventure a = new Adventure();


        System.out.println("Welcome to Acalidia!\nIf you would like a brief description of how to play type Y and hit enter\nTo get straight to the game type N and hit enter\n");
        String sel = sc.nextLine();
        if(sel.toUpperCase().contains("Y")){
            System.out.println("This is a text based role playing game\nAt times you will be prompted to do something by entering text into the console\n" +
                    "To make decisions simply type in what you would like to do\nProvided what you type contains keywords included in the prompt the story will progress\n" +
                    "If what is typed in does not include a keyword you will be prompted again with a specific list of keyword options\nEnjoy!\n");
        }

        System.out.println("Welcome Hero!\nThe realm is in danger and you alone are able to save it\n");

        //Character Creation
        Hero hero = new Hero();

        System.out.println("An agent of the vile Surter approaches\nDefeat the demon before it destroys the town!\n");
        System.out.println("Before you are a Rusty Sword, a Knolled Staff, and a Rusty Shield\nDo you take the sword, the staff, or the shield?");

        Weapon.WeaponList firstWeapon = null;
        while(firstWeapon == null){
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("SWORD")) {
                firstWeapon = Weapon.WeaponList.RUSTY_SWORD;
                System.out.println("A sword is perfect for attacking\n");
            } else if (sel.toUpperCase().contains("STAFF")) {
                firstWeapon = Weapon.WeaponList.KNOLLED_STAFF;
                System.out.println("The staff radiates a magical energy\n");
            } else if (sel.toUpperCase().contains("SHIELD")) {
                firstWeapon = Weapon.WeaponList.RUSTY_SHIELD;
                System.out.println("A warrior concerned with defense usually stays alive\n");
            } else {
                System.out.println("Sorry I didn't get that: Sword, Staff, or Shield?");
            }
            hero.equipWeapon(firstWeapon);
        }

        System.out.println("The armorer beckons you forth\nArmorer: Are you fighting that thing?! Then you better take one of these");
        System.out.println("On his table lay a rusty set of Heavy Armor, Light Armor, and Robes\nWhich will you take?");

        Armor.ArmorList firstArmor = null;
        while(firstArmor == null){
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("HEAVY") || sel.toUpperCase().contains("IRON")) {
                firstArmor = Armor.ArmorList.RUSTY_ARMOR;
                System.out.println("In heavy armor most blows will roll off you\n");
            } else if (sel.toUpperCase().contains("LIGHT") || sel.toUpperCase().contains("LEATHER")) {
                firstArmor = Armor.ArmorList.LIGHT_ARMOR;
                System.out.println("Light armor means light on your feet\n");
            } else if (sel.toUpperCase().contains("ROBE")) {
                firstArmor = Armor.ArmorList.ROBES;
                System.out.println("The robes seem to emanate magic\n");
            } else {
                System.out.println("Sorry I didn't get that: Heavy Armor, Light Armor, or Robes?");
            }
            hero.equipArmor(firstArmor);
        }

        //Opening battle
        battle(hero, Monster.MonsterList.LESSER_DEMON);

        System.out.println("Incredible Hero!\nYou have defeated the agent of Surter\n");
        //Something something town Tiil
        //Array(collection?) of MapSquares made up of Shops, streets, etc (polymorphism out the functions)

        MapSquare[][] map = new MapSquare[4][4];
        map[0][0] = new MapSquare(MapSquare.Geographies.WALLS, Monster.MonsterList.LESSER_DEMON);
        map[1][0] = new Dungeon();
        map[2][0] = new MapSquare(MapSquare.Geographies.WALLS, Monster.MonsterList.LESSER_DEMON);
        map[3][0] = new MapSquare(MapSquare.Geographies.WALLS, Monster.MonsterList.LESSER_DEMON, Weapon.WeaponList.STEEL_SWORD);
        map[0][1] = new MapSquare(MapSquare.Geographies.FOREST, Weapon.WeaponList.MAGES_STAFF);
        map[1][1] = new MapSquare(MapSquare.Geographies.FOREST, Monster.MonsterList.WOLF);
        map[2][1] = new MapSquare(MapSquare.Geographies.PLAINS);
        map[3][1] = new MapSquare(MapSquare.Geographies.PLAINS);
        map[0][2] = new MapSquare(MapSquare.Geographies.FOREST, Monster.MonsterList.WOLF);
        map[1][2] = new MapSquare(MapSquare.Geographies.FOREST);
        map[2][2] = new MapSquare(MapSquare.Geographies.PLAINS, Monster.MonsterList.WOLF);
        map[3][2] = new MapSquare(MapSquare.Geographies.PLAINS, Monster.MonsterList.COMMON_THIEF);
        map[0][3] = new MapSquare(MapSquare.Geographies.DESERT, Armor.ArmorList.SCALE_ARMOR);
        map[1][3] = new MapSquare(MapSquare.Geographies.DESERT, Monster.MonsterList.COMMON_THIEF);
        map[2][3] = new Town(Town.TownList.TIIL);
        map[3][3] = new MapSquare(MapSquare.Geographies.DESERT);

        int posX = 2;
        int posY = 3;
        int[] newXY; //= new int[2]; ?not needed?

        map[posX][posY].location(hero);

        while(!gameOver){

            newXY = travel(map, posX, posY);
            posX = newXY[0];
            posY = newXY[1];

            map[posX][posY].location(hero);

        }
        System.out.println("Thanks for playing!!");
    }

    public static void battle(Hero hero, Monster.MonsterList enemy){
        Scanner sc = new Scanner(System.in);
        Monster monster = new Monster(enemy, hero.getLevel());
        boolean monsterAlive = true;
        boolean c = false;
        String sel;

        System.out.println(monster.toString() + " approaches!");

        while(monsterAlive) {
            System.out.println("HP: " + hero.getHealthPoints() + "    MP: " + hero.getMagicPoints() + "\n");
            System.out.println("Do you want to:\nAttack\nDefend\n" + hero.getSpecial() +"\n");
            c = false;

            if(hero.getSpeed() > monster.getSpeed()){
                //Hero turn
                while(!c) {
                    sel = sc.nextLine();
                    if (sel.toUpperCase().contains("ATTACK")) {
                        monsterAlive = monster.takeDamage(hero.attack());
                        c = true;
                    } else if (sel.toUpperCase().contains("DEFEND")) {
                        hero.defend();
                        c = true;
                    } else if (sel.toUpperCase().contains("SPECIAL") || sel.toUpperCase().contains("EVADE") || sel.toUpperCase().contains("FOCUS")
                            || sel.toUpperCase().contains("MAGIC") || sel.toUpperCase().contains("FLAME") || sel.toUpperCase().contains("SPELL")
                            || sel.toUpperCase().contains("BACKSTAB") || sel.toUpperCase().contains("PSYCH")) {
                        monsterAlive = monster.takeDamage(hero.special());
                        c = true;
                    } else {
                        System.out.println("Do you want to:\nAttack\nDefend\n" + hero.getSpecial() +"\n");
                    }
                }
                //Check monster alive
                if(!monsterAlive){
                    System.out.println(monster.toString() + " defeated!");
                    hero.loot(monster.loot());
                    break;
                }
                //Monster turn
                hero.takeDamage(monster.attack());
            }else{
                //Monster turn
                hero.takeDamage(monster.attack());
                //Hero turn
                while(!c) {
                    sel = sc.nextLine();
                    if (sel.toUpperCase().contains("ATTACK")) {
                        monsterAlive = monster.takeDamage(hero.attack());
                        c = true;
                    } else if (sel.toUpperCase().contains("DEFEND")) {
                        hero.defend();
                        c = true;
                    } else if (sel.toUpperCase().contains("SPECIAL") || sel.toUpperCase().contains("EVADE") || sel.toUpperCase().contains("FOCUS")
                            || sel.toUpperCase().contains("MAGIC") || sel.toUpperCase().contains("FLAME") || sel.toUpperCase().contains("SPELL")
                            || sel.toUpperCase().contains("BACKSTAB") || sel.toUpperCase().contains("PSYCH")) {
                        monsterAlive = monster.takeDamage(hero.special());
                        c = true;
                    } else {
                        System.out.println("Do you want to:\nAttack\nDefend\n" + hero.getSpecial() +"\n");
                    }
                    //Check monster alive alive
                    if (!monsterAlive) {
                        System.out.println(monster.toString() + " defeated!");
                        hero.loot(monster.loot());
                        break;
                    }
                }
            }
        }
    }

    private static int[] travel(MapSquare[][] map, int x, int y){
        Scanner sc = new Scanner(System.in);
        String sel;
        boolean n = true;
        boolean e = true;
        boolean s = true;
        boolean w = true;
        boolean m = false;

        if(x == 0) w = false;
        if(x == 3) e = false;
        if(y == 0) n = false;
        if(y == 3) s = false;

        System.out.println("As you set out:");
        if(n) {
            System.out.println("To the North lie " + map[x][y - 1].toString());
        } else {
            System.out.println("To the North lie impassable walls");
        }
        if(e){
            System.out.println("To the East lie " + map[x+1][y].toString());
        } else {
            System.out.println("To the East lies a wide river with no visible forging points");
        }
        if(s){
            System.out.println("To the South lie " + map[x][y+1].toString());
        } else {
            System.out.println("To the South lies a vast desert which almost certainly will mean your death");
        }
        if(w){
            System.out.println("To the West lie " + map[x-1][y].toString() + "\n");
        } else {
            System.out.println("To the West lies an impassable mountain range\n");
        }

        System.out.println("Which way will you go?");
        while(!m){
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("NORTH") || ( n && sel.toUpperCase().contains(map[x][y - 1].toString().toUpperCase())) ) {
                if(n){
                    y -= 1;
                    m = true;
                } else {
                    System.out.println("You attempt to scale the walls and fall on your ass");
                    System.out.println("Which way will you go: North, East, South, or West");
                }
            } else if (sel.toUpperCase().contains("EAST") || ( e && sel.toUpperCase().contains(map[x+1][y].toString().toUpperCase())) ) {
                if(e){
                    x += 1;
                    m = true;
                } else {
                    System.out.println("Attempting to cross the river you are immediately swept by the current,\nyou're barely able to make it back to shore");
                    System.out.println("Which way will you go: North, East, South, or West");
                }
            } else if (sel.toUpperCase().contains("SOUTH") || ( s && sel.toUpperCase().contains(map[x][y+1].toString().toUpperCase())) ) {
                if(s){
                    y += 1;
                    m = true;
                } else {
                    System.out.println("You walk 10 paces into the desert immediately feeling exhaustion overcome you and turn back");
                    System.out.println("Which way will you go: North, East, South, or West");
                }
            } else if (sel.toUpperCase().contains("WEST") || ( w && sel.toUpperCase().contains(map[x-1][y].toString().toUpperCase())) ) {
                if (w) {
                    x -= 1;
                    m = true;
                } else {
                    System.out.println("You search for a path up the mountain to no avail");
                    System.out.println("Which way will you go: North, East, South, or West");
                }
            } else {
                System.out.println("You snap out of your daydream still unsure of which way to go");
                System.out.println("Which way will you go: North, East, South, or West");
            }
        }

        int[] destination = {x,y};
        return destination;
    }

}
