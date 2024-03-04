package com.example.Lost_And_Found;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LostService {
    @Autowired
    private LostRepository repo1;

    public List<Lost> listAll(String keyword1) {
        if (keyword1 != null) {
            return this.search(keyword1);
        }
        return repo1.findAll();
    }

    public List<Lost> listAllSortedByDateOfLost(String keyword1) {
        if (keyword1 != null) {
            return search(keyword1);
        }
        return repo1.findAll(Sort.by(Sort.Direction.ASC, "lostDate"));
    }

    public void save(Lost lost) {
        repo1.save(lost);
    }

    public Lost get(Long lostId) {
        return repo1.findById(lostId).get();
    }

    public void delete(Long lostId) {
        repo1.deleteById(lostId);
    }

    public List<Lost> search(String key){
        List<Lost> losts = repo1.findAll();
        List<Lost> result = new ArrayList<>();
        for (Lost lost: losts) {
            if (lost.getLostDate().toString().startsWith(key) || lost.getLostCategory().startsWith(key) || lost.getLostSubcategory().startsWith(key) ||
                    lost.getLostPlace().startsWith(key) || lost.getLostDescription().startsWith(key) || lost.getLostContacts().startsWith(key)){
                result.add(lost);
            }
        }
        return result;
    }

    public Lost getById(long LostId) {
        return repo1.findById(LostId).orElse(null);
    }

    public void update(Lost lost) {
        Lost existingLost = repo1.findById(lost.getLostId()).orElse(null);
        if(existingLost != null) {
            existingLost.setLostDate(lost.getLostDate());
            existingLost.setLostCategory(lost.getLostCategory());
            existingLost.setLostSubcategory(lost.getLostSubcategory());
            existingLost.setLostPlace(lost.getLostPlace());
            existingLost.setLostDescription(lost.getLostDescription());
            existingLost.setLostContacts(lost.getLostContacts());
            repo1.save(existingLost);
        }
    }

}