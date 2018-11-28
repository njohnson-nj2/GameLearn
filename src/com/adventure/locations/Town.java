package com.adventure.locations;

import com.adventure.Adventure;
import com.adventure.characters.Hero;
import com.adventure.characters.Monster;
import com.adventure.equip.Armor;
import com.adventure.equip.Weapon;

import java.util.Scanner;

public class Town extends MapSquare {

    private TownList name;

    public enum TownList {TIIL}

    public Town(TownList name){
        switch(name){
            case TIIL:
                this.name = name;
        }
    }

    public String toString() {
        String townName = name.toString().toLowerCase();
        townName = townName.replaceAll("_"," ");
        townName = name.toString().substring(0,1) + townName.substring(1);
        return townName + " village";
    }

    public void location(Hero hero){
        System.out.println("You are in the town of Tiil\n" +
                "Various people go about their daily lives around you\n" +
                "Of note there is a Shop, an Inn, a Tavern, and a Local Guard officer\n"
        );
        boolean leaveTown = false;
        Scanner sc = new Scanner(System.in);
        String sel;

        //loop until leave
        while(!leaveTown) {
            System.out.println("Do you want to go to the Shop, Inn, Tavern, Local Guard, or Leave town?\n");
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("SHOP") || sel.toUpperCase().contains("STORE") || sel.toUpperCase().contains("WEAPON") || sel.toUpperCase().contains("ARMOR")) {
                shop(hero);

            } else if (sel.toUpperCase().contains("INN") || sel.toUpperCase().contains("REST")) {
                inn(hero);

            } else if (sel.toUpperCase().contains("TAVERN") || sel.toUpperCase().contains("BAR")) {
                bar(hero);

            } else if (sel.toUpperCase().contains("GUARD") || sel.toUpperCase().contains("LOCAL") || sel.toUpperCase().contains("OFFICER")) {
                localGuard(hero);

            } else if (sel.toUpperCase().contains("LEAVE") || sel.toUpperCase().contains("EXIT")) {
                leaveTown = true;
                System.out.println("You set out from Tiil\n");
            } else {
                System.out.println("Sorry I didn't get that:");
            }
        }
    }

    private void shop(Hero hero){
        //Shop                                                                      !!!!!!!!!! Change price to be a feature of Weapon/Armor
        //Buy weapons Huna or buy armor Thudtran
        boolean leaveShop = false;
        Scanner sc = new Scanner(System.in);
        String sel;

        System.out.println("You approach the two smiths hammering away seemingly in tandem");
        while(!leaveShop){
            System.out.println("Do you want to shop for Weapons, shop for Armor, or Leave?");
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("WEAPON") || sel.toUpperCase().contains("BLADE") || sel.toUpperCase().contains("SWORD") || sel.toUpperCase().contains("SHIELD") || sel.toUpperCase().contains("STAFF") || sel.toUpperCase().contains("HUNA")) {
                boolean shopWeapon = true;
                System.out.println("Huna: Have a look at my wares\n" +
                        "Weapon       Gold   Weapon      Gold\n" +
                        Weapon.WeaponList.RUSTY_SWORD.toString() + "   10    " + Weapon.WeaponList.STEEL_SWORD.toString() + "  80\n" +
                        Weapon.WeaponList.RUSTY_SHIELD.toString() + "  10    " + Weapon.WeaponList.STEEL_SHIELD.toString() + " 80\n" +
                        Weapon.WeaponList.KNOLLED_STAFF.toString() + " 10    " + Weapon.WeaponList.MAGES_STAFF.toString() + "  80\n"
                    );
                Weapon weapon;
                while(shopWeapon){
                    System.out.println("Huna: What would you like to look at?");
                    sel = sc.nextLine();
                    if (sel.toUpperCase().contains(Weapon.WeaponList.RUSTY_SWORD.toString().toUpperCase()) ) {
                        weapon = new Weapon(Weapon.WeaponList.RUSTY_SWORD);

                    } else if (sel.toUpperCase().contains(Weapon.WeaponList.STEEL_SWORD.toString().toUpperCase()) ) {
                        weapon = new Weapon(Weapon.WeaponList.STEEL_SWORD);

                    } else if (sel.toUpperCase().contains(Weapon.WeaponList.RUSTY_SHIELD.toString().toUpperCase()) ) {
                        weapon = new Weapon(Weapon.WeaponList.RUSTY_SHIELD);

                    } else if (sel.toUpperCase().contains(Weapon.WeaponList.STEEL_SHIELD.toString().toUpperCase()) ) {
                        weapon = new Weapon(Weapon.WeaponList.STEEL_SHIELD);

                    } else if (sel.toUpperCase().contains(Weapon.WeaponList.KNOLLED_STAFF.toString().toUpperCase()) ) {
                        weapon = new Weapon(Weapon.WeaponList.KNOLLED_STAFF);

                    } else if (sel.toUpperCase().contains(Weapon.WeaponList.MAGES_STAFF.toString().toUpperCase()) ) {
                        weapon = new Weapon(Weapon.WeaponList.MAGES_STAFF);
                    } else if (sel.toUpperCase().contains("LEAVE") || sel.toUpperCase().contains("EXIT")) {
                        shopWeapon = false;
                        weapon = new Weapon(Weapon.WeaponList.RUSTY_SWORD);
                        System.out.println("You step away from Huna's weapons\n");
                    } else {
                        System.out.println("Sorry I didn't get that:\n");
                        continue;
                    }

                    if(shopWeapon && hero.compareEquip(weapon)){
                        if(hero.getGold() > weapon.getCost()){
                            System.out.println("You buy " + weapon.toString() + " and equip it");
                            hero.purchase(weapon.getCost());
                            hero.equipWeapon(weapon);
                        } else {
                            System.out.println("You don't have enough to buy " + weapon.toString());
                        }
                    }
                }
            } else if (sel.toUpperCase().contains("ARMOR") || sel.toUpperCase().contains("HEAVY") || sel.toUpperCase().contains("LIGHT") || sel.toUpperCase().contains("ROBE") || sel.toUpperCase().contains("THUDTRAN")) {
                boolean shopArmor = true;
                System.out.println("Thudtran: Have a look at my wares\n" +
                        "Armor       Gold   Armor       Gold" +
                        Armor.ArmorList.RUSTY_ARMOR.toString() + " 10    " + Armor.ArmorList.STEEL_ARMOR.toString() + " 90" +
                        Armor.ArmorList.LIGHT_ARMOR.toString() + " 10    " + Armor.ArmorList.SCALE_ARMOR.toString() + " 90" +
                        Armor.ArmorList.ROBES.toString() + "       10    " + Armor.ArmorList.MAGES_ROBES.toString() + " 90"
                );

                Armor armor;
                while(shopArmor){
                    System.out.println("Thudtran: What would you like to look at?");
                    sel = sc.nextLine();
                    if (sel.toUpperCase().contains(Armor.ArmorList.RUSTY_ARMOR.toString().toUpperCase()) ) {
                        armor = new Armor(Armor.ArmorList.RUSTY_ARMOR);

                    } else if (sel.toUpperCase().contains(Armor.ArmorList.STEEL_ARMOR.toString().toUpperCase()) ) {
                        armor = new Armor(Armor.ArmorList.STEEL_ARMOR);

                    } else if (sel.toUpperCase().contains(Armor.ArmorList.LIGHT_ARMOR.toString().toUpperCase()) ) {
                        armor = new Armor(Armor.ArmorList.LIGHT_ARMOR);

                    } else if (sel.toUpperCase().contains(Armor.ArmorList.SCALE_ARMOR.toString().toUpperCase()) ) {
                        armor = new Armor(Armor.ArmorList.SCALE_ARMOR);

                    } else if (sel.toUpperCase().contains(Armor.ArmorList.ROBES.toString().toUpperCase()) ) {
                        armor = new Armor(Armor.ArmorList.ROBES);

                    } else if (sel.toUpperCase().contains(Armor.ArmorList.MAGES_ROBES.toString().toUpperCase()) ) {
                        armor = new Armor(Armor.ArmorList.MAGES_ROBES);

                    } else if (sel.toUpperCase().contains("LEAVE") || sel.toUpperCase().contains("EXIT")) {
                        shopArmor = false;
                        armor = new Armor(Armor.ArmorList.RUSTY_ARMOR);
                        System.out.println("You step away from Thudtran's armor\n");
                    } else {
                        System.out.println("Sorry I didn't get that:\n");
                        continue;
                    }

                    if(shopArmor && hero.compareEquip(armor)){
                        if(hero.getGold() > armor.getCost()){
                            System.out.println("You buy " + armor.toString() + " and equip it");
                            hero.purchase(armor.getCost());
                            hero.equipArmor(armor);
                        } else {
                            System.out.println("You don't have enough to buy " + armor.toString());
                        }
                    }
                }

            } else if (sel.toUpperCase().contains("LEAVE") || sel.toUpperCase().contains("EXIT")) {
                System.out.println("You turn to leave the smiths\n");
                leaveShop = true;
            } else {
                System.out.println("Sorry I didn't get that:\n");
            }
        }
    }

    private void inn(Hero hero){
        //Inn: Stay for HP/MP
        Scanner sc = new Scanner(System.in);
        String sel;

        if(hero.getGold() >= 5){
            System.out.println("You have " + hero.getGold() + " gold, would you like to stay at the inn and recover for 5 gold?");
            boolean innChoice = false;
            while(!innChoice){
                sel = sc.nextLine();
                if (sel.toUpperCase().contains("Y")) {
                    hero.rest(5);
                    System.out.println("Your HP and MP are restored\n");
                    innChoice = true;
                } else if (sel.toUpperCase().contains("N")){
                    System.out.println("You leave the Inn\n");
                    innChoice = true;
                } else {
                    System.out.println("You have " + hero.getGold() + " gold, stay at the inn for 5 gold? Y/N");
                }
            }
        }else{
            System.out.println("The Inn is 5 gold, you only have " + hero.getGold() + " you can't stay here right now\n");
        }
    }

    private void bar(Hero hero){
        //Bar
        //Buy drink from barkeep if bought write out a drunk excursion, maybe with a chance to spend more with a short novel of drunken revelry?
        //  (if no selected obviously shady character offers free drink which kills you)
        //Local patron tells rumors about loot on map
        Scanner sc = new Scanner(System.in);
        String sel;
        boolean leaveBar = false;

        System.out.println("Scanning the bar you see a bearded dwarf standing behind the bar cleaning a glass,\n" +
                "a few patrons asleep at the bar and one friendly looking elf who waves at you as you enter\n" +
                "Do you want to order a drink from the barkeep, approach the friendly elf or leave?\n"
            );
        while(!leaveBar){
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("DWARF") || sel.toUpperCase().contains("BAR") || sel.toUpperCase().contains("DRINK") || sel.toUpperCase().contains("ORDER")) {
                System.out.println("\nAs you approach the dwarf he sets down his glass and looks at you past a bent nose protruding just above his illustrious beard\n" +
                        "Garend: Garend's me name and welcome to my bar the drunken badger, would ye like a drink? 2 gold pieces and I'll serve ye my finest");

                if(hero.getGold() > 1) {    //if Gold
                    boolean drinkChoice = false;
                    while(!drinkChoice){
                        System.out.println("You have " + hero.getGold() + " gold, purchase a drink for 2 gold pieces?");
                        sel = sc.nextLine();
                        if(sel.toUpperCase().contains("Y") || sel.toUpperCase().contains("DRINK")){
                            drinkChoice = true;
                            System.out.println("You accept Garend's offer and he walks in back for so long you think he might not be returning\n" +
                                    "When he does he's holding a very dusty bottle of brown liquid\n" +
                                    "Garend: This here is me finest for the hero what saved the village, it's a special dwarven brew\n" +
                                    "Garend: Knocks most on their asses! Let's see how you do\n" +
                                    "He pours you a drink and as you sip the enticing beverage you feel a tremendous warmth circle your body as the smooth liquid makes its way down your throat\n" +
                                    "The room seems to instantly brighten and an overwhelming sense of cheer begins build within you,\n" +
                                    "At Garend's behest you begin to recant the battle with Surter's agent, as your excitement grows telling the story many other patrons gather to listen\n" +
                                    "Before you know it you've you talked well into the night with half the town drinking, singing, and revelling with all the townsfolk eager to buy you drinks for your bravery\n" +
                                    "\nYou awaken the next morning with a splitting headache lying across several barstools\n");
                        } else if (sel.toUpperCase().contains("N")){
                            drinkChoice = true;
                            System.out.println("You decline Garend's offer");
                            shadyDrink();
                        } else {
                            System.out.println("Sorry I didn't get that:");
                        }
                    }
                } else { //Else
                    System.out.println("You delcine Garend's offer as you don't have enough gold\n");
                    shadyDrink(); //call shady
                }

                System.out.println("Do you want to talk to the Friendly Elf or Leave?");
            } else if (sel.toUpperCase().contains("ELF") || sel.toUpperCase().contains("FRIENDLY")) {
                System.out.println("As you approach the elf beckons you to sit with her\n" +
                        "Elf:  Greetings traveler, I saw you defeat that demon earlier, that was quite impressive\n" +
                        "Sulliya: I'm a scout, my name is Sulliya, and I travel the surrounding area quite often, but I've never faces such a foe in open combat\n" +
                        "         In exchange for helping the town I'm going to tell you about something I saw the other day while I was out scouting");
                int scout =(int)Math.round(Math.random() * (byte)10);
                if(scout < 4){
                    System.out.println("         In the sands to the West I was almost certain I saw something poking out of the ground\n" +
                            "         I'm not certain what it was, but it didn't look like it belonged, be careful should you seek it out\n" +
                            "         Farewell traveler and good luck on your journeys");
                } else if(scout > 7){
                    System.out.println("         Near the walls to the NorthEast I'm pretty sure I saw something shine\n" +
                            "         I'm not certain what, but it Surter's agents patrol those walls and I didn't stick around, be careful should you seek it out\n" +
                            "         Farewell traveler and good luck on your journeys");
                } else {
                    System.out.println("         Amongst the trees to the NorthWest I almost thought there was a piece of wood very different from the others\n" +
                            "         My mind could have just as easily been playing tricks on me, but be careful should you seek it out\n" +
                            "         Farewell traveler and good luck on your journeys\n");
                }
                System.out.println("Do you wish to get a drink from the Bar or Leave?\n");
            } else if (sel.toUpperCase().contains("LEAVE") || sel.toUpperCase().contains("EXIT")) {
                leaveBar = true;
                System.out.println("You leave the Tavern back out into the town\n");
            } else {
                System.out.println("Sorry I didn't get that: Barkeep, Elf, Leave?\n");
            }
        }
    }

    private void shadyDrink(){
        Scanner sc = new Scanner(System.in);
        String sel;
        boolean drinkChoice = false;
        System.out.println("As you turn to leave a very shady looking person in a dark hood in a very strange voice says to you\n" +
                "Stranger: The heeero of Tiil mus'n paey fu a driink, heeere have minee\n");

        while(!drinkChoice){
            System.out.println("Will you accept the suspicious offer?");
            sel = sc.nextLine();
            if(sel.toUpperCase().contains("Y")){
                System.out.println("You take the drink from the stranger and quaff it in a single gulp\n" +
                        "as you do you catch glimpse of the strangers face, a vile twisted demonic face contorted into a gruesome smile\n" +
                        "You suddenly feel yourself unable to breath as your body weakens, moments later you collapse to the floor dead...\n\n" +
                        "GAME OVER");
                System.exit(0);
            } else if (sel.toUpperCase().contains("N")){
                drinkChoice = true;
                System.out.println("You decline the free drink from the unknown stranger at the bar as your mother taught you much better than that\n" +
                        "Stranger: Let meee knoow if'n yoou change yeer mine" +
                        "You walk walk awsy from the bar\n");
            } else {
                System.out.println("Sorry I didn't get that:");
            }
        }
    }

    private void localGuard(Hero hero){
        //Local guard
        //Town info - including explanation for why you can't go houses
        //Map info - general desc of larger map
        //Eventual side quest????
        Scanner sc = new Scanner(System.in);
        String sel;
        boolean leaveGuard = false;

        System.out.println("You approach the guard who hails you as you approach\n" +
                "Guard: I heard about what you did, I'm sorry I wasn't there to join you but there was another of those vile demons on the other side of town\n" +
                "       I hope you understand how many lives you saved. I only wish I could make a run at Surter myself, but I can't leave the town undefended\n" +
                "       You wouldn't be thinking about taking him on would you? If so I may be able to Help, but perhaps you just wanted information on the Town, or surrounding Area?");

        while(!leaveGuard){
            System.out.println("Do you want to ask the guard about his Help, the Town, the Area, or Leave?");
            sel = sc.nextLine();
            if(sel.toUpperCase().contains("HELP")){
                if(!Dungeon.hasGateKey) {
                    boolean helpChoice = false;
                    System.out.println("Guard: The entrance to Surter's lair is almost due North of here, but the gate is always locked\n" +
                            "       I have a key that should open that lock so you can enter, but I can't simply send someone to their doom\n" +
                            "       If you can defeat me in battle I will give you the key, but I will not hold back and I may kill you\n" +
                            "       If you aren't certain you can defeat me then you will never take down Surter"
                        );
                    while(!helpChoice){
                        System.out.println("Guard: Are you ready to fight me?");
                        sel = sc.nextLine();
                        if (sel.toUpperCase().contains("Y")) {
                            Adventure.battle(hero, Monster.MonsterList.TOWN_GUARD);
                            System.out.println("Guard: Well done hero! Here is the key you will need to enter Surter's lair, I had Sulliya make the mold for me on a scouting mission\n" +
                                    "       I wish I could go with you, but I believe you can handle it. Good luck hero!");
                            Dungeon.hasGateKey = true;
                            helpChoice = true;
                        } else if (sel.toUpperCase().contains("N")){
                            System.out.println("Let me know when you change your mind, you won't be able to enter Surter's lair without this key\n");
                            helpChoice = true;
                        } else {
                            System.out.println("Sorry I didn't get that:");
                        }
                    }
                } else {
                    System.out.println("Guard: I've done all I can for you, I believe you can defeat Surter. Good luck!!");
                }
            } else if(sel.toUpperCase().contains("TOWN")  || sel.toUpperCase().contains("VILLAGE")){
                System.out.println("Guard: This is a small little hamlet, but we have the essentials\n" +
                        "       You'll find the Inn with warm beds where you can recover,\n" +
                        "       Garend run's the Drunken Badger if you're looking to find a drink, you can usually find Sulliya there as well if you want more tips on the area\n" +
                        "       You can find Huna at the weapon shop or Thudtran selling armor if you're in need of equipment, other than that most people keep to themselves\n" +
                        "       I certainly wouldn't expect anyone here to let you into their homes, especially in these times, was there anything else you wanted to ask about?\n"
                    );
            } else if(sel.toUpperCase().contains("AREA")  || sel.toUpperCase().contains("WORLD")){
                System.out.println("Guard: Around the village and further South you'll mostly find desert, I wouldn't recommend going any further South it gets very bad very quickly\n" +
                        "       NorthWest is mostly forest and NorthEast mostly plains, watch out for bandits and wolves out there\n" +
                        "       Far enough west is the Maleford mountains and far enough East is the Lollires river, both are very difficult to cross so be careful\n" +
                        "       If you go far enough North you'll reach the walls of Surter's lair, the entrance is due North, if you're think about going there I might be able to help\n" +
                        "       Was there anything else you wanted to ask about?\n"
                    );
            } else if(sel.toUpperCase().contains("LEAVE") || sel.toUpperCase().contains("EXIT")){
                leaveGuard = true;
                System.out.println("You thank the guard and turn to leave\n");
            } else {
                System.out.println("Sorry I didn't get that:");
            }
        }

    }



}
