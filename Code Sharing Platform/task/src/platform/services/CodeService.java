package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.dao.CodeRepository;
import platform.entities.CodeInfo;
import platform.exceptions.CodeNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CodeService {

    private final CodeRepository codeRepo;

    @Autowired
    public CodeService(CodeRepository codeRepo) {
        this.codeRepo = codeRepo;
    }

    public String saveCode(CodeInfo code) {
        codeRepo.save(code);
        return code.getId();
    }

    public CodeInfo getCodeSnippet(String id) {
        Optional<CodeInfo> code = codeRepo.findById(id);
        if (code.isPresent()) {
            code.get().updateTime();
            if (!code.get().isEnabled())
                throw new CodeNotFoundException();
            code.get().decrementViews();
            codeRepo.save(code.get());
            return code.get();
        } else
            throw new CodeNotFoundException();
    }

    public List<CodeInfo> getLatestCodeSnippets() {
        return codeRepo.getLatestSnippets();
    }

}