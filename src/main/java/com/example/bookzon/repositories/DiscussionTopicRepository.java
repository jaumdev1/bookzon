package com.example.bookzon.repositories;

import com.example.bookzon.domain.entities.DiscussionTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscussionTopicRepository extends JpaRepository<DiscussionTopic, UUID> {
}
