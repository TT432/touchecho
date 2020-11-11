package com.kilkags.touchecho.interfaces;

/**
 * @author DustW
 */
public interface IHasJson {
    /**
     * default:
     *  AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.TOOL);//工具
     *  AutoJson.itemJsonCreator(this.getRegistryName().getPath(), Types.JsonTypes.ITEM);//物品
     */
    void whitJson();
}
