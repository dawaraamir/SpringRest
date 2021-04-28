package com.dawar.pollapi.service;

import com.dawar.pollapi.domain.Vote;
import com.dawar.pollapi.dto.OptionCount;
import com.dawar.pollapi.dto.VoteResult;
import com.dawar.pollapi.repository.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComputeResultService {

    @Autowired
    VoteRepo voteRepository;

    public ResponseEntity<?> computeResult (@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        //algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for (Vote v : allVotes) {
            totalVotes ++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
            voteResult.setTotalValues(totalVotes);
            voteResult.settotalVotes(totalVotes);
            voteResult.setResults(tempMap.values());
        }
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
