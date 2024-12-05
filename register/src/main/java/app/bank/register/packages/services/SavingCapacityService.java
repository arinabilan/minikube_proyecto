package app.bank.register.packages.services;

import app.bank.register.packages.entities.SavingCapacityEntity;
import app.bank.register.packages.repositories.SavingCapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingCapacityService {
    @Autowired
    private SavingCapacityRepository savingCapacityRepository;

    public SavingCapacityEntity getByClientId(Long clientId) {
        return savingCapacityRepository.findByClientId(clientId);
    }

    public SavingCapacityEntity saveCapacity(SavingCapacityEntity saving) {

        return savingCapacityRepository.save(saving); //SavingCapacityEntity sav =
        //return calculateState(saving.getClient().getId(), (long)sav.getId());
    }
}
