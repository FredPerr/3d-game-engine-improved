package ca.fredericperron.untitledgame.display.input;

import ca.fredericperron.untitledgame.display.Display;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public abstract class Input {

    private static List<Input> inputs = new ArrayList<>();

    private int code;
    private boolean down;

    public Input(int code){
        this.code = code;
        this.down = false;
        inputs.add(this);
    }

    public void add(){
        if(!inputs.contains(this))
            inputs.add(this);
    }

    public void remove(){
        if(inputs.contains(this))
            inputs.remove(this);
    }

    public abstract boolean isDown();

    public boolean isPressed(){
        return isDown() && !down;
    }

    public boolean isReleased(){
        return !isDown() && down;
    }

    public void setCode(int code){
        this.code = code;
    }

    void setDown(boolean down){
        this.down = down;
    }

    public int getCode(){
        return this.code;
    }

    public static void update() {
        for (Input i : inputs)
            i.setDown(i.isDown());
    }
}
