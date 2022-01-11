package tests;

import com.codeborne.selenide.SelenideElement;
import helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("djinni")
public class DjinniCandidatesTest extends BaseTest {

    @Test
    @DisplayName("Candidate items are displayed on the page")
    void getListOfCandidateTest() {
        step("Open website", () -> {
            open("developers");
        });

        step("Find all candidates on the page", () -> {
            $$(".candidate-header").shouldBe(sizeGreaterThan(0));
        });
    }

    @Test
    @DisplayName("Set Java filter and make sure that candidates have Java in title")
    void filterJavaJobTitleTest() {
        step("Open website", () -> {
            open("developers");
        });

        step("Find count of jobs on the website", () -> {
            $x("//a[text() = 'Java']").click();
        });

        step("Check that each vacancy has Java in title", () -> {
            $$(".profile").forEach(x -> assertThat(x.getText()).containsIgnoringCase("Java"));
        });
    }

    @Test
    @DisplayName("Set Salary filter and make sure that candidates sorted by filter")
    void filterSalaryTest() {
        step("Open website", () -> {
            open("developers");
        });
        step("Set Salary filter 1000-1500", () -> {
            $("[name='salary_min']").val("1000");
            $("[name='salary_max']").val("1500");
            $(".btn-small").click();
        });
        step("Check that each candidate has salary between 1000 and 1500", () -> {
            $$(".profile-details-salary")
                    .stream()
                    .map(SelenideElement::getText)
                    .map(x -> x.substring(1))
                    .forEach(x -> assertThat(Integer.parseInt(x)).isGreaterThan(999).isLessThan(1501));
        });
    }

    @Test
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open website", () ->
                open("developers"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}
