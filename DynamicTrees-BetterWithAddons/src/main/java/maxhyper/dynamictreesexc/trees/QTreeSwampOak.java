package maxhyper.dynamictreesexc.trees;

import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenVine;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.rwtema.extrautils2.blocks.TreeIronWoods;
import maxhyper.dynamictreesexc.DynamicTreesExC;
import maxhyper.dynamictreesexc.ModContent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.quark.world.block.BlockVariantLeaves;
import vazkii.quark.world.feature.TreeVariants;

import java.util.List;

public class QTreeSwampOak extends TreeFamily {

	public static Block leavesBlock = Block.getBlockFromName("quark:variant_leaves");
	public static Block logBlock = Blocks.LOG;
	public static Block saplingBlock = Block.getBlockFromName("quark:variant_sapling");
	public static IBlockState leavesState = TreeVariants.variant_leaves.getDefaultState().withProperty(BlockVariantLeaves.VARIANT, BlockVariantLeaves.Variant.SWAMP_LEAVES);

	public class SpeciesSwampOak extends Species {

		SpeciesSwampOak(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, ModContent.swampOakLeavesProperties);
			setBasicGrowingParameters(0.3f, 12.0f, upProbability, lowestBranchHeight, 0.8f);

			envFactor(Type.COLD, 0.75f);
			envFactor(Type.DRY, 0.50f);
			envFactor(Type.SWAMP, 1.05f);

			generateSeed();
			setupStandardSeedDropping();

			addGenFeature(new FeatureGenVine().setQuantity(32).setMaxLength(8).setRayDistance(7).setVineBlock(Blocks.VINE));//Generate Vines

		}
		@Override
		public boolean isBiomePerfect(Biome biome) {
			return BiomeDictionary.hasType(biome, Type.SWAMP);
		}
	}

	public QTreeSwampOak() {
		super(new ResourceLocation(DynamicTreesExC.MODID, "swampOak"));

		setPrimitiveLog(logBlock.getDefaultState(), new ItemStack(logBlock, 1, 0));

		ModContent.swampOakLeavesProperties.setTree(this);

		addConnectableVanillaLeaves((state) -> state.getBlock() == leavesBlock);
	}

	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesSwampOak(this));
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
