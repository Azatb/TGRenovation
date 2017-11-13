package dk.tg.renovation.controllers;


import dk.tg.renovation.models.entities.AddtionalInfo;
import dk.tg.renovation.models.entities.Company;
import dk.tg.renovation.models.entities.ContactPerson;
import dk.tg.renovation.models.entities.Oil;
import dk.tg.renovation.models.services.*;
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
    @Autowired
    ICrud<AddtionalInfo> addInfoRepo = new AdditionalInfoRepository();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/opretAfhentning")
    public String opretAfhentning(Model model) {
        model.addAttribute("company", new Company());
        return "opretAfhentning";
    }

    @PostMapping("/opretAfhentning")
    public String opretAfhentning(@RequestParam("cname") String cname,
                             @RequestParam("cvr") int cvr,
                             @RequestParam("pnumber") int pnumber,
                             @RequestParam("puadress") String puadress,
                             @RequestParam("cpname") String cpname,
                             @RequestParam("phonenumber") int phonenumber,
                             @RequestParam("size") String size,
                             @RequestParam("amount") int amount,
                             @RequestParam("settlement") int settlement,
                             @RequestParam("comments") String comments){
        companyRepo.create(new Company(cname, cvr, pnumber, puadress));
        cpRepo.create(new ContactPerson(cpname, phonenumber, cvr));
        oilRepo.create(new Oil(size, amount, cvr));
        addInfoRepo.create(new AddtionalInfo(settlement, comments, cvr));
        return "redirect:/";
    }



    @GetMapping("/indexBruger")
    public String indexBruger() {
        return "indexBruger";
    }
}