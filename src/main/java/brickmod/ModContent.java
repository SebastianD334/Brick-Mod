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

import static net.minecraftforge.fml.relauncher.Side.CLIENT;

@Mod.EventBusSubscriber(modid = BrickMod.MODID)
public class ModContent {
	public static Block graniteBricks = block(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS), "granite_bricks");
	public static ItemBlock graniteBricksItem = itemBlock(new ItemBlock(graniteBricks));
	
	public static Block brownBricks = block(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS), "brown_bricks");
	public static ItemBlock brownBricksItem = itemBlock(new ItemBlock(brownBricks));
	
	public static Block lightBricks = block(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS), "light_bricks");
	public static ItemBlock lightBricksItem = itemBlock(new ItemBlock(lightBricks));
	
	public static Block grayPaintedBricks = block(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS), "gray_painted_bricks");
	public static ItemBlock grayPaintedBricksItem = itemBlock(new ItemBlock(grayPaintedBricks));
	
	public static Block magentaPaintedBricks = block(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS), "magenta_painted_bricks");
	public static ItemBlock magentaPaintedBricksItem = itemBlock(new ItemBlock(magentaPaintedBricks));
	
	public static Block streetTiles = block(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS), "street_tiles");
	public static ItemBlock streetTilesItem = itemBlock(new ItemBlock(streetTiles));
	
	public static Block andesiteTiles = block(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS), "andesite_tiles");
	public static ItemBlock andesiteTilesItem = itemBlock(new ItemBlock(andesiteTiles));
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		BrickMod.logger.info("registering blocks");
		event.getRegistry().register(graniteBricks);
		event.getRegistry().register(brownBricks);
		event.getRegistry().register(lightBricks);
		event.getRegistry().register(grayPaintedBricks);
		event.getRegistry().register(magentaPaintedBricks);
		event.getRegistry().register(streetTiles);
		event.getRegistry().register(andesiteTiles);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(graniteBricksItem);
		event.getRegistry().register(brownBricksItem);
		event.getRegistry().register(lightBricksItem);
		event.getRegistry().register(grayPaintedBricksItem);
		event.getRegistry().register(magentaPaintedBricksItem);
		event.getRegistry().register(streetTilesItem);
		event.getRegistry().register(andesiteTilesItem);
	}
	
	
	public static <B extends Block> B block(B block, String name) {
		block.setRegistryName(new ResourceLocation(BrickMod.MODID, name));
		block.setUnlocalizedName(BrickMod.MODID + "." + name);
		return block;
	}
	
	private static <IB extends ItemBlock> IB itemBlock(IB item) {
		ResourceLocation registryName = item.getBlock().getRegistryName();
		assert registryName != null;
		item.setRegistryName(registryName);
		item.setUnlocalizedName(item.getBlock().getUnlocalizedName());
		return item;
	}
	
	@SideOnly(CLIENT)
	@Mod.EventBusSubscriber(modid = BrickMod.MODID, value = CLIENT)
	private static class Client {
		@SubscribeEvent
		static void registerModels(@SuppressWarnings("unused") ModelRegistryEvent event) {
			Item[] items = { graniteBricksItem, brownBricksItem, lightBricksItem, grayPaintedBricksItem, magentaPaintedBricksItem, streetTilesItem, andesiteTilesItem };
			for (Item item : items) {
				ResourceLocation registryName = item.getRegistryName();
				assert registryName != null;
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(registryName, "inventory"));
			}
		}
	}
}
