package com.example.Lost_And_Found;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;  // Привязываем данные к sql запросу
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;  // Распознаёт класс, как управляющий
import org.springframework.ui.Model;  // Интерфейс для взаимодействия конфигуратора и контроллера, добавление эл-ов в веб-интерфейс
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;  // html

@Controller
public class AppController {

    @Autowired
    private FindService service;

    @Autowired
    private FindRepository repo;

    @Autowired
    private LostService service1;

    @Autowired
    private LostRepository repo1;

    @Autowired
    private UserInfoService service2;

    @Autowired
    private UserInfoRepository repo2;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<String> listDates = service.Dates();
        List<Integer> listHist = service.Hist();
        model.addAttribute("dates", listDates);
        model.addAttribute("hist", listHist);
        return "index";
    }

    @GetMapping("/login")
    public String viewLogUser(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String success(Model model) {
        return "redirect:/";
    }

    @GetMapping("/add_user")
    public String viewAddUser(Model model) {
        UserInfo userInfo = new UserInfo();
        model.addAttribute("userInfo", userInfo);
        return "add_user";
    }

    @PostMapping("/add_user")
    public String addNewUser(@ModelAttribute("userInfo") UserInfo userInfo){
        service2.addUser(userInfo);
        return "redirect:/login";

    }

    @GetMapping("/finds")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public String showTables(Principal principal, @RequestParam(value = "sort_by_date_of_found", required = false, defaultValue = "false") boolean sortByDateOfFound,
                             Model model, @Param("keyword") String keyword) {
        List<Find> listFound;

        if (sortByDateOfFound) {
            listFound = service.listAllSortedByDateOfFound(keyword);
        } else {
            listFound= service.listAll(keyword);
        }

        model.addAttribute("listFound", listFound);
        model.addAttribute("keyword", keyword);
        model.addAttribute("find", new Find());

        String name = principal.getName();
        String role = service2.searchByName(name);

        if (role.equals("ROLE_USER")) {
            return "finds_user";
        } else if (role.equals("ROLE_ADMIN")) {
            return "finds_admin";
        } else {
            return "/";
        }
    }

    @RequestMapping("/losts")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public String showTables1(Principal principal, @RequestParam(value = "sort_by_date_of_lost", required = false, defaultValue = "false") boolean sortByDateOfLost,
                             Model model1, @Param("keyword1") String keyword1) {
        List<Lost> listLost;

        if (sortByDateOfLost) {
            listLost = service1.listAllSortedByDateOfLost(keyword1);
        } else {
            listLost = service1.listAll(keyword1);
        }

        model1.addAttribute("listLost", listLost);
        model1.addAttribute("keyword1", keyword1);
        model1.addAttribute("lost", new Lost());

        String name = principal.getName();
        String role = service2.searchByName(name);

        if (role.equals("ROLE_USER")) {
            return "losts_user";
        } else if (role.equals("ROLE_ADMIN")) {
            return "losts_admin";
        } else {
            return "/";
        }
    }

    @RequestMapping(value = "/save_find", method = RequestMethod.POST)
    public String saveFind(@ModelAttribute("find") Find find) {
        service.save(find);
        return "redirect:/finds";
    }


    @RequestMapping(value = "/save_lost", method = RequestMethod.POST)
    public String saveLost(@ModelAttribute("lost") Lost lost) {
        service1.save(lost);
        return "redirect:/losts";
    }

    @PostMapping("/update_find")
    public String updateFind(@RequestParam("findId") Long findId,
                             @RequestParam("findDate") String findDate,
                             @RequestParam("findCategory") String findCategory,
                             @RequestParam("findSubcategory") String findSubcategory,
                             @RequestParam("findPlace") String findPlace,
                             @RequestParam("findDescription") String findDescription,
                             @RequestParam("findContacts") String findContacts) {

        Find find = repo.findById(findId).orElse(null);
        if (find != null) {
            find.setFindDate(Date.valueOf(findDate));
            find.setFindCategory(findCategory);
            find.setFindSubcategory(findSubcategory);
            find.setFindPlace(findPlace);
            find.setFindDescription(findDescription);
            find.setFindContacts(findContacts);

            repo.save(find);
        }

        return "redirect:/finds";
    }

    @PostMapping("/update_lost")
    public String updateLost(@RequestParam("lostId") Long lostId,
                             @RequestParam("lostDate") String lostDate,
                             @RequestParam("lostCategory") String lostCategory,
                             @RequestParam("lostSubcategory") String lostSubcategory,
                             @RequestParam("lostPlace") String lostPlace,
                             @RequestParam("lostDescription") String lostDescription,
                             @RequestParam("lostContacts") String lostContacts) {

        Lost lost = repo1.findById(lostId).orElse(null);
        if (lost != null) {
            lost.setLostDate(Date.valueOf(lostDate));
            lost.setLostCategory(lostCategory);
            lost.setLostSubcategory(lostSubcategory);
            lost.setLostPlace(lostPlace);
            lost.setLostDescription(lostDescription);
            lost.setLostContacts(lostContacts);

            repo1.save(lost);
        }

        return "redirect:/losts";
    }


    @RequestMapping("/delete_find/{findId}")
    public String deleteFind(@PathVariable(name = "findId") Long findId) {
        service.delete(findId);
        return "redirect:/finds";
    }

    @RequestMapping("/delete_lost/{lostId}")
    public String deleteLost(@PathVariable(name = "lostId") Long lostId) {
        service1.delete(lostId);
        return "redirect:/losts";
    }

    @RequestMapping("/about_project")
    public String showAboutProject() {
        return "about_project";
    }
}






