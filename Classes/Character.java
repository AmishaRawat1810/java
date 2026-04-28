class Character {
  private String name;
  private int hp;
  private int level;

  Character(String name, int hp, int level) {
    this.name = name;
    this.hp = hp;
    this.level = level;
  }

  void setHp(int hp) {
    this.hp = hp;
  }

  void setLevel(int level) {
    this.level = level;
  }

  int getHp() {
    return this.hp;
  }

  int getLevel() {
    return this.level;
  }

  String getName() {
    return this.name;
  }

  public static void main() {
    Character alice = new Character("alice", 100, 1);
    Combat combatManager = new Combat(alice);

    System.out.println(alice.getName());
    System.out.println(combatManager.heal(20));
    System.out.println(combatManager.levelUp(1));
  }
}