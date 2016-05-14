package mvs.controller;

import mvs.model.Mark;
import mvs.model.Student;
import mvs.model.Studentattendance;
import mvs.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Quang Minh on 4/17/2016.
 */
@RestController
public class MarkController {
    @Autowired
    MarkRepository markRepository;
    @RequestMapping(path = "/marks/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> putMark(
            @PathVariable(value = "id") long id,
            @RequestBody Mark mark
    ) {

        Mark mark1 = markRepository.findOne(id);
        if (mark1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            mark1.setDiemCuoiKy(mark.getDiemCuoiKy());
            mark1.setDiemGiuaKy(mark.getDiemGiuaKy());
            mark1.setDiemTongKet(mark.getDiemTongKet());
            markRepository.save(mark1);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }
}
