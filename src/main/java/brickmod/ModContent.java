package brickmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;

@Mod.EventBusSubscriber(modid = BrickMod.MODID)
public class ModContent {
	public static List<Block> blocks = new ArrayList<>();
	public static List<Item> items = new ArrayList<>();

	static {
		addBricks("granite_bricks");
		addBricks("brown_bricks");
		addBricks("light_bricks");
		addBricks("magenta_painted_bricks");
		addBricks("gray_painted_bricks");
		addBricks("street_tiles");
		addBricks("andesite_tiles");
		addBricks("black_rectangular_shingles");
		addBricks("dark_red_rectangular_shingles");
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Block block : blocks) {
			event.getRegistry().register(block);
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Item item : items) {
			event.getRegistry().register(item);
		}
	}

	static void addBricks(String id) {
		Block b = block(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS), id);
		itemBlock(new ItemBlock(b));
	}
	
	public static <B extends Block> B block(B block, String name) {
		block.setRegistryName(new ResourceLocation(BrickMod.MODID, name));
		block.setTranslationKey(BrickMod.MODID + "." + name);
		blocks.add(block);
		return block;
	}
	
	private static <IB extends ItemBlock> IB itemBlock(IB item) {
		ResourceLocation registryName = item.getBlock().getRegistryName();
		assert registryName != null;
		item.setRegistryName(registryName);
		item.setTranslationKey(item.getBlock().getTranslationKey());
		items.add(item);
		return item;
	}
	
	@SideOnly(CLIENT)
	@Mod.EventBusSubscriber(modid = BrickMod.MODID, value = CLIENT)
	private static class Client {
		@SubscribeEvent
		static void registerModels(@SuppressWarnings("unused") ModelRegistryEvent event) {
			for (Item item : items) {
				ResourceLocation registryName = item.getRegistryName();
				assert registryName != null;
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(registryName, "inventory"));
			}
		}
	}
}
