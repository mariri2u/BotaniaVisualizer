package mariri.botaniavisualizer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.common.block.tile.mana.TilePool;

public class ItemManaReader extends Item{

	public ItemManaReader() {
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setUnlocalizedName("mana_reader");
//        this.setTextureName("mariri:manaReader");
        this.setMaxStackSize(1);
//		GameRegistry.register(this, new ResourceLocation(BotaniaVisualizer.MODID, "mana_reader"));

//        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(
//        		new ItemStack(this, 1),
//        		"SDS", "SWS", " W ",
//        		'S', LibOreDict.MANA_STEEL,
//        		'D', LibOreDict.MANA_DIAMOND,
//        		'W', LibOreDict.LIVINGWOOD_TWIG));
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		Block block = world.getBlockState(pos).getBlock();
		TileEntity tile = world.getTileEntity(pos);
		EnumActionResult result = EnumActionResult.PASS;

		if(player instanceof EntityPlayerMP){
			EntityPlayerMP playermp = (EntityPlayerMP)player;
			if(tile instanceof IManaBlock){
				IManaBlock manablock = (IManaBlock)tile;
				StringBuilder msg = new StringBuilder();
//				msg.append(I18n.translateToLocal(block.getUnlocalizedName()));
				msg.append(block.getLocalizedName());
				int cur = manablock.getCurrentMana();
				msg.append(" - " + cur);
				int max = 0;
				if(tile instanceof TilePool){
					max = ((TilePool)tile).MAX_MANA;
				}
				if(max > 0){
					msg.append(" / " + max);
				}
				msg.append(" [mana]");
				player.sendStatusMessage(new TextComponentString(msg.toString()), true);
				result = EnumActionResult.SUCCESS;
			}
		}
		return result;
	}

    @SideOnly(Side.CLIENT)
    public void registerTextures(){
    	ResourceLocation json = new ResourceLocation("botaniavisualizer:mana_reader");
        //モデルJSONファイルのファイル名を登録。1IDで1つだけなら、登録名はGameRegistryでの登録名と同じものにする。
        //ItemStackのmetadataで種類を分けて描画させたい場合。登録名を予め登録する。
        ModelBakery.registerItemVariants(this, json);
        //1IDで複数モデルを登録するなら、上のメソッドで登録した登録名を指定する。
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0, new ModelResourceLocation(json, "inventory"));
    }

}
