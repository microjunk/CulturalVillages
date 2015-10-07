package culturalvillages.pieces;

import static net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import culturalvillages.mapgen.MapGenCulturalVillages;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class CulturalIndianStructures
{
	private static final String __OBFID = "CL_00000516";

    public static void registerVillagePieces()
    {
    	MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.House1.class, "ViBH");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.Field1.class, "ViDF");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.Field2.class, "ViF");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.Torch.class, "ViL");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.House4Garden.class, "ViSH");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.WoodHut.class, "ViSmH");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.Church.class, "ViST");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.House2.class, "ViS");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.Start.class, "ViStart");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.Path.class, "ViSR");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.House3.class, "ViTRH");
        MapGenStructureIO.registerStructureComponent(CulturalIndianStructures.Well.class, "ViW");
    }

    public static List getStructureVillageWeightedPieceList(Random p_75084_0_, int p_75084_1_)
    {
        ArrayList arraylist = Lists.newArrayList();
        arraylist.add(new CulturalIndianStructures.PieceWeight(CulturalIndianStructures.House4Garden.class, 4, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        arraylist.add(new CulturalIndianStructures.PieceWeight(CulturalIndianStructures.Church.class, 20, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 1 + p_75084_1_)));
        arraylist.add(new CulturalIndianStructures.PieceWeight(CulturalIndianStructures.House1.class, 20, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 2 + p_75084_1_)));
        arraylist.add(new CulturalIndianStructures.PieceWeight(CulturalIndianStructures.WoodHut.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 5 + p_75084_1_ * 3)));
        arraylist.add(new CulturalIndianStructures.PieceWeight(CulturalIndianStructures.Field1.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 1 + p_75084_1_, 4 + p_75084_1_)));
        arraylist.add(new CulturalIndianStructures.PieceWeight(CulturalIndianStructures.Field2.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        arraylist.add(new CulturalIndianStructures.PieceWeight(CulturalIndianStructures.House2.class, 15, MathHelper.getRandomIntegerInRange(p_75084_0_, 0, 1 + p_75084_1_)));
        arraylist.add(new CulturalIndianStructures.PieceWeight(CulturalIndianStructures.House3.class, 8, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 3 + p_75084_1_ * 2)));
        net.minecraftforge.fml.common.registry.VillagerRegistry.addExtraVillageComponents(arraylist, p_75084_0_, p_75084_1_);

        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext())
        {
            if (((CulturalIndianStructures.PieceWeight)iterator.next()).villagePiecesLimit == 0)
            {
                iterator.remove();
            }
        }

        return arraylist;
    }

    private static int func_75079_a(List p_75079_0_)
    {
        boolean flag = false;
        int i = 0;
        CulturalIndianStructures.PieceWeight pieceweight;

        for (Iterator iterator = p_75079_0_.iterator(); iterator.hasNext(); i += pieceweight.villagePieceWeight)
        {
            pieceweight = (CulturalIndianStructures.PieceWeight)iterator.next();

            if (pieceweight.villagePiecesLimit > 0 && pieceweight.villagePiecesSpawned < pieceweight.villagePiecesLimit)
            {
                flag = true;
            }
        }

        return flag ? i : -1;
    }

    private static CulturalIndianStructures.Village func_176065_a(CulturalIndianStructures.Start p_176065_0_, CulturalIndianStructures.PieceWeight p_176065_1_, List p_176065_2_, Random p_176065_3_, int p_176065_4_, int p_176065_5_, int p_176065_6_, EnumFacing p_176065_7_, int p_176065_8_)
    {
        Class oclass = p_176065_1_.villagePieceClass;
        Object object = null;

        if (oclass == CulturalIndianStructures.House4Garden.class)
        {
            object = CulturalIndianStructures.House4Garden.func_175858_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalIndianStructures.Church.class)
        {
            object = CulturalIndianStructures.Church.func_175854_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalIndianStructures.House1.class)
        {
            object = CulturalIndianStructures.House1.func_175850_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalIndianStructures.WoodHut.class)
        {
            object = CulturalIndianStructures.WoodHut.func_175853_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalIndianStructures.Field1.class)
        {
            object = CulturalIndianStructures.Field1.func_175851_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalIndianStructures.Field2.class)
        {
            object = CulturalIndianStructures.Field2.func_175852_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalIndianStructures.House2.class)
        {
            object = CulturalIndianStructures.House2.func_175855_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalIndianStructures.House3.class)
        {
            object = CulturalIndianStructures.House3.func_175849_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else
        {
            //object = net.minecraftforge.fml.common.registry.VillagerRegistry.getVillageComponent(p_176065_1_, p_176065_0_ , p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }

        return (CulturalIndianStructures.Village)object;
    }

    private static CulturalIndianStructures.Village func_176067_c(CulturalIndianStructures.Start p_176067_0_, List p_176067_1_, Random p_176067_2_, int p_176067_3_, int p_176067_4_, int p_176067_5_, EnumFacing p_176067_6_, int p_176067_7_)
    {
        int i1 = func_75079_a(p_176067_0_.structureVillageWeightedPieceList);

        if (i1 <= 0)
        {
            return null;
        }
        else
        {
            int j1 = 0;

            while (j1 < 5)
            {
                ++j1;
                int k1 = p_176067_2_.nextInt(i1);
                Iterator iterator = p_176067_0_.structureVillageWeightedPieceList.iterator();

                while (iterator.hasNext())
                {
                    CulturalIndianStructures.PieceWeight pieceweight = (CulturalIndianStructures.PieceWeight)iterator.next();
                    k1 -= pieceweight.villagePieceWeight;

                    if (k1 < 0)
                    {
                        if (!pieceweight.canSpawnMoreVillagePiecesOfType(p_176067_7_) || pieceweight == p_176067_0_.structVillagePieceWeight && p_176067_0_.structureVillageWeightedPieceList.size() > 1)
                        {
                            break;
                        }

                        CulturalIndianStructures.Village village = func_176065_a(p_176067_0_, pieceweight, p_176067_1_, p_176067_2_, p_176067_3_, p_176067_4_, p_176067_5_, p_176067_6_, p_176067_7_);

                        if (village != null)
                        {
                            ++pieceweight.villagePiecesSpawned;
                            p_176067_0_.structVillagePieceWeight = pieceweight;

                            if (!pieceweight.canSpawnMoreVillagePieces())
                            {
                                p_176067_0_.structureVillageWeightedPieceList.remove(pieceweight);
                            }

                            return village;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = CulturalIndianStructures.Torch.func_175856_a(p_176067_0_, p_176067_1_, p_176067_2_, p_176067_3_, p_176067_4_, p_176067_5_, p_176067_6_);

            if (structureboundingbox != null)
            {
                return new CulturalIndianStructures.Torch(p_176067_0_, p_176067_7_, p_176067_2_, structureboundingbox, p_176067_6_);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent func_176066_d(CulturalIndianStructures.Start p_176066_0_, List p_176066_1_, Random p_176066_2_, int p_176066_3_, int p_176066_4_, int p_176066_5_, EnumFacing p_176066_6_, int p_176066_7_)
    {
        if (p_176066_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_176066_3_ - p_176066_0_.getBoundingBox().minX) <= 112 && Math.abs(p_176066_5_ - p_176066_0_.getBoundingBox().minZ) <= 112)
        {
            CulturalIndianStructures.Village village = func_176067_c(p_176066_0_, p_176066_1_, p_176066_2_, p_176066_3_, p_176066_4_, p_176066_5_, p_176066_6_, p_176066_7_ + 1);

            if (village != null)
            {
                int i1 = (village.getBoundingBox().minX + village.getBoundingBox().maxX) / 2;
                int j1 = (village.getBoundingBox().minZ + village.getBoundingBox().maxZ) / 2;
                int k1 = village.getBoundingBox().maxX - village.getBoundingBox().minX;
                int l1 = village.getBoundingBox().maxZ - village.getBoundingBox().minZ;
                int i2 = k1 > l1 ? k1 : l1;

                if (p_176066_0_.getWorldChunkManager().areBiomesViable(i1, j1, i2 / 2 + 4, MapGenCulturalVillages.villageSpawnBiomes))
                {
                    p_176066_1_.add(village);
                    p_176066_0_.field_74932_i.add(village);
                    return village;
                }
            }

            return null;
        }
        else
        {
            return null;
        }
    }

    private static StructureComponent func_176069_e(CulturalIndianStructures.Start p_176069_0_, List p_176069_1_, Random p_176069_2_, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing p_176069_6_, int p_176069_7_)
    {
        if (p_176069_7_ > 3 + p_176069_0_.terrainType)
        {
            return null;
        }
        else if (Math.abs(p_176069_3_ - p_176069_0_.getBoundingBox().minX) <= 112 && Math.abs(p_176069_5_ - p_176069_0_.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = CulturalIndianStructures.Path.func_175848_a(p_176069_0_, p_176069_1_, p_176069_2_, p_176069_3_, p_176069_4_, p_176069_5_, p_176069_6_);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
                CulturalIndianStructures.Path path = new CulturalIndianStructures.Path(p_176069_0_, p_176069_7_, p_176069_2_, structureboundingbox, p_176069_6_);
                int i1 = (path.getBoundingBox().minX + path.getBoundingBox().maxX) / 2;
                int j1 = (path.getBoundingBox().minZ + path.getBoundingBox().maxZ) / 2;
                int k1 = path.getBoundingBox().maxX - path.getBoundingBox().minX;
                int l1 = path.getBoundingBox().maxZ - path.getBoundingBox().minZ;
                int i2 = k1 > l1 ? k1 : l1;

                if (p_176069_0_.getWorldChunkManager().areBiomesViable(i1, j1, i2 / 2 + 4, MapGenCulturalVillages.villageSpawnBiomes))
                {
                    p_176069_1_.add(path);
                    p_176069_0_.field_74930_j.add(path);
                    return path;
                }
            }

            return null;
        }
        else
        {
            return null;
        }
    }

    public static class Church extends CulturalIndianStructures.Village
        {
            private static final String __OBFID = "CL_00000525";

            public Church() {}

            public Church(CulturalIndianStructures.Start p_i45564_1_, int p_i45564_2_, Random p_i45564_3_, StructureBoundingBox p_i45564_4_, EnumFacing p_i45564_5_)
            {
                super(p_i45564_1_, p_i45564_2_);
                this.coordBaseMode = p_i45564_5_;
                this.boundingBox = p_i45564_4_;
            }

            public static CulturalIndianStructures.Church func_175854_a(CulturalIndianStructures.Start p_175854_0_, List p_175854_1_, Random p_175854_2_, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing p_175854_6_, int p_175854_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 12, 12, 14, p_175854_6_);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null ? new CulturalIndianStructures.Church(p_175854_0_, p_175854_7_, p_175854_2_, structureboundingbox, p_175854_6_) : null;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 12 - 1, 0);
                }

                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 14, 11, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, -4, 0, 14, -1, 12, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 14, 0, 12, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 0, 1, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 14, 1, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 3, 1, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 6, 1, 1, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 2, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 2, 1, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 3, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 3, 1, 9, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 0, 5, 1, 0, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 7, 1, 11, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 5, 1, 10, 6, 1, 10, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 1, 10, 9, 1, 10, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 0, 10, 1, 0, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 11, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 11, 1, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 12, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 12, 1, 8, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 13, 1, 3, 13, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 13, 1, 6, 13, 1, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 5, 3, 3, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 5, 11, 3, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 1, 9, 7, 3, 9, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 1, 1, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 14, 1, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 2, 1, 3, 2, 1, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 2, 3, 1, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 3, 1, 8, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 1, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 8, 4, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 1, 1, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 5, 1, 9, 5, 1, 10, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 10, 8, 2, 10, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 1, 1, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 9, 9, 1, 10, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 1, 1, 10, 1, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 1, 8, 10, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 2, 11, 1, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 7, 11, 1, 8, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 12, 1, 3, 12, 1, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 2, 4, 2, 2, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 2, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 2, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 2, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 2, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 2, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 2, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 2, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 2, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 2, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 2, 7, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 12, 2, 4, 12, 2, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 2, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 3, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 3, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 3, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 3, 10, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 3, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 10, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 10, 3, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 11, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 11, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 12, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 4, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 4, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 4, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 4, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 4, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 4, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 4, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 4, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 4, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 4, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 5, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 5, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 5, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 5, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 6, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 6, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 7, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 7, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 8, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 4, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 4, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 10, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 5, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 9, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 8, 6, 5, p_74875_3_);
                //this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
                
                BlockPos blockpos = new BlockPos(this.getXWithOffset(3, 4), this.getYWithOffset(1), this.getZWithOffset(3, 4));
                if (p_74875_3_.func_175898_b(blockpos))
                {
                	worldIn.setBlockState(blockpos, Blocks.chest.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.EAST));
                }
                
                BlockPos blockpos1 = new BlockPos(this.getXWithOffset(7, 0), this.getYWithOffset(4), this.getZWithOffset(7, 0));
                if (p_74875_3_.func_175898_b(blockpos1))
                {
                	worldIn.setBlockState(blockpos1, Blocks.skull.getDefaultState().withProperty(BlockSkull.FACING, this.coordBaseMode.getOpposite()));

                	TileEntity te = worldIn.getTileEntity(blockpos1);

                	if (te instanceof TileEntitySkull) {

                	 ((TileEntitySkull) te).setSkullRotation(0); // just a random value for testing

                	 ((TileEntitySkull) te).setType(0); // wither skull

                	}
                }
                
                this.spawnVillagers(worldIn, p_74875_3_, 1, 1, 2, 1);
                return true;
            }

            protected int func_180779_c(int p_180779_1_, int p_180779_2_)
            {
                return 2;
            }
        }

    public static class Field1 extends CulturalIndianStructures.Village
        {
            /** First crop type for this field. */
            private Block cropTypeA;
            /** Second crop type for this field. */
            private Block cropTypeB;
            /** Third crop type for this field. */
            private Block cropTypeC;
            /** Fourth crop type for this field. */
            private Block cropTypeD;
            private static final String __OBFID = "CL_00000518";

            public Field1() {}

            public Field1(CulturalIndianStructures.Start p_i45570_1_, int p_i45570_2_, Random p_i45570_3_, StructureBoundingBox p_i45570_4_, EnumFacing p_i45570_5_)
            {
                super(p_i45570_1_, p_i45570_2_);
                this.coordBaseMode = p_i45570_5_;
                this.boundingBox = p_i45570_4_;
                this.cropTypeA = this.func_151559_a(p_i45570_3_);
                this.cropTypeB = this.func_151559_a(p_i45570_3_);
                this.cropTypeC = this.func_151559_a(p_i45570_3_);
                this.cropTypeD = this.func_151559_a(p_i45570_3_);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound p_143012_1_)
            {
                super.writeStructureToNBT(p_143012_1_);
                p_143012_1_.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
                p_143012_1_.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
                p_143012_1_.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
                p_143012_1_.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound p_143011_1_)
            {
                super.readStructureFromNBT(p_143011_1_);
                this.cropTypeA = Block.getBlockById(p_143011_1_.getInteger("CA"));
                this.cropTypeB = Block.getBlockById(p_143011_1_.getInteger("CB"));
                this.cropTypeC = Block.getBlockById(p_143011_1_.getInteger("CC"));
                this.cropTypeD = Block.getBlockById(p_143011_1_.getInteger("CD"));
            }

            private Block func_151559_a(Random p_151559_1_)
            {
                switch (p_151559_1_.nextInt(5))
                {
                    case 0:
                        return Blocks.carrots;
                    case 1:
                        return Blocks.potatoes;
                    default:
                        return Blocks.wheat;
                }
            }

            public static CulturalIndianStructures.Field1 func_175851_a(CulturalIndianStructures.Start p_175851_0_, List p_175851_1_, Random p_175851_2_, int p_175851_3_, int p_175851_4_, int p_175851_5_, EnumFacing p_175851_6_, int p_175851_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175851_3_, p_175851_4_, p_175851_5_, 0, 0, 0, 13, 4, 9, p_175851_6_);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175851_1_, structureboundingbox) == null ? new CulturalIndianStructures.Field1(p_175851_0_, p_175851_7_, p_175851_2_, structureboundingbox, p_175851_6_) : null;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
                }

                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 12, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 0, 1, 8, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 0, 1, 11, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 12, 0, 0, 12, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 11, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 0, 8, 11, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 0, 1, 9, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
                int i;

                for (i = 1; i <= 7; ++i)
                {
                    this.func_175811_a(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 1, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 2, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 4, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 5, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 7, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 8, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 10, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 11, 1, i, p_74875_3_);
                }

                for (i = 0; i < 9; ++i)
                {
                    for (int j = 0; j < 13; ++j)
                    {
                        this.clearCurrentPositionBlocksUpwards(worldIn, j, 4, i, p_74875_3_);
                        this.func_175808_b(worldIn, Blocks.dirt.getDefaultState(), j, -1, i, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Field2 extends CulturalIndianStructures.Village
        {
            /** First crop type for this field. */
            private Block cropTypeA;
            /** Second crop type for this field. */
            private Block cropTypeB;
            private static final String __OBFID = "CL_00000519";

            public Field2() {}

            public Field2(CulturalIndianStructures.Start p_i45569_1_, int p_i45569_2_, Random p_i45569_3_, StructureBoundingBox p_i45569_4_, EnumFacing p_i45569_5_)
            {
                super(p_i45569_1_, p_i45569_2_);
                this.coordBaseMode = p_i45569_5_;
                this.boundingBox = p_i45569_4_;
                this.cropTypeA = this.func_151560_a(p_i45569_3_);
                this.cropTypeB = this.func_151560_a(p_i45569_3_);
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound p_143012_1_)
            {
                super.writeStructureToNBT(p_143012_1_);
                p_143012_1_.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
                p_143012_1_.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound p_143011_1_)
            {
                super.readStructureFromNBT(p_143011_1_);
                this.cropTypeA = Block.getBlockById(p_143011_1_.getInteger("CA"));
                this.cropTypeB = Block.getBlockById(p_143011_1_.getInteger("CB"));
            }

            private Block func_151560_a(Random p_151560_1_)
            {
                switch (p_151560_1_.nextInt(5))
                {
                    case 0:
                        return Blocks.carrots;
                    case 1:
                        return Blocks.potatoes;
                    default:
                        return Blocks.wheat;
                }
            }

            public static CulturalIndianStructures.Field2 func_175852_a(CulturalIndianStructures.Start p_175852_0_, List p_175852_1_, Random p_175852_2_, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing p_175852_6_, int p_175852_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, p_175852_6_);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ? new CulturalIndianStructures.Field2(p_175852_0_, p_175852_7_, p_175852_2_, structureboundingbox, p_175852_6_) : null;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
                }

                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 6, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 5, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 0, 8, 5, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
                int i;

                for (i = 1; i <= 7; ++i)
                {
                    this.func_175811_a(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 1, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 2, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 4, 1, i, p_74875_3_);
                    this.func_175811_a(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 5, 1, i, p_74875_3_);
                }

                for (i = 0; i < 9; ++i)
                {
                    for (int j = 0; j < 7; ++j)
                    {
                        this.clearCurrentPositionBlocksUpwards(worldIn, j, 4, i, p_74875_3_);
                        this.func_175808_b(worldIn, Blocks.dirt.getDefaultState(), j, -1, i, p_74875_3_);
                    }
                }

                return true;
            }
        }
    
    public static class House1 extends CulturalIndianStructures.Village
    {
        private static final String __OBFID = "CL_00000517";

        public House1() {}

        public House1(CulturalIndianStructures.Start p_i45571_1_, int p_i45571_2_, Random p_i45571_3_, StructureBoundingBox p_i45571_4_, EnumFacing p_i45571_5_)
        {
            super(p_i45571_1_, p_i45571_2_);
            this.coordBaseMode = p_i45571_5_;
            this.boundingBox = p_i45571_4_;
        }

        public static CulturalIndianStructures.House1 func_175850_a(CulturalIndianStructures.Start p_175850_0_, List p_175850_1_, Random p_175850_2_, int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing p_175850_6_, int p_175850_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, 11, 5, 11, p_175850_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175850_1_, structureboundingbox) == null ? new CulturalIndianStructures.House1(p_175850_0_, p_175850_7_, p_175850_2_, structureboundingbox, p_175850_6_) : null;
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
        {
            if (this.field_143015_k < 0)
            {
                this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                if (this.field_143015_k < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 5 - 1, 0);
            }

            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 9, 5, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, -4, 0, 9, -1, 9, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 9, 0, 9, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 8, 0, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            
            //this.spawnVillagers(worldIn, p_74875_3_, 2, 1, 2, 1);
            return true;
        }

        //protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        //{
        //    return 1;
        //}
    }

    public static class House2 extends CulturalIndianStructures.Village
        {
            /** List of items that Village's Blacksmith chest can contain. */
            private static final List villageBlacksmithChestContents = Lists.newArrayList(new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.sapling), 0, 3, 7, 5), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)});
            private boolean hasMadeChest;
            private static final String __OBFID = "CL_00000526";

            public House2() {}

            static
            {
                ChestGenHooks.init(VILLAGE_BLACKSMITH, villageBlacksmithChestContents, 3, 8);
            }

            public House2(CulturalIndianStructures.Start p_i45563_1_, int p_i45563_2_, Random p_i45563_3_, StructureBoundingBox p_i45563_4_, EnumFacing p_i45563_5_)
            {
                super(p_i45563_1_, p_i45563_2_);
                this.coordBaseMode = p_i45563_5_;
                this.boundingBox = p_i45563_4_;
            }

            public static CulturalIndianStructures.House2 func_175855_a(CulturalIndianStructures.Start p_175855_0_, List p_175855_1_, Random p_175855_2_, int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing p_175855_6_, int p_175855_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0, 18, 13, 19, p_175855_6_);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175855_1_, structureboundingbox) == null ? new CulturalIndianStructures.House2(p_175855_0_, p_175855_7_, p_175855_2_, structureboundingbox, p_175855_6_) : null;
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound p_143012_1_)
            {
                super.writeStructureToNBT(p_143012_1_);
                p_143012_1_.setBoolean("Chest", this.hasMadeChest);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound p_143011_1_)
            {
                super.readStructureFromNBT(p_143011_1_);
                this.hasMadeChest = p_143011_1_.getBoolean("Chest");
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 12 - 1, 0);
                }

                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 17, 11, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, -4, 0, 17, -1, 18, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 17, 0, 18, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
                //this.func_175804_a(worldIn, p_74875_3_, 1, 1, 3, 9, 1, 7, Blocks.carpet.getStateFromMeta(12), Blocks.carpet.getStateFromMeta(12), false);
                //this.func_175804_a(worldIn, p_74875_3_, 3, 1, 1, 7, 1, 2, Blocks.carpet.getStateFromMeta(12), Blocks.carpet.getStateFromMeta(12), false);
                //this.func_175804_a(worldIn, p_74875_3_, 3, 1, 8, 7, 1, 9, Blocks.carpet.getStateFromMeta(12), Blocks.carpet.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 7, 0, 1, 9, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 18, 1, 7, 18, 1, 9, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 0, 7, 1, 0, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 0, 12, 1, 0, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 1, 17, 10, 1, 17, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 5, 1, 1, 6, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 10, 1, 1, 11, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 1, 3, 2, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 1, 12, 2, 1, 13, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 3, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 3, 1, 14, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 5, 1, 1, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 15, 5, 1, 15, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 16, 7, 1, 16, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 16, 12, 1, 16, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 13, 1, 15, 14, 1, 15, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 13, 1, 1, 14, 1, 1, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 15, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 15, 1, 14, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 16, 1, 3, 16, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 16, 1, 12, 16, 1, 13, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 17, 1, 5, 17, 1, 6, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 17, 1, 10, 17, 1, 11, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 8, 3, 4, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 14, 9, 4, 14, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 15, 1, 8, 15, 4, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 4, 2, 9, 4, 3, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 5, 8, 5, 5, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 5, 12, 9, 5, 13, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 13, 5, 8, 14, 5, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 5, 3, 9, 5, 4, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 6, 8, 6, 6, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 6, 4, 9, 6, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 6, 11, 9, 6, 12, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 12, 6, 8, 13, 6, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 7, 8, 7, 7, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 7, 5, 9, 7, 6, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 7, 10, 9, 7, 11, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 7, 8, 12, 7, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 8, 8, 8, 8, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 8, 6, 9, 8, 7, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 8, 9, 9, 8, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 8, 8, 11, 8, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 7, 1, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 1, 5, 2, 1, 11, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 3, 3, 1, 5, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 11, 3, 1, 13, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 2, 4, 1, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 13, 4, 1, 14, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 1, 6, 1, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 14, 6, 1, 15, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 1, 15, 11, 2, 15, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 1, 16, 10, 1, 16, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 12, 1, 1, 12, 1, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 12, 1, 14, 12, 1, 15, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 14, 1, 2, 14, 1, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 14, 1, 13, 14, 1, 14, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 15, 1, 3, 15, 1, 5, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 15, 1, 11, 15, 1, 13, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 16, 1, 5, 16, 1, 11, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 17, 1, 7, 17, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 2, 6, 2, 2, 10, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 2, 4, 3, 2, 5, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 2, 11, 3, 2, 12, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 2, 2, 6, 2, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 2, 14, 6, 2, 14, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 12, 2, 2, 13, 2, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 12, 2, 14, 13, 2, 14, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 15, 2, 4, 15, 2, 5, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 15, 2, 11, 15, 2, 12, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 16, 2, 6, 16, 2, 10, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 3, 7, 2, 3, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 3, 5, 3, 3, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 3, 10, 3, 3, 11, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 3, 2, 7, 3, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 3, 14, 7, 3, 14, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 3, 15, 10, 3, 15, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 3, 2, 12, 3, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 3, 14, 12, 3, 14, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 15, 3, 5, 15, 3, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 15, 3, 10, 15, 3, 11, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 16, 3, 7, 16, 3, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 4, 5, 4, 4, 6, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 4, 10, 4, 4, 11, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 4, 3, 7, 4, 3, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 4, 13, 7, 4, 13, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 4, 3, 12, 4, 3, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 4, 13, 12, 4, 13, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 14, 4, 5, 14, 4, 6, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 14, 4, 10, 14, 4, 11, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 5, 5, 5, 5, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 5, 10, 5, 5, 11, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 5, 4, 7, 5, 4, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 5, 12, 7, 5, 12, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 5, 4, 12, 5, 4, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 5, 12, 12, 5, 12, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 13, 5, 5, 13, 5, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 13, 5, 10, 13, 5, 11, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 6, 7, 4, 6, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 6, 3, 10, 6, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 6, 13, 10, 6, 13, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 14, 6, 7, 14, 6, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 7, 7, 5, 7, 9, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 7, 12, 10, 7, 12, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 7, 4, 10, 7, 4, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 13, 7, 7, 13, 7, 9, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 7, 5, 7, 7, 6, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 7, 10, 7, 7, 11, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 7, 5, 11, 7, 6, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 7, 10, 11, 7, 11, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 8, 7, 6, 8, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 12, 8, 7, 12, 8, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 8, 5, 10, 8, 5, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 8, 11, 10, 8, 11, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 9, 7, 7, 9, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 9, 7, 11, 9, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 9, 6, 10, 9, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 9, 10, 10, 9, 10, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 10, 7, 8, 10, 9, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 10, 7, 10, 10, 9, Blocks.wool.getStateFromMeta(0), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 1, 14, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 13, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 13, 1, 14, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 2, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 2, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 2, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 14, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 14, 2, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 3, 12, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 3, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 10, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 13, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 13, 3, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 14, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 14, 3, 12, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 2, 4, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 4, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 4, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 4, 12, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 4, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 4, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 10, 4, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 4, 14, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 4, 15, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 10, 4, 14, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 13, 4, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 13, 4, 12, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 15, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 15, 4, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 16, 4, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 5, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 5, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 5, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 5, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 5, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 5, 14, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 5, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 14, 5, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 14, 5, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 15, 5, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 6, 10, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 6, 11, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 6, 12, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 6, 12, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 12, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 12, 6, 11, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 13, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 13, 6, 10, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 7, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 7, 10, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 12, 7, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 12, 7, 10, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 8, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 8, 10, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 8, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 8, 10, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 9, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 9, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 9, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 9, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 10, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 10, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 11, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 11, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 11, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 11, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 12, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 9, 4, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 8, 9, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 9, 9, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 9, 9, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 10, 9, 8, p_74875_3_);
                
                this.func_175811_a(worldIn, Blocks.cauldron.getDefaultState(), 3, 1, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.brewing_stand.getDefaultState(), 3, 1, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.crafting_table.getDefaultState(), 11, 1, 14, p_74875_3_);
                
                //this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
                
                BlockPos blockpos = new BlockPos(this.getXWithOffset(3, 6), this.getYWithOffset(1), this.getZWithOffset(3, 6));
                if (p_74875_3_.func_175898_b(blockpos))
                {
                	worldIn.setBlockState(blockpos, Blocks.chest.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.EAST));
                }
                
                BlockPos blockpos1 = new BlockPos(this.getXWithOffset(7, 14), this.getYWithOffset(1), this.getZWithOffset(8, 14));
                if (p_74875_3_.func_175898_b(blockpos1))
                {
                	worldIn.setBlockState(blockpos1, Blocks.furnace.getDefaultState().withProperty(BlockFurnace.FACING, EnumFacing.SOUTH));
                }

                this.spawnVillagers(worldIn, p_74875_3_, 7, 1, 1, 1);
                return true;
            }

            protected int func_180779_c(int p_180779_1_, int p_180779_2_)
            {
                return 3;
            }
        }

    public static class House3 extends CulturalIndianStructures.Village
        {
            private static final String __OBFID = "CL_00000530";

            public House3() {}

            public House3(CulturalIndianStructures.Start p_i45561_1_, int p_i45561_2_, Random p_i45561_3_, StructureBoundingBox p_i45561_4_, EnumFacing p_i45561_5_)
            {
                super(p_i45561_1_, p_i45561_2_);
                this.coordBaseMode = p_i45561_5_;
                this.boundingBox = p_i45561_4_;
            }

            public static CulturalIndianStructures.House3 func_175849_a(CulturalIndianStructures.Start p_175849_0_, List p_175849_1_, Random p_175849_2_, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing p_175849_6_, int p_175849_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 13, 8, 12, p_175849_6_);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null ? new CulturalIndianStructures.House3(p_175849_0_, p_175849_7_, p_175849_2_, structureboundingbox, p_175849_6_) : null;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
                }
                
                
                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 12, 7, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, -4, 0, 13, 0, 13, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 0, 1, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 12, 1, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 3, 1, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 6, 1, 1, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 2, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 2, 1, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 3, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 3, 1, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 4, 1, 0, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 8, 1, 0, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 10, 5, 1, 10, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 6, 1, 11, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 7, 1, 10, 8, 1, 10, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 9, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 9, 1, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 10, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 10, 1, 8, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 3, 11, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 6, 11, 1, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 5, 3, 3, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 8, 6, 3, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 5, 9, 3, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 1, 1, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 1, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 2, 1, 3, 2, 1, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 2, 3, 1, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 3, 1, 8, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 1, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 8, 4, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 1, 9, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 9, 6, 1, 10, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 1, 9, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 8, 1, 1, 8, 1, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 1, 8, 8, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 2, 9, 1, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 7, 9, 1, 8, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 1, 3, 10, 1, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 2, 4, 2, 2, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 2, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 2, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 2, 8, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 5, 2, 9, 7, 2, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 2, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 2, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 2, 7, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 10, 2, 4, 10, 2, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 2, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 3, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 3, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 3, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 3, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 10, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 4, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 4, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 4, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 4, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 4, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 4, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 8, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 5, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 7, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 7, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 8, 5, p_74875_3_);
                
                
                this.spawnVillagers(worldIn, p_74875_3_, 4, 1, 2, 2);
                return true;
            }
        }

    public static class House4Garden extends CulturalIndianStructures.Village
        {
            private static final String __OBFID = "CL_00000523";

            public House4Garden() {}

            public House4Garden(CulturalIndianStructures.Start p_i45566_1_, int p_i45566_2_, Random p_i45566_3_, StructureBoundingBox p_i45566_4_, EnumFacing p_i45566_5_)
            {
                super(p_i45566_1_, p_i45566_2_);
                this.coordBaseMode = p_i45566_5_;
                this.boundingBox = p_i45566_4_;
            }

            public static CulturalIndianStructures.House4Garden func_175858_a(CulturalIndianStructures.Start p_175858_0_, List p_175858_1_, Random p_175858_2_, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing p_175858_6_, int p_175858_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 13, 8, 12, p_175858_6_);
                return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new CulturalIndianStructures.House4Garden(p_175858_0_, p_175858_7_, p_175858_2_, structureboundingbox, p_175858_6_);
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
                }

                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 12, 7, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, -4, 0, 13, 0, 13, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 0, 1, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 12, 1, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 3, 1, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 6, 1, 1, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 2, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 2, 1, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 3, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 3, 1, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 4, 1, 0, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 8, 1, 0, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 10, 5, 1, 10, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 6, 1, 11, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 7, 1, 10, 8, 1, 10, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 9, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 9, 1, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 10, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 10, 1, 8, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 3, 11, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 11, 1, 6, 11, 1, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 5, 3, 3, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 8, 6, 3, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 5, 9, 3, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 1, 1, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 11, 1, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 2, 1, 3, 2, 1, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 2, 3, 1, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 7, 3, 1, 8, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 1, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 8, 4, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 1, 9, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 9, 6, 1, 10, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 1, 9, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 8, 1, 1, 8, 1, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 1, 8, 8, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 2, 9, 1, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 7, 9, 1, 8, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 1, 3, 10, 1, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 2, 4, 2, 2, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 2, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 2, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 2, 8, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 5, 2, 9, 7, 2, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 2, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 2, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 2, 7, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 10, 2, 4, 10, 2, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 2, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 3, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 3, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 3, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 3, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 10, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 4, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 4, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 4, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 4, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 4, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 4, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 9, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 8, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 5, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 7, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 7, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 8, 5, p_74875_3_);

                this.spawnVillagers(worldIn, p_74875_3_, 1, 1, 2, 1);
                return true;
            }
        }

    public static class Path extends CulturalIndianStructures.Road
        {
            private int averageGroundLevel;
            private static final String __OBFID = "CL_00000528";

            public Path() {}

            public Path(CulturalIndianStructures.Start p_i45562_1_, int p_i45562_2_, Random p_i45562_3_, StructureBoundingBox p_i45562_4_, EnumFacing p_i45562_5_)
            {
                super(p_i45562_1_, p_i45562_2_);
                this.coordBaseMode = p_i45562_5_;
                this.boundingBox = p_i45562_4_;
                this.averageGroundLevel = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound p_143012_1_)
            {
                super.writeStructureToNBT(p_143012_1_);
                p_143012_1_.setInteger("Length", this.averageGroundLevel);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound p_143011_1_)
            {
                super.readStructureFromNBT(p_143011_1_);
                this.averageGroundLevel = p_143011_1_.getInteger("Length");
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                boolean flag = false;
                int i;
                StructureComponent structurecomponent1;

                for (i = p_74861_3_.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + p_74861_3_.nextInt(5))
                {
                    structurecomponent1 = this.getNextComponentNN((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, i);

                    if (structurecomponent1 != null)
                    {
                        i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                        flag = true;
                    }
                }

                for (i = p_74861_3_.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + p_74861_3_.nextInt(5))
                {
                    structurecomponent1 = this.getNextComponentPP((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, i);

                    if (structurecomponent1 != null)
                    {
                        i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                        flag = true;
                    }
                }

                if (flag && p_74861_3_.nextInt(3) > 0 && this.coordBaseMode != null)
                {
                    switch (CulturalIndianStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                    {
                        case 1:
                            CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
                            break;
                        case 2:
                            CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
                            break;
                        case 3:
                            CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                            break;
                        case 4:
                            CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    }
                }

                if (flag && p_74861_3_.nextInt(3) > 0 && this.coordBaseMode != null)
                {
                    switch (CulturalIndianStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                    {
                        case 1:
                            CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
                            break;
                        case 2:
                            CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
                            break;
                        case 3:
                            CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                            break;
                        case 4:
                            CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    }
                }
            }

            public static StructureBoundingBox func_175848_a(CulturalIndianStructures.Start p_175848_0_, List p_175848_1_, Random p_175848_2_, int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing p_175848_6_)
            {
                for (int l = 7 * MathHelper.getRandomIntegerInRange(p_175848_2_, 3, 5); l >= 7; l -= 7)
                {
                    StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0, 0, 3, 3, l, p_175848_6_);

                    if (StructureComponent.findIntersecting(p_175848_1_, structureboundingbox) == null)
                    {
                        return structureboundingbox;
                    }
                }

                return null;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                IBlockState iblockstate = this.func_175847_a(Blocks.gravel.getDefaultState());
                IBlockState iblockstate1 = this.func_175847_a(Blocks.cobblestone.getDefaultState());

                for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
                {
                    for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
                    {
                        BlockPos blockpos = new BlockPos(i, 64, j);

                        if (p_74875_3_.func_175898_b(blockpos))
                        {
                            blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos).down();
                            worldIn.setBlockState(blockpos, iblockstate, 2);
                            worldIn.setBlockState(blockpos.down(), iblockstate1, 2);
                        }
                    }
                }

                return true;
            }
        }

    public static class PieceWeight
        {
            /** The Class object for the represantation of this village piece. */
            public Class villagePieceClass;
            public final int villagePieceWeight;
            public int villagePiecesSpawned;
            public int villagePiecesLimit;
            private static final String __OBFID = "CL_00000521";

            public PieceWeight(Class p_i2098_1_, int p_i2098_2_, int p_i2098_3_)
            {
                this.villagePieceClass = p_i2098_1_;
                this.villagePieceWeight = p_i2098_2_;
                this.villagePiecesLimit = p_i2098_3_;
            }

            public boolean canSpawnMoreVillagePiecesOfType(int p_75085_1_)
            {
                return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
            }

            public boolean canSpawnMoreVillagePieces()
            {
                return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
            }
        }

    public abstract static class Road extends CulturalIndianStructures.Village
        {
            private static final String __OBFID = "CL_00000532";

            public Road() {}

            protected Road(CulturalIndianStructures.Start p_i2108_1_, int p_i2108_2_)
            {
                super(p_i2108_1_, p_i2108_2_);
            }
        }

    public static class Start extends CulturalIndianStructures.Well
        {
            public WorldChunkManager worldChunkMngr;
            /** Boolean that determines if the village is in a desert or not. */
            public boolean inDesert;
            /** World terrain type, 0 for normal, 1 for flap map */
            public int terrainType;
            public CulturalIndianStructures.PieceWeight structVillagePieceWeight;
            /**
             * Contains List of all spawnable Structure Piece Weights. If no more Pieces of a type can be spawned, they
             * are removed from this list
             */
            public List structureVillageWeightedPieceList;
            public List field_74932_i = Lists.newArrayList();
            public List field_74930_j = Lists.newArrayList();
            private static final String __OBFID = "CL_00000527";
            public BiomeGenBase biome;

            public Start() {}

            public Start(WorldChunkManager p_i2104_1_, int p_i2104_2_, Random p_i2104_3_, int p_i2104_4_, int p_i2104_5_, List p_i2104_6_, int p_i2104_7_)
            {
                super((CulturalIndianStructures.Start)null, 0, p_i2104_3_, p_i2104_4_, p_i2104_5_);
                this.worldChunkMngr = p_i2104_1_;
                this.structureVillageWeightedPieceList = p_i2104_6_;
                this.terrainType = p_i2104_7_;
                BiomeGenBase biomegenbase = p_i2104_1_.func_180300_a(new BlockPos(p_i2104_4_, 0, p_i2104_5_), BiomeGenBase.field_180279_ad);
                this.inDesert = biomegenbase == BiomeGenBase.desert || biomegenbase == BiomeGenBase.desertHills;
                this.biome = biomegenbase;
                this.func_175846_a(this.inDesert);
            }

            public WorldChunkManager getWorldChunkManager()
            {
                return this.worldChunkMngr;
            }
        }

    static final class SwitchEnumFacing
        {
            static final int[] field_176064_a = new int[EnumFacing.values().length];
            private static final String __OBFID = "CL_00001968";

            static
            {
                try
                {
                    field_176064_a[EnumFacing.NORTH.ordinal()] = 1;
                }
                catch (NoSuchFieldError var4)
                {
                    ;
                }

                try
                {
                    field_176064_a[EnumFacing.SOUTH.ordinal()] = 2;
                }
                catch (NoSuchFieldError var3)
                {
                    ;
                }

                try
                {
                    field_176064_a[EnumFacing.WEST.ordinal()] = 3;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    field_176064_a[EnumFacing.EAST.ordinal()] = 4;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }

    public static class Torch extends CulturalIndianStructures.Village
        {
            private static final String __OBFID = "CL_00000520";

            public Torch() {}

            public Torch(CulturalIndianStructures.Start p_i45568_1_, int p_i45568_2_, Random p_i45568_3_, StructureBoundingBox p_i45568_4_, EnumFacing p_i45568_5_)
            {
                super(p_i45568_1_, p_i45568_2_);
                this.coordBaseMode = p_i45568_5_;
                this.boundingBox = p_i45568_4_;
            }

            public static StructureBoundingBox func_175856_a(CulturalIndianStructures.Start p_175856_0_, List p_175856_1_, Random p_175856_2_, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing p_175856_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 3, 4, 2, p_175856_6_);
                return StructureComponent.findIntersecting(p_175856_1_, structureboundingbox) != null ? null : structureboundingbox;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
                }

                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 2, 3, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 0, 0, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 1, 0, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 2, 0, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(EnumDyeColor.WHITE.getDyeDamage()), 1, 3, 0, p_74875_3_);
                boolean flag = this.coordBaseMode == EnumFacing.EAST || this.coordBaseMode == EnumFacing.NORTH;
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), flag ? 2 : 0, 3, 0, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 1, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), flag ? 0 : 2, 3, 0, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 1, 3, -1, p_74875_3_);
                return true;
            }
        }

    public abstract static class Village extends StructureComponent
        {
            protected int field_143015_k = -1;
            /** The number of villagers that have been spawned in this component. */
            private int villagersSpawned;
            private boolean field_143014_b;
            private static final String __OBFID = "CL_00000531";
            private CulturalIndianStructures.Start startPiece;

            public Village() {}

            protected Village(CulturalIndianStructures.Start p_i2107_1_, int p_i2107_2_)
            {
                super(p_i2107_2_);

                if (p_i2107_1_ != null)
                {
                    this.field_143014_b = p_i2107_1_.inDesert;
                    startPiece = p_i2107_1_;
                }
            }

            /**
             * (abstract) Helper method to write subclass data to NBT
             */
            protected void writeStructureToNBT(NBTTagCompound p_143012_1_)
            {
                p_143012_1_.setInteger("HPos", this.field_143015_k);
                p_143012_1_.setInteger("VCount", this.villagersSpawned);
                p_143012_1_.setBoolean("Desert", this.field_143014_b);
            }

            /**
             * (abstract) Helper method to read subclass data from NBT
             */
            protected void readStructureFromNBT(NBTTagCompound p_143011_1_)
            {
                this.field_143015_k = p_143011_1_.getInteger("HPos");
                this.villagersSpawned = p_143011_1_.getInteger("VCount");
                this.field_143014_b = p_143011_1_.getBoolean("Desert");
            }

            /**
             * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
             */
            protected StructureComponent getNextComponentNN(CulturalIndianStructures.Start p_74891_1_, List p_74891_2_, Random p_74891_3_, int p_74891_4_, int p_74891_5_)
            {
                if (this.coordBaseMode != null)
                {
                    switch (CulturalIndianStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                    {
                        case 1:
                            return CulturalIndianStructures.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                        case 2:
                            return CulturalIndianStructures.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                        case 3:
                            return CulturalIndianStructures.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                        case 4:
                            return CulturalIndianStructures.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    }
                }

                return null;
            }

            /**
             * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
             */
            protected StructureComponent getNextComponentPP(CulturalIndianStructures.Start p_74894_1_, List p_74894_2_, Random p_74894_3_, int p_74894_4_, int p_74894_5_)
            {
                if (this.coordBaseMode != null)
                {
                    switch (CulturalIndianStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                    {
                        case 1:
                            return CulturalIndianStructures.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                        case 2:
                            return CulturalIndianStructures.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                        case 3:
                            return CulturalIndianStructures.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                        case 4:
                            return CulturalIndianStructures.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    }
                }

                return null;
            }

            /**
             * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of
             * all the levels in the BB's horizontal rectangle).
             */
            protected int getAverageGroundLevel(World worldIn, StructureBoundingBox p_74889_2_)
            {
                int i = 0;
                int j = 0;

                for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
                {
                    for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                    {
                        BlockPos blockpos = new BlockPos(l, 64, k);

                        if (p_74889_2_.func_175898_b(blockpos))
                        {
                            i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos).getY(), worldIn.provider.getAverageGroundLevel());
                            ++j;
                        }
                    }
                }

                if (j == 0)
                {
                    return -1;
                }
                else
                {
                    return i / j;
                }
            }

            protected static boolean canVillageGoDeeper(StructureBoundingBox p_74895_0_)
            {
                return p_74895_0_ != null && p_74895_0_.minY > 10;
            }

            /**
             * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y
             * offset, z offset, number of villagers
             */
            protected void spawnVillagers(World worldIn, StructureBoundingBox p_74893_2_, int p_74893_3_, int p_74893_4_, int p_74893_5_, int p_74893_6_)
            {
                if (this.villagersSpawned < p_74893_6_)
                {
                    for (int i1 = this.villagersSpawned; i1 < p_74893_6_; ++i1)
                    {
                        int j1 = this.getXWithOffset(p_74893_3_ + i1, p_74893_5_);
                        int k1 = this.getYWithOffset(p_74893_4_);
                        int l1 = this.getZWithOffset(p_74893_3_ + i1, p_74893_5_);

                        if (!p_74893_2_.func_175898_b(new BlockPos(j1, k1, l1)))
                        {
                            break;
                        }

                        ++this.villagersSpawned;
                        EntityVillager entityvillager = new EntityVillager(worldIn);
                        entityvillager.setLocationAndAngles((double)j1 + 0.5D, (double)k1, (double)l1 + 0.5D, 0.0F, 0.0F);
                        entityvillager.func_180482_a(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null);
                        entityvillager.setProfession(this.func_180779_c(i1, entityvillager.getProfession()));
                        worldIn.spawnEntityInWorld(entityvillager);
                    }
                }
            }

            protected int func_180779_c(int p_180779_1_, int p_180779_2_)
            {
                return p_180779_2_;
            }

            protected IBlockState func_175847_a(IBlockState p_175847_1_)
            {
                BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(startPiece == null ? null : startPiece.biome, p_175847_1_);
                MinecraftForge.TERRAIN_GEN_BUS.post(event);
                if (event.getResult() == Result.DENY) return event.replacement;
                if (this.field_143014_b)
                {
                    if (p_175847_1_.getBlock() == Blocks.log || p_175847_1_.getBlock() == Blocks.log2)
                    {
                        return Blocks.sandstone.getDefaultState();
                    }

                    if (p_175847_1_.getBlock() == Blocks.cobblestone)
                    {
                        return Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
                    }

                    if (p_175847_1_.getBlock() == Blocks.planks)
                    {
                        return Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata());
                    }

                    if (p_175847_1_.getBlock() == Blocks.oak_stairs)
                    {
                        return Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, p_175847_1_.getValue(BlockStairs.FACING));
                    }

                    if (p_175847_1_.getBlock() == Blocks.stone_stairs)
                    {
                        return Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, p_175847_1_.getValue(BlockStairs.FACING));
                    }

                    if (p_175847_1_.getBlock() == Blocks.gravel)
                    {
                        return Blocks.sandstone.getDefaultState();
                    }
                }

                return p_175847_1_;
            }

            protected void func_175811_a(World worldIn, IBlockState p_175811_2_, int p_175811_3_, int p_175811_4_, int p_175811_5_, StructureBoundingBox p_175811_6_)
            {
                IBlockState iblockstate1 = this.func_175847_a(p_175811_2_);
                super.func_175811_a(worldIn, iblockstate1, p_175811_3_, p_175811_4_, p_175811_5_, p_175811_6_);
            }

            protected void func_175804_a(World worldIn, StructureBoundingBox p_175804_2_, int p_175804_3_, int p_175804_4_, int p_175804_5_, int p_175804_6_, int p_175804_7_, int p_175804_8_, IBlockState p_175804_9_, IBlockState p_175804_10_, boolean p_175804_11_)
            {
                IBlockState iblockstate2 = this.func_175847_a(p_175804_9_);
                IBlockState iblockstate3 = this.func_175847_a(p_175804_10_);
                super.func_175804_a(worldIn, p_175804_2_, p_175804_3_, p_175804_4_, p_175804_5_, p_175804_6_, p_175804_7_, p_175804_8_, iblockstate2, iblockstate3, p_175804_11_);
            }

            protected void func_175808_b(World worldIn, IBlockState p_175808_2_, int p_175808_3_, int p_175808_4_, int p_175808_5_, StructureBoundingBox p_175808_6_)
            {
                IBlockState iblockstate1 = this.func_175847_a(p_175808_2_);
                super.func_175808_b(worldIn, iblockstate1, p_175808_3_, p_175808_4_, p_175808_5_, p_175808_6_);
            }

            protected void func_175846_a(boolean p_175846_1_)
            {
                this.field_143014_b = p_175846_1_;
            }
        }

    public static class Well extends CulturalIndianStructures.Village
        {
            private static final String __OBFID = "CL_00000533";

            public Well() {}

            public Well(CulturalIndianStructures.Start p_i2109_1_, int p_i2109_2_, Random p_i2109_3_, int p_i2109_4_, int p_i2109_5_)
            {
                super(p_i2109_1_, p_i2109_2_);
                this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2109_3_);

                switch (CulturalIndianStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                {
                    case 1:
                    case 2:
                        this.boundingBox = new StructureBoundingBox(p_i2109_4_, 1, p_i2109_5_, p_i2109_4_ + 8 - 1, 15, p_i2109_5_ + 8 - 1);
                        break;
                    default:
                        this.boundingBox = new StructureBoundingBox(p_i2109_4_, 1, p_i2109_5_, p_i2109_4_ + 8 - 1, 15, p_i2109_5_ + 8 - 1);
                }
            }

            /**
             * Initiates construction of the Structure Component picked, at the current Location of StructGen
             */
            public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
                CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
                CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                CulturalIndianStructures.func_176069_e((CulturalIndianStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 11, 0);
                }
                
                int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
                int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
                int k = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
                int l = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
                
                //Totem Pole Layer 1
                this.func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 7, 2, 7, Blocks.dirt.getStateFromMeta(1), Blocks.dirt.getStateFromMeta(1), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 3, 1, 1, 3, 5, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 3, 2, 6, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 3, 5, 5, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 3, 1, 5, 3, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 3, 0, 6, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 3, 6, 6, 3, 6, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 3, 1, 7, 3, 5, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 6, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 6, 3, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 2, 4, 2, 2, 4, 4, Blocks.oak_stairs.getStateFromMeta(l), Blocks.oak_stairs.getStateFromMeta(l), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 4, 2, 6, 4, 4, Blocks.oak_stairs.getStateFromMeta(k), Blocks.oak_stairs.getStateFromMeta(k), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 4, 1, 5, 4, 1, Blocks.oak_stairs.getStateFromMeta(i), Blocks.oak_stairs.getStateFromMeta(i), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 4, 5, 5, 4, 5, Blocks.oak_stairs.getStateFromMeta(j), Blocks.oak_stairs.getStateFromMeta(j), false);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 2, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 4, 5, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 4, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 6, 5, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 5, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 5, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 6, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 4, 6, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 4, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 6, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.gold_block.getDefaultState(), 3, 6, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.gold_block.getDefaultState(), 3, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.gold_block.getDefaultState(), 5, 6, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.gold_block.getDefaultState(), 5, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 2, 6, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 4, 6, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 4, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 6, 6, 3, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 3, 7, 2, 3, 7, 4, Blocks.oak_stairs.getStateFromMeta(l), Blocks.oak_stairs.getStateFromMeta(l), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 7, 2, 5, 7, 4, Blocks.oak_stairs.getStateFromMeta(k), Blocks.oak_stairs.getStateFromMeta(k), false);
                this.func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(i), 4, 7, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(j), 4, 7, 4, p_74875_3_);
                //Layer 2
                this.func_175804_a(worldIn, p_74875_3_, 2, 8, 2, 6, 8, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 8, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 6, 8, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 8, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 6, 8, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 2, 8, 2, 2, 8, 4, Blocks.oak_stairs.getStateFromMeta(l), Blocks.oak_stairs.getStateFromMeta(l), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 8, 2, 6, 8, 4, Blocks.oak_stairs.getStateFromMeta(k), Blocks.oak_stairs.getStateFromMeta(k), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 8, 1, 5, 8, 1, Blocks.oak_stairs.getStateFromMeta(i), Blocks.oak_stairs.getStateFromMeta(i), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 8, 5, 5, 8, 5, Blocks.oak_stairs.getStateFromMeta(j), Blocks.oak_stairs.getStateFromMeta(j), false);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 2, 9, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 4, 9, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 4, 9, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 6, 9, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 9, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 9, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 9, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 9, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 10, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 4, 10, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 4, 10, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 10, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.diamond_block.getDefaultState(), 3, 10, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.diamond_block.getDefaultState(), 3, 10, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.diamond_block.getDefaultState(), 5, 10, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.diamond_block.getDefaultState(), 5, 10, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 2, 10, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 4, 10, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 4, 10, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 6, 10, 3, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 3, 11, 2, 3, 11, 4, Blocks.oak_stairs.getStateFromMeta(l), Blocks.oak_stairs.getStateFromMeta(l), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 11, 2, 5, 11, 4, Blocks.oak_stairs.getStateFromMeta(k), Blocks.oak_stairs.getStateFromMeta(k), false);
                this.func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(i), 4, 11, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(j), 4, 11, 4, p_74875_3_);
                //Layer 3
                this.func_175804_a(worldIn, p_74875_3_, 2, 12, 2, 6, 12, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 12, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 6, 12, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 12, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.log.getDefaultState(), 6, 12, 5, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 2, 12, 2, 2, 12, 4, Blocks.oak_stairs.getStateFromMeta(l), Blocks.oak_stairs.getStateFromMeta(l), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 12, 2, 6, 12, 4, Blocks.oak_stairs.getStateFromMeta(k), Blocks.oak_stairs.getStateFromMeta(k), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 12, 1, 5, 12, 1, Blocks.oak_stairs.getStateFromMeta(i), Blocks.oak_stairs.getStateFromMeta(i), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 12, 5, 5, 12, 5, Blocks.oak_stairs.getStateFromMeta(j), Blocks.oak_stairs.getStateFromMeta(j), false);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 2, 13, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 4, 13, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 4, 13, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(3), 6, 13, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 13, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 13, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 13, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 13, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 14, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 4, 14, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 4, 14, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 14, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.emerald_block.getDefaultState(), 3, 14, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.emerald_block.getDefaultState(), 3, 14, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.emerald_block.getDefaultState(), 5, 14, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.emerald_block.getDefaultState(), 5, 14, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 2, 14, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 4, 14, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 4, 14, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getStateFromMeta(3), 6, 14, 3, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 3, 15, 2, 3, 15, 4, Blocks.oak_stairs.getStateFromMeta(l), Blocks.oak_stairs.getStateFromMeta(l), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 15, 2, 5, 15, 4, Blocks.oak_stairs.getStateFromMeta(k), Blocks.oak_stairs.getStateFromMeta(k), false);
                this.func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(i), 4, 15, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(j), 4, 15, 4, p_74875_3_);
                
                for (int i1 = 0; i1 <= 6; ++i1)
                {
                    for (int j1 = 0; j1 <= 6; ++j1)
                    {
                        if (j1 == 0 || j1 == 5 || i1 == 0 || i1 == 5)
                        {
                            //this.func_175811_a(worldIn, Blocks.gravel.getDefaultState(), j, 11, i, p_74875_3_);
                            //this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, p_74875_3_);
                        }
                    }
                }

                return true;
            }
        }

    public static class WoodHut extends CulturalIndianStructures.Village
        {
            private static final String __OBFID = "CL_00000524";

            public WoodHut() {}

            public WoodHut(CulturalIndianStructures.Start p_i45565_1_, int p_i45565_2_, Random p_i45565_3_, StructureBoundingBox p_i45565_4_, EnumFacing p_i45565_5_)
            {
                super(p_i45565_1_, p_i45565_2_);
                this.coordBaseMode = p_i45565_5_;
                this.boundingBox = p_i45565_4_;
            }

            public static CulturalIndianStructures.WoodHut func_175853_a(CulturalIndianStructures.Start p_175853_0_, List p_175853_1_, Random p_175853_2_, int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing p_175853_6_, int p_175853_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, 11, 10, 11, p_175853_6_);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175853_1_, structureboundingbox) == null ? new CulturalIndianStructures.WoodHut(p_175853_0_, p_175853_7_, p_175853_2_, structureboundingbox, p_175853_6_) : null;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(worldIn, p_74875_3_);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 10 - 1, 0);
                }

                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 10, 10, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, -4, 0, 10, -1, 10, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 10, 0, 10, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 3, 9, 1, 7, Blocks.carpet.getStateFromMeta(12), Blocks.carpet.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 1, 7, 1, 2, Blocks.carpet.getStateFromMeta(12), Blocks.carpet.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 8, 7, 1, 9, Blocks.carpet.getStateFromMeta(12), Blocks.carpet.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 3, 0, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 6, 0, 1, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 5, 1, 0, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 10, 4, 1, 10, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, 10, 7, 1, 10, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 1, 3, 10, 1, 4, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 1, 6, 10, 1, 7, Blocks.wooden_slab.getDefaultState(), Blocks.wooden_slab.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 1, 5, 2, 3, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 1, 2, 5, 3, 2, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 5, 1, 8, 5, 3, 8, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 1, 5, 8, 3, 5, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 1, 3, 1, 1, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, 3, 9, 1, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 9, 7, 1, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, 1, 5, 1, 1, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 2, 4, 1, 2, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 2, 4, 9, 2, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 2, 1, 5, 2, 1, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 2, 9, 6, 2, 9, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 2, 4, 4, 2, 4, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 8, 4, 4, 8, 4, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 4, 2, 6, 4, 2, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 4, 8, 6, 4, 8, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 3, 5, 4, 3, 5, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 7, 5, 4, 7, 5, 6, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 5, 3, 6, 5, 3, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 5, 7, 6, 5, 7, Blocks.wool.getStateFromMeta(12), Blocks.wool.getStateFromMeta(12), false);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 0, 1, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 1, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 1, 1, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 2, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 2, 1, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 9, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 9, 1, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 8, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 8, 1, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wooden_slab.getDefaultState(), 7, 1, 0, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 10, 1, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 1, 10, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 1, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 2, 1, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 2, 1, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 1, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 2, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 2, 2, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 2, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 2, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 2, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 2, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 2, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 2, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 8, 2, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 1, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 2, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 2, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 3, 3, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 3, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 3, 9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 3, 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 3, 8, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 3, 2, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 3, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 7, 3, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 8, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 9, 3, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 3, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 6, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 6, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 7, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 7, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 7, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 7, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 7, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 4, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 7, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 7, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 6, 7, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 4, 8, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 8, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 5, 8, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(12), 6, 8, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.wool.getStateFromMeta(0), 5, 9, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 3, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 4, 3, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 4, 7, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 7, 4, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 5, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 5, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 5, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 6, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 6, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 6, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 9, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 9, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 9, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 9, 5, p_74875_3_);
                
                //this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

                if (this.func_175807_a(worldIn, 1, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, 1, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
                {
                    //this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, p_74875_3_);
                }
                
                this.spawnVillagers(worldIn, p_74875_3_, 1, 1, 2, 1);
                return true;
            }
        }
}