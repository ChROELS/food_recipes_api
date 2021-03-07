package eu.christineroels.foodrecipesrest.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.christineroels.foodrecipesrest.services.IngredientService;
import eu.christineroels.foodrecipesrest.services.RecipeService;
import eu.christineroels.foodrecipesrest.web.models.IngredientDto;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Tag("controllerTest")
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest
@ComponentScan(basePackages ={"eu.christineroels.foodrecipesrest.web.mappers"} )
class IngredientControllerTest {
    @MockBean
    IngredientService ingredientService;
    @MockBean
    RecipeService recipeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    IngredientDto validIngredient;


    @BeforeEach
    void setUp() {
        validIngredient = new IngredientDto();
        validIngredient.setName("Corn Flour");
        validIngredient.setIngredientId(UUID.randomUUID());
        validIngredient.setCreatedDate(OffsetDateTime.now());
        validIngredient.setLastUpdatedDate(OffsetDateTime.now());
    }

    @AfterEach
    void tearDown() {
        reset(ingredientService);
    }

    @Test
    void getIngredientById() throws Exception {
        //given
        //stubbing the mock service method call
        given(ingredientService.containsIngredientId(any())).willReturn(true);
        given(ingredientService.getIngredientById(any())).willReturn(validIngredient);
        //when
        mockMvc.perform(get("/api/food/ingredients/{ingredientId}",
                validIngredient.getIngredientId().toString()).contentType(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.ingredientId", Is.is(validIngredient.getIngredientId().toString())))
            .andExpect(jsonPath("$.name", Is.is(validIngredient.getName())))
        //documenting
                .andDo(document("food/ingredient-get", pathParameters(
                parameterWithName("ingredientId").description("UUID of desired ingredient to get.")),
                responseFields(fieldWithPath("ingredientId").description("Ingredient Id").type(UUID.class),
                        fieldWithPath("name").description("Ingredient Name"),
                        fieldWithPath("createdDate").description("Date of creation").type(OffsetDateTime.class),
                        fieldWithPath("lastUpdatedDate").description("Date of last update").type(OffsetDateTime.class))));
    }

    @Test
    void getAllByName() throws Exception {
        //given
        List<IngredientDto> allFlours = new ArrayList<>();
        allFlours.add(validIngredient);
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setIngredientId(UUID.randomUUID());
        ingredientDto.setName("Rice Flour");
        ingredientDto.setCreatedDate(OffsetDateTime.now());
        ingredientDto.setLastUpdatedDate(OffsetDateTime.now());
        allFlours.add(ingredientDto);
        //stubbing
        given(ingredientService.getAllByName(any())).willReturn(allFlours);
        //when
        mockMvc.perform(get("/api/food/ingredients/all/{name}","Flour")
                .contentType(MediaType.APPLICATION_JSON))
        //then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        //The response body contains two json objects in an array
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
        .andDo(document("food/ingredient-getAllByName", pathParameters(
                parameterWithName("name").description("Name of a ingredient like 'carrot' or a quality in case of" +
                        "a composed ingredient like 'flour' for 'rice flour', 'corn flour'.")),
                responseFields(fieldWithPath("[]").description("An array of ingredients with a common component or quality."),
                fieldWithPath("[].ingredientId").description("Ingredient Id").type(UUID.class),
                        fieldWithPath("[].name").description("Ingredient Name"),
                        fieldWithPath("[].createdDate").description("Date of creation").type(OffsetDateTime.class),
                        fieldWithPath("[].lastUpdatedDate").description("Date of last update").type(OffsetDateTime.class))));
    }
    @Test
    void getAll() throws Exception {
        //given
        List<IngredientDto> allFlours = new ArrayList<>();
        allFlours.add(validIngredient);
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setIngredientId(UUID.randomUUID());
        ingredientDto.setName("Carrot");
        ingredientDto.setCreatedDate(OffsetDateTime.now());
        ingredientDto.setLastUpdatedDate(OffsetDateTime.now());
        allFlours.add(ingredientDto);
        //stubbing
        given(ingredientService.getAll()).willReturn(allFlours);
        //when
        mockMvc.perform(get("/api/food/ingredients/")
                .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //The response body contains two json objects in an array
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(document("food/ingredient-getAll",
                        responseFields(fieldWithPath("[]").description("An array of all saved ingredients."),
                                fieldWithPath("[].ingredientId").description("Ingredient Id").type(UUID.class),
                                fieldWithPath("[].name").description("Ingredient Name"),
                                fieldWithPath("[].createdDate").description("Date of creation").type(OffsetDateTime.class),
                                fieldWithPath("[].lastUpdatedDate").description("Date of last update").type(OffsetDateTime.class))));
    }

    @Test
    void addIngredient() throws Exception {
        //given
        validIngredient.setIngredientId(null);
        ingredientService.saveIngredient(validIngredient);
        String ingredientDtoToJson = objectMapper.writeValueAsString(validIngredient);
        ConstrainedFields fields = new ConstrainedFields(IngredientDto.class);
        //when
        mockMvc.perform(post("/api/food/ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ingredientDtoToJson))
        //then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
        .andDo(document("food/ingredient-create",
                requestFields(fields.withPath("ingredientId").ignored(),
                        fields.withPath("name").description("Ingredient name")
                .type(String.class),
                        fields.withPath("createdDate").ignored(),
                        fields.withPath("lastUpdatedDate").ignored())));
    }

    @Test
    void updateIngredientFound() throws Exception {
        //given
        validIngredient.setIngredientId(null);
        IngredientDto updatedIng = new IngredientDto();
        updatedIng.setIngredientId(UUID.randomUUID());
        updatedIng.setName(validIngredient.getName());
        updatedIng.setCreatedDate(OffsetDateTime.now());
        updatedIng.setLastUpdatedDate(OffsetDateTime.now());
        given(ingredientService.containsIngredientId(any())).willReturn(true);
        given(ingredientService.updateIngredient(any(),any())).willReturn(updatedIng);
        String ingredientDtoToJson = objectMapper.writeValueAsString(validIngredient);
        ConstrainedFields fields = new ConstrainedFields(IngredientDto.class);
        //when
        mockMvc.perform(put("/api/food/ingredients/{ingredientId}",UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ingredientDtoToJson))
                //then
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.ingredientId",Is.is(updatedIng.getIngredientId().toString())))
        .andExpect(jsonPath("$.name",Is.is("Corn Flour")))
        .andExpect(jsonPath("$.createdDate",Is.isA(String.class)))
                .andExpect(jsonPath("$.lastUpdatedDate",Is.isA(String.class)))
        //documenting
        .andDo(document("food/ingredient-put",
                requestFields(fields.withPath("ingredientId").ignored(),
                fields.withPath("name").description("Ingredient name").type(String.class),
                fields.withPath("createdDate").ignored(),
                fields.withPath("lastUpdatedDate").ignored()),
                responseFields(
                        fieldWithPath("ingredientId").description("Id of the ingredient that has been updated"),
                        fieldWithPath("name").description("New ingredient name after update"),
                        fieldWithPath("createdDate").description("Date of creation of the ingredient that has been updated"),
                        fieldWithPath("lastUpdatedDate").description("Last date of update"))));
    }

    @Test
    void deleteIngredientFound() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        given(ingredientService.containsIngredientId(id)).willReturn(true);
        //when
        mockMvc.perform(delete("/api/food/ingredients/{ingredientId}",id)
        .accept(MediaType.APPLICATION_JSON))
        //then
        .andExpect(status().isOk())
        .andExpect(content().string("Ingredient deleted."));
        then(ingredientService).should().deleteIngredient(id);
    }
    @Test
    void deleteIngredientNotFound() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        given(ingredientService.containsIngredientId(id)).willReturn(false);
        //when
        mockMvc.perform(delete("/api/food/ingredients/{ingredientId}",id)
                .accept(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Ingredient id invalid or not found."));
    }
    //Documenting Constraint validation
    private static class ConstrainedFields{
        private final ConstraintDescriptions constraintDescriptions;
        ConstrainedFields(Class<?> input){
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }
        private FieldDescriptor withPath(String path){
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils.collectionToDelimitedString(this.constraintDescriptions
                    .descriptionsForProperty(path), ". ")));
        }
    }
}