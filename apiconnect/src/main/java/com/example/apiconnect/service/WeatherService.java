package com.example.apiconnect.service;

import com.example.apiconnect.model.Users;
import com.example.apiconnect.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository)
    {
        this.weatherRepository = weatherRepository;
    }

    public List<Users> findAllUsers()
    {
       return weatherRepository.findAll();


        /*var mongoClient = MongoClients.create("mongodb://localhost:27017");
        List<WeatherUsers> listUsers = new ArrayList<>();
        return  listUsers;*/
    }

    // Method 2
    // Get one user by Id
    public Users findUserById(String id)
    {
        return weatherRepository.findById(id).
                orElseThrow(()-> new RuntimeException(
                        String.format("Cannot find User by id %s", id)
                ));
    }

    // Method 3
    // Add new user data to the DB
    public void addNewUser(Users user)
    {
       weatherRepository.insert(user);
    }

    // Method 4
    //Update existing user data
    public  void UpdateUser(Users user)
    {
        Users userFound = weatherRepository.findById(user.getId())
                .orElseThrow(()-> new RuntimeException(
                        String.format("Cannot find id by ID %s", user.getId())
                ));

//        userFound.setName(user.getName());
//        userFound.setSalary(user.getSalary());

        weatherRepository.save(user);
    }

    //Method 5
    // Delete an existing users
    public void DeleteUsers(String id)
    {
        weatherRepository.deleteById(id);
    }
}
