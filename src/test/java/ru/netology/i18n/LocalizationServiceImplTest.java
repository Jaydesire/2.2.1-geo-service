package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

class LocalizationServiceImplTest {

    private LocalizationServiceImpl localizationService;

    @BeforeEach
    void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("source")
    void localeTest(Country country, String expected) {
        Assertions.assertEquals(expected, localizationService.locale(country));
    }

    private static Stream<Arguments> source(){
        return Stream.of(Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"));
    }

}