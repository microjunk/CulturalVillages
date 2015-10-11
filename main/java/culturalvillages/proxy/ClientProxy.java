package culturalvillages.proxy;

import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends ServerProxy
{
	@Override
	public void registerHandler() {
		handler = new ClientHandler();
    	MinecraftForge.TERRAIN_GEN_BUS.register(handler);    	  	
    	MinecraftForge.EVENT_BUS.register(handler);   
	}
	
	@Override
	public void registerRenderers()
	{
		
	}
}