package org.istv.ske.messages.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdel on 06/06/2017.
 */
@RestController
@RequestMapping("/TestClient")
public class TestClient {
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public void getAllCompeByIdClient(@RequestParam("idClient") long idClient) {
        System.out.println("TEEEEEEEEST");
    }
}
