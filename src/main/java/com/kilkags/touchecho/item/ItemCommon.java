package com.kilkags.touchecho.item;

import com.kilkags.touchecho.creativetab.ModTabsList;
import com.kilkags.touchecho.interfaces.IHasJson;
import com.kilkags.touchecho.lotus.AutoJson;
import com.kilkags.touchecho.lotus.LotusSymphony;
import com.kilkags.touchecho.lotus.Types;
import net.minecraft.item.Item;

/**
 * @author DustW
 */
public class ItemCommon extends Item implements IHasJson {
    public ItemCommon(String name, int stackSize) {
        this.setRegistryName(name);
        this.setTranslationKey(LotusSymphony.getLangKeyFromRegKey(name));
        this.setMaxStackSize(stackSize);

        this.setCreativeTab(ModTabsList.ITEM_TAB);

        ModItemList.ITEM_LIST_MAP.put(this, new Object[]{});
    }

    /**
     * default:
     * new JsonItemCreator(this.getRegistryName(), Types.JsonTypes.?);
     */
    @Override
    public void whitJson() {
        AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);
    }
}
