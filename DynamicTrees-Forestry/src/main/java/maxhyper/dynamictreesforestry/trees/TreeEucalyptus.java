package maxhyper.dynamictreesforestry.trees;

import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

import java.util.List;
import java.util.Objects;

public class TreeEucalyptus extends TreeFamily {

//	public static Block leavesBlock = NaturaOverworld.overworldLeaves2;
//    public static Block logBlock = NaturaOverworld.overworldLog2;
//    public static Block saplingBlock = NaturaOverworld.overworldSapling2;
//	public static IBlockState leavesState = leavesBlock.getDefaultState().withProperty(BlockOverworldLog2.TYPE, BlockOverworldLog2.LogType.EUCALYPTUS);
//
//	public class SpeciesEucalyptus extends Species {
//
//		SpeciesEucalyptus(TreeFamily treeFamily) {
//			super(treeFamily.getName(), treeFamily, ModContent.eucalyptusLeavesProperties);
//
//			this.setBasicGrowingParameters(0.15F, 24.0F, 2, 3, 0.7F);
//
////			envFactor(Type.COLD, 0.75f);
////			envFactor(Type.HOT, 0.50f);
////			envFactor(Type.DRY, 0.50f);
////			envFactor(Type.FOREST, 1.05f);
//
//			generateSeed();
//			setupStandardSeedDropping();
//		}
//	}
//
//	public TreeEucalyptus() {
//		super(new ResourceLocation(DynamicTreesForestry.MODID, "eucalyptus"));
//
//		setPrimitiveLog(logBlock.getDefaultState(), new ItemStack(logBlock, 1, 1));
//
//		ModContent.eucalyptusLeavesProperties.setTree(this);
//
//		addConnectableVanillaLeaves((state) -> state.getBlock() == leavesBlock);
//	}
//
//	@Override
//	public ItemStack getPrimitiveLogItemStack(int qty) {
//		ItemStack stack = new ItemStack(Objects.requireNonNull(logBlock), 1, 1);
//		stack.setCount(MathHelper.clamp(qty, 0, 64));
//		return stack;
//	}
//	@Override
//	public ItemStack getStick(int qty) {
//		ItemStack stick = NaturaCommons.eucalyptus_stick;
//		stick.setCount(qty);
//		return stick;
//	}
//
//	@Override
//	public void createSpecies() {
//		setCommonSpecies(new SpeciesEucalyptus(this));
//	}
//
//	@Override
//	public void registerSpecies(IForgeRegistry<Species> speciesRegistry) {
//		super.registerSpecies(speciesRegistry);
//	}
//
//	@Override
//	public List<Item> getRegisterableItems(List<Item> itemList) {
//		return super.getRegisterableItems(itemList);
//	}
//
}
