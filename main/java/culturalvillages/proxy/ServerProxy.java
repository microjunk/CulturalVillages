package culturalvillages.proxy;

import net.minecraftforge.common.MinecraftForge;

public class ServerProxy
{
	public ServerHandler handler;
	
	public void registerHandler()
	{
		handler = new ServerHandler();
    	MinecraftForge.TERRAIN_GEN_BUS.register(handler);    	  	
    	MinecraftForge.EVENT_BUS.register(handler);   
	}
	
	public void registerRenderers() {}

	//public Integer[] searchEggColor(EnumTribe tribe)
	//{
	//	return new Integer[] {0,0};
	//}
	
	//public Integer[] searchEggColor(EnumEntity monster)
	//{
	//	return new Integer[] {0,0};
	//}

	//public void registerVillagerSkin(EnumVillager villager) {}
	
	//public void registerEntityResource(EnumEntity entity) {}
	
	//public String getI18format(EnumVillager villager)
	//{
	//	return villager.villagerName;
	//}

	//public void registerItemRenderer(EnumItem item) {}

	//public Block getBlockAtEntityViewPoint(EntityLivingBase entity, float renderPartialTicks) {return null;}

	//public void registerBlockRenderer(EnumBlock block) {}

	//public void registerBlockRessource(EnumBlock block) {}
	
	//public void registerTileEntityRenderer(EnumBlock block) {}

}