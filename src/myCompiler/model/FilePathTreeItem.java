package myCompiler.model;

        import java.io.File;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;

        import javafx.event.Event;
        import javafx.event.EventHandler;
        import javafx.scene.control.ContextMenu;
        import javafx.scene.control.MenuItem;
        import javafx.scene.control.TreeItem;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import myCompiler.tools.FileIconHelper;

        import javax.imageio.ImageIO;

public class FilePathTreeItem extends TreeItem<String> {

    // this stores the full path to the file or directory
    private Path fullPath;

    public Path getFullPath() {
        return (this.fullPath);
    }

    public boolean isDirectory() {
        return Files.isDirectory(fullPath);
    }

    public FilePathTreeItem(Path filePath) {
        super();
        this.fullPath = filePath;

        String fileName = filePath.getFileName().toString();
        setValue(fileName);
        String imageLocation;
        if (Files.isDirectory(fullPath)) {
             imageLocation= FileIconHelper.getFolderImageLocation(filePath);
        } else {
             imageLocation= FileIconHelper.getImageLocation(filePath);
        }
        try {
            setGraphic(new ImageView(new Image(getClass().getResource(imageLocation).openStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void delete() {
        System.out.println("delete");
    }
}
