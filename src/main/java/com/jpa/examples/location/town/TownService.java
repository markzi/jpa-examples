package com.jpa.examples.location.town;

import com.jpa.examples.location.town.TownRepository;
import com.jpa.examples.location.town.TownEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownService {
    private final TownRepository townRepository;

    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    /**
     * Needs @Transactional because we are streaming the results back
     *
     * @return
     */
    @Transactional
    public List<TownResponse> findAll() {
        return townRepository.findAll().stream().map(TownResponse.convert).collect(Collectors.toList());
    }

    public TownResponse findById(Long id) {
        return townRepository.findById(id).map(TownResponse.convert).get();
    }
}
