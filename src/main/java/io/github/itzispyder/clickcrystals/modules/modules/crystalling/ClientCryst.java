package io.github.itzispyder.clickcrystals.modules.modules.crystalling;

import io.github.itzispyder.clickcrystals.events.EventHandler;
import io.github.itzispyder.clickcrystals.events.Listener;
import io.github.itzispyder.clickcrystals.events.events.client.PlayerAttackEntityEvent;
import io.github.itzispyder.clickcrystals.modules.Categories;
import io.github.itzispyder.clickcrystals.modules.Module;
import io.github.itzispyder.clickcrystals.util.InteractionUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;

public class ClientCryst extends Module implements Listener {

    public ClientCryst() {
        super("client-crystals", Categories.CRYSTALLING, "Removes crystals client-side the moment you punch them.");
    }

    @Override
    protected void onEnable() {
        system.addListener(this);
    }

    @Override
    protected void onDisable() {
        system.removeListener(this);
    }

    @EventHandler
    private void onPacketSend(PlayerAttackEntityEvent e) {
        final Entity ent = e.getEntity();

        if (ent == null) return;
        if (mc.player == null) return;
        if (mc.player.getWorld() == null) return;
        if (mc.isInSingleplayer()) return;
        if (!InteractionUtils.canBreakCrystals()) return;

        if (ent instanceof EndCrystalEntity crystal) {
            crystal.kill();
            crystal.remove(Entity.RemovalReason.KILLED);
            crystal.onRemoved();
        }
    }
}
