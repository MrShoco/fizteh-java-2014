/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.kalandarovshakarim.multifilehashmap.database;

import ru.fizteh.fivt.storage.strings.*;
import ru.fizteh.fivt.students.kalandarovshakarim.filemap.table.OneTableBase;

/**
 *
 * @author shakarim
 */
public class DataBase extends OneTableBase {

    private final TableProviderFactory factory;
    private final TableProvider provider;

    public DataBase(String pathToDataBase) {
        this.factory = new DataBaseProviderFactory();
        this.provider = factory.create(pathToDataBase);
    }

    public TableProvider getProvider() {
        return provider;
    }
}
