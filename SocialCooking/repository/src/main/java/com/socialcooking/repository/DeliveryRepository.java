package com.socialcooking.repository;

import com.socialcooking.domain.Comment;
import com.socialcooking.domain.Delivery;

import java.util.List;

/**
 * @author Mikalai Kisel
 */

public interface DeliveryRepository {

    public Delivery findById(Long id);

    public List<Delivery> findAll();

    public Delivery save(Delivery delivery);

    public Delivery update(Delivery delivery);

    public void delete(Delivery delivery);
}
