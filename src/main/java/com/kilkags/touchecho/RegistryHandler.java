package com.kilkags.touchecho;

import com.kilkags.touchecho.block.ModBlockList;
import com.kilkags.touchecho.capability.DirtBallPowerProvider;
import com.kilkags.touchecho.client.entity.slimeking.RenderSlimeKing;
import com.kilkags.touchecho.commond.ModCommandCommon;
import com.kilkags.touchecho.entity.EntityDirtBall;
import com.kilkags.touchecho.entity.EntitySlimeKing;
import com.kilkags.touchecho.entity.ModEntityList;
import com.kilkags.touchecho.fluid.ModFluidList;
import com.kilkags.touchecho.interfaces.IHasBlock;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.item.ModItemList;
import com.kilkags.touchecho.tileentity.TileEntityDirtCompressor;
import com.kilkags.touchecho.toolkits.LotusSymphony;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class RegistryHandler {
    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        for (Fluid fluid : ModFluidList.FLUID_LIST) {
            FluidRegistry.addBucketForFluid(fluid);

            if(fluid instanceof IHasBlock) {
                ((IHasBlock) fluid).withBlock();
            }
        }

        event.getRegistry().registerAll(ModBlockList.BLOCK_MAP.keySet().toArray(new Block[0]));

        registerTileEntities();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onBlockRegistryClient(RegistryEvent.Register<Block> event) {
        for (Fluid fluid : ModFluidList.FLUID_LIST) {
            FluidRegistry.addBucketForFluid(fluid);

            if(fluid instanceof IHasBlock) {
                ((IHasBlock) fluid).withBlock();
            }
        }

        event.getRegistry().registerAll(ModBlockList.BLOCK_MAP.keySet().toArray(new Block[0]));

        registerTileEntities();

        for(Block block : ModBlockList.BLOCK_MAP.keySet()) {
            if(block instanceof BlockFluidBase) {
                String blockStateName = block.getRegistryName().getPath();
                registerFluidRender((BlockFluidBase) block, blockStateName);

            }

            if(block instanceof IHasJson) {
                ((IHasJson) block).whitJson();
            }
        }
    }

    @SubscribeEvent
    public static void regFluidSpirit(TextureStitchEvent.Pre event) {
        TextureMap textureMap = event.getMap();
        for(Fluid fluid : ModFluidList.FLUID_LIST) {
            textureMap.registerSprite(fluid.getFlowing());
            textureMap.registerSprite(fluid.getStill());
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItemList.ITEM_LIST_MAP.keySet().toArray(new Item[0]));

        for(Item item : ModItemList.ITEM_LIST_MAP.keySet()) {
            if(item instanceof IHasJson) {
                ((IHasJson) item).whitJson();
            }
        }
    }

    @SubscribeEvent
    public static void onEntityRegistry(RegistryEvent.Register<EntityEntry> event) {
        ModEntityList.initEntityMap();

        event.getRegistry().registerAll(ModEntityList.ENTITY_MAP.keySet().toArray(new EntityEntry[0]));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(ModelRegistryEvent event) {
        registerItemModelAll(ModItemList.ITEM_LIST_MAP.keySet().toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityPlayer) {
            DirtBallPowerProvider provider = new DirtBallPowerProvider();
            event.addCapability(new ResourceLocation(TouchEcho.MOD_ID + ":dirt_ball_power"), provider);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModelAll(Item... items) {
        for(Item item : items) {
            registerItemModel(item);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenderAll() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySlimeKing.class, RenderSlimeKing::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDirtBall.class,
                manager -> new RenderSnowball<>(manager, ModItemList.DIRT_BALL, Minecraft.getMinecraft().getRenderItem()));
    }

    public static void registerCommand(FMLServerStartingEvent event) {
        event.registerServerCommand(new ModCommandCommon());
    }

    public static void registerTileEntities() {
        TileEntity.register(TileEntityDirtCompressor.ID, TileEntityDirtCompressor.class);
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidRender(BlockFluidBase blockFluid, String blockStateName) {
        String location = TouchEcho.MOD_ID + ":" + blockStateName;
        final Item itemFluid = Item.getItemFromBlock(blockFluid);
        ModelLoader.setCustomMeshDefinition(itemFluid, (stack) -> new ModelResourceLocation(blockStateName, "fluid"));
        ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase()
        {
            @Override
            protected @NotNull ModelResourceLocation getModelResourceLocation(@NotNull IBlockState state)
            {
                return new ModelResourceLocation("touchecho:"+blockStateName, "fluid");
            }
        });
    }
}
