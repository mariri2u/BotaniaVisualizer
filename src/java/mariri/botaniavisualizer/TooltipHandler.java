package mariri.botaniavisualizer;


import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import vazkii.botania.api.mana.IManaItem;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TooltipHandler {

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent e){
		Item item = e.itemStack.getItem();
		if(item instanceof IManaItem){
			IManaItem manaitem = (IManaItem)item;
			e.toolTip.add(String.format("%,d / %,d", 
					manaitem.getMana(e.itemStack),
					manaitem.getMaxMana(e.itemStack)));
		}
	}
}
