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
	
}