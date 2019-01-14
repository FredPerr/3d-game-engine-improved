package ca.fredericperron.untitledgame.storage;

import ca.fredericperron.untitledgame.util.ResourceUtil;

import java.io.File;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class ResourceFolder {

    private ResourceUtil ru;
    private String path;

    public ResourceFolder(String folderPath){
        ru = ResourceUtil.getInstance();
        this.path = validPath(folderPath);
    }

    public void importFile(String relativePath, boolean replace){
        ru.writeFile(path+validPath(relativePath), ru.loadData(ru.getInputStream(validPath(relativePath))), replace);
    }

    public byte[] extractFile(String relativePath){
        return ru.loadData(path+validPath(relativePath));
    }

    public String extractFileAsString(String relativePath){
        return ru.convertByteArrayToString(extractFile(relativePath));
    }

    public String getPath(String relativeFilePath){
        return path.concat(relativeFilePath).replace(File.separator, "/");
    }

    private String validPath(String path){
        return path.replace(File.separator, "/");
    }

    public String getPath(){
        return this.path;
    }
}

