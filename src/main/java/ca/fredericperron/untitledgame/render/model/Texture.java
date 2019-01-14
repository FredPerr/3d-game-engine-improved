package ca.fredericperron.untitledgame.render.model;

import ca.fredericperron.untitledgame.storage.Image;

import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

/**
 * Created by Frédéric Perron on 2019-01-13. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Texture {
    private final int id;

    public Texture(Image image) throws Exception {
        this(loadTexture(image));
    }

    public Texture(int id) {
        this.id = id;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getId() {
        return id;
    }

    private static int loadTexture(Image image) throws Exception {
        // Create a new OpenGL texture
        int textureId = glGenTextures();
        // Bind the texture
        glBindTexture(GL_TEXTURE_2D, textureId);
        // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        // Upload the texture data
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0,
                GL_RGBA, GL_UNSIGNED_BYTE, image.getData());
        // Generate Mip Map
        glGenerateMipmap(GL_TEXTURE_2D);
        return textureId;
    }

    public void cleanUp() {
        glDeleteTextures(id);
    }
}