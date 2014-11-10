package ru.fizteh.fivt.students.vadim_mazaev.multifilemap.commands;

import ru.fizteh.fivt.students.vadim_mazaev.multifilemap.TableManager;

public final class DropCommand extends DbCommand {
    public DropCommand(TableManager manager) {
        super(manager);
    }

    @Override
    public boolean checkArgs(int argLen) {
        return (argLen == 2);
    }

    @Override
    public void run(String[] cmdWithArgs) {
        try {
            getManager().removeTable(cmdWithArgs[1]);
            System.out.println("dropped");
        } catch (IllegalStateException e) {
            System.out.println(cmdWithArgs[1] + " not exists");
        }
    }
}
