package paulevs.betternether.structures.plants;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import paulevs.betternether.BlocksHelper;
import paulevs.betternether.blocks.BlockLumabusVine;
import paulevs.betternether.blocks.BlockProperties.TripleShape;
import paulevs.betternether.registry.NetherBlocks;
import paulevs.betternether.structures.IGrowableStructure;
import paulevs.betternether.structures.IStructure;

public class StructureLumabusVine implements IStructure, IGrowableStructure {
	@Override
	public void grow(ServerLevelAccessor world, BlockPos pos, Random random) {
		generate(world, pos, random, 128);
	}
	
	@Override
	public void generate(ServerLevelAccessor world, BlockPos pos, Random random, final int MAX_HEIGHT) {
		final float scale_factor = MAX_HEIGHT/128.0f;
		final int RANDOM_BOUND = (int)(19*scale_factor);
		
		int h = random.nextInt(RANDOM_BOUND) + 5;
		int h2 = BlocksHelper.downRay(world, pos, h);
		h2 -= 2;

		if (h2 < 3)
			return;

		BlockState vineState = NetherBlocks.LUMABUS_VINE.defaultBlockState().setValue(BlockLumabusVine.SHAPE, TripleShape.MIDDLE);

		BlocksHelper.setWithUpdate(world, pos, NetherBlocks.LUMABUS_VINE.defaultBlockState());

		for (int y = 1; y < h2; y++)
			BlocksHelper.setWithUpdate(world, pos.below(y), vineState);

		BlocksHelper.setWithUpdate(world, pos.below(h2), NetherBlocks.LUMABUS_VINE.defaultBlockState().setValue(BlockLumabusVine.SHAPE, TripleShape.BOTTOM));
	}
}