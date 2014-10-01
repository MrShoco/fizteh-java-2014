package ru.fizteh.fivt.students.SurkovaEkaterina.shell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Shell {
    private HashMap<String, Command>
            shellCommands = new HashMap<String, Command>();
    private static String invitation = "$ ";
    private String[] arguments;
    private FilesOperations shellOperations = new FilesOperations();

    public final void setArguments(final String[] args) {
        this.arguments = args;
    }

    public final void setShellCommands(final ArrayList<Command<?>> commands) {
        for (Command<?> command:commands) {
            this.shellCommands.put(command.getCommandName(), command);
        }
    }

    public final void beginExecuting() {
        if (arguments.length == 0) {
            interactiveMode();
        } else {
            packageMode();
        }
    }

    private void interactiveMode() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(invitation);
            String command = scanner.nextLine();
            String[] commands = CommandsParser.parseCommands(command);
            if (command.length() == 0) {
                continue;
            }
            for (String currentCommand:commands) {
                if (!execute(currentCommand)) {
                    break;
                }
            }
        }
    }

    private void packageMode() {
        String commandsLine = "";
        for (String s:arguments) {
            commandsLine += arguments + " ";
        }
        String[] commands = CommandsParser.parseCommandParameters(commandsLine);
        for (String currentCommand:commands) {
            boolean result = execute(currentCommand);
            if (result) {
                System.exit(-1);
            }
        }

    }

    private boolean execute(final String currentCommand) {
        String commandName = CommandsParser.getCommandName(currentCommand);
        String parameters = CommandsParser.getCommandParameters(currentCommand);
        if (commandName.equals("")) {
            System.out.println("Empty command name!");
            return true;
        }
        if (!shellCommands.containsKey(commandName)) {
            System.out.println("Unknown command: \'" + commandName + "\'");
            return true;
        }
        boolean result = true;
        try {
            shellCommands.get(commandName)
                    .executeCommand(parameters, shellOperations);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            result = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }
}
