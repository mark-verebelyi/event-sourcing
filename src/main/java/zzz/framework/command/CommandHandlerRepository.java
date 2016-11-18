package zzz.framework.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zzz.api.command.Command;
import zzz.api.command.CommandHandler;
import zzz.api.command.CommandHandlerProvider;
import zzz.api.command.CommandHandlerRegistry;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.google.common.base.Preconditions.checkArgument;

public final class CommandHandlerRepository implements CommandHandlerProvider, CommandHandlerRegistry {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ConcurrentMap<Class<?>, CommandHandler<?>> repository = new ConcurrentHashMap<>();

    @Override
    public void register(final CommandHandler<?> commandHandler) {
        checkArgument(commandHandler != null, "command handler cannot be null");
        final Class<?> commandType = commandHandler.commandType();
        final CommandHandler<?> prev = repository.put(commandType, commandHandler);
        if (prev != null) {
            throw new IllegalStateException(String.format("command handler already registered for [%s]; previous [%s], current [%s]",
                    commandType.getName(),
                    prev.getClass().getName(),
                    commandHandler.getClass().getName()));
        }
        logger.debug("registered command handler for command type [{}] -> {}", commandType.getName(), commandHandler.getClass().getName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C extends Command> Optional<CommandHandler<C>> provide(final C command) {
        checkArgument(command != null, "command cannot be null");
        final CommandHandler<?> commandHandler = repository.get(command.getClass());
        return Optional.ofNullable((CommandHandler<C>) commandHandler);
    }


}
