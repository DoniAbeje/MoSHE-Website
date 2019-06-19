package com.doniabeje.moshewebsite.controllers;

import com.doniabeje.moshewebsite.configuration.Utils;
import com.doniabeje.moshewebsite.domains.*;
import com.doniabeje.moshewebsite.repositories.GeneralInformationRepository;
import com.doniabeje.moshewebsite.repositories.LinkRepository;
import com.doniabeje.moshewebsite.repositories.SuggestionRepository;
import com.doniabeje.moshewebsite.services.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
public class VisitorController {


    NewsService newsService;

    private VacancyService vacancyService;

    private TenderService tenderService;

    private DocumentService documentService;

    private ServiceService serviceService;

    private DocumentTypeService documentTypeService;

    private PersonService personService;
    private GeneralInformationRepository generalInformationRepository;
    private SuggestionRepository suggestionRepository;
    private LinkRepository linkRepository;
    private JobService jobService;

    private static final String DEFAULT_PAGE_SIZE = "10";

    @Autowired
    public VisitorController(NewsService newsService, VacancyService vacancyService,
                             TenderService tenderService, DocumentService documentService,
                             ServiceService serviceService, DocumentTypeService documentTypeService, PersonService personService,
                             GeneralInformationRepository generalInformationRepository, SuggestionRepository suggestionRepository,
                             JobService jobService, LinkRepository linkRepository) {
        this.newsService = newsService;
        this.vacancyService = vacancyService;
        this.tenderService = tenderService;
        this.documentService = documentService;
        this.serviceService = serviceService;
        this.documentTypeService  = documentTypeService;
        this.personService = personService;
        this.generalInformationRepository = generalInformationRepository;
        this.suggestionRepository = suggestionRepository;
        this.linkRepository = linkRepository;
        this.jobService = jobService;
    }

    @ModelAttribute("Utils")
    public Utils utils(){
        Utils utils = new Utils();
        return utils;
    }

    @ModelAttribute("GeneralInformation")
    public GeneralInforamtion generalInforamtion(Model model){
        return generalInformationRepository.findById(1L).get();
    }

    @ModelAttribute("NavJobs")
    public Page<Job>  NavJobs(Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(0, 100, "title", 1);

        if (locale.getLanguage() .equals("am")) {
            return  jobService.findAllByLanguage(News.Language.AMHARIC, pageRequest);
        }
        else  {
            return jobService.findAllByLanguage(News.Language.ENGLISH, pageRequest);
        }
    }

    @ModelAttribute("NavServices")
    public Page<Service>  navServices(Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(0, 4, "title", 1);

        if (locale.getLanguage() .equals("am")) {
            return  serviceService.findAllByLanguage(News.Language.AMHARIC, pageRequest);
        }
        else  {
            return serviceService.findAllByLanguage(News.Language.ENGLISH, pageRequest);
        }
    }


    @ModelAttribute("NavDocumentTypes")
    public Page<DocumentType>  NavDocumentTypes(Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(0, 100, "title", 1);

        if (locale.getLanguage() .equals("am")) {
            return  documentTypeService.findAllByLanguage(News.Language.AMHARIC, pageRequest);
        }
        else  {
            return documentTypeService.findAllByLanguage(News.Language.ENGLISH, pageRequest);
        }
    }



    @GetMapping("/")
    public String index(Model model, Locale locale){
        PageRequest pageRequest = PageRequest.of(0,4, Sort.by("dateTime").descending());
        PageRequest servicePageRequest = AdminController.getPageRequest(0, 1000, "title", 1);
        Page pages;

        if (locale.getLanguage() .equals("am")){
            pages = newsService.findAllByLanguage(News.Language.AMHARIC, pageRequest);
            model.addAttribute("Services",serviceService.findAllByLanguage(News.Language.AMHARIC, servicePageRequest));
            model.addAttribute("Persons",personService.findAllByLanguage(News.Language.AMHARIC, pageRequest));

        }

        else {
            pages = newsService.findAllByLanguage(News.Language.ENGLISH, pageRequest);
            model.addAttribute("Services",serviceService.findAllByLanguage(News.Language.ENGLISH, servicePageRequest));
            model.addAttribute("Persons",personService.findAllByLanguage(News.Language.ENGLISH, pageRequest));

        }

        model.addAttribute("GeneralInformation", generalInformationRepository.findById(1L).get());
        model.addAttribute("News", pages.getContent());
        return "index";
    }

