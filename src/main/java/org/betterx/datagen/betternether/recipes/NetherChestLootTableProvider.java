package org.betterx.datagen.betternether.recipes;

import org.betterx.bclib.items.complex.EquipmentSet;
import org.betterx.betternether.loot.BNLoot;
import org.betterx.betternether.registry.NetherBlocks;
import org.betterx.betternether.registry.NetherItems;
import org.betterx.betternether.registry.NetherTemplates;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import java.util.function.BiConsumer;

public class NetherChestLootTableProvider extends SimpleFabricLootTableProvider {

    public NetherChestLootTableProvider(
            FabricDataOutput output
    ) {
        super(output, LootContextParamSets.CHEST);
    }


    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(
                BNLoot.CITY_LOOT,
                LootTable.lootTable()
                         .withPool(simpleCityLoot())
                         .withPool(simpleOreLoot())
                         .withPool(simpleTemplates(1, 0.2f))
                         .withPool(netherFiller())
        );

        biConsumer.accept(
                BNLoot.CITY_LOOT_COMMON,
                LootTable.lootTable()
                         .withPool(simpleCityLoot())
                         .withPool(netherFiller())
                         .withPool(netherObsidian())
                         .withPool(simpleOreLoot().when(LootItemRandomChanceCondition.randomChance(0.4f)))
                         .withPool(simpleTemplates(1, 0.1f))
        );

        biConsumer.accept(
                BNLoot.CITY_LOOT_SURPRISE,
                LootTable.lootTable()
                         .withPool(simpleCityLoot())
                         .withPool(netherFiller())
                         .withPool(netherObsidian())
                         .withPool(simpleOreLoot().when(LootItemRandomChanceCondition.randomChance(0.4f)))
                         .withPool(surpriseItem())
        );

        biConsumer.accept(
                BNLoot.LIBRARY_LOOT,
                LootTable.lootTable()
                         .withPool(simpleCityLoot())
                         .withPool(enchantedTools())
                         .withPool(allTemplates())
                         .withPool(workstations())
                         .withPool(books())
                         .withPool(netherFiller())
        );

        biConsumer.accept(
                BNLoot.WITHER_TOWER_LOOT,
                LootTable.lootTable()
                         .withPool(netherFiller())
                         .withPool(simpleTemplates(3, 0.3f))
                         .withPool(bonusOreLoot())
        );


        biConsumer.accept(
                BNLoot.WITHER_TOWER_BONUS_LOOT,
                LootTable.lootTable()
                         .withPool(netherFiller())
                         .withPool(superBonusOreLoot())
        );

