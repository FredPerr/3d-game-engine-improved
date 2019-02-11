package ca.fredericperron.untitledgame.display.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public abstract class Input {

    /**All the inputs of the application are stored here.*/
    private static List<Input> inputs = new ArrayList<>();

    /**GLFW code of the input.*/
    private int code;
    /**Whether the key is down or not.*/
    private boolean down;

    public Input(int code){
        this.code = code;
        this.down = false;
        inputs.add(this);
    }

    /**Add this input to the active inputs.*/
    public void add(){
        if(!inputs.contains(this))
            inputs.add(this);
    }

    /**Whether or not the input at the actual moment.*/
    public abstract boolean isDown();

    /**Remove this input from the active inputs.*/
    public void remove(){
        inputs.remove(this);
    }

    /**@return True when the key is pressed but is was
     * not the last frame.*/
    public boolean isPressed(){
        return isDown() && !down;
    }

    /**@return True if the key is released but it was not
     * the last frame.*/
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

    /**Update the state (press or release) of all the inputs.*/
    public static void update() {
        for (Input i : inputs)
            i.setDown(i.isDown());
    }
}
