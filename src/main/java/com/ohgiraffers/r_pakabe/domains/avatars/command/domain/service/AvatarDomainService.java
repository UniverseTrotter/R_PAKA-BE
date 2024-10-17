package com.ohgiraffers.r_pakabe.domains.avatars.command.domain.service;

import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.model.Avatar;
import com.ohgiraffers.r_pakabe.domains.avatars.command.domain.repository.AvatarRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvatarDomainService {
    private final AvatarRepository avatarRepository;

    public AvatarDomainService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public List<Avatar> getAllAvatars() {
        return avatarRepository.findAll();
    }

    public Avatar saveAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public Avatar updateAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public void deleteAvatar(Avatar avatar) {
        avatarRepository.delete(avatar);
    }

    @Nullable
    public Avatar getAvatarById(Long id) {
        return avatarRepository.findById(id).orElse(null);
    }

    @Nullable
    public Avatar getAvatarByUserCode(Long userCode) {
        return avatarRepository.findByUserCode(userCode).orElse(null);
    }

}
