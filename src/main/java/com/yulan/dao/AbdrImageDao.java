package com.yulan.dao;

import com.yulan.pojo.AbdrImage;

import java.util.List;

public interface AbdrImageDao {

    List<AbdrImage> getAbdrImage(String abdrId);

    boolean insertAbdrImage(AbdrImage abdrImage);

    boolean deleteAbdrImage(String abdrId);
}
