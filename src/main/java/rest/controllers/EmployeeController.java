package rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import rest.Employee;
import rest.repositories.EmployeeRepository;
import rest.storage.StorageService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    private final StorageService storageService;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    public EmployeeController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/new-employee")
    public String employeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new-employee";
    }

    @PostMapping("/new-employee")
    public String employeeSubmit(@ModelAttribute("employee") @Validated Employee employee, BindingResult result, Model model, MultipartFile file) {
        if(result.hasErrors()) {
            return "error";
        }

        storageService.store(file);
        employee.setImageFileName(file.getOriginalFilename());
        repository.save(employee);

        return "save-success";
    }

    @GetMapping("/employee")
    public String viewEmployee(@RequestParam("name") String lastName, Model model) throws IOException {

        List<Employee> employee = repository.findByLastName(lastName);

        model.addAttribute("id", employee.get(0).getId());
        model.addAttribute("firstName", employee.get(0).getFirstName().toUpperCase());
        model.addAttribute("lastName", employee.get(0).getLastName().toUpperCase());
        model.addAttribute("title", employee.get(0).getTitle());
        model.addAttribute("practice", employee.get(0).getPractice());
        model.addAttribute("domain", employee.get(0).getDomain());
        model.addAttribute("isPracticeLead", employee.get(0).isPracticeLead());
        model.addAttribute("isDomainLead", employee.get(0).isDomainLead());
        model.addAttribute("file", storageService.load(employee.get(0).getImageFileName()).getFileName());


        return "employee";
    }

    @GetMapping("/")
    public String viewAllEmployees(Model model) {
        model.addAttribute("employees", repository.findAll());
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "all-employees";
    }
}
