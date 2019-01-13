package ca.fredericperron.untitledgame.render.model;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

/**
 * Created by Frédéric Perron on 2019-01-13. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Mesh {

    private FloatBuffer verticesBuffer;
    private int vaoId, vboId;

    public Mesh(float[] vertices){
        verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
        verticesBuffer.put(vertices).flip();
        vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        vboId = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboId);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, verticesBuffer, GL30.GL_STATIC_DRAW);
        MemoryUtil.memFree(verticesBuffer);
        GL30.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        if (verticesBuffer != null)
            MemoryUtil.memFree(verticesBuffer);
    }

    public int getVaoId(){
        return this.vaoId;
    }

    public int getVboId(){
        return this.vboId;
    }
}
