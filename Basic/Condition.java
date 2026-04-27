class Condition{
  static boolean isEven(int value) {
    return value%2 == 0;
  }
  private static int count = 1; 

  public static void main() {
    int[] numbers = {1,2,3,4};

    for(int i = 0; i < numbers.length; i++) {
      if (isEven(numbers[i])) {
        System.out.printf("Even: %d\n", numbers[i]);
      } else {
        System.out.printf("Odd: %d\n", numbers[i]);
      }
    }

    do{
      System.out.println("hello " + count);
    } while(++count < 11);

    for (int item : numbers) {
      System.out.printf("Element in numbers array: %d\n", item);
    }
  }
}
