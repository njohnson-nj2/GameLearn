package com.adventure.characters;

public class Monster extends Character {

    private int expEarned;
    private int drop;
    private MonsterList type;

    public enum MonsterList {WOLF, COMMON_THIEF, LESSER_DEMON, TOWN_GUARD, BRUTE_DEMON, SURTER}

    public Monster(MonsterList name, int level){
        type = name;
        float levelChange = 1 + ((level - 1) /10 );

        switch(name){
            case WOLF:
                healthPoints = 25;
                magicPoints = 0;
                strength = 6;       //Governs attack
                endurance = 5;      //Governs defense
                intelligence = 1;   //Governs magic attack
                willpower = 4;      //Governs magic defense
                speed = 7;
                expEarned = 15;
                drop = 10;
                break;
            case COMMON_THIEF:
                healthPoints = 22;
                magicPoints = 0;
                strength = 6;       //Governs attack
                endurance = 4;      //Governs defense
                intelligence = 4;   //Governs magic attack
                willpower = 2;      //Governs magic defense
                speed = 15;
                expEarned = 18;
                drop = 8;
                break;
            case LESSER_DEMON:
                healthPoints = 40;
                magicPoints = 10;
                strength = 14;       //Governs attack
                endurance = 7;      //Governs defense
                intelligence = 18;   //Governs magic attack
                willpower = 5;      //Governs magic defense
                speed = 10;
                expEarned = 25;
                drop = 15;
                break;
            case TOWN_GUARD:
                healthPoints = 50;
                magicPoints = 0;
                strength = 18;       //Governs attack
                endurance = 15;      //Governs defense
                intelligence = 10;   //Governs magic attack
                willpower = 15;      //Governs magic defense
                speed = 8;
                expEarned = 50;
                drop = 0;
                break;
            case BRUTE_DEMON:
                healthPoints = 70;
                magicPoints = 10;
                strength = 20;       //Governs attack
                endurance = 18;      //Governs defense
                intelligence = 20;   //Governs magic attack
                willpower = 18;      //Governs magic defense
                speed = 10;
                expEarned = 50;
                drop = 50;
                break;
            case SURTER:
                healthPoints = 150;
                magicPoints = 30;
                strength = 30;       //Governs attack
                endurance = 25;      //Governs defense
                intelligence = 30;   //Governs magic attack
                willpower = 25;      //Governs magic defense
                speed = 30;
                expEarned = 0;
                drop = 0;
                break;
            }
        if(type == MonsterList.WOLF || type == MonsterList.COMMON_THIEF){
            healthPoints = Math.round(healthPoints *= levelChange);
            magicPoints = Math.round(magicPoints *= levelChange);
            strength = Math.round(strength *= levelChange);
            endurance = Math.round(endurance *= levelChange);
            intelligence = Math.round(intelligence *= levelChange);
            willpower = Math.round(willpower *= levelChange);
            speed = Math.round(speed *= levelChange);
            expEarned = Math.round(expEarned *= levelChange);
            drop = Math.round(expEarned *= levelChange);
        } else if(type == MonsterList.LESSER_DEMON){
            healthPoints = Math.round(healthPoints *= levelChange);
            magicPoints = Math.round(magicPoints *= levelChange);
            speed = Math.round(speed *= levelChange);
        }
        maxHP = healthPoints;
        maxMP = magicPoints;
    }

    //
    public int[] attack(){
        int a = Math.round((float)Math.random()*100);
        int[] attackReturn = new int[2];
        switch(type){
            case LESSER_DEMON: case BRUTE_DEMON:
                if(a<51){
                    attackReturn = normalAttack();
                }else if(a<81){
                    attackReturn = magicAttack();
                }else if(a<91){
                    return defend();
                }else{
                    attackReturn = powerAttack();
                }
                break;
            case WOLF:
                if(a<86){
                    attackReturn = normalAttack();
                }else{
                    attackReturn = powerAttack();
                }
                break;
            case COMMON_THIEF: case TOWN_GUARD:
                if(a<71){
                    attackReturn = normalAttack();
                }else if(a<91){
                    attackReturn = defend();
                }else{
                    attackReturn = powerAttack();
                }
                break;
            case SURTER:
                if(a<21){
                    attackReturn = surterAttack();              //Normal Attack 20%
                }else if (a<51){
                    attackReturn = surterPower();               //Power Attack  30%
                } else if (a<91){
                    attackReturn = surterMagic();               //Magic Attack  40%
                } else {
                    attackReturn = defend();                    //Defend        10%
                }
                break;
        }
        return attackReturn;
    }

