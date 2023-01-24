package github.erb3.fabric.cactusfix.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CactusBlock.class)
public class CactusMixin {

    /**
     * @author Erb3
     * @reason Disable cactus damage to items
     */

    @Overwrite()
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity) {
            return;
        }

        entity.damage(DamageSource.CACTUS, 1.0F);
    }
}
