package com.adventure.characters;

import com.adventure.equip.Armor;
import com.adventure.equip.Equipment;
import com.adventure.equip.Weapon;

import java.util.Scanner;

public class Hero extends Character {

    private String race;        //Determines starting stats+
    private SpecialList special;     //Special Ability
    private int experience = 0;     //Governs levelling
    private int level = 1;
    private int gold = 5;
    private Weapon weapon;
    private Armor armor;
    private boolean psych = false;

    public enum SpecialList {FLAME, BACKSTAB, PSYCH_UP}
    //Special Flame guaranteed hit, but costs magicka    1.4
    //Special Backstab variant hit, no cost             1.5
    //Special Psych up, guaranteed hit, turn cost       1.35

    //Create character
    public Hero(){
        Scanner sc = new Scanner(System.in);
        String sel;

        //Select Race
        System.out.println("Tell me more about yourself are you Human, Dwarven, or Elven?\n");

        while(race == null) {
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("ELVEN") || sel.toUpperCase().contains("ELF")) {
                race = "Elven";
                strength = 5;       //Low
                endurance = 8;      //Low
                intelligence = 15;   //High
                willpower = 15;      //High
                speed = 10;          //Medium
                System.out.println("Ah, welcome Elf\n");
            } else if (sel.toUpperCase().contains("DWARVEN") || sel.toUpperCase().contains("DWARF")) {
                race = "Dwarven";
                strength = 15;       //High
                endurance = 15;      //High
                intelligence = 5;   //Medium
                willpower = 10;      //Low
                speed = 6;          //Low
                System.out.println("Hail Dwarf\n");
            } else if (sel.toUpperCase().contains("HUMAN") || sel.toUpperCase().contains("HUME")) {
                race = "Human";
                strength = 11;       //Medium
                endurance = 11;      //Medium
                intelligence = 11;   //Medium
                willpower = 11;      //Medium
                speed = 11;          //Medium
                System.out.println("Greetings Human\n");
            } else {
                System.out.println("Sorry I didn't get that: Human, Dwarven, or Elven?\n");
            }
        }

