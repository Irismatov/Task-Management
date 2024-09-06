package uz.pdp.taskmanagement.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import uz.pdp.taskmanagement.domain.request.FeatureRequest;
import uz.pdp.taskmanagement.domain.response.FeatureResponse;
import uz.pdp.taskmanagement.entity.FeatureEntity;
import uz.pdp.taskmanagement.repository.FeatureRepository;

import java.util.List;
import java.util.UUID;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private ModelMapper modelMapper;

    public FeatureResponse createFeature(FeatureRequest featureRequest) {
        FeatureEntity featureEntity = modelMapper.map(featureRequest, FeatureEntity.class);
        FeatureEntity save = featureRepository.save(featureEntity);
        return modelMapper.map(save, FeatureResponse.class);
    }

    public List<FeatureResponse> getAll() {
        List<FeatureEntity> all = featureRepository.findAll();
        return all.stream().map(allFeature -> modelMapper.map(allFeature, FeatureResponse.class)).toList();
    }


    public FeatureResponse findById(UUID featureId) {
        FeatureEntity featureEntity = featureRepository.findById(featureId).orElseThrow(() -> new RuntimeException("Feature not found"));
        return modelMapper.map(featureEntity, FeatureResponse.class);
    }

    public FeatureResponse updateFeature(FeatureRequest featureRequest, UUID featureId) {
        FeatureEntity featureEntity = featureRepository.findById(featureId).orElseThrow(() -> new RuntimeException("Feature not found"));
        modelMapper.map(featureRequest, featureEntity);
        FeatureEntity save = featureRepository.save(featureEntity);
        return modelMapper.map(save, FeatureResponse.class);
    }

    public void deleteFeature(UUID featureId) {
        featureRepository.deleteById(featureId);
    }

}
