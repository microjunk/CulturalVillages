package culturalvillages.pieces;

import static net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import culturalvillages.mapgen.MapGenCulturalVillages;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

public class CulturalHalloweenStructures
{
	private static World world;
	private static final String __OBFID = "CL_00000516";

    public static void registerVillagePieces()
    {
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.House1.class, "ViBH");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.Field1.class, "ViDF");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.Field2.class, "ViF");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.Torch.class, "ViL");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.Hall.class, "ViPH");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.House4Garden.class, "ViSH");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.WoodHut.class, "ViSmH");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.Church.class, "ViST");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.House2.class, "ViS");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.Start.class, "ViStart");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.Path.class, "ViSR");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.House3.class, "ViTRH");
        MapGenStructureIO.registerStructureComponent(CulturalHalloweenStructures.Well.class, "ViW");
    }

    public static List getStructureVillageWeightedPieceList(Random p_75084_0_, int p_75084_1_)
    {
        ArrayList arraylist = Lists.newArrayList();
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.House4Garden.class, 4, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.Church.class, 20, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 1 + p_75084_1_)));
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.House1.class, 20, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 2 + p_75084_1_)));
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.WoodHut.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 5 + p_75084_1_ * 3)));
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.Hall.class, 15, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 2 + p_75084_1_)));
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.Field1.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 1 + p_75084_1_, 4 + p_75084_1_)));
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.Field2.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.House2.class, 15, MathHelper.getRandomIntegerInRange(p_75084_0_, 0, 1 + p_75084_1_)));
        arraylist.add(new CulturalHalloweenStructures.PieceWeight(CulturalHalloweenStructures.House3.class, 8, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 3 + p_75084_1_ * 2)));
        net.minecraftforge.fml.common.registry.VillagerRegistry.addExtraVillageComponents(arraylist, p_75084_0_, p_75084_1_);

        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext())
        {
            if (((CulturalHalloweenStructures.PieceWeight)iterator.next()).villagePiecesLimit == 0)
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
        CulturalHalloweenStructures.PieceWeight pieceweight;

        for (Iterator iterator = p_75079_0_.iterator(); iterator.hasNext(); i += pieceweight.villagePieceWeight)
        {
            pieceweight = (CulturalHalloweenStructures.PieceWeight)iterator.next();

            if (pieceweight.villagePiecesLimit > 0 && pieceweight.villagePiecesSpawned < pieceweight.villagePiecesLimit)
            {
                flag = true;
            }
        }

        return flag ? i : -1;
    }

    private static CulturalHalloweenStructures.Village func_176065_a(CulturalHalloweenStructures.Start p_176065_0_, CulturalHalloweenStructures.PieceWeight p_176065_1_, List p_176065_2_, Random p_176065_3_, int p_176065_4_, int p_176065_5_, int p_176065_6_, EnumFacing p_176065_7_, int p_176065_8_)
    {
        Class oclass = p_176065_1_.villagePieceClass;
        Object object = null;

        if (oclass == CulturalHalloweenStructures.House4Garden.class)
        {
            object = CulturalHalloweenStructures.House4Garden.func_175858_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalHalloweenStructures.Church.class)
        {
            object = CulturalHalloweenStructures.Church.func_175854_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalHalloweenStructures.House1.class)
        {
            object = CulturalHalloweenStructures.House1.func_175850_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalHalloweenStructures.WoodHut.class)
        {
            object = CulturalHalloweenStructures.WoodHut.func_175853_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalHalloweenStructures.Hall.class)
        {
            object = CulturalHalloweenStructures.Hall.func_175857_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalHalloweenStructures.Field1.class)
        {
            object = CulturalHalloweenStructures.Field1.func_175851_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalHalloweenStructures.Field2.class)
        {
            object = CulturalHalloweenStructures.Field2.func_175852_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalHalloweenStructures.House2.class)
        {
            object = CulturalHalloweenStructures.House2.func_175855_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else if (oclass == CulturalHalloweenStructures.House3.class)
        {
            object = CulturalHalloweenStructures.House3.func_175849_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }
        else
        {
            //object = net.minecraftforge.fml.common.registry.VillagerRegistry.getVillageComponent(p_176065_1_, p_176065_0_ , p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
        }

        return (CulturalHalloweenStructures.Village)object;
    }

    private static CulturalHalloweenStructures.Village func_176067_c(CulturalHalloweenStructures.Start p_176067_0_, List p_176067_1_, Random p_176067_2_, int p_176067_3_, int p_176067_4_, int p_176067_5_, EnumFacing p_176067_6_, int p_176067_7_)
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
                    CulturalHalloweenStructures.PieceWeight pieceweight = (CulturalHalloweenStructures.PieceWeight)iterator.next();
                    k1 -= pieceweight.villagePieceWeight;

                    if (k1 < 0)
                    {
                        if (!pieceweight.canSpawnMoreVillagePiecesOfType(p_176067_7_) || pieceweight == p_176067_0_.structVillagePieceWeight && p_176067_0_.structureVillageWeightedPieceList.size() > 1)
                        {
                            break;
                        }

                        CulturalHalloweenStructures.Village village = func_176065_a(p_176067_0_, pieceweight, p_176067_1_, p_176067_2_, p_176067_3_, p_176067_4_, p_176067_5_, p_176067_6_, p_176067_7_);

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

            StructureBoundingBox structureboundingbox = CulturalHalloweenStructures.Torch.func_175856_a(p_176067_0_, p_176067_1_, p_176067_2_, p_176067_3_, p_176067_4_, p_176067_5_, p_176067_6_);

            if (structureboundingbox != null)
            {
                return new CulturalHalloweenStructures.Torch(p_176067_0_, p_176067_7_, p_176067_2_, structureboundingbox, p_176067_6_);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent func_176066_d(CulturalHalloweenStructures.Start p_176066_0_, List p_176066_1_, Random p_176066_2_, int p_176066_3_, int p_176066_4_, int p_176066_5_, EnumFacing p_176066_6_, int p_176066_7_)
    {
        if (p_176066_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_176066_3_ - p_176066_0_.getBoundingBox().minX) <= 112 && Math.abs(p_176066_5_ - p_176066_0_.getBoundingBox().minZ) <= 112)
        {
            CulturalHalloweenStructures.Village village = func_176067_c(p_176066_0_, p_176066_1_, p_176066_2_, p_176066_3_, p_176066_4_, p_176066_5_, p_176066_6_, p_176066_7_ + 1);

            if (village != null)
            {
            	p_176066_1_.add(village);
                p_176066_0_.field_74932_i.add(village);
                return village;
                //int i1 = (village.getBoundingBox().minX + village.getBoundingBox().maxX) / 2;
                //int j1 = (village.getBoundingBox().minZ + village.getBoundingBox().maxZ) / 2;
                //int k1 = village.getBoundingBox().maxX - village.getBoundingBox().minX;
                //int l1 = village.getBoundingBox().maxZ - village.getBoundingBox().minZ;
                //int i2 = k1 > l1 ? k1 : l1;

                //if (p_176066_0_.getWorldChunkManager().areBiomesViable(i1, j1, i2 / 2 + 4, MapGenCulturalVillages.villageSpawnBiomes))
                //{
                //    p_176066_1_.add(village);
                //    p_176066_0_.field_74932_i.add(village);
                //    return village;
                //}
            }

            return null;
        }
        else
        {
            return null;
        }
    }

    private static StructureComponent func_176069_e(CulturalHalloweenStructures.Start p_176069_0_, List p_176069_1_, Random p_176069_2_, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing p_176069_6_, int p_176069_7_)
    {
        if (p_176069_7_ > 3 + p_176069_0_.terrainType)
        {
            return null;
        }
        else if (Math.abs(p_176069_3_ - p_176069_0_.getBoundingBox().minX) <= 112 && Math.abs(p_176069_5_ - p_176069_0_.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = CulturalHalloweenStructures.Path.func_175848_a(p_176069_0_, p_176069_1_, p_176069_2_, p_176069_3_, p_176069_4_, p_176069_5_, p_176069_6_);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
                CulturalHalloweenStructures.Path path = new CulturalHalloweenStructures.Path(p_176069_0_, p_176069_7_, p_176069_2_, structureboundingbox, p_176069_6_);
                int i1 = (path.getBoundingBox().minX + path.getBoundingBox().maxX) / 2;
                int j1 = (path.getBoundingBox().minZ + path.getBoundingBox().maxZ) / 2;
                int k1 = path.getBoundingBox().maxX - path.getBoundingBox().minX;
                int l1 = path.getBoundingBox().maxZ - path.getBoundingBox().minZ;
                int i2 = k1 > l1 ? k1 : l1;

                BiomeGenBase biome = world.getBiomeGenForCoords(new BlockPos(i1 * 16 + 8, 0, j1 * 16 + 8));
                if (biome == BiomeGenBase.plains)
                {
                	p_176069_1_.add(path);
                	p_176069_0_.field_74930_j.add(path);
                	return path;
                }
                //if (p_176069_0_.getWorldChunkManager().areBiomesViable(i1, j1, i2 / 2 + 4, MapGenCulturalVillages.villageSpawnBiomes))
                //{
                //    p_176069_1_.add(path);
                //    p_176069_0_.field_74930_j.add(path);
                //    return path;
                //}
            }

            return null;
        }
        else
        {
            return null;
        }
    }

    public static class Church extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000525";

        public Church() {}

        public Church(CulturalHalloweenStructures.Start p_i45564_1_, int p_i45564_2_, Random p_i45564_3_, StructureBoundingBox p_i45564_4_, EnumFacing p_i45564_5_)
        {
            super(p_i45564_1_, p_i45564_2_);
            this.coordBaseMode = p_i45564_5_;
            this.boundingBox = p_i45564_4_;
        }

        public static CulturalHalloweenStructures.Church func_175854_a(CulturalHalloweenStructures.Start p_175854_0_, List p_175854_1_, Random p_175854_2_, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing p_175854_6_, int p_175854_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 7, 12, 11, p_175854_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175854_1_, structureboundingbox) == null ? new CulturalHalloweenStructures.Church(p_175854_0_, p_175854_7_, p_175854_2_, structureboundingbox, p_175854_6_) : null;
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

            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 6, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 3, 10, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 4, 3, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 6, 0, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 10, 5, 4, 10, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 1, 1, 6, 3, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 2, 2, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 0, 5, 2, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 0, 5, 9, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 5, 4, 5, 5, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 4, 0, 5, 8, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 4, 4, 6, 5, 8, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 0, 0, 4, 3, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 4, 0, 6, 4, 3, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 9, 0, 6, 9, 4, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 5, 1, 0, 8, 3, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 5, 4, 5, 8, 4, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 5, 1, 6, 8, 3, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 1, 5, 4, 4, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 0, 4, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, 4, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 4, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 0, 10, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 0, 10, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 0, 10, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, 10, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, 10, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, 10, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, 10, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, 10, 4, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 9, 1, 2, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 1, 9, 5, 2, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 1, 9, 4, 1, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 2, 1, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 4, 1, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 4, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 3, 1, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 1)), 2, 2, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 0)), 4, 2, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 3, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 3, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 3, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 3, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 3, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 3, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 6, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 7, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 6, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 7, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 6, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 7, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 6, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 7, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 3, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 3, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 3, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 1, 4, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 5, 4, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 1, 4, 9, p_74875_3_);
            //this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 2, 4, 7, p_74875_3_);
            //this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateY()), 1, 4, 6, p_74875_3_);
            //this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.rotateYCCW()), 3, 4, 6, p_74875_3_);
            //this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 4, 5, p_74875_3_);
            //int i = this.getMetadataWithOffset(Blocks.ladder, 4);
            int j;

            //for (j = 1; j <= 4; ++j)
            //{
            //    this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 1, j, 3, p_74875_3_);
            //}

            this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 3, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

            if (this.func_175807_a(worldIn, 2, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, 3, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
            {
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 3, 0, -1, p_74875_3_);
            }

            for (j = 0; j < 11; ++j)
            {
                for (int k = 0; k < 7; ++k)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, k, 12, j, p_74875_3_);
                    this.func_175808_b(worldIn, Blocks.mossy_cobblestone.getDefaultState(), k, -1, j, p_74875_3_);
                }
            }

            this.spawnVillagers(worldIn, p_74875_3_, 3, 1, 5, 1);
            return true;
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return 2;
        }
    }

    public static class Field1 extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000518";

        public Field1() {}

        public Field1(CulturalHalloweenStructures.Start p_i45570_1_, int p_i45570_2_, Random p_i45570_3_, StructureBoundingBox p_i45570_4_, EnumFacing p_i45570_5_)
        {
            super(p_i45570_1_, p_i45570_2_);
            this.coordBaseMode = p_i45570_5_;
            this.boundingBox = p_i45570_4_;
        }

        public static CulturalHalloweenStructures.Field1 func_175851_a(CulturalHalloweenStructures.Start p_175851_0_, List p_175851_1_, Random p_175851_2_, int p_175851_3_, int p_175851_4_, int p_175851_5_, EnumFacing p_175851_6_, int p_175851_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175851_3_, p_175851_4_, p_175851_5_, 0, 0, 0, 17, 4, 9, p_175851_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175851_1_, structureboundingbox) == null ? new CulturalHalloweenStructures.Field1(p_175851_0_, p_175851_7_, p_175851_2_, structureboundingbox, p_175851_6_) : null;
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

            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 16, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 0, 1, 7, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 9, 0, 1, 9, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 0, 1, 12, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 14, 0, 1, 15, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 0, 8, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 0, 0, 6, 0, 8, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 10, 0, 0, 10, 0, 8, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 16, 0, 0, 16, 0, 8, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 15, 0, 0, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 8, 15, 0, 8, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 0, 1, 8, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 13, 0, 1, 13, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
                
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 1, 1, 7, Blocks.pumpkin.getDefaultState(), Blocks.pumpkin.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 1, 1, 2, 1, 7, Blocks.pumpkin_stem.getStateFromMeta(7), Blocks.pumpkin_stem.getStateFromMeta(7), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 5, 1, 7, Blocks.potatoes.getStateFromMeta(7), Blocks.potatoes.getStateFromMeta(7), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 1, 1, 7, 1, 7, Blocks.wheat.getStateFromMeta(7), Blocks.wheat.getStateFromMeta(7), false);
            this.func_175804_a(worldIn, p_74875_3_, 9, 1, 1, 9, 1, 7, Blocks.wheat.getStateFromMeta(7), Blocks.wheat.getStateFromMeta(7), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 1, 1, 12, 1, 7, Blocks.carrots.getStateFromMeta(7), Blocks.carrots.getStateFromMeta(7), false);
            this.func_175804_a(worldIn, p_74875_3_, 14, 1, 1, 14, 1, 7, Blocks.melon_stem.getStateFromMeta(7), Blocks.melon_stem.getStateFromMeta(7), false);
            this.func_175804_a(worldIn, p_74875_3_, 15, 1, 1, 15, 1, 7, Blocks.melon_block.getDefaultState(), Blocks.melon_block.getDefaultState(), false);
            
            int i;
            for (i = 0; i < 9; ++i)
            {
                for (int j = 0; j < 17; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 4, i, p_74875_3_);
                    this.func_175808_b(worldIn, Blocks.dirt.getDefaultState(), j, -1, i, p_74875_3_);
                }
            }

            return true;
        }
    }

    public static class Field2 extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000519";

        public Field2() {}

        public Field2(CulturalHalloweenStructures.Start p_i45569_1_, int p_i45569_2_, Random p_i45569_3_, StructureBoundingBox p_i45569_4_, EnumFacing p_i45569_5_)
        {
            super(p_i45569_1_, p_i45569_2_);
            this.coordBaseMode = p_i45569_5_;
            this.boundingBox = p_i45569_4_;
        }

        public static CulturalHalloweenStructures.Field2 func_175852_a(CulturalHalloweenStructures.Start p_175852_0_, List p_175852_1_, Random p_175852_2_, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing p_175852_6_, int p_175852_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, p_175852_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ? new CulturalHalloweenStructures.Field2(p_175852_0_, p_175852_7_, p_175852_2_, structureboundingbox, p_175852_6_) : null;
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
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 2, 0, 7, Blocks.soul_sand.getDefaultState(), Blocks.soul_sand.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 0, 1, 5, 0, 7, Blocks.soul_sand.getDefaultState(), Blocks.soul_sand.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 0, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 0, 0, 6, 0, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 5, 0, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 8, 5, 0, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.lava.getDefaultState(), Blocks.lava.getDefaultState(), false);
            
            int n;
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 2, 1, 7, Blocks.nether_wart.getStateFromMeta(3), Blocks.nether_wart.getStateFromMeta(3), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 5, 1, 7, Blocks.nether_wart.getStateFromMeta(3), Blocks.nether_wart.getStateFromMeta(3), false);
            
            int i;
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

    public static class Hall extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000522";

        public Hall() {}

        public Hall(CulturalHalloweenStructures.Start p_i45567_1_, int p_i45567_2_, Random p_i45567_3_, StructureBoundingBox p_i45567_4_, EnumFacing p_i45567_5_)
        {
            super(p_i45567_1_, p_i45567_2_);
            this.coordBaseMode = p_i45567_5_;
            this.boundingBox = p_i45567_4_;
        }

        public static CulturalHalloweenStructures.Hall func_175857_a(CulturalHalloweenStructures.Start p_175857_0_, List p_175857_1_, Random p_175857_2_, int p_175857_3_, int p_175857_4_, int p_175857_5_, EnumFacing p_175857_6_, int p_175857_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175857_3_, p_175857_4_, p_175857_5_, 0, 0, 0, 9, 7, 11, p_175857_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175857_1_, structureboundingbox) == null ? new CulturalHalloweenStructures.Hall(p_175857_0_, p_175857_7_, p_175857_2_, structureboundingbox, p_175857_6_) : null;
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

            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 8, 7, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            //Yard
            this.func_175804_a(worldIn, p_74875_3_, 1, -1, 7, 8, -1, 12, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 7, 0, 0, 12, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 12, 8, 0, 12, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 0, 7, 8, 0, 12, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty(BlockFlowerPot.CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(BlockFlowerPot.LEGACY_DATA, 10), 1, 0, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty(BlockFlowerPot.CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(BlockFlowerPot.LEGACY_DATA, 2), 1, 0, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty(BlockFlowerPot.CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(BlockFlowerPot.LEGACY_DATA, 2), 2, 0, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty(BlockFlowerPot.CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(BlockFlowerPot.LEGACY_DATA, 10), 6, 0, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty(BlockFlowerPot.CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(BlockFlowerPot.LEGACY_DATA, 1), 7, 0, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty(BlockFlowerPot.CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(BlockFlowerPot.LEGACY_DATA, 11), 7, 0, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty(BlockFlowerPot.CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(BlockFlowerPot.LEGACY_DATA, 10), 7, 0, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 7, 0, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty(BlockFlowerPot.CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY).withProperty(BlockFlowerPot.LEGACY_DATA, 10), 7, 0, 11, p_74875_3_);
            //Floor
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 8, 0, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 7, 0, 5, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            //Walls
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 3, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 1, 0, 8, 3, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 7, 1, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 6, 7, 1, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 7, 3, 0, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 2, 6, 7, 3, 6, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            //Roof
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 1, 8, 4, 1, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 5, 8, 4, 5, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 5, 2, 8, 5, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 6, 3, 8, 6, 3, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 2, 0, 4, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 4, 2, 8, 4, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
            int k;
            int l;

            for (k = -1; k <= 2; ++k)
            {
                for (l = 0; l <= 8; ++l)
                {
                    this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(i), l, 4 + k, k, p_74875_3_);
                }
            }
            this.func_175804_a(worldIn, p_74875_3_, 0, 6, 4, 8, 6, 4, Blocks.dark_oak_stairs.getStateFromMeta(j), Blocks.dark_oak_stairs.getStateFromMeta(j), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 5, 5, 8, 5, 5, Blocks.dark_oak_stairs.getStateFromMeta(j), Blocks.dark_oak_stairs.getStateFromMeta(j), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 6, 8, 4, 6, Blocks.dark_oak_stairs.getStateFromMeta(j), Blocks.dark_oak_stairs.getStateFromMeta(j), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 3, 7, 8, 3, 7, Blocks.dark_oak_stairs.getStateFromMeta(j), Blocks.dark_oak_stairs.getStateFromMeta(j), false);
            //Windows
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 0, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 0, 2, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 0, 2, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 0, 2, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 8, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 8, 2, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 8, 2, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 8, 2, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 1, 2, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 3, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 6, 2, 6, p_74875_3_);
            //Interior
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 1, 1, 2, Blocks.stonebrick.getStateFromMeta(2), Blocks.stonebrick.getStateFromMeta(2), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 1, 3, 3, 1, 5, Blocks.stonebrick.getStateFromMeta(2), Blocks.stonebrick.getStateFromMeta(2), false);
            this.func_175811_a(worldIn, Blocks.cake.getDefaultState(), 1, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.furnace.getDefaultState().withProperty(BlockFurnace.FACING, this.coordBaseMode), 1, 1, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.furnace.getDefaultState().withProperty(BlockFurnace.FACING, this.coordBaseMode), 2, 1, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 6, 1, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 7, 1, 5, p_74875_3_);
            int m = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            int n = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
            this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(m), 6, 1, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(n), 7, 1, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.carpet.getStateFromMeta(15), 6, 2, 4, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 6, 4, 2, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 1, 0, 6, 2, 0, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 4, 1, 6, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
            this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 6, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

            if (this.func_175807_a(worldIn, 6, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, 6, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
            {
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 6, 0, -1, p_74875_3_);
            }

            //for (k = 0; k < 5; ++k)
            //{
            //    for (l = 0; l < 9; ++l)
            //    {
            //        this.clearCurrentPositionBlocksUpwards(worldIn, l, 7, k, p_74875_3_);
            //        this.func_175808_b(worldIn, Blocks.mossy_cobblestone.getDefaultState(), l, -1, k, p_74875_3_);
            //    }
            //}

            this.spawnVillagers(worldIn, p_74875_3_, 4, 1, 2, 2);
            return true;
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return p_180779_1_ == 0 ? 4 : super.func_180779_c(p_180779_1_, p_180779_2_);
        }
    }

    public static class House1 extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000517";

        public House1() {}

        public House1(CulturalHalloweenStructures.Start p_i45571_1_, int p_i45571_2_, Random p_i45571_3_, StructureBoundingBox p_i45571_4_, EnumFacing p_i45571_5_)
        {
            super(p_i45571_1_, p_i45571_2_);
            this.coordBaseMode = p_i45571_5_;
            this.boundingBox = p_i45571_4_;
        }

        public static CulturalHalloweenStructures.House1 func_175850_a(CulturalHalloweenStructures.Start p_175850_0_, List p_175850_1_, Random p_175850_2_, int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing p_175850_6_, int p_175850_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, 11, 9, 9, p_175850_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175850_1_, structureboundingbox) == null ? new CulturalHalloweenStructures.House1(p_175850_0_, p_175850_7_, p_175850_2_, structureboundingbox, p_175850_6_) : null;
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

            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 11, 5, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 11, 0, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 11, 5, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 6, 1, 11, 6, 8, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 7, 2, 11, 7, 7, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 8, 3, 11, 8, 6, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
            int k;
            int l;

            for (k = -1; k <= 2; ++k)
            {
                for (l = 0; l <= 11; ++l)
                {
                    this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(i), l, 6 + k, k, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(j), l, 6 + k, 9 - k, p_74875_3_);
                }
            }

            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 1, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 9, 11, 1, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 1, 0, 11, 1, 8, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 10, 1, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 4, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 2, 9, 0, 4, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 2, 9, 11, 4, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 2, 0, 11, 4, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 2, 1, 0, 4, 8, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 2, 9, 10, 4, 9, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 2, 1, 11, 4, 8, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 10, 4, 0, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 2, 2, 0, 3, 3, Blocks.glass_pane.getDefaultState(), Blocks.glass_pane.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 2, 6, 0, 3, 7, Blocks.glass_pane.getDefaultState(), Blocks.glass_pane.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 2, 2, 11, 3, 3, Blocks.glass_pane.getDefaultState(), Blocks.glass_pane.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 2, 6, 11, 3, 7, Blocks.glass_pane.getDefaultState(), Blocks.glass_pane.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 2, 9, 3, 2, 9, Blocks.glass_pane.getDefaultState(), Blocks.glass_pane.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 2, 9, 9, 2, 9, Blocks.glass_pane.getDefaultState(), Blocks.glass_pane.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 2, 0, 6, 3, 0, Blocks.glass_pane.getDefaultState(), Blocks.glass_pane.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 1, 10, 4, 1, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 8, 10, 4, 8, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 1, 1, 3, 1, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 1, 1, 8, 1, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 2, 1, 3, 3, 4, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 2, 1, 8, 3, 4, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 4, 1, 3, 4, 7, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 4, 1, 8, 4, 7, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 8, 4, 3, 8, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 3, 8, 10, 3, 8, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 8, 4, 2, 8, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 1, 8, 7, 2, 8, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 2, 4, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 4, 4, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 4, 4, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 9, 4, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 7, 4, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 7, 4, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.crafting_table.getDefaultState(), 4, 1, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.enchanting_table.getDefaultState(), 7, 1, 1, p_74875_3_);
            int m = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
            int n = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
            this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(m), 1, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(n), 10, 1, 7, p_74875_3_);
            k = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 8, 3, 1, 8, Blocks.dark_oak_stairs.getStateFromMeta(k), Blocks.dark_oak_stairs.getStateFromMeta(k),false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 1, 8, 10, 1, 8, Blocks.dark_oak_stairs.getStateFromMeta(k), Blocks.dark_oak_stairs.getStateFromMeta(k),false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 1, 8, 6, 1, 8, Blocks.dark_oak_stairs.getStateFromMeta(k), Blocks.dark_oak_stairs.getStateFromMeta(k),false);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 2, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 9, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.carpet.getStateFromMeta(15), 2, 2, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.carpet.getStateFromMeta(15), 9, 2, 7, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 1, 2, 0, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 10, 1, 0, 10, 2, 0, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 1, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
            this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 10, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
            
            this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 0, -1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 10, 0, -1, p_74875_3_);
            
            if (this.func_175807_a(worldIn, 1, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, 1, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
            {
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, p_74875_3_);
            }
            if (this.func_175807_a(worldIn, 10, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, 10, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
            {
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 10, 0, -1, p_74875_3_);
            }
            for (l = 0; l < 10; ++l)
            {
                for (int i1 = 0; i1 < 12; ++i1)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i1, 9, l, p_74875_3_);
                    this.func_175808_b(worldIn, Blocks.mossy_cobblestone.getDefaultState(), i1, -1, l, p_74875_3_);
                }
            }

            this.spawnVillagers(worldIn, p_74875_3_, 2, 1, 2, 1);
            return true;
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return 1;
        }
    }

    public static class House2 extends CulturalHalloweenStructures.Village
    {
        /** List of items that Village's Blacksmith chest can contain. */
        private static final List villageBlacksmithChestContents = Lists.newArrayList(new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.sapling), 0, 3, 7, 5), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)});
        private boolean hasMadeChest;
        private boolean hasMadeChest2;
        private static final String __OBFID = "CL_00000526";

        public House2() {}

        static
        {
            ChestGenHooks.init(VILLAGE_BLACKSMITH, villageBlacksmithChestContents, 3, 8);
        }

        public House2(CulturalHalloweenStructures.Start p_i45563_1_, int p_i45563_2_, Random p_i45563_3_, StructureBoundingBox p_i45563_4_, EnumFacing p_i45563_5_)
        {
            super(p_i45563_1_, p_i45563_2_);
            this.coordBaseMode = p_i45563_5_;
            this.boundingBox = p_i45563_4_;
        }

        public static CulturalHalloweenStructures.House2 func_175855_a(CulturalHalloweenStructures.Start p_175855_0_, List p_175855_1_, Random p_175855_2_, int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing p_175855_6_, int p_175855_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0, 15, 12, 12, p_175855_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175855_1_, structureboundingbox) == null ? new CulturalHalloweenStructures.House2(p_175855_0_, p_175855_7_, p_175855_2_, structureboundingbox, p_175855_6_) : null;
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound p_143012_1_)
        {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setBoolean("Chest", this.hasMadeChest);
            p_143012_1_.setBoolean("Chest2", this.hasMadeChest2);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound p_143011_1_)
        {
            super.readStructureFromNBT(p_143011_1_);
            this.hasMadeChest = p_143011_1_.getBoolean("Chest");
            this.hasMadeChest2 = p_143011_1_.getBoolean("Chest2");
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

            this.func_175804_a(worldIn, p_74875_3_, 1, 2, 1, 14, 11, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 14, 0, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 9, 0, 14, 9, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 10, 0, 14, 10, 11, Blocks.wooden_slab.getStateFromMeta(1), Blocks.wooden_slab.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 10, 1, 13, 10, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 1, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 1, 1, 1, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 2, 1, 1, 6, Blocks.redstone_wire.getDefaultState(), Blocks.redstone_wire.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.unpowered_repeater.getStateFromMeta(this.getMetadataWithOffset(Blocks.unpowered_repeater, EnumFacing.SOUTH.getHorizontalIndex())).withProperty(BlockRedstoneRepeater.DELAY, 3), 1, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.unpowered_repeater.getStateFromMeta(this.getMetadataWithOffset(Blocks.unpowered_repeater, EnumFacing.SOUTH.getHorizontalIndex())).withProperty(BlockRedstoneRepeater.DELAY, 4), 1, 1, 8, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 9, 1, 1, 10, Blocks.redstone_wire.getDefaultState(), Blocks.redstone_wire.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 1, 1, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 2, 1, 0, 2, 1, 1, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.unpowered_repeater.getStateFromMeta(this.getMetadataWithOffset(Blocks.unpowered_repeater, EnumFacing.WEST.getHorizontalIndex())).withProperty(BlockRedstoneRepeater.DELAY, 3), 2, 1, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 2, 1, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 2, 1, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.unpowered_repeater.getStateFromMeta(this.getMetadataWithOffset(Blocks.unpowered_repeater, EnumFacing.SOUTH.getHorizontalIndex())).withProperty(BlockRedstoneRepeater.DELAY, 3), 2, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 2, 1, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 2, 1, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 2, 1, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 3, 1, 1, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.sticky_piston.getStateFromMeta(this.getMetadataWithOffset(Blocks.sticky_piston, EnumFacing.NORTH.getIndex())), 3, 1, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone.getDefaultState(), 3, 1, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 3, 1, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, 1, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, 1, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 0, 4, 1, 1, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.unpowered_repeater.getStateFromMeta(this.getMetadataWithOffset(Blocks.unpowered_repeater, EnumFacing.EAST.getHorizontalIndex())), 4, 1, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 4, 1, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.unpowered_repeater.getStateFromMeta(this.getMetadataWithOffset(Blocks.unpowered_repeater, EnumFacing.SOUTH.getHorizontalIndex())).withProperty(BlockRedstoneRepeater.DELAY, 3), 4, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 4, 1, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 4, 1, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 5, 1, 0, 5, 1, 1, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 5, 1, 3, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 5, 1, 6, 5, 1, 7, Blocks.redstone_wire.getDefaultState(), Blocks.redstone_wire.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 5, 1, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 6, 1, 0, 6, 1, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 6, 1, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.unpowered_repeater.getStateFromMeta(this.getMetadataWithOffset(Blocks.unpowered_repeater, EnumFacing.SOUTH.getHorizontalIndex())).withProperty(BlockRedstoneRepeater.DELAY, 3), 6, 1, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, 1, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, 1, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 7, 1, 0, 7, 1, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 7, 1, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 8, 1, 0, 10, 1, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 11, 1, 0, 11, 1, 2, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 11, 1, 3, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 11, 1, 4, 11, 1, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 12, 1, 0, 14, 1, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 2, 1, 0, 2, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 1, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 1, 2, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 2, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.sticky_piston.getStateFromMeta(this.getMetadataWithOffset(Blocks.sticky_piston, EnumFacing.UP.getIndex())), 2, 2, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 2, 2, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.sticky_piston.getStateFromMeta(this.getMetadataWithOffset(Blocks.sticky_piston, EnumFacing.UP.getIndex())), 3, 2, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, 2, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 4, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.sticky_piston.getStateFromMeta(this.getMetadataWithOffset(Blocks.sticky_piston, EnumFacing.UP.getIndex())), 4, 2, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 4, 2, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 5, 2, 0, 5, 2, 1, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 2, 10, 5, 2, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 2, 0, 6, 2, 1, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 2, 2, 6, 2, 5, Blocks.redstone_wire.getDefaultState(), Blocks.redstone_wire.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.sticky_piston.getStateFromMeta(this.getMetadataWithOffset(Blocks.sticky_piston, EnumFacing.UP.getIndex())), 6, 2, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, 2, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 7, 2, 0, 7, 2, 4, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 2, 5, 7, 2, 6, Blocks.redstone_wire.getDefaultState(), Blocks.redstone_wire.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 7, 2, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 8, 2, 0, 8, 2, 5, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 2, 6, 8, 2, 10, Blocks.redstone_wire.getDefaultState(), Blocks.redstone_wire.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 8, 2, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 9, 2, 0, 14, 2, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 3, 1, 0, 3, 11, Blocks.coal_block.getDefaultState(), Blocks.coal_block.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.coal_block.getDefaultState(), 1, 3, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.coal_block.getDefaultState(), 1, 3, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 2, 1, 3, 10, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.coal_block.getDefaultState(), 2, 3, 1, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 2, 3, 2, 2, 3, 7, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 2, 3, 8, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 2, 3, 9, 2, 3, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 3, 1, 3, 3, 9, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 3, 3, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, 3, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 4, 3, 1, 4, 3, 7, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 3, 9, 4, 3, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 4, 3, 8, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 5, 3, 1, 5, 3, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.coal_block.getDefaultState(), 6, 3, 1, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 6, 3, 6, 6, 3, 8, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.glowstone.getDefaultState(), 6, 3, 9, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 6, 3, 10, 6, 3, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.coal_block.getDefaultState(), 7, 3, 1, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 7, 3, 2, 7, 3, 4, Blocks.stone_pressure_plate.getDefaultState(), Blocks.stone_pressure_plate.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 3, 6, 7, 3, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.coal_block.getDefaultState(), 8, 3, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_pressure_plate.getDefaultState(), 8, 3, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 8, 3, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 9, 3, 1, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 9, 3, 4, 9, 3, 10, Blocks.stone_pressure_plate.getDefaultState(), Blocks.stone_pressure_plate.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 9, 3, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 10, 3, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 11, 3, 9, 11, 3, 10, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 11, 3, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.anvil.getStateFromMeta(this.getMetadataWithOffset(Blocks.anvil, 3)), 12, 3, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 12, 3, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.lava.getDefaultState(), 12, 3, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 12, 3, 11, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 13, 3, 4, 13, 3, 5, Blocks.furnace.getStateFromMeta(this.getMetadataWithOffset(Blocks.furnace, EnumFacing.SOUTH.getHorizontalIndex())), Blocks.furnace.getStateFromMeta(this.getMetadataWithOffset(Blocks.furnace, EnumFacing.SOUTH.getHorizontalIndex())), false);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 13, 3, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.lava.getDefaultState(), 13, 3, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 13, 3, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 14, 3, 1, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 14, 3, 2, 14, 3, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 1, 0, 8, 11, Blocks.coal_block.getDefaultState(), Blocks.coal_block.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 5, 2, 0, 7, 10, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 11, 13, 7, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 8, 11, 13, 8, 11, Blocks.coal_block.getDefaultState(), Blocks.coal_block.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 14, 4, 2, 14, 8, 11, Blocks.coal_block.getDefaultState(), Blocks.coal_block.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 14, 5, 3, 14, 7, 10, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 1, 8, 8, 1, Blocks.coal_block.getDefaultState(), Blocks.coal_block.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 7, 7, 1, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175811_a(worldIn, Blocks.hay_block.getDefaultState(), 1, 4, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 1, 4, 8, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 14, 4, 8, 14, 7, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 9, 1, 4, 10, Blocks.coal_block.getDefaultState(), Blocks.coal_block.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 2, 4, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.hay_block.getDefaultState(), 2, 4, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.coal_block.getDefaultState(), 2, 4, 10, p_74875_3_);
            //this.func_175811_a(worldIn, Blocks.chest.getDefaultState(), 4, 4, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.hay_block.getDefaultState(), 7, 4, 10, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 9, 4, 1, 9, 8, 1, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 14, 4, 1, 14, 7, 1, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 13, 4, 4, 13, 4, 5, Blocks.furnace.getStateFromMeta(this.getMetadataWithOffset(Blocks.furnace, EnumFacing.SOUTH.getHorizontalIndex())), Blocks.furnace.getStateFromMeta(this.getMetadataWithOffset(Blocks.furnace, EnumFacing.SOUTH.getHorizontalIndex())), false);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 0, 6, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 0, 6, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 0, 6, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.coal_block.getDefaultState(), 1, 5, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 2, 6, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.carpet.getStateFromMeta(15), 2, 5, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 4, 6, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 6, 6, 1, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 14, 6, 7, 14, 6, 9, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 1, 7, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 13, 7, 10, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 14, 7, 7, 14, 7, 9, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
                
            int i = this.getMetadataWithOffset(Blocks.stone_stairs, 3);
            int j = this.getMetadataWithOffset(Blocks.stone_stairs, 2);
            int k = this.getMetadataWithOffset(Blocks.stone_stairs, 0);
            int l = this.getMetadataWithOffset(Blocks.stone_stairs, 1);
            int i1 = this.getMetadataWithOffset(Blocks.dark_oak_stairs, 3);
            int j1 = this.getMetadataWithOffset(Blocks.dark_oak_stairs, 2);
            int k1 = this.getMetadataWithOffset(Blocks.dark_oak_stairs, 0);
            int l1 = this.getMetadataWithOffset(Blocks.dark_oak_stairs, 1);
            this.func_175804_a(worldIn, p_74875_3_, 6, 3, 2, 6, 3, 5, Blocks.stone_stairs.getStateFromMeta(l), Blocks.stone_stairs.getStateFromMeta(l), false);
            this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(i), 7, 3, 5, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 8, 3, 5, 8, 3, 10, Blocks.stone_stairs.getStateFromMeta(l), Blocks.stone_stairs.getStateFromMeta(l), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 2, 1, 4, 3, Blocks.dark_oak_stairs.getStateFromMeta(i1), Blocks.dark_oak_stairs.getStateFromMeta(i1), false);
            this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(l1), 2, 4, 2, p_74875_3_);
            
            if (!this.hasMadeChest && p_74875_3_.func_175898_b(new BlockPos(this.getXWithOffset(4, 10), this.getYWithOffset(4), this.getZWithOffset(4, 10))))
            {
                this.hasMadeChest = true;
                this.func_180778_a(worldIn, p_74875_3_, p_74875_2_, 4, 4, 10, ChestGenHooks.getItems(VILLAGE_BLACKSMITH, p_74875_2_), ChestGenHooks.getCount(VILLAGE_BLACKSMITH, p_74875_2_));
            }
            if (!this.hasMadeChest2 && p_74875_3_.func_175898_b(new BlockPos(this.getXWithOffset(5, 10), this.getYWithOffset(4), this.getZWithOffset(5, 10))))
            {
                this.hasMadeChest2 = true;
                this.func_180778_a(worldIn, p_74875_3_, p_74875_2_, 5, 4, 10, ChestGenHooks.getItems(VILLAGE_BLACKSMITH, p_74875_2_), ChestGenHooks.getCount(VILLAGE_BLACKSMITH, p_74875_2_));
            }

            //int i2;

            //for (i2 = 6; i2 <= 8; ++i2)
            //{
            //    if (this.func_175807_a(worldIn, i2, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, i2, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
            //    {
            //        this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), i2, 0, -1, p_74875_3_);
            //    }
            //}

            //for (i = 0; i < 7; ++i)
            //{
                //for (int j = 0; j < 10; ++j)
                //{
                //    this.clearCurrentPositionBlocksUpwards(worldIn, j, 6, i, p_74875_3_);
                //    this.func_175808_b(worldIn, Blocks.mossy_cobblestone.getDefaultState(), j, -1, i, p_74875_3_);
                //}
            //}

            this.spawnVillagers(worldIn, p_74875_3_, 7, 1, 1, 1);
            return true;
        }

        protected int func_180779_c(int p_180779_1_, int p_180779_2_)
        {
            return 3;
        }
    }

    public static class House3 extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000530";

        public House3() {}

        public House3(CulturalHalloweenStructures.Start p_i45561_1_, int p_i45561_2_, Random p_i45561_3_, StructureBoundingBox p_i45561_4_, EnumFacing p_i45561_5_)
        {
            super(p_i45561_1_, p_i45561_2_);
            this.coordBaseMode = p_i45561_5_;
            this.boundingBox = p_i45561_4_;
        }

        public static CulturalHalloweenStructures.House3 func_175849_a(CulturalHalloweenStructures.Start p_175849_0_, List p_175849_1_, Random p_175849_2_, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing p_175849_6_, int p_175849_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 11, 7, 13, p_175849_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175849_1_, structureboundingbox) == null ? new CulturalHalloweenStructures.House3(p_175849_0_, p_175849_7_, p_175849_2_, structureboundingbox, p_175849_6_) : null;
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

            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 0, 5, 7, 0, 10, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 9, 0, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 3, 5, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 10, 0, 0, 10, 3, 5, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 9, 2, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, 5, 2, 1, 5, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 0, 5, 9, 1, 5, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 0, 6, 2, 3, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 0, 11, 8, 3, 11, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 0, 6, 8, 3, 10, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 9, 3, 0, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 2, 5, 2, 3, 5, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 2, 5, 9, 3, 5, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 1, 10, 4, 1, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 4, 3, 4, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 4, 4, 10, 4, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 5, 2, 10, 5, 3, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 0, 4, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 0, 4, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 10, 4, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 10, 4, 3, p_74875_3_);
            //this.func_175811_a(worldIn, Blocks.stone.getDefaultState(), 10, 4, 4, p_74875_3_);
            int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
            int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
            int k;
            int l;
            this.func_175804_a(worldIn, p_74875_3_, 8, 4, 5, 10, 4, 5, Blocks.dark_oak_stairs.getStateFromMeta(j), Blocks.dark_oak_stairs.getStateFromMeta(j), false);
            this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(j), 10, 3, 6, p_74875_3_);
            for (k = -1; k <= 2; ++k)
            {
                for (l = 0; l <= 10; ++l)
                {
                    this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(i), l, 4 + k, k, p_74875_3_);

                    if ((k > -1 || l <= 1) && (k > 0 || l <= 3) && (k > 1 || l <= 4 || l >= 6))
                    {
                        this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(j), l, 4 + k, 5 - k, p_74875_3_);
                    }
                }
            }

            this.func_175804_a(worldIn, p_74875_3_, 3, 4, 5, 3, 4, 11, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 4, 5, 7, 4, 11, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 5, 4, 4, 5, 11, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 5, 4, 6, 5, 11, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 6, 3, 5, 6, 11, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            k = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
            int i1;

            for (l = 4; l >= 1; --l)
            {
                this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), l, 2 + l, 7 - l, p_74875_3_);

                for (i1 = 8 - l; i1 <= 11; ++i1)
                {
                    this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(k), l, 2 + l, i1, p_74875_3_);
                }
            }

            l = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 6, 6, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 7, 5, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 9, 3, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(l), 6, 6, 4, p_74875_3_);
            int j1;
            this.func_175804_a(worldIn, p_74875_3_, 9, 3, 7, 9, 3, 11, Blocks.dark_oak_stairs.getStateFromMeta(l), Blocks.dark_oak_stairs.getStateFromMeta(l), false);
            
            for (i1 = 6; i1 <= 8; ++i1)
            {
                for (j1 = 5; j1 <= 11; ++j1)
                {
                    this.func_175811_a(worldIn, Blocks.dark_oak_stairs.getStateFromMeta(l), i1, 12 - i1, j1, p_74875_3_);
                }
            }
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 8, 4, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 0, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 0, 2, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 4, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 5, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 6, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 7, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 8, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 10, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 10, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 10, 2, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 10, 2, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 8, 2, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 8, 2, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 2, 2, 7, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 2, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 2, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 2, 2, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 4, 4, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 5, 4, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(0), 6, 4, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.planks.getStateFromMeta(5), 5, 5, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 1, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 2, 0, p_74875_3_);
            //this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, p_74875_3_);
            this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
            this.func_175804_a(worldIn, p_74875_3_, 1, 0, -1, 3, 2, -1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

            if (this.func_175807_a(worldIn, 2, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, 2, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
            {
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, p_74875_3_);
            }

            //for (i1 = 0; i1 < 5; ++i1)
            //{
            //    for (j1 = 0; j1 < 9; ++j1)
            //    {
            //        this.clearCurrentPositionBlocksUpwards(worldIn, j1, 7, i1, p_74875_3_);
            //        this.func_175808_b(worldIn, Blocks.mossy_cobblestone.getDefaultState(), j1, -1, i1, p_74875_3_);
            //    }
            //}

            //for (i1 = 5; i1 < 12; ++i1)
            //{
            //    for (j1 = 2; j1 < 10; ++j1)
            //    {
            //        this.clearCurrentPositionBlocksUpwards(worldIn, j1, 7, i1, p_74875_3_);
            //        this.func_175808_b(worldIn, Blocks.mossy_cobblestone.getDefaultState(), j1, -1, i1, p_74875_3_);
            //    }
            //}

            this.spawnVillagers(worldIn, p_74875_3_, 4, 1, 2, 2);
            return true;
        }
    }

    public static class House4Garden extends CulturalHalloweenStructures.Village
    {
        private boolean isRoofAccessible;
        private static final String __OBFID = "CL_00000523";

        public House4Garden() {}

        public House4Garden(CulturalHalloweenStructures.Start p_i45566_1_, int p_i45566_2_, Random p_i45566_3_, StructureBoundingBox p_i45566_4_, EnumFacing p_i45566_5_)
        {
            super(p_i45566_1_, p_i45566_2_);
            this.coordBaseMode = p_i45566_5_;
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = p_i45566_3_.nextBoolean();
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound p_143012_1_)
        {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setBoolean("Terrace", this.isRoofAccessible);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound p_143011_1_)
        {
            super.readStructureFromNBT(p_143011_1_);
            this.isRoofAccessible = p_143011_1_.getBoolean("Terrace");
        }

        public static CulturalHalloweenStructures.House4Garden func_175858_a(CulturalHalloweenStructures.Start p_175858_0_, List p_175858_1_, Random p_175858_2_, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing p_175858_6_, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 8, 10, 8, p_175858_6_);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new CulturalHalloweenStructures.House4Garden(p_175858_0_, p_175858_7_, p_175858_2_, structureboundingbox, p_175858_6_);
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
            //AirFill
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 7, 5, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            //Top & Bottom
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 7, 0, 7, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 4, 0, 7, 4, 7, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 1, 6, 4, 6, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            //Corners
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 3, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 7, 0, 3, 7, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 1, 0, 7, 3, 0, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 1, 7, 7, 3, 7, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            //Walls
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 6, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 7, 1, 1, 7, 3, 6, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 7, 6, 3, 7, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 6, 3, 0, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            //Windows
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 7, 2, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 7, 2, 5, p_74875_3_);
            //RedstoneLamp
            this.func_175811_a(worldIn, Blocks.lever.getDefaultState().withProperty(BlockLever.POWERED, true), 1, 0, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.redstone_lamp.getDefaultState(), 1, 1, 6, p_74875_3_);
            //Interior
            this.func_175804_a(worldIn, p_74875_3_, 6, 1, 1, 6, 2, 1, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.lit_pumpkin.getDefaultState(), 6, 3, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.bed.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 6, 1, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.bed.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 6, 1, 5, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 2, 1, 0, 2, 2, 0, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175810_a(worldIn, p_74875_3_, p_74875_2_, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));
            //Steps
            if (this.func_175807_a(worldIn, 2, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, 2, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
            {
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, p_74875_3_);
            }
            if (!this.isRoofAccessible)
            {
            	this.func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 7, 5, 7, Blocks.wooden_slab.getStateFromMeta(1), Blocks.wooden_slab.getStateFromMeta(1), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 6, 5, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            
            if (this.isRoofAccessible)
            {
            	this.func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 7, 5, 7, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
                this.func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 6, 5, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            int i;

            if (this.isRoofAccessible)
            {
                i = this.getMetadataWithOffset(Blocks.ladder, 3);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 1, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 2, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 3, 6, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 4, 6, p_74875_3_);
            }

            this.func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, p_74875_3_);

            for (i = 0; i < 8; ++i)
            {
                for (int j = 0; j < 8; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 6, i, p_74875_3_);
                    this.func_175808_b(worldIn, Blocks.mossy_cobblestone.getDefaultState(), j, -1, i, p_74875_3_);
                }
            }

            this.spawnVillagers(worldIn, p_74875_3_, 1, 1, 2, 1);
            return true;
        }
    }

    public static class Path extends CulturalHalloweenStructures.Road
    {
        private int averageGroundLevel;
        private static final String __OBFID = "CL_00000528";

        public Path() {}

        public Path(CulturalHalloweenStructures.Start p_i45562_1_, int p_i45562_2_, Random p_i45562_3_, StructureBoundingBox p_i45562_4_, EnumFacing p_i45562_5_)
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
                structurecomponent1 = this.getNextComponentNN((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, i);

                if (structurecomponent1 != null)
                {
                    i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            for (i = p_74861_3_.nextInt(5); i < this.averageGroundLevel - 8; i += 2 + p_74861_3_.nextInt(5))
            {
                structurecomponent1 = this.getNextComponentPP((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, i);

                if (structurecomponent1 != null)
                {
                    i += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            if (flag && p_74861_3_.nextInt(3) > 0 && this.coordBaseMode != null)
            {
                switch (CulturalHalloweenStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                {
                    case 1:
                        CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
                        break;
                    case 2:
                        CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
                        break;
                    case 3:
                        CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                        break;
                    case 4:
                        CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }

            if (flag && p_74861_3_.nextInt(3) > 0 && this.coordBaseMode != null)
            {
                switch (CulturalHalloweenStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                {
                    case 1:
                        CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
                        break;
                    case 2:
                        CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
                        break;
                    case 3:
                        CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                        break;
                    case 4:
                        CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
        }

        public static StructureBoundingBox func_175848_a(CulturalHalloweenStructures.Start p_175848_0_, List p_175848_1_, Random p_175848_2_, int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing p_175848_6_)
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
            IBlockState iblockstate = this.func_175847_a(Blocks.soul_sand.getDefaultState());
            IBlockState iblockstate1 = this.func_175847_a(Blocks.mossy_cobblestone.getDefaultState());

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

    public abstract static class Road extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000532";

        public Road() {}

        protected Road(CulturalHalloweenStructures.Start p_i2108_1_, int p_i2108_2_)
        {
            super(p_i2108_1_, p_i2108_2_);
        }
    }

    public static class Start extends CulturalHalloweenStructures.Well
    {
        public WorldChunkManager worldChunkMngr;
        /** Boolean that determines if the village is in a desert or not. */
        public boolean inDesert;
        /** World terrain type, 0 for normal, 1 for flap map */
        public int terrainType;
        public CulturalHalloweenStructures.PieceWeight structVillagePieceWeight;
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
            super((CulturalHalloweenStructures.Start)null, 0, p_i2104_3_, p_i2104_4_, p_i2104_5_);
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

    public static class Torch extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000520";

        public Torch() {}

        public Torch(CulturalHalloweenStructures.Start p_i45568_1_, int p_i45568_2_, Random p_i45568_3_, StructureBoundingBox p_i45568_4_, EnumFacing p_i45568_5_)
        {
            super(p_i45568_1_, p_i45568_2_);
            this.coordBaseMode = p_i45568_5_;
            this.boundingBox = p_i45568_4_;
        }

        public static StructureBoundingBox func_175856_a(CulturalHalloweenStructures.Start p_175856_0_, List p_175856_1_, Random p_175856_2_, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing p_175856_6_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 3, 4, 3, p_175856_6_);
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

            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 2, 3, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 1, 0, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 1, 1, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 1, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 1, 1, -1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.dark_oak_fence.getDefaultState(), 1, 1, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 1, 2, -1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 1, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.lit_pumpkin.getDefaultState(), 1, 3, 0, p_74875_3_);
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
        private CulturalHalloweenStructures.Start startPiece;

        public Village() {}

        protected Village(CulturalHalloweenStructures.Start p_i2107_1_, int p_i2107_2_)
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
        protected StructureComponent getNextComponentNN(CulturalHalloweenStructures.Start p_74891_1_, List p_74891_2_, Random p_74891_3_, int p_74891_4_, int p_74891_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (CulturalHalloweenStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                {
                    case 1:
                        return CulturalHalloweenStructures.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                    case 2:
                        return CulturalHalloweenStructures.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                    case 3:
                        return CulturalHalloweenStructures.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    case 4:
                        return CulturalHalloweenStructures.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }

            return null;
        }

        /**
         * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
         */
        protected StructureComponent getNextComponentPP(CulturalHalloweenStructures.Start p_74894_1_, List p_74894_2_, Random p_74894_3_, int p_74894_4_, int p_74894_5_)
        {
            if (this.coordBaseMode != null)
            {
                switch (CulturalHalloweenStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
                {
                    case 1:
                        return CulturalHalloweenStructures.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                    case 2:
                        return CulturalHalloweenStructures.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                    case 3:
                        return CulturalHalloweenStructures.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    case 4:
                        return CulturalHalloweenStructures.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
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

                if (p_175847_1_.getBlock() == Blocks.mossy_cobblestone)
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

    public static class Well extends CulturalHalloweenStructures.Village
    {
        private static final String __OBFID = "CL_00000533";

        public Well() {}

        public Well(CulturalHalloweenStructures.Start p_i2109_1_, int p_i2109_2_, Random p_i2109_3_, int p_i2109_4_, int p_i2109_5_)
        {
            super(p_i2109_1_, p_i2109_2_);
            this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2109_3_);

            switch (CulturalHalloweenStructures.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()])
            {
                case 1:
                case 2:
                    this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 9 - 1, 78, p_i2109_5_ + 9 - 1);
                    break;
                default:
                    this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 9 - 1, 78, p_i2109_5_ + 9 - 1);
            }
        }

        /**
         * Initiates construction of the Structure Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
        {
            CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
            CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
            CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            CulturalHalloweenStructures.func_176069_e((CulturalHalloweenStructures.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
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

                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 14 -1, 0);
            }

            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 6, 8, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 1, 1, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 0, 0, 2, 1, 2, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, -1, 2, 7, 7, 2, 7, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, -1, 2, -1, 7, 2, 7, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 0, 3, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 3, 0, 6, 3, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 0, 6, 3, 0, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 6, 6, 3, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 0, 2, 4, 3, 5, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 0, 5, 2, 1, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 0, 0, 3, 1, 2, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 0, 5, 3, 1, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 0, 0, 4, 1, 2, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 0, 5, 4, 1, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 0, 0, 6, 1, 6, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 2, 6, Blocks.nether_brick.getStateFromMeta(1), Blocks.nether_brick.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 6, 2, 0, 6, 2, 6, Blocks.nether_brick.getStateFromMeta(1), Blocks.nether_brick.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 2, 1, 1, 2, 5, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 2, 1, 5, 2, 5, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 1, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 1, 2, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 2, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 2, 2, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 3, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 3, 2, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 4, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 4, 2, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 5, 2, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.nether_brick.getStateFromMeta(1), 5, 2, 6, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getStateFromMeta(1), 2, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getStateFromMeta(1), 2, 2, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getStateFromMeta(1), 3, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getStateFromMeta(1), 3, 2, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getStateFromMeta(1), 4, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.mossy_cobblestone.getStateFromMeta(1), 4, 2, 5, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 1, 1, 3, 5, Blocks.mycelium.getDefaultState(), Blocks.mycelium.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 3, 1, 5, 3, 5, Blocks.mycelium.getDefaultState(), Blocks.mycelium.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 1, 5, 3, 1, Blocks.mycelium.getDefaultState(), Blocks.mycelium.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 5, 5, 3, 5, Blocks.mycelium.getDefaultState(), Blocks.mycelium.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 1, 1, 5, 1, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 4, 1, 5, 5, 1, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 5, 1, 5, 5, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 4, 5, 5, 5, 5, Blocks.dark_oak_fence.getDefaultState(), Blocks.dark_oak_fence.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 6, 1, 1, 6, 5, Blocks.wooden_slab.getStateFromMeta(1), Blocks.wooden_slab.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 6, 1, 5, 6, 5, Blocks.wooden_slab.getStateFromMeta(1), Blocks.wooden_slab.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 6, 1, 5, 6, 1, Blocks.wooden_slab.getStateFromMeta(1), Blocks.wooden_slab.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 6, 5, 5, 6, 5, Blocks.wooden_slab.getStateFromMeta(1), Blocks.wooden_slab.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 6, 2, 2, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 6, 2, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 6, 2, 4, 6, 2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 2, 6, 4, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
            
                
            //for (int i = 0; i <= 5; ++i)
            //{
            //    for (int j = 0; j <= 5; ++j)
            //    {
            //        if (j == 0 || j == 5 || i == 0 || i == 5)
            //        {
            //            this.func_175811_a(worldIn, Blocks.gravel.getDefaultState(), j, 11, i, p_74875_3_);
            //            this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, p_74875_3_);
            //        }
            //    }
            //}

            return true;
        }
    }

    public static class WoodHut extends CulturalHalloweenStructures.Village
    {
    	public static final int SKULL = 4100;
    	private static final String __OBFID = "CL_00000524";
    	
        public WoodHut() {}
        
        public WoodHut(CulturalHalloweenStructures.Start p_i45565_1_, int p_i45565_2_, Random p_i45565_3_, StructureBoundingBox p_i45565_4_, EnumFacing p_i45565_5_)
        {
            super(p_i45565_1_, p_i45565_2_);
            this.coordBaseMode = p_i45565_5_;
            this.boundingBox = p_i45565_4_;
        }

        public static CulturalHalloweenStructures.WoodHut func_175853_a(CulturalHalloweenStructures.Start p_175853_0_, List p_175853_1_, Random p_175853_2_, int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing p_175853_6_, int p_175853_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, 4, 6, 5, p_175853_6_);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175853_1_, structureboundingbox) == null ? new CulturalHalloweenStructures.WoodHut(p_175853_0_, p_175853_7_, p_175853_2_, structureboundingbox, p_175853_6_) : null;
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

                this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
            }

            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 3, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 3, 0, 4, Blocks.mossy_cobblestone.getDefaultState(), Blocks.mossy_cobblestone.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 2, 5, 3, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 1, 4, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 2, 4, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 1, 4, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 2, 4, 4, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 0, 4, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 0, 4, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 0, 4, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 3, 4, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 3, 4, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.log2.getStateFromMeta(1), 3, 4, 3, p_74875_3_);
            
            this.func_175811_a(worldIn, Blocks.lit_pumpkin.getStateFromMeta(this.getMetadataWithOffset(Blocks.lit_pumpkin, 0)), 3, 4, 0, p_74875_3_);
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 3, 0, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 3, 3, 0, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 4, 0, 3, 4, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 1, 4, 3, 3, 4, Blocks.log2.getStateFromMeta(1), Blocks.log2.getStateFromMeta(1), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 3, 1, 1, 3, 3, 3, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 2, 3, 0, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 4, 2, 3, 4, Blocks.planks.getStateFromMeta(5), Blocks.planks.getStateFromMeta(5), false);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.web.getDefaultState(), 0, 2, 1, p_74875_3_);
            
            this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 1, 0, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 2, 0, p_74875_3_);
            
            BlockPos blockpos = new BlockPos(this.getXWithOffset(1, 0), this.getYWithOffset(1), this.getZWithOffset(1, 0));
            if (p_74875_3_.func_175898_b(blockpos))
            {
                	ItemDoor.placeDoor(worldIn, blockpos, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.dark_oak_door, 1)), Blocks.dark_oak_door);
            }
            
            BlockPos blockpos1 = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(4), this.getZWithOffset(0, 0));
            if (p_74875_3_.func_175898_b(blockpos1))
            {
            	worldIn.setBlockState(blockpos1, Blocks.skull.getDefaultState().withProperty(BlockSkull.FACING, this.coordBaseMode.getOpposite()));

            	TileEntity te = worldIn.getTileEntity(blockpos1);

            	if (te instanceof TileEntitySkull) {

            	 ((TileEntitySkull) te).setSkullRotation(0); // just a random value for testing

            	 ((TileEntitySkull) te).setType(1); // wither skull

            	}
            }
            
            if (this.func_175807_a(worldIn, 1, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && this.func_175807_a(worldIn, 1, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
            {
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, p_74875_3_);
            }

            for (int i = 0; i < 5; ++i)
            {
                for (int j = 0; j < 4; ++j)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, j, 6, i, p_74875_3_);
                    this.func_175808_b(worldIn, Blocks.mossy_cobblestone.getDefaultState(), j, -1, i, p_74875_3_);
                }
            }

            this.spawnVillagers(worldIn, p_74875_3_, 1, 1, 2, 1);
            return true;
        }
    }
}