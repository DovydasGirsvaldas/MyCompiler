package myCompiler.model;

        import java.io.File;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;

        import javafx.scene.control.TreeItem;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import myCompiler.tools.FileIconHelper;

        import javax.imageio.ImageIO;

public class FilePathTreeItem extends TreeItem<String> {
    private static String IMAGE_PATH= ".."+ File.separator +"resources" + File.separator +"fileIcons" + File.separator ;


    // this stores the full path to the file or directory
    private String fullPath;

    public String getFullPath() {
        return (this.fullPath);
    }

    private boolean isDirectory;

    public boolean isDirectory() {
        return (this.isDirectory);
    }

    public FilePathTreeItem(Path filePath) {
        super();
        this.fullPath = filePath.toString();
        String fileName = filePath.getFileName().toString();
        setValue(fileName);
        String imageLocation;
        if (Files.isDirectory(filePath)) {
            this.isDirectory = true;
             imageLocation= FileIconHelper.getFolderImageLocation(filePath);
        } else {
            this.isDirectory = false;
             imageLocation= FileIconHelper.getImageLocation(filePath);
        }
        try {
            setGraphic(new ImageView(new Image(getClass().getResource(imageLocation).openStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
