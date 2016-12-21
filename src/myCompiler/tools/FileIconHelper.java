package myCompiler.tools;

        import java.io.File;
        import java.nio.file.Path;
        import myCompiler.controller.EclipseFileTree;


public class FileIconHelper {
    private static String IMAGE_PATH= ".."+ File.separator +"resources" + File.separator +"fileIcons" + File.separator ;
    private static String DRAWABLE_PATH= File.separator +"resources" + File.separator +"drawable" ;

    public static String getImageLocation(Path fileName){
        String extension = "";
        int i = fileName.toString().lastIndexOf('.');
        if (i > 0) {
            extension = fileName.toString().substring(i + 1);
        }
        String locationString;
        switch (extension) {
            case "fxml":
                locationString=IMAGE_PATH+"fxml_file.png";
                break;
            case "png":
                locationString= IMAGE_PATH+"image.png";
                break;
            case "jpg":
                locationString= IMAGE_PATH+"image.png";
                break;
            case "jar":
                locationString= IMAGE_PATH+"jar_file.png";
                break;
            case "java":
                locationString= IMAGE_PATH+"java_file.png";
                break;
            case "css":
                locationString= IMAGE_PATH+"css.png";
                break;
            case "class":
                locationString= IMAGE_PATH+"compiled.png";
                break;
            default:
                locationString= IMAGE_PATH+"file.png";
        }
        return locationString;
    }
    public static String getFolderImageLocation(Path filePath) {
        String image="";
        String extension="";
        extension = filePath.toString().substring( EclipseFileTree.WORKSPACE_URI.length());
        if (extension.lastIndexOf("\\")==0){
            image= IMAGE_PATH+"project.png";
        }else{
            image= IMAGE_PATH+"folder.png";
        }
        return image;
    }



}
