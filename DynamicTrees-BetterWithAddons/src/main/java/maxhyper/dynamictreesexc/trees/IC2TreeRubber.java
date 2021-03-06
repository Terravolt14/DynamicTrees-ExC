package maxhyper.dynamictreesexc.trees;

import com.ferreusveritas.dynamictrees.blocks.BlockBranch;
import com.ferreusveritas.dynamictrees.blocks.BlockBranchThick;
import com.ferreusveritas.dynamictrees.systems.dropcreators.DropCreatorSeed;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenClearVolume;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import maxhyper.dynamictreesexc.DynamicTreesExC;
import maxhyper.dynamictreesexc.ModContent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary.Type;

import java.util.List;
import java.util.Objects;

public class IC2TreeRubber extends TreeFamily {

	public static Block leavesBlock = Block.getBlockFromName("ic2:leaves");
	public static Block logBlock = Block.getBlockFromName("ic2:rubber_wood");
	public static Block saplingBlock = Block.getBlockFromName("ic2:sapling");

	public class SpeciesRubberIC extends Species {

		SpeciesRubberIC(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, ModContent.rubberICLeavesProperties);

			setBasicGrowingParameters(0.2f, 14.0f, 10, 8, 1.25f);

			envFactor(Type.COLD, 0.75f);
			envFactor(Type.WET, 1.5f);
			envFactor(Type.DRY, 0.5f);
			envFactor(Type.FOREST, 1.1f);

			generateSeed();
			addDropCreator(new DropCreatorSeed(2f));

			this.addGenFeature(new FeatureGenClearVolume(12));
		}

		@Override
		public boolean isAcceptableSoil(World world, BlockPos pos, IBlockState soilBlockState) {
			return super.isAcceptableSoil(world, pos, soilBlockState) || soilBlockState.getBlock() instanceof BlockDirt || soilBlockState.getBlock() instanceof BlockGrass;
		}

//		@Override
//		protected EnumFacing newDirectionSelected(EnumFacing newDir, GrowSignal signal) {
//			if (signal.isInTrunk() && newDir != EnumFacing.UP) { // Turned out of trunk
//				signal.energy *= 0.3f;
//				if (signal.energy > 3) signal.energy = 3;
//			}
//			return newDir;
//		}
	}

	public IC2TreeRubber() {
		super(new ResourceLocation(DynamicTreesExC.MODID, "rubberIC"));

		setDynamicBranch(ModContent.rubberICBranch);
		ModContent.rubberICBranchFilled.setFamily(this);
		
		ModContent.rubberICLeavesProperties.setTree(this);
		
		addConnectableVanillaLeaves((state) -> state.getBlock() == leavesBlock);
	}
	@Override
	public ItemStack getPrimitiveLogItemStack(int qty) {
		ItemStack stack = new ItemStack(Objects.requireNonNull(logBlock), 1, 0);
		stack.setCount(MathHelper.clamp(qty, 0, 64));
		return stack;
	}

	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesRubberIC(this));
	}

	@Override
	public List<Block> getRegisterableBlocks(List<Block> blockList) {
		return super.getRegisterableBlocks(blockList);
	}

	@Override
	public BlockBranch createBranch() {
		String branchName = getName() + "branch";
		return new BlockBranchThick(branchName);
	}
	
}
