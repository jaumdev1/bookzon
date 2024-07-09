package com.example.bookzon.infrastructure.gateways;

import com.example.bookzon.application.gateways.DiscussionTopicGateway;
import com.example.bookzon.domain.entities.Comment;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.DiscussionTopicWithTop3CommentsDTO;
import com.example.bookzon.infrastructure.repositories.DiscussionTopicRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DiscussionTopicRepositoryGateway implements DiscussionTopicGateway {

    @PersistenceContext
    private EntityManager entityManager;
    private  final DiscussionTopicRepository discussionTopicRepository;

    public DiscussionTopicRepositoryGateway(DiscussionTopicRepository discussionTopicRepository){
        this.discussionTopicRepository = discussionTopicRepository;
    }

    @Override
    public DiscussionTopic createDiscussionTopic(DiscussionTopic discussionTopic) {
       return discussionTopicRepository.save(discussionTopic);
    }

    @Override
    public List<DiscussionTopic> getAllDiscussionTopics() {
        return discussionTopicRepository.findAll();
    }

    @Override
    public DiscussionTopic getDiscussionTopicById(UUID id) {
        Optional<DiscussionTopic> discussionTopicOptional=  discussionTopicRepository.findById(id);
        return discussionTopicOptional.orElse(null);
    }
    @Override
    @Transactional
    public Optional<DiscussionTopicWithTop3CommentsDTO> getDiscussionTopicWithCTop3CommentById(UUID id) {
        List<Object[]> results = discussionTopicRepository.findWithTop3CommentsById(id);

        if (results.isEmpty()) {
            return Optional.empty();
        }

        DiscussionTopicWithTop3CommentsDTO dto = new DiscussionTopicWithTop3CommentsDTO();
        List<DiscussionTopicWithTop3CommentsDTO.CommentDTO> comments = new ArrayList<>();

        for (Object[] row : results) {
            if (dto.getId() == null) {
                dto.setId((UUID) row[0]);
                dto.setTitle((String) row[1]);
                dto.setDescription((String) row[2]);
                dto.setBookId((UUID) row[3]);
                dto.setBookTitle((String) row[4]);
                dto.setUserId((UUID) row[5]);
                dto.setCreatorUsername((String) row[6]);
            }

            if (row[7] != null) {
                DiscussionTopicWithTop3CommentsDTO.CommentDTO comment = new DiscussionTopicWithTop3CommentsDTO.CommentDTO();
                comment.setCommentId((UUID) row[7]);
                comment.setCommentContent((String) row[8]);
                comment.setCommentUserId((UUID) row[9]);
                comment.setCommentUsername((String) row[10]);
                comment.setParentCommentId((UUID) row[11]);
                comments.add(comment);
            }
        }

        dto.setTopComments(comments);
        return Optional.of(dto);

    }
    @Override
    public DiscussionTopic updateDiscussionTopic(DiscussionTopic discussionTopic) {
        return null;
    }
    @Override
    public void deleteDiscussionTopic(UUID id) {

    }
}
