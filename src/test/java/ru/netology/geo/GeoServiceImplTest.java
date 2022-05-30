package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;


class GeoServiceImplTest {

    private GeoServiceImpl geoService;

    @BeforeEach
    void setUp() {
        geoService = new GeoServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("source")
    void byIp(String ip, Location location) {
        Assertions.assertEquals(location.getCity(), geoService.byIp(ip).getCity());
        Assertions.assertEquals(location.getCountry(), geoService.byIp(ip).getCountry());
        Assertions.assertEquals(location.getStreet(), geoService.byIp(ip).getStreet());
        Assertions.assertEquals(location.getBuiling(), geoService.byIp(ip).getBuiling());
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("96.44.0.1", new Location("New York", Country.USA, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("172.0.1.11", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)));
    }
}