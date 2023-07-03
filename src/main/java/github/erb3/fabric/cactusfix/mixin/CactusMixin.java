package github.erb3.fabric.cactusfix.mixin;

import github.erb3.fabric.cactusfix.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CactusBlock.class)
public class CactusMixin {

    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!world.getGameRules().getBoolean(Main.SHOULD_CACTUS_DAMAGE_ITEMS)) {
            if (entity instanceof ItemEntity) {
                ci.cancel();
            }
        }

        if (!world.getGameRules().getBoolean(Main.SHOULD_CACTUS_DAMAGE_PLAYERS)) {
            if (entity instanceof PlayerEntity) {
                ci.cancel();
            }
        }
    }

    @Inject(method="canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void onCanPlaceAt(BlockState state, WorldView worldView, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!(worldView instanceof World world) || !world.getGameRules().getBoolean(Main.BETTER_CACTUS_PLACING)) {
            return;
        }

        BlockState blockState2 = world.getBlockState(pos.down());
        cir.setReturnValue((blockState2.isOf(Blocks.CACTUS) || blockState2.isIn(BlockTags.SAND)));
    }
}
