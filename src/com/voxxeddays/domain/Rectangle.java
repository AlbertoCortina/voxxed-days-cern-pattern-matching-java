package com.voxxeddays.domain;

public class Rectangle implements Shape {

  final double length;
  final double width;

  public Rectangle(double length, double width) {
    this.length = length;
    this.width = width;
  }

  public double length() {
    return length;
  }

  public double width() {
    return width;
  }
}
