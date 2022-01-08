package testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.dao.ReplacementDAO;
import testgroup.model.Replacement;
import testgroup.service.ParseReplacement.ConnectionParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReplacementServiceImpl implements ReplacementService {
    private ReplacementDAO replacementDAO;
    private ConnectionParse connectionParse = new ConnectionParse();
    @Autowired
    public void SetRaplacement(ReplacementDAO replacementDAO){
        this.replacementDAO = replacementDAO;
    }

    @Override
    @Transactional
    public List<Replacement> allReplacement() {
        return replacementDAO.allReplacement();
    }

    @Override
    @Transactional
    public List<Replacement> allActualReplacement() {
        return replacementDAO.allReplacement();
    }

    @Override
    @Transactional
    public void add(Replacement replacement) {
        replacementDAO.add(replacement);
    }

    @Override
    @Transactional
    public void delete(Replacement replacement) {
        replacementDAO.delete(replacement);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    @Transactional
    public void edit(Replacement replacement) {
        replacementDAO.edit(replacement);
    }

    @Override
    @Transactional
    public Replacement getById(int id) {
        return replacementDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Replacement> allParseReplacement() {
        List<Replacement> replacements = new ArrayList<>();
        try {
            replacements = connectionParse.connection();
            System.out.println("ggggggggggggggggggggggggggg");
            System.out.println(replacements.size());
            if(replacements.size()>0){
                deleteRealacementsByDay(replacements.get(0).getDay());
                addNewParseReplacement(replacements);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return replacements;
    }
    @Override
    @Transactional
    public void deleteRealacementsByDay(int day){
        List<Replacement> replacements = allReplacement();
        for (int i =0; i<replacements.size();i++){
            if (replacements.get(i).getDay()==day){
                delete(replacements.get(i));
            }
        }
    }
    @Override
    @Transactional
    public void addNewParseReplacement(List<Replacement> replacements){
        for (int i =0; i<replacements.size();i++){
            add(replacements.get(i));
        }
    }

    @Override
    @Transactional
    public void deleteAllReplacement() {
        List<Replacement> replacements = allReplacement();
        for (int i = 0; i <replacements.size() ; i++) {
            delete(replacements.get(i));
        }
    }
}

