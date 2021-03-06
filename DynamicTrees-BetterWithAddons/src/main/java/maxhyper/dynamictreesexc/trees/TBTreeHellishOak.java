package maxhyper.dynamictreesexc.trees;

import com.ferreusveritas.dynamictrees.systems.dropcreators.DropCreatorSeed;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenFruit;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import maxhyper.dynamictreesexc.DynamicTreesExC;
import maxhyper.dynamictreesexc.ModContent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Objects;

public class TBTreeHellishOak extends TreeFamily {

	public static Block leavesBlock = Block.getBlockFromName("thaumicbases:netherleaves");
	public static Block logBlock = Block.getBlockFromName("thaumicbases:netherlogs");
	public static Block saplingBlock = Block.getBlockFromName("thaumicbases:nethersapling");

	public class SpeciesHellishOak extends Species {

		SpeciesHellishOak(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, ModContent.hellishOakLeavesProperties);
			setBasicGrowingParameters(0.3f, 12.0f, upProbability, lowestBranchHeight, 0.8f);

			generateSeed();
			addDropCreator(new DropCreatorSeed(0.5f));

			this.addGenFeature((new FeatureGenFruit(ModContent.blockMagmaCream)).setRayDistance(4.0F));
		}
	}

	public TBTreeHellishOak() {
		super(new ResourceLocation(DynamicTreesExC.MODID, "hellishOak"));

		setPrimitiveLog(logBlock.getDefaultState(), new ItemStack(logBlock));

		ModContent.hellishOakLeavesProperties.setTree(this);

		addConnectableVanillaLeaves((state) -> state.getBlock() == leavesBlock);
	}
	@Override
	public ItemStack getPrimitiveLogItemStack(int qty) {
		ItemStack stack = new ItemStack(Objects.requireNonNull(logBlock));
		stack.setCount(MathHelper.clamp(qty, 0, 64));
		return stack;
	}

	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesHellishOak(this));
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
