package com.adventure.equip;

public class Armor extends Equipment {

    private ArmorList type;
    private int defMod;
    private int magicMod;
    private int speedMod;
    private int cost = 10;

    public enum ArmorList {RUSTY_ARMOR, ROBES, LIGHT_ARMOR, STEEL_ARMOR, MAGES_ROBES, SCALE_ARMOR}

    public Armor(ArmorList name){

        type = name;
        switch(name){
            case RUSTY_ARMOR:
                defMod = 6;
                magicMod = 2;
                speedMod = -3;
                break;
            case STEEL_ARMOR:
                defMod = 9;
                magicMod = 2;
                speedMod = -3;
                cost = 90;
                break;
            case ROBES:
                defMod = 1;
                magicMod = 5;
                speedMod = 3;
                break;
            case MAGES_ROBES:
                defMod = 2;
                magicMod = 8;
                speedMod = 3;
                cost = 90;
                break;
            case LIGHT_ARMOR:
                defMod = 3;
                magicMod = 1;
                speedMod = 1;
                break;
            case SCALE_ARMOR:
                defMod = 5;
                magicMod = 3;
                speedMod = 1;
                cost = 90;
                break;
        }
    }

    public String toString() {
        String armorName = type.toString().toLowerCase();
        armorName = armorName.replaceAll("_"," ");
        armorName = type.toString().substring(0,1) + armorName.substring(1);
        return armorName;
    }

    public int getCost(){ return this.cost; }

    public int getMagicMod(){
        return this.magicMod;
    }

    public int getDefMod(){
        return this.defMod;
    }

    public int getSpeedMod(){
        return this.speedMod;
    }

//    public void reforge(){
//
//    }

}
