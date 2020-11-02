package com.kilkags.touchecho.enchantment;

import com.kilkags.touchecho.TouchEcho;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.jetbrains.annotations.NotNull;

/**
 * @author DustW
 */
public class EnchantmentExplosion extends Enchantment {
    protected EnchantmentExplosion() {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName(TouchEcho.MOD_ID + ".explosion");
        this.setRegistryName("explosion");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 21 + enchantmentLevel * 5;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 16 + enchantmentLevel * 5;
    }

    @Override
    protected boolean canApplyTogether(@NotNull Enchantment ench) {
        return super.canApplyTogether(ench) && Enchantments.SWEEPING != ench;
    }
}
