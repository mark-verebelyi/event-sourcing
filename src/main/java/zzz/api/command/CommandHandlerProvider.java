package zzz.api.command;

import java.util.Optional;

public interface CommandHandlerProvider {

    <C extends Command> Optional<CommandHandler<C>> provide(C command);

}
