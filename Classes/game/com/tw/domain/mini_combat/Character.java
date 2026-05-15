package com.tw.domain.mini_combat;

public class Character {
  private String name;
  private int hp;
  private int level;
  private int strength;

  public Character(String name, int hp, int level, int strength) {
    this.name = name;
    this.hp = hp;
    this.level = level;
    this.strength = strength;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int heal() {
    return this.hp = this.hp + this.level * 10;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getHp() {
    return this.hp;
  }

  public int getLevel() {
    return this.level;
  }

  public String getName() {
    return this.name;
  }

  public void displayStats() {
    System.out.printf(
      "Stats: \n - Name : %s\n - HP : %d\n - Strength : %d\n - Level : %d\n", 
      this.name, this.hp, this.strength, this.level
    );
  }

  private int getBaseAttack() {
    return this.level + this.strength;
  }

  public int attack(Enemy enemy) {
    int baseDamage = getBaseAttack();
    int randomFactor = (int) Math.ceil(Math.random() * 2);
    int totalDamage = baseDamage + randomFactor;
    enemy.defend(totalDamage); 
    return totalDamage;
  }

}