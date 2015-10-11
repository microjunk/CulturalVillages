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
	
	@SubscribeEvent
	public void OnInitMapGen(InitMapGenEvent event)
	{
		if (EventType.VILLAGE == event.type)
		{
			mapGenVillage = (MapGenStructure) event.originalGen;
			event.newGen = mapGenUniformVillage;
		}
	}
}