    @GetMapping("viewNews/{id}")
    public String viewNews(@PathVariable("id") Long id, Model model, Locale locale){
        Optional<News> optionalNews = newsService.findById(id);

        if (optionalNews.isPresent()){
            model.addAttribute("News", optionalNews.get());
        }

        PageRequest pageRequest = PageRequest.of(0,6, Sort.by("dateTime").descending());
        Page pages;

        if (locale.getLanguage() .equals("am")){
            pages = newsService.findAllByLanguage(News.Language.AMHARIC, pageRequest);

        }
        else {
            pages = newsService.findAllByLanguage(News.Language.ENGLISH, pageRequest);
        }

        model.addAttribute("recentNews", pages.getContent());
        return "visitor/viewNews";
    }


    @GetMapping("/visitor/tenders")
    public String tenders(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "dateTime") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(page, size, sort, ascending);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Page pages ;
        if (locale.getLanguage() .equals("am")){
            pages = tenderService.findAllByDeadlineAfterAndLanguage(new Date(), News.Language.AMHARIC, pageRequest);

        }
        else {
            pages = tenderService.findAllByDeadlineAfterAndLanguage(new Date(), News.Language.ENGLISH, pageRequest);
        }
        AdminController.preparePageModel(model, page, size, sort, pages, ascending, "Tenders");

