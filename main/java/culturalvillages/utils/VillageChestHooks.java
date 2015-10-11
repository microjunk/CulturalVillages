package culturalvillages.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;

public class VillageChestHooks extends ChestGenHooks
{
	public static final String SWAMPHUT_CHEST = "swampHutChest";
	public static final String CATACOMB_CHEST = "catacombChest";
	public static final String INN_CHEST = "underInnChest";
	private static final HashMap<String, ChestGenHooks> chestInfo = new HashMap<String, ChestGenHooks>();
	private static boolean hasInit = false;
	
	static
	{
	init();
	}
	
	private static void init()
	{
		if (hasInit)
		{
			return;
		}
		hasInit = true;
		//addInfo(SWAMPHUT_CHEST, WitchHutt.itemsToGenerateInHut, 3, 7);
		//addInfo(CATACOMB_CHEST, Catacomb.itemsToGenerateCatacomb, 4, 8);
		//addInfo(INN_CHEST, Inn.itemsToGenerateInn, 2, 5);
	}
	
	public VillageChestHooks(String category, List<WeightedRandomChestContent> items, int min, int max)
	{
		super(category, items, min, max);
	}
	
	private static void addInfo(String category, List<WeightedRandomChestContent> items, int min, int max)
	{
	chestInfo.put(category, new ChestGenHooks(category, items, min, max));
	}
	
	/**
	* Retrieves, or creates the info class for the specified category.
	*
	* @param category The category name
	* @return A instance of ChestGenHooks for the specified category.
	*/
	public static ChestGenHooks getInfo(String category)
	{
		if (!chestInfo.containsKey(category))
		{
			chestInfo.put(category, new ChestGenHooks(category));
		}
		return chestInfo.get(category);
	}
	//shortcut functions, See the non-static versions below
	public static List<WeightedRandomChestContent> getItems(String category, Random rnd){ return getInfo(category).getItems(rnd); }
	public static int getCount(String category, Random rand){ return getInfo(category).getCount(rand); }
	public static void addItem(String category, WeightedRandomChestContent item){ getInfo(category).addItem(item); }
	public static void removeItem(String category, ItemStack item){ getInfo(category).removeItem(item); }
	public static ItemStack getOneItem(String category, Random rand){ return getInfo(category).getOneItem(rand); }
}