package maxhyper.dynamictreestheaether.trees;

import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.blocks.natural.BlockAetherLog;
import com.legacy.aether.blocks.util.EnumLogType;
import com.legacy.aether.items.ItemsAether;
import maxhyper.dynamictreestheaether.ModContent;
import maxhyper.dynamictreestheaether.genfeatures.FeatureGenRandomLeaves;
import maxhyper.dynamictreestheaether.genfeatures.FeatureGenSnowArea;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Objects;

public class ALTreeHoliday extends TreeFamily {

	public static Block leavesBlock = BlocksAether.holiday_leaves;
	public static Block logBlock = BlocksAether.aether_log;

	public class SpeciesHoliday extends Species {

		SpeciesHoliday(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, ModContent.holidayLeavesProperties);

			setBasicGrowingParameters(0.25f, 16.0f, 3, 3, 0.9f);
			setGrowthLogicKit(TreeRegistry.findGrowthLogicKit(ModTrees.CONIFER));

			envFactor(Type.COLD, 1.8f);
			envFactor(Type.HOT, 0.5f);

			addGenFeature(new FeatureGenRandomLeaves(8, 16, ModContent.holidayLeavesProperties.getDynamicLeavesState().withProperty(BlockDynamicLeaves.HYDRO, 1), ModContent.holidayDecorLeavesProperties.getDynamicLeavesState().withProperty(BlockDynamicLeaves.HYDRO, 1), 0.1f));
			addGenFeature(new FeatureGenSnowArea(15, BlocksAether.present.getDefaultState(), 80));
			generateSeed();
			clearAcceptableSoils();
			addAcceptableSoil(BlocksAether.aether_grass, BlocksAether.enchanted_aether_grass, BlocksAether.aether_dirt);
		}
	}

	public ALTreeHoliday() {
		super(new ResourceLocation(maxhyper.dynamictreestheaether.DynamicTreesTheAether.MODID, "holiday"));

		setPrimitiveLog(logBlock.getDefaultState().withProperty(BlockAetherLog.wood_type, EnumLogType.Skyroot), new ItemStack(logBlock, 1, 0));

		ModContent.holidayLeavesProperties.setTree(this);
		ModContent.holidayDecorLeavesProperties.setTree(this);

		addConnectableVanillaLeaves((state) -> state.getBlock() == leavesBlock);
	}
	@Override
	public ItemStack getPrimitiveLogItemStack(int qty) {
		ItemStack stack = new ItemStack(Objects.requireNonNull(logBlock), qty, 0);
		stack.setCount(MathHelper.clamp(qty, 0, 64));
		return stack;
	}

	@Override
	public ItemStack getStick(int qty) {
		return new ItemStack(ItemsAether.skyroot_stick, qty);
	}

	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesHoliday(this));
	}

	@Override
	public void registerSpecies(IForgeRegistry<Species> speciesRegistry) {
		super.registerSpecies(speciesRegistry);
	}

	@Override
	public List<Item> getRegisterableItems(List<Item> itemList) {
		return super.getRegisterableItems(itemList);
	}

}
