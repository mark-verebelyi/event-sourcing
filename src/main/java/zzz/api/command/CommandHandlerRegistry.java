package zzz.api.command;

public interface CommandHandlerRegistry {

    void register(CommandHandler<?> commandHandler);

}
