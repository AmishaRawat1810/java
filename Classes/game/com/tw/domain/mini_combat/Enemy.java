package com.tw.domain.mini_combat;

public class Enemy {
  private int hp;
  private int level;
  private String name;
  private int strength;

  public Enemy(String name, int hp, int level, int strength){
    this.hp = hp;
    this.name = name;
    this.level = level;
    this.strength = strength;
  }

  public String getName() {
    return name;
  }

  public int getHp() {
    return this.hp;
  }

  public void defend(int damage) {
    this.hp = Math.max(this.hp - damage, 0);
  }

  public int heal(int hp) {
    return this.hp = this.hp + hp;
  }

  public int getLevel() {
    return this.level;
  }

  public int getAttack() {
    return this.level + this.strength + (int) (Math.random() * 3);
  }
}