package ca.fredericperron.untitledgame;

import ca.fredericperron.untitledgame.display.Display;

/**
 * Created by Frédéric Perron on 2019-01-10. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Main {

    public static void main(String[] args){
        //TESTING
        Display display = Display.getInstance();

        while(!display.shouldClose()){
            display.pollEvents();
            display.swapBuffers();
        }
    }
}
