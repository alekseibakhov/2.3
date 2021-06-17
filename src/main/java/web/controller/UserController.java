package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value = "/people")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("person", service.getAll());
        return "getAll";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", service.getById(id));
        return "showById";
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        model.addAttribute("person", new User()); // new потому что мы передаем данные в тот объект, который нам нужен
                                                        //  а а в данном случае мы создаем новый объект
        return "getNew";
    }


    @PostMapping("/create") // указан URL, тк при пост запросе из (getNew.html) страницы мы должны будем передать данные
                    // в "/people/create" , т.е. в нашу страницу пост метода ( а она у нас "/people/create")
    public String creatPeople(@ModelAttribute("peopleee") User person) {
        service.save(person);
        return "redirect:/people";
    } // redirect говорит нам о том, что после метода нужно
    // будет вернуться на страницу /people


    @GetMapping("/{id}/update")
    public String updatePeople(Model model, @PathVariable("id") int id){
        model.addAttribute("person",service.getById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("people") User person, @PathVariable("id") int id){
        service.update(id, person);
        return "redirect:/people";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        service.delete(id);
        return "redirect:/people";

    }


}
