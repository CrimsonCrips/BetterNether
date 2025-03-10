package org.betterx.betternether.registry.features.placed;

import org.betterx.bclib.api.v3.levelgen.features.BCLFeature;
import org.betterx.bclib.api.v3.levelgen.features.blockpredicates.BlockPredicates;
import org.betterx.bclib.api.v3.levelgen.features.config.TemplateFeatureConfig;
import org.betterx.bclib.api.v3.levelgen.features.features.TemplateFeature;
import org.betterx.betternether.BN;
import org.betterx.betternether.registry.features.configured.NetherTrees;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class NetherTreesPlaced {
    public static BCLFeature CRIMSON_GLOWING_TREE = NetherTrees
            .CRIMSON_GLOWING_TREE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(12)
            .isEmptyAndOnNetherGround()
            .build();

    public static BCLFeature CRIMSON_PINE = NetherTrees
            .CRIMSON_PINE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(12)
            .isEmptyAndOnNetherGround()
            .build();

    public static BCLFeature RUBEUS_TREE = NetherTrees
            .RUBEUS_TREE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(12)
            .isEmptyAndOnNetherGround()
            .build();

    public static BCLFeature MUSHROOM_FIR = NetherTrees
            .MUSHROOM_FIR
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(14)
            .isEmptyAndOnNetherGround()
            .build();

    public static final BCLFeature<RandomPatchFeature, RandomPatchConfiguration> STALAGNATE = NetherTrees
            .PATCH_STALAGNATE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(6)
            .onceEvery(7)
            .isEmptyAndOnNetherGround()
            .build();

    public static final BCLFeature<RandomPatchFeature, RandomPatchConfiguration> GIANT_MOLD = NetherTrees
            .PATCH_GIANT_MOLD
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(6)
            .onceEvery(5)
            .isEmptyAndOnNetherGround()
            .build();

    public static final BCLFeature<RandomPatchFeature, RandomPatchConfiguration> BIG_RED_MUSHROOM = NetherTrees
            .PATCH_BIG_RED_MUSHROOM
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(6)
            .onceEvery(2)
            .isEmptyAndOnNetherGround()
            .build();

    public static final BCLFeature<RandomPatchFeature, RandomPatchConfiguration> BIG_BROWN_MUSHROOM = NetherTrees
            .PATCH_BIG_BROWN_MUSHROOM
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(6)
            .onceEvery(2)
            .isEmptyAndOnNetherGround()
            .build();

    public static final BCLFeature<TemplateFeature<TemplateFeatureConfig>, TemplateFeatureConfig> OLD_RED_MUSHROOM = NetherTrees
            .OLD_RED_MUSHROOM
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(5)
            .onceEvery(3)
            .isEmptyAndOn(BlockPredicates.ONLY_MYCELIUM)
            .build();

    public static final BCLFeature<TemplateFeature<TemplateFeatureConfig>, TemplateFeatureConfig> OLD_BROWN_MUSHROOM = NetherTrees
            .OLD_BROWN_MUSHROOM
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(4)
            .onceEvery(3)
            .isEmptyAndOn(BlockPredicates.ONLY_MYCELIUM)
            .build();

    public static final BCLFeature<RandomPatchFeature, RandomPatchConfiguration> SOUL_LILY = NetherTrees
            .PATCH_SOUL_LILY
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(6)
            .isEmptyAndOn(BlockPredicates.ONLY_SOUL_GROUND)
            .build();

    public static BCLFeature WART_TREE = NetherTrees
            .WART_TREE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(14)
            .onceEvery(3)
            .isEmptyAndOn(BlockPredicates.ONLY_SOUL_GROUND)
            .build();

    public static BCLFeature WILLOW_TREE = NetherTrees
            .WILLOW_TREE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(14)
            .onceEvery(3)
            .isEmptyAndOnNetherGround()
            .build();

    public static BCLFeature OLD_WILLOW_TREE = NetherTrees
            .OLD_WILLOW_TREE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(14)
            .onceEvery(16)
            .isEmptyAndOnNetherGround()
            .build();

    public static BCLFeature SAKURA_TREE = NetherTrees
            .SAKURA_TREE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .betterNetherCeiling(5)
            .onceEvery(6)
            .isEmptyAndUnderNetherGround()
            .build();

    public static BCLFeature ANCHOR_TREE = NetherTrees
            .ANCHOR_TREE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .betterNetherCeiling(5)
            .isEmptyAndUnderNetherGround()
            .onceEvery(15)
            .build();

    public static BCLFeature ANCHOR_TREE_SPARSE = NetherTrees
            .ANCHOR_TREE
            .place(BN.id("anchor_tree_sparse"))
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .onlyInBiome()
            .count(1)
            .randomHeight4FromFloorCeil()
            .findSolidCeil(5)
            .isEmptyAndUnderNetherGround()
            .onceEvery(7)
            .build();

    public static BCLFeature ANCHOR_TREE_BRANCH = NetherTrees
            .ANCHOR_TREE_BRANCH
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .betterNetherCeiling(3)
            .isEmptyAndUnderNetherGround()
            .onceEvery(11)
            .build();

    public static BCLFeature ANCHOR_TREE_ROOT = NetherTrees
            .ANCHOR_TREE_ROOT
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .betterNetherCeiling(2)
            .onceEvery(5)
            .isEmptyAndUnderNetherGround()
            .build();

    public static final BCLFeature<TemplateFeature<TemplateFeatureConfig>, TemplateFeatureConfig> BIG_WARPED_TREE = NetherTrees
            .BIG_WARPED_TREE
            .place()
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .vanillaNetherGround(6)
            .onceEvery(2)
            .isEmptyAndOnNetherGround()
            .build();

    public static void ensureStaticInitialization() {
    }
}
