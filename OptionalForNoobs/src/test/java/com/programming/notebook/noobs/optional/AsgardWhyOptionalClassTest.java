package com.programming.notebook.noobs.optional;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Why use Optional class")
class AsgardWhyOptionalClassTest {

    @Test
    @DisplayName("Getting a NullPointerException")
    void canGiveNullPointerException() {

        UserName userName = UserName.builder().firstName("Programming Notebook").build();

        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> userName.getLastName().toUpperCase(),
                "Expected toUpperCase() to throw NullPointerException, but it didn't"
        );

        assertThat(thrown.getMessage(), containsString("Cannot invoke \"String.toUpperCase()\" because the " +
                "return value of \"com.programming.notebook.noobs.optional.AsgardWhyOptionalClassTest$UserName" +
                ".getLastName()\" is null"));
    }

    @Test
    @DisplayName("Check if the value is null")
    void checkForNullValues() {

        UserName userName = UserName.builder().firstName("Programming Notebook").build();
        Optional<String> lastNameOptional = Optional.ofNullable(userName.getLastName());

        assertThat(lastNameOptional.isPresent(), is(false));
    }

    @Test
    @DisplayName("Get the value from Optional")
    void getTheValue() {

        UserName userName = UserName.builder().firstName("Programming Notebook").build();
        Optional<String> firstNameOptional = Optional.ofNullable(userName.getFirstName());

        assertThat(firstNameOptional.isPresent(), is(true));
        assertThat(firstNameOptional.get(), is("Programming Notebook"));
    }

    @Data
    @Builder(toBuilder = true)
    private static class UserName {
        private String firstName;
        private String lastName;
    }
}
