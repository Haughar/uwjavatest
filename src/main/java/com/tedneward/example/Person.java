package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int personCount = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }

  public Person(String n, int a) {
    this(n, a, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    personCount++;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be less than 0");
    }
    this.age = age;
  }

  public int getAge() {
    return this.age;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public double getSalary() {
    return this.salary;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  public int count() {
    return personCount;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Person)) {
      return false;
    }
    Person p = (Person) obj;
    return this.name == p.getName() && this.age == p.getAge();
  }

  @Override
  public int compareTo(Person p) {
    return Double.valueOf(p.getSalary()).compareTo(Double.valueOf(this.getSalary()));
  }

  static class AgeComparator implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
      int age1 = p1.getAge();
      int age2 = p2.getAge();

      if (age1 == age2)
        return 0;
      else if (age1 > age2)
        return 1;
      else
        return -1;
    }
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList family = new ArrayList<Person>();
    family.add(new Person("Ted", 41, 250000));
    family.add(new Person("Charlotte", 43, 150000));
    family.add(new Person("Michael", 22, 10000));
    family.add(new Person("Matthew", 15));
    return family;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
