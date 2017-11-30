package dk.tg.renovation.controllers;


import dk.tg.renovation.models.entities.*;
import dk.tg.renovation.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Request;

import java.util.ArrayList;

@Controller
public class HomeController {

    //variabel med nuværende id til update
    int intId;

    ArrayList<ModelClass> mc = new ArrayList<>();

    Company company = new Company();

    @Autowired
    AdminRepository adminRepo = new AdminRepository();
    @Autowired
    CompanyRepository companyRepo = new CompanyRepository();
    @Autowired
    ContactPersonRepository cpRepo = new ContactPersonRepository();
    @Autowired
    OilRepository oilRepo = new OilRepository();
    @Autowired
    AdditionalInfoRepository addInfoRepo = new AdditionalInfoRepository();

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
        addInfoRepo.create(new AdditionalInfo(settlement, comments, cvr));
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
        addInfoRepo.create(new AdditionalInfo(settlement, comments, company.getCvr()));
        return "redirect:/seAfhentning";
    }


    // login
    @PostMapping("/login")
    public String login(@RequestParam("cName") String cName,
                        @RequestParam("password") String password){

        Admin admin = new Admin();

        //login her
        company = companyRepo.logIn(cName, password);
        admin = adminRepo.logIn(cName, password);

        if (admin != null) {
            return "redirect:/indexAdmin";
        }
        if (company != null) {
            return "redirect:/indexBruger";
        }

        return "redirect:/";
    }

    /*@GetMapping("/seAfhentning")
    public String seAfhentning(Model model) {
        model.addAttribute("company", company);
        model.addAttribute("cpPerson", cpRepo.read(company.getCvr()));
        model.addAttribute("oil", oilRepo.read(company.getCvr()));
        model.addAttribute("addInfo", addInfoRepo.read(company.getCvr()));

        return "seAfhentning";
    }*/

    @GetMapping("/seAfhentning")
    public String seAfhentning(Model model) {

        mc.clear();

        //første arrayliste
        ArrayList<ContactPerson> cp = new ArrayList<>();
        cp = cpRepo.read(company.getCvr());

        //anden arrayliste
        ArrayList<Oil> oil = new ArrayList<>();
        oil = oilRepo.read(company.getCvr());

        //tredje arrayliste
        ArrayList<AdditionalInfo> ainfo = new ArrayList<>();
        ainfo = addInfoRepo.read(company.getCvr());


        for (int i=0; i<cp.size(); i++) {

            String name = cp.get(i).getName();
            int number = cp.get(i).getNumber();
            String puAdress = cp.get(i).getPickupAdress();

            String size = oil.get(i).getSize();
            int amount = oil.get(i).getAmount();

            String settlement = ainfo.get(i).getSettlement();
            String comments = ainfo.get(i).getComments();
            int id = ainfo.get(i).getId();

            mc.add(new ModelClass(name, number, puAdress,
                    size, amount,
                    settlement, comments, id));
        }

    model.addAttribute("company", company);
    model.addAttribute("modelclass", mc);
        return "seAfhentning";
    }




    @GetMapping("/opdaterAfhentning")
    //requestparam skal udover at skulle sende videre til update.html
    //hedde det samme som parameteren Student altså studentId præcist
    public String opdaterAfhentning(@RequestParam("id") String id, Model model){
        intId = Integer.parseInt(id);
        // i get mapping ryger den her student ned og kan bruges som student
        // i htmlen.

        ModelClass theOneTrueObject = new ModelClass();

        for (int i=0; i<mc.size(); i++) {
            if (mc.equals(intId)) {
                theOneTrueObject = mc.get(i);
            }
        }

        model.addAttribute("mcobject", theOneTrueObject);
        return "opdaterAfhentning";
    }


    @PostMapping("/opdaterAfhentning")
    public String opdaterAfhentning(@RequestParam("cpname") String cpname,
                                    @RequestParam("phonenumber") int phonenumber,
                                    @RequestParam("puadress") String puadress,
                                    @RequestParam("size") String size,
                                    @RequestParam("amount") int amount,
                                    @RequestParam("settlement") String settlement,
                                    @RequestParam("comments") String comments) {
        cpRepo.update(new ContactPerson(cpname, phonenumber, puadress, company.getCvr()), intId);
        oilRepo.update(new Oil(size, amount, company.getCvr()), intId);
        addInfoRepo.update(new AdditionalInfo(settlement, comments, company.getCvr()), intId);
        return "redirect:/seAfhentning";
    }

    @GetMapping("/fjernAfhentning")
    public String fjernAfhetning(@RequestParam("id") String id, Model model) {
        int intId = Integer.parseInt(id);
        cpRepo.delete(intId);
        oilRepo.delete(intId);
        addInfoRepo.delete(intId);
        return "redirect:/seAfhentning";
    }


    @GetMapping("/indexAdmin")
    public String indexAdmin() {
        return "indexAdmin";
    }

}