package mvs.configuration;

import mvs.model.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Created by QUANG MINH on 3/8/2016.
 */


public class JpaConfig extends RepositoryRestMvcConfiguration {
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Teacher.class);
        config.exposeIdsFor(Classroom.class);
        config.exposeIdsFor(Mark.class);
        config.exposeIdsFor(Attendance.class);
        config.exposeIdsFor(Subject.class);

    }
}
