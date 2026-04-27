class Combat {
  private Character character;

  Combat(Character c) {
    this.character = c;
  }

  int heal(int value) {
    int hp = this.character.getHp();
    return hp + value;
  }
}