package tests;

import com.codeborne.selenide.CollectionCondition;
import helpers.DriverUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class DjinniJobsTest extends TestBase {

    @Test
    @DisplayName("https://djinni.co/jobs/ page has vacancy items on the page")
    void getListOfJobsTest() {
        step("Open website", () -> {
            open("https://djinni.co/jobs/");
        });

        step("Find all vacancy on the page", () -> {
            $$(".list-jobs__item").shouldBe(CollectionCondition.sizeGreaterThan(0));
        });
    }

    @Test
    @DisplayName("Set Java filter and make sure that filter is displayed")
    void filterJavaTest() {
        step("Open website", () -> {
            open("https://djinni.co/jobs/");
        });

        step("Set filter", () -> {
            $x("//a[text() = 'Java']").click();
        });

        step("Check that each filter link has text Java", () -> {
            $$(".jobs-filter__link--active").forEach(x -> assertThat(x.getText()).isEqualTo("Java"));
        });
    }

    @Test
    @DisplayName("Set Java filter and make sure that vacancy contain Java in title")
    void filterJavaJobTitleTest() {
        step("Open website", () -> {
            open("https://djinni.co/jobs/");
        });

        step("Find count of jobs on the website", () -> {
            $x("//a[text() = 'Java']").click();
        });

        step("Check that each vacancy has Java in title", () -> {
            $$(".profile").forEach(x -> assertThat(x.getText()).containsIgnoringCase("Java"));
        });
    }

    @Test
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open 'https://djinni.co/jobs/'", () ->
                open("https://djinni.co/jobs/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}