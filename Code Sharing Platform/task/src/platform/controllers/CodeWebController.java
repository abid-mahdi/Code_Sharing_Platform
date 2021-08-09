package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.services.CodeService;

@Controller
public class CodeWebController {

    private final CodeService codeService;

    @Autowired
    public CodeWebController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCode(Model model, @PathVariable String id) {
        model.addAttribute("code", codeService.getCodeSnippet(id));
        return "show-code";
    }

    @GetMapping(path = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String submitNewCode() {
        return "submit-code";
    }

    @GetMapping(path = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getLatest(Model model) {
        model.addAttribute("codeList", codeService.getLatestCodeSnippets());
        return "latest-codes";
    }

}