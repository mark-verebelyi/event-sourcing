package zzz.framework.command;


import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zzz.api.command.CommandBus;
import zzz.api.command.CommandHandler;

import java.util.List;
import java.util.Optional;

@Configuration
public class CommandInfrastructureConfiguration {

    @Autowired(required = false)
    private List<CommandHandler<?>> commandHandlers;

    @Bean({"commandHandlerProvider", "commandHandlerRegistry"})
    public CommandHandlerRepository commandHandlerRepository() {
        final CommandHandlerRepository repository = new CommandHandlerRepository();
        commandHandlers().forEach(repository::register);
        return repository;
    }

    @Bean
    public CommandBus commandBus() {
        return new ApplicationCommandBus(commandHandlerRepository());
    }

    private List<CommandHandler<?>> commandHandlers() {
        return Optional.ofNullable(commandHandlers).orElse(ImmutableList.of());
    }

}
