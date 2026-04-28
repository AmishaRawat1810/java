package com.tw.domain;
import com.tw.domain.mini_combat.Character;
import com.tw.domain.mini_combat.Enemy;

class Main{
  public static void main(String[] args) {
    Character player = new Character("Sam", 100, 1, 10);
    Enemy[] enemies = {
      new Enemy("Goblin", 50, 1, 3),
      new Enemy("Orc", 80, 2, 5),
      new Enemy("Grimlock", 120, 3, 7)
    };

    player.displayStats();

    for(Enemy enemy : enemies) {
      System.out.println("\nA wild " + enemy.getName() + " appears !!!");
      System.out.println("You [ " + player.getName() + " ] have to defeat it.");

      while (player.getHp() > 0 && enemy.getHp() > 0) {
        int damage = player.attack(enemy);
        System.out.println("You hit " + enemy.getName() + " with " + damage);
        System.out.println(enemy.getName() + " HP : " + enemy.getHp());

        if (enemy.getHp() <= 0) {
          System.out.println(enemy.getName() + " defeated !");
          System.out.println(player.getName() + " hp boosts to " + player.heal());
          break;
        }

        int enemyDamage = enemy.getAttack();
        player.setHp(player.getHp() - enemyDamage);
        System.out.println(enemy.getName() + " hits you with " + enemyDamage);
        System.out.println("Your HP: " + player.getHp());
      }

      if (player.getHp() <= 0) {
        System.out.println("You Died !");
        return;
      }

      player.setLevel(player.getLevel() + 1);
      System.out.println("You leveled up ! Level : " + player.getLevel());
    }

    System.out.println("You defeated all enemies !");
  }
}