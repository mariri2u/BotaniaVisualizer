package mariri.botaniavisualizer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BotaniaVisualizer.MODID, version = BotaniaVisualizer.VERSION, dependencies = BotaniaVisualizer.DEPENDENCIES )
public class BotaniaVisualizer {
    public static final String MODID = "BotaniaVisualizer";
    public static final String VERSION = "1.10.2-1.0";
    public static final String DEPENDENCIES = "required-after:Botania";
    private ItemManaReader itemManaReader;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        itemManaReader = new ItemManaReader();

        MinecraftForge.EVENT_BUS.register(new TooltipHandler());

        if (event.getSide().isClient()) {
        	itemManaReader.registerTextures();
        }
    }
}
