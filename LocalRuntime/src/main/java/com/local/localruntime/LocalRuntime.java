package com.local.localruntime;

import org.firstinspires.ftc.teamcode.mocks.MockRegistry;
import org.firstinspires.ftc.teamcode.mocks.MockOp;
import org.firstinspires.ftc.teamcode.mocks.OpModeMock;

import java.util.List;

public class LocalRuntime {

    public static void run(Class<?>[] mockOps, int choice) {
        for (var m : mockOps) {
            try {
                m.getMethod("Register").invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Class<?>> mocks = MockRegistry.getMocks();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("\nAvailable Mock Ops:");
        for (int i = 0; i < mocks.size(); i++) {
            Class<?> cls = mocks.get(i);
            MockOp annotation = cls.getAnnotation(MockOp.class);
            String name = annotation != null ? annotation.name() : cls.getSimpleName();
            System.out.printf("[%d] %s%n", i, name);
        }

        if (choice < 0 || choice >= mocks.size()) {
            System.out.println("Invalid choice: " + choice);
            return;
        }

        System.out.println("\nChosen option: " + choice);

        try {
            Class<?> selected = mocks.get(choice);
            System.out.println("Running " + selected.getSimpleName() + "...\n");
            System.out.println("========= STDOUT =========");
            OpModeMock opInstance = (OpModeMock) selected.getDeclaredConstructor().newInstance();
            System.out.println("==========================\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        run(new Class<?>[]{  }, 0);
    }
}
