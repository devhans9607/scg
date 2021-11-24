package com.example.gw;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.Constants;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class SwaggerConfig {
//    @Autowired
//    RouteDefinitionLocator locator;
//
//    @Bean
//    public GroupedOpenApi userOpenApi() {
//        return GroupedOpenApi.builder()
//                .group("users")
//                .packagesToScan("/**").addOpenApiCustomiser(serverOpenApiCustomiser1())
//                .addOperationCustomizer(operationCustomizer())
//                .build();
//    }
//
//    OperationCustomizer operationCustomizer() {
//        return (Operation operation, HandlerMethod handlerMethod) -> {
//            CustomizedOperation annotation = handlerMethod.getMethodAnnotation(CustomizedOperation.class);
//            if (annotation != null) {
//                operation.description(StringUtils.defaultIfBlank(operation.getDescription(), Constants.DEFAULT_DESCRIPTION) + ", " + annotation.addition());
//            }
//            return operation;
//        };
//    }
//
//    public OpenApiCustomiser serverOpenApiCustomiser1() {
//        Server server = new Server().url("http://localhost:9101").description("myserver1");
//        List<Server> servers = new ArrayList<>();
//        servers.add(server);
//        return openApi -> openApi.setServers(servers);
//    }


//    @Bean
//    public List<GroupedOpenApi> apis() {
//        List<GroupedOpenApi> groups = new ArrayList<>();
//        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//        definitions
////                .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
//                .forEach(routeDefinition -> {
//                    log.error("routerDefinition: " + routeDefinition.toString());
//                    String name = routeDefinition.getId().replaceAll("-service", "");
//                    log.error(name);
//                    GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").setGroup(name).build();
//        });
//        log.error("groups : ", groups.toString());
//        return groups;
//    }

//    @Bean
//    public GroupedOpenApi boardOpenApi() {
//        String paths[] = {"/api/v1/board/**"};
//        return GroupedOpenApi.builder().setgroup("board").pathsToMatch(paths)
//                .build();
//    }

//    @Bean
//    public String apis(RouteDefinitionLocator locator, SwaggerUiConfigProperties swaggerUiConfig) {
//        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//        definitions.stream()
//                .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
//                            .forEach(routeDefinition -> {
//            String name = routeDefinition.getId().replaceAll("-service", "");
//            swaggerUiConfig.addGroup(name);
//        });
//        return "";
//    }


    //https://stackoverflow.com/questions/43416227/commandlinerunner-and-beans-spring
    @Bean
    public CommandLineRunner openApiGroups(RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiParameters) {
        return args -> locator
                .getRouteDefinitions().collectList().block()
                .stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches(".*-service"))
                .map(id -> id.replace("-service", ""))
                .forEach(swaggerUiParameters::addGroup);
    }
}
