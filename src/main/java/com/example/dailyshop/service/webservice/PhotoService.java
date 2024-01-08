package com.example.dailyshop.service.webservice;

import com.example.dailyshop.model.entity.Photo;
import com.example.dailyshop.repository.data.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService implements IPhotoService{
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void delete(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    public Optional<Photo> findById(Long id) {
        return photoRepository.findById(id);
    }
}
