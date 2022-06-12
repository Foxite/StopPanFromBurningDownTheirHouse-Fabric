package dev.foxite.fabric.stoppanfromburningdowntheirhouse.mixin;

import dev.foxite.fabric.stoppanfromburningdowntheirhouse.StopPanFromBurningDownTheirHouse;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.UUID;

@Mixin(FireBlock.class)
public class FireBlockMixin {
	@Inject(method = "scheduledTick",
			at = @At(
					value = "INVOKE",
					shift = At.Shift.AFTER,
					target = "Lnet/minecraft/server/world/ServerWorld;createAndScheduleBlockTick(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;I)V"
			),
			cancellable = true
	)
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
		var config = StopPanFromBurningDownTheirHouse.getInstance().getConfig().getConfig();

		for (UUID uuid : config.getTargetUuidsParsed()) {
			var player = world.getPlayerByUuid(uuid);
			if (player != null && player.squaredDistanceTo(pos.getX(), pos.getY(), pos.getZ()) < config.getSquaredRange()) {
				ci.cancel();
				return;
			}
		}
	}
}
