package comparables;

public class Person implements Comparable<Person> {
    String name;
    int age;
    double weight;

    public Person(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", weight=" + weight + " kgs]";
    }

    @Override
    public int compareTo(Person person) {
        return this.getAge() - person.getAge();
    }


    static void main() {
        Person alice = new Person("alice", 30, 50);
        Person sam = new Person("sam", 50, 80);
        System.out.println(alice.youngest(sam));
    }

    private Person youngest(Person person) {
        if (this.compareTo(person) > 0 ) return person;
        return this;
    }
}
