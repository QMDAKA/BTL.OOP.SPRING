package mvs.repository;

import mvs.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Quang Minh on 4/9/2016.
 */
@RepositoryRestResource

public interface MarkRepository extends JpaRepository<Mark,Long> {
}
