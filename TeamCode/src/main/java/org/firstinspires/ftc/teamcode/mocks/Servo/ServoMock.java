package org.firstinspires.ftc.teamcode.mocks.Servo;

public class ServoMock {
    public enum Direction {FORWARD, REVERSE};

    public double MIN_POSITION = 0.0;
    public double MAX_POSITION = 1.0;

    private double position;
    private Direction direction;

    public ServoControllerMock getController() {
        return null;
    }

    public int getPortNumber() {
        return 0;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public double getPosition() {
        return this.position;
    }

    public void scaleRange(double min, double max) {
        this.MIN_POSITION = min;
        this.MAX_POSITION = max;
    }
}
