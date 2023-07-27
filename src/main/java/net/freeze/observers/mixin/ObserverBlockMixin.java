package net.freeze.observers.mixin;

import net.freeze.observers.FreezeObservers;
import net.minecraft.block.BlockState;
import net.minecraft.block.ObserverBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ObserverBlock.class)
public class ObserverBlockMixin {
    @Inject(method = "getWeakRedstonePower", at = @At("RETURN"), cancellable = true)
    private void injected(BlockState state, BlockView world, BlockPos pos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(isObserverFrozen(world) ? 0 : cir.getReturnValue());
    }

    private boolean isObserverFrozen(BlockView world) {
        if (world instanceof World clientWorld) {
            return clientWorld.getGameRules().getBoolean(FreezeObservers.FREEZE_OBSERVERS);
        }
        else if (world.getClass() == ChunkRegion.class) {
            MinecraftServer server = ((ChunkRegion)world).getServer();
            return server != null && server.getGameRules().getBoolean(FreezeObservers.FREEZE_OBSERVERS);
        }

        return false;
    }
}