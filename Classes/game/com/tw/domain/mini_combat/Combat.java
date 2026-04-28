class Combat {
  private Character character;

  Combat(Character c) {
    this.character = c;
  }

  int heal(int value) {
    int newHp = this.character.getHp() + value;
    character.setHp(newHp);
    return newHp;
  }

  int levelUp(int levelUpBy) {
    int newLevel = this.character.getLevel() + levelUpBy;
    character.setLevel(newLevel);
    return newLevel;
  }
}