package com.adventure.characters;

public abstract class Character {

    protected int healthPoints;   //HP
    protected int maxHP;
    protected int magicPoints;    //MP
    protected int maxMP;
    protected int strength;       //Governs attack
    protected int endurance;      //Governs defense
    protected int intelligence;   //Governs magic attack
    protected int willpower;      //Governs magic defense
    protected int speed;          //Governs turn order/frequency
    protected boolean defending = false;

    //Determines attack damage w/o weapon
    public abstract int[] attack();

    public int getSpeed(){
        return speed;
    }

    public int getHealthPoints(){
        return healthPoints;
    }
    public int getMagicPoints() { return magicPoints; }

}
