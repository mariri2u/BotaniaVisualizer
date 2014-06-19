package mariri.botaniavisualizer;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import vazkii.botania.common.lib.LibOreDict;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = BotaniaVisualizer.MODID, version = BotaniaVisualizer.VERSION, dependencies = BotaniaVisualizer.DEPENDENCIES )
public class BotaniaVisualizer {
    public static final String MODID = "BotaniaVisualizer";
    public static final String VERSION = "1.7.2-1.0";
    public static final String DEPENDENCIES = "required-after:Botania";
    private Item itemManaReader; 
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        itemManaReader = new ItemManaReader();
        itemManaReader.setCreativeTab(CreativeTabs.tabTools);
        itemManaReader.setUnlocalizedName("manaReader");
        itemManaReader.setTextureName("mariri:manaReader");
        itemManaReader.setMaxStackSize(1);
        GameRegistry.registerItem(itemManaReader, "manaReader");
        
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(
        		new ItemStack(itemManaReader, 1),
        		"SDS", "SWS", " W ",
        		'S', LibOreDict.MANA_STEEL,
        		'D', LibOreDict.MANA_DIAMOND,
        		'W', LibOreDict.LIVINGWOOD_TWIG));

        MinecraftForge.EVENT_BUS.register(new TooltipHandler());
    }
}
