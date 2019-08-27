package poc.graalvm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping()
    public String index(){
        Context context = Context.create();
        Value testing = context.eval("js", "(function () {return 'Executed by GraalJS'})()");
        return testing.toString();
    }
}
