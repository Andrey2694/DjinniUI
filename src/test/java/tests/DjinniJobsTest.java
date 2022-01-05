package tests;

import com.codeborne.selenide.CollectionCondition;
import helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("djinni")
public class DjinniJobsTest extends BaseTest {

    @Test
    @DisplayName("Vacancy items are displayed on the page")
    void getListOfVacancyTest() {
        step("Open website", () -> {
            open("jobs/");
        });

        step("Find all vacancy on the page", () -> {
            $$(".list-jobs__item").shouldBe(CollectionCondition.sizeGreaterThan(0));
        });
    }

    @Test
    @DisplayName("Set Java filter and make sure that filter is displayed")
    void filterJavaTest() {
        step("Open website", () -> {
            open("jobs/");
        });

        step("Set filter", () -> {
            $x("//a[text() = 'Java']").click();
        });

        step("Check that each filter link has text Java", () -> {
            $$(".jobs-filter__link--active").forEach(x -> assertThat(x.getText()).isEqualTo("Java"));
        });
    }

    @Test
    @DisplayName("Set Java filter and make sure that vacancies have Java in title")
    void filterJavaJobTitleTest() {
        step("Open website", () -> {
            open("jobs/");
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
        step("Open website", () ->
                open("jobs/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}