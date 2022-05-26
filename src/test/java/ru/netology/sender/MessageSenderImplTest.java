package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class MessageSenderImplTest {

    static MessageSenderImpl sut;

    @BeforeEach
    public void setUp() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        sut = new MessageSenderImpl(geoService, localizationService);
    }


    @ParameterizedTest
    @MethodSource("source")
    public void sendTest (String ip, String expected) {

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String result = sut.send(headers);

        Assertions.assertEquals(expected, result);

    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("172.5.50.66", "Добро пожаловать"),
                Arguments.of("96.0.32.11", "Welcome"),
                Arguments.of("172.5.50.66", "Добро пожаловать"),
                Arguments.of("96.4.02.65", "Welcome"));
    }

}