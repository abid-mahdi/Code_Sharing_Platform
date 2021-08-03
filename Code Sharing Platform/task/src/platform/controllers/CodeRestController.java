package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.Utils;
import platform.entities.CodeInfo;
import platform.services.CodeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CodeRestController {

    private final CodeService codeService;

    @Autowired
    public CodeRestController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeInfo> getCode(@PathVariable String id) {
        return ResponseEntity.ok(codeService.getCodeSnippet(id));
    }

    @PostMapping(path = "/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addNewCode(@RequestBody CodeInfo code) {
        code.setDate(Utils.getCurrentDateTime());
        String id = codeService.saveCode(code);
        return String.format("{ \"id\" : \"%s\" }", id);
    }

    @GetMapping(path = "/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CodeInfo>> getLatestCode() {
        return ResponseEntity.ok(codeService.getLatestCodeSnippets());
    }

}