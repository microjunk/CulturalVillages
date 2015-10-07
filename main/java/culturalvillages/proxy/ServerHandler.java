package culturalvillages.proxy;

import culturalvillages.mapgen.MapGenCulturalVillages;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ServerHandler
{
	public final static MapGenCulturalVillages mapGenUniformVillage = new MapGenCulturalVillages();
	public static MapGenStructure mapGenVillage;
	
	//public final static EntityEggInfo vanillaVillagerEggInfo = (EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(120));

	
	@SubscribeEvent
	public void OnInitMapGen(InitMapGenEvent event)
	{
		if (EventType.VILLAGE == event.type)
		{
			mapGenVillage = (MapGenStructure) event.originalGen;
			event.newGen = mapGenUniformVillage;
		}
	}
	
	@SubscribeEvent
	public void OnWorldUnload(WorldEvent.Unload event) {
		//AConfigTool.loadAllConfig(false);
		//AConfigTool.saveAllConfig(false);
	}
	
	@SubscribeEvent
	public void OnWorldLoad(WorldEvent.Load event) {
		//AConfigTool.loadAllConfig(true);
		//AConfigTool.saveAllConfig(true);
		//VillageHelper.load();
	}
    
	/*@SubscribeEvent
    public void OnSpawnEntity(CheckSpawn event) {
    	int x = (int) event.x;
    	int y = (int) event.y;
    	int z = (int) event.z;

    	boolean flag = false;
    	for (int tempX = -1; tempX <= 1; tempX++)
        for (int tempZ = -1; tempZ <= 1; tempZ++)
        {
        	int tempY = y;
        	while (event.world.getBlock(x, tempY, z).equals(Blocks.air) || event.world.getBlock(x, tempY, z) instanceof BlockFalling) {
        		tempY--;
        	}
	    	if (event.world.getBlock(x, tempY, z).equals(EnumBlock.fungal.block)
	    			|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_mushroom_cap.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_mushroom_cap.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_water.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_mushroom.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_mushroom.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_vine.block)) {
	    		flag = true;
	    	}
        }
    	if (flag) {
			if (event.entity instanceof EntityWitch || event.entity instanceof EntityWorshipper) {
				event.setResult(Result.ALLOW);
			} else {
				event.setResult(Result.DENY);
			}
    	} else if (event.entity instanceof EntityWorshipper) {
			event.setResult(Result.DENY);
    	}
    	
    	if (event.entity instanceof EntitySpider)
    	{
	    	for (int tempX = -1; tempX <= 1; tempX++)
	        for (int tempZ = -1; tempZ <= 1; tempZ++)
            {
    	    	if (event.world.getBlock(x, y, z).equals(Blocks.web)) {
    	    		event.setResult(Result.ALLOW);
    	    	}
            }
		}
    }

	private final WorldGenerator blueMushroom = new WorldGenBlueMushroom();
	private final WorldGenerator phosMushroom = new WorldGenPhosMushroom();
	private final WorldGenerator vineMushroom = new WorldGenBlueVine();
	private final WorldGenerator fungusGen = new WorldGenFungus(1,1);
	
	public static List<Integer[]> listShroomCaveChunk = new ArrayList<Integer[]>();
	public static List<Integer[]> listSpiderDenChunk = new ArrayList<Integer[]>();
	
	@SubscribeEvent
	public void OnDecorate(Decorate event)
    {
		if (event.type == Decorate.EventType.BIG_SHROOM || event.type == Decorate.EventType.SHROOM || event.type == Decorate.EventType.GRASS)
		for (Integer[] chunkP : listShroomCaveChunk)
		{
			if (event.chunkX == chunkP[0] && event.chunkZ == chunkP[1])
			{
				if (event.type == Decorate.EventType.BIG_SHROOM) {
			        for (int j = 0; j < 3; ++j)
			        {
			            int x = event.chunkX + event.rand.nextInt(16) + 8;
			            int z = event.chunkZ + event.rand.nextInt(16) + 8;
			            for (int y = 20; y<50; y++) {
			            	if (event.world.getBlock(x, y, z).equals(EnumBlock.fungal.block)) {
			            		if (event.rand.nextInt(8)==0) {
			            			phosMushroom.generate(event.world, event.rand, x, y+1, z);
			            		}
			            		else {
			            			blueMushroom.generate(event.world, event.rand, x, y+1, z);
			            		}
			            		break;
			            	}
			            }
			        }
				} else if (event.type == Decorate.EventType.SHROOM) {
			        for (int j = 0; j < 10; ++j)
			        {
			        	int x = event.chunkX + event.rand.nextInt(16) + 8;
			            int z = event.chunkZ + event.rand.nextInt(16) + 8;
			            for (int y = 20; y<50; y++) {
			            	if (event.world.getBlock(x, y, z).equals(EnumBlock.fungal.block) && event.world.getBlock(x, y+1, z).equals(Blocks.air)) {
			            		if (event.rand.nextInt(8)==0) {
				            		event.world.setBlock(x, y+1, z, EnumBlock.phos_mushroom.block);
			            		}
			            		else {
				            		event.world.setBlock(x, y+1, z, EnumBlock.blue_mushroom.block);
			            		}
			            	}
			            }
			        }
				} else if (event.type == Decorate.EventType.GRASS) {
			        for (int tempX = 8; tempX < 24; ++tempX)
				    for (int tempZ = 8; tempZ < 24; ++tempZ)
			        {
			        	int x = event.chunkX + tempX;
			            int z = event.chunkZ + tempZ;
			            
			            for (int tempY = 30; tempY < 60; tempY++) {
			            	if (	event.world.getBlock(x, tempY, z).getMaterial().equals(Material.air)
			            			&& event.world.getBlock(x, tempY-1, z).getMaterial().equals(Material.air)
			            			&& event.world.getBlock(x, tempY+1, z).getMaterial().equals(Material.air)
			            			&& (	event.world.getBlock(x+1, tempY, z).equals(Blocks.stone)
			            					|| event.world.getBlock(x-1, tempY, z).equals(Blocks.stone)
			            					|| event.world.getBlock(x, tempY, z+1).equals(Blocks.stone)
			            					|| event.world.getBlock(x, tempY, z-1).equals(Blocks.stone)
			            				)
			            			&& event.rand.nextInt(40)==0
			            			)
			            	{
			            		List<Integer[]> solution = new ArrayList<Integer[]>();
			            		if (tempX-1>=8)
			            			solution.add(new Integer[]{x-1,tempY,z});
			            		if (tempX+1<24)
			            			solution.add(new Integer[]{x+1,tempY,z});
			            		if (tempZ-1>=8)
			            			solution.add(new Integer[]{x,tempY,z-1});
			            		if (tempZ+1<24)
			            			solution.add(new Integer[]{x,tempY,z+1});
			            		Collections.shuffle(solution);
			            		while (!solution.isEmpty() && !event.world.getBlock(solution.get(0)[0],solution.get(0)[1],solution.get(0)[2]).getMaterial().equals(Material.rock)) {
			            			solution.remove(0);
			            		}
			            		if (!solution.isEmpty())
			            			fungusGen.generate(event.world, event.rand, solution.get(0)[0], solution.get(0)[1], solution.get(0)[2]);
			            		solution.clear();
			            	}
			            }
			            
			            if (event.rand.nextInt(5)==0) {
				            for (int tempY = 30; tempY < 60; tempY++) {
				            	if (event.world.getBlock(x, tempY, z).equals(Blocks.air) && event.world.getBlock(x, tempY+1, z).getMaterial().equals(Material.rock))
				            	{
				            		vineMushroom.generate(event.world, event.rand, x, tempY, z);
				            	}
				            }
			            }
					}
				}
			}
		}
    }
	
	@SubscribeEvent
    public void OnPostDecorate(DecorateBiomeEvent.Post event) {
		for (Integer[] chunkP : listShroomCaveChunk) {
			if (event.chunkX == chunkP[0] && event.chunkZ == chunkP[1]) {
				listShroomCaveChunk.remove(chunkP);
				return;
			}
		}
	}
	
	private final static Map<Block, Item> buckets = new HashMap<Block, Item>();
	static {
		buckets.put(EnumBlock.phos_water.block, EnumItem.phos_water_bucket.item);
		buckets.put(EnumBlock.poison_water.block, EnumItem.poison_water_bucket.item);
	}
	
	@SubscribeEvent	
	public void onBucketFill(FillBucketEvent event) {
		Block block = event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);

		if (event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ) == 0) 
		{
			for (Block bukketBlock : buckets.keySet()) {
				if (block.equals(bukketBlock)) {
					event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
					ItemStack result = new ItemStack(buckets.get(bukketBlock));
					event.result = result;
					event.setResult(Result.ALLOW);
					return;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void OnArrowNockEvent(ArrowNockEvent event) {
		if (event.entityPlayer.capabilities.isCreativeMode || event.entityPlayer.inventory.hasItem(EnumItem.spider_gland_arrow.item))
        {
        	event.entityPlayer.setItemInUse(event.result, Items.bow.getMaxItemUseDuration(event.result));
    		event.setCanceled(true);
        }
	}
	@SubscribeEvent
	public void OnArrowLooseEvent(ArrowLooseEvent event) {
		boolean flag = event.entityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, event.bow) > 0;

        if (flag || event.entityPlayer.inventory.hasItem(EnumItem.spider_gland_arrow.item))
        {
            float f = (float)event.charge / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntitySpiderGlandArrow entityarrow = new EntitySpiderGlandArrow(event.entityPlayer.worldObj, event.entityPlayer, f * 2.0F);

            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, event.bow);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, event.bow);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, event.bow) > 0)
            {
                entityarrow.setFire(100);
            }

            event.bow.damageItem(1, event.entityPlayer);
            event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "random.bow", 1.0F, 1.0F / (event.entityPlayer.worldObj.rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
            	event.entityPlayer.inventory.consumeInventoryItem(EnumItem.spider_gland_arrow.item);
            }

            if (!event.entityPlayer.worldObj.isRemote)
            {
            	event.entityPlayer.worldObj.spawnEntityInWorld(entityarrow);
            }
            
            event.setCanceled(true);
        }
	}*/

}