package com.socialcooking.repository;

import com.socialcooking.domain.Comment;
import com.socialcooking.repository.api.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository

public class CommentRepositoryImpl extends GenericRepositoryImpl<Comment> implements CommentRepository {

    private Logger log = LoggerFactory.getLogger(CommentRepository.class);




}
