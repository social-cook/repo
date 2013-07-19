package com.socialcooking.repository;

import com.socialcooking.domain.Section;

import java.util.List;

/**
 * @author Mikalai Kisel
 */

public interface SectionRepository {

    public Section findById(String name);

    public List<Section> findAll();

    public Section save(Section section);

    public Section update(Section section);

    public void delete(Section section);

}