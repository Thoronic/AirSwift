package com.AirSwift.AirSwift.BusinessLogic;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/data")
    public String getData() {
        return "This is your data.";
    }

    @PostMapping("/data")
    public String postData(@RequestBody String requestData) {
        // Process the received data and return a response
        return "Received data: " + requestData;
    }

    private final DataService dataService;

    @Autowired
    public ApiController(DataService dataService) {
        this.dataService = dataService;
    }
}
