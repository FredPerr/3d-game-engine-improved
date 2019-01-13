package ca.fredericperron.untitledgame;

import ca.fredericperron.untitledgame.display.Display;
import ca.fredericperron.untitledgame.display.Updater;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class Application implements IApplication {

    public Application(){
        instance = this;
        new Updater().start();
    }

    public void init(){
        new Display();
    }

    public void update(){
    }

    public void render(){

    }

    public void end(){

    }

    public void cleanUp(){

    }


    private static Application instance;

    public static Application getInstance(){
        return instance;
    }
}
