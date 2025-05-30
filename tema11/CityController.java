package org.example.lab11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityrepo;

    @GetMapping
    public List<CityDTO> getCities() throws SQLException {
        return cityrepo.findAll();
    }

    @PostMapping
    public void addCity(@RequestBody City city) throws SQLException {
        cityrepo.create(city);
    }

    @PutMapping("/{id}")
    public void updateCity(@PathVariable int id, @RequestBody City city) throws SQLException {
        cityrepo.updateName(id, city.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable int id) throws SQLException {
        cityrepo.delete(id);
    }
}