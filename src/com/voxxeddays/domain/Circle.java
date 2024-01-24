package com.voxxeddays.domain;

public class Circle implements Shape {

  final double radius;

  public Circle(double radius) {
    this.radius = radius;
  }

  public double radius() {
    return radius;
  }
}
