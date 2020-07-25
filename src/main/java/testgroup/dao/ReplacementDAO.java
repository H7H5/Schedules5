package testgroup.dao;

import testgroup.model.Replacement;

import java.util.List;

public interface ReplacementDAO {
    List<Replacement> allReplacement();
    void add(Replacement replacement);
    void delete(Replacement replacement);
    void edit(Replacement replacement);
    Replacement getById(int id);
}
