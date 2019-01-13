package ca.fredericperron.untitledgame.storage;

import ca.fredericperron.untitledgame.ApplicationSettings;
import ca.fredericperron.untitledgame.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frédéric Perron on 2019-01-12. This file
 * is under copyrights© as mentioned in the README file.
 */
public class ResourceManager {

    private ResourceFolder resourceFolder;

    List<String> pathsToCopy;

    public ResourceManager(){
        resourceFolder = new ResourceFolder(ResourceUtil.getInstance().getAppDataFolderPath()+"/"+ApplicationSettings.RESOURCE_FOLDER_NAME);
        pathsToCopy = new ArrayList<>();
        pathsToCopy.add(ApplicationSettings.RESOURCE_VERTEX_SHADER);
        pathsToCopy.add(ApplicationSettings.RESOURCE_FRAGMENT_SHADER);
        exportFiles();
    }

    private void exportFiles(){
        for(String s : pathsToCopy)
            resourceFolder.importFile(s, ApplicationSettings.DEBUG);
    }

    public ResourceFolder getResourceFolder(){
        return this.resourceFolder;
    }
}
