package testgroup.service;

import testgroup.model.Replacement;

import java.util.List;

public interface ReplacementService {
    List<Replacement> allReplacement();
    List<Replacement> allActualReplacement();
    void add(Replacement replacement);
    void  delete(Replacement replacement);
    void  deleteById(int id);
    void  edit (Replacement replacement);
    Replacement getById(int id);
    List<Replacement> allParseReplacement();
    void deleteRealacementsByDay(int day);
    void addNewParseReplacement(List<Replacement> replacements);
    void deleteAllReplacement();
}
