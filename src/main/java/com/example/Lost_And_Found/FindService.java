package com.example.Lost_And_Found;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FindService {
    @Autowired
    private FindRepository repo;

    public List<Find> listAll(String keyword) {
        if (keyword != null) {
            return this.search(keyword);
        }
        return repo.findAll();
    }

    public List<Find> listAllSortedByDateOfFound(String keyword) {
        if (keyword != null) {
            return search(keyword);
        }
        return repo.findAll(Sort.by(Sort.Direction.ASC, "findDate"));
    }

    public void save(Find find) {
        repo.save(find);
    }

    public Find get(Long findId) {
        return repo.findById(findId).get();
    }

    public void delete(Long findId) {
        repo.deleteById(findId);
    }

    public List<Find> search(String key){
        List<Find> finds = repo.findAll();
        List<Find> result = new ArrayList<>();
        for (Find find: finds) {
            if (find.getFindDate().toString().startsWith(key) || find.getFindCategory().startsWith(key) || find.getFindSubcategory().startsWith(key) ||
                    find.getFindPlace().startsWith(key) || find.getFindDescription().startsWith(key) || find.getFindContacts().startsWith(key)){
                result.add(find);
            }
        }
        return result;
    }

    public List<String> Dates() {
        List<Find> finds = repo.findAll();
        List<String> dates = new ArrayList<>();
        for (Find find: finds) {
            String d = find.getFindDate().toString();
            int check = 0;
            for (int i=0;i<dates.size();i++) {
                if (dates.get(i).equals(d)) {
                    check = check+1;
                }
            }
            if (check==0) {
                dates.add(d);
            }

        }
        return dates;
    }

    public List<Integer> Hist() {
        List<String> dates = this.Dates();
        List<Find> finds = repo.findAll();
        List<Integer> hist = new ArrayList<>();
        for (String date : dates) {
            int check = 0;
            for (Find find : finds) {
                if (date.equals(find.getFindDate().toString())) {
                    check = check + 1;
                }
            }
            hist.add(check);

        }
        return hist;
    }

}



