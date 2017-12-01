package dk.tg.renovation.controllers;


import dk.tg.renovation.models.entities.*;
import dk.tg.renovation.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    //variabel med nuværende id til update
    int intId;

    ArrayList<ModelClass> mc = new ArrayList<>();
    List<Driver> drivers = new ArrayList<>();

    Company company = new Company();

    //Repositories

    @Autowired
    AdminRepository adminRepo = new AdminRepository();
    @Autowired
    DriverRepository driverRepo = new DriverRepository();
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
                             @RequestParam("comments") String comments,
                             @RequestParam("weekday") String weekday,
                             @RequestParam("region") String region){
        companyRepo.create(new Company(cname, password,  cvr, pnumber));
        cpRepo.create(new ContactPerson(cpname, phonenumber, puadress, cvr));
        oilRepo.create(new Oil(size, amount, cvr));
        addInfoRepo.create(new AdditionalInfo(settlement, comments, weekday, region, cvr));
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
                                  @RequestParam("comments") String comments,
                                  @RequestParam("weekday") String weekday,
                                  @RequestParam("region") String region) {
        cpRepo.create(new ContactPerson(cpname, phonenumber, puadress, company.getCvr()));
        oilRepo.create(new Oil(size, amount, company.getCvr()));
        addInfoRepo.create(new AdditionalInfo(settlement, comments, weekday, region, company.getCvr()));
        return "redirect:/seAfhentning";
    }


    // login
    @PostMapping("/login")
    public String login(@RequestParam("cName") String cName,
                        @RequestParam("password") String password){

        Admin admin = new Admin();
        Driver driver = new Driver();

        //login her
        company = companyRepo.logIn(cName, password);
        driver = driverRepo.logIn(cName, password);
        admin = adminRepo.logIn(cName, password);


        if (company != null) {
            return "redirect:/indexBruger";
        }

        if (driver != null) {
            return "redirect:/indexDriver";
        }

        if (admin != null) {
            return "redirect:/indexAdmin";
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
            String weekDay = ainfo.get(i).getWeekDay();
            String region = ainfo.get(i).getRegion();
            int id = ainfo.get(i).getId();

            mc.add(new ModelClass(name, number, puAdress,
                    size, amount,
                    settlement, comments, weekDay, region, id));
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
                                    @RequestParam("comments") String comments,
                                    @RequestParam("weekday") String weekday,
                                    @RequestParam("region") String region) {
        cpRepo.update(new ContactPerson(cpname, phonenumber, puadress, company.getCvr()), intId);
        oilRepo.update(new Oil(size, amount, company.getCvr()), intId);
        addInfoRepo.update(new AdditionalInfo(settlement, comments, weekday, region, company.getCvr()), intId);
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


    //herfra er der admin mappings




    @GetMapping("/indexAdmin")
    public String indexAdmin() {
        return "indexAdmin";
    }


    @GetMapping("/seFirmaer")
    public String seFirmaer(Model model) {
        List<Company> companies = new ArrayList<>();
        companies = adminRepo.readAll();
        model.addAttribute("company", companies);

        return "seFirmaer";
    }

    @GetMapping("/fjernFirma")
    public String fjernFirma(@RequestParam("cvr") String cvr, Model model) {
        int intcvr = Integer.parseInt(cvr);
        adminRepo.deleteCompany(intcvr);
        return "redirect:/seFirmaer";
    }

    @GetMapping("/opretDriver")
    public String opretDriver() {

        return "opretDriver";
    }

    @PostMapping("/opretDriver")
    public String opretDriver(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("region") String region){
        adminRepo.create(new Driver(username, password, region));
        return "redirect:/seDrivers";
    }

    @GetMapping("/seDrivers")
    public String seDrivers(Model model) {
        //smider en ligegyldig int med
        int id = 123;
        drivers = adminRepo.read(id);
        model.addAttribute("drivers", drivers);
        return "seDrivers";
    }

    @GetMapping("/fjernDriver")
    public String fjernDriver(@RequestParam("driverId") String id){
        int driverID = Integer.parseInt(id);
        adminRepo.delete(driverID);
        return "redirect:/seDrivers";

    }

    @GetMapping("/opdaterDriver")
    public String opdaterDriver(@RequestParam("driverId") String id, Model model){
        intId = Integer.parseInt(id);

        Driver driver = new Driver();

        for (int i=0; i<drivers.size(); i++) {
            if (drivers.equals(intId)) {
                driver = drivers.get(i);
            }
        }
        model.addAttribute("driverObject", driver);
        return "/opdaterDriver";
    }


    @PostMapping("/opdaterDriver")
    public String opdaterDriver(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("region") String region){
        adminRepo.update(new Driver(username, password, region), intId);
        return "redirect:/seDrivers";
    }





    //herfra er chauffør mappings

    @GetMapping("/indexDriver")
    public String indexDriver() {
        return "indexDriver";
    }

    @GetMapping("/seKøreplan")
    public String seKøreplan(@RequestParam("driver") Model model) {


        mc.clear();

        //første arrayliste
        List<ContactPerson> cp = new ArrayList<>();
        cp = cpRepo.readAll();

        //anden arrayliste
        List<Oil> oil = new ArrayList<>();
        oil = oilRepo.readAll();

        //tredje arrayliste
        List<AdditionalInfo> ainfo = new ArrayList<>();
        ainfo = addInfoRepo.readAll();

        for (int i=0; i<cp.size(); i++) {

            String name = cp.get(i).getName();
            int number = cp.get(i).getNumber();
            String puAdress = cp.get(i).getPickupAdress();

            String size = oil.get(i).getSize();
            int amount = oil.get(i).getAmount();

            String settlement = ainfo.get(i).getSettlement();
            String comments = ainfo.get(i).getComments();
            String weekDay = ainfo.get(i).getWeekDay();
            String region = ainfo.get(i).getRegion();
            int id = ainfo.get(i).getId();

            mc.add(new ModelClass(name, number, puAdress,
                    size, amount,
                    settlement, comments, weekDay, region, id));
        }


        model.addAttribute("bestilling", driverRepo.checkRegion());
        return "seKøreplan";
    }

}