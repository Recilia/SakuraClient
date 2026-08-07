package keystrokesmod.utility;

import keystrokesmod.Sakura;
import keystrokesmod.utility.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class DebugInfoRenderer extends net.minecraft.client.gui.Gui {
    private static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent ev) {
        if (!Sakura.debugger || ev.phase != TickEvent.Phase.END || !Utils.nullCheck()) {
            return;
        }
        if (mc.currentScreen == null) {
            RenderUtils.renderBPS(true, true);
        }
    }
}
