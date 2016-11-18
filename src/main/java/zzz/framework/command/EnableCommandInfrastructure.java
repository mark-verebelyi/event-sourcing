package zzz.framework.command;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CommandInfrastructureConfiguration.class, CommandHandlerRegistrar.class})
public @interface EnableCommandInfrastructure {

    String[] value();

}
