package com.kilkags.touchecho.toolkits;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AutoJson {
    public static void itemJsonCreator(String name, String types) {
        String itemJson = new StringBuffer()
                .append("{\n")
                .append("  \"parent\": \"item/").append(types).append("\",\n")
                .append("  \"textures\": {\n")
                .append("    \"layer0\": \"touchecho:items/").append(name).append("\"\n")
                .append("  }\n")
                .append("}\n").toString();

        create(name, Types.Paths.getModelsPath() + "/item", itemJson);
    }

    public static void blockCommonJsonCreator(String name) {
        String blockJson = new StringBuffer()
                .append("{\n")
                .append("  \"forge_marker\": 1,\n")
                .append("  \"default\": {\n")
                .append("    \"model\": \"minecraft:cube_all\",\n")
                .append("    \"textures\": {\"all\": \"touchecho:block/").append(name).append("\"}\n")
                .append("  },\n")
                .append("  \"variants\": {\n")
                .append("    \"normal\": [{}],\n")
                .append("    \"inventory\": [{\"transform\": \"forge:default-block\"}]\n")
                .append("  }\n")
                .append("}\n").toString();

        create(name, Types.Paths.getBlockStatesFolderPath(), blockJson);
    }

    private static void create(String name, String path, String jsonContent) {
        File folder = new File(path);
        File file = new File(path + "/" + name + ".json");
        if(folder.exists()) {
            if(!file.exists()) {
                try {
                    file.createNewFile();
                    BufferedWriter out=new BufferedWriter(new FileWriter(file));
                    out.write(jsonContent);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
