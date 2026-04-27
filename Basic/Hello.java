class Hello {
  public static void main() {
    //VARIABLES AND TYPES
    byte byteMax = Byte.MAX_VALUE;
    byte byteMin = Byte.MIN_VALUE;
    short shortMax = Short.MAX_VALUE;
    short shortMin = Short.MIN_VALUE;
    int integer = Integer.MAX_VALUE;
    long longInteger = Long.MAX_VALUE;
    long num = 1324432312l;
    float floatNum = Float.MAX_VALUE;
    float num2 = 987654.8f;
    double num3 = 987654345678903456789.8d;
    char a = 'a';
    String name = "priyanka";
    boolean isClass = true;

    System.out.println("BYTES RANGE " + byteMax + "," + byteMin);
    System.out.println("SHORT RANGE " + shortMax + "," + shortMin);
    System.out.println("INTEGER " + integer);
    System.out.println("DOUBLE " + num3);
    System.out.println("CHAR " + a);
    System.out.println("BOOLEAN " + isClass);
    System.out.println("STRNG " + name);
    System.out.println("LONG " + longInteger + " eg: " + num);
    System.out.println("FLOAT " + floatNum + " eg: " + num2);
  }
}