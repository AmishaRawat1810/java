class Branch {
  public static void main() {
    String searchMe = "peter piper picked a peck of pickled peppers";
    String substring = "peck";
    boolean foundIt = false;
    int max = searchMe.length() - substring.length();

    test: 
      for(int i = 0; i < max; i++) {
        int n = substring.length();
        int j = i;
        int k = 0;
        while ( n != 0) {
          if(searchMe.charAt(j++) != substring.charAt(k++)) {
            continue test;
          }
          n--;
        }
        foundIt = true;
        break test;
      }

    System.out.println(foundIt ? "Found it !" : "ha....its not here..");
  }
}