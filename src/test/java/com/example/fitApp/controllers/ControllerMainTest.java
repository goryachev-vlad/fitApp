package com.example.fitApp.controllers;
import com.example.fitApp.models.Models;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.example.fitApp.repo.ModelsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class ControllerMainTest {

    @Mock
    private ModelsRepository modelsRepository;
    @InjectMocks
    private ControllerMain controllerMain;

    @Test
    void testHome_validModel_attributeAdded() {
        //given
        Model model = Mockito.mock(Model.class);

        //when
        String result = controllerMain.home(model);

        //then
        verify(model).addAttribute("title", "главная страница");
        Assertions.assertEquals("home", result);
    }

    @Test
    void testHome_nullModel_exceptionIsThrown() {
        //given
        Model model = null;

        //when
        Assertions.assertThrows(RuntimeException.class,
                () -> controllerMain.home(model));
    }

    @Test
    void test_History() {
        Model model = Mockito.mock(Model.class);

        Iterable<Models> posts = modelsRepository.findAll();
        String result = controllerMain.history(model);

        verify(model).addAttribute("posts", posts);
        Assertions.assertEquals("history", result);


    }

    @Test
    void test_post() {
        Models models = Mockito.mock(Models.class);
        Model model = Mockito.mock(Model.class);

        modelsRepository.save(models);
        String result = controllerMain.history(model);

        verify(modelsRepository).save(models);
        Assertions.assertEquals("history", result);
        
    }
}
