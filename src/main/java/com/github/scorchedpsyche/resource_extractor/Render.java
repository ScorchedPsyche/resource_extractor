package com.github.scorchedpsyche.resource_extractor;

import com.github.scorchedpsyche.resource_extractor.interfaces.IItemsList;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static net.minecraft.client.MinecraftClient.IS_SYSTEM_MAC;
import static org.lwjgl.opengl.GL11.*;

public class Render {
    public static class RenderIcon{
        public static boolean doIconRender = false;

        private static final MinecraftClient client = MinecraftClient.getInstance();
        public static final float[] scale = new float[]         {
                1.0F,   1.5F,   2.0F,   2.5F,   3F,     3.5F,   4F,     4.5F,
                5.0F,   5.5F,   6.0F,   6.5F,   7.0F,   7.5F,   8.0F,   8.5F,
                9.0F,   9.5F,   10.0F,  10.5F,  11.0F,  11.5F,  12.0F,  12.5F,
                13.0F,  13.5F,  14.0F,  14.5F,  15.0F,  15.5F,  16.0F
            };

        public static final int[] imageDimensions = new int[]   {
                16,     24,     32,     40,     48,     56,     64,     72,
                80,     88,     96,     104,    112,    120,    128,    136,
                144,    152,    160,    168,    176,    184,    192,    200,
                208,    216,    224,    232,    240,    248,    256
            };

        public static final int[] imageOffset = new int[]       {
                0,      4,      8,      12,     16,     20,     24,     28,
                32,     36,     40,     44,     48,     52,     56,     60,
                64,     68,     72,     76,     80,     84,     88,     92,
                96,     100,    104,    108,    112,    116,    120
            };

        public static int selectedSize = 0;

        public static void doRender(){
            if(doIconRender){ RenderIcon.renderItems(); }
        }

        public static void renderItems(){
            int totalItems = ((IItemsList) Registry.ITEM).getSize();
            int itemNbr = 0;
            Item item = null;

            JSONArray jsonArray = new JSONArray();
            List<String> itemsList = new ArrayList<>();

            Iterator<Item> items = Registry.ITEM.iterator();
            while (items.hasNext()) {
                itemNbr++;
                item = items.next();
                String itemName = item.getName().getString();
                String itemNameLowerCaseWithUnderscore =
                        item.getName().getString().toLowerCase().replaceAll("\\s+", "_");


                Utils.overlayMessage(
                        itemNbr +
                                " of " + totalItems +
                                " - §b§l" +
                                item.getName().getString()
                );

                for(int i = 0; i < imageDimensions.length; i++ ){
                    renderItem(item, i);
                    saveToFile(itemNameLowerCaseWithUnderscore);
                }

                JSONObject jsonObj = new JSONObject();
                jsonObj.put("id", itemNbr);
                jsonObj.put("name", itemName);
                jsonObj.put("name_lower_case_with_underscores", itemNameLowerCaseWithUnderscore);

                jsonArray.put(jsonObj);
                itemsList.add(itemName);
            }


            File directory = new File(client.runDirectory.getAbsolutePath()+"/screenshots/");
            if( !directory.exists() )
            {
                directory.mkdirs();
            }

            File file = new File(directory + "/items_list.json"); // The file to save to.
            File file2 = new File(directory + "/items_list.txt"); // The file to save to.

            Collections.sort(itemsList);

            try {
                FileUtils.writeStringToFile(file, jsonArray.toString(), StandardCharsets.UTF_8);

                FileUtils.writeLines(file2, itemsList);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Render.RenderIcon.doIconRender = false;
        }

        public static void renderItem(Item item, int size){
            selectedSize = size;

            boolean glError = false;

            RenderSystem.clear(16640, IS_SYSTEM_MAC);
            client.getFramebuffer().beginWrite(true);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            RenderSystem.clearColor(0, 0, 0, 0);
            RenderSystem.clear(GL_COLOR_BUFFER_BIT, glError);

            RenderSystem.enableTexture();

            client.getFramebuffer().endWrite();
            RenderSystem.popMatrix();
            RenderSystem.pushMatrix();
            client.getFramebuffer().draw(client.getFramebuffer().viewportWidth, client.getFramebuffer().viewportHeight);

            client.getItemRenderer().renderGuiItemIcon(
                    new ItemStack(item),
                    0 + imageOffset[selectedSize],
                    client.getFramebuffer().viewportHeight - imageDimensions[selectedSize] + imageOffset[selectedSize]
//                    0 + imageOffset[selectedSize],
//                    client.getFramebuffer().viewportHeight - imageDimensions[selectedSize] + imageOffset[selectedSize]
            );

            GL11.glReadBuffer(GL11.GL_BACK);
        }

        public static void saveToFile(String itemName){
//            int imgSizeX = client.getFramebuffer().viewportWidth;
//            int imgSizeY = client.getFramebuffer().viewportHeight;{
            int imgSizeX = imageDimensions[selectedSize];
            int imgSizeY = imageDimensions[selectedSize];

            int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
            ByteBuffer buffer = BufferUtils.createByteBuffer(imgSizeX * imgSizeY * bpp);
            GL11.glReadPixels(0, 0, imgSizeX, imgSizeY, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

            File directory = new File(client.runDirectory.getAbsolutePath()+"/screenshots/" + imageDimensions[selectedSize] + "x" + imageDimensions[selectedSize]);
            if(!directory.exists()){
                directory.mkdirs();
            }

            File file = new File(directory + "/" + itemName + ".png"); // The file to save to.            String format = "PNG"; // Example: "PNG" or "JPG"
            BufferedImage image = new BufferedImage(imgSizeX, imgSizeY,
                                                    BufferedImage.TYPE_INT_ARGB);

            for(int x = 0; x < imgSizeX; x++)
            {
                for(int y = 0; y < imgSizeY; y++) {
                    int i = (x + (imgSizeY * y)) * bpp;

                    int r = buffer.get(i) & 0xFF;
                    int g = buffer.get(i + 1) & 0xFF;
                    int b = buffer.get(i + 2) & 0xFF;
                    int a = buffer.get(i + 3) & 0xFF;

                    Color color = new Color(r, g, b, a);
                    image.setRGB(x, imgSizeY - (y + 1), color.getRGB());
                }
            }

            try {
                ImageIO.write(image, "PNG", file);
            } catch (IOException e) { e.printStackTrace(); }
        }

        public static void addToJson(){

        }
    }
}
