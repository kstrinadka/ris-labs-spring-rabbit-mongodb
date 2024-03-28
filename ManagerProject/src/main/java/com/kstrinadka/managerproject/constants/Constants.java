package com.kstrinadka.managerproject.constants;

public class Constants
{
    public static final int SYMBOLS_IN_ALPHABET = 36;
    public static final int WORKERS_NUMBER;

    static {
        String workersNum = System.getenv("WORKERS_NUM");
        WORKERS_NUMBER = workersNum != null ? Integer.parseInt(workersNum) : 1;
    }
}
