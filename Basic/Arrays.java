class Arrays {
  static <T> void displayArray(T[] array) {
    for (T item : array) {
      System.out.println(item);
    }
  }

  public static void main() {
    Integer[] arrayOfInts = { 32, 87, 3, 589, 12, 1076, 2000, 8, 622, 127 };
    int searchfor = 12;
    boolean found = false;

    for (int item : arrayOfInts) {
      if (item == searchfor) {
        found = true;
        break;
      }
    }

    if (found) {
      System.out.printf("Found %d in array\n", searchfor);
    } else {
      System.out.printf("Count not find %d in array\n", searchfor);
    }

    String[] copyFrom = {
      "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",   
      "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",      
      "Marocchino", "Ristretto"
    };

    String[] copyTo = new String[7];

    //copyfrom, from where in copyfrom, to which , start position to fill in , end postiion to fill till
    System.arraycopy(copyFrom, 1, copyTo, 0, 7);
    displayArray(copyTo);
  }
}
