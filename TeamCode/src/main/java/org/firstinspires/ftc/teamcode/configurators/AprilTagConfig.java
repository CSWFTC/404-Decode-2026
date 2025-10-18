package org.firstinspires.ftc.teamcode.configurators;

import com.rframeworks.di.Injectable;
import com.rframeworks.di.Singleton;
import com.rframeworks.eventbus.Event;
import com.rframeworks.eventbus.EventBus;
import com.rframeworks.eventbus.Subscribe;

@Injectable("AprilTagConfig")
@Singleton
public class AprilTagConfig {
    private static String aprilTagCode;

    public void registerConfig() {
        EventBus.getInstance().register(this);
    }

    public void setAprilTagCode(String code) {
        AprilTagConfig.aprilTagCode = code;
    }

    public String getAprilTagCode() {
        return AprilTagConfig.aprilTagCode;
    }

    @Subscribe(topic = "DDAprilTagData")
    public void onAprilTagEvent(Event<String> e) {
        AprilTagConfig.aprilTagCode = e.payload();

        System.out.println("Event: April Tag Code: "+ e.payload());
    }
}
