package ca.fredericperron.untitledgame.game.movement;

import org.joml.Vector3f;

/**
 * Created by KitK4t on 2019-02-10.
 */
public interface IMoveable {

    void move(Direction direction, boolean yAxisCalculation, float distance);

    void rotate(float dx, float dy, float dz);

    void blockPitch(boolean block, float min, float max);

    void setMoveable(boolean controlled);

    void setLocation(Vector3f location);

    void setRotation(Vector3f rotation);

    boolean isMoveable();

    Vector3f getPosition();

    Vector3f getRotation();
}
