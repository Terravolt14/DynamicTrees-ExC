package maxhyper.dynamictreesthaumicbases.trees;

import com.ferreusveritas.dynamictrees.systems.dropcreators.DropCreatorSeed;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenFruit;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import maxhyper.dynamictreesthaumicbases.DynamicTreesThaumicBases;
import maxhyper.dynamictreesthaumicbases.ModContent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Objects;

public class TBTreeEnderOak extends TreeFamily {

	public static Block leavesBlock = Block.getBlockFromName("thaumicbases:enderleaves");
	public static Block logBlock = Block.getBlockFromName("thaumicbases:enderlogs");
	public static Block saplingBlock = Block.getBlockFromName("thaumicbases:endersapling");

	public class SpeciesEnderOak extends Species {

		SpeciesEnderOak(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, ModContent.enderOakLeavesProperties);
			setBasicGrowingParameters(0.3f, 12.0f, upProbability, lowestBranchHeight, 0.8f);

			generateSeed();
			addDropCreator(new DropCreatorSeed(0.5f));

			this.addGenFeature((new FeatureGenFruit(ModContent.blockEnderPearl)).setRayDistance(4.0F));
		}
	}

	public TBTreeEnderOak() {
		super(new ResourceLocation(DynamicTreesThaumicBases.MODID, "enderOak"));

		setPrimitiveLog(logBlock.getDefaultState(), new ItemStack(logBlock));

		ModContent.enderOakLeavesProperties.setTree(this);

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
		setCommonSpecies(new SpeciesEnderOak(this));
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
