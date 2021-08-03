package platform.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.entities.CodeInfo;

import java.util.List;

@Repository
public interface CodeRepository extends CrudRepository<CodeInfo, String> {

    @Query(nativeQuery=true,
            value="SELECT * FROM CODE_INFO \n" +
                    "WHERE ENABLED = TRUE AND TIME_RESTRICTED = FALSE AND VIEW_RESTRICTED = FALSE\n" +
                    "ORDER BY DATE DESC LIMIT 10")
    List<CodeInfo> getLatestSnippets(); // Gets latest unrestricted snippets

}
