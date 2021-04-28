package com.dawar.pollapi.controller;

import com.dawar.pollapi.domain.Vote;
import com.dawar.pollapi.dto.OptionCount;
import com.dawar.pollapi.dto.VoteResult;
import com.dawar.pollapi.repository.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {

    @Autowired
    private ComputeResultController computeResultController;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult (@RequestParam Long pollId) {
        return computeResultController.computeResult(pollId);
    }

}
