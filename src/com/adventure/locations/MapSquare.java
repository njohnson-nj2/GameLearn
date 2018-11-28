package com.adventure.locations;

import com.adventure.Adventure;
import com.adventure.characters.Hero;
import com.adventure.characters.Monster;
import com.adventure.equip.Armor;
import com.adventure.equip.Equipment;
import com.adventure.equip.Weapon;

import java.util.Scanner;

public class MapSquare {

    protected String description;
    protected Monster.MonsterList enemy;
    protected Equipment loot;
    protected Geographies loc;

    public enum Geographies {PLAINS, FOREST, DESERT, WALLS}

    public MapSquare(Geographies map, Monster.MonsterList enemy, Weapon.WeaponList weapon){
        this.enemy = enemy;
        loot = new Weapon(weapon);
        loc = map;
        this.setDescription();
    }

    public MapSquare(Geographies map, Monster.MonsterList enemy, Armor.ArmorList armor){
        this.enemy = enemy;
        loot = new Armor(armor);
        loc = map;
        this.setDescription();
    }

    public MapSquare(Geographies map, Monster.MonsterList enemy){
        this.enemy = enemy;
        loc = map;
        this.setDescription();
    }

    public MapSquare(Geographies map, Weapon.WeaponList weapon){
        loc = map;
        loot = new Weapon(weapon);
        this.setDescription();
    }

    public MapSquare(Geographies map, Armor.ArmorList armor){
        loc = map;
        loot = new Armor(armor);
        this.setDescription();
    }

    public MapSquare(Geographies map){
        loc = map;
        this.setDescription();
    }

    public MapSquare(){
        loc = Geographies.PLAINS;
        this.setDescription();
    }

    public String toString(){
        return loc.toString().toLowerCase();
    }

    private void setDescription(){
        double r = Math.random();
        switch(loc){
            case WALLS:
                if(r < .33) {
                    description = "Just ahead looms a large stone wall, leading up to it the yard appears to be clear";
                } else if(r > .66) {
                    description = "At the other end of a brambly yard is a large stone wall with an ancient crack down the middle";
                }else{
                    description = "Before a sheer wall is the ditch from what once may have been a moat, now it is mostly barren with the occasional mudcrab scurrying about";
                }
                break;
            case DESERT:
                if(r < .33) {
                    description = "The air grows arid and the ground shows little sign of green, a desert is a dangerous place";
                } else if(r > .66) {
                    description = "Just ahead for a moment you were certain you saw an oasis, but it vanished just as quickly as it seemed to appear";
                }else{
                    description = "The rolling sands and beating sun can seem almost gentle at times, or perhaps thats the early stages of heatstroke";
                }
                break;
            case FOREST:
                if(r < .33) {
                    description = "Tall trees provide a canopy from the sun above, the forest welcomes you in as a host greets you to their home";
                } else if(r > .66) {
                    description = "Every which way life scurries about in and around the trees";
                }else{
                    description = "An unusually large oak looms before you, the gravity of the world seems to emanate from its mass";
                }
                break;
            case PLAINS:
                if(r < .33) {
                    description = "Grassy plains stretch outward and you feel a sudden sensation to run through them";
                } else if(r > .66) {
                    description = "Ahead is a hill with a lone tree growing with no company for several leagues,\nas you approach the tree you feel like an emissary from a far away land";
                }else{
                    description = "A cool breeze sweeps across the plains before you, as it rustles through the grass and weeds an enormous sense of calms washes over you";
                }
                break;
        }
    }

    public void location(Hero hero){
        System.out.println(description);

        if(enemy != null){
            Adventure.battle(hero, enemy);
        }
        if(loot != null){
            Scanner sc = new Scanner(System.in);
            String sel;
            boolean searchChoice = false;
            switch(loc){
                case PLAINS: System.out.println("There appears to be something hidden ahead in the brush");
                case FOREST: System.out.println("Nestled amongst some tree roots you see something hidden");
                case DESERT: System.out.println("You're pretty sure you see something protruding form the sand");
                case WALLS: System.out.println("Is that something other than stone at the base of wall");
            }
            System.out.println("Do you want to investigate?");
            sel = sc.nextLine();
            while(!searchChoice) {
                if (sel.toUpperCase().contains("Y") || sel.toUpperCase().contains("DO ")) {
                    System.out.println("Upon further investigation you find a " + loot.toString());
                    boolean equipLoot = false;
                    //ALter to take boolean of whether to equip
                    if(hero.compareEquip(loot)){
                        if(loot.getClass() == Weapon.class){
                            hero.equipWeapon((Weapon)loot);
                        }else {
                            hero.equipArmor((Armor)loot);
                        }
                        System.out.println("You equip the " + loot.toString());
                    }

                    loot = null;
                    searchChoice = true;
                } else if (sel.toUpperCase().contains("N") || sel.toUpperCase().contains("DON")) {
                    searchChoice = true;
                    System.out.println("You skirt around and move on\n");
                } else {
                    System.out.println("Sorry I didn't get that, do you want to investigate Y, N?\n");
                    sel = sc.nextLine();
                }
            }
        }

        System.out.println("Looking around there doesn't appear to be anything else in the area\nand so you press onward\n");
    }

}
