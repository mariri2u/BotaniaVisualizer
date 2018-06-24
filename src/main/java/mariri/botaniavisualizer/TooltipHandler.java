package mariri.botaniavisualizer;


import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.mana.IManaItem;

public class TooltipHandler {

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent e){
		Item item = e.getItemStack().getItem();
		if(item instanceof IManaItem){
			IManaItem manaitem = (IManaItem)item;
			e.getToolTip().add(String.format("%,d / %,d",
					manaitem.getMana(e.getItemStack()),
					manaitem.getMaxMana(e.getItemStack())));
		}
	}
}
