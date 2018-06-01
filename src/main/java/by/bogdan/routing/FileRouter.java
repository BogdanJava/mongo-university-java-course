package by.bogdan.routing;

import spark.Spark;
import spark.resource.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

@ApplicationRoute
public class FileRouter implements AppRoute {

    @Override
    public void applyMappings() {
        Spark.get("/style/:file", (req, res) -> {
            res.header("content-type", "text/css");
            ClassPathResource resource = new ClassPathResource(String.format("/style/%s", req.params(":file")));
            return getFileContent(resource);
        });

        Spark.get("/fonts/:font", (req, res) -> {
            ClassPathResource resource
                    = new ClassPathResource(String.format("/style/fonts/%s/%s-Regular.ttf", req.params(":font"), req.params(":font")));
            FileInputStream fis = new FileInputStream(resource.getFile());
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            return bytes;
        });
    }

    private static String getFileContent(ClassPathResource resource) throws IOException {
        return new BufferedReader(new FileReader(resource.getFile())).lines().reduce((a, b) -> a + "\n" + b).get();
    }
}
