package mason.crypto.labs.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/api/v1/sample")
    public String sample() {
        return "Hello Wolrd!";
    }

}