        //Select Stat
        System.out.println("What of your traits? Do you focus on Strength, Endurance, Intelligence, Willpower, or Speed?\n");
        while(maxHP <= 0){
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("STRENGTH") || sel.toUpperCase().contains("STR")) {
                strength += 5;
                maxHP = 5 +strength + endurance;
                healthPoints = maxHP;
                maxMP = intelligence + willpower;
                magicPoints = maxMP;
                System.out.println("Strength will server you well on your journey warrior\n");
            } else if (sel.toUpperCase().contains("ENDURANCE") || sel.toUpperCase().contains("END")) {
                endurance += 5;
                maxHP = 5 +strength + endurance;
                healthPoints = maxHP;
                maxMP = intelligence + willpower;
                magicPoints = maxMP;
                System.out.println("Your Endurance will see you through any number of trials\n");
            } else if (sel.toUpperCase().contains("INTELLIGENCE") || sel.toUpperCase().contains("INT")) {
                intelligence += 5;
                maxHP = 5 +strength + endurance;
                healthPoints = maxHP;
                maxMP = intelligence + willpower;
                magicPoints = maxMP;
                System.out.println("Intelligence is a great ally when faced with uncertainty\n");
            } else if (sel.toUpperCase().contains("WILLPOWER") || sel.toUpperCase().contains("WILL")) {
                willpower += 5;
                maxHP = 5 +strength + endurance;
                healthPoints = maxHP;
                maxMP = intelligence + willpower;
                magicPoints = maxMP;
                System.out.println("Your Willpower will make your ambitions real\n");
            } else if (sel.toUpperCase().contains("SPEED") || sel.toUpperCase().contains("SPD")) {
                speed += 5;
                maxHP = 5 +strength + endurance;
                healthPoints = maxHP;
                maxMP = intelligence + willpower;
                magicPoints = maxMP;
                System.out.println("Speed will ensure you get there first\n");
            } else {
                System.out.println("Sorry I didn't get that: Strength, Endurance, Intelligence, Willpower, or Speed?\n");
            }
        }
        //Select Special
        System.out.println("What of your abilities; do you Focus your attacks, control Magic, or Evade enemies to strike them from behind?\n");

        while(special == null){
            sel = sc.nextLine();
            if (sel.toUpperCase().contains("FOCUS") || sel.toUpperCase().contains("ATTACK")) {
                special = SpecialList.PSYCH_UP;
                System.out.println("Any foe would tremble before a focused attack\n");
            } else if (sel.toUpperCase().contains("MAGIC") || sel.toUpperCase().contains("SPELL")) {
                special = SpecialList.FLAME;
                System.out.println("Even a simple flame spell can be devastating\n");
            } else if (sel.toUpperCase().contains("STRIKE") || sel.toUpperCase().contains("BEHIND") || sel.toUpperCase().contains("EVADE")) {
                special = SpecialList.BACKSTAB;
                System.out.println("A clever fighter attacks an enemy when they aren't prepared for it\n");
            } else {
                System.out.println("Sorry I didn't get that: Focus, Magic, or Evade?\n");
            }
        }
    }

    //Getters & Setters
    public String getSpecial() {
        String specialString = special.toString().toLowerCase();
        specialString = specialString.replaceAll("_"," ");
        specialString = special.toString().substring(0,1) + specialString.substring(1);
        return specialString;
    }

    public int getLevel(){ return level; }

    public int getGold(){ return gold; }
    public void purchase(int amt){ gold -= amt; }

    //BASIC ATTACK AND DAMAGE
    //Determines attack damage
    public int[] attack(){
        int damage = Math.round(((float)weapon.getDamage() + (float)strength) * (((float)Math.random()/(float)2.5) + (float)0.8));
        if(psych){
            damage = Math.round(damage * (float)2.5);
            psych = false;
        }
        int[] attackReturn = {1,damage};
        System.out.println("Hero attacks");  //BEST WAY TO DO THIS???
        return attackReturn;
    }

    public void defend(){
        healthPoints += Math.round(maxHP * (float)0.10);
        if(healthPoints > maxHP)healthPoints = maxHP;
        defending = true;
        System.out.println("Hero takes a defensive stance\n");
    }

    //Determines damage taken from attack and adjusts health
    public void takeDamage(int[] damage){
        int damageTaken = 0;

        if(damage[0] == 1){
            damageTaken = damage[1] - armor.getDefMod() - endurance - weapon.getDefMod();
            if(defending) {
                damageTaken = Math.round(damageTaken/2);
                defending = false;
            }
            if(damageTaken<0) damageTaken = 0;
            System.out.println("Hero took " + damageTaken + " damage\n");
        }else if(damage[0] == 2){
            damageTaken = damage[1] - armor.getMagicMod() - willpower;
            if(defending) {
                damageTaken = Math.round(damageTaken/2);
                defending = false;
            }
            if(damageTaken<0) damageTaken = 0;
            System.out.println("Hero took " + damageTaken + " damage\n");
        }
        healthPoints -= damageTaken;
        if(healthPoints <= 0){
            System.out.println("Hero died!\n\nGAME OVER");
            System.exit(0);
        }
    }

    //SPECIAL ATTACKS
    public int[] special(){
        int[] specialDamage = new int[2];
        switch(special){
            case FLAME:
                specialDamage = flameSpell();
                break;
            case BACKSTAB:
                specialDamage = backStab();
                break;
            case PSYCH_UP:
                specialDamage = psychUp();
                break;
        }
        return specialDamage;
    }

    private int[] flameSpell(){

        int[] damageDone = new int[2];
        if(this.magicPoints > 9){
            int damage = Math.round(((float)weapon.getMagicMod() + (float)intelligence * (float)1.15) * (((float)Math.random()/(float)2.5) + (float)0.8));
            damageDone[0] = 2;
            damageDone[1] = damage;
            System.out.println("Hero casts flame!");
            magicPoints -= 10;
        }else{
            damageDone[0] = 3;
            System.out.println("Hero does not have enough magic to cast flame!");
        }
        return damageDone;
    }

    private int[] backStab(){

        int[] damageDone = new int[2];
        int damage = 0;

        if((Math.round((float)Math.random()*100) + speed)>60){
            damage = Math.round((((float)1.5 * (float)weapon.getDamage() + (float)strength) * (((float)Math.random()/(float)2.5) + (float)0.8)));
            System.out.println("Hero evades enemy and performs back stab");
        }else{
            System.out.println("Hero fails to evade enemy and can't preform back stab");
        }
        damageDone[0] = 1;
        damageDone[1] = damage;

        return damageDone;
    }

    private int[] psychUp(){
        System.out.println("Hero prepares for a power attack\n");
        psych = true;
        int[] damageDone = {3,0};
        return damageDone;
    }

    //Equips
    public boolean compareEquip(Equipment comp){
        if(comp.getClass() == Weapon.class){
            return compareEquip((Weapon)comp);
        }else {
            return compareEquip((Armor)comp);
        }
    }

    public boolean compareEquip(Weapon comp){
        Scanner sc = new Scanner(System.in);
        String sel;
        boolean weaponChoice = false;

        System.out.println("Available: " + comp.toString());
        System.out.println("Weapon    | Damage | Magic | Defense");
        if(weapon.getDamage() > 9 && weapon.getMagicMod() > 9){
            System.out.println("Equipped  |   " + weapon.getDamage() + "   |   " + weapon.getMagicMod() + "  |  " + weapon.getDefMod());
        } else if(weapon.getDamage() > 9){
            System.out.println("Equipped  |   " + weapon.getDamage() + "   |   " + weapon.getMagicMod() + "   |  " + weapon.getDefMod());
        } else if(weapon.getMagicMod() > 9){
            System.out.println("Equipped  |   " + weapon.getDamage() + "    |   " + weapon.getMagicMod() + "  |  " + weapon.getDefMod());
        } else {
            System.out.println("Equipped  |   " + weapon.getDamage() + "    |   " + weapon.getMagicMod() + "   |  " + weapon.getDefMod());
        }
        if(comp.getDamage() > 9 && comp.getMagicMod() > 9){
            System.out.println("Available |   " + comp.getDamage() + "   |   " + comp.getMagicMod() + "  |  " + comp.getDefMod());
        } else if(comp.getDamage() > 9){
            System.out.println("Available |   " + comp.getDamage() + "   |   " + comp.getMagicMod() + "   |  " + comp.getDefMod());
        } else if(comp.getMagicMod() > 9){
            System.out.println("Available |   " + comp.getDamage() + "    |   " + comp.getMagicMod() + "  |  " + comp.getDefMod());
        } else {
            System.out.println("Available |   " + comp.getDamage() + "    |   " + comp.getMagicMod() + "   |  " + comp.getDefMod());
        }
        System.out.println("\nDo you want to equip New " + comp.toString() + " or Keep equipped " + weapon.toString() + "?");
        sel = sc.nextLine();
        while(!weaponChoice){
            if (sel.toUpperCase().contains("KEEP") || sel.toUpperCase().contains("EQUIPPED") || sel.toUpperCase().contains(weapon.toString().toUpperCase())) {
                weaponChoice = true;
                System.out.println("You do not take the " + comp.toString());
                return false;
            } else if (sel.toUpperCase().contains("NEW") || sel.toUpperCase().contains("EQUIP") || sel.toUpperCase().contains(comp.toString().toUpperCase())) {
                weaponChoice = true;
                return true;
            } else {
                System.out.println("Sorry I didn't get that: Equipped or Available?\n");
                sel = sc.nextLine();
            }
        }
        return false;
    }

    public boolean compareEquip(Armor comp){

        Scanner sc = new Scanner(System.in);
        String sel;
        boolean armorChoice = false;

        System.out.println("Available: " + comp.toString());
        System.out.println("Armor     | Defense | Magic D | Speed");
        if(armor.getDefMod() > 9 && armor.getMagicMod() > 9){
            System.out.println("Equipped  |    " + armor.getDefMod() + "   |    " + armor.getMagicMod() + "   |  " + armor.getSpeedMod());
        } else if(armor.getDefMod() > 9){
            System.out.println("Equipped  |    " + armor.getDefMod() + "   |    " + armor.getMagicMod() + "    |  " + armor.getSpeedMod());
        } else if(armor.getMagicMod() > 9){
            System.out.println("Equipped  |    " + armor.getDefMod() + "    |    " + armor.getMagicMod() + "   |  " + armor.getSpeedMod());
        } else {
            System.out.println("Equipped  |    " + armor.getDefMod() + "    |    " + armor.getMagicMod() + "    |  " + armor.getSpeedMod());
        }
        if(comp.getDefMod() > 9 && comp.getMagicMod() > 9){
            System.out.println("Equipped  |    " + comp.getDefMod() + "   |    " + comp.getMagicMod() + "   |  " + comp.getSpeedMod());
        } else if(comp.getDefMod() > 9){
            System.out.println("Equipped  |    " + comp.getDefMod() + "   |    " + comp.getMagicMod() + "    |  " + comp.getSpeedMod());
        } else if(comp.getMagicMod() > 9){
            System.out.println("Equipped  |    " + comp.getDefMod() + "    |    " + comp.getMagicMod() + "   |  " + comp.getSpeedMod());
        } else {
            System.out.println("Equipped  |    " + comp.getDefMod() + "    |    " + comp.getMagicMod() + "    |  " + comp.getSpeedMod());
        }
        System.out.println("\nDo you want to equip New " + comp.toString() + " or Keep equipped " + armor.toString() + "?");
        sel = sc.nextLine();
        while(!armorChoice){
            if (sel.toUpperCase().contains("KEEP") || sel.toUpperCase().contains("EQUIPPED") || sel.toUpperCase().contains(armor.toString().toUpperCase())) {
                armorChoice = true;
                System.out.println("You do not take the " + comp.toString());
                return false;
            } else if (sel.toUpperCase().contains("NEW") || sel.toUpperCase().contains("EQUIP") || sel.toUpperCase().contains(comp.toString().toUpperCase())) {
                armorChoice = true;
                return true;
            } else {
                System.out.println("Sorry I didn't get that: Equipped or Available?\n");
                sel = sc.nextLine();
            }
        }
        return false;
    }

    public void loot(int[] drops){
        int expNeeded = level * 100;
        experience += drops[0];
        gold += drops[1];
        System.out.println("Hero gains " + drops[0] + " experience and " + drops[1] + " gold!");
        if(level < 5 && experience >= expNeeded){
            experience -= expNeeded;
            levelUp();
        }
    }

    private void levelUp(){
        System.out.println("Hero leveled up!!");
        //Stat increases: Need balancing!!!!--------------------------------------------------------------------------------------------------------------------
        float levelChange = 1 + ((level - (float).5) /10 );

        maxHP = Math.round(maxHP *= levelChange);
        maxMP = Math.round(maxMP *= levelChange);
        rest();
        strength = Math.round(strength *= levelChange);
        endurance = Math.round(endurance *= levelChange);
        intelligence = Math.round(intelligence *= levelChange);
        willpower = Math.round(willpower *= levelChange);
        speed = Math.round(speed *= levelChange);

        System.out.println("Hero raises to level " + (++level));
        System.out.println("HP increased to " + maxHP);
        System.out.println("MP increased to " + maxMP);
        System.out.println("Strength increased to " + strength);
        System.out.println("Endurance increased to " + endurance);
        System.out.println("Intelligence increased to " + intelligence);
        System.out.println("Willpower increased to " + willpower);
        System.out.println("Speed increased to " + speed);
    }

    public void equipWeapon(Weapon.WeaponList weapon){
        this.weapon = new Weapon(weapon);
    }
    public void equipWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void equipArmor(Armor.ArmorList armor){
        this.armor = new Armor(armor);
    }
    public void equipArmor(Armor armor){
        this.armor = armor;
    }

    public void rest(int gold){
        healthPoints = maxHP;
        magicPoints = maxMP;
        this.gold -= gold;
    }

    public void rest(){
        healthPoints = maxHP;
        magicPoints = maxMP;
    }



}
