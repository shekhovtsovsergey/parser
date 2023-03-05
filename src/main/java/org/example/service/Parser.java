package org.example.service;

import java.io.File;
import java.io.FileFilter;

public interface Parser {

    void jsonParser(File file);
    void csvParser(File file);

}
