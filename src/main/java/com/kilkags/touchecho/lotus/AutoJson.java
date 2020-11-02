package com.kilkags.touchecho.lotus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AutoJson {
    public static void itemJsonCreator(String name, String types) {
        String itemJson = new StringBuffer()
                .append("{\n")
                .append("  \"forge_marker\": 1,\n")
                .append("  \"default\": {\n")
                .append("    \"model\": \"minecraft:builtin/generated\",\n")
                .append("    \"textures\": {\"layer0\": \"touchecho:items/").append(name).append("/}\n")
                .append("  },\n")
                .append("  \"variants\": {\n")
                .append("    \"inventory\": [{\"transform\": \"forge:default-").append(types).append("\"}]\n")
                .append("  }\n")
                .append("}\n").toString();

        create(name, Types.Paths.getBlockStatesFolderPath(), itemJson);
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