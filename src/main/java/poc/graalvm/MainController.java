package poc.graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.nio.file.FileSystems;
import java.util.HashMap;

@RestController
public class MainController {

    @GetMapping()
    public String index(){

        // please see https://github.com/oracle/graal/issues/1348#issuecomment-534541812
        try {
            URL res = com.oracle.js.parser.ScriptEnvironment.class.getClassLoader().getResource("/META-INF/truffle/language");
            // initialize the file system for the language file
            if (res != null) {
                FileSystems.newFileSystem(res.toURI(), new HashMap<>());
            }
        } catch (Throwable ignored) {
            // in case of starting without fat jar
        }

        Context context = Context.create();
        Value testing = context.eval("js", "(function () {return 'Executed by GraalJS'})()");

        return testing.toString();
    }
}
