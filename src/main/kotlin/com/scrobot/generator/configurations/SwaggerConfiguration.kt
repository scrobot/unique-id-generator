package ru.medissima.backend.common.configuration

import com.fasterxml.classmate.TypeResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.AlternateTypeRules
import springfox.documentation.schema.WildcardType
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.*
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux
import springfox.documentation.swagger.web.SecurityConfiguration as SpingfoxSecurityConfiguration


@Configuration
@EnableSwagger2WebFlux
class SwaggerConfiguration(
    private val resolver: TypeResolver
) {

    @Bean
    fun petApi(info: ApiInfo): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/api.*"))
            .build()
            .alternateTypeRules(
                AlternateTypeRules.newRule(
                    resolver.resolve(Mono::class.java, WildcardType::class.java),
                    resolver.resolve(WildcardType::class.java)
                )
            )
            .alternateTypeRules(
                AlternateTypeRules.newRule(
                    resolver.resolve(Flux::class.java, WildcardType::class.java),
                    resolver.resolve(WildcardType::class.java)
                )
            )
            .apiInfo(info)
    }

    @Bean
    fun apiInfo() = ApiInfo(
        "REST API",
        "",
        "1.0.0-RC1",
        "",
        Contact("team", "", "some@site.com"),
        "",
        "",
        emptyList<ObjectVendorExtension>()
    )

    @Bean
    fun uiConfig(): UiConfiguration {
        return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .displayOperationId(false)
            .defaultModelsExpandDepth(1)
            .defaultModelExpandDepth(1)
            .defaultModelRendering(ModelRendering.EXAMPLE)
            .displayRequestDuration(false)
            .docExpansion(DocExpansion.NONE)
            .filter(false)
            .maxDisplayedTags(null)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .tagsSorter(TagsSorter.ALPHA)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build()
    }

}