        return "/visitor/tenders";
    }

    @GetMapping("/visitor/tenders/archives")
    public String tendersArchives(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "dateTime") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(page, size, sort, ascending);
        Page pages ;

        if (locale.getLanguage() .equals("am")){
            pages = tenderService.findAllByDeadlineBeforeAndLanguage(new Date(), News.Language.AMHARIC, pageRequest);

        }
        else {
            pages = tenderService.findAllByDeadlineBeforeAndLanguage(new Date(), News.Language.ENGLISH, pageRequest);
        }
        model.addAttribute("isArchives", true);
        AdminController.preparePageModel(model, page, size, sort, pages, ascending, "Tenders");

        return "/visitor/tenders";
    }
    @GetMapping("/visitor/searchTenders")
    public String searchTenders(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("Keyword", keyword);
        model.addAttribute("Tenders", tenderService.findByTitleContains(keyword));

        return "/visitor/tenders";
    }

    @GetMapping("/visitor/viewTender/{id}")
    public String viewTender(@PathVariable("id") Long id, Model model, Locale locale){
        Optional<Tender> optionalTender = tenderService.findById(id);

        if (optionalTender.isPresent()) {
            model.addAttribute("Tender", optionalTender.get());
        }

        PageRequest pageRequest = PageRequest.of(0,6,Sort.by("deadline").descending());
        Page pages;
        if (locale.getLanguage() .equals("am")){
            pages = tenderService.findAllByDeadlineAfterAndLanguage(new Date(), News.Language.AMHARIC, pageRequest);

        }
        else {
            pages = tenderService.findAllByDeadlineAfterAndLanguage(new Date(), News.Language.ENGLISH, pageRequest);
        }
        model.addAttribute("Tenders", pages.getContent());
        return "visitor/viewTender";
    }


    @GetMapping("/visitor/documents/{documentTypeID}")
    public String documentsByType(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10000") int size, @RequestParam(name = "sort", defaultValue = "dateTime") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending, @PathVariable("documentTypeID") long documentTypeID, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(page, size, sort, ascending);
        Page pages;

        Optional<DocumentType> optionalDocumentType = documentTypeService.findById(documentTypeID);

        if (optionalDocumentType.isPresent()){

            model.addAttribute("DocumentType", optionalDocumentType.get());
            if (locale.getLanguage() .equals("am")) {
                pages = documentService.findAllByDocumentTypeAndLanguage(optionalDocumentType.get(), News.Language.AMHARIC, pageRequest);
            }
            else  {
                pages = documentService.findAllByDocumentTypeAndLanguage(optionalDocumentType.get(), News.Language.ENGLISH, pageRequest);
            }

            model.addAttribute("DocumentType", optionalDocumentType.get());
            AdminController.preparePageModel(model, page, size, sort, pages, ascending, "Documents");

        }





        return "/visitor/documents";
    }

    @GetMapping("/visitor/documents")
    public String documents(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "dateTime") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(page, size, sort, ascending);
        Page pages;

        if (locale.getLanguage() .equals("am")) {
            pages = documentService.findAllByLanguage(News.Language.AMHARIC, pageRequest);
        }
        else  {
            pages = documentService.findAllByLanguage(News.Language.ENGLISH, pageRequest);
        }

        AdminController.preparePageModel(model, page, size, sort, pages, ascending, "Documents");

        return "/visitor/documents";
    }

    @GetMapping("/visitor/searchDocuments")
    public String searchDocuments(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("Keyword", keyword);
        model.addAttribute("Documents", documentService.findByTitleContains(keyword));

        return "/visitor/documents";
    }

    @GetMapping("/visitor/vacancies")
    public String vacancies(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "deadline") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(page, size, sort, ascending);
        Page pages ;


        if (locale.getLanguage() .equals("am")){
            pages = vacancyService.findAllByDeadlineAfterAndLanguage(new Date(), News.Language.AMHARIC, pageRequest);

        }
        else {
            pages = vacancyService.findAllByDeadlineAfterAndLanguage(new Date(), News.Language.ENGLISH, pageRequest);
        }

        AdminController.preparePageModel(model, page, size, sort, pages, ascending, "Vacancies");

        return "/visitor/vacancies";
    }

    @GetMapping("/visitor/vacancies/archives")
    public String vacanciesArchives(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "dateTime") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(page, size, sort, ascending);
        Page pages ;


        if (locale.getLanguage() .equals("am")){
            pages = vacancyService.findAllByDeadlineBeforeAndLanguage(new Date(), News.Language.AMHARIC, pageRequest);

        }
        else {
            pages = vacancyService.findAllByDeadlineBeforeAndLanguage(new Date(), News.Language.ENGLISH, pageRequest);
        }
        model.addAttribute("isArchives", true);
        AdminController.preparePageModel(model, page, size, sort, pages, ascending, "Vacancies");

        return "/visitor/vacancies";
    }

    @GetMapping("/visitor/searchVacancies")
    public String searchVacancies(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("Keyword", keyword);
        model.addAttribute("Vacancies", vacancyService.findByTitleContains(keyword));

        return "/visitor/vacancies";
    }

    @GetMapping("/visitor/viewVacancy/{id}")
    public String viewVacancy(@PathVariable("id") Long id, Model model, Locale locale){
        Optional<Vacancy> optionalVacancy = vacancyService.findById(id);

        if (optionalVacancy.isPresent()) {
            model.addAttribute("Vacancy", optionalVacancy.get());
        }

        PageRequest pageRequest = PageRequest.of(0,6,Sort.by("deadline").descending());
        Page pages ;

        if (locale.getLanguage() .equals("am")){
            pages = vacancyService.findAllByDeadlineAfterAndLanguage(new Date(), News.Language.AMHARIC, pageRequest);

        }
        else {
            pages = vacancyService.findAllByDeadlineAfterAndLanguage(new Date(), News.Language.ENGLISH, pageRequest);
        }
        model.addAttribute("Vacancies", pages.getContent());
        return "visitor/viewVacancy";
    }

    @GetMapping("/visitor/newsList")
    public String newsList(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "dateTime") String sort, @RequestParam(name = "ascending", defaultValue = "0") int ascending, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(page, size, sort, ascending);
        Page pages ;

        if (locale.getLanguage() .equals("am")){
            pages =  newsService.findAllByLanguage(News.Language.AMHARIC, pageRequest);
        }

        else {
            pages =  newsService.findAllByLanguage(News.Language.ENGLISH, pageRequest);
        }

        AdminController.preparePageModel(model, page, size, sort, pages, ascending, "NewsList");

        return "/visitor/newsList";
    }

    @GetMapping("/visitor/aboutUs")
    public String aboutUs(Model model, Locale locale){

        GeneralInforamtion generalInforamtion =generalInformationRepository.findById(1L).get();



        if (locale.getLanguage() .equals("am")){
            model.addAttribute("GeneralInformation", generalInforamtion.getAmharicDescription());
        }

        else {
            model.addAttribute("GeneralInformation", generalInforamtion.getEnglishDescription());
        }
            return "/visitor/aboutUs";
    }

    @GetMapping("/visitor/services")
    public String services(Model model, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(0, 1000, "title", 1);

        if (locale.getLanguage() .equals("am")) {
            model.addAttribute("Services",serviceService.findAllByLanguage(News.Language.AMHARIC, pageRequest));
        }
        else  {
            model.addAttribute("Services",serviceService.findAllByLanguage(News.Language.ENGLISH, pageRequest));
        }

        return "/visitor/services";
    }

    @GetMapping("/visitor/links")
    public String links(Model model, Locale locale){

        model.addAttribute("Links",linkRepository.findAll());


        return "/visitor/links";
    }

    @GetMapping("visitor/person/{id}")
    public String person(@PathVariable("id") long id, Model model, Locale locale){
        getPersonsList(model, locale);
        model.addAttribute("Person", personService.findById(id).get());
        return "/visitor/person";
    }

    private void getPersonsList(Model model, Locale locale) {

        PageRequest pageRequest = AdminController.getPageRequest(0, 1000, "dateTime", 1);
        if (locale.getLanguage() .equals("am")) {
            model.addAttribute("Persons",personService.findAllByLanguage(News.Language.AMHARIC, pageRequest));
        }
        else{
            model.addAttribute("Persons",personService.findAllByLanguage(News.Language.ENGLISH, pageRequest));
        }

    }

    @PostMapping("visitor/addSuggestion")
    public String addSuggestion(@RequestParam("email") String email, @RequestParam("content") String content, @RequestParam("phone") String phone, @RequestParam("name") String name){
        Suggestion suggestion = new Suggestion();
        suggestion.setContent(content);
        suggestion.setEmail(email);
        suggestion.setPhone(phone);
        suggestion.setName(name);

        suggestionRepository.save(suggestion);

        return "redirect:/visitor/aboutUs?success=true";
    }
    @GetMapping("/visitor/news/archives")
    public String archives(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "dateTime") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(page, size, sort, ascending);
        Page pages ;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        Date before2Months = new Date(calendar.getTimeInMillis());
        model.addAttribute("isArchives",true);
        if (locale.getLanguage() .equals("am")){
            pages =  newsService.findAllByLanguageAndDateTimeBefore(News.Language.AMHARIC,before2Months,  pageRequest);
        }

        else {
            pages =  newsService.findAllByLanguageAndDateTimeBefore(News.Language.ENGLISH,before2Months, pageRequest);
        }

        AdminController.preparePageModel(model, page, size, sort, pages, ascending, "NewsList");

        return "/visitor/newsListArchives";
    }

    @GetMapping("/visitor/structure")
    public String   structure(){

        return "/visitor/structure";
    }
    @GetMapping("/visitor/adminByJob/{id}")
    public String   adminByJob(@PathVariable("id") Long jobId, Model model, Locale locale){
        PageRequest pageRequest = AdminController.getPageRequest(0, 100, "dateTime", 1);
        Job job = jobService.findById(jobId).get();
        List<Person> persons = personService.findAllByJob(job, pageRequest).getContent();

        if (persons.size() == 1){
            return "redirect:/visitor/person/" + persons.get(0).getID();
        }
        getPersonsList(model, locale);
        model.addAttribute("PersonsByJob", persons);

        return "/visitor/persons";
    }




}
