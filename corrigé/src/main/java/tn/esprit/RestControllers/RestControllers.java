package tn.esprit.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.Services.IServices;

@RestController
@AllArgsConstructor
public class RestControllers {
    @Autowired
    IServices services;
}
