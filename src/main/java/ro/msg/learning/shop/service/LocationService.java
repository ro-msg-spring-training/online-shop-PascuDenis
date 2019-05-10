package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.LocationDTO;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.mapping.LocationMapper;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.IAddressRepository;
import ro.msg.learning.shop.repository.ILocationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class LocationService implements IService<LocationDTO, Integer> {
    private ILocationRepository locationRepository;
    private IAddressRepository addressRepository;

    @Override
    @Transactional
    public LocationDTO findOne(Integer id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
        return new LocationMapper(addressRepository).convertToDto(location);
    }

    @Override
    @Transactional
    public List<LocationDTO> findAll() {
        LocationMapper mapper = new LocationMapper(addressRepository);
        return StreamSupport.stream(locationRepository.findAll().spliterator(), false).map(mapper::convertToDto).
                collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LocationDTO save(LocationDTO entity) {
        Location locationToSave = new LocationMapper(addressRepository).convertToEntity(entity);
        Location location = locationRepository.save(locationToSave);
        return new LocationMapper(addressRepository).convertToDto(location);

    }

    @Override
    @Transactional
    public LocationDTO update(LocationDTO entity) {
        Location locationToUpdate = locationRepository.findById(entity.getId()).orElseThrow(() -> new LocationNotFoundException(entity.getId()));

        if (entity.getName() != null && !entity.getName().equals(locationToUpdate.getName())){
            locationToUpdate.setName(entity.getName());
        }

        if (entity.getAddress()!= null) {
            Optional<Address> address = addressRepository.findById(entity.getAddressId());
            address.ifPresent(locationToUpdate::setAddress);
        }

        Location updatedLocation = locationRepository.save(locationToUpdate);
        return new LocationMapper(addressRepository).convertToDto(updatedLocation);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        locationRepository.deleteById(id);
    }
}
