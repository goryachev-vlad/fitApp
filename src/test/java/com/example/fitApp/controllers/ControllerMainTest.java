package com.example.fitApp.controllers;
import com.example.fitApp.models.Models;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.example.fitApp.repo.ModelsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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
        //given
        Model model = Mockito.mock(Model.class);


        //when
        String result = controllerMain.history(model);

        //then
        verify(modelsRepository).findAll();
        Assertions.assertEquals("history", result);
    }

    @Test
    void test_post() {
        Model model = Mockito.mock(Model.class);


        String result = controllerMain.Posthome("qwe", 1, 1.1, model);

        ArgumentCaptor<Models> captor = ArgumentCaptor.forClass(Models.class);
        verify(modelsRepository).save(captor.capture());
        Models models = captor.getValue();
        Assertions.assertEquals("qwe", models.getExercise());
        Assertions.assertEquals(1, models.getApproaches());
        Assertions.assertEquals(1.1, models.getTime());
        Assertions.assertEquals("redirect:/history", result);
    }

    @Test
    void test_delete() {
        //given
        long id = 100;
        Models models = new Models("e", 1, 0d);
        when(modelsRepository.findById(id)).thenReturn(Optional.of(models));
        //when
        String result = controllerMain.delete(null, id);


        //then
       verify(modelsRepository).delete(models);


    }
}