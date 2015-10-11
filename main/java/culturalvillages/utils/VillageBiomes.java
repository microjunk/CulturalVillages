package culturalvillages.utils;

import net.minecraft.world.biome.BiomeGenBase;

public enum VillageBiomes
{
	INDIAN_VILLAGE (BiomeGenBase.mesa);
	
	private BiomeGenBase biomes;
	
	private VillageBiomes(BiomeGenBase biomes)
	{
		this.biomes = biomes;
	}

	public BiomeGenBase getBiomes() {
		return biomes;
	}
	
	public boolean canSpawnInBiome(BiomeGenBase biome)
	{
		return getBiomes() != null;
	}
}