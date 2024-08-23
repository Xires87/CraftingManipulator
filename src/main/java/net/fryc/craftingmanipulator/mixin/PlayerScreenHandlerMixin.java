package net.fryc.craftingmanipulator.mixin;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fryc.craftingmanipulator.network.payloads.ResetDrawingPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerScreenHandler.class)
abstract class PlayerScreenHandlerMixin {

    @Shadow
    private @Final PlayerEntity owner;

    @Inject(method = "onContentChanged(Lnet/minecraft/inventory/Inventory;)V", at = @At("HEAD"))
    public void resetDrawings(Inventory inventory, CallbackInfo info) {
        if(!this.owner.getWorld().isClient()){
            ServerPlayNetworking.send(((ServerPlayerEntity) this.owner), new ResetDrawingPayload(true));
        }
    }
}
