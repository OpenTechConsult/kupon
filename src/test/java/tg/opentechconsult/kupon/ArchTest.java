package tg.opentechconsult.kupon;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("tg.opentechconsult.kupon");

        noClasses()
            .that()
                .resideInAnyPackage("tg.opentechconsult.kupon.service..")
            .or()
                .resideInAnyPackage("tg.opentechconsult.kupon.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..tg.opentechconsult.kupon.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
