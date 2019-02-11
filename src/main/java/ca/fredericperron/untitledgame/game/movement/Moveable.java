package ca.fredericperron.untitledgame.game.movement;

import org.joml.Vector3f;

/**
 * Created by KitK4t on 2019-02-10.
 */
public class Moveable implements IMoveable{

    private Vector3f position, rotation;
    private float maxPitch, minPitch;
    private boolean isMoveable, blockPitch;

    protected Moveable(Vector3f position, Vector3f rotation){
        this.isMoveable = true;
        this.position = position;
        this.rotation = rotation;
    }

    @Override
    public void move(Direction direction, boolean yAxisCalculation, float distance) {
        if (direction == Direction.FORWARD)
            position.add(getForwardMove(yAxisCalculation, distance));
        if(direction == Direction.BACKWARD)
            position.add(getBackwardMove(yAxisCalculation, distance));
        if(direction == Direction.LEFTWARD)
            position.add(getLeftwardMove(distance));
        if(direction == Direction.RIGHTWARD)
            position.add(getRightwardMove(distance));
        if(direction == Direction.UPWARD)
            position.add(getUpwardMove(distance));
        if(direction == Direction.DOWNWARD)
            position.add(getDownwardMove(distance));
    }

    @Override
    public void rotate(float dx, float dy, float dz) {
        //TODO Block the pitch
        rotation.add(dx , dy, dz);
    }

    @Override
    public void blockPitch(boolean block, float min, float max) {
        blockPitch = block;
        minPitch = min;
        maxPitch = max;
    }

    @Override
    public void setMoveable(boolean moveable) {
        this.isMoveable = moveable;
    }

    @Override
    public boolean isMoveable() {
        return this.isMoveable;
    }

    @Override
    public void setLocation(Vector3f position) {
        this.position = position;
    }

    @Override
    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    @Override
    public Vector3f getPosition() {
        return position;
    }

    @Override
    public Vector3f getRotation() {
        return rotation;
    }

    private Vector3f getForwardMove(boolean yAxisCalculation, float distance){
        float dx = (float) Math.cos(Math.toRadians(rotation.y - 90)) * distance;
        float dz = (float) Math.sin(Math.toRadians(rotation.y - 90)) * distance;
        return new Vector3f(dx, yAxisCalculation ? (float) Math.cos(Math.toRadians(rotation.z)) * distance : 0, dz);
    }

    private Vector3f getBackwardMove(boolean yAxisCalculation, float distance){
        return getForwardMove(yAxisCalculation,distance).negate();
    }

    private Vector3f getLeftwardMove(float distance){
        return getRightwardMove(distance).negate();
    }

    private Vector3f getRightwardMove(float distance){
        float dx = (float) Math.cos(Math.toRadians(rotation.y)) * distance;
        float dz = (float) Math.sin(Math.toRadians(rotation.y)) * distance;
        return new Vector3f(dx, 0, dz);

    }

    private Vector3f getUpwardMove(float distance){
        return new Vector3f(0,distance,0);
    }

    private Vector3f getDownwardMove(float distance){
        return getUpwardMove(distance).negate();
    }
}
