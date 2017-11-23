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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    Company company = new Company();

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

    @GetMapping("/tilmeld")
    public String tilmeld(Model model) {
        model.addAttribute("company", new Company());
        return "tilmeld";
    }

    @PostMapping("/tilmeld")
    public String tilmeld(@RequestParam("cname") String cname,
                             @RequestParam("password") String password,
                             @RequestParam("cvr") int cvr,
                             @RequestParam("pnumber") int pnumber,
                             @RequestParam("cpname") String cpname,
                             @RequestParam("phonenumber") int phonenumber,
                             @RequestParam("puadress") String puadress,
                             @RequestParam("size") String size,
                             @RequestParam("amount") int amount,
                             @RequestParam("settlement") String settlement,
                             @RequestParam("comments") String comments){
        companyRepo.create(new Company(cname, password,  cvr, pnumber));
        cpRepo.create(new ContactPerson(cpname, phonenumber, puadress, cvr));
        oilRepo.create(new Oil(size, amount, cvr));
        addInfoRepo.create(new AddtionalInfo(settlement, comments, cvr));
        return "redirect:/";
    }



    @GetMapping("/indexBruger")
    public String indexBruger() {
        return "indexBruger";
    }


    @GetMapping("/opretAfhentning")
    public String opretAfhetning() {
        return "opretAfhentning";
    }

    @PostMapping("/opretAfhentning")
    public String opretAfhentning(@RequestParam("cpname") String cpname,
                                  @RequestParam("phonenumber") int phonenumber,
                                  @RequestParam("puadress") String puadress,
                                  @RequestParam("size") String size,
                                  @RequestParam("amount") int amount,
                                  @RequestParam("settlement") String settlement,
                                  @RequestParam("comments") String comments) {
        cpRepo.create(new ContactPerson(cpname, phonenumber, puadress, company.getCvr()));
        oilRepo.create(new Oil(size, amount, company.getCvr()));
        addInfoRepo.create(new AddtionalInfo(settlement, comments, company.getCvr()));
        return "redirect:/";
    }


    // login
    @PostMapping("/login")
    public String login(@RequestParam("cName") String cName,
                        @RequestParam("password") String password){
        //login her
        company = companyRepo.getCompany(cName, password);

        if (company == null) {
            return "redirect:/";
        }

        return "redirect:/indexBruger";
    }

    @GetMapping("/seAfhentning")
    public String seAfhentning(Model model) {
        model.addAttribute("company", company);
        model.addAttribute("cpPerson", cpRepo.read(company.getCvr()));
        model.addAttribute("oil", cpRepo.read(company.getCvr()));
        model.addAttribute("addInfo", addInfoRepo.read(company.getCvr()));

        return "seAfhentning";
    }

    @GetMapping("/opdaterAfhentning")
    public String opdaterAfhetning() {
        return "opdaterAfhentning";
    }

    @GetMapping("/fjernAfhentning")
    public String fjernAfhetning() {
        return "fjernAfhentning";
    }
}