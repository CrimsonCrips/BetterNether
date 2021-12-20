package paulevs.betternether.world.structures.plants;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import paulevs.betternether.BlocksHelper;
import paulevs.betternether.MHelper;
import paulevs.betternether.blocks.BlockProperties.TripleShape;
import paulevs.betternether.blocks.BlockWhisperingGourdVine;
import paulevs.betternether.registry.NetherBlocks;
import paulevs.betternether.world.structures.IStructure;

public class StructureWhisperingGourd implements IStructure {
	@Override
	public void generate(ServerLevelAccessor world, BlockPos pos, Random random, final int MAX_HEIGHT) {
		if (pos.getY() < (MAX_HEIGHT-38) || !BlocksHelper.isNetherrack(world.getBlockState(pos.above()))) return;
		MutableBlockPos blockPos = new MutableBlockPos();

		int h = BlocksHelper.downRay(world, pos, 4);
		if (h < 1)
			return;
		h = MHelper.randRange(1, h, random);

		BlockState bottom = NetherBlocks.WHISPERING_GOURD_VINE.defaultBlockState().setValue(BlockWhisperingGourdVine.SHAPE, TripleShape.BOTTOM);
		BlockState middle = NetherBlocks.WHISPERING_GOURD_VINE.defaultBlockState().setValue(BlockWhisperingGourdVine.SHAPE, TripleShape.MIDDLE);
		BlockState top = NetherBlocks.WHISPERING_GOURD_VINE.defaultBlockState().setValue(BlockWhisperingGourdVine.SHAPE, TripleShape.TOP);

		blockPos.set(pos);
		for (int y = 0; y < h - 1; y++) {
			blockPos.setY(pos.getY() - y);
			BlocksHelper.setWithUpdate(world, blockPos, random.nextBoolean() ? top : middle);
		}
		BlocksHelper.setWithUpdate(world, blockPos.below(), bottom);
	}
}