package github.erb3.fabric.cactusfix.mixin;

import github.erb3.fabric.cactusfix.Cactusfix;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(CactusBlock.class)
public class CactusBlockPlacementServerMixin {
    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void onCanPlaceAt(BlockState state, WorldView worldView, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!(worldView instanceof ServerWorld)) {
            return;
        }

        if (!((ServerWorld) worldView).getGameRules().getBoolean(Cactusfix.ALLOW_FREER_CACTUS_PLACING)) {
            return;
        }

        BlockState blockState2 = worldView.getBlockState(pos.down());
        cir.setReturnValue((blockState2.isOf(Blocks.CACTUS) || blockState2.isIn(BlockTags.SAND)));
    }
}
