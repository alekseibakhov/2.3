package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO interfaceDAO;

    @Override
    @Transactional
    public List<User> getAll() {
        return interfaceDAO.getAll();
    }

    @Override
    @Transactional
    public User getById(int id) {
        return interfaceDAO.getById(id);
    }

    @Override
    @Transactional
    public void save(User person) {
        User user = new User();
        user.setAge(person.getAge());
        user.setId(person.getId());
        user.setName(person.getName());
        interfaceDAO.save(user);
    }

    @Override
    @Transactional
    public void update(int id, User person) {
        interfaceDAO.update(id, person);
    }

    @Override
    @Transactional
    public void delete(int id) {
        interfaceDAO.delete(id);
    }

}
