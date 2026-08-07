package keystrokesmod.module.impl.render;

import keystrokesmod.Sakura;
import keystrokesmod.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public final class CustomCape extends Module {
    private static ResourceLocation capeTexture;

    public CustomCape() {
        super("CustomCape", category.render);
        this.hiddenInGui = true;
        this.hidden = true;
        loadCape();
    }

    public static ResourceLocation getCape() {
        return capeTexture;
    }

    private static void loadCape() {
        try {
            InputStream stream = Sakura.class.getResourceAsStream("/assets/keystrokesmod/textures/capes/astolfo.png");
            if (stream != null) {
                BufferedImage bufferedImage = ImageIO.read(stream);
                capeTexture = Minecraft.getMinecraft().renderEngine.getDynamicTextureLocation("astolfo", new DynamicTexture(bufferedImage));
                stream.close();
            }
        } catch (Exception ignored) {
        }
    }
}
