class Character {
  private String name;
  private int hp;
  private int level;

  Character(String name, int hp, int level) {
    this.name = name;
    this.hp = hp;
    this.level = level;
  }

  String getName() {
    return this.name;
  }

  int getHp() {
    return this.hp;
  }

  Object getStats() {
    return {
      name: this.name,
      hp: this.hp,
      level: this.level,
    }
  }

  public static void main() {
    Character alice = new Character("alice", 100, 1);
    Combat combatManager = new Combat(alice);

    System.out.println(alice.getName());
    System.out.println(combatManager.heal(20));
    System.out.println(alice);
  }
}