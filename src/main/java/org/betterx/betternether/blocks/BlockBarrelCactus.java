package org.betterx.betternether.blocks;

import org.betterx.bclib.behaviours.BehaviourBuilders;
import org.betterx.bclib.behaviours.interfaces.BehaviourPlant;
import org.betterx.bclib.interfaces.tools.AddMineableShears;
import org.betterx.betternether.MHelper;
import org.betterx.betternether.interfaces.SurvivesOnGravel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.google.common.collect.Lists;

import java.util.List;

public class BlockBarrelCactus extends BlockCommonPlant implements AddMineableShears, BehaviourPlant, SurvivesOnGravel {
    private static final VoxelShape EMPTY = Block.box(0, 0, 0, 0, 0, 0);
    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(5, 0, 5, 11, 5, 11),
            Block.box(3, 0, 3, 13, 9, 13),
            Block.box(2, 0, 2, 14, 12, 14),
            Block.box(1, 0, 1, 15, 14, 15)
    };

    public BlockBarrelCactus() {
        super(BehaviourBuilders
                .createCactus(MapColor.TERRACOTTA_ORANGE, false)
                .dynamicShape()
                .offsetType(Block.OffsetType.XZ)
        );
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canSurviveOnTop(world, pos);
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction facing,
            BlockState neighborState,
            LevelAccessor world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        if (canSurvive(state, world, pos))
            return state;
        else
            return Blocks.AIR.defaultBlockState();
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (state.getValue(BlockCommonPlant.AGE) > 1) entity.hurt(world.damageSources().cactus(), 1.0F);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        Vec3 vec3d = state.getOffset(view, pos);
        return SHAPES[state.getValue(BlockCommonPlant.AGE)].move(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        if (state.getValue(BlockCommonPlant.AGE) < 2) return EMPTY;
        Vec3 vec3d = state.getOffset(view, pos);
        return SHAPES[state.getValue(BlockCommonPlant.AGE)].move(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        if (state.getValue(BlockCommonPlant.AGE) == 3) {
            return Lists.newArrayList(new ItemStack(this, MHelper.randRange(1, 3, MHelper.RANDOM)));
        }
        return Lists.newArrayList(new ItemStack(this));
    }
}
