package mvs.repository;

import mvs.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Quang Minh on 4/9/2016.
 */
@RepositoryRestResource
public interface ClassroomRepository extends JpaRepository<Classroom,Long>{
    List<Classroom> findByIdNotIn(List<Long> Id);
}
