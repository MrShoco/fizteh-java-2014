package ru.fizteh.fivt.students.andrey_reshetnikov.MultiFileHashMap;

public class MultiListCommand extends Command {
    @Override
    public void execute(DataBaseOneDir base) throws Exception {
        if (base.getUsing() == null) {
            System.out.println("no table");
        } else {
            StringBuilder allKeys = new StringBuilder();
            ListCommand list = new ListCommand();
            for (int i = 0; i < NUM_DIRECTORIES; i++) {
                for (int j = 0; j < NUM_FILES; j++) {
                    DataBaseOneFile current = base.getUsing().databases[i][j];
                    if (current != null) {
                        String newList = list.getList(current);
                        if (newList.length() > 0) {
                            if (allKeys.length() > 0) {
                                allKeys.append(", ");
                            }
                            allKeys.append(newList);
                        }
                    }
                }
            }
            System.out.println(allKeys.toString());
        }
    }
}
