package paulevs.betternether;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import paulevs.betternether.advancements.BNCriterion;
import paulevs.betternether.commands.CommandRegistry;
import paulevs.betternether.config.Config;
import paulevs.betternether.config.Configs;
import paulevs.betternether.loot.BNLoot;
import paulevs.betternether.recipes.IntegrationRecipes;
import paulevs.betternether.recipes.ItemRecipes;
import paulevs.betternether.registry.NetherBiomes;
import paulevs.betternether.registry.BlockEntitiesRegistry;
import paulevs.betternether.registry.BrewingRegistry;
import paulevs.betternether.registry.EntityRegistry;
import paulevs.betternether.registry.NetherBlocks;
import paulevs.betternether.registry.NetherFeatures;
import paulevs.betternether.registry.NetherItems;
import paulevs.betternether.registry.NetherTags;
import paulevs.betternether.registry.SoundsRegistry;
import paulevs.betternether.world.BNWorldGenerator;
import paulevs.betternether.world.structures.piece.StructureTypes;
import ru.bclib.api.WorldDataAPI;
import ru.bclib.api.dataexchange.DataExchangeAPI;
import ru.bclib.util.Logger;

public class BetterNether implements ModInitializer {
	public static final String MOD_ID = "betternether";
	public static final Logger LOGGER = new Logger(MOD_ID);
	private static boolean thinArmor = true;
	private static boolean lavafallParticles = true;
	private static float fogStart = 0.05F;
	private static float fogEnd = 0.5F;
	
	@Override
	public void onInitialize() {
		LOGGER.info("######## BetterNether for 1.17.1 ########");
		//MinecraftClient.getInstance().getEntityModelLoader().reload(MinecraftClient.getInstance().getResourceManager());
		initOptions();
		SoundsRegistry.register();
		NetherBlocks.register();
		BlockEntitiesRegistry.register();
		NetherItems.register();
		EntityRegistry.register();
		StructureTypes.init();
		BNWorldGenerator.onModInit();
		NetherFeatures.register();
		NetherBiomes.register();
		BrewingRegistry.register();
		CommandRegistry.register();
		Config.save();
		
		IntegrationRecipes.register();
		NetherTags.register();
		ItemRecipes.register();
		//NetherBiomeSource.register();
		BNLoot.register();
		BNCriterion.register();
		
		Configs.saveConfigs();
		WorldDataAPI.registerModCache(MOD_ID);
		DataExchangeAPI.registerMod(BetterNether.MOD_ID);
		Patcher.register();
		
		//MigrationProfile.fixCustomFolder(new File("/Users/frank/Entwicklung/BetterNether/src/main/resources/data/betternether/structures"));
	}
	
	private void initOptions() {
		thinArmor = Configs.MAIN.getBoolean("improvement", "smaller_armor_offset", true);
		lavafallParticles = Configs.MAIN.getBoolean("improvement", "lavafall_particles", true);
		float density = Configs.MAIN.getFloat("improvement", "fog_density[vanilla: 1.0]", 0.75F);
		changeFogDensity(density);
	}
	
	public static boolean hasThinArmor() {
		return thinArmor;
	}
	
	public static void setThinArmor(boolean value) {
		thinArmor = value;
	}
	
	public static boolean hasLavafallParticles() {
		return lavafallParticles;
	}
	
	public static void changeFogDensity(float density) {
		fogStart = -0.45F * density + 0.5F;
		fogEnd = -0.5F * density + 1;
	}
	
	public static float getFogStart() {
		return fogStart;
	}
	
	public static float getFogEnd() {
		return fogEnd;
	}

	public static ResourceLocation makeID(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}

