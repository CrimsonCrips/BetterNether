package org.betterx.betternether.blocks;

import org.betterx.bclib.behaviours.interfaces.BehaviourPlant;
import org.betterx.bclib.blocks.BasePlantBlock;
import org.betterx.bclib.util.LootUtil;
import org.betterx.betternether.blocks.materials.Materials;
import org.betterx.betternether.interfaces.SurvivesOnGrassSoil;
import org.betterx.betternether.interfaces.SurvivesOnNetherrackNyliumAndSculk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.Collections;
import java.util.List;

public class BlockNetherGrass extends BaseBlockNetherGrass implements SurvivesOnNetherrackNyliumAndSculk {

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurviveOnTop(level, pos);
    }

    @Override
    public boolean isTerrain(BlockState state) {
        return SurvivesOnNetherrackNyliumAndSculk.super.isTerrain(state);
    }
}

abstract class BaseBlockNetherGrass extends BasePlantBlock implements BehaviourPlant {
    private static final VoxelShape SHAPE = box(4, 0, 4, 14, 12, 14);

    public BaseBlockNetherGrass() {
        super(Materials.makeNetherGrass(MapColor.TERRACOTTA_GRAY).offsetType(Block.OffsetType.XZ));
    }

    @Environment(EnvType.CLIENT)
    public float getShadeBrightness(BlockState state, BlockGetter view, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ePos) {
        Vec3 vec3d = state.getOffset(view, pos);
        return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
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
        if (!canSurvive(state, world, pos))
            return Blocks.AIR.defaultBlockState();
        else
            return state;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        ItemStack tool = builder.getParameter(LootContextParams.TOOL);
        if (LootUtil.isCorrectTool(this, state, tool))
            return Collections.singletonList(new ItemStack(this.asItem()));
        else
            return super.getDrops(state, builder);
    }

    public static class OnEverything extends BaseBlockNetherGrass implements SurvivesOnGrassSoil {
        public OnEverything() {
            super();
        }

        @Override
        public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
            return canSurviveOnTop(level, pos);
        }

        @Override
        public boolean isTerrain(BlockState state) {
            return SurvivesOnGrassSoil.super.isTerrain(state);
        }
    }
}
