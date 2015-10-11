package culturalvillages;

import java.util.logging.Logger;

import culturalvillages.mapgen.MapGenCulturalVillages;
import culturalvillages.pieces.CulturalHalloweenStructures;
import culturalvillages.pieces.CulturalIndianStructures;
import culturalvillages.pieces.CulturalJungleStructures;
import culturalvillages.pieces.CulturalOldWestStructures;
import culturalvillages.pieces.CulturalVanillaStructures;
import culturalvillages.pieces.CulturalVikingStructures;
import culturalvillages.proxy.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CulturalVillages.MODID, name = CulturalVillages.NAME, version = CulturalVillages.VERSION)
public class CulturalVillages
{
    public static final String MODID = "culturalvillages";
    public static final String NAME = "CulturalVillages";
    public static final String VERSION = "v.0.1.0";
    //Test Seed 7208794910977586159
    @Mod.Instance(CulturalVillages.MODID)
    public static CulturalVillages instance;
    
    @SidedProxy(clientSide="culturalvillages.proxy.ClientProxy", serverSide="culturalvillages.proxy.ServerProxy")
    public static ServerProxy proxy;
    
    @EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
    	//Uniformlogger = Logger.getLogger("CulturalVillages");
    	
    	//BiomeManager.addVillageBiome(BiomeGenBase.beach, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.birchForest, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.birchForestHills, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.coldBeach, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.coldTaiga, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.coldTaigaHills, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.deepOcean, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.desertHills, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.extremeHills, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.extremeHillsEdge, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.extremeHillsPlus, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.forest, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.forestHills, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.frozenOcean, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.frozenRiver, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.iceMountains, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.icePlains, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.jungle, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.jungleEdge, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.jungleHills, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.megaTaiga, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.megaTaigaHills, true);
    	BiomeManager.addVillageBiome(BiomeGenBase.mesa, true);
    	BiomeManager.addVillageBiome(BiomeGenBase.mesaPlateau, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.mesaPlateau_F, true);
    	BiomeManager.addVillageBiome(BiomeGenBase.mushroomIsland, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.mushroomIslandShore, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.ocean, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.river, true);
    	BiomeManager.addVillageBiome(BiomeGenBase.roofedForest, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.savannaPlateau, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.stoneBeach, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.swampland, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.taiga, true);
    	//BiomeManager.addVillageBiome(BiomeGenBase.taigaHills, true);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//CulturalVanillaStructures.registerVillagePieces();
    	//CulturalJungleStructures.registerVillagePieces();
    	//CulturalVikingStructures.registerVillagePieces();
    	CulturalHalloweenStructures.registerVillagePieces();
    	CulturalIndianStructures.registerVillagePieces();
    	//CulturalOldWestStructures.registerVillagePieces();
				
    	MapGenStructureIO.registerStructure(MapGenCulturalVillages.Start.class, CulturalVillages.MODID + ".Village"); 
    	
		//EnumVillager.register();
		//EnumEntity.register();
    	

		CulturalVillages.proxy.registerHandler();
		
    }
    
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
    	
    }
}