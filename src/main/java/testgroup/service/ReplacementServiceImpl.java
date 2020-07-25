package testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.dao.ReplacementDAO;
import testgroup.model.Replacement;

import java.util.List;

@Service
public class ReplacementServiceImpl implements ReplacementService {
    ReplacementDAO replacementDAO;
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
    @Transactional
    public void edit(Replacement replacement) {
        replacementDAO.edit(replacement);
    }

    @Override
    @Transactional
    public Replacement getById(int id) {
        return replacementDAO.getById(id);
    }
}

