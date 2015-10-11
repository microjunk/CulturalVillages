package culturalvillages.mapgen;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import culturalvillages.CulturalVillages;
import culturalvillages.pieces.CulturalHalloweenStructures;
import culturalvillages.pieces.CulturalIndianStructures;
import culturalvillages.pieces.CulturalOldWestStructures;
import culturalvillages.pieces.CulturalVanillaStructures;
import culturalvillages.pieces.CulturalVikingStructures;
import culturalvillages.utils.VillageBiomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenCulturalVillages extends MapGenVillage
{
	/** A list of all the biomes villages can spawn in. */
	public static List biomelist = Arrays.asList(new BiomeGenBase[] {BiomeGenBase.plains, BiomeGenBase.mesa, BiomeGenBase.taiga});
	
	/** World terrain type, 0 for normal, 1 for flat map */
	private int terrainType;
    private int field_82665_g;
    private int field_82666_h;
    private static final String __OBFID = "CL_00000514";

    public MapGenCulturalVillages()
    {
        this.field_82665_g = 6;
        this.field_82666_h = 2;
    }

    public MapGenCulturalVillages(Map p_i2093_1_)
    {
        this();
    }

    public String getStructureName()
    {
        return CulturalVillages.NAME + ".Village";
    }

    protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_)
    {
        int k = p_75047_1_;
        int l = p_75047_2_;

        if (p_75047_1_ < 0)
        {
            p_75047_1_ -= this.field_82665_g - 1;
        }

        if (p_75047_2_ < 0)
        {
            p_75047_2_ -= this.field_82665_g - 1;
        }

        int i1 = p_75047_1_ / this.field_82665_g;
        int j1 = p_75047_2_ / this.field_82665_g;
        Random random = this.worldObj.setRandomSeed(i1, j1, 10387312);
        i1 *= this.field_82665_g;
        j1 *= this.field_82665_g;
        i1 += random.nextInt(this.field_82665_g - this.field_82666_h);
        j1 += random.nextInt(this.field_82665_g - this.field_82666_h);

        if (k == i1 && l == j1)
        {
        	BiomeGenBase biomegenbase = this.worldObj.getWorldChunkManager().getBiomeGenerator(new BlockPos(k * 16 + 8, 0, l * 16 + 8));

            if (biomegenbase == null)
            {
                return false;
            }

            Iterator iterator = biomelist.iterator();

            while (iterator.hasNext())
            {
                BiomeGenBase biomegenbase1 = (BiomeGenBase)iterator.next();

                if (biomegenbase == biomegenbase1)
                {
                    return true;
                }
            }
        }

        return false;
    }

    protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_)
    {
        return new MapGenCulturalVillages.Start(this.worldObj, this.rand, p_75049_1_, p_75049_2_, this.terrainType);
    }

    public static class Start extends StructureStart
    {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;
		private static final String __OBFID = "CL_00000515";
		
        public Start() {}

        public Start(World worldIn, Random p_i2092_2_, int p_i2092_3_, int p_i2092_4_, int p_i2092_5_)
        {
            super(p_i2092_3_, p_i2092_4_);
            BiomeGenBase biomegenbase = worldIn.getBiomeGenForCoords(new BlockPos(p_i2092_3_ * 16 + 8, 0, p_i2092_4_ * 16 + 8));

            if (biomegenbase != BiomeGenBase.jungle && biomegenbase != BiomeGenBase.jungleHills)
            {
                if (biomegenbase == BiomeGenBase.mesa)
                {
                	List list = CulturalIndianStructures.getStructureVillageWeightedPieceList(p_i2092_2_, p_i2092_5_);
                	CulturalIndianStructures.Start start = new CulturalIndianStructures.Start(worldIn.getWorldChunkManager(), 0, p_i2092_2_, (p_i2092_3_ << 4) + 2, (p_i2092_4_ << 4) + 2, list, p_i2092_5_);
                    this.components.add(start);
                    
                }
                else if (biomegenbase == BiomeGenBase.plains)
                {
                	List list = CulturalHalloweenStructures.getStructureVillageWeightedPieceList(p_i2092_2_, p_i2092_5_);
                	CulturalHalloweenStructures.Start start = new CulturalHalloweenStructures.Start(worldIn.getWorldChunkManager(), 0, p_i2092_2_, (p_i2092_3_ << 4) + 2, (p_i2092_4_ << 4) + 2, list, p_i2092_5_);
                    this.components.add(start);
                }
            }
            else
            {
            	List list = CulturalHalloweenStructures.getStructureVillageWeightedPieceList(p_i2092_2_, p_i2092_5_);
            	CulturalHalloweenStructures.Start start = new CulturalHalloweenStructures.Start(worldIn.getWorldChunkManager(), 0, p_i2092_2_, (p_i2092_3_ << 4) + 2, (p_i2092_4_ << 4) + 2, list, p_i2092_5_);
                this.components.add(start);
                //start.buildComponent(start, this.components, p_i2092_2_);
            }

            this.updateBoundingBox();
        }
    }
}