package th.ac.ku.restaurant.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import th.ac.ku.restaurant.model.User;
import th.ac.ku.restaurant.repository.UserRepository;
import th.ac.ku.restaurant.dto.SignupDto;
@Service
public class SignupService {  //มีการ hash password ไว้เป็น object ก่อน เวลาที่ User ทำการ login เข้ามา จะได้นำ Password ที่กรอก ไปเปรียบเทียบกับตัวเก่าที่
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;


    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public void createUser(SignupDto user) {
        User record = modelMapper.map(user, User.class);


        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);

        repository.save(record);
    }

    public User getUser(String username) {
        return repository.findByUsername(username);
    }


}
