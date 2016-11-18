package zzz.framework.command;

import zzz.api.command.Command;
import zzz.api.command.CommandBus;
import zzz.api.command.CommandHandler;
import zzz.api.command.CommandHandlerProvider;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public final class ApplicationCommandBus implements CommandBus {

    private final CommandHandlerProvider provider;

    public ApplicationCommandBus(final CommandHandlerProvider provider) {
        checkArgument(provider != null, "provider cannot be null");
        this.provider = provider;
    }

    @Override
    public void dispatch(final Command command) {
        checkArgument(command != null, "command cannot be null");
        final Optional<CommandHandler<Command>> commandHandler = provider.provide(command);
        checkState(commandHandler.isPresent(), "no handler registered for command type [%s]", command.getClass().getName());
        commandHandler.get().handle(command);
    }

}
