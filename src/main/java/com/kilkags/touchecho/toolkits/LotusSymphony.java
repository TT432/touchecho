package com.kilkags.touchecho.toolkits;

import com.google.common.base.CaseFormat;
import com.kilkags.touchecho.TouchEcho;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author DustW
 */
public class LotusSymphony {
    public static boolean isNumber(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static String getLangKeyFromRegKey(String regKey) {
        return new StringBuffer().append(TouchEcho.MOD_ID).append(".").append(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, regKey)).toString();
    }

    public static EntityEntry getEntryFromEntity(Class<? extends Entity> entityClass, String name,int id, boolean isThrowable) {
        if(isThrowable) {
            return EntityEntryBuilder.create()
                    .entity(entityClass)
                    .id(name, id)
                    .name(LotusSymphony.getLangKeyFromRegKey(name))
                    .tracker(64, 10, true).build();
        }
        else {
            return EntityEntryBuilder.create()
                    .entity(entityClass)
                    .id(name, id)
                    .name(LotusSymphony.getLangKeyFromRegKey(name))
                    .tracker(80, 3, true).build();
        }
    }

    public static EntityEntry getEntryFromEntity(Class<? extends Entity> entityClass, String name,int id, int pColor, int sColor) {
        return EntityEntryBuilder.create()
                .entity(entityClass)
                .id(name, id)
                .name(LotusSymphony.getLangKeyFromRegKey(name))
                .tracker(80, 3, true)
                .egg(pColor, sColor).build();
    }

    public static <T extends Entity> boolean entityExists(T entity) {
        return entity != null && entity.isEntityAlive();
    }

    @SideOnly(Side.CLIENT)
    public static int getTexturesMovePreTick(int textureLength, int finishedTick, int tickAmount) {
        return Math.round((float) tickAmount * textureLength / finishedTick);
    }
}
