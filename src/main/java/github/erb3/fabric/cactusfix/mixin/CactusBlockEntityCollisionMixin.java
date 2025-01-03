package github.erb3.fabric.cactusfix.mixin;

import github.erb3.fabric.cactusfix.Cactusfix;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(CactusBlock.class)
public class CactusBlockEntityCollisionMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if ((world instanceof ServerWorld serverWorld)) {
            if (!serverWorld.getGameRules().getBoolean(Cactusfix.SHOULD_CACTUS_DAMAGE_ITEMS)) {
                if (entity instanceof ItemEntity) {
                    ci.cancel();
                }
            }

            if (!serverWorld.getGameRules().getBoolean(Cactusfix.SHOULD_CACTUS_DAMAGE_PLAYERS)) {
                if (entity instanceof PlayerEntity) {
                    ci.cancel();
                }
            }
        }
    }
}
