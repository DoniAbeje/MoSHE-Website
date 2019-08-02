package com.doniabeje.moshewebsite.controllers;


import com.doniabeje.moshewebsite.domains.*;
import com.doniabeje.moshewebsite.repositories.GeneralInformationRepository;
import com.doniabeje.moshewebsite.repositories.LinkRepository;
import com.doniabeje.moshewebsite.repositories.SuggestionRepository;
import com.doniabeje.moshewebsite.services.*;
import org.attoparser.dom.DocType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {


    private UserService userService;


    private BCryptPasswordEncoder passwordEncoder;


    private RoleService roleService;

    private VacancyService vacancyService;

    private TenderService tenderService;

    private NewsImageService newsImageService;

    private NewsService newsService;

    private DocumentService documentService;
    private DocumentTypeService documentTypeService;
    private ServiceService serviceService;
    private JobService jobService;
    private PersonService personService;

    private GeneralInformationRepository generalInformationRepository;
    private SuggestionRepository suggestionRepository;
    private LinkRepository linkRepository;
    private String imagesFolder = "/opt/tomcat/files/";
    private static final String DEFAULT_PAGE_SIZE = "10";

    @Autowired
    public AdminController(UserService userService, BCryptPasswordEncoder passwordEncoder, RoleService roleService,
                           VacancyService vacancyService, TenderService tenderService, NewsImageService newsImageService,
                           NewsService newsService, DocumentService documentService, ServiceService serviceService, DocumentTypeService documentTypeService,
                           JobService jobService, PersonService personService, GeneralInformationRepository generalInformationRepository
                            ,SuggestionRepository suggestionRepository, LinkRepository linkRepository) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.vacancyService = vacancyService;
        this.tenderService = tenderService;
        this.newsImageService = newsImageService;
        this.newsService = newsService;
        this.documentService = documentService;
        this.serviceService = serviceService;
        this.documentTypeService = documentTypeService;
        this.jobService = jobService;
        this.personService = personService;
        this.generalInformationRepository = generalInformationRepository;
        this.suggestionRepository = suggestionRepository;
        this.linkRepository = linkRepository;
    }


    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("NewUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("NewUser") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        User registeredUser = userService.findUserByUsername(user.getUsername());
        if (registeredUser != null) {
            bindingResult.rejectValue("username", "error.username", "Try Another User Name");
            return "register";
        }

        Role admin = roleService.findByName("ADMIN");
        HashSet<Role> roles = new HashSet<>();
        System.out.println(null == admin);
        roles.add(admin);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("/editUser")
    public String editUser(Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("NewUser",  user);
        return "editUser";
    }

    @PostMapping("/editUser")
    public String editUserPost(@Valid @ModelAttribute("NewUser") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editUser";
        }

        User updatedUser = userService.findUserByUsername(user.getUsername());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        updatedUser.setFullName(user.getFullName());
        userService.saveUser(updatedUser);
        return "redirect:/editUser?success=true";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/addVacancy")
    public String addVacancy(Model model) {

        model.addAttribute("NewVacancy", new Vacancy());
        return "addVacancy";
    }

    @PostMapping("/addVacancy")
    public String addVacancyPost(@Valid @ModelAttribute("NewVacancy") Vacancy vacancy, BindingResult bindingResult, @RequestParam("dl") String deadline, @RequestParam("file") MultipartFile file, @RequestParam("content") String content) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldErrors().get(0).getField());
            return "addVacancy";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            vacancy.setDeadline(simpleDateFormat.parse(deadline));
        } catch (ParseException e) {
            bindingResult.rejectValue("deadline", "error.deadline", "Enter correct date (deadline)");
            return "addVacancy";
        }

        if (!file.isEmpty()) {
            String fileName = uploadFile(file);
            vacancy.setImage(fileName);
        }
        vacancy.setContent(content);
        vacancyService.save(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/vacancies")
    public String vacancies(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "title") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending) {

        PageRequest pageRequest = getPageRequest(page, size, sort, ascending);
        Page pages = vacancyService.findAll(pageRequest);

        preparePageModel(model, page, size, sort, pages, ascending, "Vacancies");
        Date date = new Date();
        model.addAttribute("Today", date);

        return "vacancies";
    }


    @GetMapping("/searchVacancies")
    public String searchVacancies(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("Keyword", keyword);
        model.addAttribute("Vacancies", vacancyService.findByTitleContains(keyword));

        return "vacancies";
    }


    @GetMapping("/editVacancy/{id}")
    public String editVacancy(@PathVariable("id") Long id, Model model) {
        Optional<Vacancy> optionalVacancy = vacancyService.findById(id);
        if (optionalVacancy.isPresent()) {
            model.addAttribute("Vacancy", optionalVacancy.get());
        }

        return "editVacancy";
    }


    @PostMapping("/editVacancy")
    public String editVacancyPost(@Valid @ModelAttribute("NewVacancy") Vacancy vacancy, BindingResult bindingResult, @RequestParam("dl") String deadline, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {

            return "editVacancy";
        }


        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            vacancy.setDeadline(simpleDateFormat.parse(deadline));

        } catch (ParseException e) {
            bindingResult.rejectValue("deadline", "error.deadline", "Enter correct date (deadline)");
            return "editVacancy";
        }
        if (!file.isEmpty()) {
            String fileName = uploadFile(file);
            vacancy.setImage(fileName);
        }

        vacancyService.save(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/addTender")
    public String addTender(Model model) {
        model.addAttribute("NewTender", new Tender());
        return "addTender";
    }
    @GetMapping("/deleteTender/{id}")
    public String deleteTender(@PathVariable("id") long id) {
        tenderService.deleteById(id);
        return "redirect:/tenders";
    }

    @PostMapping("/addTender")
    public String addTenderPost(@Valid @ModelAttribute("NewTender") Tender tender, BindingResult bindingResult, @RequestParam("dl") String deadline, @RequestParam("file") MultipartFile file, @RequestParam("content") String content) {
        if (bindingResult.hasErrors()) {
            return "addTender";
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            tender.setDeadline(simpleDateFormat.parse(deadline));
        } catch (ParseException e) {
            bindingResult.rejectValue("deadline", "error.deadline", "Enter correct date (deadline)");
            return "addTender";
        }

        tender.setContent(content);
        saveTender(tender, file);

        return "redirect:/tenders";

    }


    @GetMapping("/tenders")
    public String tenders(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "title") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending) {
        PageRequest pageRequest = getPageRequest(page, size, sort, ascending);

        Page pages = tenderService.findAll(pageRequest);

        preparePageModel(model, page, size, sort, pages, ascending, "Tenders");

        Date date = new Date();
        model.addAttribute("Today", date);
        return "tenders";
    }

    @GetMapping("/searchTenders")
    public String searchTenders(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("Keyword", keyword);
        model.addAttribute("Tenders", tenderService.findByTitleContains(keyword));

        return "tenders";
    }

    @GetMapping("/editTender/{id}")
    public String editTender(@PathVariable("id") Long id, Model model) {
        Optional<Tender> optionalTender = tenderService.findById(id);
        if (optionalTender.isPresent()) {
            model.addAttribute("Tender", optionalTender.get());
        }
        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        model.addAttribute("Today", modifiedDate);
        return "editTender";
    }

    @PostMapping("/editTender")
    public String editTenderPost(@Valid @ModelAttribute("Tender") Tender tender, BindingResult bindingResult, @RequestParam("dl") String deadline, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {

            return "editTender";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            tender.setDeadline(simpleDateFormat.parse(deadline));

        } catch (ParseException e) {
            bindingResult.rejectValue("deadline", "error.deadline", "Enter correct date (deadline)");
            return "editVacancy";
        }


        saveTender(tender, file);

        return "redirect:/tenders";
    }

    @GetMapping("/addNews")
    public String addNews(Model model) {
        model.addAttribute("NewNews", new News());
        model.addAttribute("Documents", documentService.findAll());
        return "addNews";
    }

    @PostMapping("/addNews")
    public String addNews(@Valid @ModelAttribute("NewNews") News news, BindingResult bindingResult, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, @RequestParam("file4") MultipartFile file4, @RequestParam("file5") MultipartFile file5) {
        if (bindingResult.hasErrors()) {
            return "addNews";
        }
        ArrayList<NewsImage> newsImages = new ArrayList<>();

        NewsImage newsImage;
        if (!file1.isEmpty()) {
            newsImage = saveNewsImage(file1, true);
            newsImages.add(newsImage);
        }
        if (!file2.isEmpty()) {
            newsImage = saveNewsImage(file2, false);
            newsImages.add(newsImage);
        }
        if (!file3.isEmpty()) {
            newsImage = saveNewsImage(file3, false);
            newsImages.add(newsImage);

        }
        if (!file4.isEmpty()) {
            newsImage = saveNewsImage(file4, false);
            newsImages.add(newsImage);

        }
        if (!file5.isEmpty()) {
            newsImage = saveNewsImage(file5, false);
            newsImages.add(newsImage);

        }


        news.setImages(newsImages);
        newsService.save(news);

        return "redirect:/news";
    }


    @GetMapping("/news")
    public String news(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "title") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending) {

        PageRequest pageRequest = getPageRequest(page, size, sort, ascending);
        Page pages = newsService.findAll(pageRequest);

        preparePageModel(model, page, size, sort, pages, ascending, "News");


        return "news";
    }

    @GetMapping("/editNews/{id}")
    public String editNews(@PathVariable("id") Long id, Model model) {
        Optional<News> optionalNews = newsService.findById(id);
        if (optionalNews.isPresent()) {
            model.addAttribute("News", optionalNews.get());
            model.addAttribute("Documents", documentService.findAll());

        }

        return "editNews";
    }

    @PostMapping("/editNews")
    public String editNewsPost(@Valid @ModelAttribute("News") News news, BindingResult bindingResult, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, @RequestParam("file4") MultipartFile file4, @RequestParam("file5") MultipartFile file5) {
        if (bindingResult.hasErrors()) {
            return "editNews";
        }

        News newsSaved = newsService.findById(news.getID()).get();
        news.setImages(newsSaved.getImages());

        ArrayList<NewsImage> newsImages = new ArrayList<>();

        NewsImage newsImage;
        if (!file1.isEmpty()) {
            newsImage = saveNewsImage(file1, true);
            newsImages.add(newsImage);
            news.setImages(newsImages);
        }
        if (!file2.isEmpty()) {
            newsImage = saveNewsImage(file2, false);
            newsImages.add(newsImage);
            news.setImages(newsImages);
        }
        if (!file3.isEmpty()) {
            newsImage = saveNewsImage(file3, false);
            newsImages.add(newsImage);
            news.setImages(newsImages);

        }
        if (!file4.isEmpty()) {
            newsImage = saveNewsImage(file4, false);
            newsImages.add(newsImage);
            news.setImages(newsImages);

        }
        if (!file5.isEmpty()) {
            newsImage = saveNewsImage(file5, false);
            newsImages.add(newsImage);
            news.setImages(newsImages);

        }


        newsService.save(news);
        return "redirect:/news";
    }


    @GetMapping("/searchNews")
    public String searchNews(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("Keyword", keyword);
        model.addAttribute("News", newsService.findByTitleContains(keyword));

        return "news";
    }


    @GetMapping("/addDocument")
    public String addDocument(Model model) {
        model.addAttribute("NewDocument", new Document());
        model.addAttribute("DocumentTypes", documentTypeService.findAll());
        return "addDocument";
    }

    @PostMapping("/addDocument")
    public String addDocumentPost(@Valid @ModelAttribute("NewDocument") Document document, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "addDocument";
        }

        String fileName = uploadFile(file);
        document.setName(fileName);
        documentService.save(document);

        return "redirect:/documents";
    }

    @GetMapping("/documents")
    public String documents(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "title") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending) {
        PageRequest pageRequest = getPageRequest(page, size, sort, ascending);
        Page pages = documentService.findAll(pageRequest);
        model.addAttribute("DocumentTypes", documentTypeService.findAll());
        preparePageModel(model, page, size, sort, pages, ascending, "Documents");

        return "documents";
    }


    @GetMapping("/editDocument/{id}")
    public String editDocument(Model model, @PathVariable("id") Long id) {
        Optional<Document> optionalDocument = documentService.findById(id);

        if (optionalDocument.isPresent()) {
            model.addAttribute("Document", optionalDocument.get());
            model.addAttribute("DocumentTypes", documentTypeService.findAll());

        }

        return "editDocument";
    }


    @PostMapping("/editDocument")
    public String editDocumentPost(@Valid @ModelAttribute("Document") Document document, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "editDocument";
        }

        if (!file.isEmpty()) {
            String fileName = uploadFile(file);
            document.setName(fileName);
        }
        documentService.save(document);

        return "redirect:/documents";
    }

    @GetMapping("/searchDocuments")
    public String searchDocuments(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("Keyword", keyword);
        model.addAttribute("Documents", documentService.findByTitleContains(keyword));

        return "documents";
    }

    @GetMapping("/deleteDocument/{id}")
    public String deleteDocument(@PathVariable("id") long id) {

        try {
            documentService.deleteById(id);
        } catch (Exception e) {
            return "redirect:/documents?constraint_error2=true";
        }
        return "redirect:/documents";

    }

    @GetMapping("/deleteVacancy/{id}")
    public String deleteVacancy(@PathVariable("id") long id) {
        Optional<Vacancy> optionalVacancy = vacancyService.findById(id);
        if (optionalVacancy.isPresent()) {
            vacancyService.deleteById(id);
        }
        return "redirect:/vacancies";
    }

    @GetMapping("/deleteNews/{id}")
    public String deleteNews(@PathVariable("id") long id) {
        Optional<News> optionalNews = newsService.findById(id);
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            List<NewsImage> newsImages = news.getImages();

            news.getImages().clear();
            news = newsService.save(news);

            newsImageService.deleteAll(newsImages);
            newsService.deleteById(id);
        }
        return "redirect:/news";
    }


    @GetMapping("/addService")
    public String addService(Model model) {
        model.addAttribute("NewService", new Service());
        return "addService";
    }

    @PostMapping("/addService")
    public String addServicePost(@Valid @ModelAttribute("NewService") Service service, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addService";
        }


        serviceService.save(service);

        return "redirect:/services";
    }

    @GetMapping("/services")
    public String services(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "title") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending) {
        PageRequest pageRequest = getPageRequest(page, size, sort, ascending);
        Page pages = serviceService.findAll(pageRequest);

        preparePageModel(model, page, size, sort, pages, ascending, "Services");

        return "services";
    }


    @GetMapping("/editService/{id}")
    public String editService(Model model, @PathVariable("id") Long id) {
        Optional<Service> optionalService = serviceService.findById(id);

        if (optionalService.isPresent()) {
            model.addAttribute("Service", optionalService.get());
        }

        return "editService";
    }


    @PostMapping("/editService")
    public String editServicePost(@Valid @ModelAttribute("Service") Service service, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editService";
        }


        serviceService.save(service);

        return "redirect:/services";
    }


    @GetMapping("/deleteService/{id}")
    public String deleteService(@PathVariable("id") long id) {
        Optional<Service> optionalService = serviceService.findById(id);
        if (optionalService.isPresent()) {
            serviceService.deleteById(id);
        }
        return "redirect:/services";
    }

    @PostMapping("/addDocumentType")
    public String addDocumentType(@RequestParam("title") String title,@RequestParam("description") String description, @RequestParam("language") News.Language language) {
        DocumentType documentType = new DocumentType();
        documentType.setTitle(title);
        documentType.setDescription(description);
        documentType.setLanguage(language);
        documentType.setDateTime(new Date());
        documentTypeService.save(documentType);
        return "redirect:/documents";
    }

    @PostMapping("/editDocumentType")

    public String editDocumentType(@RequestParam("title") String title,@RequestParam("description") String description, @RequestParam("id") long id, @RequestParam("language") News.Language language) {
        Optional<DocumentType> optionalDocumentType = documentTypeService.findById(id);

        if (optionalDocumentType.isPresent()) {
            DocumentType documentType = optionalDocumentType.get();
            documentType.setTitle(title);
            documentType.setDescription(description);
            documentType.setLanguage(language);
            documentType.setDateTime(new Date());
            documentTypeService.save(documentType);
        }
        return "redirect:/documents";
    }

    @GetMapping("/deleteDocumentType/{id}")
    public String deleteDocumentType(@PathVariable("id") long id) {
        Optional<DocumentType> optionalDocumentType = documentTypeService.findById(id);


        if (optionalDocumentType.isPresent()) {
            try {
                documentTypeService.delete(optionalDocumentType.get());
            } catch (Exception exception) {

                return "redirect:/documents?constraint_error=true";
            }

        }

        return "redirect:/documents";

    }

    @PostMapping("/addJob")
    public String addJobPost(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("language") News.Language language) {
        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setLanguage(language);

        jobService.save(job);

        return "redirect:/persons";
    }

    @GetMapping("/addPerson")
    public String addPerson(Model model) {
        model.addAttribute("NewPerson", new Person());
        model.addAttribute("JobTypes", jobService.findAll());
        return "addPerson";
    }

    @PostMapping("/addPerson")
    public String addPersonPost(@ModelAttribute("NewPerson") Person person, @RequestParam("file") MultipartFile file, @RequestParam("description") String description) {
        if (!file.isEmpty()) {
            person.setImage(uploadFile(file));
        }
        person.setDescription(description);
        personService.save(person);
        return "redirect:/persons";
    }

    @GetMapping("/persons")
    public String persons(Model model) {
        model.addAttribute("Persons", personService.findAll());
        model.addAttribute("Jobs", jobService.findAll());
        return "persons";
    }


    @GetMapping("/editPerson/{id}")
    public String editPerson(@PathVariable("id") long id, Model model) {

        model.addAttribute("EditedPerson", personService.findById(id).get());
        model.addAttribute("JobTypes", jobService.findAll());
        return "editPerson";
    }


    @PostMapping("/editPerson")
    public String editPersonPost(@ModelAttribute("EditedPerson") Person person, @RequestParam("file") MultipartFile file, @RequestParam("description") String description) {

        if (!file.isEmpty()) {
            person.setImage(uploadFile(file));
        }

        person.setDescription(description);
        personService.save(person);
        return "redirect:/persons";
    }

    @PostMapping("/editJob")
    public String editJob(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("language") News.Language language, @RequestParam("id") long id) {

        Optional<Job> optionalJob = jobService.findById(id);

        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setTitle(title);
            job.setDescription(description);
            job.setLanguage(language);
            jobService.save(job);

        }
        return "redirect:/persons";
    }

    @GetMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable("id") long id) {

        try {
            jobService.deleteById(id);
        } catch (Exception e) {
            return "redirect:/persons?constraint_error=true";
        }
        return "redirect:/persons";

    }

    @GetMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable("id") long id) {

        personService.deleteById(id);
        return "redirect:/persons";

    }

    @GetMapping("/updateGeneralInformation")
    public String updateGeneralInformation(Model model){
        model.addAttribute("GeneralInformation", generalInformationRepository.findById(1L).get());
        return "updateGeneralInformation";
    }

    @PostMapping("/updateGeneralInformation")
    public String updateGeneralInformationPost(@ModelAttribute("GeneralInformation") GeneralInforamtion generalInforamtion, @RequestParam("file") MultipartFile file,@RequestParam("structureImagefile") MultipartFile structureImage, @RequestParam("description") String englishDescription, @RequestParam("descriptionAM") String amharicDescription, @RequestParam("location") String location){

        if (!file.isEmpty()){
            generalInforamtion.setOfficeImage(uploadFile(file));
        }
        if (!structureImage.isEmpty()){
            generalInforamtion.setStructureImage(uploadFile(structureImage));
        }
        generalInforamtion.setAmharicDescription(amharicDescription);
        generalInforamtion.setEnglishDescription(englishDescription);
        generalInforamtion.setLocation(location);
        generalInformationRepository.save(generalInforamtion);

        return "redirect:/updateGeneralInformation?success=true";
    }

    @GetMapping("/suggestions")
    public  String suggestions(Model model){
        model.addAttribute("Suggestions", suggestionRepository.findAll());
        return "suggestions";
    }

    @GetMapping("/deleteSuggestion/{id}")
    public String deleteSuggestion(@PathVariable("id") long id){
        suggestionRepository.deleteById(id);
        return "redirect:/suggestions";
    }









    @GetMapping("/addLink")
    public String addLink(Model model) {
        model.addAttribute("NewLink", new Link());
        return "addLink";
    }

    @PostMapping("/addLink")
    public String addLinkPost(@Valid @ModelAttribute("NewLink") Link link, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "addLink";
        }

        if (!file.isEmpty()) {
            link.setImage(uploadFile(file));
        }
        linkRepository.save(link);

        return "redirect:/links";
    }

    @GetMapping("/links")
    public String links(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size, @RequestParam(name = "sort", defaultValue = "title") String sort, @RequestParam(name = "ascending", defaultValue = "1") int ascending) {
        PageRequest pageRequest = getPageRequest(page, 1000, sort, ascending);
        Page pages = linkRepository.findAll(pageRequest);

        preparePageModel(model, page, size, sort, pages, ascending, "Links");

        return "links";
    }


    @GetMapping("/editLink/{id}")
    public String editLink(Model model, @PathVariable("id") Long id) {
        Optional<Link> optionalLink = linkRepository.findById(id);

        if (optionalLink.isPresent()) {
            model.addAttribute("Link", optionalLink.get());
        }

        return "editLink";
    }


    @PostMapping("/editLink")
    public String editLinkPost(@Valid @ModelAttribute("Link") Link link, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "editLink";
        }

        if (!file.isEmpty()) {
            link.setImage(uploadFile(file));
        }
        linkRepository.save(link);

        return "redirect:/links";
    }


    @GetMapping("/deleteLink/{id}")
    public String deleteLink(@PathVariable("id") long id) {
        Optional<Link> optionalLink = linkRepository.findById(id);
        if (optionalLink.isPresent()) {
            linkRepository.deleteById(id);
        }
        return "redirect:/links";
    }












    private NewsImage saveNewsImage(MultipartFile file3, boolean isMain) {
        String fileName;
        NewsImage newsImage;
        fileName = uploadFile(file3);
        newsImage = new NewsImage();
        newsImage.setMain(isMain);
        newsImage.setName(fileName);
        newsImage = newsImageService.save(newsImage);
        return newsImage;
    }

    String uploadFile(MultipartFile file) {
        String fileName = "";
        try (InputStream inputStream = file.getInputStream()) {
            try {
                String[] fileNameSplit = file.getOriginalFilename().split("\\.");
                fileName = Calendar.getInstance().getTimeInMillis() + "." + fileNameSplit[fileNameSplit.length - 1];

                Files.copy(inputStream, Paths.get(imagesFolder, fileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {

            }
        } catch (IOException e1) {

        }

        return fileName;
    }

    static Model preparePageModel(Model model, int page, int size, String sort, Page pages, int ascending, String content) {
        model.addAttribute("Page", page);
        model.addAttribute("NextPage", page + 1);
        model.addAttribute("PreviousPage", page - 1);
        model.addAttribute("Sort", sort);
        model.addAttribute("LastPage", pages.getTotalPages() - 1);
        model.addAttribute("Size", size);
        model.addAttribute("Ascending", ascending);
        model.addAttribute(content, pages.getContent().toArray());

        return model;
    }


    static PageRequest getPageRequest(int page, int size, String sort, int ascending) {
        PageRequest pageRequest;
        if (ascending == 1) {
            pageRequest = PageRequest.of(page, size, Sort.by(sort).ascending());
        } else {
            pageRequest = PageRequest.of(page, size, Sort.by(sort).descending());
        }
        return pageRequest;
    }

    private void saveTender(Tender tender, MultipartFile file) {

        if (!file.isEmpty()) {
            String fileName = uploadFile(file);
            tender.setImage(fileName);
        }

        tenderService.save(tender);
    }

}
