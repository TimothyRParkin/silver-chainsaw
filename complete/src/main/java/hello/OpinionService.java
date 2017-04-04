package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService {

    @Autowired
    protected OpinionsEntryRepository opinionsEntryRepository;

    public List<OpinionsEntry> findAll() {
        return opinionsEntryRepository.findAll();
    }

    public OpinionsEntry save(OpinionsEntry entry) {
        return opinionsEntryRepository.save(entry);
    }

    public void delete(Long id) {
        opinionsEntryRepository.delete(id);
    }

    public OpinionsEntry findOne(Long id) {
        return opinionsEntryRepository.findOne(id);
    }
}
