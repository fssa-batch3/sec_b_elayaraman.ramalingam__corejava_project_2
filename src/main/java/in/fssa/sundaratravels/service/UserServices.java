package in.fssa.sundaratravels.service;

import in.fssa.sundaratravels.dao.UserDAO;
import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.exception.ServicesException;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.User;
import in.fssa.sundaratravels.util.NumUtil;
import in.fssa.sundaratravels.validator.UserValidator;

public class UserServices {
    private final UserDAO userDAO = new UserDAO();

    public void createUser(User user) throws ServicesException {
        try {
            UserValidator.validateUser(user);
            userDAO.createUser(user);
        } catch (ValidationException | PersistenceException e) {
            throw new ServicesException( e.getMessage());
        }
    }
    public User getUserByPhoneNumber(Long phonenumber) throws ServicesException {
        User user = null;
        try {
            NumUtil.rejectIfInvalidPhoneNumber(phonenumber);
            user = userDAO.getUserByPhoneNumber(phonenumber);
        } catch (ValidationException | PersistenceException e) {
            throw new ServicesException(e.getMessage());
        }
        return user;
    }
    public User getUserByUserId(int id) throws ServicesException {
        User user = null;
        try {
            NumUtil.rejectIfInvalidNum(id,"user Id");
            user = userDAO.getUserByUserId(id);
        } catch (ValidationException | PersistenceException e) {
            throw new ServicesException(e.getMessage());
        }
        return user;
    }

    public void updateUserDetails(User user) throws ServicesException {
        try {
            UserValidator.validateUser(user);
            userDAO.updateUserDetails(user);
        } catch (ValidationException | PersistenceException e) {
            throw new ServicesException("Failed to update user details: " + e.getMessage());
        }
    }

    public void changeUserStatus(int userId, boolean isActive) throws ServicesException {
        try {
            userDAO.changeUserStatus(userId, isActive);
        } catch (PersistenceException e) {
            throw new ServicesException("Failed to change user status: " + e.getMessage());
        }
    }
}
