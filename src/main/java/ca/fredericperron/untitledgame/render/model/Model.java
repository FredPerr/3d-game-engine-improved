package ca.fredericperron.untitledgame.render.model;

/**
 * Created by Frederic on 2019-04-04.
 */
public enum Model {

    BUNNY("bunny.obj");

    private String fileName;

    Model(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }
}
