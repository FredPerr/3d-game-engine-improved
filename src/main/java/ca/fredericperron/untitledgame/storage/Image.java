package ca.fredericperron.untitledgame.storage;

import org.lwjgl.system.MemoryStack;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Image {

    private ByteBuffer data;

    private int width, height;

    public Image(String imagePath) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer comp = stack.mallocInt(1),
                    w = stack.mallocInt(1),
                    h = stack.mallocInt(1);
            this.data = org.lwjgl.stb.STBImage.stbi_load(imagePath, w, h, comp, 4);
            if (data == null)
                throw new FileNotFoundException(String.format("The file %s could not be found.", imagePath));

            this.width = w.get();
            this.height = h.get();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ByteBuffer getData() {
        return this.data;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}