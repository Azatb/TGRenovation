package dk.tg.renovation.controllers;


import dk.tg.renovation.models.entities.*;
import dk.tg.renovation.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {

    //variabel med nuværende id til update
    int intId;

    ArrayList<ModelClass> mc = new ArrayList<>();
    List<Driver> drivers = new ArrayList<>();

    Company company = new Company();
    //disse bliver fyldt igennem login metoden
    Admin admin;
    Driver driver;

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
        ArrayList<ContactPerson> cp = cpRepo.read(company.getCvr());

        //anden arrayliste
        ArrayList<Oil> oil = oilRepo.read(company.getCvr());

        //tredje arrayliste
        ArrayList<AdditionalInfo> ainfo = addInfoRepo.read(company.getCvr());


        for (int i=0; i<cp.size(); i++) {

            mc.add(new ModelClass(cp.get(i).getName(), cp.get(i).getNumber(), cp.get(i).getPickupAdress(),
                    oil.get(i).getSize(), oil.get(i).getAmount(),
                    ainfo.get(i).getSettlement(), ainfo.get(i).getComments(),
                    ainfo.get(i).getWeekDay(), ainfo.get(i).getRegion(), ainfo.get(i).getId()));
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
    public String seKøreplan(Model model) {

        //til at fylde op med alle
        List<AdditionalInfo> ainfo;

        List<ContactPerson> cp;

        List<Oil> oil;

        ainfo = addInfoRepo.readAll();
        cp = cpRepo.readAll();
        oil = oilRepo.readAll();


        mc.clear();

        //vi kører alle bestillinger igennem
        //hvis ainfo (som er den tabel med region) matcher
        //med chaufførens region, så adder vi al information
        //fra netop den i til modelclass, da den passer
        for (int i=0; i<ainfo.size(); i++) {
            if (ainfo.get(i).getRegion().equalsIgnoreCase(driver.getDriverRegion())) {
                mc.add(new ModelClass(cp.get(i).getName(), cp.get(i).getNumber(), cp.get(i).getPickupAdress(),
                        oil.get(i).getSize(), oil.get(i).getAmount(),
                        ainfo.get(i).getSettlement(), ainfo.get(i).getComments(), ainfo.get(i).getWeekDay(),
                        ainfo.get(i).getRegion(), ainfo.get(i).getId()));
            }
        }

        /*
        //tjek dag
        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.GERMAN);

        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());

        //nyt arraylist der skal fyldes:
        ArrayList<ModelClass> mc2 = new ArrayList<>();

        for (int i=0; i<mc.size(); i++) {
            if (mc.get(i).getWeekDay().equalsIgnoreCase(weekDay)) {
                mc2.add(mc.get(i));
                System.out.println("added " + mc2.get(i));
            }
        }
*/

        model.addAttribute("driveplan", mc);
        return "seKøreplan";
    }

}