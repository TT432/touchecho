package com.kilkags.touchecho.item;

import com.kilkags.touchecho.entity.EntityDirtBall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 */
public class ModItemList {
    public static final Map<Item, Object[]> ITEM_LIST_MAP = new HashMap<>();

    public static final ItemCommon DIRT_BALL = new ItemCommon("dirt_ball", 16){
        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
            ItemStack heldItem = playerIn.getHeldItem(handIn);

            if(!playerIn.capabilities.isCreativeMode) {
                heldItem.shrink(1);
            }

            if(!worldIn.isRemote) {
                EntityDirtBall entityDirtBall = new EntityDirtBall(worldIn, playerIn);
                float pitch = playerIn.rotationPitch;
                float yaw = playerIn.rotationYaw;
                entityDirtBall.shoot(playerIn, pitch, yaw, 0f, 1.5f, 1f);
                worldIn.spawnEntity(entityDirtBall);
            }

            return ActionResult.newResult(EnumActionResult.SUCCESS, heldItem);
        }
    };
    public static final ItemToolCommon DIRT_TOOL = new ItemToolCommon("dirt_tool", 1, 44, 3.0F, 1.0F, 5);
    public static final ItemArmorCommon DIRT_ARMOR = new ItemArmorCommon("dirt_armor",5, new int[]{1, 2, 2, 1}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
}
