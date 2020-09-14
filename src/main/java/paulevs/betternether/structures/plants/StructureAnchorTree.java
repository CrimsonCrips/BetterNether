package paulevs.betternether.structures.plants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ServerWorldAccess;
import paulevs.betternether.BlocksHelper;
import paulevs.betternether.MHelper;
import paulevs.betternether.noise.OpenSimplexNoise;
import paulevs.betternether.registry.BlocksRegistry;
import paulevs.betternether.structures.IStructure;

public class StructureAnchorTree implements IStructure
{
	private static final OpenSimplexNoise NOISE = new OpenSimplexNoise(2145);
	private static final Set<BlockPos> BLOCKS = new HashSet<BlockPos>(2048);
	
	@Override
	public void generate(ServerWorldAccess world, BlockPos pos, Random random)
	{
		if (canGenerate(pos)) grow(world, pos, pos.down(BlocksHelper.downRay(world, pos, 255)), random);
	}
	
	private boolean canGenerate(BlockPos pos)
	{
		return (pos.getX() & 15) == 7 && (pos.getZ() & 15) == 7;
	}
	
	private void grow(ServerWorldAccess world, BlockPos up, BlockPos down, Random random)
	{
		if (up.getY() - down.getY() < 30) return;
		
		BlockPos trunkTop = lerp(down, up, 0.6);
		BlockPos trunkBottom = lerp(down, up, 0.3);
		
		int count = (trunkTop.getY() - trunkBottom.getY()) / 7;
		if (count < 2) count = 2;
		List<BlockPos> blocks = line(trunkBottom, trunkTop, count, random, 2.5);
		
		BLOCKS.clear();
		
		buildLine(blocks, 4);
		
		count = (up.getY() - down.getY()) / 10 - 1;
		if (count < 3) count = 3;
		buildBigCircle(trunkTop, 15, count, 2, random.nextDouble() * Math.PI * 2, 3.5, random);
		buildBigCircle(trunkBottom, -15, count, 2, random.nextDouble() * Math.PI * 2, 3.5, random);
		
		BlockState state;
		for (BlockPos bpos: BLOCKS)
		{
			if (bpos.getY() < 1 || bpos.getY() > 126) continue;
			if (!BlocksHelper.isNetherGround(state = world.getBlockState(bpos)) && !state.getMaterial().isReplaceable()) continue;
			if (BLOCKS.contains(bpos.up()) && BLOCKS.contains(bpos.down()))
				BlocksHelper.setWithoutUpdate(world, bpos, BlocksRegistry.ANCHOR_TREE.log.getDefaultState());
			else
				BlocksHelper.setWithoutUpdate(world, bpos, BlocksRegistry.ANCHOR_TREE.bark.getDefaultState());
		}
	}
	
	private void buildBigCircle(BlockPos pos, int length, int count, int iteration, double angle, double size, Random random)
	{
		if (iteration < 0) return;
		List<List<BlockPos>> lines = circleLinesEnds(pos, angle, count, length, Math.abs(length) * 0.7, random);
		double sizeSmall = size * 0.8;
		length *= 0.8;
		angle += Math.PI * 4 / count;
		angle += random.nextDouble() * angle * 0.75;
		for (List<BlockPos> line: lines)
		{
			buildLine(line, size);
			buildBigCircle(line.get(1), length, count, iteration - 1, angle, sizeSmall, random);
		}
	}
	
	private void buildLine(List<BlockPos> blocks, double radius)
	{
		for (int i = 0; i < blocks.size() - 1; i++)
		{
			BlockPos a = blocks.get(i);
			BlockPos b = blocks.get(i + 1);
			if (b.getY() < a.getY())
			{
				BlockPos c = b;
				b = a;
				a = c;
			}
			double max = b.getY() - a.getY();
			if (max < 1) max = 1;
			for (int y = a.getY(); y <= b.getY(); y++) cylinder(lerpCos(a, b, y, (y - a.getY()) / max), radius);
		}
	}
	
	private BlockPos lerp(BlockPos start, BlockPos end, double mix)
	{
		double x = MathHelper.lerp(mix, start.getX(), end.getX());
		double y = MathHelper.lerp(mix, start.getY(), end.getY());
		double z = MathHelper.lerp(mix, start.getZ(), end.getZ());
		return new BlockPos(x, y, z);
	}
	
	private BlockPos lerpCos(BlockPos start, BlockPos end, int y, double mix)
	{
		double v = lcos(mix);
		double x = MathHelper.lerp(v, start.getX(), end.getX());
		double z = MathHelper.lerp(v, start.getZ(), end.getZ());
		return new BlockPos(x, y, z);
	}
	
	private double lcos(double mix)
	{
		return MathHelper.clamp(0.5 - Math.cos(mix * Math.PI) * 0.5, 0, 1);
	}
	
	private List<BlockPos> line(BlockPos start, BlockPos end, int count, Random random, double range)
	{
		List<BlockPos> result = new ArrayList<BlockPos>(count);
		int max = count - 1;
		result.add(start);
		for (int i = 1; i < max; i++)
		{
			double delta = (double) i / max;
			double x = MathHelper.lerp(delta, start.getX(), end.getX()) + random.nextGaussian() * range;
			double y = MathHelper.lerp(delta, start.getY(), end.getY());
			double z = MathHelper.lerp(delta, start.getZ(), end.getZ()) + random.nextGaussian() * range;
			result.add(new BlockPos(x, y, z));
		}
		result.add(end);
		return result;
	}
	
	private void cylinder(BlockPos pos, double radius)
	{
		int x1 = MHelper.floor(pos.getX() - radius);
		int z1 = MHelper.floor(pos.getZ() - radius);
		int x2 = MHelper.floor(pos.getX() + radius + 1);
		int z2 = MHelper.floor(pos.getZ() + radius + 1);
		radius *= radius;
		
		for (int x = x1; x <= x2; x++)
		{
			int px2 = x - pos.getX();
			px2 *= px2;
			for (int z = z1; z <= z2; z++)
			{
				int pz2 = z - pos.getZ();
				pz2 *= pz2;
				if (px2 + pz2 <= radius * (NOISE.eval(x * 0.5, pos.getY() * 0.5, z * 0.5) * 0.25 + 0.75)) BLOCKS.add(new BlockPos(x, pos.getY(), z));
			}
		}
	}
	
	private List<List<BlockPos>> circleLinesEnds(BlockPos pos, double startAngle, int count, int height, double radius, Random random)
	{
		List<List<BlockPos>> result = new ArrayList<List<BlockPos>>(count);
		double angle = Math.PI * 2 / count;
		for (int i = 0; i < count; i++)
		{
			double x = pos.getX() + Math.sin(startAngle) * radius;
			double z = pos.getZ() + Math.cos(startAngle) * radius;
			BlockPos end = new BlockPos(x, pos.getY() + height + height * random.nextDouble() * 0.5, z);
			List<BlockPos> elem = new ArrayList<>(2);
			elem.add(pos);
			elem.add(end);
			result.add(elem);
			startAngle += angle;
		}
		return result;
	}
}
