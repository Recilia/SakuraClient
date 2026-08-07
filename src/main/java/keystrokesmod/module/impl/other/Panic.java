package keystrokesmod.module.impl.other;

import keystrokesmod.Sakura;
import keystrokesmod.module.Module;
import keystrokesmod.module.setting.impl.DescriptionSetting;
import java.util.ArrayList;
import java.util.List;

public class Panic extends Module {
    public Panic() {
        super("Panic", category.other);
        this.registerSetting(new DescriptionSetting("Disables all modules."));
    }

    @Override
    public void onEnable() {
        List<Module> modulesToDisable = new ArrayList<>();
        for (Module m : Sakura.getModuleManager().getModules()) {
            if (m.isEnabled()) {
                modulesToDisable.add(m);
            }
        }
        for (Module m : modulesToDisable) {
            m.disable();

        }
        this.disable();
    }
}
