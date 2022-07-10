package classes;

public class Car {
    private String color;
    private int maxSpeed;
    private int fuelLeft;

    public Car() {
        this.color = "Default color";
        this.maxSpeed = 0;
        this.fuelLeft = 0;
    }

    public Car(String color, int maxSpeed, int fuelLeft) {
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.fuelLeft = fuelLeft;

    }

    public void refuel(int volume, char c) {
        this.fuelLeft = Math.min(this.fuelLeft + volume, 50);
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", fuelLeft=" + fuelLeft +
                '}';
    }
}
