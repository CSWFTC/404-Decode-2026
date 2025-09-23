package org.firstinspires.ftc.teamcode.mocks;

public class OpModeMock {
    private boolean isInInitLoop = false;
    private boolean isInMainLoop = false;

    public OpModeMock() {
        this.init();
        this.isInInitLoop = true;
        while (this.isInInitLoop) {
            this.init_loop();
        }
        this.isInMainLoop = true;
        while (this.isInMainLoop) {
            this.start();
        }
        this.stop();
    }

    public void init() {
    }

    public void init_loop() {

    }

    public void start() {

    }

    public void loop() {
    }

    public void stop() {
    }

    public void pressPlay() {
        this.isInInitLoop = false;
    }

    public void pressStop() {
        this.isInMainLoop = false;
    }
}