    private int[] normalAttack(){
        int[] attackReturn = {1,Math.round((float)strength * (((float)Math.random()/(float)2.5) + (float)0.8))};
        System.out.println(this.toString() + " attacks");
        return attackReturn;
    }

    private int[] powerAttack(){
        int[] attackReturn = {1,Math.round((float)strength * (float)1.5 * (((float)Math.random()/(float)2.5) + (float)0.8))};
        System.out.println(this.toString() + " lunges forward for a power attack!");
        return attackReturn;
    }

    private int[] defend(){
        int[] defendReturn = {3,0};
        healthPoints += Math.round(maxHP * (float)0.10);
        if(healthPoints > maxHP)healthPoints = maxHP;
        defending = true;
        System.out.println(this.toString() + " takes a defensive stance\n");
        return defendReturn;
    }

    private int[] magicAttack(){
        int[] magicReturn = {2, Math.round(((float)intelligence) * (((float)Math.random()/(float)2.5) + (float)0.8))};
        System.out.println(this.toString() + " points and launches a magical beam!");
        return magicReturn;
    }

    private int[] surterAttack(){
        int[] attackReturn = {1,Math.round((float)strength * (((float)Math.random()/(float)2.5) + (float)0.8))};
        System.out.println("Surter smiles as he lunges forward with his sword making several quick slices");
        return attackReturn;
    }

    private int[] surterMagic(){
        int[] magicReturn = {2, Math.round(((float)intelligence) * (((float)Math.random()/(float)2.5) + (float)0.8))};
        int a = Math.round((float)Math.random()*100);
        if(a<34){
            System.out.println("Flames rise from Surter's nostrils just before he bellows a plume of fire at you!");
        }else if(a<67){
            System.out.println("Surter slams his staff into the ground as lightning begins to gather at the gem\n" +
                    "He waves the staff in an arc from which a line of lightning bursts towards you");
        }else{
            System.out.println("Surter brings his staff and sword together as an energy radiate from his staff surrounding his blade\n" +
                    "He then lashes a fearsome blow with his great sword imbued with magical energies");
        }
        return magicReturn;
    }

    private int[] surterPower(){
        int[] powerReturn = {1,Math.round((float)strength * (float)1.5 * (((float)Math.random()/(float)2.5) + (float)0.8))};
            System.out.println("Surter roars loudly and lashes out with first staff then blade in a wide arc!");
        return powerReturn;
    }

    //Unique take damage
    //Determines damage taken from attack unarmored and adjusts health
    public boolean takeDamage(int[] damage){
        int damageTaken = 0;

        if(damage[0] == 1){
            damageTaken = damage[1]  - endurance;
            if(damageTaken < 0) damageTaken = 0;
            System.out.println(type + " took " + damageTaken + " damage\n");
        }else if(damage[0] == 2){
            damageTaken = damage[1]  - willpower;
            if(damageTaken < 0) damageTaken = 0;
            System.out.println(type + " took " + damageTaken + " damage\n");
        }
        healthPoints -= damageTaken;
        return healthPoints > 0;
    }

    //Drop loot
    public int[] loot(){
        int[] drop = new int[2];
        drop[0] = expEarned;
        drop[1] = this.drop;
        return drop;
    }


    public String toString() {
        String monsterName = type.toString().toLowerCase();
        monsterName = monsterName.replaceAll("_"," ");
        monsterName = type.toString().substring(0,1) + monsterName.substring(1);
        return monsterName;
    }



}
