package io.github.sanjeet291196.sqlitesample;

/**
 * Created by sanjit on 15/8/16.
 * Project: SQLite Sample
 */
public class DataStructure {
    public int DataId;
    public String Data;
    public String Color;

    public DataStructure() {
    }

    public DataStructure(int dataId, String data) {
        DataId = dataId;
        Data = data;
    }

    @Override
    public String toString() {
        return Data;
    }
}
