package eu.christineroels.foodrecipesrest.web.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.christineroels.foodrecipesrest.services.RecipeService;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.returns;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest
@ComponentScan(basePackages ={"eu.christineroels.foodrecipesrest.web.mappers"})
class RecipeControllerTest {

    @MockBean
    RecipeService recipeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    RecipeDto validRecipe;

    @BeforeEach
    public void setUp(){
        validRecipe = new RecipeDto();
        validRecipe.setRecipeId(UUID.randomUUID());
        validRecipe.setRecipeName("Recipe Found");
        validRecipe.setAmountServings(4);
        validRecipe.setCookingTime(143);
        validRecipe.setPreparationTime(30);
        validRecipe.setTotalTime();
        validRecipe.setCreatedDate(OffsetDateTime.now());
        validRecipe.setLastUpdatedDate(OffsetDateTime.now());
    }
    //always reset the service since it will be called by each test method
    //and with WebMvcTest we are loading the 'real' app beerService in the testing environment
    @AfterEach
    void tearDown() {
        reset(recipeService);
    }
    @Test
    public void getRecipeById() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'T'HH:mm:ssZ");
        //stubbing the mock service method call
        given(recipeService.getRecipeById(any())).willReturn(validRecipe);

        mockMvc.perform(get("/api/food/recipes/{recipeId}",validRecipe.getRecipeId().toString())
                .accept(MediaType.APPLICATION_JSON))
                //Assertions
                //response status
                .andExpect(status().isOk())
                //content type of Json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //using JayWay annotation to assert return of the desired object
                .andExpect(jsonPath("$.recipeId", Is.is(validRecipe.getRecipeId().toString())))
                .andExpect(jsonPath("$.amountServings", Is.is(4)))
                .andExpect(jsonPath("$.createdDate", Is.is(dateTimeFormatter.format(
                        validRecipe.getCreatedDate()))))
                //documenting response
                .andDo(document("food/recipe-get", pathParameters(
                        parameterWithName("recipeId").description("UUID of desired recipe to get.")),
                        responseFields(fieldWithPath("recipeId").description("Recipe Id").type(UUID.class),
                                fieldWithPath("recipeName").description("Recipe Name"),
                                fieldWithPath("cookingTime").description("Recipe cooking time"),
                                fieldWithPath("preparationTime").description("Recipe preparation time"),
                                fieldWithPath("amountServings").description("Recipe amount of servings"),
                                fieldWithPath("totalTime").description("Recipe total time of preparation including cooking time"),
                                fieldWithPath("createdDate").description("Date of creation").type(OffsetDateTime.class),
                                fieldWithPath("lastUpdatedDate").description("Last update").type(OffsetDateTime.class)
                        )));




    }
    @Test
    public void updateRecipe() throws Exception {

    }
    //Test returns status 415 unsupported media type
    @Test
    public void createRecipe() throws Exception {
        //given
        validRecipe.setRecipeId(UUID.randomUUID());
        validRecipe.setRecipeName("Saved Recipe");
        String recipeDtoJson = objectMapper.writeValueAsString(validRecipe);
        ConstrainedFields fields = new ConstrainedFields(RecipeDto.class);
        given(recipeService.saveNewRecipe(any())).willReturn(validRecipe);
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/food/recipes/new")
                .contentType(MediaType.ALL)
                .content(recipeDtoJson))
        //then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().exists("Recipe Created"))


        //Documenting post request
       .andDo(document("food/recipe-create",
                requestFields(fields.withPath("recipeId").ignored(),
                        fields.withPath("createdDate").ignored(),
                        fields.withPath("lastUpdatedDate").ignored(),
                        fields.withPath("totalTime").ignored(),
                        //fields that actually need to be filled to create a recipe
                        fields.withPath("recipeName").description("Recipe Name"),
                        fieldWithPath("cookingTime").description("Recipe cooking time"),
                        fieldWithPath("preparationTime").description("Recipe preparation time"),
                        fieldWithPath("amountServings").description("Recipe amount of servings"))
                ));
    }
    @Test
    public void deleteRecipeNotFound() throws Exception {
        //Given
        given(recipeService.getRecipeById(any())).willReturn(validRecipe);
        given(recipeService.containsRecipe(validRecipe)).willReturn(false);
        mockMvc.perform(delete("/api/food/recipes/delete/{recipeId}",validRecipe.getRecipeId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //using JayWay annotation to assert return of the desired object
                .andExpect(jsonPath("$.recipeId", Is.is(validRecipe.getRecipeId().toString())))
                .andExpect(jsonPath("$.amountServings", Is.is(4)))
                //headers
                .andExpect(header().exists("ResponseNotOK"))
                //documenting response
                .andDo(document("food/recipe-delete",
                        pathParameters(parameterWithName("recipeId").description("UUID of desired recipe to delete.")),
                        responseFields(fieldWithPath("recipeId").description("Recipe Id").type(UUID.class),
                                fieldWithPath("recipeName").description("Recipe Name"),
                                fieldWithPath("cookingTime").description("Recipe cooking time"),
                                fieldWithPath("preparationTime").description("Recipe preparation time"),
                                fieldWithPath("amountServings").description("Recipe amount of servings"),
                                fieldWithPath("totalTime").description("Recipe total time of preparation including cooking time"),
                                fieldWithPath("createdDate").description("Date of creation").type(OffsetDateTime.class),
                                fieldWithPath("lastUpdatedDate").description("Last update").type(OffsetDateTime.class)
                        )));
    }
    @Test
    public void deleteRecipeFound() throws Exception {
        //Given
        given(recipeService.getRecipeById(any())).willReturn(validRecipe);
        given(recipeService.containsRecipe(validRecipe)).willReturn(true);
        //When
        mockMvc.perform(delete("/api/food/recipes/delete/{recipeId}",validRecipe.getRecipeId().toString())
                .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //using JayWay annotation to assert return of the desired object
                .andExpect(jsonPath("$.recipeId", Is.is(validRecipe.getRecipeId().toString())))
                .andExpect(jsonPath("$.amountServings", Is.is(4)))
                //headers
                .andExpect(header().exists("ResponseOK"));
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