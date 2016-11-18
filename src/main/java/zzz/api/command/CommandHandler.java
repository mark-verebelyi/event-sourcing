package zzz.api.command;

public interface CommandHandler<C extends Command> {

    void handle(C command);

    Class<C> commandType();

}
