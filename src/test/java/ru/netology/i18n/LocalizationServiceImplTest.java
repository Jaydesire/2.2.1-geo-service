package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    LocalizationServiceImpl sut;

    @BeforeEach
    void setUp() {
        sut = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("source")
    void localeTest(Country country, String expected) {
        Assertions.assertEquals(sut.locale(country), expected);
    }

    private static Stream<Arguments> source(){
        return Stream.of(Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"));
    }

}