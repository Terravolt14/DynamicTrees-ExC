package maxhyper.dynamictreessugiforest.genfeatures;

import com.ferreusveritas.dynamictrees.api.IPostGenFeature;
import com.ferreusveritas.dynamictrees.api.IPostGrowFeature;
import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.network.MapSignal;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.ferreusveritas.dynamictrees.systems.nodemappers.NodeFruitCocoa;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import sugiforest.block.BlockSugiFallenLeaves;
import sugiforest.block.SugiBlocks;
import sun.security.ssl.Debug;

import java.util.List;
import java.util.Random;

public class FeatureGenFallenLeaves implements IPostGenFeature, IPostGrowFeature {

    protected final Block leafPileBlock;

    public FeatureGenFallenLeaves(Block leafPile){
        leafPileBlock = leafPile;
    }

    @Override
    public boolean postGrow(World world, BlockPos rootPos, BlockPos treePos, Species species, int soilLife, boolean natural) {
        if(world.rand.nextInt() % 64 == 0) {
            addFallenLeaves(world, rootPos,1, false);
        }
        return false;
    }

    @Override
    public boolean postGeneration(World world, BlockPos rootPos, Species species, Biome biome, int radius, List<BlockPos> endPoints, SafeChunkBounds safeBounds, IBlockState initialDirtState) {
        addFallenLeaves(world, rootPos,6, true);
        return true;
    }

    private boolean addFallenLeaf(World world, BlockPos pos){
        BlockPos blockpos = pos.up();
        while (!(world.getBlockState(blockpos).getProperties().containsKey(BlockDynamicLeaves.HYDRO))) {
            blockpos = blockpos.up();
            if (blockpos.getY() > pos.getY() + 20){
                return false;
            }
        }
        blockpos = blockpos.down();
        while (blockpos.getY() > 0 && world.isAirBlock(blockpos)) {
            blockpos = blockpos.down();
        }
        if (blockpos.getY() == 0){ return false; }
        blockpos = blockpos.up();

        if (leafPileBlock.canPlaceBlockAt(world, blockpos)) {
            world.setBlockState(blockpos, leafPileBlock.getDefaultState().withProperty(BlockSugiFallenLeaves.CHANCE, Boolean.TRUE));
            return true;
        } else {
            blockpos = blockpos.down();
            IBlockState state = world.getBlockState(blockpos);
            if (state.getBlock() instanceof BlockSugiFallenLeaves) {
                int layers = state.getValue(BlockSugiFallenLeaves.LAYERS);
                world.setBlockState(blockpos, leafPileBlock.getDefaultState().withProperty(BlockSugiFallenLeaves.LAYERS, (layers & 7) + 1).withProperty(BlockSugiFallenLeaves.CHANCE, Boolean.TRUE));
                return true;
            }
        }
        return false;
    }

    private void addFallenLeaves(World world, BlockPos rootPos, int amount, boolean worldGen) {
        int leaveRange = 4;

        for (int attempts = amount*10; attempts > 0; attempts--){
            int randX = world.rand.nextInt((2*leaveRange)+1)-leaveRange;
            int randZ = world.rand.nextInt((2*leaveRange)+1)-leaveRange;
            BlockPos selectedPos = new BlockPos(rootPos.getX() + randX, rootPos.getY(), rootPos.getZ() + randZ);
            if (addFallenLeaf(world, selectedPos)){
                amount--;
            }
        }
    }

}