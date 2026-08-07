package keystrokesmod.module.impl.client.discordrpc;

import keystrokesmod.module.impl.client.DiscordRpc;
import keystrokesmod.module.setting.impl.SubMode;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.util.Objects;

public class SakuraXdRPC extends SubMode<DiscordRpc> {
    private final String clientId = "1040928675182424134";
    private boolean started;
    private long startTimestamp;
    private String lastServer;
    private String lastPlayer;

    public SakuraXdRPC(String name, DiscordRpc parent) {
        super(name, parent);
    }

    @Override
    public void onEnable() {
        startTimestamp = System.currentTimeMillis();
        DiscordRPC.discordInitialize(clientId, new DiscordEventHandlers.Builder().setReadyEventHandler(user -> updatePresence()).build(), true);
        new Thread(() -> {
            while (this.isEnabled()) {
                DiscordRPC.discordRunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Discord RPC Callback").start();
        started = true;
    }

    @Override
    public void onUpdate() {
        if (started) {
            String server = getServer();
            String player = getPlayerName();
            if (!Objects.equals(server, lastServer) || !Objects.equals(player, lastPlayer)) {
                updatePresence();
            }
        }
    }

    @Override
    public void onDisable() {
        DiscordRPC.discordShutdown();
        started = false;
    }

    private String getServer() {
        return mc.getCurrentServerData() != null ? mc.getCurrentServerData().serverIP : null;
    }

    private String getPlayerName() {
        return mc.thePlayer != null ? mc.thePlayer.getName() : null;
    }

    private void updatePresence() {
        String server = getServer();
        String player = getPlayerName();
        lastServer = server;
        lastPlayer = player;
        DiscordRichPresence presence = new DiscordRichPresence.Builder("Cheating my ass off!! >.<")
                .setDetails(server != null ? "Playing " + server : "For Minecraft 1.8.9")
                .setStartTimestamps(startTimestamp)
                .setBigImage("image_2026-08-07_142439041", "Made by rein github.com/Recilia")
                .setSmallImage("4539817", player != null ? "IGN: " + player + " >.<" : "IGN >.<")
                .build();
        DiscordRPC.discordUpdatePresence(presence);
    }
}
