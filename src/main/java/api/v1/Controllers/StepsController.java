package api.v1.Controllers;

import api.v1.Services.StepsService;
import api.v1.Services.StepsServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StepsController {

    private final StepsService stepsService = new StepsServiceImpl();

    @RequestMapping("/api/v1/steps/{recipe_uid}")
    public ResponseEntity<?> show(@PathVariable("recipe_uid") String uid) {

        List<JSONObject> steps = this.stepsService.getStepsByUuid(uid);
        JSONObject resBody = new JSONObject().appendField("steps", new ArrayList<>());

        resBody.put("steps",steps);

        return new ResponseEntity<>(resBody, steps.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK );
    }

}