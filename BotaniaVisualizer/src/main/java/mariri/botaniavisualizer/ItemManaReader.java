package mariri.botaniavisualizer;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.common.block.tile.mana.TilePool;

public class ItemManaReader extends Item{
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		TileEntity tile = world.getTileEntity(x, y, z);
		boolean result = false;
		
		if(player instanceof EntityPlayerMP){
			EntityPlayerMP playermp = (EntityPlayerMP)player;
			if(tile instanceof IManaBlock){
				IManaBlock manablock = (IManaBlock)tile;
				StringBuilder msg = new StringBuilder();
				String ln = System.getProperty("line.separator");
				playermp.addChatMessage(new ChatComponentText("Scanning to " + block.getUnlocalizedName()));
				int cur = manablock.getCurrentMana();
				playermp.addChatMessage(new ChatComponentText(String.format(" - Current Mana is %,d", cur)));
				int max = 0;
				if(tile instanceof TilePool){
					max = ((TilePool)tile).MAX_MANA;
				}
				if(max > 0){
					playermp.addChatMessage(new ChatComponentText(String.format(" - Maximum Mana is %,d", max)));
				}
				result = true;
			}
		}
		return result;
	}
}
