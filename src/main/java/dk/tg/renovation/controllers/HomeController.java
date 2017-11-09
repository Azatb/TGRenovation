package dk.tg.renovation.controllers;


import dk.tg.renovation.models.entities.Company;
import dk.tg.renovation.models.entities.ContactPerson;
import dk.tg.renovation.models.entities.Oil;
import dk.tg.renovation.models.services.CompanyRepository;
import dk.tg.renovation.models.services.ContactPersonRepository;
import dk.tg.renovation.models.services.ICrud;
import dk.tg.renovation.models.services.OilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    ICrud<Company> companyRepo = new CompanyRepository();
    @Autowired
    ICrud<ContactPerson> cpRepo = new ContactPersonRepository();
    @Autowired
    ICrud<Oil> oilRepo = new OilRepository();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/afhentning")
    public String afhentning(Model model) {
        model.addAttribute("company", new Company());
        return "afhentning";
    }

    @PostMapping("/afhentning")
    public String afhentning(@RequestParam("cname") String cname,
                             @RequestParam("cvr") int cvr,
                             @RequestParam("pnumber") int pnumber,
                             @RequestParam("puadress") String puadress,
                             @RequestParam("cpname") String cpname,
                             @RequestParam("phonenumber") int phonenumber,
                             @RequestParam("size") String size,
                             @RequestParam("amount") int amount){
        companyRepo.create(new Company(cname, cvr, pnumber, puadress));
        cpRepo.create(new ContactPerson(cpname, phonenumber, cvr));
        oilRepo.create(new Oil(size, amount, cvr));
        return "redirect:/";
    }
}