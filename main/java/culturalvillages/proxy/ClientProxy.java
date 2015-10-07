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
		/*RenderingRegistry.registerEntityRenderingHandler(EntityApache.class, new RenderApache());
		RenderingRegistry.registerEntityRenderingHandler(EntityAztec.class, new RenderAztec());
		RenderingRegistry.registerEntityRenderingHandler(EntityInuit.class, new RenderInuit());
		RenderingRegistry.registerEntityRenderingHandler(EntityZulu.class, new RenderZulu());
		RenderingRegistry.registerEntityRenderingHandler(EntityTibetan.class, new RenderTibetan());
		RenderingRegistry.registerEntityRenderingHandler(EntityEgyptian.class, new RenderEgyptian());
		RenderingRegistry.registerEntityRenderingHandler(EntityLakeside.class, new RenderLakeside());
		RenderingRegistry.registerEntityRenderingHandler(EntitySettled.class, new RenderSettled());
		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderMummy());
		RenderingRegistry.registerEntityRenderingHandler(EntityTzitzimime.class, new RenderTzitzimime());
		RenderingRegistry.registerEntityRenderingHandler(EntityWarriorSkeleton.class, new RenderWarriorSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());
		RenderingRegistry.registerEntityRenderingHandler(EntityWorshipper.class, new RenderWorshipper());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkSpider.class, new RenderDarkSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityYeti.class, new RenderYeti());
		RenderingRegistry.registerEntityRenderingHandler(EntityDwarf.class, new RenderDwarf());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkSpiderTest.class, new RenderDarkSpiderTest());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderProjectile.class, new RenderSnowball(EnumItem.spider_projectile.item));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderGlandArrow.class, new RenderSpiderGlandArrow());*/
	}
	
	/**
	  * To create eggs by getting colors from texture.
	  * @param entity
	  * @return
	  */
	/*@Override
	public Integer[] searchEggColor(EnumEntity entity)
    {
	    int principalColor = 0x000000;
	    int secondColor = 0xffffff;

		ResourceLocation resource = ResourceTools.getResource(entity.entityClass);
		if (resource == null) {
			return new Integer[] {principalColor, secondColor};
		}
	    
	    InputStream inputstream = null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
   		try
   		{

            IResource iresource = Minecraft.getMinecraft().getResourceManager().getResource(resource);
            inputstream = iresource.getInputStream();
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            
		    for (int x = 0; x < bufferedimage.getWidth(); x++)
			{
			    for (int y = 0; y < bufferedimage.getHeight(); y++)
			    {
				    int color = bufferedimage.getRGB(x, y);
				    if (((color>>24) & 0xff) != 0)
				    {
					    if (map.containsKey(color))
					    {
						    map.put(color, map.get(color) + 1);
					    }
					    else
					    {
						    map.put(color, 1);
					    }
				    }
				}
			}    
		} catch (IOException e) {
	   		e.printStackTrace();
	   	}
   		finally
   		{ 
   			if (inputstream != null)
            {
                try {
   					inputstream.close();
   				} catch (IOException e) {
   					e.printStackTrace();
   				}
             }	
   		}
	    Collection<Integer> values = map.values();
	    int principalCount = 0;
	    int secondCount = 0;
	
	    for (Integer color : map.keySet())
	    {
		    if (map.get(color) > principalCount)
		    {
			    principalCount = map.get(color);
			    principalColor = color;
		    }
	
		    if (map.get(color) >= secondCount && map.get(color) != principalCount)
		    {
			    int principal= (principalColor >> 16) & 0x000000FF + (principalColor >>8 ) & 0x000000FF + (principalColor) & 0x000000FF;
			    int thisone= (color >> 16) & 0x000000FF + (color >>8 ) & 0x000000FF + (color) & 0x000000FF;
			    
		    	if (Math.abs(principal-thisone) > 150)
		    	{
				    secondCount = map.get(color);
				    secondColor = color;
		    	}
		    }
		}
	    return new Integer[] {principalColor, secondColor};
	}*/
	
	/**
	  * To create eggs by getting colors from texture.
	  * @param tribe
	  * @return
	  */
	/*@Override
	public Integer[] searchEggColor(EnumTribe tribe)
    {
	    int principalColor = 0x000000;
	    int secondColor = 0xffffff;
	    
	    InputStream inputstream = null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	   	for (EnumVillager villager : tribe.villagers)
	   	{
   			ResourceLocation resource = VillagerRegistry.getVillagerSkin(villager.profession, null);
   			if (resource == null) {
   				continue;
   			}
	   		try
	   		{
	            IResource iresource = Minecraft.getMinecraft().getResourceManager().getResource(resource);
	            inputstream = iresource.getInputStream();
	            BufferedImage bufferedimage = ImageIO.read(inputstream);
	            
			    for (int x = 0; x < bufferedimage.getWidth(); x++)
				{
				    for (int y = 0; y < bufferedimage.getHeight(); y++)
				    {
					    int color = bufferedimage.getRGB(x, y);
					    if (((color>>24) & 0xff) != 0)
					    {
						    if (map.containsKey(color))
						    {
							    map.put(color, map.get(color) + 1);
						    }
						    else
						    {
							    map.put(color, 1);
						    }
					    }
					}
				}    
			} catch (IOException e) {
		   		e.printStackTrace();
		   	}
	   		finally
	   		{ 
	   			if (inputstream != null)
	            {
	                try {
	   					inputstream.close();
	   				} catch (IOException e) {
	   					e.printStackTrace();
	   				}
	             }	
	   		}
	   	}
	    Collection<Integer> values = map.values();
	    int principalCount = 0;
	    int secondCount = 0;
	
	    for (Integer color : map.keySet())
	    {
		    if (map.get(color) > principalCount)
		    {
			    principalCount = map.get(color);
			    principalColor = color;
		    }
	
		    if (map.get(color) >= secondCount && map.get(color) != principalCount)
		    {
			    int principal= (principalColor >> 16) & 0x000000FF + (principalColor >>8 ) & 0x000000FF + (principalColor) & 0x000000FF;
			    int thisone= (color >> 16) & 0x000000FF + (color >>8 ) & 0x000000FF + (color) & 0x000000FF;
			    
		    	if (Math.abs(principal-thisone) > 150)
		    	{
				    secondCount = map.get(color);
				    secondColor = color;
		    	}
		    }
		}
	    return new Integer[] {principalColor, secondColor};
	}*/
	
	/*private static Map<Class, Class> map = new HashMap<Class, Class>();
	static {
		map.put(ItemBlowgun.class, RenderBlowgun.class);
		map.put(ItemSpear.class, RenderSpear.class);
	}*/
	
	/*@Override
	public void registerItemRenderer(EnumItem item) {
		if (map.containsKey(item.item.getClass())) {
			try {
				MinecraftForgeClient.registerItemRenderer(item.item, (IItemRenderer)map.get(item.item.getClass()).getConstructor().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	/*static {
		map.put(BlockFrozenChest.class, RenderBlockFrozenChest.class);
	}*/
	
	/*@Override
	public void registerBlockRenderer(EnumBlock block) {
		if (map.containsKey(block.blockClass)) {
			try {
				RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)map.get(block.blockClass).getConstructor().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	static {
		map.put(TileEntityFrozenChest.class, TileEntityFrozenChestRenderer.class);
	}
	
	@Override
	public void registerTileEntityRenderer(EnumBlock block) {
		if (map.containsKey(block.tileEntityClass)) {
			try {
				TileEntitySpecialRenderer renderer = (TileEntitySpecialRenderer) map.get(block.tileEntityClass).getConstructor().newInstance();
				TileEntityRendererDispatcher.instance.mapSpecialRenderers.put(block.tileEntityClass, renderer);
				renderer.func_147497_a(TileEntityRendererDispatcher.instance);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void registerVillagerSkin(EnumVillager villager) {
		ResourceLocation resource = new ResourceLocation(Diversity.MODID, villager.resourcePath);
		VillagerRegistry.instance().registerVillagerSkin(villager.profession, resource);
	}
	
	@Override
	public void registerEntityResource(EnumEntity entity) {
		ResourceTools.register(entity.entityClass, entity.resourcePath);
	}
	
	@Override
	public void registerBlockRessource(EnumBlock block) {
		ResourceTools.register(block.blockClass, block.resourcePath);
	}

	@Override
	public String getI18format(EnumVillager villager) {
		return I18n.format("entity." + Diversity.MODID + '.' + villager.tribe.name().toLowerCase() + '.' + villager.villagerName + ".name", new Object[0]);
	}*/
}