package kz.technodom;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.*;

public class baseTest {
    @BeforeAll
    static void beforeAll() {
        pageLoadStrategy = "eager";
        browserSize = "1920x1080";
    }
}