        biConsumer.accept(
                BNLoot.GHAST_HIVE,
                LootTable.lootTable()
                         .withPool(ghastHive())
        );
    }

    private LootPool.Builder surpriseItem() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem
                        .lootTableItem(NetherBlocks.NETHER_RUBY_BLOCK)
                        .setWeight(15)
                )
                .add(LootItem
                        .lootTableItem(NetherBlocks.NETHER_RUBY_BLOCK)
                        .setWeight(15)
                )
                .add(LootItem
                        .lootTableItem(Blocks.NETHERITE_BLOCK)
                        .setWeight(2)
                )
                .when(LootItemRandomChanceCondition.randomChance(0.1f));
    }

    private LootPool.Builder ghastHive() {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(8, 12))
                .add(LootItem
                        .lootTableItem(Items.DIAMOND)
                        .setWeight(40)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                )
                .add(LootItem
                        .lootTableItem(Blocks.SKELETON_SKULL)
                        .setWeight(20)
                )
                .add(LootItem
                        .lootTableItem(Blocks.WITHER_SKELETON_SKULL)
                        .setWeight(5)
                )
                .add(LootItem
                        .lootTableItem(Items.BROWN_MUSHROOM)
                        .setWeight(60)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
                .add(LootItem
                        .lootTableItem(Items.RED_MUSHROOM)
                        .setWeight(60)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
                .add(LootItem
                        .lootTableItem(Items.BONE)
                        .setWeight(60)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
                .add(LootItem
                        .lootTableItem(Items.QUARTZ)
                        .setWeight(50)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4)))
                )
                .add(LootItem
                        .lootTableItem(Items.GHAST_TEAR)
                        .setWeight(45)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                )
                .add(LootItem
                        .lootTableItem(Items.GOLD_INGOT)
                        .setWeight(55)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
                .add(LootItem
                        .lootTableItem(Items.GOLDEN_APPLE)
                        .setWeight(20)
                )

                .add(LootItem
                        .lootTableItem(NetherItems.BLACK_APPLE)
                        .setWeight(40)
                );
    }

    private LootPool.Builder books() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem
                        .lootTableItem(Blocks.BOOKSHELF)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4)))
                ).add(LootItem
                        .lootTableItem(Items.BOOK)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8)))
                )
                .when(LootItemRandomChanceCondition.randomChance(0.7f));
    }

    private LootPool.Builder workstations() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem
                        .lootTableItem(Blocks.LECTERN)
                        .setWeight(4)
                ).add(LootItem
                        .lootTableItem(NetherBlocks.NETHER_BREWING_STAND)
                        .setWeight(2)
                )
                .when(LootItemRandomChanceCondition.randomChance(0.7f));
    }

    private LootPool.Builder armor(EquipmentSet set, int max, float chance) {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(1, max))
                .add(LootItem
                        .lootTableItem(set.getSlot(EquipmentSet.HELMET_SLOT))
                        .setWeight(3)
                        .apply(new EnchantRandomlyFunction.Builder().withEnchantment(Enchantments.FIRE_PROTECTION)
                                                                    .withEnchantment(Enchantments.MENDING))
                )
                .add(LootItem
                        .lootTableItem(set.getSlot(EquipmentSet.CHESTPLATE_SLOT))
                        .setWeight(3)
                        .apply(new EnchantRandomlyFunction.Builder().withEnchantment(Enchantments.FIRE_PROTECTION)
                                                                    .withEnchantment(Enchantments.MENDING))
                )
                .add(LootItem
                        .lootTableItem(set.getSlot(EquipmentSet.LEGGINGS_SLOT))
                        .setWeight(3)
                        .apply(new EnchantRandomlyFunction.Builder().withEnchantment(Enchantments.FIRE_PROTECTION)
                                                                    .withEnchantment(Enchantments.MENDING))
                )
                .add(LootItem
                        .lootTableItem(set.getSlot(EquipmentSet.BOOTS_SLOT))
                        .setWeight(2)
                        .apply(new EnchantRandomlyFunction.Builder().withEnchantment(Enchantments.FIRE_PROTECTION)
                                                                    .withEnchantment(Enchantments.FALL_PROTECTION)
                                                                    .withEnchantment(Enchantments.MENDING))
                )
                .when(LootItemRandomChanceCondition.randomChance(chance));
    }


    private LootPool.Builder simpleTemplates(int max, float chance) {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem
                        .lootTableItem(NetherTemplates.NETHER_BOWL_SMITHING_TEMPLATE)
                        .setWeight(3)
                )
                .add(LootItem
                        .lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                        .setWeight(2)

                )
                .when(LootItemRandomChanceCondition.randomChance(chance));
    }



    private LootPool.Builder allTemplates() {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(2, 3))

                .add(LootItem
                        .lootTableItem(NetherTemplates.NETHER_BOWL_SMITHING_TEMPLATE)
                        .setWeight(2)
                )

                .add(LootItem
                        .lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                        .setWeight(3)
                );
    }





    private LootPool.Builder enchantedTools() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem
                        .lootTableItem(Blocks.LAPIS_BLOCK)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6)))
                )
                .add(LootItem
                        .lootTableItem(Items.DIAMOND_PICKAXE)
                        .apply(new EnchantRandomlyFunction.Builder().withEnchantment(Enchantments.BLOCK_EFFICIENCY)
                                                                    .withEnchantment(Enchantments.SILK_TOUCH)
                                                                    .withEnchantment(Enchantments.UNBREAKING)
                                                                    .withEnchantment(Enchantments.MENDING))
                        .setWeight(8)
                );

    }

    private LootPool.Builder netherObsidian() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(3))
                .add(LootItem
                        .lootTableItem(Blocks.OBSIDIAN)
                        .setWeight(9)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))
                ).add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_OBSIDIAN)
                        .setWeight(9)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))
                ).add(LootItem
                        .lootTableItem(Blocks.CRYING_OBSIDIAN)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                ).add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_CRYING_OBSIDIAN)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                ).add(LootItem
                        .lootTableItem(NetherBlocks.WEEPING_OBSIDIAN)
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                ).add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_WEEPING_OBSIDIAN)
                        .setWeight(1)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                );
    }

    private LootPool.Builder netherFiller() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(2))
                .add(LootItem
                        .lootTableItem(Blocks.NETHERRACK)
                        .setWeight(12)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12)))
                ).add(LootItem
                        .lootTableItem(Items.NETHER_WART)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))
                ).add(LootItem
                        .lootTableItem(Items.FLINT_AND_STEEL)
                        .setWeight(5)
                ).add(LootItem
                        .lootTableItem(Items.QUARTZ)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 12)))
                );
    }

    private LootPool.Builder superBonusOreLoot() {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(3, 6))
                .add(LootItem
                        .lootTableItem(Blocks.GOLD_BLOCK)
                        .setWeight(6)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
                .add(LootItem
                        .lootTableItem(Items.GOLD_INGOT)
                        .setWeight(8)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6)))
                )
                .add(LootItem
                        .lootTableItem(Items.NETHERITE_SCRAP)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))
                )
                .add(LootItem
                        .lootTableItem(Blocks.SCULK_CATALYST)
                        .setWeight(5)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6)))
                )
                .add(LootItem
                        .lootTableItem(Blocks.NETHERITE_BLOCK)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                );
    }

    private LootPool.Builder bonusOreLoot() {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(1, 3))
                .add(LootItem
                        .lootTableItem(Items.DIAMOND)
                        .setWeight(15)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                ).add(LootItem
                        .lootTableItem(Items.NETHERITE_SCRAP)
                        .setWeight(6)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                );
    }

    private LootPool.Builder simpleOreLoot() {
        return LootPool
                .lootPool()
                .setRolls(ConstantValue.exactly(1))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6)));
    }

    private LootPool.Builder simpleCityLoot() {
        return LootPool
                .lootPool()
                .setRolls(UniformGenerator.between(1, 3))
                .add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_CRYING_OBSIDIAN)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
                .add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_WEEPING_OBSIDIAN)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                )
                .add(LootItem
                        .lootTableItem(Blocks.CRYING_OBSIDIAN)
                        .setWeight(3)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
                .add(LootItem
                        .lootTableItem(NetherBlocks.WEEPING_OBSIDIAN)
                        .setWeight(2)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                )
                .add(LootItem
                        .lootTableItem(NetherBlocks.BLUE_OBSIDIAN)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                )
                .add(LootItem
                        .lootTableItem(Blocks.OBSIDIAN)
                        .setWeight(4)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                );
    }
}

