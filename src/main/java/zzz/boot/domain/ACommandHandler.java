package zzz.boot.domain;

import zzz.api.command.CommandHandler;
import zzz.boot.application.ACommand;

public final class ACommandHandler implements CommandHandler<ACommand> {

    @Override
    public void handle(final ACommand command) {
        System.out.println(command);
    }

    @Override
    public Class<ACommand> commandType() {
        return ACommand.class;
    }

}
