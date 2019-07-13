package com.yulan.service;

import com.yulan.pojo.AbdrImage;

import java.util.Map;

public interface AbdrImageService {

    Map insertAbdrImage(AbdrImage abdrImage);

    Map getAbdrImage(String abdrImageId);
}
