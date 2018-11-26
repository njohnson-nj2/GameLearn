package com.adventure.equip;

public class Weapon extends Equipment {

    private WeaponList type;
    private int damage;
    private int magicMod;
    private int defMod;
    private int cost = 10;

    public enum WeaponList {RUSTY_SWORD, KNOLLED_STAFF, RUSTY_SHIELD,STEEL_SWORD, MAGES_STAFF, STEEL_SHIELD}

    //Hashmap to get names for each of the weaponlists?


    //Some sort of comparator to sort a collection of the items for recommending best equip???

    public Weapon(WeaponList name){
        type = name;
        switch(name){
            case RUSTY_SWORD:
                damage = 6;
                magicMod = 0;
                defMod = 0;
                break;
            case STEEL_SWORD:
                damage = 8;
                magicMod = 0;
                defMod = 0;
                cost = 80;
                break;
            case KNOLLED_STAFF:
                damage = 2;
                magicMod = 5;
                defMod = 0;
                break;
            case MAGES_STAFF:
                damage = 3;
                magicMod = 9;
                defMod = 0;
                cost = 80;
                break;
            case RUSTY_SHIELD:
                damage = 3;
                magicMod = 0;
                defMod = 3;
                break;
            case STEEL_SHIELD:
                damage = 5;
                magicMod = 0;
                defMod = 5;
                cost = 80;
                break;
        }
    }

    public String toString() {
        String weaponName = type.toString().toLowerCase();
        weaponName = weaponName.replaceAll("_"," ");
        weaponName = type.toString().substring(0,1) + weaponName.substring(1);
        return weaponName;
    }

    public int getCost(){ return this.cost; }

    public int getDamage(){
        return this.damage;
    }

    public int getMagicMod(){
        return this.magicMod;
    }

    public int getDefMod(){
        return this.defMod;
    }

//    public void reforge(){
//
//    }

}
