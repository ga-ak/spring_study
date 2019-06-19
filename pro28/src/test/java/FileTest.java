import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) {
        String CURP_IMAGE_REPO_PATH = "/Users/cheolho/Programing/resources";
        File file = new File(CURP_IMAGE_REPO_PATH+"/testFile.txt");
        try {
            if (!file.exists()) {
                System.out.println("file not exists");
                if (file.getParentFile().mkdirs()) {
                    file.createNewFile();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
