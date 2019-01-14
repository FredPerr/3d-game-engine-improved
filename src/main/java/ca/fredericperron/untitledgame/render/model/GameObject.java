package ca.fredericperron.untitledgame.render.model;

import org.joml.Vector3f;

/**
 * Created by Frédéric Perron on 2019-01-13. This file
 * is under copyrights© as mentioned in the README file.
 */
public class GameObject {

    private final Mesh mesh;
    private final Vector3f position;
    private float scale;
    private final Vector3f rotation;

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Vector3f(0, 0, 0);
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public float getScale() {
        return scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Mesh getMesh() {
        return mesh;
    }
}