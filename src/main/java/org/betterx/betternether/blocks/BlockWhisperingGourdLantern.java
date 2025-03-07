package org.betterx.betternether.blocks;

import org.betterx.bclib.interfaces.tools.AddMineableAxe;
import org.betterx.betternether.BlocksHelper;
import org.betterx.betternether.blocks.materials.Materials;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;

public class BlockWhisperingGourdLantern extends Block implements AddMineableAxe {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BlockWhisperingGourdLantern() {
        super(Materials.makeNetherWood(MapColor.COLOR_BLUE).lightLevel(s -> 15));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return BlocksHelper.rotateHorizontal(state, rotation, FACING);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return BlocksHelper.mirrorHorizontal(state, mirror, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
}
