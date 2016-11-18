package zzz.framework.command;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import zzz.api.command.CommandHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandHandlerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(final AnnotationMetadata annotationMetadata, final BeanDefinitionRegistry registry) {
        final Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(EnableCommandInfrastructure.class.getName());
        final String[] basePackages = (String[]) annotationAttributes.get("value");
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(CommandHandler.class));
        final Set<BeanDefinition> beanDefinitions = Arrays.stream(basePackages)
                .flatMap(basePackage -> provider.findCandidateComponents(basePackage).stream()).collect(Collectors.toSet());
        beanDefinitions.forEach(beanDefinition -> {
            final String name = BeanDefinitionReaderUtils.generateBeanName(beanDefinition, registry);
            registry.registerBeanDefinition(name, beanDefinition);
        });
    }

}
